import axios from "axios";
import Cookies from 'js-cookie';
// const API_URL='http://localhost:8080/api/auth/login';

class AuthService {
    login(url, user,config) {
        Cookies.remove("user");
        return axios.post(url, user,config).then(
            (res) => {
                if (res.data.accessToken) {
                    // localStorage.setItem("user", JSON.stringify(res.data));
                    Cookies.set("user", JSON.stringify(res.data), { expires: 1 });
                    
                }
                return Promise.resolve(user);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    logout() {
        // localStorage.removeItem("user");
        Cookies.remove("user")
    }
    register(url, user) {
        return axios.post(url, user);
    }
}
export default new AuthService();
