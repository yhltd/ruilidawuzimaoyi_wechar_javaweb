<template>
  <el-container style="height: 100%;" direction="vertical">
    <el-header class="el-header" style="background-color:transparent">
      <el-row :gutter="15">
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()"><i class="el-icon-circle-plus-outline"></i>添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updUser()"><i class="el-icon-edit"></i>修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteClick()"><i class="el-icon-delete"></i>删除</el-button>
        </el-col>
      </el-row>
    </el-header>
      <el-main refs="main" class="el-main1">
        <el-table
            border
            :header-cell-style="{background:'#d6e5ef',color:'#000'}"
            :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
            style="width: 100%;"
            :height="tableHeight"
            @selection-change="handleSelectionChange">
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              prop="bianhao"
              label="编号"
              width="auto">
          </el-table-column>
          <el-table-column
              prop="name"
              label="客户名称"
              width="auto">
          </el-table-column>
          <el-table-column
              prop="qiankuan"
              label="欠款"
              width="auto">
          </el-table-column>
          <el-table-column
              prop="dingjin"
              label="订金"
              width="auto">
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

    <el-dialog title="" :visible.sync="addDialog" width="90%">

      <el-form :model="addForm" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        商品基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">期初-客户</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.bianhao" class="custom-login-inp" readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户名称" prop="name" class="custom-form-item">
              <el-select style="z-index:3000;" v-model="addForm.name" @change="changeKeHu()" clearable filterable placeholder="请选择客户">
                <!-- types 为后端查询 -->
                <el-option
                    style="z-index:3000;"
                    v-for="item in XiaLa_KeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="欠款" prop="qiankuan" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.qiankuan" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="订金" prop="dingjin" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.dingjin" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="15">
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save" style="margin-left: 30px"><i class="el-icon-finished"></i>保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addClose" style="margin-left: 30px"><i class="el-icon-circle-close"></i>取消
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
  name: "ShangPinGuiGe",
  data(){
    return{
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      currentPage:1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      name:'',
      type:'',
      guige:'',
      addForm:{
        type:'',
        guige:''
      },
      addDialog: false,
      tableData: [],
      multipleSelection: [],
      XiaLa_KeHu:[],
    }
  },
created() {
  this.getUser();
  this.getXiaLa_KeHu();
},
  methods:{
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },

    //新增窗口弹出
    addUser() {
      if(this.userPower.peizhiAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      this.addForm = {
        bianhao:'',
        name:'',
        qiankuan:'',
        dingjin:'',
      };
      this.addDialog = true;
      return true;

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
      let list = this.multipleSelection
      console.log(this.multipleSelection)
      this.addForm = {
        id:list[0].id,
        bianhao:list[0].bianhao,
        name:list[0].name,
        qiankuan:list[0].qiankuan,
        dingjin:list[0].dingjin,
      };
      this.addDialog = true;
      return true;
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
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://yhocn.cn:8102/qiChuKeHu/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
          console.log("数据")
          console.log(res.data.data)
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
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    save(){
      if(this.addForm.name == ''){
        MessageUtil.error("未选择客户")
        return;
      }
      if(this.addForm.qiankuan == '' && this.addForm.dingjin == ''){
        MessageUtil.error("欠款或订金至少填写一个")
        return;
      }
      if(this.addForm.id != undefined && this.addForm.id != null){
        this.updShangPinGuiGe()
      }else{
        this.saveShangPinGuiGe()
      }
    },

    saveShangPinGuiGe(){
      let url = "http://yhocn.cn:8102/qiChuKeHu/add";
      this.axios.post(url, this.addForm).then(res => {
        if (res.data.code == '00') {
          MessageUtil.success("添加成功");
          this.addDialog = false;
          this.getAll();
        } else {
          MessageUtil.error("添加失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },

    updShangPinGuiGe(){
      let url = "http://yhocn.cn:8102/qiChuKeHu/upd";
      this.axios.post(url, this.addForm).then(res => {
        if (res.data.code == '00') {
          MessageUtil.success("添加成功");
          this.addDialog = false;
          this.getAll();
        } else {
          MessageUtil.error("添加失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })

    },

    deleteClick(){
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否删除当前选中的信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://yhocn.cn:8102/qiChuKeHu/del";
        axios.post(url, {"list": list}).then(res => {
          switch (res.data.code) {
            case "00": {
              MessageUtil.success("删除成功");
              list.length = 0;
              this.getAll();
              break;
            }
            default: {
              MessageUtil.error("网络异常");
            }
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
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

    getXiaLa_KeHu(){
      let url = "http://yhocn.cn:8102/customer/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_KeHu = res.data.data;
          for(var i=0; i<this.XiaLa_KeHu.length; i++){
            this.XiaLa_KeHu[i].label = this.XiaLa_KeHu.name
          }
          console.log("客户下拉已获取");
        } else {
          console.log("客户下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    changeKeHu(){
      var this_val = this.addForm.name
      for(var i=0; i< this.XiaLa_KeHu.length; i++){
        if(this.XiaLa_KeHu[i].name == this_val){
          this.addForm.bianhao = this.XiaLa_KeHu[i].bianhao
          break;
        }
      }
    },


  }
}
</script>

<style scoped>
.el-table .cells{
  white-space: pre-line !important;
}
.el-select-dropdown__list{
  z-index:3000 !important;
}
</style>