<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">Our Products</h4>
                <router-link :to="{ name: 'AddProduct' }">
                    <button class="btn">Add a new Product</button>
                </router-link>
            </div>
        </div>
        <div class="row">
            <div
                v-for="product of products"
                :key="product.id"
                class="col-md-3 col-sm-6 col-xs-12"
            >
                <ProductBox :product="product" :baseURL="baseURL"> </ProductBox>
            </div>
        </div>
    </div>
</template>
<script>
import ProductBox from "@/components/Product/ProductBox";
import getFile from "@/mixins/getFile";
import sweetAlert from '@/mixins/sweetAlert';
import { nextTick } from "vue";
export default {
    name: "Product",
    components: { ProductBox },
    props: ["baseURL", "products", "config"],
    mixins: [getFile,sweetAlert],
    async created() {
        this.$emit("fetchData");
        await nextTick();
    },
};
</script>

<style scoped>
h4 {
    font-family: "Roboto", sans-serif;
    color: #484848;
    font-weight: 700;
}

#add-product {
    float: right;
    font-weight: 500;
}
</style>
