<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 v-if="!isEdit" class="pt-3">Add New Product</h3>
                <h3 v-if="isEdit" class="pt-3">Edit Product</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-3"></div>
            <div class="col-md-6 px-5 px-md-0">
                <form @submit.prevent="save">
                    <div class="form-group">
                        <label>Category</label>
                        <select
                            class="form-control"
                            v-model="product.category"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.category.id,
                            }"
                        >
                            <option
                                v-for="category of categories"
                                :key="category"
                                :value="category"
                            >
                                {{ category.category_name }}
                            </option>
                        </select>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.category.id }}
                        </div>
                        <!-- <div
                            class="invalid-feedback mt-1 mb-4 ms-1"
                            v-if="errors.has('category')"
                        >
                            {{ errors.first("category") }}
                        </div> -->
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input
                            type="text"
                            class="form-control"
                            v-model="product.product_name"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.product_name,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.product_name }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Stock</label>
                        <input
                            type="number"
                            class="form-control"
                            v-model="product.stock"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.stock,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.stock }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input
                            type="text"
                            class="form-control"
                            v-model="product.description"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.description,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.description }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Short Description</label>
                        <input
                            type="text"
                            class="form-control"
                            v-model="product.short_description"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.short_description,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.short_description }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Standard Cost</label>
                        <input
                            type="number"
                            class="form-control"
                            v-model="product.standard_cost"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.standard_cost,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.standard_cost }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input
                            type="number"
                            class="form-control"
                            v-model="product.price"
                            @blur="validateProduct()"
                            v-bind:class="{
                                'is-invalid': errors.price,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.price }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Is new ?</label>
                        <select
                            class="form-select"
                            aria-label="Select an option"
                            v-model="product.is_new"
                        >
                            <option value="true" selected>True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Is featured ?</label>
                        <select
                            class="form-select"
                            aria-label="Select an option"
                            v-model="product.is_featured"
                        >
                            <option value="true" selected>True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Is discontinued ?</label>
                        <select
                            class="form-select"
                            aria-label="Select an option"
                            v-model="product.is_discontinued"
                        >
                            <option value="true" selected>True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Image</label>
                        <div
                            v-for="(img, key) in product.product_images"
                            :key="key"
                            class="imagePreviewWrapper"
                        >
                            <div class="imagePreviewWrapper">
                                <img
                                    :ref="'image'"
                                    :src="require('@/assets/images/noImage.webp')"
                                    alt=""
                                    :aria-placeholder="require('@/assets/images/noImage.webp')"
                                    class="image-holder"
                                    width="250"
                                    height="250"
                                />
                                <button type="button" @click="removeImage(key)">X</button>
                            </div>
                        </div>
                        <input
                            type="file"
                            ref="file"
                            multiple="multiple"
                            accept="image/*"
                            @change="uploadImages"
                            class="row"
                            required
                        />
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</template>

<script>
// import { VueMultiImageUpload } from '@zakerxa/vue-multiple-image-upload';
import { Form, ErrorMessage, Field } from "vee-validate";
import { useRoute } from "vue-router";
import Product from "@/models/product";
import productService from "@/services/product.service";
import sweetAlert from "@/mixins/sweetAlert";
import mixinsProduct from "@/mixins/mixinsProduct";
import Cookies from "js-cookie";
import { getCurrentInstance } from "vue";
import { nextTick } from "vue";
export default {
    props: ["baseURL", "products", "categories", "config", "schema"],
    emits: ["fetchData"],
    data() {
        return {
            product: new Product(),
            saveProductUrl: `${this.baseURL}/seller/product/save`,
            saveProductImagesUrl: `${this.baseURL}/seller/product/images/save`,
            noImageUrl: "@/assets/images/noImage.webp",
            previewImage: null,
            isEdit: false,
        };
    },
    mixins: [sweetAlert, mixinsProduct],
    components: { Form, ErrorMessage, Field },
    // components:{
    //   VueMultiImageUpload
    // },
    async created() {
        const route = useRoute();
        if (route.params.id) {
            this.isEdit = true;
            await this.getProduct(route.params.id);
            // console.log(this.product);
        }

        this.applyImages();
        this.checkCategoryEmpty();
    },
    methods: {
        uploadImages(e) {
            let selectedFiles = e.target.files;
            for (let i = 0; i < selectedFiles.length; i++) {
                this.product.product_images.push(selectedFiles[i]);
            }
            nextTick();
            this.applyImages();
        },

        removeImage(index) {
            this.product.product_images.splice(index, 1);

            this.applyImages();
            //this.$refs.image[index].src = "" // You are hidding the 3rd one that is now in index 1.
        },
        async saveProduct() {
            try {
                let store = this.$store.state.data.user.store;

                this.product.store = store;

                this.config.headers["Accept"] = "application/json";
                // console.log(this.config);
                // console.log(this.product);
                await productService.saveProduct(this.saveProductUrl, this.product, this.config);
                await  this.saveProductImage();
                this.alertSuccess("create/update an product successfully", "");
                await this.$emit("fetchData");
                this.$router.push({ name: "Product" });
            } catch (error) {
                this.alertFail("Failed to create/update an product !", error.message);
            }
        },
        async saveProductImage() {
            let formData = new FormData();
            for (var i = 0; i < this.product.product_images.length; i++) {
                let file = this.product.product_images[i];
                formData.append("files", file);
            }
            formData.append("product_name", this.product.product_name);
            this.config.headers["content-Type"] = "multipart/form-data";

            await productService.saveProductImages(this.saveProductImagesUrl, formData, this.config);
            this.config.headers["content-Type"] = undefined;
        },
        async save() {
            if (this.validateProduct()) {
                this.saveProduct();
            }
        },
    },
};
</script>

<style scoped>
h4 {
    font-family: "Roboto", sans-serif;
    color: #484848;
    font-weight: 700;
}
.imagePreviewWrapper {
    background-repeat: no-repeat;
    width: 300px;
    height: 300px;
    display: inline-flex;
    cursor: pointer;
    margin: 0;
    background-size: contain;
    background-position: center center;
}
.image-holder {
    float: left;
    object-fit: fill;
}
</style>
