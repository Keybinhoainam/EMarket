<template>
    <v-app-bar app elevate-on-scroll elevation="3" color="white">
        <v-btn icon="mdi-home" title="Back to Home" v-if="$route.name=='Profile'" color="info" href="/"></v-btn>
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
        <Notification/>
        <AccountMenu/>
    </v-app-bar>
</template>

<script>
import AccountMenu from "@/components/Common/AccountMenu.vue"
import Notification from "@/components/Common/Notification.vue"
export default {
    name: "TopBar",
    emits: ["drawerEvent"],
    components:{AccountMenu,Notification},
    data() {
        return {
            
        };
    },
};
</script>

<style scoped></style>
