<script setup>
import { useRoute } from "vue-router";
import AccountSettingsAccount from "@/components/Account/AccountSettingsAccount2.vue";
import AccountSettingsNotification from "@/components/Account/AccountSettingsNotification.vue";
import AccountSettingsSecurity from "@/components/Account/AccountSettingsSecurity.vue";
import TopBar from "@/components/Layout/TopBar.vue";
import { ref } from "vue";

const route = useRoute();
const activeTab = ref(route.params.tab);

// tabs
const tabs = [
    {
        title: "Account",
        icon: "mdi-account",
        tab: "account",
    },
    {
        title: "Security",
        icon: "mdi-lock-open",
        tab: "security",
    },
    {
        title: "Notifications",
        icon: "mdi-bell",
        tab: "notification",
    },
];
</script>

<template>
    <v-layout>
        <TopBar />
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
                <VWindowItem value="account">
                    <AccountSettingsAccount />
                </VWindowItem>

                <!-- Security -->
                <VWindowItem value="security">
                    <AccountSettingsSecurity />
                </VWindowItem>

                <!-- Notification -->
                <VWindowItem value="notification">
                    <AccountSettingsNotification />
                </VWindowItem>
            </VWindow>
        </v-main>
    </v-layout>
</template>
