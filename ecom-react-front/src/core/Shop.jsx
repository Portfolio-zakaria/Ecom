import React, { useEffect, useState } from 'react'
import { Col, Row } from 'reactstrap'
import { getCategories,filterProducts } from './ApiCore';
import CardC from './CardC';
import FilterByCategory from './FilterByCategory';
import FilterByPrice from './FilterByPrice';
import Layout from './Layout'

const Shop = () => {
    const [categories, setCategories] = useState([]);
    const [productsFiltred, setProductsFiltred] = useState([]);
    const [limit, setLimit] = useState(6);
    const [skip, setSkip] = useState(0);
    const [size, setSize] = useState(0)
    const [myFilters, setMyFilters] = useState({
        category: [],
        price: []
    });

    useEffect(() => {
        getCategories().then(categories => setCategories(categories))

    }, []);
 
    const handelFilters = (data, filterBy) => {
        // console.log('shop', data , filterBy)
        setMyFilters({...myFilters, [filterBy]: data})
       
    }
    useEffect(() => {
        filterProducts(skip, limit, myFilters)
            .then(res => {
                setProductsFiltred(res)
                setSkip(0)
                setSize(res.length)

            })
    }, [myFilters]);

    const loadMore = () => {
        
        const toSkip = skip + limit;

        filterProducts(toSkip, limit, myFilters)
        .then(res => {
            setProductsFiltred([...productsFiltred, ...res])
            setSize(res.length)
            setSkip(toSkip)
        })
    }
  return (
    <div>
        <Layout 
                title="Shop" 
                description="Your Favorite Product in our Store" 
                className="container">
                    <Row>
                        <Col md={4}>
                    <FilterByCategory 
                         categories={categories} 
                         handelFilters={(data) => handelFilters(data,'category')}/>
                         <hr/>
                         <FilterByPrice  handelFilters={(data) => handelFilters(data,'price')}/>
                        </Col>
                        <Col md={6}>
                            <h4 >Best Seller</h4>
                            <div className='row mt-3 mb-5'>
                            {
                                productsFiltred.map((product, i)  => (
                                    
                                   <div key={i} className="col-md-4">
                                        <CardC product={product} />
                                   </div>
                                ))}

                            </div>
        { size > 0 &&  size >= limit &&
            (
                <div className="text-center">
                     <button onClick={loadMore} className="btn btn-outline-dark">Load More</button> 
                </div>
            )
}
                        </Col>

                    </Row>

        </Layout>
    </div>
  )
}

export default Shop