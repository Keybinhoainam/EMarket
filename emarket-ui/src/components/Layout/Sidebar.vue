<template>
    <v-navigation-drawer v-model="localDrawer" :rail="rail" permanent>
        <v-card>
            <v-img
                height="140"
                class="pa-4"
                cover
                :src="store.imageString ? store.imageString : defaultAvatar"
                v-if="!rail"
            >
            </v-img>
            <v-card-title class="text-h6"> {{ store.store_name }}</v-card-title>
        </v-card>

        <v-divider v-if="!rail"></v-divider>
        <v-list>
            <v-list-item
                v-for="[icon, text, link] in links"
                :key="icon"
                link
                :prepend-icon="icon"
                :title="text"
                :href="link"
            >
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
import mixinsStore from "@/mixins/mixinsStore";
import Store from "@/models/store";
import defaultAvatar from "@/assets/images/dowload/defaultStoreImage.jpg";
export default {
    name: "Sidebar",
    props: ["drawer"],
    mixins: [mixinsStore],
    data() {
        return {
            localDrawer: true,
            rail: false,
            links: [
                ["mdi-microsoft-windows", "Dashboard"],
                ["mdi-format-list-bulleted-type", "Categories", "/seller/category"],
                ["mdi-clipboard-list-outline", "Products", "/seller/product"],
                ["mdi-card-account-details-outline", "Orders"],
                ["mdi-store", "My Store", "/seller/store"],
                ["mdi-cog", "System Setting"],
            ],
            store: new Store(),
            defaultAvatar:defaultAvatar,
        };
    },
    async created() {
        if (this.$store.state.data.user.store) {
            this.store = this.$store.state.data.user.store;
            if (this.store.image) {
                await this.getImage();
                await this.applyImages();
            }
        }
    },
    watch: {
        drawer(drawer) {
            this.rail = drawer;
        },
    },
};
</script>

<style scoped></style>
