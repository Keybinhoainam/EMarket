import axios from "axios";
class CartService{
    getCart(url,config){
        return axios.get(url,config).then(
            res=>{
                return Promise.resolve(res.data);
            },
            err=>{
                return Promise.reject(err)
            }
        )
    }
    async saveCart(url,cart,config){
        await axios.post(url,cart,config).then(
            res=>{
                return Promise.resolve(res.data);
            },
            err=>{
                return Promise.reject(err)
            }
        )
    }
}
export default new CartService();