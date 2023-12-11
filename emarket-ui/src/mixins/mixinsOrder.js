import Cart from "@/models/cart";
import Order_detail from "@/models/order_detail";
import ZaloPayOrder from "@/models/zaloPayOrder";
import ZaloPayService from "@/services/ZaloPay.service";
import orderService from "@/services/order.service";
import CryptoJS from "crypto-js";
import { format } from "date-fns";
export default {
    data() {
        return {
            urlCheckOut: `${this.baseURL}/order/checkout`,
            cart: new Cart(),
            zaloPayOrder: new ZaloPayOrder(),
        };
    },
    methods: {
        async checkOut() {
            try {
                // console.log(this.order);
                this.addOrderDetails();
                if (this.cart.cart_details.length == 0) {
                    this.alertFail("Cart is empty !");
                    this.$router.push({ name: "Cart" });
                    return;
                }
                console.log("nam");
                // console.log(this.order);
                await orderService.checkOut(this.urlCheckOut, this.order, this.config);
                localStorage.removeItem("cart");
                this.$router.push({ name: "home" });
                this.alertSuccess("CheckOut successfully");
            } catch (error) {
                this.alertFail("fail to checkout");
            }
        },
        addOrderDetails() {
            if (localStorage.getItem("cart")) {
                this.cart = JSON.parse(localStorage.getItem("cart"));
            } else {
                this.cart = new Cart();
            }
            const cart_details = this.cart.cart_details;
            for (let i = 0; i < cart_details.length; i++) {
                let order_detail = new Order_detail();
                this.convertCartDetailToOrderDetail(cart_details[i], order_detail);
                this.order.order_details.push(order_detail);
            }
        },
        convertCartDetailToOrderDetail(cart_detail, order_detail) {
            order_detail.quantity = cart_detail.quantity;
            order_detail.product = cart_detail.product;
            order_detail.unit_price = cart_detail.product.price;
        },
        loadZaloPayOrder() {
            this.zaloPayOrder.app_user = "ZaloPayDemo";
            let currentDate = new Date();
            this.zaloPayOrder.app_time = currentDate.getTime();

            let amount = (this.totalcost + 10) * 24000;
            this.zaloPayOrder.amount = amount;
            this.zaloPayOrder.app_trans_id =
                format(currentDate, "yyMMdd") + "_" + this.zaloPayOrder.app_time;

            let item = [];
            this.zaloPayOrder.item = JSON.stringify(item);
            // this.zaloPayOrder.callback_url = callback_url;
            this.zaloPayOrder.description =
                "ZaloPayDemo - Thanh toán cho đơn hàng #" + this.zaloPayOrder.app_trans_id;

            let hmac_input = `${this.zaloPayOrder.app_id}|${this.zaloPayOrder.app_trans_id}|${this.zaloPayOrder.app_user}|${this.zaloPayOrder.amount}|${this.zaloPayOrder.app_time}|${this.zaloPayOrder.embed_data}|${this.zaloPayOrder.item}`;

            let mac = CryptoJS.HmacSHA256(hmac_input, this.zaloPayOrder.key1);
            this.zaloPayOrder.mac = CryptoJS.enc.Hex.stringify(mac);
        },
        async proceedToPay() {
            this.loadZaloPayOrder();
            console.log(this.zaloPayOrder);
            let response = await ZaloPayService.createOrder(this.zaloPayOrder);
            console.log(response);
            if (response.order_url) {
                window.location.href = await response.order_url;
                
                // let queryResponse=await ZaloPayService.queryOrder({app_id:this.zaloPayOrder.app_id,app_trans_id:this.zaloPayOrder.app_trans_id,mac:this.zaloPayOrder.mac})
                
                // let elapsedTime=0;
                // let totalDuration=15*60*1000;
                // // window.location.href ="http://localhost:8080/"
                // let intervalId=setInterval(() => {
                //     if(elapsedTime>=totalDuration||queryResponse.return_code!=3){
                //         console.log(elapsedTime+": "+queryResponse.return_code);
                //         clearInterval(intervalId);
                //     }
                //     else{
                //         queryResponse=ZaloPayService.queryOrder({app_id:this.zaloPayOrder.app_id,app_trans_id:this.zaloPayOrder.app_trans_id,mac:this.zaloPayOrder.mac})
                //     }
                // }, 60 * 1000);
            }
        },
    },
};
