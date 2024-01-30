<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-date-picker
              style="width:100%"
              v-model="start_date"
              type="date"
              format="yyyy-MM-dd"
              placeholder="起始日期">
          </el-date-picker>
        </el-col>
        <el-col :span="3">
          <el-date-picker
              style="width:100%"
              v-model="stop_date"
              type="date"
              format="yyyy-MM-dd"
              placeholder="截止日期">
          </el-date-picker>
        </el-col>
        <el-col :span="3">
          <el-select v-model="dianpu" clearable filterable placeholder="选择店铺">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_DianPu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="jizhang_danwei" clearable filterable placeholder="记账单位">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_JiZhangDanWei"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="jizhang_zhanghu" clearable filterable placeholder="记账账户">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_JiZhangZhangHu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          ref="multipleTable"
          :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight">
        <el-table-column
            prop="shouzhiRiqi"
            label="日期"
            width="120">
        </el-table-column>
        <el-table-column
            prop="jizhangType"
            label="记账分类"
            width="120">
        </el-table-column>
        <el-table-column
            prop="shoufuType"
            label="记账明细"
            width="120"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shouru"
            label="收入金额"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="zhichu"
            label="支出金额"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="yue"
            label="账户余额"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="dianpu"
            label="店铺"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="jizhangDanwei"
            label="记账单位"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="jizhangZhanghu"
            label="记账账户"
            width="200"
            show-overflow-tooltip>
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
      start_date:'',
      stop_date:'',
      dianpu:'',
      jizhang_danwei:'',
      jizhang_zhanghu:'',
      XiaLa_DianPu:[],
      XiaLa_JiZhangDanWei:[],
      XiaLa_JiZhangZhangHu:[],
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_DianPu();
    this.getXiaLa_JiZhangDanWei();
    this.getXiaLa_JiZhangZhangHu();
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


    getXiaLa_DianPu(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"店铺"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_DianPu = res.data.data;
          for(var i=0; i<this.XiaLa_DianPu.length; i++){
            this.XiaLa_DianPu[i].label = this.XiaLa_DianPu.name
          }
          console.log("店铺下拉已获取");
        } else {
          console.log("店铺下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_JiZhangDanWei(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"核算单位"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_JiZhangDanWei = res.data.data;
          for(var i=0; i<this.XiaLa_JiZhangDanWei.length; i++){
            this.XiaLa_JiZhangDanWei[i].label = this.XiaLa_JiZhangDanWei.name
          }
          console.log("记账单位下拉已获取");
        } else {
          console.log("记账单位下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_JiZhangZhangHu(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"收款账户"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_JiZhangZhangHu = res.data.data;
          for(var i=0; i<this.XiaLa_JiZhangZhangHu.length; i++){
            this.XiaLa_JiZhangZhangHu[i].label = this.XiaLa_JiZhangZhangHu.name
          }
          console.log("记账账户下拉已获取");
        } else {
          console.log("记账账户下拉获取失败");
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
          this.getAll();
          window.localStorage.setItem('userPower',JSON.stringify(res.data.data))
          console.log("权限信息已获取");
        } else {
          console.log("权限信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      let url = "http://yhocn.cn:8102/jiZhang/getJiZhang"
      this.axios.post(url, {
        'start_date':'1900-01-01',
        'stop_date':'2100-12-31',
        'dianpu':'',
        'jizhang_danwei':'',
        'jizhang_zhanghu':'',
      }).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          var list = this.tableData
          var yue = 0
          for(var i=list.length-1; i>=0; i--){
            yue = Math.round((yue * 1 + list[i].shouru * 1 - list[i].zhichu * 1) * 100) / 100
            yue = yue * 1
            yue = yue.toFixed(2)
            list[i].yue = yue
          }
          this.tableData = list
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //刷新
    refresh(){
      this.getAll()
    },

    //条件查询
    query(){
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == '' || start_date == null){
        start_date = '1900-01-01'
      }
      if(stop_date == '' || stop_date == null){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        dianpu:this.dianpu,
        jizhang_danwei:this.jizhang_danwei,
        jizhang_zhanghu:this.jizhang_zhanghu,
      }
      let url = "http://yhocn.cn:8102/jiZhang/getJiZhang"
      this.axios.post(url, {
        'start_date':'1900-01-01',
        'stop_date':'2100-12-31',
        'dianpu':'',
        'jizhang_danwei':'',
        'jizhang_zhanghu':'',
      }).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          var list = this.tableData
          var yue = 0
          for(var i=list.length-1; i>=0; i--){
            yue = Math.round((yue * 1 + list[i].shouru * 1 - list[i].zhichu * 1) * 100) / 100
            yue = yue * 1
            yue = yue.toFixed(2)
            list[i].yue = yue

            var panduan = 1
            if(list[i].shouzhiRiqi >= date.start_date && list[i].shouzhiRiqi <= date.stop_date){

            }else{
              panduan = 0
            }

            if(date.dianpu != ''){
              if(list[i].dianpu.indexOf(date.dianpu) == -1 || list[i].dianpu == ''){
                panduan = 0
              }
            }

            if(date.jizhang_danwei != ''){
              if(list[i].jizhangDanwei.indexOf(date.jizhang_danwei) == -1 || list[i].jizhangDanwei == ''){
                panduan = 0
              }
            }

            if(date.jizhang_zhanghu != ''){
              if(list[i].jizhangZhanghu.indexOf(date.jizhang_zhanghu) == -1 || list[i].jizhangZhanghu == ''){
                panduan = 0
              }
            }

            if(panduan == 0){
              list.splice(i,1)
            }

          }
          this.tableData = list
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
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
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>