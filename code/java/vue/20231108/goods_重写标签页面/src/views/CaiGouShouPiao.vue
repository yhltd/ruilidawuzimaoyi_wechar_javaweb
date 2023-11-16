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
              v-for="item in XiaLa_HeSuanDanWei"
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
              v-for="item in XiaLa_GongYingShang"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select v-model="shoupiao_zhuangtai" clearable filterable placeholder="请选择收票状态">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ShouPiaoZhuangTai"
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
      <el-col :span="1.5">
        <el-button  v-if="!shenheButton"size="small" round type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="!shenheButton" size="small" round type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="myShouPiao()">需要我收票</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="shenheButton" size="small" round type="primary" @click="shouPiaoClick()">收票</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="!shenheButton" size="small" round type="primary" @click="deleteClick()">删除</el-button>
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
          prop="caigouBianhao"
          label="采购订单编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="shoupiaoDanwei"
          label="收票单位"
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
          prop="shoupiaoZhuangtai"
          label="收票状态"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="getfileList(scope.row)" type="text" size="small">查看文件</el-button>
        </template>
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

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        采购单信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">收票信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="采购单号" prop="caigouBianhao" class="custom-form-item">
              <el-input ref="acc_inp" @click.native="selectProduct()" disabled="true" v-model="gongYingShang.caigouBianhao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收票单位" prop="shoupiaoDanwei" class="custom-form-item">
              <el-select v-model="gongYingShang.shoupiaoDanwei" clearable filterable placeholder="请选择供应商">
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
            <el-form-item label="开票单位" prop="kaipiaoDanwei" class="custom-form-item">
              <el-select v-model="gongYingShang.kaipiaoDanwei" clearable filterable placeholder="请选择供应商">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_GongYingShang"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
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
            <el-form-item label="信息推送" prop="xinxiTuisong" class="custom-form-item">
              <el-select v-model="gongYingShang.xinxiTuisong" clearable filterable placeholder="请选择供应商">
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
          <el-select v-model="Progongyingshang" clearable filterable placeholder="请选择供应商">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_GongYingShang"
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
            prop="gongyingshang"
            label="供应商"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jiashuiHeji"
            label="价税合计"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="shoupiaoJine"
            label="收票金额"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="weikai"
            label="未收金额"
            width="180"
        ></el-table-column>
      </el-table>

    </el-dialog>

    <!-- 文件上传 -->
    <el-dialog title="" :visible.sync="fileDialog" width="80%">
      <input ref="up_file" type="file" id="up_file" @change="uploadSelect()" style="display: none">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" :loading="downloadLoading" @click="upload()">文件上传</el-button>
        </el-col>
      </el-row>

      <el-table :data="FileList" style="width: 100%">
        <el-table-column
            prop="fileName"
            label="文件名"
            width="auto"
        ></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope">
            <el-button :loading="downloadLoading" @click="downloadFile(scope.row)" type="text" size="small">下载</el-button>
            <el-button :loading="downloadLoading" @click="deleteFile(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
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
      fileDialog:false,
      downloadLoading:false,
      FileList:[],
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      shenheButton:false,
      start_date:'',
      stop_date:'',
      shoupiao_danwei:'',
      kaipiao_danwei:'',
      shoupiao_zhuangtai:'',
      Prostart:'',
      Prostop:'',
      Progongyingshang:'',
      CaiGou_Product:[],
      XiaLa_User:[],
      XiaLa_HeSuanDanWei:[],
      XiaLa_GongYingShang:[],
      XiaLa_ShouPiaoZhuangTai:[
        {
          name:'待收票',
          label:'待收票',
        },
        {
          name:'已收票',
          label:'已收票',
        }
      ],
      gongYingShang: {
        caigouBianhao:'',
        shoupiaoDanwei: '',
        kaipiaoDanwei: '',
        kaipiaoRiqi: getNowDate(),
        kaipiaoJine: '',
        kaipiaoShuie: '',
        jiashuiHeji:'',
        beizhu:'',
        xinxiTuisong:'',
        shoupiaoZhuangtai:'待收票',
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
    this.getXiaLa_GongYingShang();
    this.getXiaLa_HeSuanDanWei();
    this.getXiaLa_CangKu();
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
      this.$confirm('是否选择此采购单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.caigouBianhao = row.bianhao
        this.gongYingShang.kaipiaoDanwei = row.gongyingshang
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
        if(this.Progongyingshang != ''){
          if(this.CaiGou_Product[i].gongyingshang != this.Progongyingshang){
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

      if(this.userPower.caigouShoupiaoAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }

      this.gongYingShang = {
        caigouBianhao:'',
        shoupiaoDanwei: '',
        kaipiaoDanwei: '',
        kaipiaoRiqi: getNowDate(),
        kaipiaoJine: '',
        kaipiaoShuie: '',
        jiashuiHeji:'',
        beizhu:'',
        xinxiTuisong:'',
        shoupiaoZhuangtai:'待收票',
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
      this.Progongyingshang = ""
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

      let url = "http://localhost:8081/shouPiao/selectByCaiGouId"
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
      let url = "http://localhost:8081/shouPiao/selectYingShou"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("应收票列表已获取");
          this.selProduct = true;
        } else {
          console.log("应收票列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    //读取账号信息（未实现）
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
          if(this.userPower.caigouShoupiaoSel == '是'){
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
      if(this.userPower.caigouShoupiaoSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://localhost:8081/shouPiao/getAll"
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
      this.shenheButton = false
      this.getAll()
    },

    query(){
      this.shenheButton = false
      if(this.userPower.caigouShoupiaoSel == '是'){
        this.queryList();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
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
        shoupiao_zhuangtai:this.shoupiao_zhuangtai,
      }
      let url = "http://localhost:8081/shouPiao/queryList"
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
    //保存
    saveGongYingShang(){
      var save_list = this.gongYingShang

      if(save_list.shoupiaoDanwei == ''){
        MessageUtil.error("请选择收票单位");
        return;
      }
      if(save_list.kaipiaoDanwei == ''){
        MessageUtil.error("请选择开票单位");
        return;
      }
      if(save_list.kaipiaoRiqi == ''){
        MessageUtil.error("请选择开票日期");
        return;
      }
      if(save_list.kaipiaoJine == ''){
        MessageUtil.error("请选择开票金额");
        return;
      }
      if(save_list.xinxiTuisong == ''){
        MessageUtil.error("请选择信息推送");
        return;
      }


      let url = "http://localhost:8081/shouPiao/shouPiaoAdd"
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
      let url = "http://localhost:8081/shouPiao/shouPiaoUpd"
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

      if(this.userPower.caigouShoupiaoUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
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
      if(this.userPower.caigouShoupiaoDel != '是'){
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
        let url = "http://localhost:8081/shouPiao/delShouPiao";
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

    //条件查询
    myShouPiao(){
      this.shenheButton = true
      let url = "http://localhost:8081/shouPiao/getShouPiao"
      this.axios.post(url, {"xinxi_tuisong":this.userInfo.name}).then(res => {
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

    shouPiaoClick(){
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否对当前选中的信息收票?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8081/shouPiao/shouPiao";
        axios.post(url, {"list": list}).then(res => {
          MessageUtil.success(res.data.msg);
          this.query()
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

    getfileList(row){
      console.log(row)
      this.p_id = row.id
      let url = "http://localhost:8081/fileTable/getAll"
      this.axios.post(url, {"id":row.id,"type":"采购收票"}).then(res => {
        if(res.data.code == '00') {
          this.FileList = res.data.data;
          this.fileDialog = true
          MessageUtil.success("共查询到" + this.FileList.length + "条数据")

        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    refreshfileList(){
      let url = "http://localhost:8081/fileTable/getAll"
      this.axios.post(url, {"id":this.p_id,"type":"采购收票"}).then(res => {
        if(res.data.code == '00') {
          this.FileList = res.data.data;
          this.fileDialog = true
          MessageUtil.success("共查询到" + this.FileList.length + "条数据")

        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    downloadFile(row){
      console.log(row)
      let url = "http://localhost:8081/fileTable/getById"
      this.axios.post(url, {"id":row.id}).then(res => {
        if(res.data.code == '00') {
          if(res.data.data[0].fileName != '' && res.data.data[0].fileName != null){
            downloadFileByBase64(res.data.data[0].fileName, res.data.data[0].file.split(',')[1])
          }
          MessageUtil.success("读取到文件，正在下载")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    deleteFile(row){
      console.log(row)
      this.downloadLoading = true
      let url = "http://localhost:8081/fileTable/deleteById"
      this.axios.post(url, {"list":[row.id]}).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.downloadLoading = false
          this.refreshfileList()
        } else {
          MessageUtil.error(res.data.msg);
          this.downloadLoading = false
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.downloadLoading = false
      })
    },

    upload(){
      this.$refs.up_file.click();
    },

    uploadSelect(){
      this.downloadLoading = true
      var file = document.getElementById("up_file").files;
      console.log(file)
      if(file.length == 0){
        this.downloadLoading = false
      }
      var oFReader = new FileReader();
      var this_file = file[0];
      var fileName = file[0].name;
      var obj = [];
      console.log("fileName:" + fileName)
      oFReader.readAsDataURL(this_file);
      oFReader.onload = oFRevent => {
        console.log('oFRevent----', oFRevent)
        this_file = oFRevent.target.result;
        obj = {
          "fileId": this.p_id,
          "fileName": fileName,
          "file": this_file,
          "type": "采购收票",
        };
        let url = "http://localhost:8081/fileTable/fileAdd"
        this.axios.post(url, obj).then(res => {
          if(res.data.code == '00') {
            console.log(res)
            MessageUtil.success(res.data.msg);
            this.downloadLoading = false
            this.refreshfileList()
          } else {
            MessageUtil.error(res.data.msg);
            this.downloadLoading = false
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
          this.downloadLoading = false
        })
      }
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

function dataURLtoBlob(dataurl, name) {//name:文件名
  var mime = name.substring(name.lastIndexOf('.') + 1)//后缀名
  var bstr = atob(dataurl), n = bstr.length, u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new Blob([u8arr], {type: mime});
}

function downloadFile(url, name = '默认文件名') {
  var a = document.createElement("a")//创建a标签触发点击下载
  a.setAttribute("href", url)//附上
  a.setAttribute("download", name);
  a.setAttribute("target", "_blank");
  let clickEvent = document.createEvent("MouseEvents");
  clickEvent.initEvent("click", true, true);
  a.dispatchEvent(clickEvent);
}

//主函数
function downloadFileByBase64(name, base64) {
  var myBlob = dataURLtoBlob(base64, name);
  var myUrl = URL.createObjectURL(myBlob);
  downloadFile(myUrl, name)
}

//获取后缀
function getType(file) {
  var filename = file;
  var index1 = filename.lastIndexOf(".");
  var index2 = filename.length;
  var type = filename.substring(index1 + 1, index2);
  return type;
}

//根据文件后缀 获取base64前缀不同
function getBase64Type(type) {
  switch (type) {
    case 'data:text/plain;base64':
      return 'txt';
    case 'data:application/msword;base64':
      return 'doc';
    case 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64':
      return 'docx';
    case 'data:application/vnd.ms-excel;base64':
      return 'xls';
    case 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64':
      return 'xlsx';
    case 'data:application/pdf;base64':
      return 'pdf';
    case 'data:application/vnd.openxmlformats-officedocument.presentationml.presentation;base64':
      return 'pptx';
    case 'data:application/vnd.ms-powerpoint;base64':
      return 'ppt';
    case 'data:image/png;base64':
      return 'png';
    case 'data:image/jpeg;base64':
      return 'jpg';
    case 'data:image/gif;base64':
      return 'gif';
    case 'data:image/svg+xml;base64':
      return 'svg';
    case 'data:image/x-icon;base64':
      return 'ico';
    case 'data:image/bmp;base64':
      return 'bmp';
  }
}

function base64ToBlob(code) {
  code = code.replace(/[\n\r]/g, '');
  const raw = window.atob(code);
  const rawLength = raw.length;
  const uInt8Array = new Uint8Array(rawLength);
  for (let i = 0; i < rawLength; ++i) {
    uInt8Array[i] = raw.charCodeAt(i)
  }
  return new Blob([uInt8Array], {type: 'application/pdf'})
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