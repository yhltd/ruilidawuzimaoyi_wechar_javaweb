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
import mingxi from "../views/MingXi.vue"
import zhuanzhang from '../views/ZhuanZhang.vue'
import kucun from '../views/KuCun.vue'
import zhanghuyue from '../views/ZhangHuYuE.vue'
import shourufenlei from '../views/ShouRuFenLei.vue'
import zhichufenlei from '../views/ZhiChuFenLei.vue'
import yuedushouzhi from '../views/YueDuShouZhi.vue'
import caigouruku from '../views/CaiGouRuKu.vue'
import caigoushoupiao from '../views/CaiGouShouPiao.vue'
import zhichujilu from '../views/ZhiChuJiLu.vue'
import shourujilu from '../views/ShouRuJiLu.vue'
import xiaoshoubaojia from '../views/XiaoShouBaoJia.vue'
import xiaoshoudingdan from '../views/XiaoShouDingDan.vue'
import xiaoshouchuku from '../views/XiaoShouChuKu.vue'
import xiaoshoukaipiao from '../views/XiaoShouKaiPiao.vue'
import fujiashuishezhi from '../views/FuJiaShuiSheZhi.vue'
import print from '../views/Print.vue'
import printlist from '../views/PrintList.vue'
import main from '../views/main.vue'
import xiaoshoumaolitongji from '../views/XiaoShouMaoLiTongJi.vue'
import xiaoshoudingdantongji from '../views/XiaoShouDingDanTongJi.vue'
import xiaoshoushoukuantongji from '../views/XiaoShouShouKuanTongJi.vue'
import kehuwanglai from '../views/KeHuWangLai.vue'
import gongyingshangwanglai from '../views/GongYingShangWangLai.vue'
import dingdanzoushi from '../views/XiaoShouDingDanZouShi.vue'
import shoukuanzoushi from '../views/XiaoShouShouKuanZouShi.vue'
import caigoufukuanzoushi from '../views/CaiGouFuKuanZouShi.vue'
import ShangPinGuiGe from "@/views/ShangPinGuiGe.vue";// 增加商品规格页面
import jiZhangMingXi from "@/views/JiZhangMingXi.vue";
import JiZhang from "@/views/JiZhang.vue";
import XiaoShouKaiPiaoTongJi from "@/views/XiaoShouKaiPiaoTongJi.vue";
import QiChuKeHu from "@/views/QiChuKeHu.vue";
import QiChuGongYingShang from "@/views/QiChuGongYingShang.vue";
import QiChuShangPin from "@/views/QiChuShangPin.vue";


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
            // requireAuth: true
        },
        children: [
            {
                path: '/basePeizhi',
                name: 'basePeizhi',
                component: () => import('../views/peizhi/BasePeiZhi'),
                props: '',
                meta: {
                    title: '仓库',
                    requireAuth: true
                },
            },
            {
                path: '/cangKu',
                name: 'cangKu',
                component: () => import('../views/peizhi/Cangku'),
                meta: {
                    title: '仓库',
                    type: '仓库',
                    requireAuth: true
                }
            }, {
                path: '/dianPu',
                name: 'dianPu',
                component: () => import('../views/peizhi/DianPu.vue'),
                meta: {
                    title: '店铺',
                    type: '店铺',
                    requireAuth: true
                }
            }, {
                path: '/gongyingshangLevel',
                name: 'gongyingshangLevel',
                component: () => import('../views/peizhi/GongYingShangLevel.vue'),
                meta: {
                    title: '供应商等级',
                    type: '供应商等级',
                    requireAuth: true
                }
            }, {
                path: '/hesuanDanwei',
                name: 'hesuanDanwei',
                component: () => import('../views/peizhi/HeSuanDanWei.vue'),
                meta: {
                    title: '核算单位',
                    type: '核算单位',
                    requireAuth: true
                }
            }, {
                path: '/jiageLevel',
                name: 'jiageLevel',
                component: () => import('../views/peizhi/JiaGeLevel.vue'),
                meta: {
                    title: '价格等级',
                    type: '价格等级',
                    requireAuth: true
                }
            }, {
                path: '/jiageShangFuLv',
                name: 'jiageShangFuLv',
                component: () => import('../views/peizhi/JiaGeShangFuLv.vue'),
                meta: {
                    title: '价格上浮率',
                    type: '价格上浮率',
                    requireAuth: true
                }
            }, {
                path: '/jizhangMingxiType',
                name: 'jizhangMingxiType',
                component: () => import('../views/peizhi/JiZhangMingXiType.vue'),
                meta: {
                    title: '记账明细类型',
                    type: '记账明细类型',
                    requireAuth: true
                }
            }, {
                path: '/jiZhangMingXi',
                name: 'jiZhangMingXi',
                component: () => import('../views/JiZhangMingXi.vue'),
                meta: {
                    title: '记账明细',
                    type: '记账明细',
                    requireAuth: true
                }
            }, {
                path: '/jizhangType',
                name: 'jizhangType',
                component: () => import('../views/peizhi/JiZhangType.vue'),
                meta: {
                    title: '记账分类',
                    type: '记账分类',
                    requireAuth: true
                }
            }, {
                path: '/kehuLevel',
                name: 'kehuLevel',
                component: () => import('../views/peizhi/KeHuLevel.vue'),
                meta: {
                    title: '客户等级',
                    type: '客户等级',
                    requireAuth: true
                }
            }, {
                path: '/kehuType',
                name: 'kehuType',
                component: () => import('../views/peizhi/KeHuType.vue'),
                meta: {
                    title: '客户分类',
                    type: '客户分类',
                    requireAuth: true
                }
            }, {
                path: '/shangpinType',
                name: 'shangpinType',
                component: () => import('../views/peizhi/ShangpinType.vue'),
                meta: {
                    title: '商品分类',
                    type: '商品分类',
                    requireAuth: true
                }
            }, {
                path: '/shouKuanZhanghu',
                name: 'shouKuanZhanghu',
                component: () => import('../views/peizhi/ShouKuanZhangHu.vue'),
                meta: {
                    title: '收款账户',
                    type: '收款账户',
                    requireAuth: true
                }
            }, {
                path: '/zhibaoLevel',
                name: 'zhibaoLevel',
                component: () => import('../views/peizhi/ZhiBaoLevel.vue'),
                meta: {
                    title: '质保等级',
                    type: '质保等级',
                    requireAuth: true
                }
            },

            // 增加商品单位页面
            {
                path: '/shangPinDanWei',
                name: 'shangPinDanWei',
                component: () => import('../views/peizhi/ShangPinDanWei.vue'),
                meta: {
                    title: '商品单位',
                    type: '商品单位',
                    requireAuth: true
                }
            },
            // // 增加进项税率页面
            // {
            //     path: '/jinXiangShuiLv',
            //     name: 'jinXiangShuiLv',
            //     component: () => import('../views/peizhi/JinXiangShuiLv.vue'),
            //     meta: {
            //         title: '进项税率',
            //         type: '进项税率',
            //         requireAuth: true
            //     }
            // },
            // // 增加销项税率页面
            // {
            //     path: '/xiaoXiangShuiLv',
            //     name: 'xiaoXiangShuiLv',
            //     component: ()=> import('../views/peizhi/XiaoXiangShuiLv.vue'),
            //     meta: {
            //         title: '销项税率',
            //         type: '销项税率',
            //         requireAuth: true
            //     }
            // },

            // 增加增值税率页面
            {
                path: '/ZengZhiShuiLv',
                name: 'ZengZhiShuiLv',
                component: () => import('../views/peizhi/ZengZhiShuiLv.vue'),
                meta: {
                    title: '增值税率',
                    type: '增值税率',
                    requireAuth: true
                }
            },

            {
                path: '/supplier',
                name: 'supplier',
                component: () => import("../views/GongYingShang.vue"),
                meta: {
                    title: '供应商',
                    requireAuth: true
                }
            },
            {
                path: '/zhuanzhang',
                name: 'zhuanzhang',
                component: () => import("../views/ZhuanZhang.vue"),
                meta: {
                    title: '转账',
                    requireAuth: true
                }
            },
            {
                path: '/kucun',
                name: 'kucun',
                component: () => import("../views/KuCun.vue"),
                meta: {
                    title: '库存',
                    requireAuth: true
                }
            },
            {
                path: '/zhanghuyue',
                name: 'zhanghuyue',
                component: () => import("../views/ZhangHuYuE.vue"),
                meta: {
                    title: '账户余额',
                    requireAuth: true
                }
            },
            {
                path: '/shourufenlei',
                name: 'shourufenlei',
                component: () => import("../views/ShouRuFenLei.vue"),
                meta: {
                    title: '收入分类统计',
                    requireAuth: true
                }
            },
            {
                path: '/zhichufenlei',
                name: 'zhichufenlei',
                component: () => import("../views/ZhiChuFenLei.vue"),
                meta: {
                    title: '支出分类统计',
                    requireAuth: true
                }
            },
            {
                path: '/yuedushouzhi',
                name: 'yuedushouzhi',
                component: () => import("../views/YueDuShouZhi.vue"),
                meta: {
                    title: '月度收支统计',
                    requireAuth: true
                }
            },
            {
                path: '/xiaoshoumaolitongji',
                name: 'xiaoshoumaolitongji',
                component: () => import("../views/XiaoShouMaoLiTongJi.vue"),
                meta: {
                    title: '销售毛利统计',
                    requireAuth: true
                }
            },
            {
                path: '/xiaoshoudingdantongji',
                name: 'xiaoshoudingdantongji',
                component: () => import("../views/XiaoShouDingDanTongJi.vue"),
                meta: {
                    title: '销售订单统计',
                    requireAuth: true
                }
            },
            {
                path: '/xiaoshoushoukuantongji',
                name: 'xiaoshoushoukuantongji',
                component: () => import("../views/XiaoShouShouKuanTongJi.vue"),
                meta: {
                    title: '销售收款统计',
                    requireAuth: true
                }
            },
            {
                path: '/kehuwanglai',
                name: 'kehuwanglai',
                component: () => import("../views/KeHuWangLai.vue"),
                meta: {
                    title: '客户往来',
                    requireAuth: true
                }
            },
            {
                path: '/gongyingshangwanglai',
                name: 'gongyingshangwanglai',
                component: () => import("../views/GongYingShangWangLai.vue"),
                meta: {
                    title: '供应商往来',
                    requireAuth: true
                }
            },
            {
                path: '/dingdanzoushi',
                name: 'dingdanzoushi',
                component: () => import("../views/XiaoShouDingDanZouShi.vue"),
                meta: {
                    title: '销售订单走势',
                    requireAuth: true
                }
            },
            {
                path: '/shoukuanzoushi',
                name: 'shoukuanzoushi',
                component: () => import("../views/XiaoShouShouKuanZouShi.vue"),
                meta: {
                    title: '销售收款走势',
                    requireAuth: true
                }
            },
            {
                path: '/caigoufukuanzoushi',
                name: 'caigoufukuanzoushi',
                component: () => import("../views/CaiGouFuKuanZouShi.vue"),
                meta: {
                    title: '采购付款走势',
                    requireAuth: true
                }
            },
            {
                path: "/powerManage",
                name: "powerManage",
                component: () => import("../views/UserPower.vue"),
                meta: {
                    title: '权限管理',
                    requireAuth: true
                }
            },
            {
                path: "/userInfo",
                name: 'userInfo',
                component: () => import('../views/UserInfo.vue'),
                meta: {
                    title: '账号管理',
                    requireAuth: true
                }
            },
            {
                path: "/goods",
                name: "goods",
                component: goods,
                meta: {
                    title: "商品",
                    requireAuth: true
                }
            },
            {
                path: "/user",
                name: "user",
                component: user,
                meta: {
                    title: "客户",
                    requireAuth: true
                }
            },
            {
                path: "/collAccount",
                name: "collAccount",
                component: collAccount,
                meta: {
                    title: '收款账户',
                    requireAuth: true
                },
            },
            {
                path: "/accountingunit",
                name: "accountingunit",
                component: accountingunit,
                meta: {
                    title: '核算单位',
                    requireAuth: true
                },
            },
            {
                path: "/mingxi",
                name: "mingxi",
                component: mingxi,
                meta: {
                    title: '采购订单',
                    requireAuth: true
                },
            },
            {
                path: "/caigouruku",
                name: "caigouruku",
                component: caigouruku,
                meta: {
                    title: '采购入库',
                    requireAuth: true
                }
            },
            {
                path: "/caigoushoupiao",
                name: "caigoushoupiao",
                component: caigoushoupiao,
                meta: {
                    title: '采购收票',
                    requireAuth: true
                }
            },
            {
                path: "/zhichujilu",
                name: "zhichujilu",
                component: zhichujilu,
                meta: {
                    title: '支出记录',
                    requireAuth: true
                }
            },
            {
                path: "/shourujilu",
                name: "shourujilu",
                component: shourujilu,
                meta: {
                    title: '收入记录',
                    requireAuth: true
                }
            },
            {
                path: "/xiaoshoubaojia",
                name: "xiaoshoubaojia",
                component: xiaoshoubaojia,
                meta: {
                    title: '销售报价',
                    requireAuth: true
                }
            },
            {
                path: "/xiaoshoudingdan",
                name: "xiaoshoudingdan",
                component: xiaoshoudingdan,
                meta: {
                    title: '销售订单',
                    requireAuth: true
                }
            },
            {
                path: "/xiaoshouchuku",
                name: "xiaoshouchuku",
                component: xiaoshouchuku,
                meta: {
                    title: '销售出库',
                    requireAuth: true
                }
            },
            {
                path: "/xiaoshoukaipiao",
                name: "xiaoshoukaipiao",
                component: xiaoshoukaipiao,
                meta: {
                    title: '销售开票',
                    requireAuth: true
                }
            },
            {
                path: "/fujiashuishezhi",
                name: "fujiashuishezhi",
                component: fujiashuishezhi,
                meta: {
                    title: '价格管理',
                    requireAuth: true
                }
            },
            {
                path: "/print",
                name: "print",
                component: print,
                meta: {
                    title: '打印',
                    requireAuth: true
                }
            },
            {
                path: "/printlist",
                name: "printlist",
                component: printlist,
                meta: {
                    title: '打印',
                    requireAuth: true
                }
            },


            {
                path: "/shangPinGuiGe",
                name: "shangPinGuiGe",
                component: ShangPinGuiGe,
                meta:{
                    title: '商品规格',
                    requireAuth: true
                }
            },

            {
                path: "/jiZhang",
                name: "jiZhang",
                component: JiZhang,
                meta:{
                    title: '记账',
                    requireAuth: true
                }
            },

            {
                path: "/xiaoShouKaiPiaoTongJi",
                name: "xiaoShouKaiPiaoTongJi",
                component: XiaoShouKaiPiaoTongJi,
                meta:{
                    title: '销售开票统计',
                    requireAuth: true
                }
            },

            {
                path: "/qiChuKeHu",
                name: "qiChuKeHu",
                component: QiChuKeHu,
                meta:{
                    title: '期初-客户',
                    requireAuth: true
                }
            },

            {
                path: "/qiChuGongYingShang",
                name: "qiChuGongYingShang",
                component: QiChuGongYingShang,
                meta:{
                    title: '期初-供应商',
                    requireAuth: true
                }
            },

            {
                path: "/qiChuShangPin",
                name: "qiChuShangPin",
                component: QiChuShangPin,
                meta:{
                    title: '期初-库存',
                    requireAuth: true
                }
            },

            {
                path: "/main",
                name: "main",
                component: main,
                meta: {
                    title: '首页',
                    requireAuth: true
                }
            },
        ],
    },
];

const router = new VueRouter({
    mode: "history",
    routes,
});

// 设置页面标题
router.afterEach((to, from) => {
    document.title = to.meta.title || "进销存系统";
})
export default router;
