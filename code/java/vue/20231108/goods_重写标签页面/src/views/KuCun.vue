<template>
  <el-container direction="vertical">
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
      <el-col :span="1.5">
        <el-button type="primary" @click="query()">查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="refresh()">刷新</el-button>
      </el-col>
    </el-row>
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
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

    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>

  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      cangku:'',
      XiaLa_CangKu:[],
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_CangKu();
    this.getAll();
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
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
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

    //查询全部
    getAll(){
      let url = "http://localhost:8081/kuCun/getKuCun"
      this.axios(url, this.form).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
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
      this.cangku = ""
      this.getAll()
    },

    //条件查询
    query(){
      if(this.cangku != ''){
        var date = {
          cangku:this.cangku
        }
        let url = "http://localhost:8081/kuCun/getKuCunByCangKu"
        this.axios.post(url, date).then(res => {
          if(res.data.code == '00') {
            this.tableData = res.data.data;
            MessageUtil.success("共查询到" + this.tableData.length + "条数据")
          } else {
            MessageUtil.error(res.data.msg);
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })
      }else{
        this.getAll()
      }

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