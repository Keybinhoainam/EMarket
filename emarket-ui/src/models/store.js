export default class Store {
    constructor(
        id = null,
        store_code = null,
        store_name = "",
        description = null,
        image = null,
        imageString,
        imageFile
    ) {
        Object.assign(this, {
            id,
            store_code,
            store_name,
            description,
            image,
            imageString,
            imageFile,
        });
    }
}
