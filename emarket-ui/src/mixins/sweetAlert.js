export default {
    data() {
        return {};
    },
    methods: {
        alertSuccess(title, text) {
            this.$swal.fire({
                position: "top-end",
                icon: "success",
                title: title,
                showConfirmButton: false,
                timer: 1500,
            });
        },
        alertFail(title, text) {
            this.$swal.fire({
                position: "top-end",
                icon: "error",
                title: title,
                text: text,
                showConfirmButton: false,
                timer: 1500,
            });
        },
        alertWarning(title, text,confirmButtonText) {
            return this.$swal.fire({
                title: title,
                text: text,
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: confirmButtonText,
            });
        },
    },
};
