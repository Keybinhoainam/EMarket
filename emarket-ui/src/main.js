import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/common.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import vuetify from './plugins/vuetify'
import store from "./store";

// import { loadFonts } from './plugins/webfontloader'

// loadFonts()

const app=createApp(App).use(router).use(store);
app.use(VueAxios,axios)
app.use(vuetify)
app.use(VueSweetalert2);
app.mount('#app')

// Vue.config.ignoredElements = [/^ion-/]
