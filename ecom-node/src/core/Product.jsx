import React, { useEffect, useState } from 'react'
import { useParams } from "react-router-dom";
import { getProduct, relatedProducts } from './ApiCore';
import CardC from './CardC';
import Layout from './Layout';

const Product = () => {
    const [product, setProduct] = useState({})
    const [related, setRelated] = useState([])
    let params = useParams();
    useEffect(() => {

        let productId = params.id;
        getProduct(productId)
          .then(res => {
              setProduct(res)
               return relatedProducts(productId)
            })
           .then(related => setRelated(related))
          .catch(err => console.error(err))

    }, [params])
  return (
    <div>
        {product && product.description && (
                <Layout 
                title={product.name}
                description={product.description.substring(0, 100)}
                className="container"
                >
                
                   <div className="row">
                       <div className="col-md-9">
                            <CardC product={product} showViewBtn={false}/>
                       </div>
                       <div className="col-md-3">
                           {related.map((product, i) => (

                                <CardC key={product._id} product={product} />

                           ))}
                       </div>
                   </div>

                </Layout>
           )}
    </div>
  )
}

export default Product