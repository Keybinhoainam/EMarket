import User from "@/models/user";
import router from "@/router";
import authService from "@/services/auth.service";
import Cookies from "js-cookie";
import fileService from "@/services/file.service";
import Cart from "@/models/cart";
import WishList from "@/models/wishList";
import authHeader from "@/services/authHeader";
export default {
    data() {
        return {
            
        };
    },
    methods: {
        async mxLogin() {
            
            await this.$store.dispatch("data/changeUser", new User());
            Cookies.remove("accessToken");
            try {
                // console.log(this.$store.state.data.user);
                this.user = await authService.login(this.url, this.user, this.config);
                // console.log(res.data.avatar);
                // res.data.avatarFile = await fileService.getImage(this.urlGetImage, res.data.avatar, "image/*");
                // console.log(res.data);
                await this.$store.dispatch("data/changeUser", this.user);
                if (this.user.accessToken) Cookies.set("accessToken", JSON.stringify(this.user.accessToken), { expires: 1 });
                // console.log(this.$store.state.data.user);
                this.alertSuccess("Login success !");
                let redirect=this.$route.query.redirect;
                if(this.user.roles[0].name=="ADMIN"){
                    this.$router.push('/admin')
                }
                else if(redirect&&redirect.startsWith('/admin')&&this.user.roles[0].name!="ADMIN"){
                    this.alertFail(
                        "Login Fail...",
                        'Admin rights needed!'
                    );
                }
                else if(redirect&&redirect.startsWith('/seller')&&this.user.roles[0].name!="SELLER"){
                    this.alertFail(
                        "Login Fail...",
                        'Seller rights needed!'
                    );
                    
                }
                else{
                    
                    this.$router.push(this.$route.query.redirect || "/");
                }
                
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
            this.$store.commit("data/changeCart",new Cart());
            this.$store.commit("data/changeWishList",new WishList());
            Cookies.remove("accessToken");
            
        },
    },
};
