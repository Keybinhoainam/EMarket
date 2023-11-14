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
        async editCartDetail(cart_detail) {
            try {
                let cartDetail = this.cart.cart_details.find(
                    (x) => x.product.id === cart_detail.product.id
                );
                let index = this.cart.cart_details.indexOf(cartDetail);
                this.cart.cart_details.splice(index, 1,cart_detail);
                // console.log(this.cart);
                localStorage.setItem("cart", JSON.stringify(this.cart));

                this.getCart();
                await nextTick()
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
            let cartDetail = this.cart.cart_details.find((x) => x.product.id === product.id);
            if (cartDetail) {
                console.log("edit quantity");
                cartDetailTmp.quantity += cartDetail.quantity;
                let index = this.cart.cart_details.indexOf(cartDetail);
                this.cart.cart_details.splice(index, 1,cartDetailTmp);
                // cartDetail=cartDetailTmp;
            } else {
                this.cart.cart_details.push(cartDetailTmp);
            }
            localStorage.setItem("cart", JSON.stringify(this.cart));
            this.alertSuccess("Add to Cart Successfully");
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
        removeItem(cart_detail) {
            this.alertWarning(
                "Are you sure?",
                "You won't be able to revert this!",
                "Yes, delete it!"
            ).then((result) => {
                if (result.isConfirmed) {
                    try {
                        let cartDetail = this.cart.cart_details.find(
                            (x) => x.product.id === cart_detail.product.id
                        );
                        let index = this.cart.cart_details.indexOf(cartDetail);
                        this.cart.cart_details.splice(index, 1);
                        localStorage.setItem("cart", JSON.stringify(this.cart));
                        this.getCart();
                        nextTick();
                        this.alertSuccess("Remove CartItem Successfully");
                    } catch (error) {
                        console.log(error.message);
                    }
                }
            });
        },
    },
};
