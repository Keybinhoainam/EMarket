import categoryService from "@/services/category.service";
import { useRoute } from "vue-router"

export default{
    data() {
        return {
            getProductsCategoryUrl:`${this.baseURL}/data/category/product/get/`
        }
    },
    methods: {
        async getProductsCategory(id){
            if(id){
                try {
                    this.category=await categoryService.getProductsCategory(`${this.getProductsCategoryUrl}${id}`,this.config)
                } catch (error) {
                    
                }
            }
        }
        // async delete
    },
}