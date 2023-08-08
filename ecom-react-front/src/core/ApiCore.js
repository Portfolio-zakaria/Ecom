import { API_URL } from "../config"
import { notify } from "../helpers/Toast"
import queryString from 'query-string'

export const getProducts = (params) =>{
    let query = queryString.stringify(params)
   return fetch(`${API_URL}/products?${query}`)
    .then(res =>res.json())
    .then(res =>res.products)
    .catch(err => console.log(err))
}

export const getCategories = () => {
  return  fetch(`${API_URL}/categories`)
    .then(res =>res.json() )
   .then(res =>res.categories)
   .catch(error =>
       notify('error','Internal Server Error')
   )
}

export const filterProducts = (skip, limit, filters) => {
    console.log(filters)
    const data = {
        skip,
        limit,
      filters
    }
    return  fetch(`${API_URL}/products/search`,{
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
      .then(res =>res.json() )
     .then(res =>res.products)

     .catch(error =>{
         console.log(error)
         notify('error','Internal Server Error')
        }
     )
  }


  export const getProduct = (id) => {
    return  fetch(`${API_URL}/products/${id}`,{
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type":"application/json"
        }
    })
      .then(res =>res.json() )
     .then(res =>res.product)
     .catch(error =>{
         notify('error','Internal Server Error')
         console.log(error)
     }
     ) 
  }
  export const relatedProducts = (id) => {

    return fetch(`${API_URL}/products/related/${id}`)
      .then(res => res.json())
      .then(res => res.products)
      .catch(err => console.error(err))
    }

    export const getBraintreeToken = (userId,token) => {

      return fetch(`${API_URL}/braintree/getToken/${userId}`,
      {
        method: "GET",
        headers: {
            Accept: "application/json",
            ContentType:"application/json",
            Authorization: `Bearer ${token}`
        }
      })
        .then(res => res.json())
      
      }

      
    export const processPayment = (userId,token, paymentData) => {

      return fetch(`${API_URL}/braintree/purchase/${userId}`,
      {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type":"application/json",
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(paymentData) 
      })
        .then(res => res.json())
      
      }

      export const createOrder = (userId,token, orderData) => {

        return fetch(`${API_URL}/orders/create/${userId}`,
        {
          method: "POST",
          headers: {
              Accept: "application/json",
              "Content-Type":"application/json",
              Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(orderData) 
        })
          .then(res => res.json())
        
        }
