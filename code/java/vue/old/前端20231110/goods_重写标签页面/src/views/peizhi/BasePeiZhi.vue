<template>
  <!-- 配置类通用页面
  适配页面： 仓库、店铺、供应商等级、核算单位、价格等级、
  价格上浮率、记账明细类型、记账分类、客户等级、商品分类、收款账户
  质保等级
  -->
  <el-container>
    <el-header>
      <el-row :gutter="20">
        <el-col :xs="10" :sm="10" :md="6" :lg="8" :xl="5">
          <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select"
                    @keyup.enter.native="fuzzyQuery()">
            <el-button :loading="fuzzy_query_loading" slot="append" icon="el-icon-search"

                       @click="fuzzyQuery()"></el-button>
          </el-input>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button @click="addDialog = true" type="primary">添加</el-button>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button @click="updConfig();" type="primary">修改</el-button>
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
    <el-main>
      <el-table
          :data="tableData"
          border
          style="width: 100%; margin-top: 10px;"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column fixed prop="id" label="编号" width="50">

        </el-table-column>
        <el-table-column
            fixed
            prop="name"
            label="id"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="name"
            :label=PAGE_TYPE
            width="auto">
        </el-table-column>
      </el-table>
    </el-main>

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
      PAGE_TYPE: '', // 页面类型以及页面标题 不可轻易调用
      tableData: [{
        id: 0,
        name: '',
      }],
      addForm: {
        id: 0,
        name: '',
        type: this.PAGE_TYPE,
      },
      dialogFormVisible: false,
      addDialog: false,
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
          let url = "http://localhost:8081/peizhi/peizhiAdd";
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
          let url = "http://localhost:8081/peizhi/peizhiUpd";
          this.axios.post(url, this.addForm).then(res => {
            if (res.data.code == '00') {
              MessageUtil.success("添加成功");
              this.queryByType();
              this.addDialog = false;
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
    updConfig() {
      let list = this.tableCheckList;
      if (list.length > 1) {
        MessageUtil.warning("只能同时更改一条数据");
        return false;
      } else if (list.length == 0) {
        MessageUtil.warning("需要选中一条数据")
        return false;
      }
      this.addForm = list[0];
      return true;
    },
    delConfig() {
      let list = this.tableCheckList;
      if (list.length == 0) {
        MessageUtil.warning("至少选择一条数据");
        return;
      }
      let tmp = [];
      for (let i of list) {
        tmp.push(i.id);
      }
      let url = "http://localhost:8081/peizhi/delList";
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
    },
    queryByType() {
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8081/peizhi/queryPeizhi';
      axios.post(url, {'type': this.PAGE_TYPE}).then(res => {
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
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
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8081/peizhi/fuzzyQuery';
      axios.post(url, {'keyword': this.keyword}).then(res => {
        switch (res.data.code) {
          case '00':
            this.tableData = res.data.data;
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
    }
  },
  created() {
    this.getPageType();
    changePageTitle(this.PAGE_TYPE);
    this.callParentChangeHeaderName();
    this.fuzzyQuery();
  }
}
</script>

<style scoped>

</style>