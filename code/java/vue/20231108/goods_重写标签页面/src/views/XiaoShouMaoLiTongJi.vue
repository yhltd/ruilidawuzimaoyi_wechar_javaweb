<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="4">
          <el-date-picker
              style="width:100%"
              v-model="start_date"
              type="date"
              format="yyyy-MM-dd"
              placeholder="选择起始日期">
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-date-picker
              style="width:100%"
              v-model="stop_date"
              type="date"
              format="yyyy-MM-dd"
              placeholder="选择截止日期">
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-select v-model="type" filterable placeholder="选择统计类型">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_Type"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
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
      start_date:"",
      stop_date:"",
      type:'按出货单',
      XiaLa_Type:[
        {
          name:'按出货单',
          label:'按出货单',
        },
        {
          name:'按产品',
          label:'按产品',
        },
        {
          name:'按客户',
          label:'按客户',
        },
        {
          name:'按业务员',
          label:'按业务员',
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
          this.query();
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
    async query(){
      console.log(this.start_date)
      console.log(this.stop_date)
      var start_date = this.start_date
      var stop_date = this.stop_date
      var type = this.type
      if(type == '' || type == null){
        MessageUtil.error("请选择汇总条件");
        return;
      }
      if(start_date == "" || start_date == null){
        start_date = "1900-01-01"
      }

      if(stop_date == "" || stop_date == null){
        stop_date = "2100-12-31"
      }

      var date = {
        start_date:start_date,
        stop_date:stop_date,
      }
      let url = ""
      if(type == '按出货单'){
        url = "http://yhocn.cn:8102/yiBiaoPan/xiaoShouMaoLiTongJi_ChuHuoDan"
      }else if(type == '按产品'){
        url = "http://yhocn.cn:8102/yiBiaoPan/xiaoShouMaoLiTongJi_ChanPin"
      }else if(type == '按客户'){
        url = "http://yhocn.cn:8102/yiBiaoPan/xiaoShouMaoLiTongJi_KeHu"
      }else if(type == '按业务员'){
        url = "http://yhocn.cn:8102/yiBiaoPan/xiaoShouMaoLiTongJi_YeWuYuan"
      }

      await this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          var title = [
            {
              name: type,
              text: type,
              width: "250rpx",
              columnName: "name",
              type: "text",
              isupd: true
            },
            {
              name: "销售金额",
              text: "销售金额",
              width: "250rpx",
              columnName: "xiaoshou",
              type: "text",
              isupd: true
            },
            {
              name: "成本金额",
              text: "成本金额",
              width: "250rpx",
              columnName: "chengben",
              type: "text",
              isupd: true
            },
            {
              name: "毛利金额",
              text: "毛利金额",
              width: "250rpx",
              columnName: "maoli",
              type: "text",
              isupd: true
            },
          ]
          this.title = title
          this.type_list = res.data.data;
          console.log(res.data.data)

          MessageUtil.success("共查询到" + this.type_list.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
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
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>