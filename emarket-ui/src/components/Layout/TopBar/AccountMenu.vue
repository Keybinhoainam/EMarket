<template>
    <div>
        <v-menu offset-y>
            <template v-slot:activator="{ props }">
                <span style="cursor: pointer" v-bind="props">
                    <v-chip link>
                        <v-badge
                            dot
                            location="bottom end"
                            :color="user.id ? 'green' : 'white'"
                            overlap
                        >
                            <v-avatar
                                ref="avatar"
                                :image="user.avatarString ? user.avatarString : defaultAvatar"
                            >
                            </v-avatar>
                        </v-badge>
                        <span class="ml-3">{{ user.fullname }}</span>
                    </v-chip>
                </span>
            </template>
            <v-list width="250" class="py-0">
                <v-list-item
                    lines="two"
                    :prepend-avatar="user.avatarString ? user.avatarString : defaultAvatar"
                    :title="user.fullname"
                    :subtitle="user.id ? 'Logged In' : 'Not Login'"
                >
                </v-list-item>
                <v-divider />
                <v-list-item
                    link
                    v-for="(menu, i) in user.id ? menus.loggedIn : menus.notLogIn"
                    :key="i"
                    :prepend-icon="menu.icon"
                    :title="menu.title"
                    @click="menu.click"
                    :href="menu.link"
                >
                </v-list-item>
            </v-list>
        </v-menu>
    </div>
</template>
<script>
import User from "@/models/user";
import Cookies from "js-cookie";
import mixinsAuthen from "@/mixins/mixinsAuthen";
import defaultAvatar from "@/assets/images/defaultAvatar.png";
import mixinsAccount from "@/mixins/mixinsAccount";
import fileService from "@/services/file.service";
import { nextTick } from "vue";
export default {
    mixins: [mixinsAuthen, mixinsAccount],
    data() {
        return {
            user: new User(),
            cart: null,
            defaultAvatar: defaultAvatar,
            menus: {
                loggedIn: [
                    { title: "Profile", icon: "mdi-account", link: "/profile" },
                    { title: "Change Password", icon: "mdi-key", link: "/security" },
                    { title: "My Purchase", icon: "mdi-shopping", link: "/myPurchase"},
                    { title: "My Store", icon: "mdi-store", link: "/myStore"},
                    { title: "Logout", icon: "mdi-logout", click: this.logout },
                ],
                notLogIn: [
                    { title: "Login", icon: "mdi-account", link: "/login" },
                    { title: "Register", icon: "mdi-key", link: "/register" },
                ],
            },
        };
    },
    methods: {
        async logout() {
            this.user = new User();
            await this.mxLogout();
            // this.$router.push('/');
            window.location.href="/";
        },
    },
    async created() {
        this.user = await this.$store.state.data.user;
        if (this.user.avatar) {
            await this.getImage();
            await this.applyImages();
        }
        // await console.log(this.avatarString);
    },
};
</script>
