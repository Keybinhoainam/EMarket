export default class Store{
    constructor(id=null,store_code=null,store_name=null,description=null,image=null){
        Object.assign(this,{id,store_code,store_name,description,image})
    }
}