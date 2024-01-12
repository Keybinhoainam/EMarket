import Cart from "@/models/cart";
import Order from "@/models/order";
import Order_detail from "@/models/order_detail";
import ZaloPayOrder from "@/models/zaloPayOrder";
import ZaloPayService from "@/services/ZaloPay.service";
import orderService from "@/services/order.service";
import CryptoJS from "crypto-js";
import { format } from "date-fns";
export default {
    data() {
        return {
            urlCheckOut: `http://localhost:8080/api/order/checkout`,
            saveOrderUrl: `http://localhost:8080/api/order/saveOrder`,
            changeStatusUrl: "http://localhost:8080/api/order/changeStatus",
            getOrdersByUserUrl: `http://localhost:8080/api/order/getOrdersByUser`,
            getOrdersByStoreUrl: `http://localhost:8080/api/order/getOrdersByStore`,
            cart: new Cart(),
            zaloPayOrder: new ZaloPayOrder(),
        };
    },
    methods: {
        saveStatus(){
            console.log(this.order);
            orderService.saveOrder(
                this.saveOrderUrl,
                this.order,
                this.$store.state.data.config
            );
        },
        async getOrder() {
            
            let url = `${this.baseURL}/order/getOrder/${this.$route.params.id}`;
            this.order = await orderService.getOrder(url, this.config);
            console.log("getOrder");
            console.log(this.order);
        },
        async checkOut() {
            try {
                // console.log(this.order);
                this.addOrderDetails();
                if (this.cart.cart_details.length == 0) {
                    this.alertFail("Cart is empty !");
                    this.$router.push({ name: "Cart" });
                    return;
                }
                // console.log(this.order);
                await orderService.checkOut(this.urlCheckOut, this.order, this.config);
                this.$store.commit("data/changeCart", new Cart());
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
            this.order.amount = 0;
            for (let i = 0; i < cart_details.length; i++) {
                let order_detail = new Order_detail();
                this.convertCartDetailToOrderDetail(cart_details[i], order_detail);
                this.order.order_details.push(order_detail);
                this.order.amount = this.order.amount + order_detail.unit_price;
            }
            if (this.order.amount < 300) this.order.amount = this.order.amount + 10; //phí ship
        },
        convertCartDetailToOrderDetail(cart_detail, order_detail) {
            order_detail.quantity = cart_detail.quantity;
            order_detail.product = cart_detail.product;
            order_detail.unit_price = cart_detail.product.price;
        },
        async loadZaloPayOrder() {
            this.zaloPayOrder.app_user = "ZaloPayDemo";
            let currentDate = new Date();
            this.zaloPayOrder.app_time = await currentDate.getTime();

            this.zaloPayOrder.amount = this.order.amount * 24000;
            // this.zaloPayOrder.app_trans_id = this.order.id
            //     ? this.order.id
            //     : format(currentDate, "yyMMdd") + "_" + this.zaloPayOrder.app_time;
            let user = this.$store.state.data.user;
            this.zaloPayOrder.app_trans_id =
                format(currentDate, "yyMMdd") + "_" + this.zaloPayOrder.app_time + user.id;

            let item = [];
            this.zaloPayOrder.item = JSON.stringify(item);
            // this.zaloPayOrder.callback_url = callback_url;
            this.zaloPayOrder.description =
                "ZaloPayDemo - Thanh toán cho đơn hàng #" + this.zaloPayOrder.app_trans_id;

            // this.zaloPayOrder.embed_data=`{\"redirecturl\":\"http://localhost:8080/myPurchase\"}`;
            // console.log(this.zaloPayOrder.embed_data);
            let hmac_input = `${this.zaloPayOrder.app_id}|${this.zaloPayOrder.app_trans_id}|${this.zaloPayOrder.app_user}|${this.zaloPayOrder.amount}|${this.zaloPayOrder.app_time}|${this.zaloPayOrder.embed_data}|${this.zaloPayOrder.item}`;

            let mac = CryptoJS.HmacSHA256(hmac_input, this.zaloPayOrder.key1);
            this.zaloPayOrder.mac = CryptoJS.enc.Hex.stringify(mac);
            // console.log(this.zaloPayOrder);
        },
        async saveOrder() {
            this.order.id = this.zaloPayOrder.app_trans_id;
            this.order.ship_address = `${this.order.houseNo},${this.order.ward},${this.order.district},${this.order.city},`;
            this.order.order_status =
                this.order.payment_type == "Online Payment Methods" ? "Unpaid" : "Order Placed";
            this.order.user = this.$store.state.data.user;
            if (this.order.amount >= 300) this.order.ship_fee = 0;
            await orderService.saveOrder(
                this.saveOrderUrl,
                this.order,
                this.$store.state.data.config
            );
            this.$store.commit("data/changeCart", new Cart());
        },
        async proceedToPay() {
            await this.addOrderDetails();
            await this.loadZaloPayOrder();

            if (this.order.payment_type == "Online Payment Methods") {
                let order_url = await ZaloPayService.createOrder(this.zaloPayOrder);

                this.order.paymentLink = order_url;
                await this.saveOrder();
                // console.log(order_url);
                window.location.href = order_url;

                // console.log(this.zaloPayOrder);
                // let response = await ZaloPayService.createOrder(this.zaloPayOrder);
                // console.log(response);

                // if (response.order_url) {
                //     // window.location.href = await response.order_url;
                // }
            } else {
                await this.saveOrder();
                this.$router.push("/myPurchase");
                
            }
        },
        async changeStatus(status) {
            this.alertWarning(
                "Are you sure?",
                "You won't be able to revert this!",
                "Yes, cancel Order"
            ).then((result) => {
                if (result.isConfirmed) {
                    try {
                        this.order.order_status = status;
                        orderService.changeStatus(
                            this.changeStatusUrl,
                            this.order,
                            this.$store.state.data.config
                        );
                    } catch (error) {
                        this.alertFail("Cancel Order Fail", error.message);
                    }
                }
            });
        },
        async getOrdersByUser() {
            this.orders = await orderService.getOrdersByUser(
                this.getOrdersByUserUrl,
                this.$store.state.data.user,
                this.$store.state.data.config
            );

        },
        async getOrdersByStore() {
            console.log(this.$store.state.data.user.store);
            this.orders = await orderService.getOrdersByStore(
                this.getOrdersByStoreUrl,
                this.$store.state.data.user.store,
                this.$store.state.data.config
            );
            console.log(this.orders.slice());
        },
        proceedToPayment() {
            window.location.href = this.order.paymentLink;
        },
    },
    created() {
        // console.log(this.saveOrderUrl);
    },
};
