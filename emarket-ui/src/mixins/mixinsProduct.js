import Product from "@/models/product";
import productService from "@/services/product.service";
import { useRoute } from "vue-router";
export default {
  data() {
    return {
      errors: new Product(),
      isValid: true,
      baseGetImageUrl: `${this.baseURL}/data/file/images/`,
      getProductUrl: `${this.baseURL}/data/product/get/`,
    };
  },
  methods: {
    getImageURL(filename) {
      return `${this.baseGetImageUrl}${filename}`;
    },
    async getProduct() {
      const route = useRoute();
      if (route.params.id) {
        this.isEdit = true;
        try {
          this.product = await productService.getProduct(
            `${this.getProductUrl}${route.params.id}`,
            this.config
          );
          let length = this.product.product_images.length;
          for (let i = 0; i < length; i++) {
            let fileName = this.product.product_images[i].image;
            let src = this.getImageURL(fileName);
            this.product.product_images[i] =
              await productService.getProductImageFile(
                src,
                fileName,
                "image/*"
              );
              console.log(this.product.product_images[i]);
          }
        } catch (error) {
          this.alertFail("Failed to edit an product !", "product not exists");
          this.$router.push({ name: "Product" });
        }
      }
    },
    checkCategoryEmpty() {
      if (this.categories == []) {
        this.alertFail("");
        this.alertWarning(
          "Not have any Category!",
          "Do you add category?",
          "Yes!"
        ).then((result) => {
          if (result.isConfirmed) {
            this.$router.push("/seller/category/add");
          }
        });
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
    validateProduct() {
      this.isValid = true;
      this.errors = new Product();
      this.validateCategoryProduct();
      this.validateProductName();
      this.validateProductPrice();

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
  },
};
