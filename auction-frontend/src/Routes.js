import React, { Component } from "react";
import  { Router, Switch, Route } from "react-router-dom";
import RegisterUser from './RegisterUser/RegisterUser';
import Login from './Login/Login';
import Auction from "./Auction/Auction";
import history from './history';
import { Sell } from "./Sell/Sell";
import { Buy } from "./Buy/Buy";

export default class Routes extends Component {
    render() {
        return (
            <Router history={history}>
                <Switch>
                    <Route path="/" exact component={RegisterUser} />
                    <Route path="/signin" exact component={Login} />
                    <Route path="/auction" exact component={Auction} />
                    <Route path="/sell" exact component={Sell} />
                    <Route path="/buy" exact component={Buy} />
                </Switch>
            </Router>
        )
    }
}
