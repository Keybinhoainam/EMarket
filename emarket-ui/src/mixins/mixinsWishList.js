import WishList from "@/models/wishList";
import { nextTick } from "vue";

export default {
    data() {
        return {
            async getWishList() {
                // localStorage.removeItem("wishList")
                if (localStorage.getItem("wishList")) {
                    this.wishList = JSON.parse(localStorage.getItem("wishList"));
                } else {
                    this.wishList = new WishList();
                }
                await nextTick();
            },
            addToWishList(product) {
                this.getWishList();
                console.log(product);
                let tmp = this.wishList.products.find((x) => x.id === product.id);
                if (!tmp) {
                    this.wishList.products.push(product);
                }
                localStorage.setItem("wishList", JSON.stringify(this.wishList));
                this.alertSuccess("Add to wishList Successfully");
            },
            removeWishListItem(product) {
                // console.log(this.wishList);
                this.alertWarning(
                    "Are you sure?",
                    "You won't be able to revert this!",
                    "Yes, delete it!"
                ).then((result) => {
                    if (result.isConfirmed) {
                        try {

                            let tmp = this.wishList.products.find((x) => x.id === product.id);
                            let index = this.wishList.products.indexOf(tmp);
                            console.log(index);
                            this.wishList.products.splice(index, 1);
                            localStorage.setItem("wishList", JSON.stringify(this.wishList));
                            this.getWishList();
                            nextTick();
                            this.alertSuccess("Remove wishList Successfully");
                        } catch (error) {
                            console.log(error.message);
                        }
                    }
                });
            },
        };
    },
    methods: {},
};
