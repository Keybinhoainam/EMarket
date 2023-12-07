import User from "@/models/user";
import router from "@/router";
import authService from "@/services/auth.service";
import Cookies from "js-cookie";
import fileService from "@/services/file.service";
export default {
    data() {
        return {
        };
    },
    methods: {
        async mxLogin() {
            
            await this.mxLogout();
            try {
                // console.log(this.$store.state.data.user);
                let res = await authService.login(this.url, this.user, this.config);
                // console.log(res.data.avatar);
                // res.data.avatarFile = await fileService.getImage(this.urlGetImage, res.data.avatar, "image/*");
                // console.log(res.data);
                await this.$store.dispatch("data/changeUser", res.data);
                if (res.data.accessToken) Cookies.set("accessToken", JSON.stringify(res.data.accessToken), { expires: 1 });
                // console.log(this.$store.state.data.user);
                this.alertSuccess("Login success !");

                this.$router.push(this.$route.query.redirect || "/");
            } catch (error) {
                this.alertFail(
                    "Login Fail...",
                    error.message
                    // "Something went wrong!"
                );
            }
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
            await this.$store.dispatch("data/changeUser", new User());
            localStorage.removeItem("cart");
            localStorage.removeItem("wishList");
            Cookies.remove("accessToken");
            
        },
    },
};
