<template>
    <v-app-bar app elevate-on-scroll elevation="3" color="white">
        <v-app-bar-nav-icon @click="$emit('drawerEvent')"></v-app-bar-nav-icon>
        <v-spacer />
        <v-col lg="6" cols="12">
            <v-form>
                <v-text-field
                    class="p-0 m-0 mt-6"
                    full-width
                    dense
                    append-icon="mdi-magnify"
                    variant="outlined" 
                    rounded
                    placeholder="Search"
                />
            </v-form>
        </v-col>
        <v-spacer />
        <v-menu location="end">
            <template v-slot:activator="{ props }">
                <span class="mx-5 mr-10" style="cursor: pointer" v-bind="props">
                    <v-badge content="3" color="red" overlap>
                        <v-icon>mdi-bell</v-icon>
                    </v-badge>
                </span>
            </template>
            <v-list lines="three" width="250">
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
            </v-list>
        </v-menu>
        <v-menu offset-y>
            <template v-slot:activator="{ props }">
                <span style="cursor: pointer" v-bind="props">
                    <v-chip link>
                        <v-badge dot location="bottom end" color="green" overlap>
                            <v-avatar image="https://randomuser.me/api/portraits/women/81.jpg">
                            </v-avatar>
                        </v-badge>
                        <span class="ml-3">Jane Smith</span>
                    </v-chip>
                </span>
            </template>
            <v-list width="250" class="py-0">
                <v-list-item
                    lines="two"
                    prepend-avatar="https://randomuser.me/api/portraits/women/81.jpg"
                    title="Jane Smith"
                    subtitle="Logged In"
                >
                </v-list-item>
                <v-divider />
                <v-list-item
                    link
                    v-for="(menu, i) in menus"
                    :key="i"
                    :prepend-icon="menu.icon"
                    :title="menu.title"
                >
                </v-list-item>
            </v-list>
        </v-menu>
    </v-app-bar>
</template>

<script>
export default {
    name: "TopBar",
    emits: ["drawerEvent"],
    data() {
        return {
            menus: [
                { title: "Profile", icon: "mdi-account" },
                { title: "Change Password", icon: "mdi-key" },
                { title: "Setting", icon: "mdi-cog" },
                { title: "Logout", icon: "mdi-logout" },
            ],
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

<style scoped></style>
