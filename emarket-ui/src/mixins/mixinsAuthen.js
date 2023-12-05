import User from "@/models/user";
import router from "@/router";
import authService from "@/services/auth.service";
import Cookies from "js-cookie";
export default {
    data() {
        return {};
    },
    methods: {
        async mxLogin() {
            this.$store.dispatch("data/changeUser", new User());
            Cookies.remove("user");
            await authService.login(this.url, this.user, this.config).then(
                (res) => {
                    this.$store.dispatch("data/changeUser", res.data);
                    console.log(this.$store.state.data.user);
                    this.alertSuccess("Login success !");

                    this.$router.push(this.$route.query.redirect || "/");
                },
                (error) => {
                    this.alertFail(
                        "Login Fail...",
                        error.message
                        // "Something went wrong!"
                    );
                }
            );
        },
        async mxRegister() {
            await authService.register(this.url, this.user, this.config).then(
                (user) => {
                    this.alertSuccess("Account created successfully success !");
                    this.$router.push({ name: "login" });
                },
                (error) => {
                    this.alertFail("Failed to create an account !", error.message);
                }
            );
        },
        async mxLogout() {
            localStorage.removeItem("cart");
            localStorage.removeItem("wishList");
            Cookies.remove("user");
            router.push({ name: "home" });
        },
    },
};
