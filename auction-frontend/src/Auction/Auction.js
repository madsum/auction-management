import React, { Component } from "react";
import { Button } from 'react-bootstrap';
import history from '../history';
import './Auction.css'

export class Auction extends Component {
    constructor(props) {
        super(props);
        this.state = {
          role: props?.location?.state?.role,
          isLoggin: false,
          isBuyer: false,
          isSeller: false,
          isAdmin: false
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
              {this.state?.role == null ? (
                <section style={{background: "white"}}>
                    <h1>Please login first!</h1>
                    <p>
                        <a href="/signin">Sign In</a>
                    </p>
                </section>
            ) : (
                <section style={{background: "white"}}>
                    <p>
                        <a href="/sell">Sell</a>  
                    </p>
                    <p>
                        <a href="/buy">Buy</a> 
                    </p>
                </section>
              
            /*
              <form>
                <h3>You are loggedin:</h3>
                <label>
                    Player Name:
                    <input type="text" value={this.state.playerName}  
                    onChange={this.handleChange} 
                    onKeyDown={this.handleKeyDown}/>
                </label>
                <br/>
                <Button variant="btn btn-success" onClick={() =>  history.push('/Game/'+this.state.playerName)}>Register</Button>
              </form>

              */
        )}
        </div>
    )}
}

export default Auction
