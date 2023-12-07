import accountService from "@/services/account.service";
import fileService from "@/services/file.service";
import { nextTick } from "vue";
export default {
    data() {
        return {
            
        };
    },
    methods: {
        async checkCurrentPassword() {
            let checkPasswordUrl = `${this.baseURL}/account/checkCurrentPassword`;
            // console.log(formData);
            console.log(this.user);
            await accountService.checkCurrentPassword(checkPasswordUrl, this.user, this.config).then(
                (res) => {
                    this.alertSuccess("Change Password Successfully");
                },
                (error) => {
                    this.alertFail("Incorrect Current Password", error.message);
                }
            );
        },
        async saveAccount(user) {
            // console.log(this.config);
            try {
                // console.log(this.$store.state.data.user.avatar);
                // console.log(this.user.avatar);
                if(this.isChangeAvatar) await this.saveAvatarAccount(user);

                const avatarFile=user.avatarFile;
                let saveAccountUrl = `${this.baseURL}/account/saveAccount`;
                this.user = await accountService.saveAccount(saveAccountUrl, user, this.config);
                this.user.avatarFile=avatarFile;
                this.alertSuccess("Save Successfully");
            } catch (error) {
                this.alertFail("Fail to save", error.message);
            }
        },
        async saveAvatarAccount(user) {
            // console.log(typeof user.avatarFile);
            // console.log(user.avatarFile);
            console.log("saveAvatarAccount");
            let formData = new FormData();
            formData.append("avatar", user.avatarFile);
            // formData.append("avatar",user.avatarFile,user.avatar);
            formData.append("idAccount", user.id);
            // console.log(formData.get("avatar"));
            let saveAvatarAccountUrl = `${this.baseURL}/account/saveAvatarAccount`;
            this.config.headers["content-Type"] = "multipart/form-data";
            await accountService.saveAvatarAccount(saveAvatarAccountUrl, formData, this.config);
            this.config.headers["content-Type"] = undefined;
        },
        async applyImages() {
            // console.log(this.user.avatar);
            await nextTick();
            const fileReader = new FileReader();
            fileReader.onload = () => {
                // this.user.avatarFile=fileReader.result;
                this.user.avatarString = fileReader.result;

                // console.log(this.avatar);
            };
            // await console.log(this.user.avatarFile);
            await fileReader.readAsDataURL(this.user.avatarFile);
        },
        async getImage() {
            // await console.log(this.user.avatarFile);
            this.user.avatarFile = await fileService.getImage(this.user.avatar, "image/*");
            // console.log(this.user);
        },
    },
};
