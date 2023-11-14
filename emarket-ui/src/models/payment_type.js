export default class Payment_type {
    constructor(id=null,name, description, image, orders=[]) {
        Object.assign(this, {
            id,
            name,
            description,
            image,
            orders,
        });
    }
}
