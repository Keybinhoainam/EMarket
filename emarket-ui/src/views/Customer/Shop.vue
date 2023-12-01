<template>
    <div>
        <v-container>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-12">
                    <v-card class="px-5">
                        <v-card-title>Filters</v-card-title>
                        <v-divider></v-divider>
                        <v-list>
                            <v-list-group v-for="category in categories" :key="category.id">
                                <template v-slot:activator="{ props }">
                                    <v-list-item
                                        v-bind="props"
                                        :title="category.category_name"
                                    ></v-list-item>
                                </template>

                                <v-list-item
                                    v-for="product in category.products"
                                    :key="product.id"
                                    :title="product.product_name"
                                    :value="product.id"
                                ></v-list-item>
                            </v-list-group>
                        </v-list>

                        <v-divider></v-divider>
                        <v-card-title>Price</v-card-title>
                        <v-range-slider
                            v-model="range"
                            :min="min"
                            :max="max"
                            :step="1"
                            class="align-center"
                        >
                        </v-range-slider>
                        <v-row class="pa-2" dense>
                            <v-col cols="12" sm="5">
                                <v-text-field
                                    v-model="range[0]"
                                    label="Min"
                                    type="number"
                                    variant="outlined"
                                    density="compact"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="1">
                                <p class="pt-2 text-center">-</p>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-text-field
                                    v-model="range[1]"
                                    label="Max"
                                    type="number"
                                    variant="outlined"
                                    density="compact"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-divider></v-divider>
                        <v-card-title class="pb-0">Customer Rating</v-card-title>

                        <v-container class="pt-0" fluid>
                            <v-checkbox
                                v-for="r in ratings"
                                :key="r"
                                v-model="rating"
                                :value="r.ratingAbove"
                                :label="r.label"
                                hide-details
                                dense
                            >
                                <template v-slot:append>
                                    <v-icon icon="mdi-star"></v-icon>
                                </template>
                            </v-checkbox>
                        </v-container>
                    </v-card>
                </div>
                <div class="col-md-9 col-sm-9 col-xs-12">
                    <v-row dense>
                        <v-col cols="12" sm="8" class="pl-6 pt-6">
                            <small>Showing 1-12 of 200 products</small>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-select
                                class="pa-0"
                                v-model="select"
                                :items="options"
                                style="margin-bottom: -20px"
                                outlined
                                dense
                            ></v-select>
                        </v-col>
                    </v-row>

                    <v-divider></v-divider>

                    <div class="row text-center">
                        <div
                            class="col-md-3 col-sm-6 col-xs-12"
                            :key="product.id"
                            v-for="product in shopProducts"
                        >
                            <ProductBox :product="product" :baseURL="baseURL"> </ProductBox>
                        </div>
                    </div>
                    <div class="text-center mt-12">
                        <v-pagination v-model="page" :length="6"></v-pagination>
                    </div>
                </div>
            </div>
        </v-container>
    </div>
</template>
<style>
.v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: 0.8;
    position: absolute;
    width: 100%;
}
</style>
<script>
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick, shallowRef } from "vue";
import ProductBox from "@/components/Product/ProductBox";
export default {
    props: ["categories", "products", "baseURL", "textSearch"],
    mixins: [mixinsProduct, sweetAlert],
    components: { ProductBox },
    data: () => ({
        range: [0, 10000],
        rating: 0,
        ratings: [
            {
                ratingAbove: 0,
                label: "All ratings",
            },
            {
                ratingAbove: 5,
                label: "5",
            },
            {
                ratingAbove: 4,
                label: "4 & above",
            },
            {
                ratingAbove: 3,
                label: "3 & above",
            },
            {
                ratingAbove: 2,
                label: "2 & above",
            },
            {
                ratingAbove: 1,
                label: "1 & above",
            },
        ],
        noImageUrl: "@/assets/images/noImage.webp",
        select: "Popularity",
        options: ["Default", "Popularity", "Relevance", "Price: Low to High", "Price: High to Low"],
        page: 1,
        min: 0,
        max: 10000,
        shopProducts: null,
    }),
    watch: {
        async textSearch(textSearch) {
            await this.findProductsLikeName(textSearch);
            await nextTick();
        },
    },
    async mounted() {
        await this.findProductsLikeName(this.textSearch);
        // if(textSearch){
        //     await this.findProductsLikeName(textSearch);
        // }
    },
};
</script>
