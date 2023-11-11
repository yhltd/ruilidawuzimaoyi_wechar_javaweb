import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import ITable from './views/ITable'
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import axios from 'axios'
import Route from "@/router";
Vue.prototype.axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  // store,
  render: function (h) { return h(App) }
}).$mount('#app')
