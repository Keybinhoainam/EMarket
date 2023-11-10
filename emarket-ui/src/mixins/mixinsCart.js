import Cart_detail from "@/models/cart_detail";
import Cart from "@/models/cart";
import cartService from "@/services/cart.service";
import { nextTick } from "vue";

export default {
    data() {
        return {
            getCartUrl: `${this.baseURL}/customer/cart`,
            saveCartUrl: `${this.baseURL}/customer/cart/save`,
        };
    },
    // created() {
    //     this.getCart();
    // },
    methods: {
        async getCart() {
            // localStorage.removeItem("cart")
            if (localStorage.getItem("cart")) {
                this.cart = JSON.parse(localStorage.getItem("cart"));
            } else {
                this.cart = new Cart();
            }
            await nextTick();
            // try {
            //     this.cart=await cartService.getCart(this.getCartUrl,this.config);

            // } catch (error) {
            //     this.alertFail(
            //         "Failed to load Cart !"
            //         // "Something went wrong!"
            //     );
            //     this.$router.push({ name: "home" });
            // }
        },
        editCartDetail(cart_detail) {
            try {
                let cartDetail = this.cart.cart_details.find(
                    (x) => x.product.id === cart_detail.product.id
                );
                cartDetail.quantity = cart_detail.quantity;
                localStorage.setItem("cart", JSON.stringify(this.cart));
                this.getCart();
                this.alertSuccess("edit Cart Successfully");
            } catch (error) {
                console.log(error.message);
            }
        },
        addToCart(product, quantity) {
            this.getCart();
            let cartDetailTmp = new Cart_detail();
            cartDetailTmp.quantity = quantity;
            cartDetailTmp.product = product;
            let cartDetail = this.cart.cart_details.find(
                (cartDetailTmp) => cartDetailTmp.product.id === product.id
            );
            if (cartDetail) {
                cartDetail.quantity += quantity;
            } else {
                this.cart.cart_details.push(cartDetailTmp);
            }
            localStorage.setItem("cart", JSON.stringify(this.cart));
            // this.alertSuccess("Add to Cart Successfully");
        },
        async saveCart() {
            try {
                this.cart = await cartService.saveCart(this.saveCartUrl, this.cart, this.config);
            } catch (error) {
                this.alertFail(
                    "Failed to save Cart !"
                    // "Something went wrong!"
                );
                this.$router.push({ name: "home" });
            }
        },
    },
};
