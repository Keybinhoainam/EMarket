import categoryService from "@/services/category.service";
import productService from "@/services/product.service";
import User from "@/models/user";
import Cookies from "js-cookie";
export const data = {
    namespaced: true,
    state: {
        baseURL: "http://localhost:8080/api",
        products: null,
        categories: null,
        config: null,
        user:Cookies.get("user")?JSON.parse(Cookies.get("user")):new User(),
        // avatarFile:null
    },
    actions: {
        fetchProductsData({ commit }, { url, config }) {
            productService
                .getAllProducts(url, config)
                .then(
                    (res) => {
                        commit("fetchProductsData",res.data)
                        // this.products = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all products", error.message);
                    }
                );
        },
        fetchCategoriesData({ commit }, { url, config }) {
            categoryService
                .getAllCategories(url, config)
                .then(
                    (res) => {
                        console.log("load Products");
                        commit("fetchCategoriesData",res.data)
                        // this.products = res.data;
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed to load all Categories", error.message);
                    }
                );
        },
        changeConfig({commit},config){
            commit("changeConfig",config)
        },
        changeUser({commit},user){
            commit("changeUser",user)
        }
    },
    mutations: {
        fetchProductsData(state,products){
            state.products=products;
        },
        fetchCategoriesData(state,categories){
            state.categories=categories;
        },
        changeConfig(state,config){
            state.config=config;
        },
        changeUser(state,user){
            Cookies.set("user",JSON.stringify(user));
            // console.log(user.avatarFile);
            // state.avatarFile=user.avatarFile;
            // console.log(state.avatarFile);
            state.user=user;
        },

    },
};
