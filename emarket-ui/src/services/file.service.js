import axios from "axios";
class FileService {
    getImage(url, filename,mimeType) {
        return axios
            .get(`${url}${filename}`, { responseType: "blob" })
            .then((res) =>{
                // console.log([res.data]);
                return new File([res.data], filename, { type: mimeType });
            })
    }
}
export default new FileService();
