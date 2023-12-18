<script>
import User from "@/models/user";
import { ref } from "vue";
import mixinsAccount from "@/mixins/mixinsAccount";
import sweetAlert from "@/mixins/sweetAlert";
import defaultAvatar from "@/assets/images/defaultAvatar.png";
import mixinsFile from "@/mixins/mixinsFile";
import { nextTick } from "vue";
import Cookies from "js-cookie";
import fileService from "@/services/file.service";
import Store from "@/models/store";
import mixinsValidation from "@/mixins/mixinsValidation";
export default {
    mixins: [mixinsAccount, sweetAlert, mixinsFile, mixinsValidation],
    data() {
        return {
            user: new User(),
            baseURL: "http://localhost:8080/api",
            config: this.$store.state.data.config,
            defaultAvatar: defaultAvatar,
            isChangeAvatar: false,
            store: new Store(),
            isCreateStore: false,
        };
    },
    methods: {
        async resetAvatar() {
            if (this.user.avatar) {
                await this.getImage();
                await this.applyImages();
            }
        },
        resetForm() {
            this.user = new User();
        },
        async changeAvatar(file) {
            this.isChangeAvatar = true;
            let files = file.target.files;
            if (files && files.length) {
                this.user.avatarFile = files[0];
                // this.user.avatar = files[0].name;
            }
            await nextTick();
            await this.applyImages();
            // console.log(this.avatar);
        },
        uploadFile() {
            this.$refs.refInput.click();
        },
        async saveChange() {
            await this.saveAccount(this.user);
            this.$store.dispatch("data/changeUser", this.user);
            if (this.user.avatar) {
                await this.getImage();
                await this.applyImages();
            }
            this.isChangeAvatar = false;
        },
        goToStore() {},
        createStore() {},
    },
    async created() {
        this.user = await this.$store.state.data.user;
        if (this.user.avatar) {
            await this.getImage();
            await this.applyImages();
        }
        // console.log(this.user.roles);
        // await this.user.avatar == defaultAvatar ? (this.avatar = defaultAvatar) : this.applyImages();
    },
};
</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-card title="Account Details">
                <v-cardText class="d-flex">
                    <v-avatar
                        ref="avatar"
                        size="120"
                        class="me-6"
                        :image="user.avatarString ? user.avatarString : defaultAvatar"
                    >
                    </v-avatar>
                    <form class="d-flex flex-column justify-center gap-5">
                        <div class="d-flex flex-wrap gap-2">
                            <v-btn color="primary" @click="uploadFile">
                                <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                                <span class="d-none d-sm-block">Upload new photo</span>
                            </v-btn>

                            <input
                                ref="refInput"
                                type="file"
                                accept=".jpeg,.png,.jpg,GIF"
                                hidden
                                @input="changeAvatar"
                            />

                            <v-btn type="reset" color="error" variant="tonal" @click="resetAvatar">
                                <span class="d-none d-sm-block">Reset</span>
                                <VIcon icon="bx-refresh" class="d-sm-none" />
                            </v-btn>
                        </div>

                        <p class="text-body-1 mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                    </form>
                </v-cardText>

                <VDivider />

                <v-cardText>
                    <v-form class="mt-6">
                        <v-row>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="user.fullname"
                                    :rules="required"
                                    placeholder="John"
                                    label="Full Name"
                                />
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field
                                    v-model="user.email"
                                    :rules="required"
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
                                    :rules="required"
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
                                    :rules="required"
                                />
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-select
                                    label="Payment Type"
                                    v-model="user.gender"
                                    :rules="required"
                                    :items="['Male', 'Female', 'Other']"
                                ></v-select>
                            </v-col>
                            <v-col cols="12" class="d-flex flex-wrap gap-4">
                                <v-btn @click="saveChange()">Save changes</v-btn>

                                <v-btn
                                    color="secondary"
                                    variant="tonal"
                                    type="reset"
                                    @click.prevent="resetForm"
                                >
                                    Reset
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-cardText>
            </v-card>
        </v-col>
    </v-row>
</template>
