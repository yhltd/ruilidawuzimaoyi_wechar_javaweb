import Vue from "vue";
import VueRouter from "vue-router";
//主路由
import Index from "../components/Index.vue"
import Login from "../views/Login.vue"
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
import Table from '../views/ITable.vue'

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "login",
        show: true,
        component: Login,
        meta: {
            title: '登录'
        },
    },
    {
        path: '/index',
        name: 'index',
        component: Index,
        meta: {
            title: "首页",
        },
        children: [
            {
                path: "/powerManage",
                name: "powerManage",
                component: () => import("../views/UserPower.vue"),
                meta: {
                    title: '权限管理'
                }
            },
            {
                path: "/userInfo",
                name: 'userInfo',
                component: () => import('../views/UserInfo.vue'),
                meta: {
                    title: '账号管理'
                }
            },
            {
                path: "/goods",
                name: "goods",
                component: goods,
                meta: {
                    title: "商品",
                }
            },
            {
                path: "/user",
                name: "user",
                component: user,
                meta: {
                    title: "客户",
                }
            },
            {
                path: "/collAccount",
                name: "collAccount",
                component: collAccount,
                meta: {
                    title: '收款账户'
                },
            },
            {
                path: "/accountingunit",
                name: "accountingunit",
                component: accountingunit,
                meta: {
                    title: '核算单位'
                },
            },
            {
                path: "/editgoods",
                name: "editgoods",
                component: editgoods,
                meta: {
                    title: '编辑商品'
                },
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
                meta: {
                    title: '修改商品'
                },
            },
            {
                path: "/dingdanadd",
                name: "dingdanadd",
                component: dingdanadd,
                meta: {
                    title: '添加订单'
                }
            },
            {
                path: "/dingdan",
                name: "dingdan",
                component: dingdan,
                meta: {
                    title: '订单'
                },
            },
        ],
    },
];

const router = new VueRouter({
    routes,
});

// 设置页面标题
router.afterEach((to, from) => {
    document.title = to.meta.title || "goods";
})
export default router;
