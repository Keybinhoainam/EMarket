const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    devServer: {
        proxy: {
            "^/zaloPay": {
                target: "https://sb-openapi.zalopay.vn/v2",
                changeOrigin: true,
                pathRewrite: {
                    "^/zaloPay": "", // Loại bỏ tiền tố '/zaloPay' từ yêu cầu
                },
            },
            "": {
                target: "http://localhost:8085",
                changeOrigin: true,
            },
        },
        // onBeforeSetupMiddleware: function (devServer) {
        //     // Cấu hình proxy cho các đường dẫn bắt đầu với '/zaloPay'
        //     devServer.app.use(
        //         "/zaloPay",
        //         createProxyMiddleware({
        //             target: "https://sb-openapi.zalopay.vn/v2",
        //             changeOrigin: true,
        //             pathRewrite: {
        //                 "^/zaloPay": "", // Loại bỏ tiền tố '/zaloPay' từ yêu cầu
        //             },
        //         })
        //     );

        //     // Cấu hình proxy cho các đường dẫn khác
        //     devServer.app.use(
        //         createProxyMiddleware({
        //             target: "http://localhost:8085",
        //             changeOrigin: true,
        //         })
        //     );
        // },
    },

    transpileDependencies: true,

    pluginOptions: {
        vuetify: {
            // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
        },
    },
});
