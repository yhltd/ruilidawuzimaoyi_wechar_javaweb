<template>
  <el-container style="height: 100%;" direction="vertical" class="bg-image">

<!--    <div class="row justify-content-center align-content-center h-100" style="padding-top:15%">-->
<!--      <div class="col-md-5" style="text-align: center;font-size: 4rem;color: #525258;white-space: nowrap">-->
<!--        欢迎使用进销存系统-->
<!--      </div>-->
<!--    </div>-->
<!--    ztt 导航栏快速跳转未审核-->
    <div>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick" stretch>
      <el-tab-pane name="first" label="销售报价" @click="getXiaoShouBaoJiaShenHe">
        <div class="shenHeShuLiang" style="display: flex;justify-content: space-between">
          <span @click="gotoXiaoShouBaoJia('/xiaoshoubaojia','baojiashenhegeshu')">需要我审核：{{ this.tableData.length }}个</span>
          <span></span>
        </div>
      </el-tab-pane>
      <el-tab-pane label="销售订单" @click="getXiaoShouDingDanShenHe" >
        <div class="shenHeShuLiang" style="display: flex;justify-content: space-between">
          <span @click="gotoXiaoShouDingDan('/xiaoshoudingdan','xiaoshoudingdanshenhegeshu')">需要我审核：{{ this.tableData.length }}个</span>
          <span></span>
        </div>
      </el-tab-pane>
      <el-tab-pane label="采购订单" @click="getCaiGouDingDanShenHe">
        <div class="shenHeShuLiang" style="display: flex;justify-content: space-between">
          <span @click="gotoCaiGouDingDan('/mingxi','caigoudingdangeshu')">需要我审核：{{ this.tableData.length }}个</span>
          <span></span>
        </div>
      </el-tab-pane>
      <el-tab-pane label="销售开票" @click="getXiaoShouKaiPiaoShenHe">
        <div class="shenHeShuLiang" style="display: flex;justify-content: space-between">
          <span @click="gotoXiaoShouKaiPiao('/xiaoshoukaipiao','xiaoshoukaipiaogeshu')">需要我开票：{{ this.tableData.length }}个</span>
          <span></span>
        </div>
      </el-tab-pane>
      <el-tab-pane label="采购收票" @click="getCaiGouShouPiaoShenHe">
        <div class="shenHeShuLiang" style="display: flex;justify-content: space-between">
          <span @click="gotoCaiGouShouPiao('/caigoushoupiao','caigoushoupiaogeshu')">需要我收票：{{ this.tableData.length }}个</span>
          <span></span>
        </div>
      </el-tab-pane>
    </el-tabs>
    </div>
    <!--            ztt end-->
    <el-main refs="main" style="height: 50%;">
      <div style="display: flex;margin-top:20px" >
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span style="font-weight: bold">新增销售数据</span>
            <div style="float: right;">
              <el-button @click="XinZengXiaoShou_Day()" type="text">今日</el-button>
              <el-button @click="XinZengXiaoShou_Month()" type="text">本月</el-button>
              <el-button @click="XinZengXiaoShou_Year()" type="text">本年</el-button>
            </div>

          </div>
          <div class="text item">
            <div style="display: flex">
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>订单</p>
                  <p style="font-size: 10px;margin-top:10px">{{dingdan}}</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>出库</p>
                  <p style="font-size: 10px;margin-top:10px">{{chuku}}</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>收款</p>
                  <p style="font-size: 10px;margin-top:10px">{{shoukuan}}</p>
                </span>
              </el-card>
              <el-card shadow="hover" style="margin:auto;border:0">
                <span style="text-align: center">
                  <p>毛利</p>
                  <p style="font-size: 10px;margin-top:10px">{{maoli}}</p>
                </span>
              </el-card>

            </div>
          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>客户欠款排行</span>
            <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
          </div>
          <div id="kehu_qiankuan" style="width: 100%; height: 100%">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>供应商欠款排行</span>
            <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
          </div>
          <div id="gongyingshang_qiankuan" style="width: 100%; height: 100%">

          </div>
        </el-card>
      </div>

      <div style="display: flex;margin-top:20px">
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>销售订单排行</span>
            <div style="float: right;">
              <el-button @click="xiaoshou_tiaojian_click()" type="text">{{xiaoshou_tiaojian}}</el-button>
              <el-button @click="xiaoshou_dingdan_paihang_Month()" type="text">本月</el-button>
              <el-button @click="xiaoshou_dingdan_paihang_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="xiaoshou_dingdan" style="width: 100%; height: 100%">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>销售收款排行</span>
            <div style="float: right;">
              <el-button @click="xiaoshou_shoukuan_tiaojian_click()" type="text">{{xiaoshou_shoukuan_tiaojian}}</el-button>
              <el-button @click="xiaoshou_shoukuan_paihang_Month()" type="text">本月</el-button>
              <el-button @click="xiaoshou_shoukuan_paihang_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="xiaoshou_shoukuan" style="width: 100%; height: 100%">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>销售毛利排行</span>
            <div style="float: right;">
              <el-button @click="xiaoshou_maoli_tiaojian_click()" type="text">{{xiaoshou_maoli_tiaojian}}</el-button>
              <el-button @click="xiaoshou_maoli_paihang_Month()" type="text">本月</el-button>
              <el-button @click="xiaoshou_maoli_paihang_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="xiaoshou_maoli" style="width: 100%; height: 100%">

          </div>
        </el-card>
      </div>



      <div style="display: flex;margin-top:20px;margin-bottom:50px;">
        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>销售订单走势</span>
            <div style="float: right;">
              <el-button @click="xiaoshou_dingdan_zoushi_Month()" type="text">本月</el-button>
              <el-button @click="xiaoshou_dingdan_zoushi_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="dingdan_zoushi" style="width: 100%; height: 100%">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>销售收款走势</span>
            <div style="float: right;">
              <el-button @click="xiaoshou_shoukuan_zoushi_Month()" type="text">本月</el-button>
              <el-button @click="xiaoshou_shoukuan_zoushi_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="shoukuan_zoushi" style="width: 100%; height: 100%">

          </div>
        </el-card>

        <el-card class="box-card" style="width:30%;height:250px;margin: auto">
          <div slot="header" class="clearfix">
            <span>采购付款走势</span>
            <div style="float: right;">
              <el-button @click="caigou_fukuan_zoushi_Month()" type="text">本月</el-button>
              <el-button @click="caigou_fukuan_zoushi_Year()" type="text">本年</el-button>
              <el-button @click="" type="text">更多</el-button>
            </div>
          </div>
          <div id="fukuan_zoushi" style="width: 100%; height: 100%">

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

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import * as  echarts from 'echarts';
// ztt
import RouterUtil from "../utils/RouterU.js"
import xiaoShouBaoJia from "@/views/XiaoShouBaoJia.vue";
// end
export default {
  data() {
    return {
      dingdan:0,
      chuku:0,
      shoukuan:0,
      maoli:0,
      xiaoshou_tiaojian:"按业务员",
      xiaoshou_shoukuan_tiaojian:"按业务员",
      xiaoshou_maoli_tiaojian:"按业务员",
      // ztt
      activeName: 'first',
      tableData:'',
      userinfo:'',
      targetPagePath:'/xiaoshoubaojia',
      // end
    }
  },
  created() {
    this.XinZengXiaoShou_Day();
    // ztt
    this.getUser();
    this.getXiaoShouBaoJiaShenHe()
  },
  mounted(){
    this.KeHuQianKuan_refresh()
    this.GongYingShangQianKuan_refresh()
    this.xiaoshou_dingdan_paihang_Month()
    this.xiaoshou_shoukuan_paihang_Month()
    this.xiaoshou_maoli_paihang_Month()
    this.xiaoshou_dingdan_zoushi_Month()
    this.xiaoshou_shoukuan_zoushi_Month()
    this.caigou_fukuan_zoushi_Month()
  },
  methods: {
    //ztt
    handleClick(tab,event){
      console.log(tab,event)
      if (tab.index == "0"){
        this.getXiaoShouBaoJiaShenHe();
      } else if(tab.index == "1"){
        this.getXiaoShouDingDanShenHe();
      } else if(tab.index == "2"){
        this.getCaiGouDingDanShenHe();
      } else if(tab.index == "3"){
        this.getXiaoShouKaiPiaoShenHe();
      } else if(tab.index == "4"){

      }
    },
    gotoXiaoShouBaoJia(url,shuliang){
      var shuliang=this.tableData.length
      RouterUtil.to(url + '?baojiashenhegeshu=' +shuliang)
    },
    gotoXiaoShouDingDan(url, shuliang){
      var shuliang=this.tableData.length
      RouterUtil.to(url + '?xiaoshoudingdanshenhegeshu=' + shuliang)
    },
    gotoCaiGouDingDan(url, shuliang){
      var shuliang=this.tableData.length
      RouterUtil.to(url + '?caigoudingdangeshu=' + shuliang)
    },
    gotoXiaoShouKaiPiao(url, shuliang){
      var shuliang=this.tableData.length
      RouterUtil.to(url + '?xiaoshoukaipiaogeshu=' + shuliang)
    },
    gotoCaiGouShouPiao(url, shuliang){
      var shuliang=this.tableData.length
      console.log("kaishi")
      console.log(shuliang)
      RouterUtil.to(url + '?caigoushoupiaogeshu=' + shuliang)
    },
    getXiaoShouBaoJiaShenHe(){
      let url = "http://localhost:8102/xiaoShouBaoJia/shenheList"
      this.axios.post(url, {"name":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaoShouDingDanShenHe(){
      let url = "http://localhost:8102/xiaoShouDingDan/shenheList"
      this.axios.post(url, {"name":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getCaiGouDingDanShenHe(){
      let url = "http://localhost:8102/caiGouDingDan/getShenHe"
      this.axios.post(url, {"shenhe":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaoShouKaiPiaoShenHe(){
      let url = "http://localhost:8102/kaiPiao/getKaiPiao"
      this.axios.post(url, {"xinxi_tuisong":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getCaiGouShouPiaoShenHe(){
      let url = "http://localhost:8102/shouPiao/getShouPiao"
      this.axios.post(url, {"xinxi_tuisong":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getUser(){
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://localhost:8102/user/queryUserInfoById"
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
    },
    //end
    XinZengXiaoShou_Day(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      var stop_date = start_date
      var date = {
        start_date:start_date,
        stop_date:stop_date
      }
      let url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouDingDanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.dingdan = res.data.data[0].dingdan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouChuKuNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.chuku = res.data.data[0].chuku
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouShouKuanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.shoukuan = res.data.data[0].shoukuan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })


      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouMaoLiNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.maoli = res.data.data[0].maoli
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

    },

    XinZengXiaoShou_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      var date = {
        start_date:start_date,
        stop_date:stop_date
      }


      let url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouDingDanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.dingdan = res.data.data[0].dingdan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouChuKuNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.chuku = res.data.data[0].chuku
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouShouKuanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.shoukuan = res.data.data[0].shoukuan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })


      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouMaoLiNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.maoli = res.data.data[0].maoli
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    XinZengXiaoShou_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      var date = {
        start_date:start_date,
        stop_date:stop_date
      }


      let url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouDingDanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.dingdan = res.data.data[0].dingdan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouChuKuNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.chuku = res.data.data[0].chuku
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouShouKuanNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.shoukuan = res.data.data[0].shoukuan
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })


      url = "http://localhost:8102/yiBiaoPan/xinZengXiaoShouMaoLiNum"
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.maoli = res.data.data[0].maoli
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    KeHuQianKuan_refresh(){
      let getchart = echarts.init(document.getElementById('kehu_qiankuan'));
      let url = "http://localhost:8102/yiBiaoPan/keHuQianKuanPaiHang"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          var x_list = []
          var y_list = []
          for(var i=0; i<list.length; i++){
            y_list.push(list[i].kehu)
            x_list.push(Math.round(list[i].qiankuan * 100) / 100)
          }
          let getchart = echarts.init(document.getElementById('kehu_qiankuan'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01],
              axisTick:{
                show:false // 不显示坐标轴刻度线
              },
              axisLine: {
                show: false, // 不显示坐标轴线
              },
              axisLabel: {
                show: false, // 不显示坐标轴上的文字
              },
              splitLine:{
                show:false // 不显示网格线
              },
            },
            yAxis: {
              type: 'category',
              data: y_list
            },
            series: [
              {
                name: '欠款',
                type: 'bar',
                data: x_list,
                label: {
                  show: true,
                  position: 'right'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    GongYingShangQianKuan_refresh(){
      let getchart = echarts.init(document.getElementById('gongyingshang_qiankuan'));
      let url = "http://localhost:8102/yiBiaoPan/gongYingShangQianKuanPaiHang"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          var x_list = []
          var y_list = []
          for(var i=0; i<list.length; i++){
            y_list.push(list[i].gongyingshang)
            x_list.push(Math.round(list[i].qiankuan * 100) / 100)
          }
          let getchart = echarts.init(document.getElementById('gongyingshang_qiankuan'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01],
              axisTick:{
                show:false // 不显示坐标轴刻度线
              },
              axisLine: {
                show: false, // 不显示坐标轴线
              },
              axisLabel: {
                show: false, // 不显示坐标轴上的文字
              },
              splitLine:{
                show:false // 不显示网格线
              },
            },
            yAxis: {
              type: 'category',
              data: y_list
            },
            series: [
              {
                name: '欠款',
                type: 'bar',
                data: x_list,
                label: {
                  show: true,
                  position: 'right'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    xiaoshou_tiaojian_click(){
      if(this.xiaoshou_tiaojian == '按业务员'){
        this.xiaoshou_tiaojian = '按客户'
      }else if(this.xiaoshou_tiaojian == '按客户'){
        this.xiaoshou_tiaojian = '按商品'
      }else if(this.xiaoshou_tiaojian == '按商品'){
        this.xiaoshou_tiaojian = '按业务员'
      }
    },

    xiaoshou_dingdan_paihang_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      this.xiaoshou_dingdan_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_dingdan_paihang_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      this.xiaoshou_dingdan_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_dingdan_paihang_refresh(start_date,stop_date){
      console.log(start_date)
      console.log(stop_date)
      var type = this.xiaoshou_tiaojian
      var url = ""
      if(type == '按业务员'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouDingDanPaiHang_YeWuYuan"
      }else if(type == '按客户'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouDingDanPaiHang_KeHu"
      }else if(type == '按商品'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouDingDanPaiHang_ShangPin"
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          var x_list = []
          var y_list = []
          for(var i=0; i<list.length; i++){
            y_list.push(list[i].name)
            x_list.push(Math.round(list[i].money* 100) / 100)
          }
          let getchart = echarts.init(document.getElementById('xiaoshou_dingdan'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01],
              axisTick:{
                show:false // 不显示坐标轴刻度线
              },
              axisLine: {
                show: false, // 不显示坐标轴线
              },
              axisLabel: {
                show: false, // 不显示坐标轴上的文字
              },
              splitLine:{
                show:false // 不显示网格线
              },
            },
            yAxis: {
              type: 'category',
              data: y_list
            },
            series: [
              {
                name: '金额',
                type: 'bar',
                data: x_list,
                label: {
                  show: true,
                  position: 'right'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    xiaoshou_shoukuan_tiaojian_click(){
      if(this.xiaoshou_shoukuan_tiaojian == '按业务员'){
        this.xiaoshou_shoukuan_tiaojian = '按客户'
      }else if(this.xiaoshou_shoukuan_tiaojian == '按客户'){
        this.xiaoshou_shoukuan_tiaojian = '按业务员'
      }
    },

    xiaoshou_shoukuan_paihang_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      this.xiaoshou_shoukuan_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_shoukuan_paihang_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      this.xiaoshou_shoukuan_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_shoukuan_paihang_refresh(start_date,stop_date){
      console.log(start_date)
      console.log(stop_date)
      var type = this.xiaoshou_shoukuan_tiaojian
      var url = ""
      if(type == '按业务员'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouShouKuanPaiHang_YeWuYuan"
      }else if(type == '按客户'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouShouKuanPaiHang_KeHu"
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          var x_list = []
          var y_list = []
          for(var i=0; i<list.length; i++){
            y_list.push(list[i].name)
            x_list.push(Math.round(list[i].money * 100) / 100)
          }
          let getchart = echarts.init(document.getElementById('xiaoshou_shoukuan'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01],
              axisTick:{
                show:false // 不显示坐标轴刻度线
              },
              axisLine: {
                show: false, // 不显示坐标轴线
              },
              axisLabel: {
                show: false, // 不显示坐标轴上的文字
              },
              splitLine:{
                show:false // 不显示网格线
              },
            },
            yAxis: {
              type: 'category',
              data: y_list
            },
            series: [
              {
                name: '金额',
                type: 'bar',
                data: x_list,
                label: {
                  show: true,
                  position: 'right'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    xiaoshou_maoli_tiaojian_click(){
      if(this.xiaoshou_maoli_tiaojian == '按业务员'){
        this.xiaoshou_maoli_tiaojian = '按客户'
      }else if(this.xiaoshou_maoli_tiaojian == '按客户'){
        this.xiaoshou_maoli_tiaojian = '按商品'
      }else if(this.xiaoshou_maoli_tiaojian == '按商品'){
        this.xiaoshou_maoli_tiaojian = '按业务员'
      }
    },

    xiaoshou_maoli_paihang_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      this.xiaoshou_maoli_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_maoli_paihang_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      this.xiaoshou_maoli_paihang_refresh(start_date,stop_date)
    },

    xiaoshou_maoli_paihang_refresh(start_date,stop_date){
      console.log(start_date)
      console.log(stop_date)
      var type = this.xiaoshou_maoli_tiaojian
      var url = ""
      if(type == '按业务员'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouMaoLiPaiHang_YeWuYuan"
      }else if(type == '按客户'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouMaoLiPaiHang_KeHu"
      }else if(type == '按商品'){
        url = "http://localhost:8102/yiBiaoPan/xiaoShouMaoLiPaiHang_ShangPin"
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          var x_list = []
          var y_list = []
          for(var i=0; i<list.length; i++){
            y_list.push(list[i].name)
            x_list.push(Math.round(list[i].money * 100) / 100)
          }
          let getchart = echarts.init(document.getElementById('xiaoshou_maoli'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01],
              axisTick:{
                show:false // 不显示坐标轴刻度线
              },
              axisLine: {
                show: false, // 不显示坐标轴线
              },
              axisLabel: {
                show: false, // 不显示坐标轴上的文字
              },
              splitLine:{
                show:false // 不显示网格线
              },
            },
            yAxis: {
              type: 'category',
              data: y_list
            },
            series: [
              {
                name: '金额',
                type: 'bar',
                data: x_list,
                label: {
                  show: true,
                  position: 'right'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    xiaoshou_dingdan_zoushi_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(stop_date)
      var url = "http://localhost:8102/yiBiaoPan/xiaoShouDingDanZouShi_AnYue"
      var x_list = []
      var y_list = []
      var stop_day = stop_date.split("-")[2] * 1
      for(var i=1; i<=stop_day; i++){
        x_list.push(i + '号')
        y_list.push(0)
      }
      this.xiaoshou_dingdan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    xiaoshou_dingdan_zoushi_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      var url = "http://localhost:8102/yiBiaoPan/xiaoShouDingDanZouShi_AnNian"
      var stop_day = stop_date.split("-")[2] * 1
      var x_list = []
      var y_list = []
      for(var i=1; i<=12; i++){
        x_list.push(i + '月')
        y_list.push(0)
      }
      this.xiaoshou_dingdan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    xiaoshou_dingdan_zoushi_refresh(start_date,stop_date,url,x_list,y_list){
      console.log(start_date)
      console.log(stop_date)
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          for(var i=0; i<list.length; i++){
            var index = (list[i].riqi * 1) - 1
            y_list[index] = Math.round(list[i].money * 100) / 100
          }
          let getchart = echarts.init(document.getElementById('dingdan_zoushi'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'category',
              data: x_list
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                name: '金额',
                type: 'line',
                data: y_list,
                smooth: true,
                label: {
                  show: true,
                  position: 'top'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    xiaoshou_shoukuan_zoushi_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(stop_date)
      var url = "http://localhost:8102/yiBiaoPan/xiaoShouShouKuanZouShi_AnYue"
      var x_list = []
      var y_list = []
      var stop_day = stop_date.split("-")[2] * 1
      for(var i=1; i<=stop_day; i++){
        x_list.push(i + '号')
        y_list.push(0)
      }
      this.xiaoshou_shoukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    xiaoshou_shoukuan_zoushi_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      var url = "http://localhost:8102/yiBiaoPan/xiaoShouShouKuanZouShi_AnNian"
      var stop_day = stop_date.split("-")[2] * 1
      var x_list = []
      var y_list = []
      for(var i=1; i<=12; i++){
        x_list.push(i + '月')
        y_list.push(0)
      }
      this.xiaoshou_shoukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    xiaoshou_shoukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list){
      console.log(start_date)
      console.log(stop_date)
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          for(var i=0; i<list.length; i++){
            var index = (list[i].riqi * 1) - 1
            y_list[index] = Math.round(list[i].money * 100) / 100
          }
          let getchart = echarts.init(document.getElementById('shoukuan_zoushi'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'category',
              data: x_list
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                name: '金额',
                type: 'line',
                data: y_list,
                smooth: true,
                label: {
                  show: true,
                  position: 'top'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    caigou_fukuan_zoushi_Month(){
      var firstDayOfMonth = new Date();
      firstDayOfMonth.setDate(1);
      var start_date = firstDayOfMonth.getFullYear() + '-' + (firstDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + firstDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(start_date)
      // 获取本月最后一天
      var lastDayOfMonth = new Date(firstDayOfMonth.getFullYear(), firstDayOfMonth.getMonth() + 1, 0);
      var stop_date = lastDayOfMonth.getFullYear() + '-' + (lastDayOfMonth.getMonth() + 1).toString().padStart(2, '0') + '-' + lastDayOfMonth.getDate().toString().padStart(2, '0');
      console.log(stop_date)
      var url = "http://localhost:8102/yiBiaoPan/caiGouFuKuanZouShi_AnYue"
      var x_list = []
      var y_list = []
      var stop_day = stop_date.split("-")[2] * 1
      for(var i=1; i<=stop_day; i++){
        x_list.push(i + '号')
        y_list.push(0)
      }
      this.caigou_fukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    caigou_fukuan_zoushi_Year(){
      var firstDayOfMonth = new Date();
      var start_date = firstDayOfMonth.getFullYear() + '-01-01'
      var stop_date = firstDayOfMonth.getFullYear() + '-12-31'
      var url = "http://localhost:8102/yiBiaoPan/caiGouFuKuanZouShi_AnNian"
      var stop_day = stop_date.split("-")[2] * 1
      var x_list = []
      var y_list = []
      for(var i=1; i<=12; i++){
        x_list.push(i + '月')
        y_list.push(0)
      }
      this.caigou_fukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list)
    },

    caigou_fukuan_zoushi_refresh(start_date,stop_date,url,x_list,y_list){
      console.log(start_date)
      console.log(stop_date)
      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      this.axios.post(url,date).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var list = res.data.data
          for(var i=0; i<list.length; i++){
            var index = (list[i].riqi * 1) - 1
            y_list[index] = Math.round(list[i].money * 100) / 100
          }
          let getchart = echarts.init(document.getElementById('fukuan_zoushi'));
          var option = {
            grid: {
              left: '5%',
              right: '15%',
              top: '20',
              bottom: '20',
              containLabel: true
            },
            xAxis: {
              type: 'category',
              data: x_list
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                name: '金额',
                type: 'line',
                data: y_list,
                smooth: true,
                label: {
                  show: true,
                  position: 'top'
                },
              },
            ]
          };
          getchart.setOption(option);
          console.log("新增销售订单信息已获取");
        } else {
          console.log("新增销售订单信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
  }
}

function getNowDate() {
  var date = new Date();
  var sign1 = "-";
  var sign2 = ":";
  var year = date.getFullYear() // 年
  var month = date.getMonth() + 1; // 月
  var day  = date.getDate(); // 日
  var hour = date.getHours(); // 时
  var minutes = date.getMinutes(); // 分
  var seconds = date.getSeconds() //秒
  var weekArr = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'];
  var week = weekArr[date.getDay()];
  // 给一位数数据前面加 “0”
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (day >= 0 && day <= 9) {
    day = "0" + day;
  }
  if (hour >= 0 && hour <= 9) {
    hour = "0" + hour;
  }
  if (minutes >= 0 && minutes <= 9) {
    minutes = "0" + minutes;
  }
  if (seconds >= 0 && seconds <= 9) {
    seconds = "0" + seconds;
  }
  // var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds + " " + week;
  var currentdate = year + sign1 + month + sign1 + day;
  return currentdate;
}

function getDays(year, month) {
  let days = [31,28,31,30,31,30,31,31,30,31,30,31]
  if ( (year % 4 ===0) && (year % 100 !==0 || year % 400 ===0) ) {
    days[1] = 29
  }
  return days[month]
}

</script>