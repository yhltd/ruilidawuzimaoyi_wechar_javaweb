<template>
  <!-- 配置类通用页面
  适配页面： 仓库、店铺、供应商等级、核算单位、价格等级、
  价格上浮率、记账明细类型、记账分类、客户等级、商品分类、收款账户
  质保等级
  -->
  <el-container>
    <el-header style="background-color: transparent;">
      <el-row :gutter="20">
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button size="small" round @click="addConfig();" type="primary">添加</el-button>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button size="small" round @click="updConfig();" type="primary">修改</el-button>
          <!--      <el-button @click="" type="danger">删除</el-button>-->
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button size="small" round type="danger" @click="delConfig();">删除</el-button>
          <!--      <el-button @click="" type="danger">删除</el-button>-->
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button v-if="PAGE_TYPE == '仓库' || PAGE_TYPE == '店铺' || PAGE_TYPE == '核算单位' " @click="moRenConfig();" type="primary">设置默认</el-button>
          <!--      <el-button @click="" type="danger">删除</el-button>-->
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-popover
              placement="bottom"
              width="160"
              v-model="del_popover_visible"
          >
            <p>确定要删除选中的账号吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="del_popover_visible = false">取消</el-button>
              <el-button type="danger" size="mini" @click="delConfig()">确定</el-button>
            </div>
            <el-button @click="del_popover_visible=true">删除</el-button>
          </el-popover>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          style="width: 100%;"
          :height="tableHeight"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
<!--        <el-table-column fixed prop="id" label="编号" width="50">-->

<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            fixed-->
<!--            prop="name"-->
<!--            label="id"-->
<!--            width="auto">-->
<!--        </el-table-column>-->
        <el-table-column
            prop="name"
            :label=PAGE_TYPE
            width="auto">
        </el-table-column>
        <el-table-column
            prop="moren"
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


    <el-dialog title="添加配置" :visible.sync="addDialog">
      <el-form :model="addForm" ref="addConfig" :rules="rules" label-width="100px"
               class="demo-info">
        <el-form-item label="配置" prop="addName" class="custom-form-item">
          <el-input ref="name" v-model="addForm.name" class="custom-login-inp"
                    @keyup.enter.native="addFormSub('addConfig')"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="custom-login-button" type="primary"
                     @click="addFormSub('addConfig')">添加
          </el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="修改配置" :visible.sync="updDialog">
      <el-form :model="addForm" ref="updConfig" :rules="rules" label-width="100px"
               class="demo-info">
        <el-form-item label="配置" prop="updName" class="custom-form-item">
          <el-input ref="name" v-model="addForm.name" class="custom-login-inp"
                    @keyup.enter.native="addFormSub('addConfig')"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="custom-login-button" type="primary"
                     @click="updFormSub('updConfig')">修改
          </el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-container>

</template>

<script>
import router from "@/router";
import MessageUtil from "@/utils/MessageUtil";
import axios from "axios";

/**
 * 重置页面标题
 *
 * 会增加`配置`字样后缀
 * @param title
 */
const changePageTitle = (title) => {
  document.title = title + "配置";
}
export default {
  name: "BasePeiZhi",
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      PAGE_TYPE: '', // 页面类型以及页面标题 不可轻易调用
      tableData: [],
      addForm: {
        id: 0,
        name: '',
        type: this.PAGE_TYPE,
      },
      dialogFormVisible: false,
      addDialog: false,
      updDialog: false,
      del_popover_visible: false,
      fuzzy_query_loading: false,
      tableCheckList: [],
      keyword: '',
      rules: [
        {
          addName: [
            {required: true, message: '请输入配置名称', trigger: 'blur'},
            {min: 5, max: 15, message: '长度在 1 到 50 个字符', trigger: 'blur'}
          ],
          updName: [
            {required: false, message: '请输入配置名称', trigger: 'blur'},
            {min: 2, max: 15, message: '长度在 1 到 50 个字符', trigger: 'blur'}
          ],
        }
      ]
    }
  },
  methods: {
    addFormSub(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addForm.type = this.PAGE_TYPE
          let url = "http://localhost:8102/peizhi/peizhiAdd";
          this.axios.post(url, this.addForm).then(res => {
            if (res.data.code == '00') {
              MessageUtil.success("添加成功");
              this.addDialog = false;
              this.queryByType();
            } else {
              MessageUtil.error("添加失败")
            }
          }).catch(() => {
            MessageUtil.error("网络异常")
          });
        } else {
          MessageUtil.error("格式错误")
        }
      })
    },
    updFormSub(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = "http://localhost:8102/peizhi/peizhiUpd";
          this.axios.post(url, this.addForm).then(res => {
            if (res.data.code == '00') {
              MessageUtil.success("添加成功");
              this.queryByType();
              this.updDialog = false;
            } else {
              MessageUtil.error("添加失败")
            }
          }).catch(() => {
            MessageUtil.error("网络异常")
          });
        } else {
          MessageUtil.error("格式错误")
        }
      })
    },
    addConfig() {
      if(this.userPower.peizhiAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      this.addForm = {
        name:'',
        type:this.PAGE_TYPE,
      };
      this.addDialog = true;
      return true;
    },
    updConfig() {
      if(this.userPower.peizhiUpd != '是'){
        MessageUtil.error("无修改权限");
        return;
      }
      let list = this.multipleSelection;
      if (list.length > 1) {
        MessageUtil.warning("只能同时更改一条数据");
        return false;
      } else if (list.length == 0) {
        MessageUtil.warning("需要选中一条数据")
        return false;
      }
      this.addForm = {
        id:list[0].id,
        name:list[0].name,
        type:list[0].type
      };
      this.updDialog = true;
      return true;
    },
    delConfig() {
      if(this.userPower.peizhiDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
      let list = this.multipleSelection;
      if (list.length == 0) {
        MessageUtil.warning("至少选择一条数据");
        return;
      }
      this.$confirm('是否删除选中信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let tmp = [];
        for (let i of list) {
          tmp.push(i.id);
        }
        let url = "http://localhost:8102/peizhi/delPeizhi";
        axios.post(url, {"list": tmp}).then(res => {
          switch (res.data.code) {
            case "00": {
              MessageUtil.success("删除成功");
              list.length = 0;
              this.queryByType();
              break;
            }
            default: {
              MessageUtil.error("网络异常");
            }
          }
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

    moRenConfig() {
      let list = this.multipleSelection;
      if (list.length == 0) {
        MessageUtil.warning("至少选择一条数据");
        return;
      }
      this.$confirm('是否将当前信息设置为默认?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let tmp = [];
        for (let i of list) {
          tmp.push(i.id);
        }
        let url = "http://localhost:8102/user/updateMoRen";
        axios.post(url,
      {"type": this.PAGE_TYPE,
            "typeId": this.multipleSelection[0].id,
            "id": this.userInfo.id,
            }).then(res => {
          switch (res.data.code) {
            case "00": {
              MessageUtil.success("设置成功");
              list.length = 0;
              this.getUser();
              break;
            }
            default: {
              MessageUtil.error("网络异常");
            }
          }
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

    queryByType() {
      if(this.userPower.peizhiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8102/peizhi/queryPeiZhi';
      axios.post(url, {'type': this.PAGE_TYPE}).then(res => {
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
            this.total = res.data.data.length;
            if(this.PAGE_TYPE == '仓库' || this.PAGE_TYPE == '店铺' || this.PAGE_TYPE == '核算单位'){
              for(var i=0; i<this.tableData.length; i++){
                if(this.tableData[i].id * 1 == this.userInfo.cangku * 1 || this.tableData[i].id * 1 == this.userInfo.dianpu * 1 || this.tableData[i].id * 1 == this.userInfo.hesuanDanwei * 1){
                  this.tableData[i].moren = '默认'
                }else{
                  this.tableData[i].moren = ""
                }
              }
            }
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
    fuzzyQuery() {
      if(this.userPower.peizhiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8102/peizhi/queryPeiZhi';
      axios.post(url, {'type': this.PAGE_TYPE}).then(res => {
        console.log(res)
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
            this.total = res.data.data.length;
            if(this.PAGE_TYPE == '仓库' || this.PAGE_TYPE == '店铺' || this.PAGE_TYPE == '核算单位'){
              for(var i=0; i<this.tableData.length; i++){
                if(this.tableData[i].id * 1 == this.userInfo.cangku * 1 || this.tableData[i].id * 1 == this.userInfo.dianpu * 1 || this.tableData[i].id * 1 == this.userInfo.hesuanDanwei * 1){
                  this.tableData[i].moren = '默认'
                }else{
                  this.tableData[i].moren = ""
                }
              }
            }
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
          if(this.userPower.peizhiSel == '是'){
            this.fuzzyQuery();
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
    /**
     * 获取页面类型
     */
    getPageType() {
      this.PAGE_TYPE = this.$route.query.type;
    },
    /**
     * 修改header标题
     */
    callParentChangeHeaderName() {
      this.$emit.childCallTitleRename(this.PAGE_TYPE)
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

  },
  created() {
    this.getPageType();
    changePageTitle(this.PAGE_TYPE);
    this.getUser();
  }
}
</script>

<style scoped>

</style>