import axios from "axios";

class ZaloPayService {
    constructor() {
        // this.createUrl = "http://localhost:8080/zaloPay/create";
        this.createUrl = "http://localhost:8080/api/zaloPay/createOrder";
        this.queryUrl="http://localhost:8080/zaloPay/query"
    }
    createOrder(dataRequest) {
        return axios.post(this.createUrl, dataRequest).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
    queryOrder(dataRequest){
        return axios.post(this.queryUrl, dataRequest).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
}
export default new ZaloPayService();
