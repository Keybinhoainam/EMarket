import Product from "@/models/product";
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
        };
    },
    methods: {
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
                            this.$router.push("/seller/category/add");
                        }
                    }
                );
            }
        },
        validateCategoryProduct() {
            if (!this.product.category.id) {
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
            if (!this.product.stock) {
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
                        productService.deleteProduct(`${this.deleteProductUrl}${product.id}`, this.config);
                        this.$emit("fetchData");
                        nextTick();
                        this.alertSuccess("Remove CartItem Successfully");
                    } catch (error) {
                        this.alertFail("Remove CartItem Fail",error.message)
                    }
                }
            });
        },
    },
};
