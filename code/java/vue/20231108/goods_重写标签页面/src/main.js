import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import axios from 'axios'
import Route from "@/router";
// import KrPrintDesigner from "kr-print-designer";
// import "kr-print-designer/lib/kr-print-designer.css";

Vue.prototype.axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);
// Vue.use(KrPrintDesigner);

// new Vue({
//   router,
//   store,
//   render: function (h) { return h(App) }
// }).$mount('#app')





router.beforeEach((to,from,next)=>{
  //如果当前要跳转的路由需要认证，则认证
  if (to.meta.requireAuth) {
    //获取当前浏览器cookie是否存在账号
    let userInfo = window.localStorage.getItem('userInfo')
    let userPower = window.localStorage.getItem('userPower')
    //如果cookie中保存有登录信息，就说明登陆过，放行
    if(userInfo && userPower) {
      next()
      //如果没有登录到，就强制重定向到login
    } else {
      next({
        name: 'login',
        //下面的query参数会让地址栏多一个重定向提示
        //可以注释掉，这样地址栏会简洁一点
        // query: {redirect: '/login'}
      })
    }
    //如果不需要认证就放行
  } else {
    next()
  }
})

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  // 注意这里
  store,
  components: { App },
  template: '<App/>'
})