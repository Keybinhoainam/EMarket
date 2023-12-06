import axios from "axios";
class FileService {
    
    constructor() {
        this.url= "http://localhost:8080/api/data/file/images/";
      }
    getImage(filename,mimeType) {
        return axios
            .get(`${this.url}${filename}`, { responseType: "blob" })
            .then((res) =>{
                // console.log([res.data]);
                return new File([res.data], filename, { type: mimeType });
            })
    }
}
export default new FileService();
