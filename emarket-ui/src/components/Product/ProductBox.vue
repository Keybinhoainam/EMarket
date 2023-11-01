<template>
    <div class="card h-100 w-100" width = "1000" height = "1000">
        <div class="embed-responsive embed-responsive-16by9">
            
            <img
                class="card-img-top embed-responsive-item " 
                width = "300" height = "300"
                :src="
                    product.product_images.length > 0 &&
                    product.product_images[0].image != null
                        ? getImageURL(product.product_images[0].image)
                        : noImageUrl
                "
                alt="Product Image"
            />
        </div>
        <div class="card-body">
            <router-link
                :to="{ name: 'ShowDetails', params: { id: product.id } }"
                ><h5 class="card-title">
                    {{ product.product_name }}
                </h5></router-link
            >
            <p class="card-text"><sup>$</sup>{{ product.price }}</p>
            <p class="card-text font-italic">
                {{ product.short_description }}...
            </p>
            <router-link
                id="edit-product"
                :to="{ name: 'EditProduct', params: { id: product.id } }"
                v-if="$route.name == 'Product'"
            >
                Edit
            </router-link>
        </div>
    </div>
</template>

<script>
import getFile from "@/mixins/getFile";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import VLazyImage from "v-lazy-image";
export default {
    name: "ProductBox",
    props: ["product","baseURL"],
    mixins: [getFile, sweetAlert, mixinsProduct],
    components:{VLazyImage},
    data() {
        return {
            baseGetImageUrl: `${this.baseURL}/data/file/images/`,
            noImageUrl:
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png",
        };
    }
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
