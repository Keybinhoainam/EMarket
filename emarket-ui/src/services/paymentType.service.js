import axios from "axios";

class PaymentTypeService{
    async getPaymentTypes(url,config){
        return await axios.get(url,config).then(
            (res)=>{
                return Promise.resolve(res.data)
            },
            err=>{
                return Promise.reject(err)
            }
        );
    }
}
export default new PaymentTypeService();