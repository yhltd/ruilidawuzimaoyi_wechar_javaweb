<template>
  <el-container>
    <el-header style="background-color: transparent;">
      <el-row :gutter="20">
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button size="small" round @click="updConfig();" type="primary">修改</el-button>
          <!--      <el-button @click="" type="danger">删除</el-button>-->
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
            prop="jizhangFenlei"
            label="记账分类"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="jizhangMingxi"
            label="记账明细"
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
                    @keyup.enter.native="addFormSub('addConfig')"><template v-if="PAGE_TYPE=='增值税率'" slot="append">%</template></el-input>
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
        <el-form-item label="记账分类" prop="updName" class="custom-form-item">
          <el-input ref="name" v-model="addForm.jizhangFenlei" class="custom-login-inp"></el-input>
        </el-form-item>
        <el-form-item label="记账明细" prop="updName" class="custom-form-item">
          <el-input ref="name" type="textarea" autosize="true" v-model="addForm.jizhangMingxi" class="custom-login-inp"></el-input>
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
    updFormSub(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = "http://yhocn.cn:8102/jiZhangMingXi/updateById";
          this.axios.post(url, {'head':this.addForm}).then(res => {
            if (res.data.code == '00') {
              MessageUtil.success("修改成功");
              this.fuzzyQuery();
              this.updDialog = false;
            } else {
              MessageUtil.error("修改失败")
            }
          }).catch(() => {
            MessageUtil.error("网络异常")
          });
        } else {
          MessageUtil.error("格式错误")
        }
      })
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
        jizhangFenlei:list[0].jizhangFenlei,
        jizhangMingxi:list[0].jizhangMingxi
      };
      if(list[0].id!=4){
        MessageUtil.warning("只允许修改日常收支信息")
        return false;
      }
      this.updDialog = true;
      return true;
    },

    fuzzyQuery() {
      if(this.userPower.peizhiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.fuzzy_query_loading = true;
      let url = 'http://yhocn.cn:8102/jiZhangMingXi/getAll';
      this.axios(url).then(res => {
        console.log(res)
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
    this.getUser();
  }
}
</script>


<style scoped>

</style>