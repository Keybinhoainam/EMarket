<template>
    <div>
        <v-container>
            <p class="display-3 font-weight-light text-center pa-4">Order Detail</p>
            <v-row>
                <v-col :cols="12" md="8" sm="12">
                    <v-table>
                        <!-- <template v-slot:default> -->
                        <thead>
                            <tr>
                                <th>ITEM</th>
                                <th>PRICE</th>
                                <th>QUANTITY</th>
                                <th>TOTAL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="orderDetail in order.order_details" :key="orderDetail.id">
                                <td>
                                    <v-list-item>
                                        <v-list-item
                                            :title="orderDetail.product.product_name"
                                            :prepend-avatar="
                                                orderDetail.product.product_images.length > 0 &&
                                                orderDetail.product.product_images[0].image != null
                                                    ? getImageURL(
                                                          orderDetail.product.product_images[0]
                                                              .image
                                                      )
                                                    : require('@/assets/images/noImage.png')
                                            "
                                        >
                                        </v-list-item>
                                    </v-list-item>
                                </td>
                                <td>${{ orderDetail.product.price }}</td>
                                <td>
                                    {{ orderDetail.quantity }}
                                </td>
                                <td>${{ orderDetail.product.price * orderDetail.quantity }}</td>
                            </tr>
                        </tbody>
                        <!-- </template> -->
                    </v-table>
                </v-col>
                <v-col :cols="12" md="4" sm="12" style="background-color: lightgray">
                    <p class="headline bold">Order #{{ order.id }}</p>
                    <v-table>
                        <template v-slot:default>
                            <tbody>
                                <tr>
                                    <td>Order Date:</td>
                                    <td class="text-right">
                                        {{ order.order_date }}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Payment Type: </td>
                                    <td class="text-right" >{{order.payment_type}}</td>
                                </tr>

                                <tr>
                                    <td>Order Status: </td>
                                    <td class="text-right" >{{order.order_status}}</td>
                                </tr>
                                <tr>
                                    <td>Ship Address: </td>
                                    <td class="text-right" >{{order.ship_address}}</td>
                                </tr>
                                <tr v-if="order.note">
                                    <td>Note: </td>
                                    <td class="text-right" >{{order.note}}</td>
                                </tr>
                                <tr>
                                    <td>Amount</td>
                                    <td class="text-right">
                                        ${{order.amount}}
                                    </td>
                                </tr>
                            </tbody>
                        </template>
                    </v-table>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>
<script>
import mixinsCart from "@/mixins/mixinsCart";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import Order from "@/models/order";
import mixinsOrder from "@/mixins/mixinsOrder";
export default {
    data() {
        return {
            noImageUrl: "@/assets/images/noImage.png",
            order: new Order(),
        };
    },
    props: ["baseURL", "config", "products"],
    mixins: [mixinsProduct, sweetAlert, mixinsOrder],
    methods: {
        checkout() {
            this.$router.push("/checkout");
        },
    },
    async created() {
        await this.getOrder();
        // console.log(this.cart);
    },
};
</script>
