<script>
import sweetAlert from "@/mixins/sweetAlert";
import defaultAvatar from "@/assets/images/defaultAvatar.png";
import Order from "@/models/order";
import mixinsValidation from "@/mixins/mixinsValidation";
import mixinsOrder from "@/mixins/mixinsOrder";
export default {
    mixins: [sweetAlert, mixinsValidation, mixinsOrder],
    data() {
        return {
            order: new Order(),
            baseURL: "http://localhost:8080/api",
            config: this.$store.state.data.config,
            defaultAvatar: defaultAvatar,
        };
    },
    methods: {
        resetForm() {
            this.order = new Order();
        },
        checkToPay(){
          if(this.order.city&&this.order.district&&this.order.ward&&this.order.houseNo){
            this.proceedToPay()
          }
        }
    },
    async created() {
    },
};
</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-card title="SHIPPING INFORMATION">
                <v-cardText>
                    <v-form class="mt-6" @submit.prevent>
                        <v-row>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="order.city"
                                    placeholder="City"
                                    label="City"
                                    :rules="required"
                                />
                            </v-col>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="order.district"
                                    placeholder="District"
                                    label="District"
                                    :rules="required"
                                />
                            </v-col>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="order.ward"
                                    placeholder="Ward"
                                    label="Ward"
                                    :rules="required"
                                />
                            </v-col>
                            <v-col md="6" cols="12">
                                <v-text-field
                                    v-model="order.houseNo"
                                    placeholder="Street Name, Building, House No."
                                    label="Street Name, Building, House No."
                                    :rules="required"
                                />
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="order.note" label="Note" placeholder="" />
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-select
                                    label="Payment Type"
                                    v-model="order.payment_type"
                                    :rules="required"
                                    :items="[
                                        'Cash On Delivery(COD)',
                                        'Pay Now',
                                    ]"
                                ></v-select>
                            </v-col>

                            <v-col cols="12" class="d-flex flex-wrap gap-4">
                                <v-btn @click="checkToPay()" type="submit">Proceed To Pay</v-btn>

                                <v-btn
                                    color="secondary"
                                    variant="tonal"
                                    type="reset"
                                    @click.prevent="resetForm"
                                >
                                    Reset
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-cardText>
            </v-card>
        </v-col>
    </v-row>
</template>
