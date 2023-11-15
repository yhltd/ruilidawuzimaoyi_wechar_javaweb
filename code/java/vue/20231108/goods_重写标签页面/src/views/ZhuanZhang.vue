<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-date-picker
            style="width:100%"
            value-format="yyyy-MM-dd"
            v-model="start_date"
            type="date"
            placeholder="选择起始日期">
        </el-date-picker>
      </el-col>
      <el-col :span="3">
        <el-date-picker
            style="width:100%"
            value-format="yyyy-MM-dd"
            v-model="stop_date"
            type="date"
            placeholder="选择结束日期">
        </el-date-picker>
      </el-col>
      <el-col :span="3">
        <el-select v-model="zhuanru" clearable filterable placeholder="选择转入账户">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ZhangHu"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
<!--        <el-input placeholder="转入账户" v-model="zhuanru" class="input-with-select">-->
<!--        </el-input>-->
      </el-col>
      <el-col :span="3">
        <el-select v-model="zhuanchu" clearable filterable placeholder="选择转出账户">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ZhangHu"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
<!--        <el-input placeholder="转出账户" v-model="zhuanchu" class="input-with-select">-->
<!--        </el-input>-->
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="query()">查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="deleteClick()">删除</el-button>
      </el-col>
    </el-row>
    <el-table
        ref="multipleTable"
        :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="money"
          label="金额"
          width="120">
      </el-table-column>
      <el-table-column
          prop="riqi"
          label="日期"
          width="120">
      </el-table-column>
      <el-table-column
          prop="zhuanru"
          label="转入账户"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="zhuanchu"
          label="转出账户"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="zhaiyao"
          label="摘要"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
    </el-table>

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

    <el-dialog title="" :visible.sync="addDialog" width="50%">

      <el-form :model="ZhuanZhang" ref="addUsr" label-width="100px"
               class="demo-info">

        <el-form-item label="金额" prop="money" class="custom-form-item">
          <el-input v-model="ZhuanZhang.money" class="custom-login-inp" ></el-input>
        </el-form-item>

        <el-form-item label="日期" prop="riqi" class="custom-form-item">
          <el-date-picker
              style="width:100%"
              value-format="yyyy-MM-dd"
              v-model="ZhuanZhang.riqi"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
<!--          <el-input ref="acc_inp" v-model="ZhuanZhang.riqi" class="custom-login-inp" ></el-input>-->
        </el-form-item>

        <el-form-item label="转入账户" prop="zhuanru" class="custom-form-item">
          <el-select v-model="ZhuanZhang.zhuanru" clearable filterable placeholder="请选择">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_ZhangHu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
<!--          <el-input ref="acc_inp" v-model="ZhuanZhang.zhuanru" class="custom-login-inp" ></el-input>-->
        </el-form-item>

        <el-form-item label="转出账户" prop="zhuanchu" class="custom-form-item">
          <el-select v-model="ZhuanZhang.zhuanchu" clearable filterable placeholder="请选择">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_ZhangHu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
<!--          <el-input ref="acc_inp" v-model="ZhuanZhang.zhuanchu" class="custom-login-inp" ></el-input>-->
        </el-form-item>

        <el-form-item label="摘要" prop="zhaiyao" class="custom-form-item">
          <el-input ref="acc_inp" v-model="ZhuanZhang.zhaiyao" class="custom-login-inp" ></el-input>
        </el-form-item>



          <el-row :gutter="15">
            <el-col :span="4">
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="save">保存
                </el-button>
              </div>
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="addClose">取消
                </el-button>
              </div>
            </el-col>
          </el-row>



      </el-form>
    </el-dialog>


  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      start_date:'',
      stop_date:'',
      zhuanru:'',
      zhuanchu:'',
      XiaLa_ZhangHu:[],
      ZhuanZhang: {
        money:'',
        riqi:'',
        zhuanru:'',
        zhuanchu:'',
        zhaiyao:'',

      },
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_ZhangHu();
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

    //新增窗口弹出
    addUser() {

      if(this.userPower.zhuanzhangAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }

      this.ZhuanZhang = {
        money:'',
        riqi:'',
        zhuanru:'',
        zhuanchu:'',
        zhaiyao:'',
      }

      this.addDialog = true;
    },

    addClose(){
      this.addDialog = false;
    },

    //修改窗口弹出
    updUser() {
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }else if(this.multipleSelection.length > 1){
        MessageUtil.error("请选择一条明细信息再进行修改");
        return;
      }
      var this_id = this.multipleSelection[0].id

      console.log(this.multipleSelection)

      let url = "http://localhost:8081/zhuanZhang/selectZhuanZhangById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this.ZhuanZhang = this_val[0]
          console.log(res.data.data);
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

    },

    getXiaLa_ZhangHu(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"收款账户"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ZhangHu = res.data.data;
          for(var i=0; i<this.XiaLa_ZhangHu.length; i++){
            this.XiaLa_ZhangHu[i].label = this.XiaLa_ZhangHu.name
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
      let url = "http://localhost:8081/user/queryUserInfoById"
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
      let poweruUrl = "http://localhost:8081/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          if(this.userPower.zhuanzhangSel == '是'){
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
      if(this.userPower.zhuanzhangSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://localhost:8081/zhuanZhang/getAll"
      this.axios(url).then(res => {
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
      this.getAll()
    },

    query(){
      this.shenheButton = false
      if(this.userPower.zhuanzhangSel == '是'){
        this.queryList();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == ""){
        start_date = "1900-01-01"
      }
      if(stop_date == ""){
        stop_date = "2100-12-31"
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        zhuanru:this.zhuanru,
        zhuanchu:this.zhuanchu,
      }
      let url = "http://localhost:8081/zhuanZhang/queryList"
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


    saveGongYingShang(){
      var save_list = this.ZhuanZhang
      let url = "http://localhost:8081/zhuanZhang/zhuanZhangAdd"
      this.axios.post(url, save_list).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.addDialog = false;
          this.query()
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    updGongYingShang(){
      var save_list = this.ZhuanZhang
      let url = "http://localhost:8081/zhuanZhang/zhuanZhangUpd"
      this.axios.post(url, save_list).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.addDialog = false;
          this.query()
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    save(){
      if(this.userPower.zhuanzhangUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
        return;
      }

      if(this.ZhuanZhang.id != undefined && this.ZhuanZhang.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.userPower.zhuanzhangDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否删除当前选中的客户信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8081/zhuanZhang/delZhuanZhang";
        axios.post(url, {"list": list}).then(res => {
          MessageUtil.success(res.data.msg);
          this.del_popover_visible = false;
          this.del_loading = false;
          this.query()
        }).catch(() => {
          MessageUtil.error("网络异常");
          this.del_loading = false;
          this.del_popover_visible = false;
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
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