import axios from 'axios';

const REGISTER_URL = 'http://localhost:8081/api/v1/registerUser';

export default class CurdApi {
  
    static postScore(data){
          axios.post('http://localhost:8080/score/add', data)
        .then(res => {
            console.log('res: ' + res.data);
        })
        .catch( error => {
            console.log('err: ' + error);
        })
    }

    static  resiterUse(user){
      axios.post(REGISTER_URL, user)
      .then(res => {
          console.log('res: ' + JSON.stringify(res.data));
          return true;
      })
      .catch( error => {
          console.log('err: ' + error);
          return false;
      })
    }

    static async getScore(){
      try{
        const response = await  axios.get('http://localhost:8080/score/all')
        const data = await response.data;
        return data;  
      } catch (error) {
        console.log('GET error: ' + error.message);
          return error.message; 
      }   
    }
}