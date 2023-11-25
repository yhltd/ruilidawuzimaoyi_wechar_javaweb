<template>
  <el-container style="height: 100%;" direction="vertical" class="bg-image">

    <div class="row justify-content-center align-content-center h-100" style="padding-top:15%">
      <div class="col-md-5" style="text-align: center;font-size: 4rem;color: #525258;white-space: nowrap">
        欢迎使用进销存系统
      </div>
    </div>

  </el-container>

</template>


<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
.el-table .hidden-row {
  display: none;
}

.bg-image{
  /**
  no-repeat:背景图片不平铺
  repeat:背景图片平铺
   */
  background: url("../assets/backgroundImage.jpg") center fixed;
  background-size: cover;
}
</style>