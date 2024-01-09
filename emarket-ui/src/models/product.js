import Category from "./category";
import Product_Image from "./product_image";
import Product_review from "./product_review";
import Store from "./store";

export default class Product {
    constructor(
        id=null,
        product_code=null,
        product_name=null,
        description=null,
        short_description=null,
        standard_cost=0,
        price=null,
        stock=null,
        product_status,
        product_images=[],
        category=new Category(),
        store=new Store(),
        rating,
        reviews,
        product_reviews=null,
        quantitySold
    ) {
        Object.assign(this, {
            id,
            product_code,
            product_name,
            description,
            short_description,
            standard_cost,
            price,
            stock,
            product_status,
            product_images,
            category,
            store,
            rating,
            reviews,
            product_reviews,
            quantitySold
        });
    }
}
