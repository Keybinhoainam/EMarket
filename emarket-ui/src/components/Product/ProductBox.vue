<template>
    <v-hover v-slot:default="{ isHovering, props }">
        <v-card class="mx-auto">
            <v-img
                class="text-white align-end"
                :aspect-ratio="1"
                height="300px"
                :src="
                    product.product_images.length > 0 && product.product_images[0].image != null
                        ? getImageURL(product.product_images[0].image)
                        : require('@/assets/images/noImage.webp')
                "
                cover
                v-bind="props"
            >
                <!-- <v-card-title>{{ product.category.category_name }} </v-card-title> -->
                <v-expand-transition>
                    <div
                        v-if="isHovering"
                        class="d-flex justify-center flex-column transition-fast-in-fast-out v-card--reveal display-3 text-white bg-grey-lighten-1"
                        style="height: 100%"
                    >
                        <v-btn :href="'/product/show/' + product.id" class="my-1">VIEW</v-btn>
                        
                        <v-btn v-if="$route.name=='WishList'" @click="removeWishListItem(product)" class="text-white my-1" color="red">Remove Item</v-btn>
                    </div>
                </v-expand-transition>
            </v-img>
            <v-card-text class="text-primary">
                <div>
                    <a href="/product" style="text-decoration: none">{{ product.product_name }}</a>
                </div>
                <div>${{ product.price }}</div>
            </v-card-text>
        </v-card>
    </v-hover>
</template>

<script>
import getFile from "@/mixins/mixinsFile";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import VLazyImage from "v-lazy-image";
import mixinsWishList from "@/mixins/mixinsWishList";
export default {
    name: "ProductBox",
    props: ["product", "baseURL"],
    emits: ["changeCart"],
    mixins: [getFile, sweetAlert, mixinsProduct, mixinsWishList],
    components: { VLazyImage },
    data() {
        return {
            baseGetImageUrl: `${this.baseURL}/data/file/images/`,
            noImageUrl: "@/assets/images/noImage.webp",
            wishList:null,
            
        };
    },
    created() {
        this.getWishList();
    },
    // watch:{
    //     wishList(wishList){
            
    //     }
    // }
};
</script>

<style scoped>
.embed-responsive .card-img-top {
    object-fit: fill;
}

a {
    text-decoration: none;
}

.card-title {
    color: #484848;
    font-size: 1.1rem;
    font-weight: 400;
}

.card-title:hover {
    font-weight: bold;
}

.card-text {
    font-size: 0.9rem;
}

#edit-product {
    float: right;
}
</style>
@/mixins/mixinFile@/mixins/mixinsFile