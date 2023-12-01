import Store from "./store";

export default class User{
    // constructor(username,password){
    //     this.username=username;
    //     this.password=password;
    // }
    constructor(username,password,confirmPassword,email,fullname,gender,birthday,avatar,phone,store=new Store()){
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
    }
}