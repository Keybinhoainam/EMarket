import Order_detail from "./order_detail";
import Payment_type from "./payment_type";
import User from "./user";

export default class Order {
    constructor(
        id = null,
        note,
        order_date = new Date(),
        shipped_date,
        ship_address,
        city="Ha Noi",
        district="Nam Tu Liem",
        ward="Trung Van",
        houseNo="Dai Linh",
        ship_fee = 10,
        paid_date,
        order_status,
        user = new User(),
        payment_type,
        order_details = [],
        amount=0,
    ) {
        Object.assign(this, {
            id,
            note,
            order_date,
            shipped_date,
            ship_address,
            city,
            district,
            ward,
            houseNo,
            ship_fee,
            paid_date,
            order_status,
            user,
            payment_type,
            order_details,
            amount,
        });
    }
}
