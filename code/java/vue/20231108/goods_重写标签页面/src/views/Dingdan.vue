<template>
  <div>
    <el-container>
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
