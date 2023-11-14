import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import axios from 'axios'
// axios.defaults.withCredentials=true;
import Route from "@/router";
Vue.prototype.axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  // store,
  render: function (h) { return h(App) }
}).$mount('#app')
