<template>
    <!-- <Navbar
    :cartCount="cartCount"
    @resetCartCount="resetCartCount"
    v-if="!['Signup', 'Signin'].includes($route.name)"
  /> -->
    <!-- <Layout :baseURL="baseURL" :products="products" :categories="categories" :config="config" /> -->
    <div style="min-height: 60vh">
        <router-view
            :baseURL="baseURL"
            :products="products"
            :categories="categories"
            :config="config"
            :schema="schema"
            @fetchData="fetchData"
        >
        </router-view>
    </div>
    <!-- <Footer v-if="!['Signup', 'Signin'].includes($route.name)" /> -->
</template>

<script>
import sweetAlert from "./mixins/sweetAlert";
import mixinsData from "./mixins/mixinsData";
import authHeader from "./services/authHeader";
import Cart from "./models/cart";
// import Navbar from './components/Navbar.vue';
// import Footer from './components/Footer.vue';
export default {
    data() {
        return {
            cart: new Cart(),
            baseURL: "http://localhost:8080/api",
            products: this.$store.state.data.products,
            categories: this.$store.state.data.categories,
            config: this.$store.state.data.config,
            schema: null,
        };
    },

    // components: { Layout },
    mixins: [sweetAlert, mixinsData],
    methods: {},
    async created() {
        this.config = await authHeader();
        await this.$store.dispatch("data/changeConfig", this.config);
        await this.fetchData();
    },
    async mounted() {},
};
</script>

<style>
html {
    overflow-y: scroll;
}
</style>
