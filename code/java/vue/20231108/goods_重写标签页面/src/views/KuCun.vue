<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-select v-model="cangku" clearable filterable placeholder="选择仓库">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_CangKu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="type" clearable filterable placeholder="商品分类">
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
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="shangpinBianma"
            label="编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="name"
            label="名称"
            width="120">
        </el-table-column>
        <el-table-column
            prop="guige"
            label="规格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="caizhi"
            label="材质"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="jishuBiaozhun"
            label="技术标准"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="zhibaoDengji"
            label="质保等级"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="danwei"
            label="单位"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shuliang"
            label="库存数量"
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
      cangku:'',
      type:'',
      XiaLa_CangKu:[],
      XiaLa_Type:[],
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_CangKu();
    this.getXiaLa_Type();
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


    getXiaLa_CangKu(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"仓库"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_CangKu = res.data.data;
          for(var i=0; i<this.XiaLa_CangKu.length; i++){
            this.XiaLa_CangKu[i].label = this.XiaLa_CangKu.name
          }
          console.log("下拉已获取");
        } else {
          console.log("下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_Type(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"商品分类"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_Type = res.data.data;
          for(var i=0; i<this.XiaLa_Type.length; i++){
            this.XiaLa_Type[i].label = this.XiaLa_Type.name
          }
          console.log("下拉已获取");
        } else {
          console.log("下拉获取失败");
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
          if(this.userPower.kucunSel == '是'){
            this.getAll();
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

    //查询全部
    getAll(){
      if(this.userPower.kucunSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://yhocn.cn:8102/kuCun/getKuCun"
      this.axios(url, this.form).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
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
      if(this.userPower.kucunSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    //条件查询
    query(){
      if(this.userPower.kucunSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        cangku:this.cangku,
        type:this.type
      }
      let url = "http://yhocn.cn:8102/kuCun/getKuCunByCangKu"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
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