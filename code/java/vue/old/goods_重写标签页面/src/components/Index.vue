<template>
  <div>
    <div
        class="custom-header"
    >
      <div class="logo-box"> LOGO 预留</div>

      <el-menu

          class="el-menu-demo"
          mode="horizontal"
          background-color="#000"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
      >
        <el-submenu index="1">
          <template slot="title">销售</template>
          <el-menu-item index="/1-1" @click="goRouter('/1-1')"><p class="custom-menu-item">选项1</p></el-menu-item>
          <el-menu-item @click="goRouter('/1-2')"><p class="custom-menu-item">选项2</p></el-menu-item>
          <el-menu-item @click="goRouter('/1-3')"><p class="custom-menu-item">选项3</p></el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">采购</template>
          <el-menu-item @click="goRouter('/dingdan')"><p class="custom-menu-item">采购订单</p></el-menu-item>
          <el-menu-item @click="goRouter('/2-2')"><p class="custom-menu-item">选项2</p></el-menu-item>
          <el-menu-item @click="goRouter('/2-3')"><p class="custom-menu-item">选项3</p></el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">库存</template>
          <el-menu-item @click="goRouter('/3-1')"><p class="custom-menu-item">选项1</p></el-menu-item>
          <el-menu-item @click="goRouter('/3-2')"><p class="custom-menu-item">选项2</p></el-menu-item>
          <el-menu-item @click="goRouter('/3-3')"><p class="custom-menu-item">选项3</p></el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">基础资料</template>
          <el-menu-item @click="goRouter('/userInfo')"><p class="custom-menu-item">账号管理</p></el-menu-item>
          <el-menu-item @click="goRouter('/powerManage')"><p class="custom-menu-item">权限管理</p></el-menu-item>
          <el-menu-item @click="goRouter('/user')"><p class="custom-menu-item">客户</p></el-menu-item>
          <el-menu-item @click="goRouter('/goods')"><p class="custom-menu-item">商品</p></el-menu-item>
          <el-menu-item @click="goRouter('/4-4')"><p class="custom-menu-item">供应商</p></el-menu-item>
          <el-menu-item @click="goRouter('/4-5')"><p class="custom-menu-item">仓库</p></el-menu-item>
          <el-menu-item @click="goRouter('/4-6')"><p class="custom-menu-item">店铺</p></el-menu-item>
          <el-menu-item @click="goRouter('/collAccount')"><p class="custom-menu-item">收款帐户</p></el-menu-item>
          <el-menu-item @click="goRouter('/accountingunit')"><p class="custom-menu-item">核算单位</p></el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
    <el-container>
      <el-main>
        <div class="container-box">
          <el-header height="40px" class="inner-header">{{ PageTitle || '错误' }}</el-header>
          <div class="view-box">
          <router-view></router-view>
          </div>
        </div>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import RouterUtil from "../utils/RouterU.js"

export default {
  data() {
    return {
      PageTitle: ''
    }
  },
  methods: {
    goRouter(url) {
      RouterUtil.to(url);
    },
    getPageTitle() {
      this.PageTitle = document.title
    }
  },
  mounted() {
    this.getPageTitle();
  }
}
</script>

<style>
:root{
  --frame-bgcolor: #000;

}
body {
  margin: 0;
  padding: 0;
  background-color: var(--frame-bgcolor);
}

/* 以下为代码测试区 */

</style>

<style scoped>
.custom-menu-item {
  height: 25px;
  line-height: 25px;
  margin: 0px;
  padding: 0;
}
.custom-menu-item:hover {
  color: #ffd04b;
}
/* 以上为代码测试区 */

.custom-header {
  margin: 0;
  padding: 0;
  width: 100vw;
  background-color: var(--frame-bgcolor);
  display: flex;
  justify-content: left;
  border-bottom: 1px solid #FFF;
}

.el-menu-demo {
  width: 100%;
  background-color: var(--frame-bgcolor);
}

.logo-box {
  background-color: var(--frame-bgcolor);
  height: 60px;
  width: 200px;
  background-color: #eeeeee;
}

.container-box {
  width: 95%;
  height: 89vh;
  margin: 0 auto;
  background-color: white;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  overflow: hidden;
}

.inner-header {
  font-size: 25px;
  line-height: 40px;
  font-weight: 600;
  background-color: white;
  border-bottom: 1px solid lightgray;
}

.view-box {
  background: none;
  width: 95%;
  margin: 20px auto 10px;
  height: calc(100% - 20px);
}
</style>
