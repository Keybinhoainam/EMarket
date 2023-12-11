import fileService from "@/services/file.service";

// import FileService from "@/services/file.servi"
export default {
    data() {
        return {
            url: "http://localhost:8080/api/data/file/images/",
        };
    },
    methods: {
        // async getImage(filename) {
        //     this.user.avatar= await fileService.getImage(this.url, filename, "image/*");
        //     console.log(this.user.avatar);
        // },
        convertImagePathToBlob(imagePath) {
            const xhr = new XMLHttpRequest();
            xhr.open("GET", imagePath, true);
            xhr.responseType = "blob";
      
            xhr.onload = () => {
              if (xhr.status === 200) {
                return xhr.response;
              }
            };
      
            xhr.send();
          },
    },
};
