import accountService from "@/services/account.service";
import fileService from "@/services/file.service";
import { nextTick } from "vue";
export default {
    data() {
        return {
            avatarString: null,
        };
    },
    methods: {
        checkPassword() {
            let checkPasswordUrl = `${this.baseURL}/account/checkPassword`;
            accountService
                .checkPassword(
                    checkPasswordUrl,
                    { "user": this.user, "currentPassword": this.currentPassword },
                    this.config
                )
                .then(
                    (res) => {},
                    (error) => {
                        this.alertFail("Incorrect Current Password", error.message);
                    }
                );
        },
        async saveAccount(user) {
            // console.log(this.config);
            try {
                await this.saveAvatarAccount(user);
                user.avatarFile = null;

                let saveAccountUrl = `${this.baseURL}/account/saveAccount`;
                // console.log(saveAccountUrl);

                this.user = await accountService.saveAccount(saveAccountUrl, user, this.config);
                // this.user.avatar=userTmp.avatar;
                // this.config.headers["content-Type"] = undefined;
                this.alertSuccess("Save Successfully");
            } catch (error) {
                this.alertFail("Fail to save", error.message);
            }
        },
        async saveAvatarAccount(user) {
            // console.log(typeof user.avatarFile);
            // console.log(user.avatarFile);
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
                this.avatarString = fileReader.result;

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
