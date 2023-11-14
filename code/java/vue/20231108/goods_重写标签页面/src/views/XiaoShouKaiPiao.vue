<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-date-picker
            style="width:100%"
            v-model="start_date"
            type="date"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            placeholder="选择起始日期">
        </el-date-picker>
      </el-col>
      <el-col :span="3">
        <el-date-picker
            style="width:100%"
            v-model="stop_date"
            type="date"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            placeholder="选择结束日期">
        </el-date-picker>
      </el-col>
      <el-col :span="3">
        <el-select v-model="shoupiao_danwei" clearable filterable placeholder="请选择收票单位">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_KeHu"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select v-model="kaipiao_danwei" clearable filterable placeholder="请选择开票单位">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_HeSuanDanWei"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select v-model="kaipiao_zhuangtai" clearable filterable placeholder="请选择开票状态">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_KaiPiaoZhuangTai"
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
      <el-col :span="1.5">
        <el-button type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="deleteClick()">删除</el-button>
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
          prop="xiaoshouBianhao"
          label="销售订单编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="shoupiaoDanwei"
          label="收票单位"
          width="120">
      </el-table-column>
      <el-table-column
          prop="shibiehao"
          label="纳税人识别号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kaipiaoDizhi"
          label="开票地址"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kaipiaoDianhua"
          label="开票电话"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kaipiaoYinhang"
          label="开户银行"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kaipiaoZhanghao"
          label="银行账号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kaipiaoDanwei"
          label="开票单位"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="kaipiaoRiqi"
          label="开票日期"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="kaipiaoJine"
          label="开票金额"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="kaipiaoShuie"
          label="开票税额"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jiashuiHeji"
          label="价税合计"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="beizhu"
          label="备注"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="xinxiTuisong"
          label="信息推送"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="kaipiaoZhuangtai"
          label="开票状态"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
    </el-table>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        采购单信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">开票信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="销售单号" prop="xiaoshouBianhao" class="custom-form-item">
              <el-input ref="acc_inp" @click.native="selectProduct()" disabled="true" v-model="gongYingShang.xiaoshouBianhao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收票单位" prop="shoupiaoDanwei" class="custom-form-item">
              <el-select v-model="gongYingShang.shoupiaoDanwei" clearable filterable placeholder="请选择收票单位">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_KeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="shibiehao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shibiehao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票地址" prop="kaipiaoDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票电话" prop="kaipiaoDianhua" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDianhua" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="kaipiaoYinhang" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="kaipiaoZhanghao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票日期" prop="kaipiaoRiqi" class="custom-form-item">
              <el-date-picker
                  style="width:100%"
                  v-model="gongYingShang.kaipiaoRiqi"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票金额" prop="kaipiaoJine" class="custom-form-item">
              <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.kaipiaoJine" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票税额" prop="kaipiaoShuie" class="custom-form-item">
              <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.kaipiaoShuie" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价税合计" prop="jiashuiHeji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.jiashuiHeji" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票单位" prop="kaipiaoDanwei" class="custom-form-item">
              <el-select v-model="gongYingShang.kaipiaoDanwei" clearable filterable placeholder="请选择开票单位">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_HeSuanDanWei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="信息推送" prop="xinxiTuisong" class="custom-form-item">
              <el-select v-model="gongYingShang.xinxiTuisong" clearable filterable placeholder="请选择信息推送">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_User"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save">保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="addClose">取消
              </el-button>
            </div>
          </el-col>
        </el-row>



      </el-form>
    </el-dialog>

    <el-dialog title="" :visible.sync="selProduct" width="80%">

      <el-row :gutter="15">
        <el-col :span="5">
          <el-date-picker
              style="width:100%"
              v-model="Prostart"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择起始日期">
          </el-date-picker>
        </el-col>
        <el-col :span="5">
          <el-date-picker
              style="width:100%"
              v-model="Prostop"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择结束日期">
          </el-date-picker>
        </el-col>
        <el-col :span="5">
          <el-select v-model="Prokehu" clearable filterable placeholder="请选择客户">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_KeHu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProQuery()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="Prorefresh()">刷新</el-button>
        </el-col>
      </el-row>

      <el-table :data="CaiGou_Product" :row-class-name="rowClassName" @row-click="rowClick" style="width: 100%">
        <el-table-column
            prop="riqi"
            label="日期"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="bianhao"
            label="采购单号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="kehu"
            label="客户"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jiashuiHeji"
            label="价税合计"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="kaipiaoJine"
            label="开票金额"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="weikai"
            label="未开金额"
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
  data() {
    return {
      start_date:'',
      stop_date:'',
      shoupiao_danwei:'',
      kaipiao_danwei:'',
      kaipiao_zhuangtai:'',
      Prostart:'',
      Prostop:'',
      Prokehu:'',
      CaiGou_Product:[],
      XiaLa_KeHu:[],
      XiaLa_User:[],
      XiaLa_HeSuanDanWei:[],
      XiaLa_GongYingShang:[],
      XiaLa_KaiPiaoZhuangTai:[
        {
          name:'待开票',
          label:'待开票',
        },
        {
          name:'已开票',
          label:'已开票',
        }
      ],
      gongYingShang: {
        xiaoshouBianhao:'',
        shoupiaoDanwei: '',
        shibiehao: '',
        kaipiaoDizhi: '',
        kaipiaoDianhua: '',
        kaipiaoYinhang: '',
        kaipiaoZhanghao:'',
        kaipiaoRiqi:getNowDate(),
        kaipiaoJine:'',
        kaipiaoShuie:'',
        jiashuiHeji:'',
        beizhu:'',
        kaipiaoDanwei:'',
        xinxiTuisong:'',
        kaipiaoZhuangtai:'待开票',
      },
      addDialog: false,
      selProduct: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.getXiaLa_User();
    this.getXiaLa_KeHu();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_HeSuanDanWei();
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

    //隐藏窗口table中的选择项
    rowClassName: function ({ row }) {
      if (row.isselect == 0) {
        return "hidden-row";
      }
      return '';
    },
    //点击窗口table中的选择项
    rowClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此销售单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.xiaoshouBianhao = row.bianhao
        this.gongYingShang.shoupiaoDanwei = row.kehu
        if(row.kehu != ''){
          for(var i=0; i<this.XiaLa_KeHu.length; i++){
            if(row.kehu == this.XiaLa_KeHu[i].name){
              this.gongYingShang.shibiehao = this.XiaLa_KeHu[i].shibiehao
              this.gongYingShang.kaipiaoDizhi = this.XiaLa_KeHu[i].kaipiaoDizhi
              this.gongYingShang.kaipiaoDianhua = this.XiaLa_KeHu[i].kaipiaoDianhua
              this.gongYingShang.kaipiaoYinhang = this.XiaLa_KeHu[i].kaipiaoYinhang
              this.gongYingShang.kaipiaoZhanghao = this.XiaLa_KeHu[i].kaipiaoZhanghao
              break;
            }
          }
        }
        this.selProduct = false;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    //编辑添加窗口中的信息自动计算
    changeValue(){
      this.gongYingShang.jiashuiHeji = Math.round(((this.gongYingShang.kaipiaoJine) * 1 + (this.gongYingShang.kaipiaoShuie * 1)) * 100) / 100
    },
    //窗口table中的查询
    ProQuery(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        var panduan = true
        console.log(i)
        if(this.Prostart != ''){
          if(this.CaiGou_Product[i].riqi < this.Prostart){
            panduan = false
          }
        }
        if(this.Prostop != ''){
          if(this.CaiGou_Product[i].riqi > this.Prostop){
            panduan = false
          }
        }
        if(this.Prokehu != ''){
          if(this.CaiGou_Product[i].kehu != this.Prokehu){
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
    //窗口table中的刷新
    Prorefresh(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        this.CaiGou_Product[i].isselect = 1
      }
    },

    //新增窗口弹出
    addUser() {
      this.gongYingShang = {
        xiaoshouBianhao:'',
        shoupiaoDanwei: '',
        shibiehao: '',
        kaipiaoDizhi: '',
        kaipiaoDianhua: '',
        kaipiaoYinhang: '',
        kaipiaoZhanghao:'',
        kaipiaoRiqi:getNowDate(),
        kaipiaoJine:'',
        kaipiaoShuie:'',
        jiashuiHeji:'',
        beizhu:'',
        kaipiaoDanwei:'',
        xinxiTuisong:'',
        kaipiaoZhuangtai:'待开票',
      }
      this.addDialog = true;
    },
    //新增窗口关闭
    addClose(){
      this.addDialog = false;
    },
    //窗口table弹出
    selectProduct(){
      this.Prostart = ""
      this.Prostop = ""
      this.Prokehu = ""
      this.getCaiGouProduct()
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

      let url = "http://localhost:8081/kaiPiao/selectXiaoShouById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this.gongYingShang = this_val[0]
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
    //查询窗口table中的内容列表
    getCaiGouProduct(){
      let url = "http://localhost:8081/kaiPiao/selectYingKai"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("应开票列表已获取");
          this.selProduct = true;
        } else {
          console.log("应开票列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    //读取账号信息（未实现）
    getUser(){
      let url = "http://localhost:8081/user/getLogin"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.User_List = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("登录信息已获取");
        } else {
          console.log("登录信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    //读取下拉列表
    getXiaLa_User(){
      let url = "http://localhost:8081/user/getall"
      this.axios(url).then(res => {
        this.XiaLa_User = res.data;
        for(var i=0; i<this.XiaLa_User.length; i++){
          this.XiaLa_User[i].label = this.XiaLa_User.name
        }
        console.log("信息推送下拉已获取");
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaLa_GongYingShang(){
      let url = "http://localhost:8081/gongYingShang/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_GongYingShang = res.data.data;
          for(var i=0; i<this.XiaLa_GongYingShang.length; i++){
            this.XiaLa_GongYingShang[i].label = this.XiaLa_GongYingShang.name
          }
          console.log("供应商下拉已获取");
        } else {
          console.log("供应商下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_KeHu(){
      let url = "http://localhost:8081/customer/getAll"
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

    getXiaLa_HeSuanDanWei(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"核算单位"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_HeSuanDanWei = res.data.data;
          for(var i=0; i<this.XiaLa_HeSuanDanWei.length; i++){
            this.XiaLa_HeSuanDanWei[i].label = this.XiaLa_HeSuanDanWei.name
          }
          console.log("核算单位下拉已获取");
        } else {
          console.log("核算单位下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaLa_CangKu(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"仓库"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_CangKu = res.data.data;
          for(var i=0; i<this.XiaLa_CangKu.length; i++){
            this.XiaLa_CangKu[i].label = this.XiaLa_CangKu.name
          }
          console.log("仓库下拉已获取");
        } else {
          console.log("仓库下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      let url = "http://localhost:8081/kaiPiao/getAll"
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
      this.start_date = ""
      this.stop_date = ""
      this.shoupiao_danwei = ""
      this.kaipiao_danwei = ""
      this.shoupiao_zhuangtai = ""
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
        shoupiao_danwei:this.shoupiao_danwei,
        kaipiao_danwei:this.kaipiao_danwei,
        kaipiao_zhuangtai:this.kaipiao_zhuangtai,
      }
      let url = "http://localhost:8081/kaiPiao/queryList"
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
    },
    //保存
    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/kaiPiao/kaiPiaoAdd"
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
    //修改
    updGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/kaiPiao/kaiPiaoUpd"
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
    //根据对象中有无id执行保存或修改
    save(){
      if(this.gongYingShang.shoupiaoDanwei == ''){
        MessageUtil.error('请选择收票单位');
        return;
      }
      if(this.gongYingShang.kaipiaoDanwei == ''){
        MessageUtil.error('请选择开票单位');
        return;
      }
      if(this.gongYingShang.kaipiaoRiqi == ''){
        MessageUtil.error('请选择开票日期');
        return;
      }
      if(this.gongYingShang.kaipiaoJine == ''){
        MessageUtil.error('请填写开票金额');
        return;
      }
      if(this.gongYingShang.xinxiTuisong == ''){
        MessageUtil.error('请选择信息推送');
        return;
      }
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },
    //删除选中
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
        let url = "http://localhost:8081/kaiPiao/delKaiPiao";
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
  }
}

function PrefixInteger(num, n) {
  return (Array(n).join(0) + num).slice(-n);
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
  var currentdate = year + sign1 + month + sign1 + day ;
  return currentdate;
}
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
.el-table .hidden-row {
  display: none;
}
</style>