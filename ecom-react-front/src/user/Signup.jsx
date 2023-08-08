import React from 'react'
import { ErrorMessage, Field, Formik,Form } from 'formik'
import * as Yup from 'yup'
import { Alert, Button, Col,  FormGroup, Label, Row } from 'reactstrap';
import {useNavigate} from 'react-router-dom'
import Layout from '../core/Layout'
import { API_URL } from '../config';
import {notify} from '../helpers/Toast';
const Signup = () => {
    const navigate = useNavigate();
    const initialValues = {name: '', email: '', password: ''}
    const validationSchema = Yup.object({
        name: Yup.string().required(),
        password: Yup.string().required(),
        email: Yup.string().email().required()
     });
     const onSubmit = (values, onSubmitProps) => {
        fetch(`${API_URL}/signup`,{
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(values)
        } )
         .then(res =>res.json() )
        .then(res =>{
            if(res.error){
                notify('warning',res.error, "Please Check Your Form !")
            }else{

                notify('success','Compte is created SuccessFully', "New Account")
               navigate('/signin')
            }
        })
        .catch(error =>
            notify('error','Internal Server Error')
        )

     }
  return (
    <Layout 
            title="Signup page" 
            description="SignUp Ecommerce app" 
            className="container">
    <Row  className="justify-content-md-center">
         <Col md="6">
         <Formik initialValues={ initialValues} 
          onSubmit={onSubmit}
                 validationSchema={validationSchema} 
                 validateOnChange={false} >

            {formik => (
                
         <Form>
            <FormGroup className="mb-3" >
                <Label>Name:</Label>
                <Field className='form-control' type="text" name="name" 
                                   id="name" placeholder="Enter name" />    
                       
                        
            </FormGroup>
                 <ErrorMessage className='alert-danger' name='name' component={Alert}/>
            <FormGroup className="mb-3" >
                <Label>Email address</Label>
                <Field className='form-control' type="email" name="email" 
                                   id="email" placeholder="Enter email" />    
                       
            </FormGroup>
                 <ErrorMessage className='alert-danger ' name='email' component={Alert}/>

            <FormGroup className="mb-3" >
                <Label>Password</Label>
                <Field className='form-control' type="password" name="password" 
                                   id="password" placeholder="Password" />    
                       
            </FormGroup>
                 <ErrorMessage className='alert-danger' name='password' component={Alert}/>
                 <Button outline color={'success'} block  disabled={!formik.isValid} >{'SignUp'}</Button>

            </Form>
            )}
            </Formik>
         </Col>
    </Row>
    </Layout>
  )
}

export default Signup