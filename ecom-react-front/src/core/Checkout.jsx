import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { isAuthenticated } from '../helpers/IsAuthenticated'
import { createOrder, getBraintreeToken, processPayment } from './ApiCore';
import DropIn from "braintree-web-drop-in-react";
import { notify } from '../helpers/Toast';
import { empthCart } from '../helpers/helpers';
const Checkout = ({products}) => {
    // const [adress, setAdress] = useState('');
    const [data, setData] = useState({
        braintreaToken: null,
        error: null,
        instance: {},
        address: ''
    });

    const userId = isAuthenticated() && isAuthenticated().user._id;
    // console.log(userId);
    const token = isAuthenticated() && isAuthenticated().token;
    useEffect(() => {
        getBraintreeToken(userId,token)
        .then(res => setData({...data, braintreaToken: res.token}))
        .catch(err => setData({...data,error: err}))
    }, []);

    const totalToCheckout = (products) => {
      return  products.reduce((total, product) => total +(product.count * product.price),0 )
    }

    const dropIn = () => (
        <div>
        {data.braintreaToken !== null && products.length > 0 && (
            <DropIn  options={{
                authorization: data.braintreaToken,
                paypal: {
                    flow: "vault"
                }
            }}  
                onInstance={instance => data.instance = instance}
            />
        )}
        </div>
    )
    const Buy = () => {
        const deleveryAddresss= data.address;
        data.instance.requestPaymentMethod()
        .then(data => {
            let paymentData = { 
                amount:totalToCheckout(products),
                paymentMethodNone: data.nonce
            }
            processPayment(userId,token, paymentData)
            .then(res => {
                console.log("eeeee")

                console.log(res)
                let orderData = {
                    products,
                    transaction_id: res.transaction.id,
                    amount: res.transaction.amount,
                    address: deleveryAddresss
                }
                createOrder(userId,token,orderData)
                .then((res) => {
                    console.log(res)
                })
                .catch(err => {
                    console.log(err)

                })
                empthCart(() => {
                    notify('success',"Thanks", 'Payment succufuly')
                })
                
              
            })
            .catch(error =>  {   
                notify('danger',"inValid",error)
            } )
           

        })
        .catch(err => {
            notify('warning','InValid',err.message,)

        })
    }
    const showBtnToCheckout = () => {
        if(isAuthenticated()) {
            return (
            <>
            {dropIn()}
            <button onClick={Buy} className="btn btn-success ">
                Pay
            </button>
            
            </>)
        }else{
            return (
            <Link to={'/signin'}>
                <button className="btn btn-warning btn-block">
            sign to checkout
        </button>

            </Link>)
        }
    }

    const inputHadnler = (e) => {
       setData({...data,address: e.target.value})
    }
  return (
    <div>
        <h2>Total : {totalToCheckout(products)} </h2>
        <label htmlFor='address'>Adsress delivery</label>
        <textarea id='address' className='form-control' row='2' onChange={inputHadnler}></textarea>
        {showBtnToCheckout()}
    </div>
  )
}

export default Checkout