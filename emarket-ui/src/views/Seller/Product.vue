<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">Our Products</h4>
                <router-link :to="{ name: 'AddProduct' }">
                    <button class="btn">Add a new Product</button>
                </router-link>
            </div>
        </div>
        <div class="row">
            <v-card-title class="d-flex align-center pe-2">
                <v-icon icon="mdi-clipboard-list-outline"></v-icon> &nbsp; Find Product

                <v-spacer></v-spacer>

                <v-text-field
                    v-model="textSearch"
                    prepend-inner-icon="mdi-magnify"
                    density="compact"
                    label="Search"
                    single-line
                    flat
                    hide-details
                    variant="solo-filled"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-select
                    v-model="filters.select"
                    :items="options"
                    density="compact"
                    single-line
                    flat
                    hide-details
                    variant="solo-filled"
                ></v-select>
            </v-card-title>

            <v-table>
                <!-- <template v-slot:default> -->
                <thead>
                    <tr>
                        <th>ITEM</th>
                        <th>PRICE</th>
                        <th>STANDARD COST</th>
                        <th>STOCK</th>
                        <th>QUANTITY SOLD</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in paginatedProducts" :key="product">
                        <td>
                            <v-list>
                                <v-list-item
                                    :title="product.product_name"
                                    :subtitle="product.category.category_name"
                                    :prepend-avatar="
                                        product.product_images.length > 0 &&
                                        product.product_images[0].image != null
                                            ? getImageURL(product.product_images[0].image)
                                            : require('@/assets/images/noImage.png')
                                    "
                                >
                                </v-list-item>
                            </v-list>
                        </td>
                        <td>${{ product.price }}</td>
                        <td>${{ product.standard_cost }}</td>
                        <td>{{ product.stock }}</td>
                        <td>{{ product.quantitySold }}</td>
                        <td>
                            <v-icon
                                icon="mdi-pencil-circle"
                                color="warning"
                                size="x-large"
                                class="px-7"
                                title="Edit Product"
                                @click="editProduct(product)"
                            ></v-icon>
                            <v-icon
                                icon="mdi-delete-forever"
                                color="error"
                                size="x-large"
                                class="px-7"
                                title="Delete Product"
                                @click="deleteProduct(product)"
                            ></v-icon>
                        </td>
                    </tr>
                </tbody>

                <!-- </template> -->
            </v-table>
            <div class="text-center mt-12">
                <v-pagination
                    v-model="currentPage"
                    :length="totalPages"
                    rounded="circle"
                ></v-pagination>
            </div>
        </div>
    </div>
</template>
<script>
import ProductBox from "@/components/Product/ProductBox";
import mixinsFile from "@/mixins/mixinsFile";
import sweetAlert from "@/mixins/sweetAlert";
import { nextTick } from "vue";
import mixinsProduct from "@/mixins/mixinsProduct";
export default {
    name: "Product",
    components: { ProductBox },
    emits: ["fetchData"],
    props: ["baseURL", "config"],
    mixins: [mixinsFile, sweetAlert, mixinsProduct],
    data() {
        return {
            products: [],
            textSearch: "",
            items: [
                {
                    name: "Nebula GTX 3080",
                    image: "1.png",
                    price: 699.99,
                    rating: 5,
                    stock: true,
                },
                {
                    name: "Galaxy RTX 3080",
                    image: "2.png",
                    price: 799.99,
                    rating: 4,
                    stock: false,
                },
                {
                    name: "Orion RX 6800 XT",
                    image: "3.png",
                    price: 649.99,
                    rating: 3,
                    stock: true,
                },
                {
                    name: "Vortex RTX 3090",
                    image: "4.png",
                    price: 1499.99,
                    rating: 4,
                    stock: true,
                },
                {
                    name: "Cosmos GTX 1660 Super",
                    image: "5.png",
                    price: 299.99,
                    rating: 4,
                    stock: false,
                },
            ],
            filters: {
                // category: 0,
                // range: [0, 10000],
                // rating: 0,
                select: "Default",
            },
            options: [
                "Default",
                "Popularity",
                "On Sale",
                "Out of Stock",
                // "Quantity Sold: Low to High",
                // "Quantity Sold: High to Low",
            ],
            itemsPerPage: 12,
            currentPage: 1,
        };
    },
    computed: {
        totalPages() {
            return Math.ceil(this.filteredProducts.length / this.itemsPerPage);
        },
        paginatedProducts() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.filteredProducts.slice(startIndex, endIndex);
        },
        filteredProducts() {
            let filterProducts = this.products.slice();
            if (this.textSearch != "")
                filterProducts = filterProducts.filter((product) =>
                    product.product_name.toLowerCase().includes(this.textSearch)
                );
            // if (this.filters.category != 0)
            //     filterProducts = filterProducts.filter(
            //         (product) => product.category.id == this.filters.category
            //     );
            // if (this.filters.range != [0, 10000])
            //     filterProducts = filterProducts.filter(
            //         (product) =>
            //             product.price >= this.filters.range[0] &&
            //             product.price <= this.filters.range[1]
            //     );
            // if (this.filters.rating != 0)
            //     filterProducts = filterProducts.filter(
            //         (product) => product.rating >= this.filters.rating
            //     );
            if (this.filters.select != "Default") {
                if (this.filters.select == "Popularity")
                    filterProducts = filterProducts.sort((a, b) => b.quantitySold - a.quantitySold);
                if (this.filters.select == "On Sale" || this.filters.select == "New Arrivals")
                    filterProducts = filterProducts.filter(
                        (product) => product.product_status == this.filters.select
                    );
                if (this.filters.select == "Out of Stock")
                    filterProducts = filterProducts.filter((product) => product.stock === 0);
                // if (this.filters.select == "Quantity Sold: Low to High")
                //     filterProducts = filterProducts.sort((a, b) => a.quantitySold - b.quantitySold);
                // if (this.filters.select == "Quantity Sold: High to Low")
                //     filterProducts = filterProducts.sort((a, b) => b.quantitySold - a.quantitySold);
            }

            return filterProducts;
        },
    },
    async created() {
        await this.getAllProductsStore();
    },
};
</script>

<style scoped>
h4 {
    font-family: "Roboto", sans-serif;
    color: #484848;
    font-weight: 700;
}

#add-product {
    float: right;
    font-weight: 500;
}
</style>
