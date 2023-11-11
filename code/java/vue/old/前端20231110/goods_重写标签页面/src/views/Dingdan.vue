<template>
  <div>
    <el-container>
        <el-header>
          <el-container>采购订单</el-container>
          <el-container>
            <el-tabs>
              <el-tab-pane label="列表" name="first" tab-click="first"></el-tab-pane>
              <el-tab-pane label="明细" name="second" tab-click="second"></el-tab-pane>
            </el-tabs>
          </el-container>
        </el-header>
      <br />
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>

 
  </div>
</template>

<script>
export default {
    methods: {
        first(){
            this.$router.push("/dingdan")
        },
        second(){
            this.$router.push("/mingxi")
        },
    },

}
</script>

<style scoped>

.el-header {
  background-color: #afb4b9;
  color: #333;
  line-height: 60px;
  font-size: 20px;
  display: flex;
}
.spans {
  display: block;
  font-size: 20px;
}
</style>
