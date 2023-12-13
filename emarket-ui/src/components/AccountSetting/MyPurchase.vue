<script>
import Order from "@/models/order";

export default {
    components: {},
    data() {
        return {
            orders: [new Order(0), new Order(1), new Order(2)],
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
                    key: "payment_type.name",
                },
                {
                    title: "ORDER STATUS",
                    key: "order_status",
                },
            ],
            expanded: [],
        };
    },
};

// tabs
</script>

<template>
    <v-data-table
        v-model:expanded="expanded"
        :headers="headersTable"
        :items="orders"
        item-value="id"
        show-expand
    >
        <template v-slot:expanded-row="{ item }">
            <v-list>
                <v-list-item
                    v-for="order_detail in item.order_details"
                    :key="order_detail.product"
                    :title="order_detail.product.product_name"
                    :prepend-avatar="
                        order_detail.product.product_images.length > 0 &&
                        order_detail.product.product_images[0].image != null
                            ? getImageURL(order_detail.product.product_images[0].image)
                            : require('@/assets/images/noImage.png')
                    "
                >
                    <template v-slot:append>
                        <p class="text-subtitle-1 font-weight-thin">{{ order_detail.unit_price }}</p>
                    </template>
                    <v-list-item-subtitle>
                        <p class="text-subtitle-1 font-weight-thin">{{ order_detail.product.category.category_name }}</p>
                        <p class="text-subtitle-1 font-weight-thin">{{ order_detail.quantity }}</p>
                    </v-list-item-subtitle>
                </v-list-item>
            </v-list>
        </template>
    </v-data-table>
    <!-- <v-table>
        <thead>
            <tr>
                <th>ORDER NO</th>
                <th>ORDER DATE</th>
                <th>SHIP ADDRESS</th>
                <th>PAYMENT METHOD</th>
                <th>ORDER STATUS</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <v-expansion-panels>
                    <v-expansion-panel v-for="order in orders" :key="order">
                        <v-expansion-panel-title expand-icon="mdi-plus" collapse-icon="mdi-minus">
                            <td>${{ order.id }}</td>
                            <td>${{ order.order_date }}</td>
                            <td>${{ order.ship_address }}</td>
                            <td>{{ order.payment_type.name }}</td>
                            <td>
                                <v-select
                                    label="Order Status"
                                    clearable
                                    :items="[
                                        'California',
                                        'Colorado',
                                        'Florida',
                                        'Georgia',
                                        'Texas',
                                        'Wyoming',
                                    ]"
                                    variant="solo"
                                ></v-select>
                            </td>
                            <td>
                                <v-icon
                                    icon="mdi-pencil-circle"
                                    color="success"
                                    size="x-large"
                                    class="px-7"
                                    title="View Order Items"
                                    @click="editProduct(order)"
                                ></v-icon>
                            </td>
                        </v-expansion-panel-title>
                        <v-expansion-panel-text>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                            commodo consequat.
                        </v-expansion-panel-text>
                    </v-expansion-panel>
                </v-expansion-panels>
            </tr>
        </tbody>
    </v-table> -->
</template>
