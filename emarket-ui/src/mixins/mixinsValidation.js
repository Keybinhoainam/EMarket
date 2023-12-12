export default {
    data() {
        return {
            required: [
                (value) => {
                    if (value) return true;

                    return "this field is required.";
                },
            ],
        };
    },
    methods: {},
};
