<template>
    <div>
        <v-container>
            <p class="display-3 font-weight-light text-center pa-4">SHOPPING CART</p>
            <v-row>
                <v-col :cols="12" md="9" sm="12">
                    <v-table>
                        <!-- <template v-slot:default> -->
                        <thead>
                            <tr>
                                <th class="text-center">ITEM</th>
                                <th class="text-center">PRICE</th>
                                <th class="text-center">QUANTITY</th>
                                <th class="text-center">TOTAL</th>
                                <th class="text-center"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="cart_detail in cart.cart_details" :key="cart_detail.id">
                                <td>
                                    <v-list-item>
                                        <v-list-item
                                            :title="cart_detail.product.product_name"
                                            :subtitle="cart_detail.product.category.category_name"
                                            :prepend-avatar="
                                                cart_detail.product.product_images.length > 0 &&
                                                cart_detail.product.product_images[0].image != null
                                                    ? getImageURL(
                                                          cart_detail.product.product_images[0]
                                                              .image
                                                      )
                                                    : require('@/assets/images/noImage.webp')
                                            "
                                        >
                                        </v-list-item>
                                    </v-list-item>
                                </td>
                                <td>$40.00</td>
                                <td>
                                    <v-text-field
                                        class="pt-10"
                                        style="width: 80px"
                                        variant="outlined"
                                        model-value="2"
                                        type="number"
                                    ></v-text-field>
                                </td>
                                <td>$80.00</td>
                                <td><a>X</a></td>
                            </tr>
                        </tbody>
                        <!-- </template> -->
                    </v-table>
                </v-col>
                <v-col :cols="12" md="3" sm="12" style="background-color: lightgray">
                    <p class="headline">Order Summary</p>
                    <p class="overline">
                        Shipping and additional costs are calculated based on values you have
                        entered.
                    </p>
                    <v-table>
                        <template v-slot:default>
                            <tbody>
                                <tr>
                                    <td>Order Subtotal</td>
                                    <td class="text-right" style="width: 50px">$160.00</td>
                                </tr>
                                <tr>
                                    <td>Shipping Charges</td>
                                    <td class="text-right" style="width: 50px">$10.00</td>
                                </tr>
                                <tr>
                                    <td>Tax</td>
                                    <td class="text-right" style="width: 50px">$5.00</td>
                                </tr>
                                <tr>
                                    <td><b>Total</b></td>
                                    <td class="text-right" style="width: 50px"><b>$175.00</b></td>
                                </tr>
                            </tbody>
                        </template>
                    </v-table>
                    <div class="text-center">
                        <v-btn class="primary white--text mt-5" outlined>PROCEED TO PAY</v-btn>
                    </div>
                </v-col>
            </v-row>
        </v-container>
        <v-card color="accent">
            <v-container>
                <v-row no-gutters>
                    <v-col cols="12" md="4" sm="12">
                        <v-row>
                            <v-col class="pr-4" align="right" cols="12" sm="3">
                                <v-icon class="text-h3">mdi-truck</v-icon>
                            </v-col>
                            <v-col class="pr-4" cols="12" sm="9">
                                <h5 class="font-weight-light">FREE SHIPPING & RETURN</h5>
                                <p class="font-weight-thin">Free Shipping over $300</p>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="12" md="4" sm="12">
                        <v-row>
                            <v-col class="pr-4" align="right" cols="12" sm="3">
                                <v-icon class="text-h3">mdi-cash-usd</v-icon>
                            </v-col>
                            <v-col class="pr-4" cols="12" sm="9">
                                <h5 class="font-weight-light">MONEY BACK GUARANTEE</h5>
                                <p class="font-weight-thin">30 Days Money Back Guarantee</p>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="12" md="4" sm="12">
                        <v-row>
                            <v-col class="pr-4" align="right" cols="12" sm="3">
                                <v-icon class="text-h3">mdi-phone</v-icon>
                            </v-col>
                            <v-col class="pr-4" cols="12" sm="9">
                                <h5 class="font-weight-light">+8486837****</h5>
                                <p class="font-weight-thin">24/7 Available Support</p>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </div>
</template>
<script>
import mixinsCart from "@/mixins/mixinsCart";
import Cart from "@/models/cart";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from "vue";

const axios = require("axios");
export default {
    data() {
        return {
            cart: new Cart(),
            noImageUrl: "@/assets/images/noImage.webp",
            totalcost: 0,
        };
    },
    name: "Cart",
    props: ["baseURL", "config", "products"],
    mixins: [mixinsCart, mixinsProduct, sweetAlert],
    methods: {},
    async created() {
        console.log(this.baseURL);
        await this.getCart(this.products);
        // console.log(this.cart);
    },
};
</script>
