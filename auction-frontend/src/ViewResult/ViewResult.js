import React, { Component } from 'react';
import CurdApi from '../CurdUtil/CurdApi';
import { Table } from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css';
import './ViewResult.css'



export class ViewResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            profilesState: "Loading...",
        }
    }

    async componentDidMount() {
        try {
            let score = await CurdApi.getScore();
            this.setState({ profilesState: score });
        } catch (error) {
            console.log(error);
        }
    }





    render() { 

        return (
            <div>
                {
                    Array.isArray(this.state.profilesState) ?
                        <div className="space"> 
                        <Table style={{ width: "auto", tableLayout: "auto" }}  >
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell style={{ Width: '50px' }}>Id</Table.HeaderCell>
                                    <Table.HeaderCell>Name</Table.HeaderCell>
                                    <Table.HeaderCell>Round</Table.HeaderCell>
                                    <Table.HeaderCell>Result</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {this.state.profilesState.map((score) => {
                                    return (
                                        <Table.Row key={score.id}>
                                            <Table.Cell>{score.id}</Table.Cell>
                                            <Table.Cell>{score.name}</Table.Cell>
                                            <Table.Cell>{score.round}</Table.Cell>
                                            <Table.Cell>{score.result}</Table.Cell>
                                        </Table.Row>
                                    );
                                })
                                }
                            </Table.Body>
                        </Table>
                        </div>
                        : <h1>{this.state.profilesState}</h1>
                }
            </div>
        )
    }
}

export default ViewResult
