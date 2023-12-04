import categoryService from "@/services/category.service";
import productService from "@/services/product.service";
import sweetAlert from "@/mixins/sweetAlert";
export const data = {
    namespaced: true,
    state: {
        products: null,
        categories: null,
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
        // login({ commit }, { url, user, config }) {
        //     return AuthService.login(url, user, config).then(
        //         (user) => {
        //             commit("loginSuccess", user);
        //             return Promise.resolve(user);
        //         },
        //         (error) => {
        //             commit("loginFailure");
        //             return Promise.reject(error);
        //         }
        //     );
        // },
        // logout({ commit }) {
        //     AuthService.logout();
        //     commit("logout");
        // },
        // register({ commit }, { url, user }) {
        //     return AuthService.register(url, user).then(
        //         (user) => {
        //             commit("registerSuccess", user);
        //             return Promise.resolve(user);
        //         },
        //         (error) => {
        //             commit("registerFailure");
        //             return Promise.reject(error);
        //         }
        //     );
        // },
    },
    mutations: {
        fetchProductsData(state,products){
            state.products=products;
        },
        fetchCategoriesData(state,categories){
            state.categories=categories;
        },
        // loginSuccess(state, user) {
        //     state.status.loggedIn = true;
        //     state.user = user;
        // },
        // loginFailure(state) {
        //     state.status.loggedIn = false;
        //     state.user = null;
        // },
        // logout(state) {
        //     state.status.loggedIn = false;
        //     state.user = null;
        // },
        // registerSuccess(state) {
        //     state.status.loggedIn = false;
        // },
        // registerFailure(state) {
        //     state.status.loggedIn = false;
        // },
    },
};
