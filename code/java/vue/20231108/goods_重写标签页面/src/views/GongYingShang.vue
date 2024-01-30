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
            prop="gongyingshangDengji"
            label="等级"
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
            prop="caigouyuan"
            label="采购员"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="jianzuoKehu"
            label="兼做客户"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="zhuyaoYewu"
            label="主要业务"
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

        <!--        供应商基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">供应商基本信息</p>
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
            <el-form-item label="等级" prop="gongyingshangDengji" class="custom-form-item" :rules="[{required:true,message:'等级不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.gongyingshangDengji" clearable filterable placeholder="请选择等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_Level"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--             ztt 快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '供应商等级')">添加</el-button>
              <!--            ztt end-->
<!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="地区" prop="suozaiDiqu" class="custom-form-item">
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
            <el-form-item label="采购员" prop="caigouyuan" class="custom-form-item" :rules="[{required:true,message:'采购员不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.caigouyuan" clearable filterable placeholder="请选择采购员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_CaiGouYuan"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.caigouyuan" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="兼做客户" prop="jianzuoKehu" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.jianzuoKehu" clearable filterable placeholder="请选择兼做客户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_JianZuoKeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jianzuoKehu" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="主要业务" prop="zhuyaoYewu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.zhuyaoYewu" class="custom-login-inp"></el-input>
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
                     @click="copyLianXiRen()"><i class="el-icon-document-copy"></i>复制主联系人
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
            <el-form-item label="单位名称" prop="userName" class="custom-form-item":rules="[{required:true,message:'单位名称不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDanwei" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="userName" class="custom-form-item" :rules="[{required:true,message:'纳税人识别号不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.shibiehao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDianhua" class="custom-login-inp" type="number"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="userName" class="custom-form-item" :rules="[{required:true,message:'开户银行不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="userName" class="custom-form-item"  :rules="[{required:true,message:'银行账号不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoZhanghao" class="custom-login-inp" type="number"></el-input>
            </el-form-item>
          </el-col>
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
              <el-form-item label="姓名" :prop="'body.' + index +'.name'" class="custom-form-item" :rules="[{required:true,message:'联系人不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
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
              <el-form-item label="电话" :prop="'body.' + index +'.phone'" class="custom-form-item" :rules="[{required:true,message:'电话不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].phone" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址" prop="address" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].address" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="类型" :prop="'body.' + index +'.type'" class="custom-form-item" :rules="[{required:true,message:'类型不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
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
                       @click="delLianXiRen(index)"><i class="el-icon-delete"></i>删除
            </el-button>
          </el-row>
        </div>



          <el-row :gutter="15">
            <el-col :span="4">
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button"  type="primary"
                           @click="addLianXiRen" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>添加联系人
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
                <el-button class="custom-login-button"  type="primary"
                           @click="addClose" style="margin-left: 30px"><i class="el-icon-circle-close"></i>取消
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
      FileList:[],
      XiaLa_Level:[],
      XiaLa_CaiGouYuan:[],
      XiaLa_Area:[],
      XiaLa_JianZuoKeHu:[
        {
          name:'是',
          label:'是',
        },
        {
          name:'否',
          label:'否',
        }
      ],
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
        id: 0,
        bianhao:'123123',
        name: '',
        gongyingshangDengji: '',
        suozaiDiqu: '',
        dizhi: '',
        beizhu: '',
        caigouyuan:'',
        jianzuoKehu:'',
        zhuyaoYewu:'',
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
            gongyingshangId:'',
          }
        ]
      },
      fileDialog:false,
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_Level();
    this.getXiaLa_CaiGouYuan();
    this.getUser();
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

    areaChange(value){
      console.log(value)
      this.gongYingShang.suozaiDiqu = value[0] + "-" + value[1] + "-" + value[2]
      this.XiaLa_Area = []
    },

    //新增窗口弹出
    addUser() {
      if(this.userPower.gongyingshangAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }

      let url = "http://yhocn.cn:8102/gongYingShang/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "GYS" + this_danhao

          this.gongYingShang = {
            bianhao:this_danhao,
            name: '',
            gongyingshangDengji: '',
            suozaiDiqu: '',
            dizhi: '',
            beizhu: '',
            caigouyuan:this.userInfo.name,
            jianzuoKehu:'',
            zhuyaoYewu:'',
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
                name:'',
                department:'',
                zhiwu:'',
                phone:'',
                address:'',
                type:'',
                gongyingshangId:'',
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

      let url = "http://yhocn.cn:8102/gongYingShang/selectById"
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

    getXiaLa_Level(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"供应商等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_Level = res.data.data;
          for(var i=0; i<this.XiaLa_Level.length; i++){
            this.XiaLa_Level[i].label = this.XiaLa_Level.name
          }
          console.log("供应商等级下拉已获取");
        } else {
          console.log("供应商等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_CaiGouYuan(){
      let url = "http://yhocn.cn:8102/user/fuzzyQuery"
      this.axios.post(url,{"keyword":""}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_CaiGouYuan = res.data.data;
          for(var i=0; i<this.XiaLa_CaiGouYuan.length; i++){
            this.XiaLa_CaiGouYuan[i].label = this.XiaLa_CaiGouYuan.name
          }
          console.log("供应商等级下拉已获取");
        } else {
          console.log("供应商等级下拉获取失败");
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
          if(this.userPower.gongyingshangSel == '是'){
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
      let url = "http://yhocn.cn:8102/gongYingShang/getAll"
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
      if(this.userPower.gongyingshangSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    //条件查询
    query(){
      if(this.userPower.gongyingshangSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        bianhao:this.bianhao,
        name:this.name
      }
      let url = "http://yhocn.cn:8102/gongYingShang/queryList"
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
        gongyingshangId:'',
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
      let url = "http://yhocn.cn:8102/gongYingShang/gongyingshangAdd"
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
      let url = "http://yhocn.cn:8102/gongYingShang/gongyingshangUpd"
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
        MessageUtil.error("请输入供应商名称");
        return;
      }
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        if(this.userPower.gongyingshangUpd != '是'){
          MessageUtil.error("无修改权限");
          return;
        }
        let url = "http://yhocn.cn:8102/gongYingShang/getChongFu";
        axios.post(url, {
          "name": this.gongYingShang.name,
          "id": this.gongYingShang.id,
        }).then(res => {
          var list = res.data.data
          if(list.length > 0){
            MessageUtil.error("已有相同名称的供应商");
          }else{
            this.updGongYingShang()
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })
      }else{
        let url = "http://yhocn.cn:8102/gongYingShang/getChongFu";
        axios.post(url, {
          "name": this.gongYingShang.name,
          "id": 0,
        }).then(res => {
          var list = res.data.data
          if(list.length > 0){
            MessageUtil.error("已有相同名称的供应商");
          }else{
            this.saveGongYingShang()
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })
      }
    },

    deleteClick(){
      if(this.userPower.gongyingshangDel != '是'){
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
        let url = "http://yhocn.cn:8102/gongYingShang/delGongYingShang";
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
      this.axios.post(url, {"id":row.id,"type":"供应商"}).then(res => {
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
      this.axios.post(url, {"id":this.p_id,"type":"供应商"}).then(res => {
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
          "type": "供应商",
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