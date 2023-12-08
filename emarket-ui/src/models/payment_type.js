export default class Payment_type {
    constructor(id=null,name="Cash on Delivery", description, image) {
        Object.assign(this, {
            id,
            name,
            description,
            image,
        });
    }
}
