
import React, { Component } from "react";
import CurdApi from "../Utility/CurdApi";

export class Sell extends Component {
    constructor(props) {
      super(props);
      this.state = {
            logedUser: props?.location?.state?.logedUser,
            name: '',
            price: 0,
            file: '',
            sellerName: props?.location?.state?.logedUser?.fullName
        };
    
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeNumber = this.handleChangeNumber.bind(this);
        this.handleChangeFile = this.handleChangeFile.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
    
      handleChangeName(event) {
        this.setState({name: event.target.value});
      }

      handleChangeNumber(event) {
        this.setState({price: event.target.value});
      }

      handleChangeFile(event) {
        this.setState({file: event.target.files[0]});
      }

      clearState(){ 
        // clear state 
        this.setState({name : ''}) 
        this.setState({price : ''}) 
        this.setState({file : ''}) 
      } 
      async handleSubmit(event) {
        event.preventDefault();
        let respose = await CurdApi.postProduct(this.state.name, this.state.price, this.state.file, this.state.sellerName);
        if(respose?.data){
            this.clearState();
            event.target.reset();
            this.props.history.push("/buy"); 
        }
      }
    
      render() {
        console.log(JSON.stringify(this.state.logedUser));
        return (
          <div className="Home">
            {this.state?.logedUser?.role !== "SELLER" ? (
            <section style={{background: "white"}}>
                <h1>Only seller can access this seller page. Please sign in as a seller</h1>
                <p>
                    <a href="/signin">Sign In</a>
                </p>
            </section>
        ) : (
          <form onSubmit={this.handleSubmit}>
            <label>
              Item name:{' '}
              <input type="text" name={this.state.name} onChange={this.handleChangeName} />
            </label>
            <label>
              Item price:{' '}
              <input type="number" price={this.state.price} onChange={this.handleChangeNumber} />
            </label>
            <label>
              Item picture:{' '}
              <input type="file" file={this.state.file} onChange={this.handleChangeFile} 
              name="file" accept="image/apng, image/avif, image/gif, image/jpeg, image/png, image/svg+xml, image/webp"></input>
            </label>
            <label>
              Seller name:{' '} {this.state.sellerName}
            </label>
            
            <input type="submit" value="Submit" />
          </form>
        )}
        </div> 
        )
    }
  }