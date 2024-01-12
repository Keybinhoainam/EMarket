import Product from "@/models/product";
import Product_review from "@/models/product_review";
import productService from "@/services/product.service";
import { nextTick } from "vue";
import { useRoute } from "vue-router";
export default {
    data() {
        return {
            errors: new Product(),
            isValid: true,
            baseGetImageUrl: `${this.baseURL}/data/file/images/`,
            getProductUrl: `${this.baseURL}/data/product/get/`,
            getProductsLikeNameUrl: `${this.baseURL}/data/product/getproductslikename`,
            deleteProductUrl: `${this.baseURL}/seller/product/delete/`,
            getAllProductsUrl:`${this.baseURL}/data/product/getallproducts`,
            getAllCategoriesUrl:`${this.baseURL}/data/category/getAllCategories`,
            getAllProductsStoreUrl:`${this.baseURL}/data/product/getAllProductsStore`,
            saveReviewUrl:`${this.baseURL}/product/saveReivew`
            
        };
    },
    methods: {
        async submitReview(orderDetail){
            if(this.review.rating&&this.review.title&&this.review.subtitle){
                this.review.user=this.$store.state.data.user
                this.review.product.id=orderDetail.product.id
                this.review.order_detail.id=orderDetail.id
                // console.log(this.review);
                await productService.saveReivew(this.saveReviewUrl,this.review,this.config),
                this.showReviewDialog = false;
                this.review=new Product_review();
                orderDetail.isReviewed=true

            }
        },
        getImageURL(filename) {
            return `${this.baseGetImageUrl}${filename}`;
        },
        async applyImages() {
            await nextTick();
            for (
                let i = 0;
                i < this.product.product_images.length && i < this.$refs.image.length;
                i++
            ) {
                let reader = new FileReader();
                //#lỗi khi add ảnh mới, this.$refs.image[i].src bị undefine => dữ liệu trên Dom chưa được cập nhật ở hàm uploadImages hoặc removeImage khi vào hàm applyImages
                // console.log(this.$refs.image[i].src);
                reader.onload = (e) => {
                    if (reader.readyState == FileReader.DONE) {
                        this.$refs.image[i].src = reader.result;
                        // console.log(this.$refs.image[i].src);
                    }
                };
                reader.readAsDataURL(this.product.product_images[i]);
                // console.log(reader.readyState);
            }
        },

        async getProduct(id) {
            if (id) {
                try {
                    this.product = await productService.getProduct(
                        `${this.getProductUrl}${id}`,
                        this.config
                    );
                    this.categoryProductId = this.product.category.id;
                    let length = this.product.product_images.length;
                    for (let i = 0; i < length; i++) {
                        let fileName = this.product.product_images[i].image;
                        let src = this.getImageURL(fileName);
                        this.product.product_images[i] = await productService.getProductImageFile(
                            src,
                            fileName,
                            "image/*"
                        );
                    }
                } catch (error) {
                    this.alertFail("Failed to edit an product !", "product not exists");
                    this.$router.push({ name: "Product" });
                }
            }
        },
        async getProductNoCofig(id) {
            if (id) {
                try {
                    this.product = await productService.getProduct(
                        `${this.getProductUrl}${id}`,
                        this.config
                    );
                    console.log(this.product);
                    this.indexShowImage=0;
                } catch (error) {
                    this.alertFail("Failed to edit an product !", "product not exists");
                    this.$router.push({ name: "home" });
                }
            }
        },
        checkCategoryEmpty() {
            if (this.categories == []) {
                this.alertFail("");
                this.alertWarning("Not have any Category!", "Do you add category?", "Yes!").then(
                    (result) => {
                        if (result.isConfirmed) {
                            this.$router.push("/admin/category/add");
                        }
                    }
                );
            }
        },
        validateCategoryProduct() {
            if (this.categoryProductId==-1) {
                this.errors.category.id = "Category is required";
                this.isValid = false;
            }
        },
        validateProductName() {
            if (!this.product.product_name) {
                this.errors.product_name = "ProductName is required";
                this.isValid = false;
            }
        },
        validateProductPrice() {
            if (!this.product.price) {
                this.errors.price = "Product Price is required";
                this.isValid = false;
            }
        },
        validateStockProduct() {
            if (this.product.stock<0) {
                this.errors.stock = "Stock is required";
                this.isValid = false;
            }
        },
        validateProduct() {
            this.isValid = true;
            this.errors = new Product();
            this.validateCategoryProduct();
            this.validateProductName();
            this.validateProductPrice();
            this.validateStockProduct();
            // const regexImageURL=/(http(s?):)([/|.|\w|\s|-])*\.(?:jpg|gif|png)/;
            // if(!regexImageURL.test(this.product.product_images.url)){
            //     this.errors.product_images.url="fill image url with .jpg, .gif, .png"
            //     isValid=false;
            // }
            // if(!this.product.product_images.url){
            //     this.errors.product_images.url="imageURL is required";
            //     isValid = false;
            // }

            // if (!this.category.description) {
            //   this.errors.description = "Password is required";
            //   isValid = false;
            // }
            return this.isValid;
        },
        async findProductsLikeName(name) {
            await productService
                .getProductsLikeName(
                    this.getProductsLikeNameUrl,
                    { params: { name: name } },
                    this.config
                )
                .then(
                    (res) => {
                        this.shopProducts = { ...res.data };
                    },
                    (error) => {
                        console.log(error.message);
                        this.alertFail("Failed To Load Products Like Name", error.message);
                    }
                );
        },
        editProduct(product) {
            this.$router.push({ name: "EditProduct", params: { id: product.id } });
        },
        async deleteProduct(product) {
            this.alertWarning(
                "Are you sure?",
                "You won't be able to revert this!",
                "Yes, delete it!"
            ).then((result) => {
                if(result.isConfirmed){
                    try {
                        productService.deleteProduct(`${this.deleteProductUrl}${product.id}`, this.config).then(
                            (res)=>{
                                this.$emit("fetchData");
                                nextTick();
                                this.alertSuccess("Remove CartItem Successfully");
                            }
                        );
                        // this.$store.dispatch('data/fetchProductsData',{url:this.getAllProductsUrl,config:this.config});
                        
                    } catch (error) {
                        this.alertFail("Remove CartItem Fail",error.message)
                    }
                }
            });
        },
        async getAllProductsStore(){
            this.products=await productService.getAllProductsStore(this.getAllProductsStoreUrl,this.$store.state.data.user.store,this.config);

        }
    },
};
