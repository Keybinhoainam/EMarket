
import categoryService from "@/services/category.service";
import productService from "@/services/product.service";
import { nextTick } from "vue";
export default {
    data() {
        return {
            
        };
    },
    methods: {
        async fetchData() {
            // console.log(this.getAllProductsUrl);
            // await this.$store.dispatch('data/fetchProductsData',{url:`${this.baseURL}/data/product/getallproducts`,config:this.config})
            // await this.$store.dispatch('data/fetchCategoriesData',{url:`${this.baseURL}/data/category/getAllCategories`,config:this.config})
            // console.log(this.$store.state.data.products);
            // this.products=this.$store.state.data.products;
            // this.categories=this.$store.state.data.categories;


            await productService
                .getAllProducts(`${this.baseURL}/data/product/getallproducts`, this.config)
                .then(
                    (res) => {
                        this.products = res.data;
                        this.$store.commit('data/changeProducts',res.data);
                        // console.log(this.$store.state.data.products);
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all products", error.message);
                    }
                );

            //fetch categories
            await categoryService
                .getAllCategories(`${this.baseURL}/data/category/getAllCategories`, this.config)
                .then(
                    (res) => {
                        this.categories = res;
                        this.$store.commit('data/changeCategories',res);
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all categories", error.message);
                    }
                );

            
        },
        
    },
};
