<template>
    <div class="container">
        <router-link :to="{ name: 'home' }">
            <button>Continue shopping</button>
        </router-link>
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">Your WishList</h4>
            </div>
        </div>

        <div class="row">
            <div
                v-for="product of wishList.products"
                :key="product.id"
                class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex flex-column"
            >
                <ProductBox :product="product" :baseURL="baseURL"> </ProductBox>
                <button @click="removeWishListItem(product)">Remove item</button>
            </div>
        </div>
    </div>
</template>

<script>
import WishList from "@/models/wishList";
import ProductBox from "../../components/Product/ProductBox";
import mixinsWishList from "@/mixins/mixinsWishList";
import sweetAlert from "@/mixins/sweetAlert";
export default {
    data() {
        return {
            wishList: new WishList(),
        };
    },
    props: ["baseURL"],
    name: "WishList",
    components: { ProductBox },
    mixins: [mixinsWishList,sweetAlert],

    methods: {},
    created() {
        this.getWishList();
    },
};
</script>

<style scoped>
h4 {
    font-family: "Roboto", sans-serif;
    color: #484848;
    font-weight: 700;
}
</style>
