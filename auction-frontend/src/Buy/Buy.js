import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import CurdApi from '../Utility/CurdApi';
import { Table, TableCell } from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css';
import './Buy.css';

export class Buy extends Component {
    constructor(props) {
        super(props);
        this.state = {
            logedUser: props?.location?.state?.logedUser,
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
            <div className="center">
                {
                    Array.isArray(this.state.productState) ?
                        <Table style={{ width: "auto", tableLayout: "auto" }}>
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell>Product Name</Table.HeaderCell>
                                    <Table.HeaderCell>Price</Table.HeaderCell>
                                    <Table.HeaderCell>Highest bid price</Table.HeaderCell>
                                    <Table.HeaderCell>Image</Table.HeaderCell>
                                    <Table.HeaderCell>End Time</Table.HeaderCell>
                                    <Table.HeaderCell>Bid product</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {this.state.productState.map((product) => {

                                    return (
                                        <Table.Row key={product.id}>
                                            <Table.Cell>{product.productName}</Table.Cell>
                                            <Table.Cell>{product.price}</Table.Cell>
                                            <Table.Cell>{product.bidPrice}</Table.Cell>
                                            <Table.Cell><img id="ItemPreview" src={product.photoUrl} alt="product photo" width="50" height="50"/> </Table.Cell>
                                            <Table.Cell>{product.strAuctionEndTime}</Table.Cell>
                                            { product.sold === false ? 
                                            
                                            <Table.Cell><Link to={{
                                                pathname: "/bidProduct",
                                                state: {
                                                    logedUser: this.state.logedUser,
                                                    product: product
                                                }
                                            }} >Bid product</Link>
                                            </Table.Cell>
                                             : <Table.Cell>Item sold</Table.Cell>
                                            }
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
