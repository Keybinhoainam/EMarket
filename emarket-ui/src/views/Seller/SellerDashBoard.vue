<script>
import Order from "@/models/order";
import mixinsOrder from "@/mixins/mixinsOrder";
import { nextTick } from 'vue';

export default {
    components: {},
    mixins: [mixinsOrder],
    data() {
        return {
            orders: [],
            headersTable: [
                {
                    title: "ORDER NO",
                    align: "start",
                    sortable: false,
                    key: "id",
                },
                {
                    title: "ORDER DATE",
                    key: "order_date",
                },
                {
                    title: "SHIP ADDRESS",
                    key: "ship_address",
                },
                {
                    title: "PAYMENT METHOD",
                    key: "payment_type",
                },
                {
                    title: "ORDER STATUS",
                    key: "order_status",
                },
                { title: "Actions", key: "actions", sortable: false, align: "center" },
            ],
            expanded: [],
        };
    },
    async created() {
        // if (this.$route.query.status && this.$route.query.status == 1) {
        //     await this.changeStatus();
        // }
        await this.getOrdersByStore();
        nextTick()
        // console.log(this.orders);
    },
};

// tabs
</script>

<template>
    
</template>
