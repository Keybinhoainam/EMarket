import WishList from "@/models/wishList";
import { nextTick } from "vue";

export default {
    data() {
        return {
            wishList: new WishList(),
            
        };
    },
    methods: {
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
                        
                        let tmp = this.wishList.products.find((x) => x.id == product.id);
                        // console.log(tmp);
                        let index = this.wishList.products.indexOf(tmp);
                        // console.log(index);
                        this.wishList.products.splice(index, 1);
                        localStorage.setItem("wishList", JSON.stringify(this.wishList));
                        this.getWishList();
                        this.$emit('changeCart');
                        nextTick();
                        this.alertSuccess("Remove wishList Successfully");
                    } catch (error) {
                        console.log(error.message);
                        this.alertFail("Fail to remove WishList Item",error.message)
                    }
                }
            });
        },
    },
    created() {
        this.getWishList()
    },
};
