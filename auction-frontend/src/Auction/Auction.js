import React, { Component } from "react";
import { Button } from 'react-bootstrap';
import history from '../history';
import './Auction.css'

export class Auction extends Component {
    constructor(props) {
        super(props);
        this.state = {
          logedUser: props?.location?.state?.logedUser,
        };
        this.handleChange = this.handleChange.bind(this);
          }


      handleChange(event) {
        this.setState({playerName: event.target.value});
        console.log('role: '+this.state.role);
      }


    render() {
        return (
            <div className="center">
              {this.state?.logedUser == null ? (
                <section>
                    <h1>Please login first!</h1>
                    <p>
                        <a href="/signin">Sign In</a>
                    </p>
                </section>
            ) : (
                <div>
                    <h6>User name: {this.state?.logedUser.fullName}</h6>
                    <h6>User role: {this.state?.logedUser.role}</h6>
                    <label>Sell an item:{"  "}
                      <Button variant="btn btn-primary" onClick={() => this.props.history.push('/sell/',{logedUser: this.state.logedUser})}>Sell</Button>
                    </label>
                    <p></p>
                    <label>Buy an item:{"  "}
                      <Button variant="btn btn-primary" onClick={() => this.props.history.push('/buy/',{logedUser: this.state.logedUser})}>Buy</Button>
                    </label>
                </div>
        )}
        </div>
    )}
}

export default Auction
