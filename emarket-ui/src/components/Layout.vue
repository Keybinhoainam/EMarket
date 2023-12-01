<template>
    <v-app id="inspire">
        <v-app-bar :clipped-left="lgAndUp" app color="primary" dark>
            <!--      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />-->

            <v-toolbar-title style="width: 350px">
                <a href="/" class="text-white" style="text-decoration: none"
                    ><v-icon>mdi-store</v-icon>&nbsp;Emarket</a
                >
            </v-toolbar-title>
            <v-text-field
                flat
                solo-inverted
                hide-details
                prepend-inner-icon="mdi-magnify"
                label="Search"
                class="hidden-sm-and-down pl-10 ml-4"
                v-model="textSearch"
                @keyup.enter="search()"
            />
            <v-spacer />
            <v-btn icon title="Account">
                <v-icon>mdi-account-circle</v-icon>
            </v-btn>
            <v-btn icon title="Notification">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-bell</v-icon>
                </v-badge>
            </v-btn>
            <v-btn href="/cart" icon title="Cart">
                <v-badge :content="cart.cart_details.length"  color="green" overlap>
                    <v-icon>mdi-cart</v-icon>
                </v-badge>
            </v-btn>
            <v-btn href="/wishlist" icon title="Wishlist">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-tag-heart</v-icon>
                </v-badge>
            </v-btn>
        </v-app-bar>

        <v-main class="pb-0">
            <v-bottom-navigation color="primary" horizontal style="position: relative">
                <v-btn href="/">
                    <span>Home</span>
                </v-btn>
                <v-btn href="/shop">
                    <span>Shop</span>
                </v-btn>
                <v-menu open-on-hover offset-y>
                    <template v-slot:activator="{ props }">
                        <v-btn v-bind="props"> Product </v-btn>
                    </template>
                    <v-list class="mx-auto" max-width="344px">
                        <v-list-item v-for="(product, index) in products" :key="index" :href="'/product/show/'+product.id">
                            <v-list-item-title>{{ product.product_name }}</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>
                
                <v-btn href="/blog">
                    <span>Blog</span>
                </v-btn>
            </v-bottom-navigation>
        </v-main>
        <router-view
            :baseURL="baseURL"
            :products="products"
            :categories="categories"
            :config="config"
            :textSearch="textSearch"
            @loadCart="loadCart"
        />
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
        <v-footer class="p-0">
            <v-card flat tile width="100%" class="text-white text-center" color="secondary" dark>
                <v-card-text>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-home"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-email"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-calendar"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-delete"></v-icon>
                    </v-btn>
                </v-card-text>

                <v-card-text class="text-white pt-0">
                    I'm Bùi Hoài Nam from PTIT with love !!!
                </v-card-text>

                <v-divider></v-divider>

                <v-card-text class="text-white">
                    {{ new Date().getFullYear() }} — <strong>Emarket</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </v-app>
</template>
<script>
import mixinsCart from "@/mixins/mixinsCart";
import Cart from "@/models/cart";
import { useDisplay } from "vuetify";
import mixinsProduct from '@/mixins/mixinsProduct';
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick, shallowRef  } from 'vue';
export default {
    setup(){
        const { lgAndUp } = useDisplay();
        return {
            lgAndUp
        }
    },
    props: ["products", "baseURL", "categories", "config"],
    mixins: [mixinsCart,mixinsProduct,sweetAlert],
    data() {
        return {
            cartItemsQuantity: 0,
            cart: new Cart(),
            textSearch:"",
        };
    },
    methods: {
        async loadCart(){
            this.cart = JSON.parse(localStorage.getItem("cart"));
        },
        search(){
            this.$router.push({name:"shop"})
        }
    },
    async created() {
        this.loadCart();
    },
    
};


</script>
<style scoped>
.bottom-navigation-wrapper {
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index: 1000;
}
</style>
