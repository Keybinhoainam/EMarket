<template>
    <form class="div_class" @submit.prevent="checkOut()">
        <h3>You will be redirected to payment page</h3>

        <div class="controls">
            <input
                class="billing-address-name"
                type="text"
                v-model="order.ship_address"
                placeholder="Ship Address"
                required
            />
        </div>
        <div class="swit-radio">
            <label>Payment Type</label>
            <!-- v-bind:class="{
                    'is-invalid': errors.category.id,
                }" -->
            <select class="form-control" v-model="order.payment_type" required>
                <option
                    v-for="payment_type of payment_types"
                    :key="payment_type.id"
                    :value="payment_type"
                    
                >
                    {{ payment_type.name }}
                </option>
            </select>

            <div v-if="order.payment_type.name == 'Online Payment Methods'">
                <p>Agribank Ha Noi</p>
                <p>1500206098656</p>
                <p>Please Pay To This Account</p>
            </div>

            <!-- <div class="invalid-feedback mt-1 mb-4 ms-1">
                {{ errors.category.id }}
            </div> -->

            <div class="clearfix"></div>
        </div>

        <button class="checkout_button " type="submit">
            Make payment
        </button>
    </form>
</template>
<script>
import sweetAlert from "@/mixins/sweetAlert";
import mixinsPaymentType from "@/mixins/mixinsPaymentType";
import Order from "@/models/order";
import mixinsOrder from "@/mixins/mixinsOrder";
export default {
    data() {
        return {
            // stripeAPIToken: process.env.VUE_APP_STRIPETOKEN,
            // stripe: "",
            // token: null,
            // sessionId: null,
            // checkoutBodyArray: [],
            order: new Order(),
            payment_types: [],
        };
    },

    name: "Checkout",
    props: ["baseURL", "config"],
    mixins: [sweetAlert, mixinsPaymentType,mixinsOrder],
    methods: {
        // configureStripe() {},
        // getAllItems() {
        //     axios.get(`${this.baseURL}cart/?token=${this.token}`).then(
        //         (response) => {
        //             if (response.status == 200) {
        //                 let products = response.data;
        //                 let len = Object.keys(products.cartItems).length;
        //                 for (let i = 0; i < len; i++)
        //                     this.checkoutBodyArray.push({
        //                         imageUrl: products.cartItems[i].product.imageURL,
        //                         productName: products.cartItems[i].product.name,
        //                         quantity: products.cartItems[i].quantity,
        //                         price: products.cartItems[i].product.price,
        //                         productId: products.cartItems[i].product.id,
        //                         userId: products.cartItems[i].userId,
        //                     });
        //             }
        //         },
        //         (err) => {
        //             console.log(err);
        //         }
        //     );
        // },
        // goToCheckout() {
        //     axios
        //         .post(this.baseURL + "order/create-checkout-session", this.checkoutBodyArray)
        //         .then((response) => {
        //             localStorage.setItem("sessionId", response.data.sessionId);
        //             return response.data;
        //         })
        //         .then((session) => {
        //             return this.stripe.redirectToCheckout({
        //                 sessionId: session.sessionId,
        //             });
        //         });
        // },
    },
    async created() {
        await this.getPaymentTypes();
    },
    mounted() {
        // // get the token
        // this.token = localStorage.getItem("token");
        // // get all the cart items
        // this.stripe = Stripe(this.stripeAPIToken);
        // this.getAllItems();
    },
};
</script>

<style>
.alert {
    width: 50%;
}

.div_class {
    margin-top: 5%;
    margin-left: 30%;
}

.checkout_button {
    background-color: #5d3dec;
    border: none;
    color: white;
    margin-left: 15%;
    padding: 15px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    font-weight: bold;
    border-radius: 15px;
}

.checkout_button:focus {
    outline: none;
    box-shadow: none;
}

.checkout_button:disabled {
    background-color: #9b86f7;
    border: none;
    color: white;
    margin-left: 15%;
    padding: 15px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    font-weight: bold;
    border-radius: 15px;
    cursor: not-allowed;
}
</style>
