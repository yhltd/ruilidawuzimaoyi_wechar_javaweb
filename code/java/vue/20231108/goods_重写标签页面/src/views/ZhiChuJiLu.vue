<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
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
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()"><i class="el-icon-circle-plus-outline"></i>添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updUser()"><i class="el-icon-edit"></i>编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteClick()"><i class="el-icon-delete"></i>删除</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          ref="multipleTable"
          :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight"
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
            prop="huokuanzhuangtai"
            label="货款状态"
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
            prop="jizhangDanwei"
            label="记账单位"
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
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-button @click="getfileList(scope.row)" type="text" size="small"><i class="el-icon-folder-opened"></i>查看文件</el-button>
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
          <el-col :span="10"><!--  ztt修改宽度-->
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.dianpu" clearable filterable placeholder="请选择店铺">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_DianPu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              ztt快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '店铺')">添加</el-button>
              <!--            ztt end-->

            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单据类型" prop="danjuLeixing" class="custom-form-item">
              <el-select style="z-index:999;" @change="selectDanType()" v-model="gongYingShang.danjuLeixing" clearable filterable placeholder="请选择单据类型">
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
              <el-input ref="acc_inp" @click.native="selectProduct()" readonly="true" v-model="gongYingShang.danjuBianhao" class="custom-login-inp" placeholder="点击选择单据"></el-input>
            </el-form-item>
          </el-col>

<!--          <el-col :span="6">-->
<!--            <el-form-item label="货款状态" prop="huokuanzhuangtai" class="custom-form-item">  &lt;!&ndash; clearable filterable placeholder="请选择货款状态" &ndash;&gt;-->
<!--              <el-select style="z-index:999;" @change="selecthuo()" disabled="true" @click.native="selectProduct()" v-model="gongYingShang.huokuanzhuangtai">-->
<!--                &lt;!&ndash; types 为后端查询 &ndash;&gt;-->
<!--                <el-option-->
<!--                    v-for="item in XiaLa_huokuanzhuangtai"-->
<!--                    :key="item.name"-->
<!--                    :label="item.name"-->
<!--                    :value="item.name">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

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
              <el-select style="z-index:999;" v-model="gongYingShang.jizhangren" clearable filterable placeholder="请选择记账人">
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
          <el-col :span="10"><!--  ztt修改宽度-->
            <el-form-item label="记账分类" prop="jizhangType" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.jizhangType" clearable filterable placeholder="请选择记账分类">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_JiZhangFenLei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--             ztt 快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '记账分类')">添加</el-button>
              <!--            ztt end-->
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jizhangType" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="10"><!--  ztt修改宽度-->
            <el-form-item label="记账账户" prop="jizhangZhanghu" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.jizhangZhanghu" clearable filterable placeholder="请选择记账账户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShouKuanZhangHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              ztt快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '收款账户')">添加</el-button>
              <!--            ztt end-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="记账金额" prop="jizhangJine" class="custom-form-item">
              <el-input ref="acc_inp"  v-model="gongYingShang.jizhangJine" class="custom-login-inp" readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="可抵税额" prop="kediShuie" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kediShuie" class="custom-login-inp" readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="摘要" prop="zhaiyao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.zhaiyao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="记账单位" prop="jizhangDanwei" class="custom-form-item">
              <el-select style="z-index:999;" @change="selectDanType()" v-model="gongYingShang.jizhangDanwei" clearable filterable placeholder="请选择单据类型">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_jizhangDanwei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
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
            <el-col :span="5.5">
              <el-form-item label="明细类型" prop="mingxiType" class="custom-form-item" >
                <el-select style="z-index:999;" v-model="gongYingShang.body[index].mingxiType" clearable filterable placeholder="请选择明细类型">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_JiZhangMingXiLeiXing"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--             ztt
                 快速跳转到配置项页-->
                <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                           @click="goToPeiZhi('/basePeizhi', '记账明细类型')">添加</el-button>
                <!--            ztt end-->
              </el-form-item>
            </el-col>
            <el-col :span="5.5">
              <el-form-item label="用途" prop="yongtu" class="custom-form-item">
                <el-input ref="acc_inp"  v-model="gongYingShang.body[index].yongtu" class="custom-login-inp" placeholder="请填写用途"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5.5">
              <el-form-item label="记账金额" prop="jizhangJine" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.body[index].jizhangJine" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5.5">
              <el-form-item label="可抵税额" prop="kediShuie" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue()" v-model="gongYingShang.body[index].kediShuie" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-button v-if="index > 0" class="custom-login-button"  type="primary"
                         @click="delLianXiRen(index)"><i class="el-icon-delete"></i>删除
              </el-button>
            </el-col>
          </el-row>
        </div>


        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addLianXiRen" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>添加明细
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save" style="margin-left: 30px"><i class="el-icon-finished"></i>保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="addClose" style="margin-left: 30px"><i class="el-icon-circle-close"></i>取消
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
          <el-select style="z-index:999;" v-model="Progongyingshang" clearable filterable placeholder="请选择供应商">
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
            label="单据编号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="riqi"
            label="日期"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="gongyingshang"
            label="供应商"
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

    <!-- 文件上传 -->
    <el-dialog title="" :visible.sync="fileDialog" width="80%">
      <input ref="up_file" type="file" id="up_file" @change="uploadSelect()" style="display: none">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" :loading="downloadLoading" @click="upload()"><i class="el-icon-document-add"></i>文件上传</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          :data="FileList" style="width: 100%">
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
            <el-button :loading="downloadLoading" @click="downloadFile(scope.row)" type="text" size="small"><i class="el-icon-download"></i>下载</el-button>
            <el-button :loading="downloadLoading" @click="deleteFile(scope.row)" type="text" size="small"><i class="el-icon-delete"></i>删除</el-button>
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
//ztt 导入跳转配置项的RouterUtil
import RouterUtil from "../utils/RouterU.js"
// ztt end
export default {
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      fileDialog:false,
      downloadLoading:false,
      FileList:[],
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      start_date:'',
      stop_date:'',
      dianpu:'',
      jizhangren:'',
      jizhang_zhanghu:'',
      Prostart:'',
      Prostop:'',
      Progongyingshang:'',
      CaiGou_Product:[],
      XiaLa_User:[],
      XiaLa_ShouKuanZhangHu:[],
      XiaLa_DianPu:[],
      XiaLa_GongYingShang:[],
      XiaLa_JiZhangMingXiLeiXing:[],
      XiaLa_JiZhangFenLei:[],
      Xiala_jizhangDanwei:[],
      XiaLa_DanJuLeiXing:[
        {
          name: '采购订单',
          label: '采购订单'
        },
        {
          name: '采购入库',
          label: '采购入库'
        },
        {
          name: '日常开支',
          label: '日常开支'
        },
      ],
      XiaLa_huokuanzhuangtai:[
        {
          name: '付定金',
          label: '付定金'
        },
        {
          name: '付欠款',
          label: '付欠款'
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
        shouzhi_type:'支出',
        huokuanzhuangtai:'',
        jizhangDanwei: '',
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
      multipleSelection: [],
      shoudongSelection: []
    }
  },
  created() {
    this.getUser();
    this.getXiaLa_User();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_ShouKuanZhangHu();
    this.getXiaLa_DianPu();
    this.getXiaLa_JiZhangFenLei();
    this.getXiaLa_JiZhangMingXiLeiXing();
    this.getXiaLa_jizhangDanwei();
  },
  methods: {
    //ztt 跳转到相应配置项页
    goToPeiZhi(url,pageType){
      RouterUtil.to(url + '?type=' + pageType);
    },
//ztt end
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

    shoudongSelectChange(val) {
      this.shoudongSelection = val;
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
      if(this.userPower.zhichuAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://localhost:8102/shouZhiMingXi/selectMaxDanHao"
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
            jizhangren: this.userInfo.name,
            jizhangType:'',
            jizhangZhanghu:'',
            jizhangJine:'',
            kediShuie:'',
            zhaiyao:'',
            shouzhi_type:'支出',
            huokuanzhuangtai:'',
            jizhangDanwei: '',
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
      if(this.gongYingShang.danjuLeixing == '采购入库'){
        this.gongYingShang.shoufuType = '付欠款';
      }else if(this.gongYingShang.danjuLeixing == '采购订单') {
        this.gongYingShang.shoufuType = '付定金';
      }else{
        this.gongYingShang.shoufuType = '';
      }
      this.gongYingShang.danjuBianhao = "";
    },
    //窗口table弹出
    selectProduct(){
      this.Prostart = ""
      this.Prostop = ""
      this.Progongyingshang = ""
      if(this.gongYingShang.danjuLeixing == '采购入库'){
        this.getRuKuProduct();
      }else if (this.gongYingShang.danjuLeixing == '采购订单') {
        this.getCaiGouProduct();
      }else if(this.gongYingShang.danjuLeixing == '日常开支'){
        MessageUtil.error("日常开支无需选择单据编号");
      }else if(this.gongYingShang.danjuLeixing == '') {
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

      let url = "http://localhost:8102/shouZhiMingXi/selectById"
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
      let url = "http://localhost:8102/caiGouDingDan/selectWeiFu"//ztt审核
      this.axios.post(url,{}).then(res => {//ztt审核
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
      let url = "http://localhost:8102/caiGouRuKu/selectWeiFu"
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
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://localhost:8102/user/queryUserInfoById"
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
      let poweruUrl = "http://localhost:8102/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          if(this.userPower.zhichuSel == '查看全部') {
            this.getAll();
          }else if(this.userPower.zhichuSel == '查看个人'){
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
    //读取下拉列表
    getXiaLa_User(){
      let url = "http://localhost:8102/user/getall"
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
      let url = "http://localhost:8102/gongYingShang/getAll"
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
    getXiaLa_ShouKuanZhangHu(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
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
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
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
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
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
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
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

    getXiaLa_jizhangDanwei(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"核算单位"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_jizhangDanwei = res.data.data;
          for(var i=0; i<this.XiaLa_jizhangDanwei.length; i++){
            this.XiaLa_jizhangDanwei[i].label = this.XiaLa_jizhangDanwei.name
          }
          console.log("记账单位类型下拉已获取");
        } else {
          console.log("记账单位类型下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      let url = "http://localhost:8102/shouZhiMingXi/getZhiChu"
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

    //查询全部
    getAllByName(){
      let url = "http://localhost:8102/shouZhiMingXi/getZhiChuByName"
      this.axios.post(url, {"yuangong_name":this.userInfo.name}).then(res => {
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
      if(this.userPower.zhichuSel == '查看全部'){
        this.getAll();
      }else if(this.userPower.zhichuSel == '查看个人'){
        this.getAllByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    query(){
      this.shenheButton = false
      if(this.userPower.zhichuSel == '查看全部'){
        this.queryList();
      }else if(this.userPower.zhichuSel == '查看个人'){
        this.queryListByName();
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
        dianpu:this.dianpu,
        jizhangren:this.jizhangren,
        jizhang_zhanghu:this.jizhang_zhanghu,
      }
      let url = "http://localhost:8102/shouZhiMingXi/queryZhiChuList"
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

    queryListByName(){
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
        yuangong_name:this.userInfo.name
      }
      let url = "http://localhost:8102/shouZhiMingXi/queryZhiChuListByName"
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
      let url = "http://localhost:8102/shouZhiMingXi/shouzhiAdd"
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
      let url = "http://localhost:8102/shouZhiMingXi/shouzhiUpd"
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

      if(this.userPower.zhichuUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
        return;
      }

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

      if(this.gongYingShang.zhaiyao == ''){
        MessageUtil.error("摘要不能为空");
        return;
      }

      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].mingxiType == ''){
          MessageUtil.error('第' + (i * 1+1) + '条明细未选择明细类型');
          return;
        }

        // 12.5
        if(this.gongYingShang.body[i].yongtu == ''){
          MessageUtil.error('第' + (i * 1+1+1) + '条用途不能为空');
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
      if(this.userPower.zhichuDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
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
        let url = "http://localhost:8102/shouZhiMingXi/delShouZhi";
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

    getfileList(row){
      console.log(row)
      this.p_id = row.id
      let url = "http://localhost:8102/fileTable/getAll"
      this.axios.post(url, {"id":row.id,"type":"收支记录"}).then(res => {
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
      let url = "http://localhost:8102/fileTable/getAll"
      this.axios.post(url, {"id":this.p_id,"type":"收支记录"}).then(res => {
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
      let url = "http://localhost:8102/fileTable/getById"
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
      let url = "http://localhost:8102/fileTable/deleteById"
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
          "type": "收支记录",
        };
        let url = "http://localhost:8102/fileTable/fileAdd"
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