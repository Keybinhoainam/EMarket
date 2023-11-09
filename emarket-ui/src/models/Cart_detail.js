import Product from "./product";
import User from "./user";

export default class Cart_detail{
    constructor(id=null, quantity=0,user=new User(),product=new Product()){
        Object.assign(this,{id, quantity,user,product})
    }
}