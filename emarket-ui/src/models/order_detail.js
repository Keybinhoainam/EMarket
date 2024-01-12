import Order from "./order";
import Product from "./product";

export default class Order_detail {
    constructor(
        id = null,
        quantity = 0,
        unit_price,
        discount_percentage,
        discount_amount,
        order_detail_status,
        date_allocated,
        order = new Order(),
        product = new Product(),
        isReviewed
    ) {
        Object.assign(this, {
            id,
            quantity,
            unit_price,
            discount_percentage,
            discount_amount,
            order_detail_status,
            date_allocated,
            order,
            product,
            isReviewed
        });
    }
}
