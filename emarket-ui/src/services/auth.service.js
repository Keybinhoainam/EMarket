import User from "@/models/user";
import axios from "axios";
import Cookies from 'js-cookie';
// const API_URL='http://localhost:8080/api/auth/login';

class AuthService {
    login(url, user,config) {
        // this.$store.dispatch("data/changeUser",new User());
        // Cookies.set("user",JSON.stringify(new User()))
        Cookies.remove("user");
        return axios.post(url, user,config).then(
            (res) => {
                if (res.data.accessToken) {
                    // localStorage.setItem("user", JSON.stringify(res.data));
                    Cookies.set("accessToken", JSON.stringify(res.data.accessToken), { expires: 1 });
                    // this.$store.dispatch("data/changeUser",res.data);
                    // Cookies.set("user",JSON.stringify(res.data));
                    
                }
                return Promise.resolve(res);
            },
            (error) => {
                return Promise.reject(error);
            }
        );
    }
    // logout() {
    //     // localStorage.removeItem("user");
    //     // this.$store.dispatch("data/changeUser",new User());
    //     Cookies.remove("user");
    // }
    register(url, user) {
        return axios.post(url, user);
    }
}
export default new AuthService();
