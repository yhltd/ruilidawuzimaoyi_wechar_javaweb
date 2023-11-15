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
        <el-select v-model="kehu" clearable filterable placeholder="请选择客户">
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
        <el-select v-model="shenhe" clearable filterable placeholder="请选择审核状态">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ShenHeZhuangTai"
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
        <el-button v-if="!shenheButton" size="small" round type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="!shenheButton" size="small" round type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button size="small" round type="primary" @click="myShenHe()">需要我审核</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="shenheButton" size="small" round type="primary" @click="shenheClick()">审核</el-button>
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
          prop="bianhao"
          label="编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="riqi"
          label="日期"
          width="120">
      </el-table-column>
      <el-table-column
          prop="kehu"
          label="客户"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="yewuyuan"
          label="业务员"
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
          prop="xiaoxiangShuilv"
          label="销项税率"
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
          prop="shenhe"
          label="审核人"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jiageDengji"
          label="价格等级"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="shenheZhuangtai"
          label="审核状态"
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

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        采购单信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售报价单信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="订单编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单日期" prop="riqi" class="custom-form-item">
              <el-date-picker
                  style="width:100%"
                  v-model="gongYingShang.riqi"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="客户" prop="kehu" class="custom-form-item">
              <el-select v-model="gongYingShang.kehu" @change="changeKeHu()" clearable filterable placeholder="请选择客户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_KeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价格等级" prop="jiageDengji" class="custom-form-item">
              <el-input ref="acc_inp" disabled="true" v-model="gongYingShang.jiageDengji" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item">
              <el-select v-model="gongYingShang.yewuyuan" clearable filterable placeholder="请选择业务员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShenHe"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
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
<!--              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="销项税率" prop="xiaoxiangShuilv" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoxiangShuilv" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">
              <el-select v-model="gongYingShang.shenhe" clearable filterable placeholder="请选择审核人">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShenHe"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.caigouyuan" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>

        </el-row>


        <div v-for="(item, index) in gongYingShang.body">
          <!--        商品信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">商品信息{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="商品编码" prop="shangpinBianhao" class="custom-form-item" >
                <el-input ref="acc_inp" readonly="true" @click.native="selectProduct(index)" v-model="gongYingShang.body[index].shangpinBianhao" class="custom-login-inp" placeholder="点击选择商品"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="名称" prop="shangpinMingcheng" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].shangpinMingcheng" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="规格" prop="guige" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].guige" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="材质" prop="caizhi" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].caizhi" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="技术标准" prop="jishuBiaozhun" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jishuBiaozhun" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="质保等级" prop="zhibaoDengji" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhibaoDengji" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="单位" prop="danwei" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].danwei" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="数量" prop="shuliang" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index,'shuliang')" v-model="gongYingShang.body[index].shuliang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="报价单价" prop="baojiaDanjia" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index,'baojia_danjia')" v-model="gongYingShang.body[index].baojiaDanjia" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="价税小计" prop="jiashuiXiaoji" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jiashuiXiaoji" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="建议报价" prop="jianyiBaojia" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jianyiBaojia" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="需用日期" prop="xuyongRiqi" class="custom-form-item">
                <el-date-picker
                    style="width:100%"
                    v-model="gongYingShang.body[index].xuyongRiqi"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择需用日期">
                </el-date-picker>
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jiaohuoRiqi" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="报价浮动" prop="baojiaFudong" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index,'baojia_fudong')" v-model="gongYingShang.body[index].baojiaFudong" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="备注" prop="beizhu" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].beizhu" class="custom-login-inp"></el-input>
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
                         @click="addLianXiRen">添加商品
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
          <el-input placeholder="商品分类" v-model="Protype" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="商品名称" v-model="Proname" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProQuery()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProRefresh()">刷新</el-button>
        </el-col>
      </el-row>

      <el-table :data="CaiGou_Product" :row-class-name="rowClassName" @row-click="rowClick" style="width: 100%">
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
            prop="lingshouPrice"
            label="零售价格"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="pifaPrice"
            label="批发价格"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="kakehuPrice"
            label="大客户价格"
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

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        center>
      <span>请选择审核状态</span>
      <span slot="footer" class="dialog-footer">
        <el-button  type="primary" @click="shenheTrue">审核通过</el-button>
        <el-button  @click="shenheFalse">审核未通过</el-button>
      </span>
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
      dialogVisible:false,
      shenheButton:false,
      userInfo:'',
      userPower:'',
      p_index:'',
      start_date:'',
      stop_date:'',
      kehu:'',
      shenhe_zhuangtai:'',
      shenhe:'',
      Protype:'',
      Proname:'',
      XiaLa_KeHu:[],
      XiaLa_User:[],
      XiaLa_DianPu:[],
      CaiGou_Product:[],
      XiaLa_GongYingShang:[],
      XiaLa_ShenHe:[],
      XiaLa_ShenHeZhuangTai:[
        {
          name:'审核中',
          label:'审核中',
        },
        {
          name:'审核通过',
          label:'审核通过',
        },
        {
          name:'审核未通过',
          label:'审核未通过',
        }
      ],
      gongYingShang: {
        bianhao:'123123',
        riqi: getNowDate(),
        kehu: '',
        yewuyuan: '',
        dianpu: '',
        xiaoxiangShuilv: '',
        beizhu:'',
        shenhe:'',
        jiageDengji:'',
        shenheZhuangtai:'审核中',
        body:[
          {
            shangpinBianhao:'',
            shangpinMingcheng:'',
            guige:'',
            caizhi:'',
            jishuBiaozhun:'',
            zhibaoDengji:'',
            danwei:'',
            shuliang:'',
            baojiaDanjia:'',
            jiashuiXiaoji:'',
            jianyiBaojia:'',
            xuyongRiqi:'',
            baojiaFudong:'',
            beizhu:'',
          }
        ]
      },
      addDialog: false,
      updDialog: false,
      selProduct: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.getCaiGouProduct();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_KeHu()
    this.getXiaLa_ShenHe();
    this.getXiaLa_DianPu();
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

    rowClassName: function ({ row }) {
      if (row.isselect == 0) {
        return "hidden-row";
      }
      return '';
    },

    rowClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.body[this.p_index].shangpinBianhao = row.shangpinBianma
        this.gongYingShang.body[this.p_index].shangpinMingcheng = row.name
        this.gongYingShang.body[this.p_index].guige = row.guige
        this.gongYingShang.body[this.p_index].caizhi = row.caizhi
        this.gongYingShang.body[this.p_index].jishuBiaozhun = row.jishuBiaozhun
        this.gongYingShang.body[this.p_index].zhibaoDengji = row.zhibaoDengji
        this.gongYingShang.body[this.p_index].danwei = row.danwei

        this.gongYingShang.body[this.p_index].caigouDanjia = row.caigouPrice

        if(this.gongYingShang.jiageDengji == '零售价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.lingshouPrice
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.lingshouPrice
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.lingshouPrice * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }else if(this.gongYingShang.jiageDengji == '批发价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.pifaPrice
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.pifaPrice
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.pifa_price * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }else if(this.gongYingShang.jiageDengji == '大客户价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.dakehu_price
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.dakehu_price
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.dakehu_price * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }

        if(this.gongYingShang.body[this.p_index].baojiaDanjia != '' && this.gongYingShang.body[this.p_index].shuliang != ''){
          this.gongYingShang.body[this.p_index].jiashuiXiaoji = Math.round(this.gongYingShang.body[this.p_index].baojiaDanjia * this.gongYingShang.body[this.p_index].shuliang * 100) / 100
        }
        this.selProduct = false;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

    changeValue(index,column){
      console.log(index,column)
      if(column == 'baojia_fudong'){
        this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round(this.gongYingShang.body[this.p_index].jianyiBaojia * this.gongYingShang.body[this.p_index].baojiaFudong / 100 * 100) / 100
      }
      if(column == 'baojia_danjia'){
        this.gongYingShang.body[this.p_index].baojiaFudong = Math.round(this.gongYingShang.body[this.p_index].baojiaDanjia / this.gongYingShang.body[this.p_index].jianyiBaojia * 100 * 100) / 100
      }
      this.gongYingShang.body[this.p_index].jiashuiXiaoji = Math.round(this.gongYingShang.body[this.p_index].shuliang * this.gongYingShang.body[this.p_index].baojiaDanjia * 100) / 100
    },

    changeKeHu(){
      var this_val = this.gongYingShang.kehu
      for(var i=0; i< this.XiaLa_KeHu.length; i++){
        if(this.XiaLa_KeHu[i].name == this_val){
          this.gongYingShang.jiageDengji = this.XiaLa_KeHu[i].jiageDengji
          break;
        }
      }
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

    //新增窗口弹出
    addUser() {
      if(this.userPower.xiaoshouBaojiaAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://localhost:8081/xiaoShouBaoJia/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "BJ" + this_danhao
          this.gongYingShang = {
            bianhao:this_danhao,
            riqi: getNowDate(),
            kehu: '',
            yewuyuan: this.userInfo.name,
            dianpu: '',
            xiaoxiangShuilv: '',
            beizhu:'',
            shenhe:this.userInfo.shenpi,
            jiageDengji:'',
            shenheZhuangtai:'审核中',
            body:[
              {
                shangpinBianhao:'',
                shangpinMingcheng:'',
                guige:'',
                caizhi:'',
                jishuBiaozhun:'',
                zhibaoDengji:'',
                danwei:'',
                shuliang:'',
                baojiaDanjia:'',
                jiashuiXiaoji:'',
                jianyiBaojia:'',
                xuyongRiqi:'',
                baojiaFudong:'',
                beizhu:'',
              }
            ]
          }

          let url = "http://localhost:8081/peizhi/selectByPeiZhiId"
          this.axios.post(url,{"id":this.userInfo.dianpu}).then(res => {
            if(res.data.code == '00') {
              console.log(res.data.data[0].name)
              this.gongYingShang.dianpu = res.data.data[0].name
              console.log("默认店铺信息已获取");
            } else {
              console.log("账号店铺获取失败");
            }
          }).catch(() => {
            MessageUtil.error("网络异常");
          })

          console.log(res.data.data);
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })


      this.addDialog = true;
    },

    addClose(){
      this.addDialog = false;
    },

    selectProduct(index){
      if(this.gongYingShang.jiageDengji == '' || this.gongYingShang.jiageDengji == null || this.gongYingShang.jiageDengji == undefined){
        MessageUtil.error("未读取到客户价格等级，请选择客户后再试");
        return;
      }
      console.log(index)
      this.p_index = index
      this.Proname = ""
      this.Protype = ""
      for(var i=0; i<this.CaiGou_Product.length; i++){
        this.CaiGou_Product[i].isselect = 1
      }
      this.selProduct = true;
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

      let url = "http://localhost:8081/xiaoShouBaoJia/selectBaoJiaById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          if(res.data.data.shenheZhuangtai == '审核未通过'){
            res.data.data.shenheZhuangtai = '审核中'
          }
          this_val.body = this_val.itemList
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

    getCaiGouProduct(){
      let url = "http://localhost:8081/product/selectCaiGouProduct"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("采购商品列表已获取");
        } else {
          console.log("采购商品列表获取失败");
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
          if(this.userPower.xiaoshouBaojiaSel == '查看全部'){
            this.getAll();
          }else if(this.userPower.xiaoshouBaojiaSel == '查看个人'){
            this.getAllByName();
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

    getXiaLa_User(){
      let url = "http://localhost:8081/user/getall"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_User = res.data.data;
          for(var i=0; i<this.XiaLa_User.length; i++){
            this.XiaLa_User[i].label = this.XiaLa_User.name
          }
          console.log("业务员下拉已获取");
        } else {
          console.log("业务员下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ShenHe(){
      let url = "http://localhost:8081/user/fuzzyQuery"
      this.axios.post(url,{"keyword":""}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ShenHe = res.data.data;
          for(var i=0; i<this.XiaLa_ShenHe.length; i++){
            this.XiaLa_ShenHe[i].label = this.XiaLa_ShenHe.name
          }
          console.log("审核人下拉已获取");
        } else {
          console.log("审核人下拉获取失败");
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
          console.log("店铺下拉已获取");
        } else {
          console.log("店铺下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      this.shenheButton = false
      let url = "http://localhost:8081/xiaoShouBaoJia/getAll"
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

    //查询全部
    getAllByName(){
      this.shenheButton = false
      let url = "http://localhost:8081/xiaoShouBaoJia/getAllByName"
      this.axios.post(url, {"yewuyuan":this.userInfo.name}).then(res => {
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
      if(this.userPower.xiaoshouBaojiaSel == '查看全部'){
        this.getAll();
      }else if(this.userPower.xiaoshouBaojiaSel == '查看个人'){
        this.getAllByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    query(){
      this.shenheButton = false
      if(this.userPower.xiaoshouBaojiaSel == '查看全部'){
        this.queryList();
      }else if(this.userPower.xiaoshouBaojiaSel == '查看个人'){
        this.queryListByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
      this.shenheButton = false
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == ''){
        start_date = '1900-01-01'
      }
      if(stop_date == ''){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        kehu:this.kehu,
        shenhe_zhuangtai:this.shenhe
      }
      let url = "http://localhost:8081/xiaoShouBaoJia/queryList"
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

    //条件查询
    queryListByName(){
      this.shenheButton = false
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == ''){
        start_date = '1900-01-01'
      }
      if(stop_date == ''){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        kehu:this.kehu,
        shenhe_zhuangtai:this.shenhe,
        yewuyuan:this.userInfo.name,
      }
      let url = "http://localhost:8081/xiaoShouBaoJia/queryListByName"
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

    //条件查询
    myShenHe(){
      this.shenheButton = true
      let url = "http://localhost:8081/xiaoShouBaoJia/shenheList"
      this.axios.post(url, {"name":this.userInfo.name}).then(res => {
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

    delLianXiRen(index){
      console.log(index)
      this.$confirm('是否删除此联系人?', '提示', {
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
        shangpinBianhao:'',
        shangpinMingcheng:'',
        guige:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        danwei:'',
        shuliang:'',
        baojiaDanjia:'',
        jiashuiXiaoji:'',
        jianyiBaojia:'',
        xuyongRiqi:'',
        baojiaFudong:'',
        beizhu:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/xiaoShouBaoJia/baoJiaAdd"
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

    updGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/xiaoShouBaoJia/baoJiaUpd"
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

    save(){
      if(this.userPower.xiaoshouBaojiaUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
        return;
      }

      if(this.gongYingShang.riqi == ''){
        MessageUtil.error('请选择日期');
        return;
      }
      if(this.gongYingShang.kehu == ''){
        MessageUtil.error('请选择客户');
        return;
      }
      if(this.gongYingShang.yewuyuan == ''){
        MessageUtil.error('请选择业务员');
        return;
      }
      if(this.gongYingShang.dianpu == ''){
        MessageUtil.error('请选择店铺');
        return;
      }
      if(this.gongYingShang.shenheren == ''){
        MessageUtil.error('请选择审核人');
        return;
      }
      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].shangpinBianma == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未选择商品');
          return;
        }
        if(this.gongYingShang.body[i].shuliang == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写数量');
          return;
        }
        if(this.gongYingShang.body[i].baojiaDanjia == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写报价单价');
          return;
        }
      }

      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.userPower.xiaoshouBaojiaDel != '是'){
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
        let url = "http://localhost:8081/xiaoShouBaoJia/delXiaoShouBaoJia";
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

    shenheClick(){
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.dialogVisible = true;
    },

    shenheTrue(){
      var list = []
      for(var i=0; i<this.multipleSelection.length; i++){
        list.push(this.multipleSelection[i].id)
      }
      console.log(list)
      let url = "http://localhost:8081/xiaoShouBaoJia/baoJiaShenHe";
      axios.post(url, {"list": list,"type":"审核通过"}).then(res => {
        MessageUtil.success(res.data.msg);
        this.dialogVisible = false;
        this.myShenHe()
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.dialogVisible = false;
      })
    },
    shenheFalse(){
      var list = []
      for(var i=0; i<this.multipleSelection.length; i++){
        list.push(this.multipleSelection[i].id)
      }
      console.log(list)
      let url = "http://localhost:8081/xiaoShouBaoJia/baoJiaShenHe";
      axios.post(url, {"list": list,"type":"审核未通过"}).then(res => {
        MessageUtil.success(res.data.msg);
        this.dialogVisible = false;
        this.myShenHe()
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.dialogVisible = false;
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