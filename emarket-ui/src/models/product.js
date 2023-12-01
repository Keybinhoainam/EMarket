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
        is_discontinued=false,
        is_featured=true,
        is_new=true,
        product_images=[],
        category=new Category(),
        store=new Store(),
        rating=4.5,
        reviews=20,
        product_reviews=null
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
            is_discontinued,
            is_featured,
            is_new,
            product_images,
            category,
            store,
            rating,
            reviews,
            product_reviews
        });
    }
}
