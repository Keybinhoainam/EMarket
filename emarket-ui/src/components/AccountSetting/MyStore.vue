<script>
import sweetAlert from "@/mixins/sweetAlert";
import defaultAvatar from "@/assets/images/defaultAvatar.png";
import mixinsFile from "@/mixins/mixinsFile";
import { nextTick } from "vue";
import Cookies from "js-cookie";
import fileService from "@/services/file.service";
import Store from "@/models/store";
import mixinsStore from "@/mixins/mixinsStore";
import User from "@/models/user";
import mixinsAccount from "@/mixins/mixinsAccount";
export default {
    mixins: [mixinsStore, sweetAlert, mixinsFile, mixinsAccount],
    data() {
        return {
            user: new User(),
            baseURL: "http://localhost:8080/api",
            config: this.$store.state.data.config,
            defaultAvatar: defaultAvatar,
            isChangeImage: false,
            store:new Store(),
            isCreateStore:false,
        };
    },
    methods: {
        async resetAvatar() {
            if (this.store.image) {
                await this.getImage();
                await this.applyImages();
            }
        },
        resetForm() {
            this.store = new Store();
        },
        async changeImage(file) {
            this.isChangeImage = true;
            let files = file.target.files;
            if (files && files.length) {
                this.store.imageFile = files[0];
            }
            await nextTick();
            await this.applyImages();
            // console.log(this.avatar);
        },
        uploadFile() {
            this.$refs.refInput.click();
        },
        async saveChange() {
            await this.saveStore();
            if (this.isChangeImage && this.store.imageFile) await this.applyImages();
            this.isChangeImage = false;
        },
        goToStore(){

        },
        createStore(){

        }

    },
    async created() {
        this.user = await this.$store.state.data.user;
        // this.store = await this.$store.state.data.user.store;
        // if (this.store.image) {
        //     await this.getImage();
        //     await this.applyImages();
        // }
        
    },
};
</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-card title="Create a new Store">
                <v-cardText class="d-flex">
                    <v-avatar
                        ref="avatar"
                        size="120"
                        class="me-6"
                        :image="store.imageString ? store.imageString : defaultAvatar"
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
                                @input="changeImage"
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
                <v-btn
                class="ml-6"
                    color="primary"
                    @click="isCreateStore=!isCreateStore"
                    v-if="user.roles[0] && user.roles[0].name == 'CUSTOMER'"
                >
                    <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                    <span class="d-none d-sm-block">create my store</span>
                </v-btn>
                <v-btn
                class="ml-6"
                    color="primary"
                    @click="goToStore"
                    v-else
                >
                    <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                    <span class="d-none d-sm-block">Go to my store</span>
                </v-btn>
                <v-cardText v-if="isCreateStore&&user.roles[0] && user.roles[0].name == 'CUSTOMER'">
                    <v-form class="mt-6">
                        <v-row>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="store.store_name"
                                    placeholder="John"
                                    label="Store Name"
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
                        </v-row>
                    </v-form>
                </v-cardText>
                <VDivider />

                <v-cardText>
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
