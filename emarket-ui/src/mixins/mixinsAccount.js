import accountService from "@/services/account.service";

export default {
    data() {
        return {};
    },
    methods: {
        async saveAccount(user) {
            // console.log(this.config);
            try {
                let saveAccountUrl = `${this.baseURL}/account/saveAccount`;
                // console.log(saveAccountUrl);
                await accountService.saveAccount(saveAccountUrl, user, this.config);
                this.alertSuccess("Save Successfully")
            } catch (error) {
                this.alertFail("Fail to save",error.message)
            }
        },
        // getImageURL(filename) {
        //     return `${this.baseGetImageUrl}${filename}`;
        // },
        // async applyImages() {
        //     await nextTick();
        //     for (
        //         let i = 0;
        //         i < this.product.product_images.length && i < this.$refs.image.length;
        //         i++
        //     ) {
        //         let reader = new FileReader();
        //         //#lỗi khi add ảnh mới, this.$refs.image[i].src bị undefine => dữ liệu trên Dom chưa được cập nhật ở hàm uploadImages hoặc removeImage khi vào hàm applyImages
        //         // console.log(this.$refs.image[i].src);
        //         reader.onload = (e) => {
        //             if (reader.readyState == FileReader.DONE) {
        //                 this.$refs.image[i].src = reader.result;
        //                 // console.log(this.$refs.image[i].src);
        //             }
        //         };
        //         reader.readAsDataURL(this.product.product_images[i]);
        //         // console.log(reader.readyState);
        //     }
        // },
    },
};
