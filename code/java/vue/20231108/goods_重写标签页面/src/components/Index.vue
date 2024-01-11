<template>
  <div>
    <div class="custom-header">

      <el-menu
          class="el-menu-demo"
          mode="horizontal"
          background-color="#2b62b5"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
      ><!-- ui-->
        <el-menu-item  style="margin-left:3%">
          <img src="../assets/menuLogo.png" style="height: 50px;width: 50px;margin-right: 10px"><!-- ui-->
          <span style="font-size: 30px">进销存系统</span>
        </el-menu-item>

        <el-menu-item @click="goRouter('/main')">首页</el-menu-item>

        <el-submenu index="1" style="margin-left:1%" class="el-submenu-item"><!-- ui-->
          <template slot="title">销售</template>
          <el-menu-item @click="goRouter('/xiaoshoubaojia')"><p class="custom-menu-item">销售报价</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshoudingdan')"><p class="custom-menu-item">销售订单</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshouchuku')"><p class="custom-menu-item">销售出库</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshoukaipiao')"><p class="custom-menu-item">销售开票</p></el-menu-item>
          <el-menu-item @click="goRouter('/shourujilu')"><p class="custom-menu-item">收入记录</p></el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">采购</template>
          <el-menu-item @click="goRouter('/mingxi')"><p class="custom-menu-item">采购订单</p></el-menu-item>
          <el-menu-item @click="goRouter('/caigouruku')"><p class="custom-menu-item">采购入库</p></el-menu-item>
          <el-menu-item @click="goRouter('/caigoushoupiao')"><p class="custom-menu-item">采购收票</p></el-menu-item>
          <el-menu-item @click="goRouter('/zhichujilu')"><p class="custom-menu-item">支出记录</p></el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">统计</template>
          <el-menu-item @click="goRouter('/ZhuanZhang')"><p class="custom-menu-item">转账</p></el-menu-item>
          <el-menu-item @click="goRouter('/KuCun')"><p class="custom-menu-item">库存</p></el-menu-item>
          <el-menu-item @click="goRouter('/ZhangHuYuE')"><p class="custom-menu-item">账户余额</p></el-menu-item>
          <el-menu-item @click="goRouter('/ShouRuFenLei')"><p class="custom-menu-item">收入分类统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/ZhiChuFenLei')"><p class="custom-menu-item">支出分类统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/YueDuShouZhi')"><p class="custom-menu-item">月度收支统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshoumaolitongji')"><p class="custom-menu-item">销售毛利统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshoudingdantongji')"><p class="custom-menu-item">销售订单统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/xiaoshoushoukuantongji')"><p class="custom-menu-item">销售收款统计</p></el-menu-item>
          <el-menu-item @click="goRouter('/kehuwanglai')"><p class="custom-menu-item">客户往来</p></el-menu-item>
          <el-menu-item @click="goRouter('/gongyingshangwanglai')"><p class="custom-menu-item">供应商往来</p></el-menu-item>
          <el-menu-item @click="goRouter('/dingdanzoushi')"><p class="custom-menu-item">销售订单走势</p></el-menu-item>
          <el-menu-item @click="goRouter('/shoukuanzoushi')"><p class="custom-menu-item">销售收款走势</p></el-menu-item>
          <el-menu-item @click="goRouter('/caigoufukuanzoushi')"><p class="custom-menu-item">采购付款走势</p></el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">基础资料</template>
          <el-menu-item @click="goRouter('/userInfo')"><p class="custom-menu-item">账号管理</p></el-menu-item>
          <el-menu-item @click="goRouter('/powerManage')"><p class="custom-menu-item">权限管理</p></el-menu-item>
          <el-menu-item @click="goRouter('/user')"><p class="custom-menu-item">客户</p></el-menu-item>
          <el-menu-item @click="goRouter('/goods')"><p class="custom-menu-item">商品</p></el-menu-item>
          <el-menu-item @click="goRouter('/supplier')"><p class="custom-menu-item">供应商</p></el-menu-item>
          <el-menu-item @click="goRouter('/fujiashuishezhi')"><p class="custom-menu-item">价格管理</p></el-menu-item>
          <el-menu-item @click="goRouter('/printlist')"><p class="custom-menu-item">打印</p></el-menu-item>
          <!--          增加商品规格页面-->
          <el-menu-item @click="goRouter('/shangPinGuiGe')"><p class="custom-menu-item">商品规格</p></el-menu-item>
        </el-submenu>
        <el-submenu index="5">
          <template slot="title">下拉配置</template>
          <el-menu-item @click="getRouterParam('/basePeizhi', '商品分类')"><p class="custom-menu-item">商品分类</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '仓库')"><p class="custom-menu-item">仓库</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '收款账户')"><p class="custom-menu-item">收款账户</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '店铺')"><p class="custom-menu-item">店铺</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '核算单位')"><p class="custom-menu-item">核算单位</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '客户等级')"><p class="custom-menu-item">客户等级</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '供应商等级')"><p class="custom-menu-item">供应商等级</p>
          </el-menu-item>
<!--          <el-menu-item @click="getRouterParam('/basePeizhi', '价格等级')"><p class="custom-menu-item">价格等级</p>-->
<!--          </el-menu-item>-->
          <el-menu-item @click="getRouterParam('/basePeizhi', '客户分类')"><p class="custom-menu-item">客户分类</p>
          </el-menu-item>
          <el-menu-item @click="getRouterParam('/basePeizhi', '质保等级')"><p class="custom-menu-item">质保等级</p>
          </el-menu-item>
<!--          <el-menu-item @click="getRouterParam('/basePeizhi', '价格上浮率')"><p class="custom-menu-item">价格上浮率</p>-->
<!--          </el-menu-item>-->
<!--          <el-menu-item @click="getRouterParam('/basePeizhi', '记账分类')"><p class="custom-menu-item">记账分类</p>-->
<!--          </el-menu-item>-->
<!--          <el-menu-item @click="getRouterParam('/basePeizhi', '记账明细类型')"><p class="custom-menu-item">记账明细类型</p>-->
<!--          </el-menu-item>-->
          <el-menu-item @click="goRouter('/jiZhangMingXi')"><p class="custom-menu-item">记账明细</p>
          </el-menu-item>
          <!--         index页面 增加商品单位、进项税率、销项税率-->
          <el-menu-item class="menuItemYiDong" @click="getRouterParam('/basePeizhi','商品单位')"><p class="custom-menu-item">商品单位</p>
          </el-menu-item>
<!--          <el-menu-item class="menuItemYiDong" @click="getRouterParam('/basePeizhi','进项税率')"><p class="custom-menu-item">进项税率</p>-->
<!--          </el-menu-item>-->
<!--          <el-menu-item class="menuItemYiDong" @click="getRouterParam('/basePeizhi','销项税率')"><p class="custom-menu-item">销项税率</p>-->
<!--          </el-menu-item>-->
          <el-menu-item class="menuItemYiDong" @click="getRouterParam('/basePeizhi','增值税率')"><p class="custom-menu-item">增值税率</p>
          </el-menu-item>
        </el-submenu>

        <el-menu-item style="float: right;" >
          <span>1.0.0</span>
        </el-menu-item>

        <el-menu-item style="float: right;margin-right: 1%" index="7" @click="quit()">
          <i class="el-icon-switch-button"></i>
          <span>退出登录</span>
        </el-menu-item>

        <el-menu-item style="float: right;" >
          <span>权限:{{userInfo.power}}</span>
          <i class="el-icon-s-custom"></i><!-- ui-->
        </el-menu-item>

        <el-menu-item style="float: right;" >
          <span>欢迎您:{{userInfo.name}}</span>
        </el-menu-item>

      </el-menu>

    </div>
    <el-container>
      <el-main style="padding-top:0px;padding-bottom: 0px;" >
        <div class="container-box">
          <el-header height="40px" class="inner-header">{{ PageTitle || '错误' }}</el-header>
          <div class="view-box">
            <router-view>

            </router-view>
          </div>
        </div>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import RouterUtil from "../utils/RouterU.js"
import MessageUtil from "@/utils/MessageUtil";

export default {
  data() {
    return {
      PageTitle: ''
    }
  },
  created() {
    this.getUser();
  },
  methods: {
    goRouter(url) {
      RouterUtil.to(url);
    },
    /**
     * 跳转配置页面专用 不能作为他用
     * @param url
     * @param {string} pageType  跳转页面 类型必须为相关页面中 meta中的type值
     */
    getRouterParam(url, pageType) {
      RouterUtil.to(url + '?type=' + pageType);
    },
    getPageTitle() {
      this.PageTitle = document.title
    },
    childCallTitleRename(title) {
      this.PageTitle = title
    },
    quit(){
      localStorage.removeItem("userInfo");
      localStorage.removeItem("userPower");
      RouterUtil.to("/");
    },
    getUser(){
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://yhocn.cn:8102/user/queryUserInfoById"
      this.axios.post(url,{"id":this.userInfo.id}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userInfo = res.data.data
          window.localStorage.setItem('userInfo',JSON.stringify(res.data.data))
          console.log("账号信息已获取");
        } else {
          console.log("账号信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
      let poweruUrl = "http://yhocn.cn:8102/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          window.localStorage.setItem('userPower',JSON.stringify(res.data.data))
          console.log("权限信息已获取");
        } else {
          console.log("权限信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
  },
  mounted() {
    this.getPageTitle();
  }
}
</script>

<style>
:root {
  /* ui */
  --frame-bgcolor: #fff;

}

body {
  margin: 0;
  padding: 0;
  background-color: var(--frame-bgcolor);
}

/* 以下为代码测试区 */

</style>

<style scoped>
/* ui */
.custom-menu-item {
  height: 35px;
  line-height: 35px;
  margin-left: -10px;
  margin-right: -10px;
  padding-left: 20px;
  box-sizing: border-box;
  font-size: 15px;
}
/* ui */
.custom-menu-item:hover {
  color: #2b62b5;
  background-color: #fff;
}

/* 以上为代码测试区 */

.custom-header {
  margin: 0;
  padding: 0;
  /*width: 100vw;*/
  background-color: var(--frame-bgcolor);
  display: flex;
  justify-content: left;
  /*border-bottom: 1px solid #FFF;*/
}

.el-menu-demo {
  width: 100%;
  background-color: var(--frame-bgcolor);
}

/*.logo-box {*/
/*  background-color: var(--frame-bgcolor);*/
/*  height: 60px;*/
/*  width: 200px;*/
/*  background-color: #eeeeee;*/
/*}*/

.container-box {
  width: 95%;
  height: 85vh;
  margin: 0 auto;
  background-color: white;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  overflow: hidden;
  /* ui */
  border-bottom: 1px solid #57a8f5;
}

.inner-header {
  font-size: 20px;
  line-height: 40px;
  font-weight: 600;
  background-color: #F2F5F7;
  /*border-bottom: 1px solid lightgray;*/
  /* ui */
  background-image: linear-gradient(to left,#2b62b5, #4dc6f1);
  color: #fff;
  margin-top: 10px;
}

.view-box {
  /*background: none;*/
  /*width: 95%;*/
  /*margin: 20px auto 10px;*/
  height: calc(100% - 20px);
  /* ui */
  background-color: #f4f5f6;
  border-left: 1px solid #57a8f5;
  border-right: 1px solid #57a8f5;
}

.el-menu {
  border: 0 !important;
}

.bg-image{
  /**
  no-repeat:背景图片不平铺
  repeat:背景图片平铺
   */
  /* ui */
  background: url("../assets/shouye1.png") center fixed;
  background-size: cover;
}
/* ui */
.el-icon-switch-button,.el-icon-s-custom{
  color: #fff;
}
/deep/.el-table--border{
  border:1px solid #b9b9b9
}
/deep/.has-gutter{
  color:#000!important;
}
/deep/.has-gutter tr th{
  background:#d6e5ef!important;
  border-right: 1px solid #fff;
}
/deep/.el-table__body tbody tr td{
  border-right: 1px solid #d4d5d5;
  border-bottom: 1px solid #d4d5d5;
}
/deep/.el-submenu__title i{
  color: #ffffff;
}
/deep/.el-menu--horizontal.el-menu--popup{
  width: 100px!important;
}
/deep/th.el-table_2_column_23 is-hidden is-leaf el-table__cell{
  background:#d6e5ef!important;
}
</style>
