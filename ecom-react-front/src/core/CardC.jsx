
import React from 'react'
import { useDispatch } from 'react-redux'
import { Link } from 'react-router-dom'
import { Badge, Button, Card, CardBody, CardHeader, Col, Row } from 'reactstrap'
import { addToCart } from '../redux/actions/cartActions'
import ShowImage from './ShowImage'

const CardC = ({product,showViewBtn = true}) => {
  const dispatch = useDispatch();
  return (
    <div>
        <Card >
            <CardHeader>{product.name}</CardHeader>
            <ShowImage item={product} url="products/photo" className={'card-img-top img-card'}  />
            <CardBody>
              <p>{product.description}</p>
              <div> 
                <Badge  color='info'> ${product.price}</Badge>
                <br/>
                <Badge  color='danger' > {product.category ? product.category.name :''}</Badge>

              </div>
            
                <Row>
                  
                  <Col md={6}>
                  {showViewBtn && (
                <Link to={`/product/${product._id}`} >
              <Button   color={'warning'}   >{'View'}</Button>

              </Link>
                  )}
                  </Col>
                  <Col md={6}>
              <Button  color={'success'} onClick={() => dispatch(addToCart(product))}   >{'Add To Cart'}</Button>
                    </Col>
                </Row>

            </CardBody>
        </Card>
    </div>
  )
}

export default CardC