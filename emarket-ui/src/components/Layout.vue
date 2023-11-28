<template>
    <v-app id="inspire" >
        <v-app-bar :clipped-left="lgAndUp" app color="primary" dark>
            <!--      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />-->

            <v-toolbar-title style="width: 350px">
                <a href="/" class="text-white" style="text-decoration: none"
                    ><v-icon>mdi-store</v-icon>&nbsp;Emarket</a
                >
            </v-toolbar-title>
            <v-text-field
                flat
                solo-inverted
                hide-details
                prepend-inner-icon="mdi-magnify"
                label="Search"
                class="hidden-sm-and-down pl-10 ml-4"
            />
            <v-spacer />
            <v-btn icon title="Account">
                <v-icon>mdi-account-circle</v-icon>
            </v-btn>
            <v-btn icon title="Notification">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-bell</v-icon>
                </v-badge>
            </v-btn>
            <v-btn  href="/cart" icon title="Cart">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-cart</v-icon>
                </v-badge>
            </v-btn>
            <v-btn  href="/wishlist" icon title="Wishlist">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-tag-heart</v-icon>
                </v-badge>
            </v-btn>
            
        </v-app-bar>
        
        <v-main class="pb-0">
            <v-bottom-navigation color="primary" horizontal style="position: relative;">
                
                <v-btn href="/">
                    <span>Home</span>
                </v-btn>
                <v-menu open-on-hover offset-y>
                    <template v-slot:activator="{ props }">
                        <v-btn v-bind="props">
                            Shop
                        </v-btn>
                    </template>
                    <v-list class="mx-auto" max-width="344px">
                        <v-list-item v-for="(item, index) in products" :key="index" href="/shop">
                            <v-list-item-title >{{ item.product_name }}</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>
                <v-btn href="/product">
                    <span>Product</span>
                </v-btn>
                <v-btn href="/blog">
                    <span>Blog</span>
                </v-btn>
            </v-bottom-navigation>
        </v-main>
        <router-view />
        <v-footer class="p-0">
            <v-card flat tile width="100%" class="text-white text-center" color="secondary" dark>
                <v-card-text>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-home"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-email"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-calendar"></v-icon>
                    </v-btn>
                    <v-btn class="mx-4 text-white" icon color="secondary">
                        <v-icon size="24px" icon="mdi-delete"></v-icon>
                    </v-btn>
                </v-card-text>

                <v-card-text class="text-white pt-0">
                    Hello everyone, My name is Bùi Hoài Nam. I am a PTIT student. This is my project, I hope you love it !
                </v-card-text>

                <v-divider></v-divider>

                <v-card-text class="text-white">
                    {{ new Date().getFullYear() }} — <strong>Emarket</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </v-app>
</template>
<script setup>
import { ref } from "vue";
import { useDisplay } from "vuetify";
import { defineProps } from 'vue';

const { products,baseURL,categories,config } = defineProps(['products','baseURL','categories','config']);
const { lgAndUp } = useDisplay();
</script>
<style scoped>
.bottom-navigation-wrapper {
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index: 1000; /* Đảm bảo nằm trên cùng */
}

/* Các kiểu CSS khác tùy thuộc vào yêu cầu của bạn */
</style>
