export default class Category{
    constructor(id=null,category_name=null, description=null,image=null,products=[]){
        this.id=id
        this.category_name=category_name;
        this.description=description;
        this.image=image;
        this.products=products
        
    }
}