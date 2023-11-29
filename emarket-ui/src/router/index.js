import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AddOrEditCategory from '../views/Category/AddOrEditCategory.vue'
import Category from '../views/Category/Category'
import AddOrEditProduct from '../views/Product/AddOrEditProduct.vue'
import Product from '../views/Product/Product'
import PageNotFound from '@/views/PageNotFound.vue'
import Cookies from 'js-cookie';
import ShowDetails from '@/views/Customer/ShowDetails.vue'
import ProductsCategory from '@/views/Product/ProductsCategory.vue'
import Cart from '@/views/Customer/Cart.vue'
import WishList from '@/views/Product/Wishlist.vue'
import Checkout from '@/views/Checkout/Checkout.vue'
import Layout from '@/components/Layout'
const routes = [
  // {
  //   path:'/',
  //   redirect:'/home'
  // },
  {
    path:'/',
    component:Layout,
    children:[
      {
        path: '/',
        name: 'home',
        component: HomeView
      },
      {
        path: '/login',
        name: 'login',
        component: () => import('../views/LoginView.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import('../views/RegisterView.vue')
      },
      {
        path : '/product/show/:id',
        name : 'ShowDetails',
        component: ShowDetails
      },
      {
        path : '/category/products/:id',
        name : 'ShowProductsCategory',
        component: ProductsCategory
      },
      {
        path: '/cart',
        name: 'Cart',
        component: Cart
      },
      
      {
        path: '/wishList',
        name: 'WishList',
        component: WishList
      },
      {
        path: '/checkout',
        name: 'Checkout',
        meta: {
          requiresAuth: true
        },
        component: Checkout
      },
      {
        path: '/seller/category/add',
        name: 'AddCategory',
        meta: {
          requiresAuth: true
        },
        component: AddOrEditCategory
      },
      {
        path: '/seller/category/edit/:id',
        name: 'EditCategory',
        meta: {
          requiresAuth: true
        },
        component: AddOrEditCategory
      },
      {
        path: '/seller/category',
        name: 'Category',
        meta: {
          requiresAuth: true
        },
        component: Category
      },
      {
        path: '/seller/product/add',
        name: 'AddProduct',
        meta: {
          requiresAuth: true
        },
        component: AddOrEditProduct
      },
      {
        path: '/seller/product/edit/:id',
        name: 'EditProduct',
        meta: {
          requiresAuth: true
        },
        component: AddOrEditProduct
      },
      {
        path: '/seller/product',
        name: 'Product',
        meta: {
          requiresAuth: true
        },
        component: Product
      },
      {
        path: "/:notFound",
        component: PageNotFound,
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!Cookies.get('user')) {
      const path = window.location.pathname;
      next({ name: 'login', query: { redirect: `${path}` }  })
    } else {
      next() // go to wherever I'm going
    }
  } else {
    next() // does not require auth, make sure to always call next()!
  }
})
export default router
