import { ErrorMessage, Field, Form, Formik } from 'formik';
import React from 'react'
import { Alert, Button, Col, FormGroup, Label, Row } from 'reactstrap';
import { API_URL } from '../../config';
import * as Yup from 'yup'
import Layout from '../../core/Layout'
import { isAuthenticated } from '../../helpers/IsAuthenticated';
import { notify } from '../../helpers/Toast';
import { useNavigate } from 'react-router-dom';
const AddCategory = () => {
    const navigate = useNavigate();
    const initialValues = { name: '' }
    const validationSchema = Yup.object({
        name: Yup.string().required(),
    });
    const onSubmit = (values, onSubmitProps) => {
        const {user, token} = isAuthenticated();
        fetch(`${API_URL}/categories/create/${user._id}`,{
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(values)
        } )
         .then(res =>res.json() )
        .then(res =>{
            if(res.error){
                notify('warning',res.error, "Please Check Your Form !")
            }else{

                notify('success',`Category ${values.name} created`)
               navigate('/admin/dashboard')
            }
        })
        .catch(error =>{
            console.log(error)
            notify('error','Internal Server Error')})
     }
    return (
        <div>
            <Layout
                title="Category"
                description="Create Category"
                className="container">
                <Row className="justify-content-md-center">
                    <Col md="6">
                        <Formik initialValues={initialValues}
                            onSubmit={onSubmit}
                            validationSchema={validationSchema}
                            validateOnChange={false} >

                            {formik => (

                                <Form>
                                    <FormGroup className="mb-3" >
                                        <Label>Name:</Label>
                                        <Field className='form-control' type="text"  name="name"
                                            id="name" placeholder="Enter name" />


                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='name' component={Alert} />
                                    <Button outline color={'success'} block disabled={!formik.isValid} >{'Save'}</Button>

                                </Form>
                            )}
                        </Formik>
                    </Col>
                </Row>
            </Layout>
        </div>
    )
}

export default AddCategory