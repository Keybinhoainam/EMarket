<template>
    <div>
        <v-carousel cycle hide-delimiters>
            <v-carousel-item
                v-for="slide in slides"
                :key="slide"
                cover
                :src="slide.image"
                @click="$router.push('/shop')"
            >
                <v-row class="fill-height" align="center" justify="center">
                    <!-- <div class="display-2 text-white pl-5 pr-5 hidden-sm-only">
                        <strong>Upto 60% + Extra 10%</strong>
                    </div>
                    <br /> -->
                </v-row>
            </v-carousel-item>
        </v-carousel>
        <div class="pl-4 pr-4 row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <v-card>
                    <v-img
                        
                        :src="require('@/assets/images/dowload/backGround/onsale.png')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="400px"
                    >
                        <h1 class="text-center font-size">On Sale</h1>
                        <div class="text-center">
                            <v-btn
                                @click="showFlashSaleProducts()"
                                class="text-white"
                                variant="outlined"
                                >SHOP NOW</v-btn
                            >
                        </div>

                        <!--            <v-expand-transition>-->
                        <!--              <div-->
                        <!--                v-if="hover"-->
                        <!--                class="d-flex transition-fast-in-fast-out orange darken-2 v-card&#45;&#45;reveal display-3 white&#45;&#45;text"-->
                        <!--                style="height: 100%;"-->
                        <!--              >-->

                        <!--                <h3>Top Picks</h3><br/>-->
                        <!--                <h3>sdfs</h3>-->
                        <!--              </div>-->
                        <!--            </v-expand-transition>-->
                    </v-img>
                </v-card>
                <!--        </v-hover>-->
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <v-card>
                    <v-img
                        cover
                        :src="require('@/assets/img/home/slider1.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="400px"
                    >
                        <h1 class="text-center font-size">New Arrivals</h1>
                        <div class="text-center">
                            <v-btn @click="showNewProducts()" class="text-white" variant="outlined"
                                >SHOP NOW</v-btn
                            >
                        </div>
                    </v-img>
                </v-card>
            </div>
        </div>
        <v-container>
            <v-row no-gutters>
                <v-col :cols="12">
                    <v-card-text class="" tile variant="outlined">
                        <v-card-title class="text-h4">Categories</v-card-title>
                        <v-divider></v-divider>
                        <div class="row">
                            <div
                                class="col-12 col-md-3 col-sm-6 text-center"
                                v-for="category in categories"
                                :key="category.id"
                            >
                                <v-hover v-slot="{ isHovering, props }" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img
                                            cover
                                            :src="
                                                category.image != null
                                                    ? fileService(category.image)
                                                    : require('@/assets/images/noImage.png')
                                            "
                                            class="text-white align-center"
                                            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                            height="150px"
                                            width="1500x"
                                        >
                                            <h1 class="text-center font-size">
                                                {{ category.category_name }}
                                            </h1>
                                            <div class="text-center mt-2">
                                                <v-btn
                                                    class="text-white caption"
                                                    @click="showProductsCategory(category.id)"
                                                    text
                                                    :style="{ backgroundColor: 'transparent' }"
                                                    >SHOP NOW
                                                    <v-icon class="text-white caption"
                                                        >mdi-arrow-right</v-icon
                                                    ></v-btn
                                                >
                                            </div>
                                        </v-img>
                                    </v-card>
                                </v-hover>
                            </div>
                        </div>
                    </v-card-text>
                </v-col>
            </v-row>
        </v-container>
        <v-container>
            <v-row no-gutters>
                <v-col :cols="12">
                    <v-card-text class="" tile variant="outlined">
                        <v-card-title class="text-h4">Products</v-card-title>
                        <v-divider></v-divider>
                        <div class="row">
                            <div
                                class="col-12 col-md-3 col-sm-6 text-center mb-10"
                                v-for="product in products"
                                :key="product.id"
                            >
                                <v-hover v-slot="{ isHovering, props }" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <ProductBox :product="product" :baseURL="baseURL">
                                        </ProductBox>
                                    </v-card>
                                </v-hover>
                            </div>
                        </div>
                    </v-card-text>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import ProductBox from "@/components/Product/ProductBox";
import CategoryBox from "@/components/Category/CategoryBox";
export default {
    name: "Home",
    components: { ProductBox, CategoryBox },
    props: ["baseURL", "products", "categories"],
    data() {
        return {
            slides: [
                { image: require("@/assets/images/dowload/backGround/1.png") },
                { image: require("@/assets/img/home/slider4.jpg") },
                { image: require("@/assets/img/home/slider3.jpg") },
                { image: require("@/assets/img/home/slider1.jpg") },
            ],
        };
    },
    methods: {
        showProductsCategory(id) {
            this.$router.push({ path: "/shop", query: { findBy: "category", category: id } });
        },
        showNewProducts() {
            this.$router.push("/shop", (query = { findBy: "newArrivals" }));
        },
        showFlashSaleProducts() {
            this.$router.push({ path: "/shop", query: { findBy: "flashSale" } });
        },
    },
};
</script>

<style>
.v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: 0.5;
    position: absolute;
    width: 100%;
}
</style>
