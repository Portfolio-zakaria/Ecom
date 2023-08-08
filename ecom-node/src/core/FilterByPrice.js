import React from 'react'
import { FormGroup, Input, Label } from 'reactstrap'

const FilterByPrice = ({handelFilters}) => {
    const prices = [
        {
            _id: 1,
            name: "Any",
            value: []
        },
        {
            _id: 2,
            name: "0$ to 39$",
            value:[0, 39]
        }
        ,
        {
            _id: 3,
            name: "40$ to 79$",
            value:[40, 79]
        }
        ,
        {
            _id: 4,
            name: "80$ to 119$",
            value:[80, 119]
        }
        ,
        {
            _id: 5,
            name: "120$ to 160$",
            value:[120, 160]
        },
        {
            _id: 6,
            name: "More",
            value:[161, 999999]
        }
    ]

   const  handelPrice = (e) => {
       handelFilters(prices[e.target.value]['value'])
   }
  return (
    <div>
        <h4>Filter By Price</h4>
        {prices.map((price,i) => (
            <div className='my-2' key={i} >
                   <FormGroup check>
                   <Label check>
                        <Input
                        value={i}
                        onChange={handelPrice}
                        name='price'
                        type="radio"
                        />
                        {' '}
                      
                       {price.name}
                        </Label>
                    </FormGroup>
 
            </div>
        ))}
    </div>
  )
}

export default FilterByPrice