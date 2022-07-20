import axios from 'axios';

const SELL_URL = 'http://localhost:8083/api/v1/addProduct';
const BID_URL = 'http://localhost:8083/api/v1/updateProduct/';
const BUY_URL = 'http://localhost:8084/api/v1/productLookup';


export default class CurdApi {
  static async postProduct(name, price, file) {
    try {
      let product = new FormData();    
      product.append("name", name);
      product.append("price", price);
      product.append("file", file);
      
      const response = await axios.post(SELL_URL, product , {
          headers: {
              'Content-Type': 'multipart/form-data'
            }
        });
        return response;
  } catch (err) {
     console.log(JSON.stringify(err));
  }
}

static async bidProduct(product, bidPrice) {  
    try {
      let updateProduct= {
        "product": {
          "id": product.id,
          "productName": product.productName,
          "price": product.price,
          "photoUrl": product.photoUrl,
          "bidPrice": bidPrice
        }
      }
      const response = await axios.put(BID_URL+product.id, updateProduct, {
          headers: {
              'Content-Type': 'application/json'
            }
        });
        return response;
  } catch (err) {
     console.log(JSON.stringify(err));
  }
}

static async getAllProduct() {
    try {
      const response = await axios.get(BUY_URL);
      const json = await response.data;
      return json;
    } catch (error) {
      console.log(error);
      return error.message;
    }
  }
}