
import accountService from "@/services/account.service";
import fileService from "@/services/file.service";
import storeService from "@/services/store.service";
import { nextTick } from "vue";
export default {
    data() {
        return {
            
        };
    },
    methods: {
        async saveStore() {
            // console.log(this.config);
            try {
                let saveStoreUrl = `${this.baseURL}/store/saveStore`;
                let tmp=await storeService.saveStore(saveStoreUrl,this.store, this.config );
                this.store.id=tmp.id;
                if(this.isChangeImage&&this.store.imageFile) await this.saveImageStore();
                
                let saveAccountUrl = `${this.baseURL}/account/saveAccount`;
                await accountService.saveAccount(saveAccountUrl, this.user, this.config);
                
                this.alertSuccess("Save Successfully");
            } catch (error) {
                this.alertFail("Fail to save", error.message);
            }
        },
        async saveImageStore() {
            let formData = new FormData();
            formData.append("image", this.store.imageFile);
            formData.append("idStore", this.store.id);
            let saveImageStoreUrl = `${this.baseURL}/store/saveImageStore`;
            this.config.headers["content-Type"] = "multipart/form-data";
            let tmp=await accountService.saveAvatarAccount(saveImageStoreUrl, formData, this.config);
            // console.log(tmp);
            this.store.image=tmp.image
            this.config.headers["content-Type"] = undefined;
        },
        async applyImages() {
            await nextTick();
            const fileReader = new FileReader();
            fileReader.onload = () => {
                this.store.imageString = fileReader.result;

            };
            await fileReader.readAsDataURL(this.store.imageFile);
            
        },
        async getImage() {
            this.store.imageFile = await fileService.getImage(this.store.image, "image/*");
           
        },
    },
};
