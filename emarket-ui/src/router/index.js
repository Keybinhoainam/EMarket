import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/Customer/HomeView.vue";
import AddOrEditCategory from "@/views/Category/AddOrEditCategory.vue";
import Category from "@/views/Category/Category";
import AddOrEditProduct from "@/views/Product/AddOrEditProduct.vue";
import Product from "@/views/Product/Product";
import PageNotFound from "@/views/PageNotFound.vue";
import Cookies from "js-cookie";
import ShowDetails from "@/views/Customer/ShowDetails.vue";
import Shop from "@/views/Customer/Shop.vue";
import ProductsCategory from "@/views/Product/ProductsCategory.vue";
import Cart from "@/views/Customer/Cart.vue";
import WishList from "@/views/Customer/Wishlist.vue";
import Checkout from "@/views/Checkout/Checkout.vue";
import CustomerLayout from "@/components/Layout/CustomerLayout.vue";
import AdminLayout from "@/components/Layout/AdminLayout.vue";
import AccountSetting from "@/views/Common/AccountSetting.vue";
import MyStore from "@/components/AccountSetting/MyStore.vue";
const routes = [
    // {
    //   path:'/',
    //   redirect:'/home'
    // },
    {
        path: "/login",
        name: "login",
        component: () => import("@/views/LoginView.vue"),
    },
    {
        path: "/register",
        name: "register",
        component: () => import("@/views/RegisterView.vue"),
    },

    {
        path: "/customer",
        component: CustomerLayout,
        props: { isCustomer: true },
        children: [
            {
                path: "/",
                name: "home",
                component: HomeView,
            },
            {
                path: "/shop",
                name: "shop",
                component: Shop,
            },

            {
                path: "/product/show/:id",
                name: "ShowDetails",
                component: ShowDetails,
            },
            {
                path: "/category/products/:id",
                name: "ShowProductsCategory",
                component: ProductsCategory,
            },
            {
                path: "/cart",
                name: "Cart",
                component: Cart,
            },

            {
                path: "/wishList",
                name: "WishList",
                component: WishList,
            },
            {
                path: "/checkout",
                name: "Checkout",
                meta: {
                    requiresAuth: true,
                },
                component: Checkout,
            },
            {
                path: "/profile",
                name: "Profile",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "profile" },
                component: AccountSetting,
            },
            {
                path: "/security",
                name: "Security",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "security" },
                component: AccountSetting,
            },
            {
                path: "/myPurchase",
                name: "MyPurchase",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "myPurchase" },
                component: AccountSetting,
            },
            {
                path: "/myStore",
                name: "CreateMyStore",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "myStore" },
                component: AccountSetting,
            },
        ],
    },
    {
        path: "/seller",
        component: AdminLayout,
        props: { isCustomer: false },
        meta: {
            requiresAuth: true,
            // requireSellerAuth: true,
        },
        children: [
            {
                path: "/seller/category/add",
                name: "AddCategory",
                component: AddOrEditCategory,
            },
            {
                path: "/seller/category/edit/:id",
                name: "EditCategory",
                component: AddOrEditCategory,
            },
            {
                path: "/seller/category",
                name: "Category",
                component: Category,
            },
            {
                path: "/seller/product/add",
                name: "AddProduct",
                component: AddOrEditProduct,
            },
            {
                path: "/seller/product/edit/:id",
                name: "EditProduct",
                component: AddOrEditProduct,
            },
            {
                path: "/seller/product",
                name: "Product",
                component: Product,
            },
            {
                path: "/seller/store",
                name: "MyStore",
                component: MyStore,
            },
        ],
    },

    {
        path: "/:notFound",
        component: PageNotFound,
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});
router.beforeEach((to, from, next) => {
    if (to.matched.some((record) => record.meta.requiresAuth)) {
        if (!Cookies.get("accessToken")) {
            const path = window.location.pathname;
            next({ name: "login", query: { redirect: `${path}` } });
        } else {
            next();
        }
    }
    // else if(to.matched.some((record) => record.meta.requireSellerAuth)){
    //     console.log(JSON.parse(Cookies.get("user")).roles[0].name=="CUSTOMER");
    //     if (Cookies.get("accessToken")&&Cookies.get("user")&&JSON.parse(Cookies.get("user")).roles[0].name!="CUSTOMER") {
    //         next();
    //     } else {
    //         const path = window.location.pathname;

    //         next({ name: "login", query: { redirect: `${path}` } });
    //     }
    // }
    else {
        next(); // does not require auth, make sure to always call next()!
    }
});
export default router;
