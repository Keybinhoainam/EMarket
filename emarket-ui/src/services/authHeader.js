import Cookies from "js-cookie";
export default function authHeader() {
    if(Cookies.get("accessToken")){
        let accessToken = JSON.parse(Cookies.get("accessToken"));
        // console.log(user.accessToken);
        if (accessToken) {
            return {
                headers: {
                    Authorization: "Bearer " + accessToken,
                    // "content-Type": "multipart/form-data",
                    // "Accept":"application/json"
                },
            };
        } else return {};
    }
}
