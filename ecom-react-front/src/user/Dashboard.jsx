import React from 'react'
import { Link } from 'react-router-dom'
import { Card, CardBody, CardHeader, CardTitle, Col, Row } from 'reactstrap'
import Layout from '../core/Layout'
import { isAuthenticated } from '../helpers/IsAuthenticated'

const Dashboard = () => {
    const {user: {name, email, role}} = isAuthenticated();
  return (
    <div>
        <Layout  
                title="dashboard"
                description={`Welcome ${name}`}
                className='container'>
            <Row>
                <Col md={'3'}>
                    <Card>
                        <CardBody>
                        <CardHeader>User Links</CardHeader>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>
                                    <Link className='nav-link' to='/cart' >My Cart</Link> 
                                </li>
                                <li className='list-group-item'>
                                <Link className='nav-link' to='/profile' >Profile</Link> 

                                </li>
                                <li className='list-group-item'></li>
                            </ul>
                           
                        </CardBody>
                    </Card>
                </Col>
                <Col md={'9'}>
                    <Card>
                        <CardBody>
                        <CardHeader>User Information</CardHeader>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>{name}</li>
                                <li className='list-group-item'>{email}</li>
                                <li className='list-group-item'>{role ? 'Admin': 'user'}</li>
                            </ul>
                            <hr />
                            <CardHeader>Purshase History</CardHeader>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'></li>
                                <li className='list-group-item'></li>
                                <li className='list-group-item'></li>
                            </ul>
                           
                        </CardBody>
                    </Card>
                </Col>
               
            </Row>
        </Layout>
    </div>
  )
}

export default Dashboard