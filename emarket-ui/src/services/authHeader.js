import Cookies from "js-cookie";
export default function authHeader() {
    if(Cookies.get("user")){
        let user = JSON.parse(Cookies.get("user"));
        // console.log(user.accessToken);
        if (user.accessToken) {
            return {
                headers: {
                    Authorization: "Bearer " + user.accessToken,
                    // "content-Type": "multipart/form-data",
                    // "Accept":"application/json"
                },
            };
        } else return {};
    }
}
