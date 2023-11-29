<template>
    <div>
        <v-container>
            <div class="row">
                <div class="col-md-5 col-sm-5 col-xs-12">
                    <v-carousel cycle eager>
                        <v-carousel-item v-for="(img, key) in product.product_images" :key="key">
                            <!-- <v-img :src="getImageURL(img.image)" class="fill-image" eager></v-img> -->
                            <img :src="getImageURL(img.image)" class="fill-image" />
                        </v-carousel-item>
                    </v-carousel>
                </div>
                <div class="col-md-7 col-sm-7 col-xs-12">
                    <v-breadcrumbs class="pb-0" :items="breadcrums"></v-breadcrumbs>
                    <div class="pl-6">
                        <p class="display-1 mb-0">{{ product.product_name }}</p>
                        <v-card-actions class="pa-0">
                            <p class="text-h5 font-weight-light pt-3">
                                ${{ product.price }}
                                <del style="" class="text-subtitle-1 font-weight-thin"
                                    >${{ product.standard_cost }}</del
                                >
                            </p>
                            <v-spacer></v-spacer>
                            <v-rating
                                v-if="product.rating"
                                readonly
                                :model-value="product.rating"
                                class=""
                                background-color="warning lighten-3"
                                color="warning"
                                dense
                            ></v-rating>
                            <span class="body-2 font-weight-thin">
                                {{ product.reviews }} REVIEWS</span
                            >
                        </v-card-actions>
                        <p class="text-subtitle-1 font-weight-thin">
                            {{ product.short_description }}
                        </p>
                        <!-- <p class="title">SIZE</p>
                        <v-radio-group v-model="row" row>
                            <v-radio label="XS" value="XS"></v-radio>
                            <v-radio label="S" value="s"></v-radio>
                            <v-radio label="M" value="m"></v-radio>
                            <v-radio label="L" value="l"></v-radio>
                            <v-radio label="XL" value="xl"></v-radio>
                        </v-radio-group> -->
                        <p class="title">ITEMS</p>

                        <div v-if="product.stock > 0"></div>
                        <div v-else>Out of stock</div>
                        <v-text-field
                            variant="outlined"
                            style="width: 100px"
                            v-model="quantity"
                            min="1"
                            :max="product.stock"
                            type="number"
                        ></v-text-field>
                        <v-btn
                            :disabled="product.stock <= 0 || quantity > product.stock"
                            class="white--text"
                            color="primary"
                            tile
                            dense
                            @click="addToCart(this.product, this.quantity)"
                            ><v-icon>mdi-cart</v-icon> ADD TO CART</v-btn
                        >
                        <v-btn class="ml-4" tile @click="addToWishList(this.product)"
                            >ADD TO WISHLIST</v-btn
                        >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-xs-12 col-md-12">
                    <v-tabs v-model="tab">
                        <v-tab value="description">Description</v-tab>
                        <v-tab value="materials">Materials</v-tab>
                        <v-tab value="reviews">REVIEWS</v-tab>
                    </v-tabs>
                    <v-window v-model="tab">
                        <v-window-item value="description">
                            <p class="pt-10 text-subtitle-1 font-weight-thin">
                                {{ product.description }}
                            </p>
                        </v-window-item>

                        <v-window-item value="materials">
                            <p class="pt-10 subtitle-1 font-weight-thin">
                                {{ product.description }}
                            </p>
                        </v-window-item>

                        <v-window-item value="reviews">
                            <v-list lines="three" height="300px">
                                <v-list-item
                                    v-for="(review, i) in product.product_reviews"
                                    :key="i"
                                    :prepend-avatar="null"
                                    :title="review.comment"
                                    color="primary"
                                >
                                    <v-list-item-title> </v-list-item-title>
                                    <v-rating
                                        v-model="review.rating"
                                        class=""
                                        background-color="warning lighten-3"
                                        color="warning"
                                    >
                                    </v-rating>
                                    <v-list-item-subtitle></v-list-item-subtitle>
                                </v-list-item>
                            </v-list>
                        </v-window-item>
                    </v-window>
                    <v-card-text class="pa-0 pt-4" tile outlined>
                        <p class="text-subtitle-1 font-weight-light pt-3 text-center">
                            YOU MIGHT ALSO LIKE
                        </p>
                        <v-divider></v-divider>
                        <div class="row text-center">
                            <div class="col-md-4 col-sm-4 col-xs-12 col-xs-6 text-center">
                                <v-hover v-slot:default="{ isHovering, props }" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img
                                            class="white--text align-end"
                                            height="200px"
                                            :src="require('@/assets/img/home/deal1.jpg')"
                                            cover
                                        >
                                            <v-card-title>Bags & Purses </v-card-title>
                                        </v-img>

                                        <v-card-text class="text--primary text-center">
                                            <div>Upto 60% + Extra 10%</div>
                                            <div>Baggit, Zara, Fossil</div>
                                        </v-card-text>

                                        <div class="text-center">
                                            <v-btn class="ma-2" color="info"> Explore </v-btn>
                                        </div>
                                    </v-card>
                                </v-hover>
                            </div>
                        </div>
                    </v-card-text>
                </div>
            </div>
        </v-container>
    </div>
</template>
<script>
import Product from "@/models/product";
import { useRoute } from "vue-router";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import mixinsCart from "@/mixins/mixinsCart";
import mixinsWishList from "@/mixins/mixinsWishList";
import { nextTick } from "vue";
export default {
    data: () => ({
        product: new Product(),
        noImageUrl: "@/assets/images/noImage.webp",
        quantity: 1,
        rating: 4.5,
        tab: null,
        breadcrums: [],
        item: 5,
        items: [
            {
                avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
                title: "Lorem ipsum dolor?",
                subtitle:
                    "<span class='text--primary'>Ali Connors</span> &mdash; Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Tincidunt arcu non sodales neque sodales ut etiam. Lectus arcu bibendum at varius vel pharetra. Morbi tristique senectus et netus et malesuada.\n" +
                    "\n",
            },
            {
                avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
                title: 'Lorem ipsum dolor <span class="grey--text text--lighten-1">4</span>',
                subtitle:
                    "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
            },
            {
                avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
                title: "Lorem ipsum dolor",
                subtitle:
                    "<span class='text--primary'>Sandra Adams</span> &mdash; Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            },
            {
                avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
                title: "Lorem ipsum dolor",
                subtitle: "",
            },
            {
                avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
                title: "Lorem ipsum dolor",
                subtitle:
                    "<span class='text--primary'>Britta Holt</span> &mdash; Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            },
        ],
    }),
    props: ["baseURL", "products", "categories"],
    mixins: [mixinsProduct, sweetAlert, mixinsCart, mixinsWishList],
    methods: {},
    async created() {
        const route = useRoute();
        if (route.params.id) {
            await this.getProductNoCofig(route.params.id);

            this.breadcrums = [
                {
                    title: "Home",
                    disabled: false,
                    href: "/",
                },
                {
                    title: "Store()",
                    disabled: false,
                    href: "/",
                },
                {
                    title: this.product.category.category_name,
                    disabled: false,
                    href: `/`,
                },
            ];
            await nextTick();
        }
    },
    mounted() {},
};
</script>
<style scoped>
.fill-image {
    width: 100%;
    height: 100%;
    object-fit: fill;
}
</style>
