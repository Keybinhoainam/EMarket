<template>
    <v-app id="inspire">
        <v-layout>
            <Sidebar :drawer="drawer" />
            <TopBar @drawerEvent="drawerEvent" :role="role"/>
            <v-main style="background: #f5f5f540">
                <v-container class="py-8 px-6" fluid>
                    <router-view
                        :baseURL="baseURL"
                        :products="products"
                        :categories="categories"
                        :config="config"
                        @fetchData="fetchData"
                    />
                </v-container>
            </v-main>
        </v-layout>
    </v-app>
</template>
<script>
import { useDisplay } from "vuetify";
import mixinsProduct from "@/mixins/mixinsProduct";
import sweetAlert from "@/mixins/sweetAlert";
import TopBar from "@/components/Layout/TopBar/TopBar.vue";
import Sidebar from "@/components/Layout/Sidebar.vue";
export default {
    setup() {
        const { lgAndUp } = useDisplay();
        return {
            lgAndUp,
        };
    },
    props: ["products", "baseURL", "categories", "config","role"],
    emits:["fetchData"],
    // mixins: [mixinsCart, mixinsProduct, sweetAlert],
    components: { TopBar, Sidebar },
    data() {
        return {
            cartItemsQuantity: 0,
            textSearch: "",
            drawer: false,
        };
    },
    methods: {
        async load() {
        },
        search() {
            this.$router.push({ name: "shop" });
        },
        drawerEvent() {
            this.drawer = !this.drawer;
        },
        fetchData(){
            this.$emit("fetchData");
        }
    },
    async created() {
        this.load();
    },
};
</script>
<style scoped>
.bottom-navigation-wrapper {
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index: 1000;
}
</style>
