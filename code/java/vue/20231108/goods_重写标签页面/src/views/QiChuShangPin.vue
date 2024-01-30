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
            prop="shangpinBianma"
            label="编号"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="name"
            label="名称"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="guige"
            label="规格"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="caizhi"
            label="材质"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="jishuBiaozhun"
            label="技术标准"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="zhibaoDengji"
            label="质保等级"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="danwei"
            label="单位"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="shuliang"
            label="数量"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="danjia"
            label="单价"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="jiashuiXiaoji"
            label="价税小计"
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
            <p class="dialog-title">期初-库存</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="编号" prop="shangpinBianma" class="custom-form-item">
              <el-input ref="acc_inp" @click.native="selectProduct()" v-model="addForm.shangpinBianma" placeholder="点击选择产品" class="custom-login-inp" readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="名称" prop="name" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.name" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="规格" prop="guige" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.guige" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="材质" prop="caizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.caizhi" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="技术标准" prop="jishuBiaozhun" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.jishuBiaozhun" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质保等级" prop="zhibaoDengji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.zhibaoDengji" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="单位" prop="danwei" class="custom-form-item">
              <el-input ref="acc_inp" v-model="addForm.danwei" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="数量" prop="shuliang" class="custom-form-item">
              <el-input ref="acc_inp" @change="changeValue('shuliang')" v-model="addForm.shuliang" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="单价" prop="danjia" class="custom-form-item">
              <el-input ref="acc_inp" @change="changeValue('danjia')" v-model="addForm.danjia" class="custom-login-inp" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="金额" prop="jiashuiXiaoji" class="custom-form-item">
              <el-input ref="acc_inp" @change="changeValue('jiashuiXiaoji')" v-model="addForm.jiashuiXiaoji" class="custom-login-inp" ></el-input>
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

    <el-dialog title="" :visible.sync="selProduct" width="80%">

      <el-row :gutter="15" style="margin-bottom: 10px">
        <el-col :span="5">
          <el-input placeholder="商品分类" v-model="Protype" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="商品名称" v-model="Proname" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProQuery()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="Prorefresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          :data="CaiGou_Product"
          :row-class-name="rowClassName"
          @row-click="rowClick"
          style="width: 100%">
        <el-table-column
            prop="bianhao"
            label="编号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="name"
            label="名称"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="type"
            label="类别"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="danwei"
            label="单位"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="caizhi"
            label="材质"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jishuBiaozhun"
            label="技术标准"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="zhibaoDengji"
            label="质保等级"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="caigouPrice"
            label="采购价格"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="zuidijia"
            label="历史最低"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jinxiang"
            label="进项税率"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="xiaoxiang"
            label="销项税率"
            width="180"
        ></el-table-column>
      </el-table>

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
        shangpinBianma:'',
        name:'',
        guige:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        danwei:'',
        danjia:'',
        shuliang:'',
        jiashuiXiaoji:'',
      },
      addDialog: false,
      selProduct:false,
      tableData: [],
      multipleSelection: [],
      XiaLa_KeHu:[],
      CaiGou_Product:[],
    }
  },
  created() {
    this.getUser();
    this.getCaiGouProduct();
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
        shangpinBianma:'',
        name:'',
        guige:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        danwei:'',
        danjia:'',
        shuliang:'',
        jiashuiXiaoji:'',
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
        shangpinBianma:list[0].shangpinBianma,
        name:list[0].name,
        guige:list[0].guige,
        caizhi:list[0].caizhi,
        jishuBiaozhun:list[0].jishuBiaozhun,
        zhibaoDengji:list[0].zhibaoDengji,
        danwei:list[0].danwei,
        danjia:list[0].danjia,
        shuliang:list[0].shuliang,
        jiashuiXiaoji:list[0].jiashuiXiaoji,
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
      let url = "http://yhocn.cn:8102/qiChuProduct/getAll"
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
        MessageUtil.error("未选择产品")
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
      let url = "http://yhocn.cn:8102/qiChuProduct/add";
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
      let url = "http://yhocn.cn:8102/qiChuProduct/upd";
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
        let url = "http://yhocn.cn:8102/qiChuProduct/del";
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

    getCaiGouProduct(){
      let url = "http://yhocn.cn:8102/product/selectCaiGouProduct"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("入库商品列表已获取");
        } else {
          console.log("入库商品列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    selectProduct(index){
      this.Proname = ""
      this.Protype = ""
      for(var i=0; i<this.CaiGou_Product.length; i++){
        this.CaiGou_Product[i].isselect = 1
      }
      this.selProduct = true;
    },


    ProQuery(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        var panduan = true
        console.log(i)
        if(this.Protype != ''){
          if(this.CaiGou_Product[i].type.indexOf(this.Protype) == -1){
            panduan = false
          }
        }
        if(this.Proname != ''){
          if(this.CaiGou_Product[i].name.indexOf(this.Proname) == -1){
            panduan = false
          }
        }
        if(panduan){
          this.CaiGou_Product[i].isselect = 1
        }else{
          this.CaiGou_Product[i].isselect = 0
        }
      }
    },

    Prorefresh(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        this.CaiGou_Product[i].isselect = 1
      }
    },

    rowClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.addForm.shangpinBianma = row.bianhao
        this.addForm.name = row.name
        this.addForm.guige = row.guige
        this.addForm.caizhi = row.caizhi
        this.addForm.jishuBiaozhun = row.jishuBiaozhun
        this.addForm.zhibaoDengji = row.zhibaoDengji
        this.addForm.danwei = row.danwei
        this.addForm.danjia = row.caigouPrice
        this.selProduct = false;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

    changeValue(name){
      console.log(name)
      if(name == 'shuliang' || name == 'danjia'){
        this.addForm.jiashuiXiaoji = Math.round(this.addForm.danjia * this.addForm.shuliang * 100) / 100
      }else if(name == 'jiashuiXiaoji'){
        this.addForm.danjia = Math.round(this.addForm.jiashuiXiaoji / this.addForm.shuliang * 100) / 100
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