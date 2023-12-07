<script>
import { ref } from "vue";
import validateAccount from "@/mixins/validateAccount";
import User from "@/models/user";
import mixinsAccount from "@/mixins/mixinsAccount";
import sweetAlert from "@/mixins/sweetAlert";

export default {
    mixins: [validateAccount, mixinsAccount, sweetAlert],
    data() {
        return {
            user: new User(),
            baseURL:this.$store.state.data.baseURL,
            config:this.$store.state.data.config,
            
            isCurrentPasswordVisible: false,
            isNewPasswordVisible: false,
            isConfirmPasswordVisible: false,
            passwordRequirements: [
                "Minimum 8 characters long - the more, the better",
                "At least one lowercase character",
                "At least one number, symbol, or whitespace character",
            ],
            serverKeys: [
                {
                    name: "Server Key 1",
                    key: "23eaf7f0-f4f7-495e-8b86-fad3261282ac",
                    createdOn: "28 Apr 2021, 18:20 GTM+4:10",
                    permission: "Full Access",
                },
                {
                    name: "Server Key 2",
                    key: "bb98e571-a2e2-4de8-90a9-2e231b5e99",
                    createdOn: "12 Feb 2021, 10:30 GTM+2:30",
                    permission: "Read Only",
                },
                {
                    name: "Server Key 3",
                    key: "2e915e59-3105-47f2-8838-6e46bf83b711",
                    createdOn: "28 Dec 2020, 12:21 GTM+4:10",
                    permission: "Full Access",
                },
            ],
        };
    },
    methods: {
        saveChanges() {
            if (this.validateAccountSettingSecurity()) {
                this.checkCurrentPassword();
            } else {
                this.alertFail("Incorrect password confirmation");
            }
        },
    },
    created() {
        this.user.username = this.$store.state.data.user.username;
    },
};

// const recentDevicesHeaders = [
//     {
//         title: "BROWSER",
//         key: "browser",
//     },
//     {
//         title: "DEVICE",
//         key: "device",
//     },
//     {
//         title: "LOCATION",
//         key: "location",
//     },
//     {
//         title: "RECENT ACTIVITY",
//         key: "recentActivity",
//     },
// ];

// const recentDevices = [
//     {
//         browser: "Chrome on Windows",
//         device: "HP Spectre 360",
//         location: "New York, NY",
//         recentActivity: "28 Apr 2022, 18:20",
//         deviceIcon: {
//             icon: "bxl-windows",
//             color: "primary",
//         },
//     },
//     {
//         browser: "Chrome on iPhone",
//         device: "iPhone 12x",
//         location: "Los Angeles, CA",
//         recentActivity: "20 Apr 2022, 10:20",
//         deviceIcon: {
//             icon: "bx-mobile",
//             color: "error",
//         },
//     },
//     {
//         browser: "Chrome on Android",
//         device: "Oneplus 9 Pro",
//         location: "San Francisco, CA",
//         recentActivity: "16 Apr 2022, 04:20",
//         deviceIcon: {
//             icon: "bxl-android",
//             color: "success",
//         },
//     },
//     {
//         browser: "Chrome on MacOS",
//         device: "Apple iMac",
//         location: "New York, NY",
//         recentActivity: "28 Apr 2022, 18:20",
//         deviceIcon: {
//             icon: "bxl-apple",
//             color: "secondary",
//         },
//     },
//     {
//         browser: "Chrome on Windows",
//         device: "HP Spectre 360",
//         location: "Los Angeles, CA",
//         recentActivity: "20 Apr 2022, 10:20",
//         deviceIcon: {
//             icon: "bxl-windows",
//             color: "primary",
//         },
//     },
//     {
//         browser: "Chrome on Android",
//         device: "Oneplus 9 Pro",
//         location: "San Francisco, CA",
//         recentActivity: "16 Apr 2022, 04:20",
//         deviceIcon: {
//             icon: "bxl-android",
//             color: "success",
//         },
//     },
// ];
</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-card title="Change Password">
                <v-form>
                    <v-card-text>
                        <v-row>
                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.currentPassword"
                                    :type="isCurrentPasswordVisible ? 'text' : 'password'"
                                    :append-inner-icon="
                                        isCurrentPasswordVisible ? 'bx-hide' : 'bx-show'
                                    "
                                    label="Current Password"
                                    placeholder="············"
                                />
                                <!-- @click:append-inner="isCurrentPasswordVisible = !isCurrentPasswordVisible" -->
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.password"
                                    :type="isNewPasswordVisible ? 'text' : 'password'"
                                    :append-inner-icon="
                                        isNewPasswordVisible ? 'bx-hide' : 'bx-show'
                                    "
                                    label="New Password"
                                    placeholder="············"
                                    @click:append-inner="
                                        isNewPasswordVisible = !isNewPasswordVisible
                                    "
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.confirmPassword"
                                    :type="isConfirmPasswordVisible ? 'text' : 'password'"
                                    :append-inner-icon="
                                        isConfirmPasswordVisible ? 'bx-hide' : 'bx-show'
                                    "
                                    label="Confirm New Password"
                                    placeholder="············"
                                    @click:append-inner="
                                        isConfirmPasswordVisible = !isConfirmPasswordVisible
                                    "
                                />
                            </v-col>
                        </v-row>
                    </v-card-text>
                    <v-card-text>
                        <p class="text-base font-weight-medium mt-2">Password Requirements:</p>

                        <ul class="d-flex flex-column gap-y-3">
                            <li v-for="item in passwordRequirements" :key="item" class="d-flex">
                                <div>
                                    <v-icon size="7" icon="bxs-circle" class="me-3" />
                                </div>
                                <span class="font-weight-medium">{{ item }}</span>
                            </li>
                        </ul>
                    </v-card-text>
                    <v-card-text class="d-flex flex-wrap gap-4">
                        <v-btn @click="saveChanges">Save changes</v-btn>

                        <v-btn type="reset" color="secondary" variant="tonal"> Reset </v-btn>
                    </v-card-text>
                </v-form>
            </v-card>
        </v-col>

        <v-col cols="12">
            <v-card title="Two-steps verification">
                <v-card-text>
                    <p class="font-weight-semibold">
                        Two factor authentication is not enabled yet.
                    </p>
                    <p>
                        Two-factor authentication adds an additional layer of security to your
                        account by requiring more than just a password to log in.
                        <a href="javascript:void(0)" class="text-decoration-none">Learn more.</a>
                    </p>

                    <v-btn> Enable 2FA </v-btn>
                </v-card-text>
            </v-card>
        </v-col>

        <v-col cols="12">
            <v-card title="Create an API key">
                <v-row>
                    <v-col cols="12" md="5" order-md="0" order="1">
                        <v-card-text>
                            <v-form @submit.prevent="() => {}">
                                <v-row>
                                    <v-col cols="12">
                                        <VSelect
                                            label="Choose the API key type you want to create"
                                            placeholder="Select API key type"
                                            :items="[
                                                'Full Control',
                                                'Modify',
                                                'Read & Execute',
                                                'List Folder Contents',
                                                'Read Only',
                                                'Read & Write',
                                            ]"
                                        />
                                    </v-col>
                                    <v-col cols="12">
                                        <v-text-field
                                            label="Name the API key"
                                            placeholder="Name the API key"
                                        />
                                    </v-col>
                                    <v-col cols="12">
                                        <v-btn type="submit" block> Create Key </v-btn>
                                    </v-col>
                                </v-row>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
            </v-card>
        </v-col>

        <v-col cols="12">
            <v-card title="API Key List &amp; Access">
                <v-card-text>
                    An API key is a simple encrypted string that identifies an application without
                    any principal. They are useful for accessing public data anonymously, and are
                    used to associate API requests with your project for quota and billing.
                </v-card-text>
                <v-card-text class="d-flex flex-column gap-y-4">
                    <div
                        v-for="serverKey in serverKeys"
                        :key="serverKey.key"
                        class="bg-var-theme-background pa-4"
                    >
                        <div class="d-flex align-center flex-wrap mb-3">
                            <h6 class="text-h6 mb-0 me-3">
                                {{ serverKey.name }}
                            </h6>
                            <VChip label color="primary" size="small">
                                {{ serverKey.permission }}
                            </VChip>
                        </div>
                        <p class="text-base font-weight-medium">
                            <span class="me-3">{{ serverKey.key }}</span>
                            <v-icon :size="18" icon="bx-copy" class="cursor-pointer" />
                        </p>
                        <span>Created on {{ serverKey.createdOn }}</span>
                    </div>
                </v-card-text>
            </v-card>
        </v-col>

        <!-- <v-col cols="12">
            <v-card title="Recent Devices">
                <v-data-table
                    :headers="recentDevicesHeaders"
                    :items="recentDevices"
                    class="text-no-wrap rounded-0 text-sm"
                >
                    <template v-slot:item="{ item }">
                        <div class="d-flex">
                            <v-icon
                                start
                                :icon="item.raw.deviceIcon.icon"
                                :color="item.raw.deviceIcon.color"
                            />
                            <span class="text-high-emphasis text-base">
                                {{ item.raw.browser }}
                            </span>
                        </div>
                    </template>

                    <template #bottom />
                </v-data-table>
            </v-card>
        </v-col> -->
        <!-- !SECTION -->
    </v-row>
</template>
