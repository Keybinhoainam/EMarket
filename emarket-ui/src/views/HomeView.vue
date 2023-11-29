<template>
    <div>
        <v-carousel cycle hide-delimiters>
            <v-carousel-item cover :src="require('@/assets/img/home/slider4.jpg')">
                <v-row class="fill-height" align="center" justify="center">
                    <div class="display-2 text-white pl-5 pr-5 hidden-sm-only">
                        <strong>Upto 60% + Extra 10%</strong>
                    </div>
                    <br />
                </v-row>
            </v-carousel-item>
            <v-carousel-item cover :src="require('@/assets/img/home/slider2.jpg')">
                <v-row class="fill-height" align="center" justify="center">
                    <div class="display-2 text-white pl-5 pr-5 hidden-sm-only">
                        <strong>Upto 60% + Extra 10%</strong>
                    </div>
                    <br />
                </v-row>
            </v-carousel-item>
            <v-carousel-item cover :src="require('@/assets/img/home/slider3.jpg')">
                <v-row class="fill-height" align="center" justify="center">
                    <div class="display-2 text-white pl-5 pr-5 hidden-sm-only">
                        <strong>Upto 60% + Extra 10%</strong>
                    </div>
                    <br />
                </v-row>
            </v-carousel-item>
            <v-carousel-item cover :src="require('@/assets/img/home/slider1.jpg')">
                <v-row class="fill-height" align="center" justify="center">
                    <div class="display-2 text-white pl-5 pr-5 hidden-sm-only">
                        <strong>Upto 60% + Extra 10%</strong>
                    </div>
                    <br />
                </v-row>
            </v-carousel-item>
        </v-carousel>
        <div class="pl-4 pr-4 row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <v-card>
                    <v-img cover
                        :src="require('@/assets/img/home/slider2.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="400px"
                    >
                        <h1 class="text-center font-size">Top Picks</h1>
                        <div class="text-center">
                            <v-btn href="/shop" class="text-white" variant="outlined">SHOP NOW</v-btn>
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
                    <v-img cover
                        :src="require('@/assets/img/home/slider3.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="400px"
                    >
                        <h1 class="text-center font-size">New Arrivals</h1>
                        <div class="text-center">
                            <v-btn href="/shop" class="text-white" variant="outlined">SHOP NOW</v-btn>
                        </div>
                    </v-img>
                </v-card>
            </div>
        </div>
        <div class="pl-4 pr-4 row">
            <div class="col-md-4 col-sm-4 col-xs-12">
                <v-card variant="outlined">
                    <v-img cover
                        :src="require('@/assets/img/home/deal2.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="300px"
                    >
                        <h1 class="text-center font-size">Lorem & Ipsum</h1>
                        <div class="text-center mt-2" >
                            <v-btn class="text-white caption" href="/shop" text :style="{ backgroundColor: 'transparent' }"
                                >SHOP NOW
                                <v-icon class="text-white caption">mdi-arrow-right</v-icon></v-btn
                            >
                        </div>
                    </v-img>
                </v-card>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <v-card variant="outlined">
                    <v-img cover
                        :src="require('@/assets/img/home/deal3.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="300px"
                    >
                        <h1 class="text-center font-size">Lorem & Ipsum</h1>
                        <div class="text-center mt-2">
                            <v-btn class="text-white caption " href="/shop" text :style="{ backgroundColor: 'transparent' }"
                                >SHOP NOW
                                <v-icon class="text-white caption">mdi-arrow-right</v-icon></v-btn
                            >
                        </div>
                    </v-img>
                </v-card>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <v-card variant="outlined">
                    <v-img cover
                        :src="require('@/assets/img/home/deal4.jpg')"
                        class="text-white align-center"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="300px"
                    >
                        <h1 class="text-center font-size">Lorem & Ipsum</h1>
                        <div class="text-center mt-2">
                            <v-btn class="text-white caption" href="/shop" text :style="{ backgroundColor: 'transparent' }"
                                >SHOP NOW
                                <v-icon class="text-white caption">mdi-arrow-right</v-icon></v-btn
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
                        <v-card-title class="subheading">Deals of the Day</v-card-title>
                        <v-divider></v-divider>
                        <div class="row">
                            <div class="col-12 col-md-3 col-sm-6  text-center">
                                <v-hover v-slot="{ isHovering, props}" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img cover
                                            class="text-white align-end"
                                            height="200px"
                                            :src="require('@/assets/img/home/deal1.jpg')"
                                        >
                                            <v-card-title>Bags & Purses </v-card-title>
                                        </v-img>

                                        <v-card-text class="text--primary text-center">
                                            <div>Upto 60% + Extra 10%</div>
                                            <div>Baggit, Zara, Fossil</div>
                                        </v-card-text>

                                        <div class="text-center">
                                            <v-btn href="/shop" class="ma-2" variant="outlined" color="info">
                                                Explore
                                            </v-btn>
                                        </div>
                                    </v-card>
                                </v-hover>
                            </div>
                            <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                                <v-hover v-slot="{ isHovering, props}" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img cover
                                            class="text-white align-end"
                                            height="200px"
                                            :src="require('@/assets/img/home/deal2.jpg')"
                                        >
                                            <v-card-title>T-Shirt </v-card-title>
                                        </v-img>

                                        <v-card-text class="text--primary text-center">
                                            <div>Upto 50%</div>
                                            <div>Zara, Selected, Celio</div>
                                        </v-card-text>

                                        <div class="text-center">
                                            <v-btn href="/shop" class="ma-2" variant="outlined" color="info">
                                                Explore
                                            </v-btn>
                                        </div>
                                    </v-card>
                                </v-hover>
                            </div>
                            <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                                <v-hover v-slot="{ isHovering, props}" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img cover
                                            class="text-white align-end"
                                            height="200px"
                                            :src="require('@/assets/img/home/deal3.jpg')"
                                        >
                                            <v-card-title>Jeans </v-card-title>
                                        </v-img>

                                        <v-card-text class="text--primary text-center">
                                            <div>Upto 60% + Extra 10%</div>
                                            <div>Jack & Jones, Levis</div>
                                        </v-card-text>

                                        <div class="text-center">
                                            <v-btn href="/shop" class="ma-2" variant="outlined" color="info">
                                                Explore
                                            </v-btn>
                                        </div>
                                    </v-card>
                                </v-hover>
                            </div>
                            <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                                <v-hover v-slot="{ isHovering, props}" open-delay="200">
                                    <v-card :elevation="isHovering ? 16 : 2" v-bind="props">
                                        <v-img cover
                                            class="text-white align-end"
                                            height="200px"
                                            :src="require('@/assets/img/home/deal4.jpg')"
                                        >
                                            <v-card-title>Shoes </v-card-title>
                                        </v-img>

                                        <v-card-text class="text--primary text-center">
                                            <div>Upto 60% + Extra 10%</div>
                                            <div>Nike, Adidas, Puma</div>
                                        </v-card-text>

                                        <div class="text-center">
                                            <v-btn href="/shop" class="ma-2" variant="outlined" color="info">
                                                Explore
                                            </v-btn>
                                        </div>
                                    </v-card>
                                </v-hover>
                            </div>
                        </div>
                    </v-card-text>
                </v-col>
            </v-row>
        </v-container>
        
    </div>
    <!-- <div id="home">

        <div id="start-shopping" class="container">
            <div class="row">
                <div class="col-12 text-left">
                    <h2 class="pt-3">Top Categories</h2>
                </div>
            </div>
            <div class="row">
                <div
                    v-for="category of categories"
                    :key="category"
                    class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex"
                >
                    <CategoryBox :category="category"> </CategoryBox>
                </div>
            </div>
        </div>

        <hr />
        <div class="container">
            <div class="row">
                <div class="col-12 text-left">
                    <h2 class="pt-3">Top Products</h2>
                </div>
            </div>
            <div class="row">
                <div
                    v-for="product of products"
                    :key="product"
                    class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex"
                >
                    <ProductBox :product="product" :baseURL="baseURL"> </ProductBox>
                </div>
            </div>
        </div>
    </div> -->
    
</template>

<script>
import ProductBox from "../components/Product/ProductBox";
import CategoryBox from "../components/Category/CategoryBox";
import Cart from "@/models/cart";
export default {
    name: "Home",
    components: { ProductBox, CategoryBox },
    props: ["baseURL", "products", "categories"],
    data() {
        return {
            category_size: 0,
            product_size: 0,
            items: [
                    { title: 'Click Me' },
                    { title: 'Click Me' },
                    { title: 'Click Me' },
                    { title: 'Click Me 2' },
                ],
                activeBtn: 1,
                colors: [
                    'indigo',
                    'warning',
                    'pink darken-2',
                    'red lighten-1',
                    'deep-purple accent-4',
                ],
                slides: [
                    'First',
                    'Second',
                    'Third',
                    'Fourth',
                    'Fifth',
                ],
        };
    },
    mounted() {
        // this.category_size = this.categories.length;
        // this.category_size = Math.min(6, this.category_size);
        // this.product_size = this.products.length;
        // this.product_size = Math.min(8, this.product_size);
    },
};
</script>

<style>
.v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .5;
    position: absolute;
    width: 100%;
  }

</style>
