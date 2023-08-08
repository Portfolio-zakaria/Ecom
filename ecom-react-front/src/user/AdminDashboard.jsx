import React from 'react'
import { Link } from 'react-router-dom'
import { Card, CardBody, CardHeader, CardTitle, Col, Row } from 'reactstrap'
import Layout from '../core/Layout'
import { isAuthenticated } from '../helpers/IsAuthenticated'

const AdminDashboard = () => {
    const {user: {name, email, role}} = isAuthenticated();
  return (
    <div>
        <Layout  
                title="Admin Dashboard"
                description={`Welcome ${name}`}
                className='container'>
            <Row>
                <Col md={'3'}>
                    <Card>
                        <CardBody>
                        <CardHeader>Admin Links</CardHeader>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>
                                    <Link className='nav-link' to='/admin/category/create' >Create Category</Link> 
                                </li>
                                <li className='list-group-item'>
                                <Link className='nav-link' to='/admin/product/create' >Create Product</Link> 

                                </li>
                                <li className='list-group-item'>
                                <Link className='nav-link' to='/admin/orders' >Orders</Link> 

                                </li>
                            </ul>
                           
                        </CardBody>
                    </Card>
                </Col>
                <Col md={'9'}>
                    <Card>
                        <CardBody>
                        <CardHeader>Admin Info</CardHeader>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item'>{name}</li>
                                <li className='list-group-item'>{email}</li>
                                <li className='list-group-item'>{role ? 'Admin': 'user'}</li>
                            </ul>
                            <hr />
                           
                        </CardBody>
                    </Card>
                </Col>
               
            </Row>
        </Layout>
    </div>
  )
}

export default AdminDashboard