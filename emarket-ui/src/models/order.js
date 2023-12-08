import Order_detail from "./order_detail";
import Payment_type from "./payment_type";
import User from "./user";

export default class Order {
    constructor(
        id=null,
        note,
        order_date=new Date(),
        shipped_date,
        ship_address,
        ship_fee,
        paid_date,
        order_status,
        user=new User(),
        payment_type=new Payment_type(),
        order_details=[]
    ) {
        Object.assign(this, {
            id,
            note,
            order_date,
            shipped_date,
            ship_address,
            ship_fee,
            paid_date,
            order_status,
            user,
            payment_type,
            order_details,
        });
    }
}
