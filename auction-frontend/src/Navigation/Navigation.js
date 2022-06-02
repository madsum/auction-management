import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import { withRouter } from 'react-router-dom';
import './Navigation.css'
import 'bootstrap/dist/css/bootstrap.min.css';

const Navigation = () => {
  return (
    <>
        <Navbar bg="primary" variant="dark">
          <Navbar.Brand href="/">Online Auction</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav>
              <Nav.Link href="/">Register user</Nav.Link>
              <Nav.Link href="/signin">Sign In</Nav.Link>
              <Nav.Link href="/auction">Auction</Nav.Link>
             </Nav>
          </Navbar.Collapse>
        </Navbar>
    </>
  );
};
export default withRouter(Navigation);