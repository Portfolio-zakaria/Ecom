import React, { useEffect, useState } from 'react'
import { Col, Row } from 'reactstrap';
import moment from 'moment'
import Layout from '../../core/Layout';
import { isAuthenticated } from '../../helpers/IsAuthenticated';
import { getStatus, listOfOrders, updateOrderStatus } from '../ApiAdmin';

const ListOrder = () => {
 const [orders, setOrders] = useState([]);
 const [status, setStatus] = useState([]);

 const { user, token } = isAuthenticated();
 const loadOrders = (user,token) =>  {
     listOfOrders(user._id, token)
        .then((res) => {
                setOrders(res)   
        })
        .catch(err => {
            console.error(err)
        })
 }
 const loadStatus = (user,token) =>  {
    getStatus(user._id, token)
       .then((res) => {
               setStatus(res.status)   
       })
       .catch(err => {
           console.error(err)
       })
}
useEffect(() => {
    loadOrders(user,token)
    loadStatus(user,token)
}, []);
const notOrders = () => {
    if(orders.length === 0){
        return(
            <div className="alert alert-warning text-center my-5">
                Not Orders Yet
            </div>
        )
    }
    else {
        return(
            <div className="alert alert-info text-center my-5">
                Total Orders {orders.length}
            </div>
        )
    }
}
const showInput = (key, value) => {
    return (
        <div className="form-group">
            <label htmlFor={key}>{key}</label>
            <input id={key} type="text" value={value} readOnly className="form-control" />
        </div>
    )
}
const handleStatus = (e,order) => {
    updateOrderStatus(user._id, token,order._id, e.target.value)
    .then(res => {
        if(res.error){
            console.log(res.error)
        }
        loadOrders(user,token);
    })
}
const showStatus = (order) => {
    return status.length && (
        <>
        <h4>Status: {order.status}</h4>
        <select onChange={e => handleStatus(e, order)} className='form-control'>
            <option value={''}>Select status</option>
            {
                status.map(s=> (
                    <option key={s} value={s}>{s}</option>
                ))
            }
        </select>
        </>
    )
}
const showOrders = () => {
    return orders.length && orders.map(order => (
        <div className="my-3" key={order._id}>
            <ul className="list-group">
                <li className="list-group-item active"><strong>Transaction Id : </strong> {order.transaction_id}</li>
                <li className="list-group-item"><strong>Amount : </strong> {order.amount} $</li>
                <li className="list-group-item">{showStatus(order)} </li>
                <li className="list-group-item"><strong>Order on : </strong>{moment(order.createdAt).fromNow()}</li>
                <li className="list-group-item"><strong>Customer : </strong>{order.user.name}</li>
                <li className="list-group-item"><strong>Delivery Address : </strong>{order.address}</li>
        
            </ul>

            <div className="my-5">
                <h3 className="display-4 my-3">Total: {order.products.length}</h3>
                <div className="row">
                {order.products.map(product => (
                    <div className="col-md-4" key={product._id}>

                <div className="card text-white bg-primary mb-3" >
                  
                  <div className="card-body">
                    <h4 className="card-title">{product.name}</h4>
                    {showInput('Product ID', product._id)}
                    {showInput('Product name', product.name)}
                    {showInput('Product price', product.price)}
                    {showInput('Product count', product.count)}
                  </div>
                </div>
                    </div>

                ))}
                </div>
            </div>
        </div>
    )) 
}
  return (
    <div>
         <Layout
                title="Orders"
                description="List Orders"
                className="container">
                <Row className="justify-content-md-center mb-5">
                    <Col md="6">
                        {notOrders()}
                        {showOrders()}
                    </Col>
                </Row>
        </Layout>
    </div>
  )
}

export default ListOrder