<script>
import Profile from "@/components/AccountSetting/Profile.vue";
import MyPurchase from "@/components/AccountSetting/MyPurchase.vue";
import Security from "@/components/AccountSetting/Security.vue";
import TopBar from "@/components/Layout/TopBar.vue";
export default {
    props: ["tab"],
    components: {
        Profile,
        MyPurchase,
        Security,
        TopBar,
    },
    data() {
        return {
            activeTab: this.tab,
            tabs: [
                {
                    title: "Profile",
                    icon: "mdi-account",
                    tab: "profile",
                },
                {
                    title: "Security",
                    icon: "mdi-lock-open",
                    tab: "security",
                },
                {
                    title: "My Purchase",
                    icon: "mdi-shopping",
                    tab: "myPurchase",
                },
            ],
        };
    },
    methods: {
        search(textSearch) {
            this.$router.push({ name: "shop" ,params: {textSearch:textSearch} });
        },
    },
};

// tabs
</script>

<template>
    <v-layout>
        <TopBar @search="search"/>
        <v-main>
            <VTabs v-model="activeTab" show-arrows>
                <VTab v-for="item in tabs" :key="item.icon" :value="item.tab">
                    <VIcon size="20" start :icon="item.icon" />
                    {{ item.title }}
                </VTab>
            </VTabs>
            <VDivider />

            <VWindow v-model="activeTab" class="mt-5 disable-tab-transition">
                <!-- Account -->
                <VWindowItem value="profile">
                    <Profile />
                </VWindowItem>

                <!-- Security -->
                <VWindowItem value="security">
                    <Security />
                </VWindowItem>

                <!-- Notification -->
                <VWindowItem value="myPurchase">
                    <MyPurchase />
                </VWindowItem>
            </VWindow>
        </v-main>
    </v-layout>
</template>
