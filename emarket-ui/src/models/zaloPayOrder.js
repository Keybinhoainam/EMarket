export default class ZaloPayOrder {
    constructor(
        app_id = 2554,
        app_user,
        app_time,
        app_trans_id,
        amount,
        bank_code = "",
        embed_data = JSON.stringify({redirecturl: "http://localhost:8080/myPurchase"}),
        item = JSON.stringify([]),
        callback_url="http://localhost:8085/api/zaloPay/callback",
        description,
        mac,
        key1 = "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn",
        key2 = "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf",
    ) {
        Object.assign(this, {
            app_id,
            app_user,
            app_time,
            app_trans_id,
            amount,
            bank_code,
            embed_data,
            item,
            callback_url,
            description,
            mac,
            key1,
            key2,
        });
    }
}
