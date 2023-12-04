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
import authHeader from "./services/authHeader";
import categoryService from "./services/category.service";
import productService from "./services/product.service";
import * as Yup from "yup";
import Cookies from "js-cookie";
import Cart from "./models/cart";
// import Navbar from './components/Navbar.vue';
// import Footer from './components/Footer.vue';
export default {
    data() {
        return {
            cart: new Cart(),
            baseURL: "http://localhost:8080/api",
            products: null,
            categories: null,
            config: null,
            schema: null,
            
            // key: 0,
            // token: null,
            // cartCount: 0,
        };
    },

    // components: { Layout },
    mixins: [sweetAlert],
    methods: {
        async fetchData() {
            // console.log(this.getAllProductsUrl);
            // await this.$store.dispatch('data/fetchProductsData',{url:`${this.baseURL}/data/product/getallproducts`,config:this.config})
            // await this.$store.dispatch('data/fetchCategoriesData',{url:`${this.baseURL}/data/category/getallcategories`,config:this.config})
            // console.log(this.$store.state.data.products);
            // this.products=this.$store.state.data.products;
            // this.categories=this.$store.state.data.categories;


            await productService
                .getAllProducts(`${this.baseURL}/data/product/getallproducts`, this.config)
                .then(
                    (res) => {
                        this.products = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all products", error.message);
                    }
                );

            //fetch categories
            await categoryService
                .getAllCategories(`${this.baseURL}/data/category/getallcategories`, this.config)
                .then(
                    (res) => {
                        this.categories = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all categories", error.message);
                    }
                );

            
        },
        // resetCartCount() {
        //     this.cartCount = 0;
        // },
    },
    async created() {
        // this.schema = Yup.object().shape({
        //     name: Yup.string().required(),
        //     email: Yup.string().email().required(),
        //     password: Yup.string().min(6).required(),
        //     confirm_password: Yup.string()
        //         .required()
        //         .oneOf([Yup.ref("password")], "Passwords do not match"),
        // });
        // console.log(this.categories);
        this.config = authHeader();
        this.$store.commit("data/changeConfig",this.config);
        await this.fetchData();
    },
     mounted() {
        // this.token = localStorage.getItem("token");

        
    },
};
</script>

<style>
html {
    overflow-y: scroll;
}
</style>
