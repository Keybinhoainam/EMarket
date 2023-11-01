import axios from "axios";
export default {
    data() {
        return {
            url: "http://localhost:8080/api/data/file/images/",
        };
    },
    methods: {
        getImage(filename) {
            return axios.get(`${this.url}${filename}`).then(
                (res)=>{
                    return Promise.resolve(res);
                },
                (error) => {
                    return Promise.reject(error);
                }
            )
        },
    },
};
