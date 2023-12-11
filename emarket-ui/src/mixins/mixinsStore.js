
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
                this.store=await storeService.saveStore(saveStoreUrl,this.store, this.config );
                if(this.isChangeImage&&this.store.imageFile) await this.saveImageStore();
                
                this.user.store=this.store;
                this.$store.dispatch("data/changeUser", this.user);
                this.saveAccount(this.user);
                this.alertSuccess("Save Successfully");
            } catch (error) {
                this.alertFail("Fail to save", error.message);
            }
        },
        async saveImageStore() {
            let formData = new FormData();
            formData.append("image", this.store.image);
            formData.append("idStore", this.store.id);
            let saveImageStoreUrl = `${this.baseURL}/store/saveImageStore`;
            this.config.headers["content-Type"] = "multipart/form-data";
            this.store=await accountService.saveAvatarAccount(saveImageStoreUrl, formData, this.config);
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
