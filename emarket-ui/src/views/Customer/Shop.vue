<template>
    <div>
        <v-container>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-12">
                    <v-card class="px-5">
                        <v-card-title>Filters</v-card-title>
                        <v-divider></v-divider>

                        <v-card-title class="pb-0">Category</v-card-title>
                        <v-container class="pt-0" fluid>
                            <v-checkbox
                                v-model="filters.category"
                                :value="0"
                                label="All"
                                hide-details
                                dense
                            >
                            </v-checkbox>
                            <v-checkbox
                                v-for="c in categories"
                                :key="c.id"
                                v-model="filters.category"
                                :value="c.id"
                                :label="c.category_name"
                                hide-details
                                dense
                            >
                            </v-checkbox>
                        </v-container>

                        <v-divider></v-divider>
                        <v-card-title>Price</v-card-title>
                        <v-range-slider
                            v-model="filters.range"
                            :min="min"
                            :max="max"
                            :step="1"
                            class="align-center"
                        >
                        </v-range-slider>
                        <v-row class="pa-2" dense>
                            <v-col cols="12" sm="5">
                                <v-text-field
                                    v-model="filters.range[0]"
                                    label="Min"
                                    type="number"
                                    variant="outlined"
                                    density="compact"
                                    >$</v-text-field
                                >
                            </v-col>
                            <v-col cols="12" sm="1">
                                <p class="pt-2 text-center">-</p>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-text-field
                                    v-model="filters.range[1]"
                                    label="Max"
                                    type="number"
                                    variant="outlined"
                                    density="compact"
                                    >$</v-text-field
                                >
                            </v-col>
                        </v-row>
                        <v-divider></v-divider>
                        <v-card-title class="pb-0">Customer Rating</v-card-title>

                        <v-container class="pt-0" fluid>
                            <v-checkbox
                                v-for="r in ratings"
                                :key="r"
                                v-model="filters.rating"
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
                            <!-- <small>Showing 1-12 of 200 products</small> -->
                        </v-col>
                        <v-col cols="12" sm="4">
                            <v-select
                                class="pa-0"
                                v-model="filters.select"
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
                            v-for="product in paginatedProducts"
                        >
                            <ProductBox :product="product" :baseURL="baseURL"> </ProductBox>
                        </div>
                    </div>

                    <div class="text-center mt-12">
                        <v-pagination
                            v-model="currentPage"
                            :length="totalPages"
                            rounded="circle"
                        ></v-pagination>
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
        noImageUrl: "@/assets/images/noImage.png",

        options: [
            "Default",
            "Popularity",
            "On Sale",
            "New Arrivals",
            "Price: Low to High",
            "Price: High to Low",
        ],
        min: 0,
        max: 10000,
        shopProducts: null,
        filters: {
            category: 0,
            range: [0, 10000],
            rating: 0,
            select: "Default",
        },
        itemsPerPage: 12,
        currentPage: 1,
    }),
    computed: {
        totalPages() {
            return Math.ceil(this.filteredProducts.length / this.itemsPerPage);
        },
        paginatedProducts() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.filteredProducts.slice(startIndex, endIndex);
        },
        filteredProducts() {
            let filterProducts = this.products.slice();
            if(this.textSearch!="")filterProducts=filterProducts.filter(product => product.product_name.toLowerCase().includes(this.textSearch));
            if (this.filters.category != 0)
                filterProducts = filterProducts.filter(
                    (product) => product.category.id == this.filters.category
                );
            if (this.filters.range != [0, 10000])
                filterProducts = filterProducts.filter(
                    (product) =>
                        product.price >= this.filters.range[0] &&
                        product.price <= this.filters.range[1]
                );
            if (this.filters.rating != 0)
                filterProducts = filterProducts.filter(
                    (product) => product.rating >= this.filters.rating
                );
            if (this.filters.select != "Default") {
                if (this.filters.select == "Popularity")
                    filterProducts = filterProducts.sort((a, b) => b.quantitySold - a.quantitySold);
                if (this.filters.select == "On Sale" || this.filters.select == "New Arrivals")
                    filterProducts = filterProducts.filter(
                        (product) => product.product_status == this.filters.select
                    );
                if (this.filters.select == "Price: Low to High")
                    filterProducts = filterProducts.sort((a, b) => a.price - b.price);
                if (this.filters.select == "Price: High to Low")
                    filterProducts = filterProducts.sort((a, b) => b.price - a.price);
            }
            
            return filterProducts;
        },
    },
    methods: {
        changePage(page) {
            this.currentPage = page;
        },
    },
    async created() {

        // if(textSearch){
        //     await this.findProductsLikeName(textSearch);
        // }
    },
};
</script>
