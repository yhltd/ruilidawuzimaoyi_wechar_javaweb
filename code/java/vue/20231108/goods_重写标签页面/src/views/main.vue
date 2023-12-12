<template>
  <el-container style="height: 100%;" direction="vertical" class="bg-image">

<!--    <div class="row justify-content-center align-content-center h-100" style="padding-top:15%">-->
<!--      <div class="col-md-5" style="text-align: center;font-size: 4rem;color: #525258;white-space: nowrap">-->
<!--        欢迎使用进销存系统-->
<!--      </div>-->
<!--    </div>-->

    <el-main refs="main" style="height: 50%;">
      <div style="display: flex;margin-top:20px" >
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold">新增销售数据</span>
            <div style="float: right;">
              <el-button type="text">今日</el-button>
              <el-button type="text">本月</el-button>
              <el-button type="text">本年</el-button>
            </div>

          </div>
          <div class="text item">
            <div style="display: flex">
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>订单</p>
                  <p style="font-size: 30px;margin-top:10px">0</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>出库</p>
                  <p style="font-size: 30px;margin-top:10px">0</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>收款</p>
                  <p style="font-size: 30px;margin-top:10px">0</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>毛利</p>
                  <p style="font-size: 30px;margin-top:10px">0</p>
                </span>
              </el-card>

            </div>
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>客户欠款排行</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div id="kehu_qiankuan_paihang">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </div>

      <div style="display: flex;margin-top:20px">
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </div>

      <div style="display: flex;margin-top:20px;margin-bottom:50px;">
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </div>

    </el-main>



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