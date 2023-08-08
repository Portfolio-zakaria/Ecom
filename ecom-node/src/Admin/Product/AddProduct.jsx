import { ErrorMessage, Field, Form, Formik } from 'formik';
import React, { useEffect, useState } from 'react'
import { Alert, Button, Col, FormGroup, Input, Label, Row } from 'reactstrap';
import { API_URL } from '../../config';
import Layout from '../../core/Layout'
import { isAuthenticated } from '../../helpers/IsAuthenticated';
import { notify } from '../../helpers/Toast';
import * as Yup from 'yup'
import { useNavigate } from 'react-router-dom';
import { getCategories } from '../../core/ApiCore';

const AddProduct = () => {
    const navigate = useNavigate();
     const [categories, setCategories] = useState([]);
    const initialValues = { name: '', description: '', price: 0, quantity: 0, photo: '',category: '', shipping: false }
    const validationSchema = Yup.object({
        name: Yup.string().required(),
        description: Yup.string().required(),
        price: Yup.number().required(),
        quantity: Yup.number().required(),
        // photo: Yup.string().required(),
        category: Yup.string().required(),
        shipping:  Yup.boolean().required(),
    });

   
    const onSubmit = (values, onSubmitProps) => {
        console.log(values)
        const formData = new FormData();
            for (let index = 0; index < Object.keys(values).length; index++) {
            formData.append(Object.keys(values)[index],Object.values(values)[index])
            
        }
            for (var key of formData.entries()) {
                console.log(key[0] + ', ' + key[1])
            }
            
          
        // formData.set(Object.keys(values),Object.values(values))
        //   console.log(formData)
        const {user, token} = isAuthenticated();
        fetch(`${API_URL}/products/create/${user._id}`,{
            method: "POST",
            headers: {
                "Accept": "*/*",
                "Authorization": `Bearer ${token}`
            },
            body: formData
        } )
         .then(res =>res.json() )
        .then(res =>{
            if(res.err){
                notify('warning',res.err, "Please Check Your Form !")
            }else{

                notify('success',`Product ${values.name} created`)
               navigate('/admin/dashboard')
            }
        })
        .catch(error =>
            notify('error','Internal Server Error')
        )
     }

     useEffect(() => {
      getCategories().then(categories => setCategories(categories))
     }, []);
    return (
        <div>
            <Layout
                title="Product"
                description="Create Product"
                className="container">
                <Row className="justify-content-md-center mb-5">
                    <Col md="6">
                        <Formik initialValues={initialValues}
                            onSubmit={onSubmit}
                            validationSchema={validationSchema}
                            validateOnChange={false} >

                            {formik => (

                                <Form>
                                      <FormGroup className="mb-3" >
                                        <Label>Photo:</Label>
                                        <Input className='form-control' type="file" 
                                        onChange={(event) => formik.setFieldValue("photo",event.target.files[0])} 
                                        //  name="photo"
                                            id="photo"  />
                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='photo' component={Alert} />
                                    <FormGroup className="mb-3" >
                                        <Label>Name:</Label>
                                        <Field className='form-control' type="text"  name="name"
                                            id="name" placeholder="Enter name" />
                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='name' component={Alert} />
                                   
                                    <FormGroup className="mb-3" >
                                        <Label>Description:</Label>
                                        <Field as='textarea'  className='form-control' type="text"  name="description" 
                                            id="description" placeholder="Enter description" />
                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='description' component={Alert} />
                                   <Row>
                                  <Col md={6}>
                                    <FormGroup className="mb-3" >
                                        <Label>Price:</Label>
                                        <Field className='form-control' type="number"  name="price"
                                            id="price"  />
                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='price' component={Alert} />
                                   
                                  </Col>
                                  <Col md={6}>
                                    <FormGroup className="mb-3" >
                                        <Label>Quantity:</Label>
                                        <Field className='form-control' type="number"  name="quantity"
                                            id="quantity"  />
                                    </FormGroup>
                                    <ErrorMessage className='alert-danger' name='quantity' component={Alert} />
                                   
                                  </Col>
                                       
                                    </Row>
                                    <Row >
                       <Col md={12}>
                       <FormGroup>
                            <Label for="category">Category</Label>
                            <Field as="select" className="form-control"  name="category" id="category"  >
                            <option value="0" >Select your ...</option>
                               {
                                categories && categories.map(category => {
                                  return (
                                     <option  key={category._id} value={category._id}>{category.name}</option>
                                  )
                                })
                              }
                            </Field> 
                            </FormGroup>
                           <ErrorMessage className='alert-danger' name='category' component={Alert} />

                       </Col>
                    </Row>
                    
                      <FormGroup  inline>
                           <Label >
                           <Field type="checkbox"  name='shipping'   /> {'  '}  
                           shipping
                           </Label>
                       </FormGroup>
                        <ErrorMessage className='alert-danger' name='shipping' component={Alert} />
                        <Row >
                       <Col md={12}>
                         <Button outline color={'success'} block disabled={!formik.isValid} >{'Save'}</Button>
</Col></Row>
                                </Form>
                            )}
                        </Formik>
                    </Col>
                </Row>
            </Layout>
        </div>
    )
}

export default AddProduct