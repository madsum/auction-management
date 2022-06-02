import React, { Component } from "react";
import  { Router, Switch, Route } from "react-router-dom";
import Game from './Game/Game'
import RegisterUser from './RegisterUser/RegisterUser';
import Login from './Login/Login';
import Auction from "./Auction/Auction";
import ViewResult from './ViewResult/ViewResult';
import history from './history';

export default class Routes extends Component {
    render() {
        return (
            <Router history={history}>
                <Switch>
                    <Route path="/" exact component={RegisterUser} />
                    <Route path="/signin" exact component={Login} />
                    <Route path="/auction" exact component={Auction} />
                    <Route path="/game/:title" exact component={Game} />
                    <Route path="/result" exact component={ViewResult} />
                </Switch>
            </Router>
        )
    }
}
