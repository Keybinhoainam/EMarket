import axios from "axios";

class OrderService {
    async checkOut(url, order, config) {
        return await axios.post(url, order, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
    async saveOrder(url, order, config) {
        return await axios.post(url, order, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
    async changeStatus(url, order, config) {
        return axios.post(url, order, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
    async getOrdersByUser(url, data, config) {
        // console.log(url);
        // console.log(data);
        // console.log(config);
        return await axios.post(url, data, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (err) => {
                return Promise.reject(err);
            }
        );
    }
}
export default new OrderService();
