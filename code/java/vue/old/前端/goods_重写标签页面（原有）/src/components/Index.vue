<template>
  <div>
    <el-menu

        class="el-menu-demo"
        mode="horizontal"

        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
    >
      <el-submenu index="1">
        <template slot="title">销售</template>
        <el-menu-item @click="goRouter('1-1')">选项1</el-menu-item>
        <el-menu-item @click="goRouter('1-2')">选项2</el-menu-item>
        <el-menu-item @click="goRouter('1-3')">选项3</el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">采购</template>
        <el-menu-item @click="goRouter('/dingdan')">采购订单</el-menu-item>
        <el-menu-item @click="goRouter('2-2')">选项2</el-menu-item>
        <el-menu-item @click="goRouter('2-3')">选项3</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">库存</template>
        <el-menu-item @click="goRouter('3-1')">选项1</el-menu-item>
        <el-menu-item @click="goRouter('3-2')">选项2</el-menu-item>
        <el-menu-item @click="goRouter('3-3')">选项3</el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">基础资料</template>
        <el-menu-item @click="goRouter('/userInfo')">账号管理</el-menu-item>
        <el-menu-item @click="goRouter('/powerManage')">权限管理</el-menu-item>
        <el-menu-item @click="goRouter('/user')">客户</el-menu-item>
        <el-menu-item @click="goRouter('/goods')">商品</el-menu-item>
        <el-menu-item @click="goRouter('4-4')">供应商</el-menu-item>
        <el-menu-item @click="goRouter('4-5')">仓库</el-menu-item>
        <el-menu-item @click="goRouter('4-6')">店铺</el-menu-item>
        <el-menu-item @click="goRouter('/collAccount')">收款帐户</el-menu-item>
        <el-menu-item @click="goRouter('/accountingunit')">核算单位</el-menu-item>
      </el-submenu>
    </el-menu>
    <el-container>
      <el-main>
        <el-header>{{ PageTitle || '错误' }}</el-header>
        <br/>
        <router-view></router-view>
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

<style></style>
