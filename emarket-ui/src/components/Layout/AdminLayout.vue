<template>
    <v-app id="inspire">
        <v-layout>
            <Sidebar :drawer="drawer" />
            <TopBar @drawerEvent="drawerEvent" />
            <v-main style="background: #f5f5f540">
                <v-container class="py-8 px-6" fluid>
                    <router-view
                        :baseURL="baseURL"
                        :products="products"
                        :categories="categories"
                        :config="config"
                        @fetchData="fetchData"
                    />
                </v-container>
            </v-main>
        </v-layout>
    </v-app>
</template>
<script>
import mixinsCart from "@/mixins/mixinsCart";
import Cart from "@/models/cart";
import { useDisplay } from "vuetify";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import WishList from "@/models/wishList";
import TopBar from "@/components/Layout/TopBar.vue";
import Sidebar from "@/components/Layout/Sidebar.vue";
export default {
    setup() {
        const { lgAndUp } = useDisplay();
        return {
            lgAndUp,
        };
    },
    props: ["products", "baseURL", "categories", "config"],
    emits:["fetchData"],
    // mixins: [mixinsCart, mixinsProduct, sweetAlert],
    components: { TopBar, Sidebar },
    data() {
        return {
            cartItemsQuantity: 0,
            cart: new Cart(),
            wishList: new WishList(),
            textSearch: "",
            drawer: true,
        };
    },
    methods: {
        async load() {
            this.cart = JSON.parse(localStorage.getItem("cart"));
            this.wishList = JSON.parse(localStorage.getItem("wishList"));
        },
        search() {
            this.$router.push({ name: "shop" });
        },
        drawerEvent() {
            this.drawer = !this.drawer;
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
