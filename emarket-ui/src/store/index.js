import Vuex from 'vuex'
// import {auth} from './modules/auth';
import { data } from './modules/data';
export default new Vuex.Store({
    modules:{
        // auth
        data
    }
});
// import { createStore } from 'vuex'
// const store = createStore({
//     modules:{
//         data
//     }
// })
// export default store;