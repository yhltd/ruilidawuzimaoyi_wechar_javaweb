<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="4">
          <el-input placeholder="请输入姓名" v-model="keyword" class="input-with-select"
                    @keyup.enter.native="fuzzyQuery()">
            <el-button :loading="fuzzy_query_loading" slot="append" icon="el-icon-search"

                       @click="fuzzyQuery()"></el-button>
          </el-input>
        </el-col>

        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()"><i class="el-icon-circle-plus-outline"></i>添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updateBtnClick()"><i class="el-icon-edit"></i>编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteUsers()"><i class="el-icon-delete"></i>删除</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          border
          style="width: 100%; "
          :height="tableHeight"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column fixed prop="id" label="编号" width="50">

        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="userName"
            label="账号"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="password"
            label="密码"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="power"
            label="权限"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="shenpi"
            label="审批人"
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


    <el-dialog title="编辑" :visible.sync="dialogFormVisible">

      <el-form :model="updUserInfo" ref="usrInfo" :rules="rules" label-width="100px"
               class="demo-info">

        <el-form-item label="账号" prop="userName" class="custom-form-item">
          <el-input :disabled="true" ref="acc_inp" v-model="updUserInfo.userName" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="updPassword" class="custom-form-item">
          <el-input :disabled="!show_upd_btn" ref="pwd_inp" v-model="updUserInfo.updPassword"
                    class="custom-login-inp"
                    @keyup.enter.native="inpFocus('name')"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name" class="custom-form-item">
          <el-input :disabled="!show_upd_btn" ref="name" v-model="updUserInfo.name" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="userName" class="custom-form-item">
          <el-select style="z-index:999;" :loading="Power_Loading" :disabled="!show_upd_btn" v-model="updUserInfo.power" placeholder="请选择">
            <el-option
                v-for="item in powers"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审批" prop="userName" class="custom-form-item">
          <el-select style="z-index:999;" :loading="SP_Loading" :disabled="!show_upd_btn" v-model="updUserInfo.shenpi" placeholder="请选择">
            <el-option
                v-for="item in shenpis"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- scope.row -->
        <el-form-item>
          <!-- 添加loading组件防止重复点击 -->
          <el-button v-show="show_upd_btn" class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="submitForm('usrInfo')"><i class="el-icon-check"></i>提交修改
          </el-button>
          <el-button v-show="!show_upd_btn" @click="changeBtnHid(); queryAllSelectInfo()" style="background-color: #57a8f5;color: #fff"><i class="el-icon-edit"></i>修改</el-button>
          <el-button v-show="show_upd_btn" @click="changeBtnHid();dialogFormVisible=false"><i class="el-icon-circle-close"></i>取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="添加账号" :visible.sync="addDialog">

      <el-form :model="userInfo" ref="addUsr" :rules="rules" label-width="100px"
               class="demo-info">

        <el-form-item label="账号" prop="userName" class="custom-form-item">
          <el-input ref="acc_inp" v-model="userInfo.userName" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" class="custom-form-item">
          <el-input ref="pwd_inp" v-model="userInfo.password" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('name_inp')"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="name" class="custom-form-item">
          <el-input ref="name_inp" v-model="userInfo.name" class="custom-login-inp"
          ></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="userName" class="custom-form-item">
          <el-select style="z-index:999;" :loading="Power_Loading" v-model="userInfo.power" placeholder="请选择">
            <el-option
                v-for="item in powers"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审批" prop="userName" class="custom-form-item">
          <el-select style="z-index:999;" :loading="SP_Loading" v-model="userInfo.shenpi" placeholder="请选择">
            <el-option
                v-for="item in shenpis"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <!-- 添加loading组件防止重复点击 -->
          <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="addFormSub('addUsr')"><i class="el-icon-circle-plus-outline"></i>添加
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
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      dialogFormVisible: false,
      addDialog: false,
      tableData: [],
      multipleSelection: [],
      del_popover_visible: false,
      visible: false,
      show_upd_btn: false,
      login_btn_loading: false,
      Power_Loading: false,
      SP_Loading: false,
      del_loading: false,
      fuzzy_query_loading: false,
      userInfo: {
        id: 1,
        name: '',
        userName: '',
        password: '',
        power: '',
        shenpi: '',
      },
      updUserInfo: {
        id: 1,
        name: '',
        userName: '',
        password: '',
        power: '',
        shenpi: '',
        updPassword: '',
      },
      powers: [],
      shenpis: [],
      rules: {
        userName: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
        ],
        updPassword: [
          {required: false, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
        ],
        addSP: [
          {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
        ],
        addPower: [
          {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
        ],
      },
      keyword: '',
    }
  },
  methods: {
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.currentPage = 1
      console.log(`每页 ${val} 条`);
    },
    /**
     * 查看按钮
     */
    handleClick(row) {
    },
    fuzzyQuery() {
      if(this.userPower.zhanghaoguanliSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8102/user/fuzzyQuery';
      axios.post(url, {'keyword': this.keyword}).then(res => {
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
            this.total = res.data.data.length;
            MessageUtil.success("共查询到" + this.tableData.length + "条数据")
            break;
          default: {
            MessageUtil.error("查询失败");
          }
        }
        this.fuzzy_query_loading = false;
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.fuzzy_query_loading = false;
      })
    },
    /**
     * 点击表格上的编辑按钮
     */
    updateBtnClick() {

      if(this.userPower.zhanghaoguanliUpd != '是'){
        MessageUtil.error("无修改权限");
        return;
      }

      let arr = this.multipleSelection;
      if (arr.length == 0) {
        MessageUtil.info("选择需要编辑的用户")
        return;
      } else if (arr.length > 1) {
        MessageUtil.info("只能同时编辑一个用户")
        return;
      }
      this.updUserInfo.id = arr[0].id;
      this.updUserInfo.name = arr[0].name;
      this.updUserInfo.userName = arr[0].userName;
      this.updUserInfo.password = arr[0].password;
      this.updUserInfo.power = arr[0].power;
      this.updUserInfo.shenpi = arr[0].shenpi;
      this.updUserInfo.updPassword = arr[0].password;
      this.dialogFormVisible = true;
    },

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
          if(this.userPower.zhanghaoguanliSel == '是'){
            this.queryUsersInfo();
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

    addUser() {
      if(this.userPower.zhanghaoguanliAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      this.userInfo = {
        name: '',
        userName: '',
        password: '',
        power: '',
        shenpi: '',
      }
      this.addDialog = true;
      this.queryAllSelectInfo();
    },
    addFormSub(formName) {
      this.login_btn_loading = true;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let URL = "http://localhost:8102/user/useradd";
          axios.post(
              URL, this.userInfo, true
          ).then(res => {
            switch (res.data.code) {
              case "00": {
                MessageUtil.success("添加成功");
                break;
              }
              case "10": {
                MessageUtil.error("用户无权限");
                break;
              }
            }
            this.queryUsersInfo();
            this.addDialog = false;
            this.login_btn_loading = false;
          }).catch(() => {
            MessageUtil.error("网络异常");
            this.login_btn_loading = true;
          })
        } else {
          MessageUtil.error('格式错误');
          this.login_btn_loading = false;
        }
      })
    },
    deleteUsers() {
      if(this.userPower.zhanghaoguanliDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
      this.del_popover_visible = false;
      let list = this.multipleSelection;
      this.del_loading = true;
      if (list == undefined || list == null || list.length == 0) {
        MessageUtil.info("至少选择一条数据");
        return;
      }

      this.$confirm('是否删除当前选中的信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        let tmp = [];
        for (let i of list) {
          tmp.push(i.id);
        }
        let url = "http://localhost:8102/user/delUsers";
        axios.post(url, {"list": tmp}).then(res => {
          switch (res.data.code) {
            case "00": {
              MessageUtil.success("删除成功");
              list.length = 0;
              this.queryUsersInfo();
              break;
            }
            case "02": {
              MessageUtil.error(res.data.msg);
              break;
            }
            default: {
              MessageUtil.error("网络异常");
            }
          }
          this.del_popover_visible = false;
          this.del_loading = false;
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
    toggleSelection(rows) {
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
    },
    submitForm(formName) {
      let info = this.updUserInfo;
      this.login_btn_loading = true;
      console.log("更新表单：", this.updUserInfo)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          info.password = info.updPassword;
          let URL = "http://localhost:8102/user/updUsrInfo";
          axios.post(
              URL, info, true
          ).then(res => {
            switch (res.data.code) {
              case "00": {
                MessageUtil.success("修改成功");
                break;
              }
              case "06": {
                MessageUtil.error("账号或密码错误");
                break;
              }
              case "10": {
                MessageUtil.error("用户无访问权限");
                break;
              }
            }
            this.queryUsersInfo();
            this.dialogFormVisible = false;
            this.login_btn_loading = false;
            this.changeBtnHid();
            this.multipleSelection.length = 0;
          }).catch(() => {
            MessageUtil.error("网络异常");
            this.changeBtnHid();
          })
        } else {
          MessageUtil.error('账号或密码格式错误');
          this.login_btn_loading = false;
          this.inpFocus("acc_inp");
        }
      })
    },
    queryAllSelectInfo() {
      this.queryPowers()
      this.queryShenPis()
    },
    queryPowers() {
      this.Power_Loading = true;
      let url = "http://localhost:8102/userpower/queryPower";
      axios.post(url, {"name": ""}).then(res => {
        switch (res.data.code) {
          case '00':
            this.powers = res.data.data;
            break;
          default: {
            MessageUtil.error("获取权限失败");
          }
        }
        this.Power_Loading = false;
      }).catch(() => {
        MessageUtil.error("网络异常");
      }).finally(() => {
        this.Power_Loading = false;
      })
    },
    queryShenPis() {
      this.SP_Loading = true
      let url = "http://localhost:8102/user/queryShenpis";
      axios.post(url).then(res => {
        switch (res.data.code) {
          case '00':
            this.shenpis = res.data.data;
            break;
          default: {
            MessageUtil.error("获取审批人失败");
          }
        }
        this.SP_Loading = false;
      }).catch(() => {
        this.SP_Loading = false;
        MessageUtil.error("网络异常");
      })
    },
    inpBlur(refName) {
      this.$refs[refName].blur();
    },
    inpFocus(refName) {
      this.$refs[refName].focus();
    },
    changeBtnHid() {
      this.login_btn_loading = false;
      this.show_upd_btn = !this.show_upd_btn;
    },
    resetForm() {
      let url = "http://localhost:8102/user/queryUserInfoById";
      axios.post(url, {'id': this.userInfo.id}).then(res => {
        switch (res.data.code) {
          case '00':
            this.userInfo = res.data.data;
            break;
          default: {
            MessageUtil.error("获取审批人失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    queryUsersInfo() {
      let url = "http://localhost:8102/user/queryAllUsers";
      axios.post(url).then(res => {
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
            this.total = res.data.data.length;
            break;
          default: {
            MessageUtil.error("获取审批人失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    }
  },
  created() {
    this.getUser();
    this.queryPowers()
    this.queryShenPis()
  }
}
</script>

<style scoped>

.demo-info {
  width: 400px;
  margin: 0 auto;
}

.page {
  margin: 0 auto;
}
</style>