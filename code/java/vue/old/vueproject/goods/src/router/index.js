import Vue from "vue";
import VueRouter from "vue-router";
//主路由
import Index from "../components/Index.vue";


//子路由
import user from "../views/User.vue";
import goods from "../views/Goods.vue";
import collAccount from "../views/CollAccount.vue";
import accountingunit from "../views/AccountingUnit.vue";
import editgoods from "../views/EditGoods.vue";
import asd from "../views/asd.vue";
import dingdan from "../views/Dingdan.vue";
import liebiao from "../views/LieBiao.vue";
import dingdanadd from "../views/DingDanAdd.vue"
import mingxi from "../views/MingXi.vue"
import updgoods from '../views/UpdGoods.vue'
Vue.use(VueRouter);

const routes = [

  {
    path: "/",
    name: "index",
    show: true,
    component: Index,
    children: [
      {
        path: "/goods",
        name: "goods",
        component: goods,
      },
      {
        path: "/user",
        name: "user",
        component: user,
      },
      {
        path: "/collAccount",
        name: "collAccount",
        component: collAccount,
      },
      {
        path: "/accountingunit",
        name: "accountingunit",
        component: accountingunit,
      },
      {
        path: "/editgoods",
        name: "editgoods",
        component: editgoods,
      },
      {
        path: "/asd",
        name: "asd",
        component: asd,
      },
      {
        path: "/updgoods",
        name: "updgoods",
        component: updgoods,
      },
      {
        path: "/dingdanadd",
        name: "dingdanadd",
        component: dingdanadd,
      },
      {
        path: "/dingdan",
        name: "dingdan",
        component: dingdan,
        children: [
          {
            path: "/dingdan",
            name: "liebiao",
            component: liebiao,
          },
          {
            path: "/mingxi",
            name: "mingxi",
            component: mingxi,
          },
        ],
      },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
