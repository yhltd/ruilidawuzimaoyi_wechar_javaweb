<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="4">
          <el-date-picker
              style="width:100%"
              v-model="riqi"
              type="year"
              format="yyyy"
              placeholder="选择日期">
          </el-date-picker>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          ref="multipleTable"
          :data="type_list"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight"
          @selection-change="handleSelectionChange">
        <el-table-column
            v-for="column in title"
            :prop="column.columnName+ ''"
            :label="column.name"
            :width="auto">
        </el-table-column>
      </el-table>
    </el-main>

  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      title:[],
      type_list:[],
      riqi:getNowDate(),
      type:'按月',
      jizhangType:'收入',
      XiaLa_Type:[
        {
          name:'按日',
          label:'按日',
        },
        {
          name:'按月',
          label:'按月',
        }
      ],
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.riqi=getNowDate()
  },
  methods: {
    toggleSelection(rows) {
      console.log(rows)
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },


    //刷新
    refresh(){
      this.query()
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
          if(this.userPower.yueduTongjiSel == '是'){
            this.query();
          }else{
            MessageUtil.error("无查询权限");
          }
          window.localStorage.setItem('userPower',JSON.stringify(res.data.data))
          console.log("权限信息已获取");
        } else {
          console.log("权限信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //条件查询
    query(){
      if(this.userPower.yueduTongjiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var riqi = this.riqi
      if(riqi == ""){
        MessageUtil.error("请选择日期");
      }

      riqi = riqi.split("-")[0]
      var date = {
        riqi:riqi,
      }
      let url = "http://yhocn.cn:8102/shouZhiTongJI/getShouZhiByMonth"
      this.axios.post(url, date).then(res => {
        var title = [
              {
                name: "月份",
                text: "月份",
                width: "250rpx",
                columnName: "month",
                type: "text",
                isupd: true
              },
              {
                name: "收入",
                text: "收入",
                width: "250rpx",
                columnName: "shouru",
                type: "text",
                isupd: true
              },
              {
                name: "支出",
                text: "支出",
                width: "250rpx",
                columnName: "zhichu",
                type: "text",
                isupd: true
              },
              {
                name: "净收入",
                text: "净收入",
                width: "250rpx",
                columnName: "jingshouru",
                type: "text",
                isupd: true
              },
            ]
        var type_list = [
          { month:'1' },
          { month:'2' },
          { month:'3' },
          { month:'4' },
          { month:'5' },
          { month:'6' },
          { month:'7' },
          { month:'8' },
          { month:'9' },
          { month:'10' },
          { month:'11' },
          { month:'12' },
        ]
        var money_list = res.data.data;
        console.log(money_list)
        for(var i=0; i<money_list.length; i++){
          var this_month = money_list[i].shouzhiRiqi.split("-")[1] * 1
          console.log(this_month)
          if(money_list[i].jizhangType == '收入'){
            type_list[this_month -1].shouru = money_list[i].money
          }else if(money_list[i].jizhangType == '支出'){
            type_list[this_month -1].zhichu = money_list[i].money
          }
        }
        for(var i=0; i<type_list.length; i++){
          if(type_list[i].shouru == undefined){
            type_list[i].shouru = 0
          }
          if(type_list[i].zhichu == undefined){
            type_list[i].zhichu = 0
          }
          type_list[i].jingshouru = type_list[i].shouru - type_list[i].zhichu
        }
        console.log(type_list)
        this.type_list = type_list
        this.title = title
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
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>