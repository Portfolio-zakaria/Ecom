import React from 'react'
import { Navigate } from 'react-router-dom'
import { isAuthenticated } from '../helpers/IsAuthenticated'

const AdminRoute = ({children}) => {
    const {user: {role}} = isAuthenticated();

  return isAuthenticated() ? role ? children : <Navigate to="/signin" />:<Navigate to="/signin" /> 

}

export default AdminRoute