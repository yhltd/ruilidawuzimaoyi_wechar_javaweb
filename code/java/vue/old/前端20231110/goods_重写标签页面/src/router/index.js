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
                path: '/basePeizhi',
                name: 'basePeizhi',
                component: () => import('../views/peizhi/BasePeiZhi'),
                props: '',
                meta: {
                    title: '仓库',
                },
            },
            {
                path: '/cangKu',
                name: 'cangKu',
                component: () => import('../views/peizhi/Cangku'),
                meta: {
                    title: '仓库',
                    type: '仓库'
                }
            }, {
                path: '/dianPu',
                name: 'dianPu',
                component: () => import('../views/peizhi/DianPu.vue'),
                meta: {
                    title: '店铺',
                    type: '店铺'
                }
            }, {
                path: '/gongyingshangLevel',
                name: 'gongyingshangLevel',
                component: () => import('../views/peizhi/GongYingShangLevel.vue'),
                meta: {
                    title: '供应商等级',
                    type: '供应商等级'
                }
            }, {
                path: '/hesuanDanwei',
                name: 'hesuanDanwei',
                component: () => import('../views/peizhi/HeSuanDanWei.vue'),
                meta: {
                    title: '核算单位',
                    type: '核算单位'
                }
            }, {
                path: '/jiageLevel',
                name: 'jiageLevel',
                component: () => import('../views/peizhi/JiaGeLevel.vue'),
                meta: {
                    title: '价格等级',
                    type: '价格等级',
                }
            }, {
                path: '/jiageShangFuLv',
                name: 'jiageShangFuLv',
                component: () => import('../views/peizhi/JiaGeShangFuLv.vue'),
                meta: {
                    title: '价格上浮率',
                    type: '',
                }
            }, {
                path: '/jizhangMingxiType',
                name: 'jizhangMingxiType',
                component: () => import('../views/peizhi/JiZhangMingXiType.vue'),
                meta: {
                    title: '记账明细类型',
                    type: '记账明细类型',
                }
            }, {
                path: '/jizhangType',
                name: 'jizhangType',
                component: () => import('../views/peizhi/JiZhangType.vue'),
                meta: {
                    title: '记账分类',
                    type: '记账分类',
                }
            }, {
                path: '/kehuLevel',
                name: 'kehuLevel',
                component: () => import('../views/peizhi/KeHuLevel.vue'),
                meta: {
                    title: '客户等级',
                    type: '客户等级',
                }
            }, {
                path: '/kehuType',
                name: 'kehuType',
                component: () => import('../views/peizhi/KeHuType.vue'),
                meta: {
                    title: '客户分类',
                    type: '客户分类',
                }
            }, {
                path: '/shangpinType',
                name: 'shangpinType',
                component: () => import('../views/peizhi/ShangpinType.vue'),
                meta: {
                    title: '商品分类',
                    type: '商品分类',
                }
            }, {
                path: '/shouKuanZhanghu',
                name: 'shouKuanZhanghu',
                component: () => import('../views/peizhi/ShouKuanZhangHu.vue'),
                meta: {
                    title: '收款账户',
                    type: '收款账户',
                }
            }, {
                path: '/zhibaoLevel',
                name: 'zhibaoLevel',
                component: () => import('../views/peizhi/ZhiBaoLevel.vue'),
                meta: {
                    title: '质保等级',
                    type: '质保等级',
                }
            },
            {
                path: '/supplier',
                name: 'supplier',
                component: () => import("../views/GongYingShang.vue"),
                meta: {
                    title: '供应商'
                }
            },
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
                children: [
                    {
                        path: "/dingdan",
                        name: "liebiao",
                        component: liebiao,
                        meta: {
                            title: '列表'
                        },
                    },
                    {
                        path: "/mingxi",
                        name: "mingxi",
                        component: mingxi,
                        meta: {
                            title: '明细'
                        },
                    },
                ],
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
