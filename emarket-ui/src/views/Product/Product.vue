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
            <v-table>
                <!-- <template v-slot:default> -->
                <thead>
                    <tr>
                        <th>ITEM</th>
                        <th>PRICE</th>
                        <th>STANDARD COST</th>
                        <th>STOCK</th>
                        <th>QUANTITY SOLD</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in products" :key="product">
                        <td>
                            <v-list>
                                <v-list-item
                                    :title="product.product_name"
                                    :subtitle="product.category.category_name"
                                    :prepend-avatar="
                                        product.product_images.length > 0 &&
                                        product.product_images[0].image != null
                                            ? getImageURL(
                                                  product.product_images[0].image
                                              )
                                            : require('@/assets/images/noImage.webp')
                                    "
                                >
                                </v-list-item>
                            </v-list>
                        </td>
                        <td>${{ product.price }}</td>
                        <td>${{ product.standard_cost }}</td>
                        <td>{{ product.stock }}</td>
                        <td></td>
                        <td>
                            <v-icon icon="mdi-pencil-circle" color="warning" size="x-large" class="px-7" title="Edit Product" @click="editProduct(product)"></v-icon>
                            <v-icon icon="mdi-delete-forever" color="error" size="x-large" class="px-7" title="Delete Product" @click="deleteProduct(product)"></v-icon>
                        </td>
                    </tr>
                </tbody>
                <!-- </template> -->
            </v-table>
        </div>
    </div>
</template>
<script>
import ProductBox from "@/components/Product/ProductBox";
import getFile from "@/mixins/mixinsFile";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from "vue";
import mixinsProduct from '@/mixins/mixinsProduct';
export default {
    name: "Product",
    components: { ProductBox },
    emits: ["fetchData"],
    props: ["baseURL", "products", "config"],
    mixins: [getFile, sweetAlert, mixinsProduct],
    async created() {
        // this.$emit("fetchData");
        // await nextTick();
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
@/mixins/mixinFile@/mixins/mixinsFile