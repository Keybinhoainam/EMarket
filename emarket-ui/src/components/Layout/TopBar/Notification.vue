<template>
    <v-menu>
        <template v-slot:activator="{ props }">
            <v-btn icon v-bind="props" title="Notification">
                <v-badge v-if="false" content="3" color="red" overlap>
                    <v-icon>mdi-bell</v-icon>
                </v-badge>
                <v-icon v-else>mdi-bell</v-icon>
            </v-btn>
        </template>
        <v-list lines="three" width="auto" class="d-flex justify-center">
            <v-list-item
                title="Functionality in progress"
                subtitle="Coming soon ..."
                class="text-h4"
            >
                <template v-slot:prepend>
                    <v-icon icon="mdi-alert" color="red"></v-icon>
                </template>
            </v-list-item>
        </v-list>
        <!-- <v-list lines="three" width="250">
            <v-list-item
                v-for="(item, index) in items"
                :key="index"
                :title="item.title"
                :prepend-avatar="item.avatar"
            >
                <template v-slot:subtitle="{ subtitle }">
                    <div v-html="item.subtitle" v-bind="subtitle"></div>
                </template>
            </v-list-item>
        </v-list> -->
    </v-menu>
</template>
<script>
import User from "@/models/user";
import Cookies from "js-cookie";

export default {
    data() {
        return {
            items: [
                {
                    avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
                    title: "Brunch this weekend?",
                    subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
                },
                {
                    avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
                    title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
                    subtitle: `<span class="text--primary">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.`,
                },
                {
                    avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
                    title: "Oui oui",
                    subtitle:
                        '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?',
                },
                {
                    avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
                    title: "Birthday gift",
                    subtitle:
                        '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
                },
                {
                    avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
                    title: "Recipe to try",
                    subtitle:
                        '<span class="text--primary">Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
                },
            ],
        };
    },
};
</script>
