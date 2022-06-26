import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import CurdApi from '../Utility/CurdApi';
import { Table, TableCell } from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css';

export class Buy extends Component {
    constructor(props) {
        super(props);
        this.state = {
            productState: []
        }
    }

    async UNSAFE_componentWillMount(){
        try {
            let products = await CurdApi.getAllProduct();
            this.setState({ productState: products });
        } catch (error) {
            console.log(error);
        }
    }

    showUrl(url){
        if(url){
            return <a href= {url} 
            target="_blank" rel="noopener noreferrer" >product Photo</a>;
        }else{
            return "No photo"     
        }

    }

    render() { 

        return (
            <div>
                {
                    Array.isArray(this.state.productState) ?
                        <Table style={{ width: "auto", tableLayout: "auto" }}>
                            <Table.Header>
                                <Table.Row>
                                        <Table.HeaderCell>Product Name</Table.HeaderCell>
                                    <Table.HeaderCell>Price</Table.HeaderCell>
                                    <Table.HeaderCell>Seller Name</Table.HeaderCell>
                                    <Table.HeaderCell>Bid product</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {this.state.productState.map((product) => {
                                    return (
                                        <Table.Row key={product.id}>
                                            <Table.Cell>{product.productName}</Table.Cell>
                                            <Table.Cell>{product.price}</Table.Cell>
                                            <Table.Cell>{product.sellerName}</Table.Cell>
                                            <Table.Cell><Link to={{
                                                pathname: "/bidProduct",
                                                state: {
                                                    title: "Bid product",
                                                    product: product
                                                }
                                            }} >Bid product</Link>
                                            </Table.Cell>
                                        </Table.Row>
                                    );
                                })
                                }
                            </Table.Body>
                        </Table>
                        : <h1>{this.state.productState}</h1>
                }
            </div>
        )
    }
}
export default Buy
