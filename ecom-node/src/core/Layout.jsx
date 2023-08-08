import React from 'react'
import { Outlet } from 'react-router-dom'
import Menu from './Menu'

const Layout = ({title, description, className, children}) => {
  return (
    <div>
          <div className="p-5 mt-2 mb-4 bg-light rounded-3 jumbotron">
            <div className="container-fluid ">
                <h1 className="display-5 fw-bold">{title}</h1>
                <p className="col-md-8 fs-4">{description}</p>
            </div>
         </div>
         <div className={className}>
            {children}
        </div>

    </div>
  )
}

export default Layout