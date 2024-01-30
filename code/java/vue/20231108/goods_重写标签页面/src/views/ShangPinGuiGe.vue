<template>
  <el-container style="height: 100%;" direction="vertical">
    <el-header class="el-header" style="background-color:transparent">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-input placeholder="商品分类" v-model="type" class="input-with-select"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="规格名称" v-model="guige" class="input-with-select"></el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
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
              prop="type"
              label="商品分类"
              width="auto">
          </el-table-column>
          <el-table-column
              label="规格名称"
              width="auto">
            <template slot-scope="scope">
              <div v-html="scope.row.guige2"></div>
            </template>
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

        <input ref="file" type="file" id="pic_file" @change="fileSelect()" style="display: none">
        <!--        商品基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">商品规格</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="商品分类" prop="type" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.type" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规格名称" prop="guige" class="custom-form-item">
              <el-input ref="acc_inp" type="textarea" autosize="true" v-model="addForm.guige" class="custom-login-inp"></el-input>
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
      multipleSelection: []

    }
  },
created() {
  this.getUser();
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
        type:'',
        guige:''
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
        type:list[0].type,
        guige:list[0].guige
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
          if(this.userPower.shangpinSel == '是'){
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
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://yhocn.cn:8102/peiZhiGuiGe/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          console.log('data')
          console.log(this.tableData)
          for(var i=0; i<this.tableData.length; i++){
            this.tableData[i].guige2 = this.tableData[i].guige.replace(/\r\n/g, '<br/>');
            this.tableData[i].guige2 = this.tableData[i].guige.replace(/\n/g, '<br/>');
          }
          console.log(this.tableData)
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

    //条件查询
    query(){
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        type:this.type,
        guige:this.guige
      }
      let url = "http://yhocn.cn:8102/peiZhiGuiGe/queryProduct"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          for(var i=0; i<this.tableData.length; i++){
            this.tableData[i].guige2 = this.tableData[i].guige.replace(/\r\n/g, '<br/>');
            this.tableData[i].guige2 = this.tableData[i].guige.replace(/\n/g, '<br/>');
          }
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

    delLianXiRen(index){
      console.log(index)
      this.$confirm('是否删除此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.Product.body.splice(index, 1)
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    save(){
      if(this.addForm.id != undefined && this.addForm.id != null){
        let url = "http://yhocn.cn:8102/peiZhiGuiGe/getChongFu";
        axios.post(url, {
          "type": this.addForm.type,
          "id": this.addForm.id,
        }).then(res => {
          var list = res.data.data
          if(list.length > 0){
            MessageUtil.error("已有相同名称的规格组");
          }else{
            this.updShangPinGuiGe()
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })
      }else{
        let url = "http://yhocn.cn:8102/peiZhiGuiGe/getChongFu";
        axios.post(url, {
          "type": this.addForm.type,
          "id": 0,
        }).then(res => {
          var list = res.data.data
          if(list.length > 0){
            MessageUtil.error("已有相同名称的规格组");
          }else{
            this.saveShangPinGuiGe()
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })
      }
    },
    saveShangPinGuiGe(){
      let url = "http://yhocn.cn:8102/peiZhiGuiGe/peiZhiGuiGeAdd";
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
      let url = "http://yhocn.cn:8102/peiZhiGuiGe/peiZhiGuiGeUpd";
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
      if(this.userPower.shangpinDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
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
        let url = "http://yhocn.cn:8102/peiZhiGuiGe/delPeiZhiGuiGe";
        axios.post(url, {"list": list}).then(res => {
          switch (res.data.code) {
            case "00": {
              MessageUtil.success("删除成功");
              list.length = 0;
              this.query();
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

    fileSelect(){
      var file = document.getElementById("pic_file").files;
      var this_file = file[0];
      var fileName = file[0].name;
      console.log(fileName)
      console.log(this_file)
      let URL = window.URL || window.webkitURL;
      // 通过 file 生成目标 url
      let imgURL = URL.createObjectURL(this_file);
      console.log(imgURL)
      this.Product.body[this.p_index].image = imgURL
      this.Product.body[this.p_index].imgFile = file[0]
      this.Product.body[this.p_index].imgFileName = file[0].name
      console.log(this.Product.body[this.p_index])
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

<style scoped>
.el-table .cells{
  white-space: pre-line !important;
}
</style>