// import { async } from "@zakerxa/vue-multiple-image-upload";
import axios from "axios";

class StoreService {
    saveStore(url, user, config) {
        user.avatarFile = null;
        return axios.post(url, user, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    saveImageStore(url, formData, config) {
        return axios.post(url, formData, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
}
export default new StoreService();
