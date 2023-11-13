import categoryService from "@/services/category.service";
import { useRoute } from "vue-router";

export default {
    data() {
        return {
            getProductsCategoryUrl: `${this.baseURL}/data/category/product/get/`,
            urlSave: `${this.baseURL}/seller/category/save`,
            urlGet: `${this.baseURL}/data/category/get/`,
        };
    },
    methods: {
        async getProductsCategory(id) {
            if (id) {
                try {
                    this.category = await categoryService.getProductsCategory(
                        `${this.getProductsCategoryUrl}${id}`,
                        this.config
                    );
                } catch (error) {}
            }
        },
        deleteCategory(id) {
            this.alertWarning(
                "Are you sure?",
                "You won't be able to revert this!",
                "Yes, delete it!"
            ).then((result) => {
                if (result.isConfirmed) {
                    categoryService.deleteCategory(`${this.urlDelete}${id}`, this.config).then(
                        () => {
                            this.alertSuccess("Deleted!", "Your category has been deleted.");
                        },
                        (error) => {
                            this.alertFail(
                                `Failed to delete category with id = ${id}!`,
                                error.message
                            );
                        }
                    );
                    this.$emit("fetchData");
                    this.forceUpdate;
                }
            });
        },
        async getCategory(id) {
            try {
                this.category = await categoryService.getCategory(
                    `${this.urlGet}${id}`,
                    this.config
                );
            } catch (error) {
                this.alertFail(
                    "Failed to edit category !",
                    "Category not exists"
                    // "Something went wrong!"
                );
                this.$router.push({ name: "Category" });
            }
        },
        async saveCategory() {
            try {
                console.log(this.category);
                await categoryService.saveCategory(this.urlSave, this.category, this.config);
                this.alertSuccess("Category saved successfully");
                this.$router.push({ name: "Category" });
            } catch (error) {
                this.alertFail(
                    "Failed to create/update an category !",
                    error.message
                    // "Something went wrong!"
                );
            }
        },
        // async delete
    },
};
