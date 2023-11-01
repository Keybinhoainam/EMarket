import { async } from "@zakerxa/vue-multiple-image-upload";
import axios from "axios";

class ProductService {
    saveProduct(url, product, config) {
        return axios.post(url, product, config).then(
            (res) => {
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getProductImageFile(src, fileName, mimeType) {
        return axios
            .get(src,{ responseType: 'blob' })
            .then((res) =>{
                // console.log([res.data]);
                return new File([res.data], fileName, { type: mimeType });
            })
    }
    saveProductImages(url, formdata, config) {
        return axios.post(url, formdata, config).then(
            (res) => {
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    async getProduct(url, config) {
        return await axios.get(url, config).then(
            (product) => {
                return Promise.resolve(product);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getAllProducts(url, config) {
        return axios.get(url, config).then(
            (product) => {
                return Promise.resolve(product);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    deleteProduct(url, config) {
        return axios.delete(url, config).then(
            (res) => {
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
}
export default new ProductService();
