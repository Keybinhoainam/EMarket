import Category from "@/models/category";

export default {
  data() {
    return {
      errors: new Category(null,'','','')
    };
  },
  methods: {
    
    validateCategory() {
      let isValid = true;
      this.errors = new Category(null,'','','');
      if (!this.category.category_name) {
        this.errors.category_name = "CategoryName is required";
        isValid = false;
      }

      // if (!this.category.description) {
      //   this.errors.description = "Password is required";
      //   isValid = false;
      // }
      return isValid;
    },
  },
};
