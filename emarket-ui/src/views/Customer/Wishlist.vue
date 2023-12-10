<template>
    <div class="container">
        <p class="display-3 font-weight-light text-center pa-4">WishList</p>
        <div class="row">
            <div
                v-for="product of wishList.products"
                :key="product.id"
                class="col-md-4 col-sm-6 col-xs-12"
            >
                <ProductBox :product="product" :baseURL="baseURL" @changeCart="changeCart"> </ProductBox>
                
            </div>
        </div>
    </div>
</template>

<script>
import ProductBox from "../../components/Product/ProductBox";
import mixinsWishList from "@/mixins/mixinsWishList";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from 'vue';
export default {
    data() {
        return {
            wishList:null,
        };
    },
    props: ["baseURL"],
    name: "WishList",
    components: { ProductBox },
    mixins: [mixinsWishList,sweetAlert],

    methods: {
        changeCart(){
            this.getWishList();
            nextTick();
        }
    },
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
