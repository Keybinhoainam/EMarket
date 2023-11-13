<template>
    <!-- <Navbar
    :cartCount="cartCount"
    @resetCartCount="resetCartCount"
    v-if="!['Signup', 'Signin'].includes($route.name)"
  /> -->
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
            cart:new Cart(),
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

    // components: { Footer, Navbar },
    mixins: [sweetAlert],
    methods: {
        async fetchData() {
            // fetch products
            // console.log(this.schema);
            await productService
                .getAllProducts(
                    `${this.baseURL}/data/product/getallproducts`,
                    this.config
                )
                .then(
                    (res) => {
                        this.products = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail(
                            "Failed to load all products",
                            error.message
                        );
                    }
                );

            //fetch categories
            await categoryService
                .getAllCategories(
                    `${this.baseURL}/data/category/getallcategories`,
                    this.config
                )
                .then(
                    (res) => {
                        this.categories = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail(
                            "Failed to load all categories",
                            error.message
                        );
                    }
                );

            //fetch cart items
            // if (this.token) {
            //   await axios.get(`${this.baseURL}cart/?token=${this.token}`).then(
            //     (response) => {
            //       if (response.status == 200) {
            //         // update cart
            //         this.cartCount = Object.keys(response.data.cartItems).length;
            //       }
            //     },
            //     (error) => {
            //       console.log(error);
            //     }
            //   );
            // }
        },
        // resetCartCount() {
        //     this.cartCount = 0;
        // },
    },
    created() {
        // this.schema = Yup.object().shape({
        //     name: Yup.string().required(),
        //     email: Yup.string().email().required(),
        //     password: Yup.string().min(6).required(),
        //     confirm_password: Yup.string()
        //         .required()
        //         .oneOf([Yup.ref("password")], "Passwords do not match"),
        // });
        
        // console.log(this.categories);
    },
    async mounted() {
        // this.token = localStorage.getItem("token");
        
        this.config=authHeader();
        await this.fetchData();
    },
};
</script>

<style>
html {
    overflow-y: scroll;
}
</style>
