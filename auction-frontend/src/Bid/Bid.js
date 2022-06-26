import React, { Component } from "react";
import axios from 'axios'
import { Image,Container,Row,Col } from 'react-bootstrap'
import { Button } from 'react-bootstrap';
import history from '../history';

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
              {$imagePreview}
            </label>
            
            <label>
              Seller name:{' '} {this.state.sellerName}
            </label>
            
            <input type="submit" value="Submit" />
          </form>

    )}
}

export default Bid
