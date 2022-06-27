import React, { Component } from "react";
import axios from 'axios'
import { Image,Container,Row,Col } from 'react-bootstrap'

export class Bid extends Component {
    constructor(props) {
        super(props);
        this.state = {
            product: props?.location?.state?.product,
            photo: '',
            imagePreviewUrl: ''
        };
      }

      async UNSAFE_componentWillMount(){
       let photoUrl = "http://localhost:8084/api/v1/productLookup/getImage?name="+this.state.product.photoFileName; 
       let photo = await axios.get(photoUrl);
       this.setState({imagePreviewUrl: photo});
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

            <form onSubmit={this.handleSubmit}>
            <label>
              Item name:{' '} {this.state.product.productName}
            </label>
            <label>
              Item price:{' '} {this.state.product.price}
            </label>
            <label>
              Item picture:{' '} 
              <img id="ItemPreview" src={this.state.product.photoUrl} alt="product photo" width="150" height="200"/> 
            </label>
            
            <label>
              Seller name:{' '} {this.state.sellerName}
            </label>
            
            <input type="submit" value="Submit" />
          </form>

    )}
}

export default Bid
