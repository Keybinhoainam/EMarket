import Order_detail from "./order_detail";
import Payment_type from "./payment_type";
import Product from "./product";
import User from "./user";

export default class Product_review {
    constructor(id = null, rating = 5, title, subtitle, create_at, update_at, user = new User(), order_detail=new Order_detail(),product=new Product()) {
        Object.assign(this, {
            id,
            rating,
            title,
            subtitle,
            create_at,
            update_at,
            user,
            order_detail,
            product
        });
    }
}
