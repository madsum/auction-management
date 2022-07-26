import React, { Component } from "react";
import axios from 'axios'
import CurdApi from "../Utility/CurdApi";
import { Image} from 'react-bootstrap'
import "./Bid.css"

export class Bid extends Component {
    constructor(props) {
        super(props);
        this.state = {
            product: props?.location?.state?.product,
            logedUser: props?.location?.state?.logedUser,
            photo: '',
            imagePreviewUrl: '',
            bidPrice: 0
        };
        this.handleChangeNumber = this.handleChangeNumber.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }

      handleChangeNumber(event) {
        this.setState({bidPrice: event.target.value});
      }

      async UNSAFE_componentWillMount(){
       let photoUrl = "http://localhost:8084/api/v1/productLookup/getImage?name="+this.state.product.photoFileName; 
       let photo = await axios.get(photoUrl);
       this.setState({imagePreviewUrl: photo});
      }

      clearState(){ 
        // clear state 
        this.setState({name : ''}) 
        this.setState({price : ''}) 
        this.setState({file : ''}) 
        this.setState({bidPrice : 0}) 
      } 

      async handleSubmit(event) {
        event.preventDefault();
        console.log(this.state.product.id);
        if(this.state.bidPrice <= this.state.product.price){
          alert("The bid price "+this.state.bidPrice +" must be greater than the product price "+this.state.product.price);
          return;
        }
        if(this.state.bidPrice <= this.state.product.bidPrice){
          alert("The bid price "+this.state.bidPrice +" must be greater than the highest bid price "+this.state.product.bidPrice);
          return;
        }
        let respose = await CurdApi.bidProduct(this.state.product, this.state.logedUser?.email, this.state.bidPrice);
        if(respose?.data){
            this.clearState();
            event.target.reset();
            this.props.history.push("/thanks"); 
        }
      }

      render() {
        let {imagePreviewUrl} = this.state;
        let $imagePreview = null;
        if (imagePreviewUrl) {
          $imagePreview = (<Image src={imagePreviewUrl} thumbnail />);
        } else {
          $imagePreview = (<div className="previewText">Image thumbnail</div>);
        }
        
        return (

            <form className="center" onSubmit={this.handleSubmit}>
            <label>
              Item name:{' '} {this.state.product.productName}
            </label>
            <label>
              Item price:{' '} {this.state.product.price}
            </label>
            <label>
              Highest bid price:{' '} {this.state.product.bidPrice}
            </label>
            <label>
              Bid ends at:{' '} {this.state.product.strAuctionEndTime}
            </label>
            <label>
              Item picture:{' '} 
              <img id="ItemPreview" src={this.state.product.photoUrl} alt="product photo" width="150" height="200"/> 
            </label>
            <label>
              Bid price:{' '}
              <input type="number" bidPrice={this.state.bidPrice} onChange={this.handleChangeNumber} />
            </label>
            <input  type="submit" value="Bid" />
          </form>

    )}
}

export default Bid
