import axios from "axios";

class CategoryService {
    saveCategory(url, category,config) {
        return axios.post(url, category,config).then(
            (res) => {
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getCategory(url,config) {
        return axios.get(url,config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getProductsCategory(url,config) {
        return axios.get(url,config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getAllCategories(url,config) {
        return axios.get(url,config).then(
            (categories) => {
                return Promise.resolve(categories.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    deleteCategory(url,config) {
        return axios.delete(url,config).then(
            (res) => {
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    getAllCategoriesStore(url,data,config) {
        return axios.post(url,data,config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
}
export default new CategoryService();
