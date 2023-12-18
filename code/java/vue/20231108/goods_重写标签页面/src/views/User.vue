<template>
  <el-container style="height: 100%;" direction="vertical">
    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-input placeholder="编号" v-model="bianhao" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="名称" v-model="name" class="input-with-select">
          </el-input>
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
          <el-button size="small" round type="danger" @click="deleteClick()">删除</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
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
            prop="bianhao"
            label="编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="name"
            label="名称"
            width="120">
        </el-table-column>
        <el-table-column
            prop="type"
            label="客户分类"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shangjiDanwei"
            label="上级单位"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kehuDengji"
            label="客户等级"
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
            prop="suozaiDiqu"
            label="所在地区"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="dizhi"
            label="地址"
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
            prop="yewuyuan"
            label="业务员"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianName"
            label="收件人姓名"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianPhone"
            label="收件人电话"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianDizhi"
            label="收件地址"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoDanwei"
            label="开票单位名称"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shibiehao"
            label="纳税人识别号"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoDizhi"
            label="开票地址"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoDianhua"
            label="开票电话"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoYinhang"
            label="开户银行"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoZhanghao"
            label="银行账号"
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

      <el-form :model="gongYingShang" :rules="rules" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        供应商基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">客户基本信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="名称" prop="name" class="custom-form-item" :rules="[{required:true,message:'名称不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.name" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10"><!--  ztt修改宽度-->
            <el-form-item label="客户分类" prop="type" class="custom-form-item" :rules="[{required:true,message:'客户分类不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.type" clearable filterable placeholder="请选择客户分类">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_Type"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              ztt快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '客户分类')">添加</el-button>
              <!--            ztt end-->
            </el-form-item>
          </el-col>
         <!--ztt 上级单位下拉框，并模糊查询-->
          <el-col :span="6">
            <el-form-item label="上级单位" prop="shangjiDanwei" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.shangjiDanwei" clearable filterable placeholder="请选择或输入上级单位">
                <el-option
                    v-for="item in XiaLa_ShangJiDanWei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name"></el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.shangjiDanwei" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <!--            ztt end-->
<!--          <el-col :span="6">-->
<!--            <el-form-item label="上级单位" prop="shangjiDanwei" class="custom-form-item">-->
<!--              <el-input ref="acc_inp" v-model="gongYingShang.shangjiDanwei" class="custom-login-inp"></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="10"><!--  ztt修改宽度-->
            <el-form-item label="客户等级" prop="kehuDengji" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.kehuDengji" clearable filterable placeholder="请选择客户等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_KeHuDengJi"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--             ztt 快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '客户等级')">添加</el-button>
              <!--            ztt end-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价格等级" prop="jiageDengji" class="custom-form-item" :rules="[{required:true,message:'价格等级不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.jiageDengji" clearable filterable placeholder="请选择价格等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_JiaGeDengJi"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="所在地区" prop="suozaiDiqu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.suozaiDiqu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-cascader separator="-" :filterable="true" v-model="XiaLa_Area" class="custom-login-inp" :options="optionsnative_place" @change="areaChange"/>
          </el-col>
          <el-col :span="6">
            <el-form-item label="地址" prop="dizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.dizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item" :rules="[{required:true,message:'业务员不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.yewuyuan" clearable filterable placeholder="请选择业务员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_YeWuYuan"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.caigouyuan" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
        </el-row>

        <!--        收件信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">收件人信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">

          <el-col :span="6">
            <el-form-item label="收件人姓名" prop="shoujianName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianName" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人电话" prop="shoujianPhone" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianPhone" class="custom-login-inp" type="number"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人地址" prop="shoujianDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

          <el-button class="custom-login-button"  type="primary"
                     @click="copyLianXiRen()">复制主联系人
          </el-button>

        </el-row>

        <!--        开票信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">开票信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="单位名称" prop="kaipiaoDanwei" class="custom-form-item" :rules="[{required:true,message:'单位名称不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDanwei" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="shibiehao" class="custom-form-item" :rules="[{required:true,message:'纳税人识别号不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.shibiehao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票地址" prop="kaipiaoDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话" prop="kaipiaoDianhua" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDianhua" class="custom-login-inp" type="number"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="kaipiaoYinhang" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="kaipiaoZhanghao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoZhanghao" class="custom-login-inp" type="number"></el-input>
            </el-form-item>
          </el-col>
<!--          ztt 开票信息关联-->
          <el-button class="custom-login-button"  type="primary"
                     @click="addKaiPiaoXinXi" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>复制开票信息
          </el-button>
          <!--            ztt end-->
        </el-row>

        <div v-for="(item, index) in gongYingShang.body">
          <!--        联系人信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">联系人信息{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="姓名" :prop="'body.' + index + '.name'" class="custom-form-item" :rules="[{required:true,message:'姓名不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].name" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="部门" prop="department" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].department" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="职务" prop="zhiwu" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhiwu" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="电话" :prop="'body.' + index + '.phone'" class="custom-form-item" :rules="[{required:true,message:'电话不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].phone" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址" prop="address" class="custom-form-item" >
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].address" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="类型" :prop="'body.' + index + '.type'" class="custom-form-item" :rules="[{required:true,message:'类型不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-select style="z-index:999;" :index="index" @change="selectLeiXing(index,value)" v-model="gongYingShang.body[index].type" clearable filterable placeholder="请选择联系人类型">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_LianXiRenLeiXing"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].type" class="custom-login-inp"></el-input>-->
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
                         @click="addLianXiRen">添加联系人
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
              <el-button class="custom-login-button"  type="primary"
                         @click="addClose">取消
              </el-button>
            </div>
          </el-col>
        </el-row>



      </el-form>
    </el-dialog>

    <el-dialog title="" :visible.sync="fileDialog" width="80%">
      <input ref="up_file" type="file" id="up_file" @change="uploadSelect()" style="display: none">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" :loading="downloadLoading" @click="upload()">文件上传</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
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
import { provinceAndCityData,regionData,pcTextArr,pcaTextArr, codeToText} from 'element-china-area-data'
//ztt 导入跳转配置项的RouterUtil
import RouterUtil from "../utils/RouterU.js"
// ztt end

const optionsnative_place = pcaTextArr
export default {
  data() {
    return {
      provinceAndCityData,
      regionData,
      pcTextArr,
      pcaTextArr,
      codeToText,
      optionsnative_place,
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      downloadLoading:false,
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      name:'',
      bianhao:'',
      ShuiLv:{},
      FileList:[],
      XiaLa_Type :[],
      XiaLa_KeHuDengJi:[],
      XiaLa_Area:[],
      XiaLa_JiaGeDengJi:[],
      XiaLa_YeWuYuan:[],
      XiaLa_ShangJiDanWei:[],//ztt上级单位下拉框
      XiaLa_LianXiRenLeiXing:[
        {
          name:'联系人',
          label:'联系人',
        },
        {
          name:'主联系人',
          label:'主联系人',
        }
      ],
      gongYingShang: {
        bianhao:'',
        name: '',
        type: '',
        shangjiDanwei: '',
        kehuDengji: '',
        jiageDengji: '',
        suozaiDiqu:'',
        dizhi:'',
        beizhu:'',
        yewuyuan:'',
        shoujianName:'',
        shoujianPhone:'',
        shoujianDizhi:'',
        kaipiaoDanwei:'',
        shibiehao:'',
        kaipiaoDizhi:'',
        kaipiaoDianhua:'',
        kaipiaoYinhang:'',
        kaipiaoZhanghao:'',
        body:[
          {
            id:0,
            name:'',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            customerId:'',
          }
        ]
      },
      fileDialog:false,
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: [],
      rules: {
        phone: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {min: 11, max: 11, message: '长度为11位', trigger: 'blur'}
        ],
        kaipiaoDianhua: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {min: 11, max: 11, message: '长度为11位', trigger: 'blur'}
        ],
        kaipiaoZhanghao: [
          {required: true, message: '请输入银行账号', trigger: 'blur'},
          {min: 16, max: 19, message: '长度在 16 到 19 个字符', trigger: 'blur'}
        ],
        shoujianPhone: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {min: 11, max: 11, message: '长度为11位', trigger: 'blur'}
        ],
      },
    }
  },
  created() {
    this.getXiaLa_Type();
    this.getXiaLa_KeHuDengJi();
    this.getXiaLa_JiaGeDengJi()
    this.getXiaLa_YeWuYuan()
    this.getUser();
    this.getXiaLa_ShangJiDanWei();//ztt上级单位下拉框
  },
  methods: {
    // ztt开票信息
    addKaiPiaoXinXi(){
      if(this.userPower.kehuAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      var this_list = this.gongYingShang
      if(this_list.shangjiDanwei != ''){
        let url = "http://yhocn.cn:8102/customer/getAll"
        this.axios(url).then(res =>{
          console.log("shuju")
          console.log(res.data.data)

          if(res.data.code == '00'){
            var this_danhao = Math.trunc(res.data.data[0].bianhao)
            console.log(this_danhao)
            this_danhao = PrefixInteger(this_danhao,6)
            console.log(this_danhao)
            this_danhao = "KH" + this_danhao
            this.kaipiaoxinxi = res.data.data
            for (var i=0; i<this.kaipiaoxinxi.length; i++){
              if(this_list.shangjiDanwei == this.kaipiaoxinxi[i].kaipiaoDanwei){
                var info = this.kaipiaoxinxi[i]
                console.log("kaipiao")
                console.log(info)
                this.gongYingShang={
                  bianhao:this_danhao,
                  name: '',
                  type: '',
                  shangjiDanwei: this_list.shangjiDanwei,
                  kehuDengji: '',
                  jiageDengji: '',
                  suozaiDiqu:'',
                  dizhi:'',
                  beizhu:'',
                  yewuyuan:this.userInfo.name,
                  shoujianName:'',
                  shoujianPhone:'',
                  shoujianDizhi:'',
                  kaipiaoDanwei:info.kaipiaoDanwei,
                  shibiehao:info.shibiehao,
                  kaipiaoDizhi:info.kaipiaoDizhi,
                  kaipiaoDianhua:info.kaipiaoDianhua,
                  kaipiaoYinhang:info.kaipiaoYinhang,
                  kaipiaoZhanghao:info.kaipiaoZhanghao,
                  body:[
                    {
                      id:0,
                      name:'',
                      department:'',
                      zhiwu:'',
                      phone:'',
                      address:'',
                      type:'',
                      customerId:'',
                    }
                  ]
                }
              }
            }
          }
        })
      }
    },
    //ztt end
    //ztt 上级单位下拉框
    getXiaLa_ShangJiDanWei(){
      let url = "http://yhocn.cn:8102/customer/getAll"
      this.axios(url,{"keyword":""}).then(res =>{
        console.log(res.data.data)
        if (res.data.code == '00') {
          this.XiaLa_ShangJiDanWei = res.data.data;
          console.log(res.data.data)
          for (var i=0;i<this.XiaLa_ShangJiDanWei.length;i++){
            this.XiaLa_ShangJiDanWei[i].label = this.XiaLa_ShangJiDanWei[i].shangjiDanwei
            console.log(this.XiaLa_ShangJiDanWei[i].shangjiDanwei)
          }
          console.log("上级单位下拉已获取");
        } else {
          console.log("上级单位下拉获取失败")
        }
      }).catch(()=>{
        MessageUtil.error("网络异常")
      })
    },
    //ztt end
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

    areaChange(value){
      console.log(value)
      this.gongYingShang.suozaiDiqu = value[0] + "-" + value[1] + "-" + value[2]
      this.XiaLa_Area = []
    },

    //新增窗口弹出
    addUser() {
      if(this.userPower.kehuAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }

      let url = "http://yhocn.cn:8102/customer/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "KH" + this_danhao

          this.gongYingShang = {
            bianhao:this_danhao,
            name: '',
            type: '',
            shangjiDanwei: '',
            kehuDengji: '',
            jiageDengji: '',
            suozaiDiqu:'',
            dizhi:'',
            beizhu:'',
            yewuyuan:this.userInfo.name,
            shoujianName:'',
            shoujianPhone:'',
            shoujianDizhi:'',
            kaipiaoDanwei:'',
            shibiehao:'',
            kaipiaoDizhi:'',
            kaipiaoDianhua:'',
            kaipiaoYinhang:'',
            kaipiaoZhanghao:'',
            body:[
              {
                id:0,
                name:'',
                department:'',
                zhiwu:'',
                phone:'',
                address:'',
                type:'',
                customerId:'',
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

      let url = "http://yhocn.cn:8102/customer/selectById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
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

    getXiaLa_Type(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"客户分类"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_Type = res.data.data;
          for(var i=0; i<this.XiaLa_Type.length; i++){
            this.XiaLa_Type[i].label = this.XiaLa_Type.name
          }
          console.log("客户分类下拉已获取");
        } else {
          console.log("客户分类下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_KeHuDengJi(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"客户等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_KeHuDengJi = res.data.data;
          for(var i=0; i<this.XiaLa_KeHuDengJi.length; i++){
            this.XiaLa_KeHuDengJi[i].label = this.XiaLa_KeHuDengJi.name
          }
          console.log("客户等级下拉已获取");
        } else {
          console.log("客户等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_JiaGeDengJi(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"价格等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_JiaGeDengJi = res.data.data;
          for(var i=0; i<this.XiaLa_JiaGeDengJi.length; i++){
            this.XiaLa_JiaGeDengJi[i].label = this.XiaLa_JiaGeDengJi.name
          }
          console.log("价格等级下拉已获取");
        } else {
          console.log("价格等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },


    getXiaLa_YeWuYuan(){
      let url = "http://yhocn.cn:8102/user/fuzzyQuery"
      this.axios.post(url,{"keyword":""}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_YeWuYuan = res.data.data;
          for(var i=0; i<this.XiaLa_YeWuYuan.length; i++){
            this.XiaLa_YeWuYuan[i].label = this.XiaLa_YeWuYuan.name
          }
          console.log("业务员下拉已获取");
        } else {
          console.log("业务员下拉获取失败");
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
          if(this.userPower.kehuSel == '是'){
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
      if(this.userPower.gongyingshangSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://yhocn.cn:8102/customer/getAll"
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
      if(this.userPower.kehuSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    //条件查询
    query(){
      if(this.userPower.kehuSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        bianhao:this.bianhao,
        name:this.name
      }
      let url = "http://yhocn.cn:8102/customer/queryList"
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
        id:0,
        name:'',
        department:'',
        zhiwu:'',
        phone:'',
        address:'',
        type:'',
        customerId:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    selectLeiXing(index,value){
      console.log(index)
      console.log(value)
      console.log(this.gongYingShang.body)
      if(this.gongYingShang.body[index].type == '主联系人'){
        for(var i=0; i<this.gongYingShang.body.length; i++){
          if(i != index){
            this.gongYingShang.body[i].type = "联系人"
          }
        }
      }
    },

    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://yhocn.cn:8102/customer/customerAdd"
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
      let url = "http://yhocn.cn:8102/customer/customerUpd"
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
      if(this.gongYingShang.name == ''){
        MessageUtil.error("请输入客户名称");
        return;
      }
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        if(this.userPower.kehuUpd != '是'){
          MessageUtil.error("无修改权限");
          return;
        }
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.userPower.kehuDel != '是'){
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
        let url = "http://yhocn.cn:8102/customer/delCustomer";
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


    copyLianXiRen(){
      this.$confirm('是否复制主联系人信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var list = this.gongYingShang.body
        for(var i=0; i<list.length; i++){
          if(list[i].type == '主联系人'){
            this.gongYingShang.shoujianName = list[i].name
            this.gongYingShang.shoujianPhone = list[i].phone
            this.gongYingShang.shoujianDizhi = list[i].address
          }
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
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
      let url = "http://yhocn.cn:8102/fileTable/getAll"
      this.axios.post(url, {"id":row.id,"type":"客户"}).then(res => {
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
      let url = "http://yhocn.cn:8102/fileTable/getAll"
      this.axios.post(url, {"id":this.p_id,"type":"客户"}).then(res => {
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
      let url = "http://yhocn.cn:8102/fileTable/getById"
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
      let url = "http://yhocn.cn:8102/fileTable/deleteById"
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
      if(file.length == 0){
        this.downloadLoading = false
      }
      var oFReader = new FileReader();
      var this_file = file[0];
      var fileName = file[0].name;
      var obj = [];
      oFReader.readAsDataURL(this_file);
      oFReader.onload = oFRevent => {
        console.log('oFRevent----', oFRevent)
        this_file = oFRevent.target.result;
        obj = {
          "fileId": this.p_id,
          "fileName": fileName,
          "file": this_file,
          "type": "客户",
        };
        let url = "http://yhocn.cn:8102/fileTable/fileAdd"
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
.el-cascader-panel {
  height: 300px;
}
</style>