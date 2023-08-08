import React, { useState } from 'react'
import { Input, Label, List } from 'reactstrap'

const FilterByCategory = ({categories, handelFilters }) => {
    const [checked] = useState(new Set());
    const handelChange = (category) => {
        if(checked.has(category._id)){
            checked.delete(category._id)
        }else{

            checked.add(category._id)
        }
            handelFilters(Array.from(checked))
    }
  return (
    <div>
        <h4>Filter By Categories</h4>
        <List type="unstyled">
        {
            categories && categories.map((category, i) => (
        <li className='my-3' key={i}>
             <Label htmlFor={i}>
              <Input onClick={() =>handelChange(category)} className={'me-3'}type="checkbox" value={category._id}  name='' id= {i}  /> {' '}  
              {category.name}
             </Label>
        
        </li>
             ) )}
        </List>
    </div>
  )
}

export default FilterByCategory