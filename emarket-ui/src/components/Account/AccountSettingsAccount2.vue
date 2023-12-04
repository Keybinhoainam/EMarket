<script>
import User from "@/models/user";
import { ref } from 'vue';
export default {
    data() {
        return {
            user: new User(),

        };
    },
    methods: {
        resetAvatar(){
            this.user.image=(new User()).avatar;
        },
        resetForm(){
            this.user=new User();
        },
        changeAvatar(file){
            const fileReader = new FileReader();
            const { files } = file.target;
            if (files && files.length) {
                fileReader.readAsDataURL(files[0]);
                fileReader.onload = () => {
                    if (typeof fileReader.result === "string")
                    this.user.avatar = fileReader.result;
                };
            }
        },
        uploadFile(){
            this.$refs.refInput.click();
        }
    },
};
</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-card title="Account Details">
                <v-cardText class="d-flex">
                    <VAvatar
                        rounded="lg"
                        size="100"
                        class="me-6"
                        :image="user.avatar"
                    />
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

                            <v-col cols="12" class="d-flex flex-wrap gap-4">
                                <v-btn>Save changes</v-btn>

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

        <!-- <v-col cols="12">
            <v-card title="Deactivate Account">
                <v-cardText>
                    <div>
                        <VCheckbox
                            v-model="isAccountDeactivated"
                            label="I confirm my account deactivation"
                        />
                    </div>

                    <v-btn :disabled="!isAccountDeactivated" color="error" class="mt-3">
                        Deactivate Account
                    </v-btn>
                </v-cardText>
            </v-card>
        </v-col> -->
    </v-row>
</template>
