import Payment_type from "./payment_type";
import Product from "./product";
import User from "./user";

export default class Product_review {
    constructor(
        id = null,
        rating = 5,
        comment,
        create_at,
        update_at,
        user = new User()
    ) {
        Object.assign(this, {
            id,
            rating,
            comment,
            create_at,
            update_at,
            user,
        });
    }
}
