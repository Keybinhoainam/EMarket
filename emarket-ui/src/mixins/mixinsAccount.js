import accountService from "@/services/account.service";
import { nextTick } from "vue";
export default {
    data() {
        return {};
    },
    methods: {
        async saveAccount(user) {
            // console.log(this.config);
            try {
                await this.saveAvatarAccount(user);
                user.avatar=null;
                
                let saveAccountUrl = `${this.baseURL}/account/saveAccount`;
                // console.log(saveAccountUrl);
                await accountService.saveAccount(saveAccountUrl, user, this.config);
                this.alertSuccess("Save Successfully");
            } catch (error) {
                this.alertFail("Fail to save", error.message);
            }
        },
        async saveAvatarAccount(user){
            let formData = new FormData();
            formData.append("avatar",user.avatar);
            formData.append("idAccount",user.id);
            let saveAvatarAccountUrl = `${this.baseURL}/account/saveAvatarAccount`;
            this.config.headers["content-Type"] = "multipart/form-data";
            await accountService.saveAvatarAccount(saveAvatarAccountUrl,formData,this.config)
            this.config.headers["content-Type"] = undefined;
        },
        async applyImages() {
            console.log(this.user.avatar);
            await nextTick();
            const fileReader = new FileReader();
            fileReader.onload = () => {
                this.avatar=fileReader.result;
                // console.log(this.avatar);
            };
            // console.log(this.user.avatar);
            await fileReader.readAsDataURL(this.user.avatar);
        },
    },
};
