import Cart from "@/models/cart";
import Order_detail from "@/models/order_detail";
import orderService from "@/services/order.service"

export default{
    data() {
        return {
            urlCheckOut:`${this.baseURL}/order/checkout`,
            cart:new Cart()
        }
    },
    methods: {
        async checkOut(){
            try {
                // console.log(this.order);
                this.addOrderDetails();
                // console.log(this.order);
                await orderService.checkOut(this.urlCheckOut,this.order, this.config);
                localStorage.removeItem("cart");
                this.alertSuccess("CheckOut successfully")
            } catch (error) {
                this.alertFail("fail to checkout")
            }
        },
        addOrderDetails(){
            if (localStorage.getItem("cart")) {
                this.cart = JSON.parse(localStorage.getItem("cart"));
            } else {
                this.cart = new Cart();
            }
            const cart_details=this.cart.cart_details;
            for(let i=0;i<cart_details.length;i++){
                let order_detail=new Order_detail();
                this.convertCartDetailToOrderDetail(cart_details[i],order_detail);
                this.order.order_details.push(order_detail);
            }
        },
        convertCartDetailToOrderDetail(cart_detail, order_detail){
            order_detail.quantity=cart_detail.quantity;
            order_detail.product=cart_detail.product;
            order_detail.unit_price=cart_detail.product.price;

        }

    },
}