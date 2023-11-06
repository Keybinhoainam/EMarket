<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 v-if="!isEdit" class="pt-3">Add Category</h3>
                <h3 v-if="isEdit" class="pt-3">Edit Category</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form>
                    <div class="form-group">
                        <label>Name</label>
                        <input
                            type="text"
                            class="form-control"
                            v-model="category.category_name"
                            @blur="validateCategory()"
                            v-bind:class="{
                                'is-invalid': errors.category_name,
                            }"
                        />
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.category_name }}
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Description</label>

                        <textarea
                            type="text"
                            class="form-control"
                            v-model="category.description"
                            @blur="validateCategory()"
                            v-bind:class="{ 'is-invalid': errors.description }"
                        ></textarea>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.description }}
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" @click="save">
                        Submit
                    </button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</template>
<script>
import validateCategory from "@/mixins/validateCategory";
import Category from "@/models/category";
import { useRoute } from "vue-router";
import CategoryService from "@/services/category.service";
import sweetAlert from "@/mixins/sweetAlert";
import mixinsCategory from '@/mixins/mixinsCategory';
export default {
    data() {
        return {
            category: new Category(),
            isEdit: false,
            
        };
    },
    mixins: [validateCategory, sweetAlert,mixinsCategory],
    props: ["baseURL", "products", "categories", "config"],
    async created() {
        const route = useRoute();
        if (route.params.id) {
            this.isEdit = true;
            await this.getCategory(route.params.id);
        }
    },
    methods: {
        async save() {
            // console.log(this.validateCategory());
            if (this.validateCategory()) {
                this.saveCategory();
            }
        },
    },
};
</script>
<style scoped></style>
