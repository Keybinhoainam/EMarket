<template>
    <div class="container">
        <router-link :to="{ name: 'home' }">
            <button>Continue shopping</button>
        </router-link>
        <div class="row pt-5">
            <div class="col-md-1"></div>
            <div class="col-md-4 col-12">
                <div
                    v-for="(img, key) in product.product_images"
                    :key="key"
                    class="imagePreviewWrapper"
                >
                    <img
                        class="card-img-top embed-responsive-item"
                        :src="getImageURL(img.image)"
                        alt="Product Image"
                    />
                </div>
            </div>
            <div class="col-md-6 col-12 pt-3 pt-md-0">
                <h4>{{ product.product_name }}</h4>
                <h6 class="category font-italic">
                    {{ product.category.category_name }}
                </h6>
                <h6 class="font-weight-bold">$ {{ product.price }}</h6>
                <p>
                    {{ product.description }}
                </p>

                <div class="d-flex flex-row justify-content-between">
                    <div class="input-group col-md-3 col-4 p-0">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Quantity</span>
                        </div>
                        <input class="form-control" type="number" v-model="quantity" />
                    </div>

                    <div class="input-group col-md-3 col-4 p-0">
                        <button
                            type="button"
                            id="add-to-cart-button"
                            class="btn"
                            @click="addToCart(this.product, this.quantity)"
                        >
                            Add to Cart
                            <ion-icon name="cart-outline" v-pre></ion-icon>
                        </button>
                    </div>
                </div>

                <div class="features pt-3">
                    <h5><strong>Features</strong></h5>
                    <ul>
                        <li>Lorem ipsum dolor sit amet consectetur adipisicing elit.</li>
                        <li>Officia quas, officiis eius magni error magnam voluptatem</li>
                        <li>nesciunt quod! Earum voluptatibus quaerat dolorem doloribus</li>
                        <li>molestias ipsum ab, ipsa consectetur laboriosam soluta et</li>
                        <li>ut doloremque dolore corrupti, architecto iusto beatae.</li>
                    </ul>
                </div>

                <button
                    id="wishlist-button"
                    class="btn mr-3 p-1 py-0"
                    @click="addToWishList(this.product)"
                >
                    Add to wishlist
                </button>
                <router-link :to="{ name: 'Cart' }"
                    ><button id="show-cart-button" type="button" class="btn mr-3 p-1 py-0">
                        Show Cart

                        <ion-icon name="cart-outline" v-pre></ion-icon></button
                ></router-link>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</template>

<script>
import Product from "@/models/product";
import productService from "@/services/product.service";
import { useRoute } from "vue-router";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from "vue";
import mixinsCart from "@/mixins/mixinsCart";
import mixinsWishList from "@/mixins/mixinsWishList";

export default {
    data() {
        return {
            product: new Product(),
            noImageUrl: "@/assets/images/noImage.webp",
            quantity: 1,
            getProductUrl: `${this.baseURL}/data/product/get/`,
        };
    },
    props: ["baseURL", "products", "categories"],
    mixins: [mixinsProduct, sweetAlert, mixinsCart, mixinsWishList],
    methods: {},
    async created() {
        const route = useRoute();
        if (route.params.id) {
            await this.getProductNoCofig(route.params.id);
        }
    },
    mounted() {},
};
</script>

<style>
.category {
    font-weight: 400;
}

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type="number"] {
    -moz-appearance: textfield;
}

#add-to-cart-button {
    background-color: #febd69;
}

#wishlist-button {
    background-color: #b9b9b9;
    border-radius: 0;
}

#show-cart-button {
    background-color: #131921;
    color: white;
    border-radius: 0;
}
</style>
