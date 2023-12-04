<script setup>
import avatar1 from "@/assets/img/avatar-1.png";
import User from "@/models/user";
import { ref } from "vue";
const user=new User();
const accountData = {
    avatarImg: avatar1,
    firstName: "john",
    lastName: "Doe",
    email: "johnDoe@example.com",
    org: "ThemeSelection",
    phone: "+1 (917) 543-9876",
    address: "123 Main St, New York, NY 10001",
    state: "New York",
    zip: "10001",
    country: "USA",
    language: "English",
    timezone: "(GMT-11:00) International Date Line West",
    currency: "USD",
};

const refInputEl = ref();
const accountDataLocal = ref(structuredClone(accountData));
const isAccountDeactivated = ref(false);

const resetForm = () => {
    accountDataLocal.value = structuredClone(accountData);
};

const changeAvatar = (file) => {
    const fileReader = new FileReader();
    const { files } = file.target;
    if (files && files.length) {
        fileReader.readAsDataURL(files[0]);
        fileReader.onload = () => {
            if (typeof fileReader.result === "string")
                accountDataLocal.value.avatarImg = fileReader.result;
        };
    }
};

// reset avatar image
const resetAvatar = () => {
    accountDataLocal.value.avatarImg = accountData.avatarImg;
};


</script>

<template>
    <VRow>
        <v-col cols="12">
            <v-card title="Account Details">
                <v-cardText class="d-flex">
                    <VAvatar
                        rounded="lg"
                        size="100"
                        class="me-6"
                        :image="accountDataLocal.avatarImg"
                    />
                    <form class="d-flex flex-column justify-center gap-5">
                        <div class="d-flex flex-wrap gap-2">
                            <VBtn color="primary" @click="refInputEl?.click()">
                                <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                                <span class="d-none d-sm-block">Upload new photo</span>
                            </VBtn>

                            <input
                                ref="refInputEl"
                                type="file"
                                name="file"
                                accept=".jpeg,.png,.jpg,GIF"
                                hidden
                                @input="changeAvatar"
                            />

                            <VBtn type="reset" color="error" variant="tonal" @click="resetAvatar">
                                <span class="d-none d-sm-block">Reset</span>
                                <VIcon icon="bx-refresh" class="d-sm-none" />
                            </VBtn>
                        </div>

                        <p class="text-body-1 mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                    </form>
                </v-cardText>

                <VDivider />

                <v-cardText>
                    <!-- 👉 Form -->
                    <v-form class="mt-6">
                        <v-row>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="user.fullname"
                                    placeholder="John"
                                    label="Full Name"
                                />
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.email"
                                    label="E-mail"
                                    placeholder="johndoe@gmail.com"
                                    type="email"
                                />
                            </v-col>

                            <!-- 👉 Organization -->
                            <v-col cols="12" md="6">
                                <v-text-field
                                    type="date"
                                    v-model="user.birthday"
                                    label="Birthday"
                                    placeholder="Date of Birth"
                                />
                            </v-col>

                            <!-- 👉 Phone -->
                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.phone"
                                    label="Phone Number"
                                    placeholder="+84123456789"
                                />
                            </v-col>

                            <!-- <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="accountDataLocal.address"
                                    label="Address"
                                    placeholder="123 Main St, New York, NY 10001"
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="accountDataLocal.state"
                                    label="State"
                                    placeholder="New York"
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="accountDataLocal.zip"
                                    label="Zip Code"
                                    placeholder="10001"
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <VSelect
                                    v-model="accountDataLocal.country"
                                    label="Country"
                                    :items="['USA', 'Canada', 'UK', 'India', 'Australia']"
                                    placeholder="Select Country"
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <VSelect
                                    v-model="accountDataLocal.language"
                                    label="Language"
                                    placeholder="Select Language"
                                    :items="['English', 'Spanish', 'Arabic', 'Hindi', 'Urdu']"
                                />
                            </v-col>

                        
                            <v-col cols="12" md="6">
                                <VSelect
                                    v-model="accountDataLocal.timezone"
                                    label="Timezone"
                                    placeholder="Select Timezone"
                                    :items="timezones"
                                    :menu-props="{ maxHeight: 200 }"
                                />
                            </v-col>

                            <v-col cols="12" md="6">
                                <VSelect
                                    v-model="accountDataLocal.currency"
                                    label="Currency"
                                    placeholder="Select Currency"
                                    :items="currencies"
                                    :menu-props="{ maxHeight: 200 }"
                                />
                            </v-col> -->

                            <v-col cols="12" class="d-flex flex-wrap gap-4">
                                <VBtn>Save changes</VBtn>

                                <VBtn
                                    color="secondary"
                                    variant="tonal"
                                    type="reset"
                                    @click.prevent="resetForm"
                                >
                                    Reset
                                </VBtn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-cardText>
            </v-card>
        </v-col>

        <v-col cols="12">
            <!-- 👉 Deactivate Account -->
            <v-card title="Deactivate Account">
                <v-cardText>
                    <div>
                        <VCheckbox
                            v-model="isAccountDeactivated"
                            label="I confirm my account deactivation"
                        />
                    </div>

                    <VBtn :disabled="!isAccountDeactivated" color="error" class="mt-3">
                        Deactivate Account
                    </VBtn>
                </v-cardText>
            </v-card>
        </v-col>
    </VRow>
</template>
