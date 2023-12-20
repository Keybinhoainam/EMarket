<template>
    <!-- <Sidebar></Sidebar> -->
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Our Categories</h3>
                <router-link :to="{ name: 'AddCategory' }">
                    <button class="btn" style="float: right">
                        Add Category
                    </button>
                </router-link>
            </div>
        </div>
        <div class="row">
            <table class="table table-striped table-inverse">
                <thead class="thead-inverse">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="category of categories" :key="category.id">
                        <td scope="row">{{ category.id }}</td>
                        <td>{{ category.category_name }}</td>
                        <td>{{ category.description }}</td>
                        <td class="text-center">
                            <router-link
                                :to="{
                                    name: 'EditCategory',
                                    params: { id: category.id },
                                }"
                                class="btn btn-warning mx-2"
                                ><i class="fa fa-edit"></i
                            ></router-link>
                            <button
                                class="btn btn-danger mx-2"
                                @click="deleteCategory(category.id)"
                            >
                                <i class="fa fa-recycle"></i>
                            </button>
                            <!-- <a
                href="@{'/admin/categories/delete/'+${category.categoryId}}"
                class="btn btn-danger"
                ><i class="fa fa-recycle"></i
              ></a> -->
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
import sweetAlert from "@/mixins/sweetAlert";
import mixinsCategory from "@/mixins/mixinsCategory";
import { getCurrentInstance } from "vue";
import categoryService from "@/services/category.service";
import authHeader from "@/services/authHeader";
const axios = require("axios");
// import CategoryBox from "@/components/Category/CategoryBox.vue";
export default {
    name: "Category",
    data() {
        return {
            urlDelete: `${this.baseURL}/seller/category/delete/`,
            categories:[],
        };
    },
    mixins: [sweetAlert,mixinsCategory],
    props: ["baseURL", "config"],
    methods: {
        forcesUpdate() {
            const instance = getCurrentInstance();
            instance.proxy.forceUpdate();
        },
    },
    async created() {
        await this.getAllCategories();
    },
    mounted() {
        // this.$emit("fetchData");
        // this.forceUpdate;
    },
};
</script>
<style scoped></style>
