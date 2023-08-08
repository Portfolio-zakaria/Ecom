import React, { useEffect, useState } from 'react'
import Layout from './Layout'
import {getCategories, getProducts} from './ApiCore'
import CardC from './CardC';
import { Col, Row } from 'reactstrap';
import Search from './Search';
const Home = () => {
   const [productsBestSellers, setProductsBestSellers] = useState([]);
   const [productsArrivals, setProductsArrivals] = useState([]);
   const [categories,setCategories] = useState([])
   useEffect(() => {
   getProducts({sortBy:'sold',order:'desc',limit:6}).then(products => setProductsBestSellers(products))
   getProducts({sortBy:'createdAt',order:'desc',limit:3}).then(products =>  setProductsArrivals(products))

    
   }, []);
   useEffect(() => {
    getCategories().then(categories => setCategories(categories))

}, []);
  return (
      <div>
        <Layout 
                title="Home page" 
                description="Node react Ecommerce app" 
                className="container">

                  <Search categories={categories} />
                  <hr />
            <h2>Arrivales</h2>
            <Row>
            {productsArrivals.map((product, i )=> (
              <Col md={4} key={i}>
                <CardC product={product} ></CardC>
              </Col>
            ))}

            </Row>

            <hr />
            <h2>Best Seller </h2>
           

            <Row>
            {productsBestSellers.map((product, i )=> (
              <Col md={4} key={i}>
                <CardC product={product} ></CardC>
              </Col>
            ))}

            </Row>
        </Layout>
             
      </div>
  )
}

export default Home