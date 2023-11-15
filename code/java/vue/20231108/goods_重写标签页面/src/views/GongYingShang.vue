<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-input placeholder="编号" v-model="bianhao" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select">
        </el-input>
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
            <el-form-item label="名称" prop="name" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.name" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="等级" prop="gongyingshangDengji" class="custom-form-item">
              <el-select v-model="gongYingShang.gongyingshangDengji" clearable filterable placeholder="请选择等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_Level"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="地区" prop="suozaiDiqu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.suozaiDiqu" class="custom-login-inp"></el-input>
            </el-form-item>
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
            <el-form-item label="采购员" prop="caigouyuan" class="custom-form-item">
              <el-select v-model="gongYingShang.caigouyuan" clearable filterable placeholder="请选择采购员">
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
              <el-select v-model="gongYingShang.jianzuoKehu" clearable filterable placeholder="请选择兼做客户">
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
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianPhone" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人地址" prop="shoujianDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!--        开票信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">开票信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="单位名称" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDanwei" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="userName" class="custom-form-item">
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
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDianhua" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoZhanghao" class="custom-login-inp"></el-input>
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
              <el-form-item label="姓名" prop="name" class="custom-form-item">
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
              <el-form-item label="电话" prop="phone" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].phone" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址" prop="address" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].address" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="类型" prop="type" class="custom-form-item">
                <el-select :index="index" @change="selectLeiXing(index,value)" v-model="gongYingShang.body[index].type" clearable filterable placeholder="请选择联系人类型">
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
            <el-button v-if="index > 0" class="custom-login-button" :loading="login_btn_loading" type="primary"
                       @click="delLianXiRen(index)">删除
            </el-button>
          </el-row>
        </div>



          <el-row :gutter="15">
            <el-col :span="4">
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="addLianXiRen">添加联系人
                </el-button>
              </div>
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="save">保存
                </el-button>
              </div>
            </el-col>
            <el-col :span="4">
              <div style="display: flex">
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="addClose">取消
                </el-button>
              </div>
            </el-col>
          </el-row>



      </el-form>
    </el-dialog>

    <el-dialog title="" :visible.sync="fileDialog" width="80%">

      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" @click="upload()">文件上传</el-button>
        </el-col>
      </el-row>

      <el-table :data="FileList" :row-class-name="rowClassName" @row-click="rowClick" style="width: 100%">
        <el-table-column
            prop="fileName"
            label="文件名"
            width="auto"
        ></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template>
            <el-button @click="downloadFile(scope.row)" type="text" size="small">下载</el-button>
            <el-button @click="deleteFile(scope.row)" type="text" size="small">删除</el-button>
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
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      name:'',
      bianhao:'',
      FileList:[],
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
        shoujian_dizhi:'',
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

    //新增窗口弹出
    addUser() {
      if(this.userPower.gongyingshangAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      this.gongYingShang = {
          bianhao:'',
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
          shoujian_dizhi:'',
          kaipiaoDanwei:'',
          shibiehao:'',
          kaipiaoDizhi:'',
          kaipiaoDianhua:'',
          kaipiaoYinhang:'',
          kaipiaoZhanghao:'',
        body:[
          {
            name:'1',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            gongyingshangId:'',
          },
          {
            name:'2',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            gongyingshangId:'',
          }
        ]
      }

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

      let url = "http://localhost:8081/gongYingShang/selectById"
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
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
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
      let url = "http://localhost:8081/user/fuzzyQuery"
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
      let url = "http://localhost:8081/gongYingShang/getAll"
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
      let url = "http://localhost:8081/gongYingShang/queryList"
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
      let url = "http://localhost:8081/gongYingShang/gongyingshangAdd"
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
      let url = "http://localhost:8081/gongYingShang/gongyingshangUpd"
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
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        if(this.userPower.gongyingshangUpd != '是'){
          MessageUtil.error("无修改权限");
          return;
        }
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
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
        let url = "http://localhost:8081/gongYingShang/delGongYingShang";
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
      let url = "http://localhost:8081/fileTable/getAll"
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

    downloadFile(row){
      console.log(row)
    },

    deleteFile(row){
      console.log(row)
    }


  }
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
</style>