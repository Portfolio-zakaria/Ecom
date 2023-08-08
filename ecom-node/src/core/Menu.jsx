
import React from "react";
import { Nav, Navbar } from "react-bootstrap";
import { useSelector } from "react-redux";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { API_URL } from "../config";
import { isAuthenticated } from "../helpers/IsAuthenticated";
import { notify } from "../helpers/Toast";
const Menu = () => {
    const navigate = useNavigate();
    const {user} = isAuthenticated();
    let countItem = useSelector(state => state.cart.count)
    const signout = () =>{
        fetch(`${API_URL}/signout`)
        .then(() => {
            notify('info','User Sign Out', 'Next Time');
            localStorage.removeItem('jwt_info')
            navigate('/signin',{replace: true})
        })
        .catch(err => {
            console.log(err);
        })
    }
   
  return (
  
    <Navbar bg="dark" variant={"dark"} expand="lg" fixed="top" >
    <Navbar.Brand as={NavLink} className='mx-3 ' to="#">Logo</Navbar.Brand>
    <Navbar.Toggle aria-controls="navbarScroll" />
    <Navbar.Collapse id="navbarScroll">
        <Nav
            className="me-auto my-2 my-lg-0 "
            style={{ maxHeight: '100px' }}
            navbarScroll
        >
            {isAuthenticated() && (
                <>
                    <Nav.Link as={NavLink} to="/">Home</Nav.Link>
                    <Nav.Link as={NavLink} to="/shop">Shop</Nav.Link>
                    <Nav.Link as={NavLink} to={user.role?'/admin/dashboard' : 'dashboard'}>Dashboard</Nav.Link>
                </>
             )} 
        </Nav>
        <Nav
            className="ms-auto my-2 my-lg-0 mx-5"
            style={{ maxHeight: '100px' }}
            navbarScroll
        >
            { !isAuthenticated() && (
                <>
                <Nav.Link as={NavLink} to="/signin">signin</Nav.Link>
                <Nav.Link as={NavLink} to="/signup">signup</Nav.Link>
                </>
            )}
            <Nav.Link  as={NavLink} to="/cart">
                Cart <span className="badge bdge-warning">{ countItem }</span>
            </Nav.Link>

            {isAuthenticated() && (
                <>
            <Nav.Link  onClick={signout}>signout</Nav.Link>
            </>
            )}

        </Nav>

    </Navbar.Collapse>
</Navbar>
  
   
  );
};

export default Menu;
