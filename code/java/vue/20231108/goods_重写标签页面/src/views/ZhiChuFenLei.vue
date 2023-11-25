<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
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
        <el-col :span="4">
          <el-date-picker
              style="width:100%"
              v-model="riqi"
              :type="type=='按日'?'month':'year'"
              :format="type=='按日'?'yyyy-MM':'yyyy'"
              placeholder="选择日期">
          </el-date-picker>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          ref="multipleTable"
          :data="type_list.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight"
          @selection-change="handleSelectionChange">
        <el-table-column
            v-for="column in title"
            :prop="column.columnName+ ''"
            :label="column.name"
            :width="column.width">
        </el-table-column>
      </el-table>
    </el-main>

    <el-footer style="height: 10%;margin-bottom: 5%">
      <el-pagination
          :currentPage="currentPage"
          :page-sizes="[10,20,30,40,50]"
          :page-size="pageSize"
          background
          layout="total, sizes, prev,pager,next,jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-footer>


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
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      title:[],
      type_list:[],
      riqi:getNowDate(),
      type:'按月',
      shouzhi_type:'支出',
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
      if(this.userPower.zhichuTongjiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.query();
    },

    getUser(){
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://user-20200618gm:8102/user/queryUserInfoById"
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
      let poweruUrl = "http://user-20200618gm:8102/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          if(this.userPower.zhichuTongjiSel == '是'){
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
      if(this.userPower.zhichuTongjiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var riqi = this.riqi
      if(riqi == ""){
        MessageUtil.error("请选择日期");
      }
      if(this.type == '按日'){
        riqi = riqi.split("-")[0] + "-" + riqi.split("-")[1]
      }else{
        riqi = riqi.split("-")[0]
      }
      var date = {
        riqi:riqi,
        type:this.type,
        shouzhi_type:this.shouzhi_type,
      }
      let url = "http://user-20200618gm:8102/shouZhiTongJI/getShouZhi"
      this.axios.post(url, date).then(res => {
        console.log(res)
        this.tableData = res.data.data;
        var type_list = [];
        var money_list = res.data.data
        var this_money = {}
        for(var i=0; i<money_list.length; i++){
          this_money[money_list[i].jizhangType] = ""
        }
        console.log(this_money)
        for(let i in this_money){
          type_list.push({
            jizhangType:i
          })
        }
        var title = []
        var type = this.type
        if(type == '按月'){
          title = [
            {
              name: "分类",
              text: "分类",
              width: "250",
              columnName: "jizhangType",
              type: "text",
            },
          ]
          console.log(title)
          title.push({
            name: "合计",
            text: "合计",
            width: "170",
            columnName: "sum",
            type: "text",
          })
          for(var i=1; i<=12; i++){
            title.push({
              name: i + "月",
              text: i + "月",
              width: "170rpx",
              columnName: i,
              type: "text",
            })
          }
          for(var i=0; i<type_list.length; i++){
            for(var j=money_list.length-1; j>=0; j--){
              if(type_list[i].jizhangType == money_list[j].jizhangType){
                var this_column = money_list[j].shouzhiRiqi.split("-")[1] * 1
                console.log(this_column)
                this_column = this_column + ""
                type_list[i][this_column] = money_list[j].money
                if(type_list[i].sum == undefined){
                  type_list[i].sum = money_list[j].money
                }else{
                  type_list[i].sum = (type_list[i].sum * 1) + (money_list[j].money * 1)
                }
                money_list.splice(j,1)
              }
            }
          }
        }else if(type == '按日'){
          title = [
            {
              name: "分类",
              text: "分类",
              width: "250rpx",
              columnName: "jizhangType",
              type: "text",
              isupd: true
            },
          ]
          console.log(title)
          title.push({
            name: "合计",
            text: "合计",
            width: "170rpx",
            columnName: "sum",
            type: "text",
            isupd: true
          })
          console.log(this.riqi)
          var this_day = getDays(this.riqi.split("-")[0] * 1,this.riqi.split("-")[1] * 1 -1)
          console.log(this_day)
          for(var i=1; i<=this_day; i++){
            title.push({
              text: i + "号",
              name: i + "号",
              width: "170rpx",
              columnName: i,
              type: "text",
              isupd: true
            })
          }
          for(var i=0; i<type_list.length; i++){
            for(var j=money_list.length-1; j>=0; j--){
              if(type_list[i].jizhangType == money_list[j].jizhangType){
                var this_column = money_list[j].shouzhiRiqi.split("-")[2] * 1
                console.log(this_column)
                this_column = this_column + ""
                type_list[i][this_column] = money_list[j].money
                if(type_list[i].sum == undefined){
                  type_list[i].sum = money_list[j].money
                }else{
                  type_list[i].sum = (type_list[i].sum * 1) + (money_list[j].money * 1)
                }
                money_list.splice(j,1)
              }
            }
          }
        }
        console.log(title)
        console.log(type_list)
        this.title = title
        this.type_list = type_list
        this.total = this.type_list.length;
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.currentPage = 1
      console.log(`每页 ${val} 条`);
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