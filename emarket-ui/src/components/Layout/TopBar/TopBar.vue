<template>
    <div >
        <v-app-bar :clipped-left="lgAndUp" app color="primary" dark v-if="role=='customer'" >
            <!--      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />-->

            <v-toolbar-title style="width: 350px">
                <a href="/" class="text-white" style="text-decoration: none"
                    ><v-icon>mdi-store</v-icon>&nbsp;Emarket</a
                >
            </v-toolbar-title>

            <v-spacer />
            <v-col lg="6" cols="12">
                <v-text-field
                    class="p-0 m-0 mt-6"
                    dense
                    append-icon="mdi-magnify"
                    variant="outlined"
                    rounded
                    placeholder="Search"
                    v-model="textSearch"
                    @keyup.enter="search()"
                />
            </v-col>
            <!-- <v-text-field
                flat
                solo-inverted
                hide-details
                variant="outlined"
                rounded
                prepend-inner-icon="mdi-magnify"
                label="Search"
                class="hidden-sm-and-down pl-10 ml-4"
                
            /> -->
            <v-spacer />

            <!-- <v-btn icon title="Notification">
                <v-badge content="2" value="2" color="green" overlap>
                    <v-icon>mdi-bell</v-icon>
                </v-badge>
            </v-btn> -->
            <Notification />
            <v-btn href="/cart" icon title="Cart">
                <v-badge v-if="cart&&cart.cart_details&&cart.cart_details.length>0" :content="cart.cart_details.length" color="green" overlap>
                    <v-icon>mdi-cart</v-icon>
                </v-badge>
                <v-icon v-else>mdi-cart</v-icon>
            </v-btn>
            <v-btn href="/wishlist" icon title="Wishlist">
                <v-badge v-if="wishList&&wishList.products&&wishList.products.length>0" :content="wishList.products.length" color="green" overlap>
                    <v-icon>mdi-tag-heart</v-icon>
                </v-badge>
                <v-icon v-else>mdi-tag-heart</v-icon>
            </v-btn>
            <AccountMenu></AccountMenu>
        </v-app-bar>
        <v-app-bar app elevate-on-scroll elevation="3" color="white" v-else>
            <v-btn
                icon="mdi-home"
                title="Back to Home"
                v-if="$route.name == 'Profile'"
                color="info"
                href="/"
            ></v-btn>
            <v-app-bar-nav-icon @click="$emit('drawerEvent')" v-else></v-app-bar-nav-icon>
            <v-spacer />
            <v-col lg="6" cols="12">
                <v-form>
                    <v-text-field
                        class="p-0 m-0 mt-6"
                        dense
                        append-icon="mdi-magnify"
                        variant="outlined"
                        rounded
                        placeholder="Search"
                    />
                </v-form>
            </v-col>
            <v-spacer />
            <Notification />
            <AccountMenu />
        </v-app-bar>
    </div>
</template>

<script>
import AccountMenu from "@/components/Layout/TopBar/AccountMenu.vue";
import Notification from "@/components/Layout/TopBar/Notification.vue";
import Cart from "@/models/cart";
import WishList from "@/models/wishList";
import { useDisplay } from 'vuetify/lib/framework.mjs';
export default {
    name: "TopBar",
    setup() {
        const { lgAndUp } = useDisplay();
        return {
            lgAndUp,
        };
    },
    props:["role"],
    emits: ["drawerEvent","search"],
    components: { AccountMenu, Notification },
    data() {
        return {
            cartItemsQuantity: 0,
            textSearch: "",
            cart:null,
            wishList:null,
        };
        
    },
    methods: {
        search() {
            this.$emit("search",this.textSearch);
        },
    },
    created(){
        this.cart=this.$store.state.data.cart;
        this.wishList=this.$store.state.data.wishList;
    }
};
</script>

<style scoped></style>
