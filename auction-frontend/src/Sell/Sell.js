
import React, { Component } from "react";
import CurdApi from "../Utility/CurdApi";
import DateTimePicker from 'react-datetime-picker'; 
import moment from 'moment';


export class Sell extends Component {
    constructor(props) {
      super(props);
      this.state = {
            logedUser: props?.location?.state?.logedUser,
            name: '',
            price: 0,
            file: '',
            endDate: new Date()
        };
    
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeNumber = this.handleChangeNumber.bind(this);
        this.handleChangeFile = this.handleChangeFile.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onEndDateChange = this.onEndDateChange.bind(this);
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

      onEndDateChange = endDate => {
        this.setState({ endDate });
      };  
/*
      onEndDateChange = value => {
        this.setState({ value });
      };  
*/
      clearState(){ 
        // clear state 
        this.setState({name : ''}) 
        this.setState({price : ''}) 
        this.setState({file : ''}) 
        this.setState({value : ''}) 
      } 
      async handleSubmit(event) {
        event.preventDefault();
        let respose = await CurdApi.postProduct(this.state.name, this.state.price, this.state.file, this.state.endDate);
        if(respose?.data){
            this.clearState();
            event.target.reset();
            this.props.history.push("/buy"); 
        }
      }
    
      render() {
        console.log(JSON.stringify(this.state.logedUser));
        return (
          <div className="center">
            {this.state?.logedUser?.role !== "SELLER" ? (
            <section style={{background: "white"}}>
                <h1>Only seller can access this seller page. Please sign in as a seller</h1>
                <p>
                    <a href="/signin">Sign In</a>
                </p>
            </section>
        ) : (
          <form style={{width: "640px", height: "460px"}} onSubmit={this.handleSubmit}>
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
              Auction end time:{' '}
              <DateTimePicker
              minDate={moment().toDate()}
              format="dd/MM/y h:mm:ss a"
              value={this.state.endDate} onChange={this.onEndDateChange}
              />
            </label>
            
            <input style={{width: "100px", height: "40px"}}  type="submit" value="Submit" />
          </form>
        )}
        </div> 
        )
    }
  }