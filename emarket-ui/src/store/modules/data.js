import categoryService from "@/services/category.service";
import productService from "@/services/product.service";
import User from "@/models/user";
import Cookies from "js-cookie";
import Cart from "@/models/cart";
import WishList from "@/models/wishList";
export const data = {
    namespaced: true,
    state: {
        baseURL: "http://localhost:8080/api",
        products: null,
        categories: null,
        config: null,
        user:Cookies.get("user")?JSON.parse(Cookies.get("user")):new User(),
        cart:JSON.parse(localStorage.getItem("cart"))?JSON.parse(localStorage.getItem("cart")):new Cart(),
        wishList:JSON.parse(localStorage.getItem("wishList"))?JSON.parse(localStorage.getItem("wishList")):new WishList(),
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
        },
        changeStoreUser({commit},store){
            commit("changeStoreUser",store)
        }
    },
    mutations: {
        fetchProductsData(state,products){
            state.products=products;
        },
        fetchCategoriesData(state,categories){
            state.categories=categories;
        },
        changeProducts(state,products){
            state.products=products

        },
        changeCategories(state,categories){
            state.categories=categories

        },
        changeConfig(state,config){
            state.config=config;
        },
        changeUser(state,user){
            Cookies.set("user",JSON.stringify(user), { expires: 1 });
            state.user=user;
        },
        changeStoreUser(state,store){
            store.imageString=null;
            store.imageFile=null;
            state.user.store={...store};
            state.user.store.imageString=null;
            state.user.store.imageFile=null;
            console.log(store);
            console.log(state.user.store);
            Cookies.set("user",JSON.stringify(state.user));
            console.log(JSON.parse(Cookies.get("user")).store);
            // Cookies.set("user",JSON.stringify(state.user));
            // console.log(JSON.parse(Cookies.get("user")).store);
            
        },
        changeCart(state,cart){
            localStorage.setItem("cart",JSON.stringify(cart));
            state.cart=cart;
        },
        changeWishList(state,wishList){
            localStorage.setItem("wishList",JSON.stringify(wishList));
            state.wishList=wishList;
        },

    },
};
