import Cart_detail from "@/models/cart_detail";
import Cart from "@/models/cart";
import cartService from "@/services/cart.service";
import { nextTick } from "vue";

export default {
    data() {
        return {
            getCartUrl: `${this.baseURL}/customer/cart`,
            saveCartUrl: `${this.baseURL}/customer/cart/save`,
            // cart:this.$store.state.data.cart,
        };
    },
    // created() {
    //     this.getCart();
    // },
    methods: {
        checkTotalCost() {
            this.totalcost = 0;
            const cart_details = this.cart.cart_details;
            for (let i = 0; i < cart_details.length; i++) {
                this.totalcost += cart_details[i].quantity * cart_details[i].product.price;
            }
        },
        async saveAndLoadCart() {
            this.$store.commit("data/changeCart", this.cart);
            this.getCart();
            this.checkTotalCost();
            await nextTick();
        },
        async getCart() {
            // localStorage.removeItem("cart")

            this.cart = this.$store.state.data.cart;
            const cart_details = this.cart.cart_details;
            for (let i = 0; i < cart_details.length; i++) {
                if (cart_details[i].quantity > cart_details[i].product.stock) {
                    cart_details[i].quantity = cart_details[i].product.stock;
                }
                if (cart_details[i].quantity == 0) {
                    this.removeItem(cart_details[i]);
                }
            }
            this.$store.commit("data/changeCart", this.cart);
            this.checkTotalCost();
            await nextTick();

            // if (localStorage.getItem("cart")) {
            //     this.cart = JSON.parse(localStorage.getItem("cart"));
            //     const cart_details = this.cart.cart_details;
            //     for (let i = 0; i < cart_details.length; i++) {
            //         if (cart_details[i].quantity > cart_details[i].product.stock) {
            //             cart_details[i].quantity = cart_details[i].product.stock;
            //         }
            //         if (cart_details[i].quantity == 0) {
            //             this.removeItem(cart_details[i]);
            //         }
            //     }
            //     this.$store.commit("data/changeCart",this.cart);
            //     this.checkTotalCost();
            //     await nextTick();
            // } else {
            //     this.cart = new Cart();
            // }
            // this.checkTotalCost();
            // this.$emit("loadCart");
            // await nextTick();
        },
        async editCartDetail(cart_detail) {
            try {
                let check = true;
                if (
                    cart_detail.quantity === "" ||
                    cart_detail.quantity <= 0 ||
                    cart_detail.quantity > cart_detail.product.stock
                ) {
                    check = false;
                    cart_detail.quantity = cart_detail.product.stock;
                }
                let cartDetail = this.cart.cart_details.find(
                    (x) => x.product.id === cart_detail.product.id
                );
                // if(!cart_detail.quantity){
                //     console.log("nam");
                // }
                let index = this.cart.cart_details.indexOf(cartDetail);
                this.cart.cart_details.splice(index, 1, cart_detail);
                // console.log(this.cart);
                this.saveAndLoadCart();
                if (!check) {
                    this.alertFail(
                        "Please re-enter quantity",
                        `greater than 0 and less than or equal to ${cart_detail.product.stock}`
                    );
                    return;
                }
                this.alertSuccess("edit Cart Successfully");
            } catch (error) {
                console.log(error.message);
            }
        },
        addToCart(product, quantity) {
            if (quantity <= 0 || quantity == "") {
                this.alertFail("Please re-fill quantity");
                return;
            }
            this.getCart();
            let cartDetailTmp = new Cart_detail();
            cartDetailTmp.quantity = quantity;
            cartDetailTmp.product = product;
            let cartDetail = this.cart.cart_details.find((x) => x.product.id === product.id);
            if (cartDetail) {
                cartDetailTmp.quantity += cartDetail.quantity;
                if (cartDetailTmp.quantity > cartDetailTmp.product.stock)
                    cartDetailTmp.quantity = cartDetailTmp.product.stock;

                let index = this.cart.cart_details.indexOf(cartDetail);
                this.cart.cart_details.splice(index, 1, cartDetailTmp);
                // cartDetail=cartDetailTmp;
            } else {
                this.cart.cart_details.push(cartDetailTmp);
            }
            this.$store.commit("data/changeCart", this.cart);
            // 
            this.alertSuccess("Add to Cart Successfully");
        },
        // async saveCart() {
        //     try {
        //         this.cart = await cartService.saveCart(this.saveCartUrl, this.cart, this.config);
        //     } catch (error) {
        //         this.alertFail(
        //             "Failed to save Cart !"
        //             // "Something went wrong!"
        //         );
        //         this.$router.push({ name: "home" });
        //     }
        // },
        removeItem(cart_detail) {
            let cartDetail = this.cart.cart_details.find(
                (x) => x.product.id === cart_detail.product.id
            );
            let index = this.cart.cart_details.indexOf(cartDetail);
            this.cart.cart_details.splice(index, 1);
            this.$store.commit("data/changeCart", this.cart);
            this.getCart();
            this.checkTotalCost();
            nextTick();
        },
        askToRemove(cart_detail) {
            this.alertWarning(
                "Are you sure?",
                "You won't be able to revert this!",
                "Yes, delete it!"
            ).then((result) => {
                if (result.isConfirmed) {
                    try {
                        this.removeItem(cart_detail);
                        this.alertSuccess("Remove CartItem Successfully");
                    } catch (error) {
                        console.log(error.message);
                    }
                }
            });
        },
        checkVaildCart() {
            const cart_details = this.cart.cart_details;
            for (let i = 0; i < cart_details.length; i++) {
                this.totalcost += cart_details[i].quantity * cart_details[i].product.price;
                if (cart_details[i].quantity > cart_details[i].product.stock) return false;
            }
            return true;
        },
    },
};
