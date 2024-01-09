<template>
    <v-app id="inspire">
        <TopBar @search="search" :role="role" />
        <v-app-bar class="pb-0" >
            <v-bottom-navigation color="primary" horizontal style="position: relative">
                <v-btn @click="$router.push('/')">
                    <span>Home</span>
                </v-btn>
                <v-btn @click="$router.push('/shop')">
                    <span>Shop</span>
                </v-btn>
                <v-menu open-on-hover offset-y>
                    <template v-slot:activator="{ props }">
                        <v-btn v-bind="props"> Product </v-btn>
                    </template>
                    <v-list class="mx-auto" max-width="344px">
                        <v-list-item
                            v-for="(product, index) in products"
                            :key="index"
                            @click="$router.push('/product/show/' + product.id)"
                        >
                            <v-list-item-title>{{ product.product_name }}</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>

                <!-- <v-btn href="/blog">
                    <span>Blog</span>
                </v-btn> -->
            </v-bottom-navigation>
        </v-app-bar>
        <v-main>
            <router-view
                :baseURL="baseURL"
                :products="products"
                :categories="categories"
                :config="config"
                :textSearch="textSearch"
                @load="load"
            />
        </v-main>

        <v-footer class="p-0" style="flex: 0 1 auto">
            <v-row>
                <v-col cols="12" class="p-0">
                    <v-card color="accent">
                        <v-container>
                            <v-row no-gutters>
                                <v-col cols="12" md="4" sm="12">
                                    <v-row>
                                        <v-col class="pr-4" align="right" cols="12" sm="3">
                                            <v-icon class="text-h3">mdi-truck</v-icon>
                                        </v-col>
                                        <v-col class="pr-4" cols="12" sm="9">
                                            <h5 class="font-weight-light">
                                                FREE SHIPPING & RETURN
                                            </h5>
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
                                            <p class="font-weight-thin">
                                                30 Days Money Back Guarantee
                                            </p>
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
                </v-col>
                <v-col cols="12" class="p-0">
                    <v-card
                        flat
                        tile
                        width="100%"
                        class="text-white text-center"
                        color="secondary"
                        dark
                    >
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
                </v-col>
            </v-row>
        </v-footer>
    </v-app>
</template>
<script>
import { useDisplay } from "vuetify";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick, shallowRef } from "vue";
import WishList from "@/models/wishList";
import TopBar from "@/components/Layout/TopBar/TopBar.vue";
export default {
    props: ["products", "baseURL", "categories", "config", "role"],
    mixins: [mixinsProduct, sweetAlert],
    components: { TopBar },
    data() {
        return {
            textSearch: "",
        };
    },
    methods: {
        async load() {
            // this.cart = JSON.parse(localStorage.getItem("cart"));
            // this.wishList = JSON.parse(localStorage.getItem("wishList"));
        },
        search(textSearch) {
            this.textSearch = textSearch;
            this.$router.push({ name: "shop" });
        },
    },
    async created() {
        this.load();
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
