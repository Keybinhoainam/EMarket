<template>
    <v-navigation-drawer v-model="localDrawer" :rail="rail" permanent>
        <v-img
            height="140"
            class="pa-4"
            cover
            src="https://preview.pixlr.com/images/800wm/1439/2/1439104804.jpg"
            v-if="!rail"
        >
            <div class="text-center">
                <v-avatar class="mb-4" color="grey darken-1" size="64">
                    <v-img
                        aspect-ratio="30"
                        src="https://yt3.ggpht.com/esazPAO03T0f0vKdByJvkDy6MSwjyG5f-c_2S2CJapszQ3KPQyZarpoqvgv0Us0atUbILytj=s88-c-k-c0x00ffffff-no-rj"
                    />
                </v-avatar>
                <h2 class="white--text">Web Burden</h2>
            </div>
        </v-img>

        <v-divider v-if="!rail"></v-divider>
        <v-list>
            <v-list-item
                v-for="[icon, text,link] in links"
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
export default {
    name: "Sidebar",
    props: ["drawer"],
    data() {
        return {
            localDrawer: true,
            rail: false,
            links: [
                ["mdi-microsoft-windows", "Dashboard"],
                ["mdi-account", "Profile"],
                ["mdi-format-list-bulleted-type", "Categories","/seller/category"],
                ["mdi-clipboard-list-outline", "Products","/seller/product"],
                ["mdi-card-account-details-outline", "Orders"],
                ["mdi-cog", "System Setting"],
            ],
        };
    },
    watch: {
        drawer(drawer) {
            this.rail = drawer;
        },
    },
};
</script>

<style scoped></style>
