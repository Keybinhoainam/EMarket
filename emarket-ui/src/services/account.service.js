// import { async } from "@zakerxa/vue-multiple-image-upload";
import axios from "axios";

class AccountService {
    saveAccount(url, user, config) {
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
    saveAvatarAccount(url, formData, config) {
        return axios.post(url, formData, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    checkCurrentPassword(url, data, config) {
        return axios.post(url, data, config).then(
            (res) => {
                return Promise.resolve(res.data);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
}
export default new AccountService();
