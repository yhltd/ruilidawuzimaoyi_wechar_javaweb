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
        <el-select v-model="dianpu" clearable filterable placeholder="请选择店铺">
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
        <el-select v-model="jizhangren" clearable filterable placeholder="请选择记账人">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_User"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select v-model="jizhang_zhanghu" clearable filterable placeholder="请选择记账账户">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ShouKuanZhangHu"
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
          prop="dianpu"
          label="对应店铺"
          width="120">
      </el-table-column>
      <el-table-column
          prop="danjuLeixing"
          label="单据类型"
          width="120">
      </el-table-column>
      <el-table-column
          prop="danjuBianhao"
          label="单据编号"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="shouzhiBianhao"
          label="记账单号"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="shouzhiRiqi"
          label="收支日期"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jizhangren"
          label="记账人"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jizhangType"
          label="记账分类"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jizhangZhanghu"
          label="记账账户"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jizhangJine"
          label="记账金额"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="kediShuie"
          label="可抵税额"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="zhaiyao"
          label="摘要"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="shouzhiType"
          label="收支类型"
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
            <p class="dialog-title">收支信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
<!--          <el-col :span="6">-->
<!--            <el-form-item label="采购单号" prop="caigouBianhao" class="custom-form-item">-->
<!--              <el-input ref="acc_inp" @click.native="selectProduct()" disabled="true" v-model="gongYingShang.caigouBianhao" class="custom-login-inp"></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="6">
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item">
              <el-select v-model="gongYingShang.dianpu" clearable filterable placeholder="请选择店铺">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_DianPu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单据类型" prop="danjuLeixing" class="custom-form-item">
              <el-select @change="selectDanType()" v-model="gongYingShang.danjuLeixing" clearable filterable placeholder="请选择单据类型">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_DanJuLeiXing"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单据编号" prop="danjuBianhao" class="custom-form-item">
              <el-input ref="acc_inp" @click.native="selectProduct()" disabled="true" v-model="gongYingShang.danjuBianhao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账单号" prop="shouzhiBianhao" class="custom-form-item">
              <el-input ref="acc_inp" disabled="true" v-model="gongYingShang.shouzhiBianhao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账日期" prop="shouzhiRiqi" class="custom-form-item">
              <el-date-picker
                  style="width:100%"
                  v-model="gongYingShang.shouzhiRiqi"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账人" prop="jizhangren" class="custom-form-item">
              <el-select v-model="gongYingShang.jizhangren" clearable filterable placeholder="请选择记账人">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_User"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jizhangren" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账分类" prop="jizhangType" class="custom-form-item">
              <el-select v-model="gongYingShang.jizhangType" clearable filterable placeholder="请选择记账分类">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_JiZhangFenLei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jizhangType" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账账户" prop="jizhangZhanghu" class="custom-form-item">
              <el-select v-model="gongYingShang.jizhangZhanghu" clearable filterable placeholder="请选择记账账户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShouKuanZhangHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账金额" prop="jizhangJine" class="custom-form-item">
              <el-input ref="acc_inp"  v-model="gongYingShang.jizhangJine" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="可抵税额" prop="kediShuie" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kediShuie" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="摘要" prop="zhaiyao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.zhaiyao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <div v-for="(item, index) in gongYingShang.body">
          <!--        商品信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">记账明细{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="明细类型" prop="mingxiType" class="custom-form-item" >
                <el-select v-model="gongYingShang.body[index].mingxiType" clearable filterable placeholder="请选择明细类型">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_JiZhangMingXiLeiXing"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="用途" prop="yongtu" class="custom-form-item">
                <el-input ref="acc_inp"  v-model="gongYingShang.body[index].yongtu" class="custom-login-inp" placeholder="请填写用途"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="记账金额" prop="jizhangJine" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.body[index].jizhangJine" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="可抵税额" prop="kediShuie" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.body[index].kediShuie" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-button v-if="index > 0" class="custom-login-button"  type="primary"
                       @click="delLianXiRen(index)">删除
            </el-button>
          </el-row>
        </div>


        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addLianXiRen">添加明细
              </el-button>
            </div>
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
            prop="bianhao"
            label="单据编号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="riqi"
            label="日期"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="kehu"
            label="客户"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="dianpu"
            label="店铺"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jiashuiXiaoji"
            label="价税小计"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="yifu"
            label="已付款"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="weifu"
            label="未付款"
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
      dianpu:'',
      jizhangren:'',
      jizhang_zhanghu:'',
      Prostart:'',
      Prostop:'',
      Prokehu:'',
      CaiGou_Product:[],
      XiaLa_User:[],
      XiaLa_ShouKuanZhangHu:[],
      XiaLa_DianPu:[],
      XiaLa_KeHu:[],
      XiaLa_JiZhangMingXiLeiXing:[],
      XiaLa_JiZhangFenLei:[],
      XiaLa_DanJuLeiXing:[
        {
          name: '销售订单',
          label: '销售订单'
        },
        {
          name: '销售出库',
          label: '销售出库'
        },
        {
          name: '日常开支',
          label: '日常开支'
        },
      ],
      gongYingShang: {
        dianpu:'',
        danjuLeixing: '',
        danjuBianhao: '',
        shouzhiBianhao: '',
        shouzhiRiqi: getNowDate(),
        jizhangren: '',
        jizhangType:'',
        jizhangZhanghu:'',
        jizhangJine:'',
        kediShuie:'',
        zhaiyao:'',
        shouzhi_type:'收入',
        body:[
          {
            mingxiType:'',
            yongtu:'',
            jizhangJine:'',
            kediShuie:'',
          }
        ]
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
    this.getXiaLa_ShouKuanZhangHu();
    this.getXiaLa_DianPu();
    this.getXiaLa_JiZhangFenLei();
    this.getXiaLa_JiZhangMingXiLeiXing();
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
      this.$confirm('是否选择此单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.danjuBianhao = row.bianhao
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
      var jizhang_jine = 0
      var kedi_shuie = 0
      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].jizhangJine != ""){
          jizhang_jine = Math.round(((jizhang_jine * 1) + (this.gongYingShang.body[i].jizhangJine * 1)) * 100) / 100
        }
        if(this.gongYingShang.body[i].kediShuie != "") {
          kedi_shuie = Math.round(((kedi_shuie * 1) + (this.gongYingShang.body[i].kediShuie * 1)) * 100) / 100
        }
      }
      this.gongYingShang.jizhangJine = jizhang_jine
      this.gongYingShang.kediShuie = kedi_shuie
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

    delLianXiRen(index){
      console.log(index)
      this.$confirm('是否删除此明细?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.body.splice(index, 1)
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

    addLianXiRen(){
      var this_lianxiren = this.gongYingShang.body
      this_lianxiren.push({
        mingxiType:'',
        yongtu:'',
        jizhangJine:'',
        kediShuie:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    //新增窗口弹出
    addUser() {
      let url = "http://localhost:8081/shouZhiMingXi/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].shouzhiBianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "SZ" + this_danhao

          this.gongYingShang = {
            dianpu:'',
            danjuLeixing: '',
            danjuBianhao: '',
            shouzhiBianhao: this_danhao,
            shouzhiRiqi: getNowDate(),
            jizhangren: '',
            jizhangType:'',
            jizhangZhanghu:'',
            jizhangJine:'',
            kediShuie:'',
            zhaiyao:'',
            shouzhi_type:'收入',
            body:[
              {
                mingxiType:'',
                yongtu:'',
                jizhangJine:'',
                kediShuie:'',
              }
            ]
          }
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
    //新增窗口关闭
    addClose(){
      this.addDialog = false;
    },

    selectDanType(){
      if(this.gongYingShang.danjuLeixing == '日常开支'){
        this.gongYingShang.danjuBianhao = ""
      }
    },
    //窗口table弹出
    selectProduct(){
      this.Prostart = ""
      this.Prostop = ""
      this.Prokehu = ""
      if(this.gongYingShang.danjuLeixing == '销售订单'){
        this.getCaiGouProduct()
      }else if(this.gongYingShang.danjuLeixing == '销售出库'){
        this.getRuKuProduct()
      }else if(this.gongYingShang.danjuLeixing == '日常开支'){
        MessageUtil.error("日常开支无需选择单据");
      }else if(this.gongYingShang.danjuLeixing == ''){
        MessageUtil.error("请先选择单据类型");
      }

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

      let url = "http://localhost:8081/shouZhiMingXi/selectById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.shouzhiItem
          this.gongYingShang = this_val
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
      let url = "http://localhost:8081/xiaoShouDingDan/selectWeiFu"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("应付款列表已获取");
          this.selProduct = true;
        } else {
          console.log("应付款列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getRuKuProduct(){
      let url = "http://localhost:8081/xiaoShouChuKu/selectWeiFu"
      this.axios.post(url,{}).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("应付款列表已获取");
          this.selProduct = true;
        } else {
          console.log("应付款列表获取失败");
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
    getXiaLa_ShouKuanZhangHu(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"收款账户"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ShouKuanZhangHu = res.data.data;
          for(var i=0; i<this.XiaLa_ShouKuanZhangHu.length; i++){
            this.XiaLa_ShouKuanZhangHu[i].label = this.XiaLa_ShouKuanZhangHu.name
          }
          console.log("核算单位下拉已获取");
        } else {
          console.log("核算单位下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaLa_DianPu(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"店铺"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_DianPu = res.data.data;
          for(var i=0; i<this.XiaLa_DianPu.length; i++){
            this.XiaLa_DianPu[i].label = this.XiaLa_DianPu.name
          }
          console.log("仓库下拉已获取");
        } else {
          console.log("仓库下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaLa_JiZhangFenLei(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"记账分类"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_JiZhangFenLei = res.data.data;
          for(var i=0; i<this.XiaLa_JiZhangFenLei.length; i++){
            this.XiaLa_JiZhangFenLei[i].label = this.XiaLa_JiZhangFenLei.name
          }
          console.log("记账分类下拉已获取");
        } else {
          console.log("记账分类下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    getXiaLa_JiZhangMingXiLeiXing(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"记账明细类型"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_JiZhangMingXiLeiXing = res.data.data;
          for(var i=0; i<this.XiaLa_JiZhangMingXiLeiXing.length; i++){
            this.XiaLa_JiZhangMingXiLeiXing[i].label = this.XiaLa_JiZhangMingXiLeiXing.name
          }
          console.log("记账明细类型下拉已获取");
        } else {
          console.log("记账明细类型下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      let url = "http://localhost:8081/shouZhiMingXi/getShouRu"
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
      this.dianpu = ""
      this.jizhangren = ""
      this.jizhang_zhanghu = ""
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
        jizhangren:this.jizhangren,
        jizhang_zhanghu:this.jizhang_zhanghu,
      }
      let url = "http://localhost:8081/shouZhiMingXi/queryShouRuList"
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
      let url = "http://localhost:8081/shouZhiMingXi/shouzhiAdd"
      this.axios.post(url, {
        "head":this.gongYingShang,
        "body":this.gongYingShang.body
      }).then(res => {
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
      let url = "http://localhost:8081/shouZhiMingXi/shouzhiUpd"
      this.axios.post(url, {
        "head":this.gongYingShang,
        "body":this.gongYingShang.body
      }).then(res => {
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
      if(this.gongYingShang.dianpu == ''){
        MessageUtil.error("请选择店铺");
        return;
      }
      if(this.gongYingShang.danjuLeixing == ''){
        MessageUtil.error("请选择单据类型");
        return;
      }
      if(this.gongYingShang.danjuLeixing != '日常开支' && this.gongYingShang.danjuBianhao == ''){
        MessageUtil.error("请选择单据编号");
        return;
      }
      if(this.gongYingShang.shouzhiRiqi == ''){
        MessageUtil.error("请选择记账日期");
        return;
      }

      if(this.gongYingShang.jizhangren == ''){
        MessageUtil.error("请选择记账人");
        return;
      }

      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].mingxiType == ''){
          MessageUtil.error('第' + (i * 1+1) + '条明细未选择明细类型');
          return;
        }
        if(this.gongYingShang.body[i].jizhangJine == ''){
          MessageUtil.error('第' + (i * 1+1) + '条明细未填写记账金额');
          return;
        }
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
      this.$confirm('是否删除当前选中的支出信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8081/shouZhiMingXi/delShouZhi";
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