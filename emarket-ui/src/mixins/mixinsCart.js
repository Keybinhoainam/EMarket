import cartService from "@/services/cart.service"

export default {
    data() {
        return {
            getCartUrl:`${this.baseURL}/customer/cart`,
            saveCartUrl:`${this.baseURL}/customer/cart/save`
        }
    },
    methods: {
        async getCart(){
            try {
                this.cart=await cartService.getCart(this.getCartUrl,this.config)
            } catch (error) {
                this.alertFail(
                    "Failed to load Cart !"
                    // "Something went wrong!"
                );
                this.$router.push({ name: "home" });
            }
            
        },
        async saveCart(){
            try {
                this.cart=await cartService.saveCart(this.saveCartUrl,this.cart,this.config);

            } catch (error) {
                this.alertFail(
                    "Failed to save Cart !"
                    // "Something went wrong!"
                );
                this.$router.push({ name: "home" });
            }
            
        }
    },
}