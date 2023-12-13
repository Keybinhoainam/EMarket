<script>
import sweetAlert from "@/mixins/sweetAlert";
import defaultAvatar from "@/assets/images/dowload/defaultStoreImage.jpg";
import mixinsFile from "@/mixins/mixinsFile";
import { nextTick } from "vue";
import Cookies from "js-cookie";
import fileService from "@/services/file.service";
import Store from "@/models/store";
import mixinsStore from "@/mixins/mixinsStore";
import User from "@/models/user";
import mixinsValidation from "@/mixins/mixinsValidation";
import * as VeeValidate from "vee-validate";
export default {
    mixins: [mixinsStore, sweetAlert, mixinsFile, mixinsValidation],
    data() {
        return {
            user: new User(),
            baseURL: "http://localhost:8080/api",
            config: this.$store.state.data.config,
            defaultAvatar: defaultAvatar,
            isChangeImage: false,
            store: new Store(),
            isCreateStore: false,
        };
    },
    methods: {
        async resetAvatar() {
            if (this.store.image) {
                let object = {
                    image: this.store.image,
                    imageFile: this.store.imageFile,
                    imageString: this.store.imageString,
                };
                await this.getImage(object);
                await this.applyImages(object);
                this.convert(object);
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
            let object = {
                image: this.store.image,
                imageFile: this.store.imageFile,
                imageString: this.store.imageString,
            };
            await this.applyImages(object);
            this.convert(object);
            // console.log(this.avatar);
        },
        uploadFile() {
            this.$refs.refInput.click();
        },
        goToStore() {
            this.$router.push("/seller/store");
        },
        async createStore() {
            if (this.store.store_name && this.store.description) {
                await this.saveStore();

                if (this.isChangeImage && this.store.imageFile) {
                    let object = {
                        image: this.store.image,
                        imageFile: this.store.imageFile,
                        imageString: this.store.imageString,
                    };
                    await this.applyImages(object);
                    this.convert(object);
                }

                this.isChangeImage = false;
            }
        },
        convert(object) {
            this.store.image = object.image;
            this.store.imageFile = object.imageFile;
            this.store.imageString = object.imageString;
        },
    },
    async created() {
        this.user = await this.$store.state.data.user;
        if (this.$store.state.data.user.store) {
            this.store = this.$store.state.data.user.store;
            // console.log(this.store);
            if (this.store.image) {
                let object = {
                    image: this.store.image,
                    imageFile: this.store.imageFile,
                    imageString: this.store.imageString,
                };
                // console.log(object);
                await this.getImage(object);
                console.log(object.imageFile);
                await this.applyImages(object);
                console.log(object.imageString);
                this.convert(object);
                // console.log(this.store);
            }
        }
    },
};
</script>

<template>
    <v-row>
        <v-col
            cols="12"
            v-if="
                $route.path != '/seller/store' && user.roles[0] && user.roles[0].name != 'CUSTOMER'
            "
        >
            <v-btn class="ml-6 mb-6" color="primary" @click="goToStore">
                <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                <span class="d-none d-sm-block">Go to my store</span>
            </v-btn>
        </v-col>

        <v-col cols="12" v-else>
            <v-card title="Create a new Store">
                <v-cardText class="d-flex">
                    <v-img
                        max-width="600px"
                        height="200px"
                        aspect-ratio="16/9"
                        :src="store.imageString ? store.imageString : defaultAvatar"
                    ></v-img>
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

                <v-cardText>
                    <v-form validate-on="submit lazy" @submit.prevent="createStore()">
                        <v-row class="mt-6">
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="store.store_name"
                                    placeholder="Store Name"
                                    label="Store Name"
                                    :rules="required"
                                />
                            </v-col>

                            <!-- <v-col cols="12" md="6">
                                <v-text-field
                                    type="date"
                                    v-model="user.birthday"
                                    label="Birthday"
                                    placeholder="Date of Birth"
                                />
                            </v-col> -->
                        </v-row>
                        <v-row>
                            <v-col cols="12" md="6">
                                <v-textarea
                                    v-model="store.description"
                                    label="Description"
                                    :rules="required"
                                ></v-textarea>
                            </v-col>
                            <v-col cols="12" class="d-flex flex-wrap gap-4">
                                <v-btn
                                    color="primary"
                                    type="submit"
                                    v-if="$route.path != '/seller/store'"
                                >
                                    <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                                    <span class="d-none d-sm-block">create my store</span>
                                </v-btn>
                                <v-btn color="primary" type="submit" v-else>
                                    <VIcon icon="bx-cloud-upload" class="d-sm-none" />
                                    <span class="d-none d-sm-block">Save</span>
                                </v-btn>
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
