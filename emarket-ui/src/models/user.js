import Store from "./store";
import defaultAvatar from "@/assets/images/defaultAvatar.png"
export default class User{
    // constructor(username,password){
    //     this.username=username;
    //     this.password=password;
    // }
    constructor(username,password="12345678",confirmPassword="12345678",email,fullname,gender,birthday,avatar,phone,store=new Store(),roles=[],accessToken,avatarFile,currentPassword="12345678",avatarString){
        this.username=username;
        this.password=password;
        this.confirmPassword=confirmPassword;
        this.email=email;
        this.fullname=fullname;
        this.gender=gender;
        this.birthday=birthday;
        this.avatar=avatar;
        this.phone=phone;
        this.store=store;
        this.roles=roles;
        this.accessToken=accessToken;
        this.avatarFile=avatarFile;
        this.currentPassword=currentPassword;
        this.avatarString=avatarString;
    }
}