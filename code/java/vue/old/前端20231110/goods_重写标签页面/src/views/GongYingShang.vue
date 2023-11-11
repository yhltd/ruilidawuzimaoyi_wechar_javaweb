<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-input placeholder="编号" v-model="bianhao" class="input-with-select"
                  @keyup.enter.native="fuzzyQuery()">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select"
                  @keyup.enter.native="fuzzyQuery()">
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
        <el-button type="primary" @click="deleteBtnClick()">删除</el-button>
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
    </el-table>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

<!--        供应商基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="编号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="名称" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="等级" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="地区" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="采购员" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="兼做客户" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="主要业务" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

<!--        收件信息-->
        <el-row :gutter="15">

          <el-col :span="6">
            <el-form-item label="收件人姓名" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

<!--        开票信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="单位名称" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
        </el-row>



        <el-form-item>
          <!-- 添加loading组件防止重复点击 -->
          <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="addFormSub('addUsr')">添加
          </el-button>

          <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="addFormSub('addUsr')">取消
          </el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


    <el-dialog title="修改供应商" :visible.sync="updDialog">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <el-row :gutter="15">
          <el-col :span="10">
            <el-form-item label="编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="名称" prop="name" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="等级" prop="gongyingshangDengji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="地区" prop="suozaiDiqu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="地址" prop="dizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="采购员" prop="caigouyuan" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="兼做客户" prop="jianzuoKehu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="主要业务" prop="zhuyaoYewu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人姓名" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.head.bianhao" class="custom-login-inp"
                        @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
            </el-form-item>
          </el-col>
        </el-row>



        <el-form-item>
          <!-- 添加loading组件防止重复点击 -->
          <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="addFormSub('addUsr')">添加
          </el-button>

          <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="addFormSub('addUsr')">取消
          </el-button>
        </el-form-item>
      </el-form>
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
      name:'',
      bianhao:'',
      gongYingShang: {
        head:{
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
        },
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
      addDialog: false,
      updDialog: false,
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      multipleSelection: []
    }
  },
  created() {
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

    //新增窗口弹出
    addUser() {
      this.gongYingShang = {
        head:{
          id: 0,
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
        },
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
      }

      this.addDialog = true;
      this.queryAllSelectInfo();
    },

    //修改窗口弹出
    updUser() {
      this.gongYingShang = {
        head:{
          id: 0,
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
        },
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
      }

      this.updDialog = true;
      this.queryAllSelectInfo();
    },

    //查询全部
    getAll(){
      let url = "http://localhost:8081/gongYingShang/getAll"
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
      this.bianhao = ""
      this.name = ""
      this.getAll()
    },

    //条件查询
    query(){
      var date = {
        bianhao:this.bianhao,
        name:this.name
      }
      let url = "http://localhost:8081/gongYingShang/queryList"
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

  }
}
</script>