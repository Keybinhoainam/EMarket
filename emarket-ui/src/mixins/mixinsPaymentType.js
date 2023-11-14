import paymentTypeService from "@/services/paymentType.service"

export default {
    data() {
        return {
            urlGetPaymentTypes:`${this.baseURL}/data/paymenttype/getall`
        }
    },
    methods: {
        async getPaymentTypes(){
            try {
                // console.log(this.urlGetPaymentTypes);
                this.payment_types= await paymentTypeService.getPaymentTypes(this.urlGetPaymentTypes,this.config);
            } catch (error) {
                this.alertFail(
                    "Failed to get paymentTypes !"
                    // "Something went wrong!"
                );
                // this.$router.push({ name: "home" });
            }
        }
    },
}