// import AuthService from "@/services/auth.service";
// const user = JSON.parse(localStorage.getItem("user"));
// const initialState = user
//     ? { status: { loggedIn: true }, user }
//     : { status: { loggedIn: false }, user: null };
// export const auth = {
//     namespaced: true,
//     state: initialState,
//     actions: {
//         login({ commit }, {url, user,config}) {
//             return AuthService.login(url, user,config).then(
//                 (user) => {
                    
//                     commit("loginSuccess", user);
//                     return Promise.resolve(user);
//                 },
//                 (error) => {
//                     commit("loginFailure");
//                     return Promise.reject(error);
//                 }
//             );
//         },
//         logout({ commit }) {
//             AuthService.logout();
//             commit("logout");
//         },
//         register({ commit }, {url, user}) {
//             return AuthService.register(url, user).then(
//                 (user)=>{
//                     commit("registerSuccess",user);
//                     return Promise.resolve(user);
//                 },
//                 error=>{
//                     commit("registerFailure");
//                     return Promise.reject(error);
//                 }
//             )
//         },
//     },
//     mutations: {
//         loginSuccess(state, user) {
//             state.status.loggedIn = true;
//             state.user = user;
//         },
//         loginFailure(state) {
//             state.status.loggedIn = false;
//             state.user = null;
//         },
//         logout(state) {
//             state.status.loggedIn = false;
//             state.user = null;
//         },
//         registerSuccess(state) {
//             state.status.loggedIn = false;
//         },
//         registerFailure(state) {
//             state.status.loggedIn = false;
//         },
//     },
// };
