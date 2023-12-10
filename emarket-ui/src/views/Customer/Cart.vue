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
                                <th >ITEM</th>
                                <th >PRICE</th>
                                <th >QUANTITY</th>
                                <th >TOTAL</th>
                                <th ></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="cart_detail in cart.cart_details" :key="cart_detail.id">
                                <td>
                                    <v-list-item>
                                        <v-list-item
                                            :title="cart_detail.product.product_name"
                                            :subtitle="'Stock: '+cart_detail.product.stock"
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
                                <td >
                                    ${{ cart_detail.product.price }}
                                </td>
                                <td>
                                    <v-text-field
                                        class="pt-10"
                                        style="width: 80px"
                                        v-model="cart_detail.quantity"
                                        type="number"
                                        @change="editCartDetail(cart_detail)"
                                    ></v-text-field>
                                </td>
                                <td>${{ cart_detail.product.price * cart_detail.quantity }}</td>
                                <td  title="Remove Item"><button type="button" class="btn btn-danger" @click="askToRemove(cart_detail)" title="Remove item">X</button></td>
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
                                    <td class="text-right" style="width: 50px">${{ totalcost.toFixed(2) }}</td>
                                </tr>
                                <tr>
                                    <td>Shipping Charges</td>
                                    <td class="text-right" style="width: 50px">$10.00</td>
                                </tr>
                                <tr>
                                    <td>Tax</td>
                                    <td class="text-right" style="width: 50px">$0</td>
                                </tr>
                                <tr>
                                    <td><b>Total</b></td>
                                    <td class="text-right" style="width: 50px"><b>${{ (totalcost+10).toFixed(2)}}</b></td>
                                </tr>
                            </tbody>
                        </template>
                    </v-table>
                    <div class="text-center">
                        <v-btn class="white--text mt-5" color="primary" @click="proceedToPay()">PROCEED TO PAY</v-btn>
                    </div>
                </v-col>
            </v-row>
        </v-container>
        
    </div>
</template>
<script>
import mixinsCart from "@/mixins/mixinsCart";
import Cart from "@/models/cart";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from "vue";
import mixinsOrder from '@/mixins/mixinsOrder';

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
    mixins: [mixinsCart, mixinsProduct, sweetAlert,mixinsOrder],
    methods: {
        
    },
    async created() {
        await this.getCart();
        // console.log(this.cart);
    },
};
</script>
