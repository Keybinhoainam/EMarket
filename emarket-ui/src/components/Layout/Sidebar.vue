<template>
    <v-navigation-drawer v-model="localDrawer" :rail="rail" permanent>
        <v-card class="mx-auto" max-width="434" rounded="0" v-if="this.role == 'admin'">
            <v-img height="100%" cover src="@/assets/images/server-room.jpg">
                <v-avatar color="grey" size="150" cover>
                    <v-img cover :src="user.avatarString ? user.avatarString : defaultAvatar"></v-img>
                </v-avatar>
                <v-list-item
                    class="text-white"
                    :title="user.fullname"
                    subtitle="Administrator"
                ></v-list-item>
            </v-img>
        </v-card>
        <v-card v-else>
            <v-img
                height="140"
                class="my-4"
                cover
                :src="store.imageString ? store.imageString : defaultAvatarStore"
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
import defaultAvatarStore from "@/assets/images/dowload/defaultStoreImage.jpg";
import defaultAvatar from "@/assets/images/defaultAvatar.png";
import mixinsAccount from "@/mixins/mixinsAccount";
export default {
    name: "Sidebar",
    props: ["drawer"],
    mixins: [mixinsStore,mixinsAccount],
    props: ["role"],
    data() {
        return {
            localDrawer: true,
            rail: false,
            links: [],
            store: new Store(),
            defaultAvatarStore: defaultAvatarStore,
            defaultAvatar: defaultAvatar,
            user:this.$store.state.data.user
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
        this.user = await this.$store.state.data.user;
        if (this.user.avatar) {
            await this.getImage();
            await this.applyImages();
        }
    },
    mounted() {
        if (this.role == "seller") {
            this.links = [
                ["mdi mdi-monitor-dashboard", "Dashboard"],
                ["mdi-clipboard-list-outline", "Products", "/seller/product"],
                ["mdi mdi-clipboard-check-outline", "Orders", "/seller/orders"],
                ["mdi-store", "My Store", "/seller/store"],
                ["mdi-card-account-details-outline", "Profile", "/seller/profile"],
            ];
        } else {
            this.links = [
                ["mdi mdi-monitor-dashboard", "Dashboard"],
                ["mdi-format-list-bulleted-type", "Categories", "/admin/category"],
                ["mdi-card-account-details-outline", "Profile", "/admin/profile"],
                ["mdi-cog", "System Setting"],
            ];
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
