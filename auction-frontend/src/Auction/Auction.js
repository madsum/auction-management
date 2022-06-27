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

      handleKeyDown = (e) => {
        if (e.key === 'Enter') {
          this.setState({playerName: e.target.value});
          history.push('/Game/'+this.state.playerName)
        }
      }

    render() {
        return (
            <div className="Home">
              {this.state?.logedUser == null ? (
                <section style={{background: "white"}}>
                    <h1>Please login first!</h1>
                    <p>
                        <a href="/signin">Sign In</a>
                    </p>
                </section>
            ) : (
                <section style={{background: "white"}}>
                    <p>
                      <Button variant="btn btn-info" onClick={() => this.props.history.push('/sell/',{logedUser: this.state.logedUser})}>Sell</Button>
                    </p>
                    <p>
                      <Button variant="btn btn-info" onClick={() => this.props.history.push('/buy/',{logedUser: this.state.logedUser})}>Buy</Button>
                    </p>
                </section>
        )}
        </div>
    )}
}

export default Auction
