import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/Customer/HomeView.vue";
import AddOrEditCategory from "@/views/Admin/AddOrEditCategory.vue";
import Category from "@/views/Admin/Category";
import AddOrEditProduct from "@/views/Seller/AddOrEditProduct.vue";
import Product from "@/views/Seller/Product";
import PageNotFound from "@/views/PageNotFound.vue";
import Cookies from "js-cookie";
import ShowDetails from "@/views/Customer/ShowDetails.vue";
import Shop from "@/views/Customer/Shop.vue";
import Cart from "@/views/Customer/Cart.vue";
import WishList from "@/views/Customer/Wishlist.vue";
import CustomerLayout from "@/components/Layout/CustomerLayout.vue";
import SellerLayout from "@/components/Layout/SellerLayout.vue";
import MyStore from "@/components/AccountSetting/MyStore.vue";
import CheckOut from "@/views/Customer/CheckOut.vue";
import ShowOrderDetailVue from "@/views/Customer/ShowOrderDetail.vue";
import SellerShowOrderDetailVue from "@/views/Seller/ShowOrderDetail.vue";
import OrdersVue from "@/views/Seller/Orders.vue";
import SellerDashBoardVue from "@/views/Seller/SellerDashBoard.vue";
import AdminAccountSettingVue from "@/views/Common/AccountSetting.vue";
import SellerAccountSettingVue from "@/views/Common/AccountSetting.vue";
import CustomerAccountSettingVue from "@/views/Common/AccountSetting.vue";

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
        props: { role: "customer" },
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
                path: "/cart",
                name: "Cart",
                component: Cart,
            },
            {
                path: "/checkout",
                name: "CheckOut",
                meta: {
                    requiresAuth: true,
                },
                component: CheckOut,
            },
            {
                path: "/wishList",
                name: "WishList",
                component: WishList,
            },
            {
                path: "/profile",
                name: "CustomerProfile",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "profile" },
                component: CustomerAccountSettingVue,
            },
            {
                path: "/security",
                name: "Security",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "security" },
                component: CustomerAccountSettingVue,
            },
            {
                path: "/myPurchase",
                name: "MyPurchase",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "myPurchase" },
                component: CustomerAccountSettingVue,
            },
            {
                path: "/showOrderDetail/:id",
                name: "ShowOrderDetail",
                meta: {
                    requiresAuth: true,
                },
                component: ShowOrderDetailVue,
            },
            {
                path: "/myStore",
                name: "CreateMyStore",
                meta: {
                    requiresAuth: true,
                },
                props: { tab: "myStore" },
                component: CustomerAccountSettingVue,
            },
        ],
    },
    {
        path: "/seller",
        component: SellerLayout,
        props: { role: "seller" },
        meta: {
            requiresAuth: true,
            // requireSellerAuth: true,
        },
        children: [
            {
                path: "/seller/profile",
                name: "SellerProfile",
                props: { tab: "profile" },
                component: SellerAccountSettingVue,
            },
            {
                path: "/seller/security",
                name: "Security",
                props: { tab: "security" },
                component: SellerAccountSettingVue,
            },
            {
                path: "/seller/dashBoard",
                name: "DashBoard",
                component: SellerDashBoardVue,
            },
            {
                path: "/seller/showOrderDetail/:id",
                name: "SellerShowOrderDetail",
                component: SellerShowOrderDetailVue,
            },
            {
                path: "/seller/orders",
                name: "Orders",
                component: OrdersVue,
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
        path: "/admin",
        component: SellerLayout,
        props: { role: "admin" },
        meta: {
            requiresAuth: true,
            // requireSellerAuth: true,
        },
        children: [
            {
                path: "/admin/profile",
                name: "AdminProfile",
                props: { tab: "profile" },
                component: AdminAccountSettingVue,
            },
            {
                path: "/admin/security",
                name: "Security",
                props: { tab: "security" },
                component: AdminAccountSettingVue,
            },
            {
                path: "/admin/category/add",
                name: "AddCategory",
                component: AddOrEditCategory,
            },
            {
                path: "/admin/category/edit/:id",
                name: "EditCategory",
                component: AddOrEditCategory,
            },
            {
                path: "/admin/category",
                name: "Category",
                component: Category,
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
        if (!Cookies.get("accessToken")||!Cookies.get("user")) {
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
