import React, { Component } from "react";
import axios from 'axios';

const SELL_URL = 'http://localhost:8081/api/v1/addProduct/upload4';

export class Buy extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            price: 0,
            file: ''
        };
    
      }

        render() {
            return (
                <h1>Buyer page</h1>
            );
          }
        }        