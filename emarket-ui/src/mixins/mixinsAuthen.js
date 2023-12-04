import router from "@/router";
import authService from "@/services/auth.service";
export default {
    data() {
        return {};
    },
    methods: {
        async mxLogin() {
            await authService.login(this.url, this.user, this.config).then(
                () => {
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
        async mxLogout(){
            authService.logout();
            localStorage.removeItem("cart");
            localStorage.removeItem("wishList");
            router.push({name:"home"})
        }
    },
};
