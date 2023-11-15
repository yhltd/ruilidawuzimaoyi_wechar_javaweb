<template>
  <el-container style="height: 100%;">
    <el-row :gutter="20">
      <el-col :xs="20" :sm="6" :md="10" :lg="10" :xl="4">
        <el-form :inline="true" :model="fuzzyQueryKeyword">
          <el-form-item label="编号">
            <el-input placeholder="请输入编号" v-model="fuzzyQueryKeyword.bianhao" class="input-with-select">
            </el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input placeholder="请输入内容" v-model="fuzzyQueryKeyword.name" class="input-with-select">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" @click="fuzzyQuery()">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :xs="6" :sm="3" :md="2" :lg="2" :xl="1.1">
        <el-button size="medium" @click="queryAllCustomer()">刷新</el-button>
      </el-col>
      <el-col :xs="5" :sm="3" :md="2" :lg="2" :xl="1.1">
        <el-button size="medium" @click="addDialog">新增</el-button>
      </el-col>
      <el-col :xs="5" :sm="3" :md="2" :lg="2" :xl="1.1">
        <el-button size="medium" @click="updDialog()">编辑</el-button>
      </el-col>
      <el-col :xs="5" :sm="3" :md="2" :lg="2" :xl="1.1">
        <el-button size="medium" @click="delUsrList()">删除</el-button>
      </el-col>
      <el-col :xs="6" :sm="3" :md="2" :lg="2" :xl="1.1">
        <el-button size="medium" @click="exportExcel()">导出Excel</el-button>
      </el-col>

      <!--        <el-col :xs="9" :sm="6" :md="3" :lg="3" :xl="2">-->

      <!--          <el-dropdown>-->
      <!--            <el-button>-->
      <!--              批量更改<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
      <!--            </el-button>-->
      <!--            <el-dropdown-menu slot="dropdown">-->
      <!--              <el-dropdown-item>更改业务员</el-dropdown-item>-->
      <!--            </el-dropdown-menu>-->
      <!--          </el-dropdown>-->
      <!--        </el-col>-->
    </el-row>
    <br/>
    <el-container style="height: 600px;">
      <el-main>
        <el-table
            border
            :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
            style="width: 100%"
            @selection-change="handleSelectionChange"
        >
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
<!--          <el-table-column width="50" prop="id" v-show="false">-->
<!--          </el-table-column>-->
          <el-table-column prop="bianhao" label="客户编号"></el-table-column>
          <el-table-column prop="name" label="客户名称"></el-table-column>
          <el-table-column prop="type" label="客户分类"></el-table-column>
          <el-table-column prop="shangjiDanwei" label="上级单位"></el-table-column>
          <el-table-column prop="kehuDengji" label="客户等级"></el-table-column>
          <el-table-column prop="jiageDengji" label="价格等级"></el-table-column>
          <el-table-column prop="suozaiDiqu" label="所在地区"></el-table-column>
          <el-table-column prop="dizhi" label="地址"></el-table-column>
          <el-table-column prop="beizhu" label="备注"></el-table-column>
          <el-table-column prop="yewuyuan" label="业务员"></el-table-column>
        </el-table>
      </el-main>
    </el-container>
    <el-footer>

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
    <el-dialog
        title="添加客户"
        :visible.sync="userAdd"
        width="70%"
        :fullscreen="false"
        :close-on-press-escape="false"
        show-close
        :close-on-click-modal="false"
        :before-close="closeDialog"
    >
      <div class="view-box">
      <span>
        <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
            inline
        >
          <span class="spans">基本信息</span>
          <!--          <el-form-item label="客户编号" prop="name">-->
          <!--            <el-input v-model="ruleForm.cid"></el-input>-->
          <!--          </el-form-item>-->
          <el-form-item label="客户名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="客户分类">
            <el-select v-model="ruleForm.type" placeholder="请选择">
              <!-- types 为后端查询 -->
            <el-option
                v-for="item in types"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="上级单位">
            <el-input v-model="ruleForm.shangjiDanwei"></el-input>
          </el-form-item>
          <el-form-item label="客户等级">
            <el-select v-model="ruleForm.kehuDengji" placeholder="请选择" @click="queryUserLevel()">
            <el-option
                v-for="item in kehuLevel"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="价格等级">
            <el-select v-model="ruleForm.jiageDengji" placeholder="请选择">
            <el-option
                v-for="item in jiageLevel"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="所在地区">
            <el-select v-model="area.proId" placeholder="省份"
                       @change="queryNextArea('CITY');">
              <el-option
                  v-for="(v, k) in area.proArr"
                  :key="k"
                  :label="v"
                  :value="k">{{ v }}
            </el-option>
            </el-select>
            <el-select v-model="area.cityId" placeholder="所在市" @change="queryNextArea('COUNTRY');concatArea()">
              <el-option
                  v-for="(item) in area.cityArr"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">{{ item.name }}
            </el-option>
            </el-select>
            <el-select v-model="area.countId" placeholder="所在县/区" @change="concatArea();concatArea()">
              <el-option
                  v-for="item in area.countArr"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">{{ item.name }}
            </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="ruleForm.dizhi"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="ruleForm.ctext"></el-input>
          </el-form-item>
          <el-form-item label="业务员">
            <el-input v-model="ruleForm.salesMan"></el-input>
          </el-form-item>
          <el-form-item label="客户资料">
            <el-input v-model="ruleForm.cfile"></el-input>
          </el-form-item>


          <span class="spans">联系人信息</span>
          <!-- 动态添加添加联系人 -->
                    <div class="app-container home">
            <div class="table">
            <el-table
                :data="contactUserList"
                style="width: 100%"
                class="list-table"
                size="mini"
                border
            >
              <el-table-column>
                <template slot-scope="scope">联系人{{ scope.$index + 1 }}
                </template>
              </el-table-column>

              <el-table-column
                  label="姓名"
                  align="center"
                  prop="name"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.name"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="部门"
                  align="center"
                  prop="department"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.department"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="职务"
                  align="center"
                  prop="zhiwu"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.zhiwu"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="手机号"
                  align="center"
                  prop="phone"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.phone"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label=""
                  align="center"
                  prop="customerId"
                  v-if="false"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.customerId"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="主联系人" align="center" prop="type">
                <template slot-scope="scope">
                  <el-checkbox   true-label="主联系人" false-label="" v-model="mainUser"></el-checkbox >
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button @click="delColumn(scope.$index)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
        </div>
          </div>

          <el-form-item>
            <el-button @click="addUserItem()">增加</el-button>
          </el-form-item>



          <span class="spans">收件信息</span>
          <el-form-item label="收件人姓名">
            <el-input v-model="ruleForm.shoujianName"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话">
            <el-input v-model="ruleForm.shoujianPhone"></el-input>
          </el-form-item>
          <el-form-item label="收件地址">
            <el-input v-model="ruleForm.shoujianDizhi"></el-input>
          </el-form-item>


          <span class="spans">开票信息</span>
          <el-form-item label="单位名称">
            <el-input v-model="ruleForm.kaipiaoDanwei"></el-input>
          </el-form-item>
          <el-form-item label="纳税人标识号">
            <el-input v-model="ruleForm.shibiehao"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="ruleForm.kaipiaoDizhi"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="ruleForm.kaipiaoDianhua"></el-input>
          </el-form-item>
          <el-form-item label="开户银行">
            <el-input v-model="ruleForm.kaipiaoYinhang"></el-input>
          </el-form-item>
          <el-form-item label="开票账号">
            <el-input v-model="ruleForm.kaipiaoZhanghao"></el-input>
          </el-form-item>
          </el-form>
      </span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="SubmitEvent">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改 -->
    <el-dialog
        title="修改"
        :visible.sync="userupdate"
        width="70%"
        :fullscreen="false"
        :close-on-press-escape="false"
        show-close
        :close-on-click-modal="false"
        :before-close="closeDialog"
    >
      <span>
        <el-form
            v-model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
            inline
        >
                <div class="view-box">

          <span class="spans">基本信息</span>
          <el-form-item label="客户编号" prop="name">
            <el-input v-model="ruleForm.id"></el-input>
          </el-form-item>
          <el-form-item label="客户名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
                    <el-form-item label="客户分类">
            <el-select v-model="ruleForm.type" placeholder="请选择">
              <!-- types 为后端查询 -->
            <el-option
                v-for="item in types"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="上级单位">
            <el-input v-model="ruleForm.shangjiDanwei"></el-input>
          </el-form-item>
          <el-form-item label="客户等级">
            <el-select v-model="ruleForm.kehuDengji" placeholder="请选择" @click="queryUserLevel()">
            <el-option
                v-for="item in kehuLevel"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="价格等级">
            <el-select v-model="ruleForm.jiageDengji" placeholder="请选择">
            <el-option
                v-for="item in jiageLevel"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="所在地区">
            <el-select v-model="area.proId" placeholder="省份"
                       @change="queryNextArea('CITY');">
              <el-option
                  v-for="(v, k) in area.proArr"
                  :key="k"
                  :label="v"
                  :value="k">{{ v }}
            </el-option>
            </el-select>
            <el-select v-model="area.cityId" placeholder="所在市" @change="queryNextArea('COUNTRY');concatArea()">
              <el-option
                  v-for="(item) in area.cityArr"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">{{ item.name }}
            </el-option>
            </el-select>
            <el-select v-model="area.countId" placeholder="所在县/区" @change="concatArea();concatArea()">
              <el-option
                  v-for="item in area.countArr"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">{{ item.name }}
            </el-option>
            </el-select>
          </el-form-item>
                  <!--          </el-form-item>-->
          <el-form-item label="地址">
            <el-input v-model="ruleForm.dizhi"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="ruleForm.beizhu"></el-input>
          </el-form-item>
          <el-form-item label="业务员">
            <el-input v-model="ruleForm.yewuyuan"></el-input>
          </el-form-item>

          <span class="spans">联系人信息</span>

          <div class="app-container home">
            <div class="table">
            <el-table
                :data="contactUserList"
                style="width: 100%"
                class="list-table"
                highlight-current-row
                @current-change="tableChange"
                size="mini"
                border
            >
              <el-table-column></el-table-column>

              <el-table-column
                  label="姓名"
                  align="center"
                  prop="name"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.name"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="部门"
                  align="center"
                  prop="department"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.department"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="职务"
                  align="center"
                  prop="zhiwu"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.zhiwu"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="手机号"
                  align="center"
                  prop="phone"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.phone"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label=""
                  align="center"
                  prop="customerId"
                  v-if="false"
              >
                <template slot-scope="scope" v-if="false">
                  <el-input v-model="scope.row.customerId"></el-input>
                </template>
              </el-table-column>
<!--              <el-table-column label="主联系人" align="center" prop="type">-->
<!--                <template slot-scope="scope">-->
<!--                  <el-checkbox name="checkbox" @change="changeMainUser(scope.$index)" true-label="主联系人" false-label=""   v-model="mainUser"></el-checkbox >-->
<!--                </template>-->
<!--              </el-table-column>-->
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button @click="delColumn(scope.$index)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
        </div>
          </div>

          <el-form-item>
            <el-button @click="addUserItem()">增加</el-button>
          </el-form-item>
          <span class="spans">收件信息</span>
          <el-form-item label="收件人姓名">
            <el-input v-model="ruleForm.shoujianName"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话">
            <el-input v-model="ruleForm.shoujianPhone"></el-input>
          </el-form-item>
          <el-form-item label="收件地址">
            <el-input v-model="ruleForm.shoujianDizhi"></el-input>
          </el-form-item>

          <span class="spans">开票信息</span>
          <el-form-item label="单位名称">
            <el-input v-model="ruleForm.kaipiaoDanwei"></el-input>
          </el-form-item>
          <el-form-item label="纳税人标识号">
            <el-input v-model="ruleForm.shibiehao"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="ruleForm.kaipiaoDizhi"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="ruleForm.kaipiaoDianhua"></el-input>
          </el-form-item>
          <el-form-item label="开户银行">
            <el-input v-model="ruleForm.kaipiaoYinhang"></el-input>
          </el-form-item>
          <el-form-item label="银行账号">
            <el-input v-model="ruleForm.kaipiaoZhanghao"></el-input>
          </el-form-item>
                </div>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog()">取 消</el-button>
        <el-button type="primary" @click="SubmitEventTwo">确 定</el-button>
      </span>
    </el-dialog>
  </el-container>


</template>

<script>
import ParseDataArea from "@/utils/ParseDataArea";
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import exportExcel from "@/utils/FileUtil";

/**
 * 深复制 确保复制内容不会相互影响
 * @param {any} obj
 * @returns {any}
 */
const deepCopy = (obj) => {
  return JSON.parse(JSON.stringify(obj));
};
export default {
  inject: ["reload"],
  created() {
    this.queryAllCustomer();
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

    edits(data) {
      this.id = data.cid
    },
    setCurrent(row) {
      this.$refs.singleTable.setCurrentRow(row);
    },
    tableChange(val) {
      this.currentRow = val;
    },
    addDialog() {
      //显示Dialog弹框
      this.userAdd = true;
      this.queryNextArea('PROVINCE', undefined);
      this.queryAllSelectInfo(-1);

      // queryContactUser
    },
    updDialog() {
      let arr = this.tableCheckList;
      if (arr.length == 0) {
        MessageUtil.info("选择需要编辑的用户")
        return;
      } else if (arr.length > 1) {
        MessageUtil.info("只能同时编辑一个用户")
        return;
      }
      let id = arr[0].id;
      let url = 'http://localhost:8081/customer/selectById';
      this.queryAllSelectInfo(id);
      this.axios.post(url, {id: arr[0].id}).then(res => {
        if (res.data.code == '00') {
          this.itemsList = res.data.data.itemList;
          this.ruleForm = res.data.data;
          console.log(this.ruleForm)
          console.log(res.data.data)
        } else {
          MessageUtil.error("查找联系人失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
      //显示Dialog弹框
      this.userupdate = true;

    },
    changeMainUser(ind) {
      console.log("get", ind)
      let count = 0;
      for(let item of this.contactUserList) {
        console.log("联系人",count,item)
        if(count == ind) {
          item.type="主联系人"
        } else {
          item.type = ""
        }
      }
    },
    closeDialog() {
      //弹框的关闭方法
      this.userAdd = false;
      this.userupdate = false;
      this.queryNextArea('PROVINCE');

    },
    SubmitEvent() {
      // 需要事务 list为联系人列表  baseInfo 为基础信息
      this.axios
          .post("http://localhost:8081/customer/customerAdd", {'body': this.contactUserList, 'head': this.ruleForm})
          .then((res) => {
            switch (res.data.code) {
              case '00': {
                MessageUtil.success("添加成功");
                this.closeDialog();
                this.queryAllCustomer()
                break;
              }
              default: {
                MessageUtil.error("添加失败");
              }
            }
          })
          .catch((error) => {
            MessageUtil.error("网络异常")
          });
    },
    SubmitEventTwo() {
      //  可以拆分为两次请求
      this.axios
          .post("http://localhost:8081/customer/customerUpd", {'body': this.contactUserList, 'head': this.ruleForm})
          .then((res) => {
            switch (res.data.code) {
              case '00': {
                MessageUtil.success("修改成功");
                this.closeDialog();
                this.queryAllCustomer()
                break;
              }
              default: {
                MessageUtil.error("修改失败");
              }
            }
          })
          .catch((error) => {
            MessageUtil.error("网络异常")
          });
    },
    updateYewu() {
    },

    //  后添加方法
    /**
     * 导出excel
     *
     * 向后端发送请求获取文件下载地址
     *
     * 前端产生提示消息由用户点击进行下载
     */
    async exportExcel() {
      let url = "http://localhost:8081/customer/exportExcel";
      this.axios(url).then(res => {
        if (res.data.code == '00') {
          // TODO 获取到后端连接后提示用户可以下载 res.data.data.
          this.file.exportFileDialog = true;
          exportExcel.download(this.fileName, url)

        } else {
          MessageUtil.error("文件导出失败");
        }
      }).catch(() => {
        MessageUtil.error("未知错误");
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
      this.tableCheckList = val;
    },
    /**
     * 下拉列表获取
     */
    async queryAllSelectInfo(id) {
      this.queryNextArea('PROVINCE', undefined);
      this.queryUserLevel();
      this.queryJiageLevel();
      this.queryUserTypeLevel();
      if (id != -1) {
        this.queryContactUser(id);
      }
    },

    async fuzzyQuery() {
      console.log("查询")
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8081/customer/queryList';
      axios.post(url, this.fuzzyQueryKeyword).then(res => {
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
     * 查询所有用户
     */
    queryAllCustomer() {
      console.log("模糊查询:")
      this.axios
          .get("http://localhost:8081/customer/getAll")
          .then((res) => {
            if (res.data.code == '00') {
              this.tableData = res.data.data;
              this.total = res.data.data.length;
            } else {
              MessageUtil.error("获取用户失败");
            }
          })
          .catch((error) => {
            MessageUtil.error("获取用户失败");
          });
    },
    /**
     * 批量删除用户
     */
    delUsrList() {
      let list = this.tableCheckList;
      if (list == undefined || list.length == 0) {
        Message.info("至少选择一个用户");
        return;
      }

      let url = "http://localhost:8081/customer/delCustomer";
      let tmp = [];
      for (let i of list) {
        tmp.push(i.id);
      }
      axios.post(url, {"list": tmp}).then(res => {
        switch (res.data.code) {
          case "00": {
            MessageUtil.success("删除成功");
            this.queryAllCustomer();
            list.length = 0;
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
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    /**
     * 动态添加联系人
     */
    addUserItem() {
      let data = deepCopy(this.contactUser);
      this.contactUserList.push(data)
    },
    delColumn(index) {
      this.contactUserList.splice(index, 1);
    },

    /**
     * 查询用户已有的联系人
     */
    queryContactUser(id) {
      let url = "http://localhost:8081/customer/selectById";
      this.axios.post(url, {'id': id}).then(res => {
        if (res.data.code == '00') {
          this.contactUserList_exist = res.data.data.itemList;
          this.contactUserList = deepCopy(this.contactUserList_exist);
        } else {
          MessageUtil.error("获取联系人列表失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },

    /**
     * 查询用户等级
     */
    queryUserLevel() {
      let URL = "http://localhost:8081/peizhi/queryPeiZhi";
      axios.post(URL, {type: "客户等级"}).then(res => {
        switch (res.data.code) {
          case '00':
            this.kehuLevel = res.data.data;
            break;
          default: {
            MessageUtil.error("获取用户等级失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    /**
     * 查询jiage等级
     */
    queryJiageLevel() {
      console.log("价格等级调用成功")
      let URL = "http://localhost:8081/peizhi/queryPeiZhi";
      axios.post(URL, {type: "价格等级"}).then(res => {
        switch (res.data.code) {
          case '00':
            this.jiageLevel = res.data.data;
            break;
          default: {
            MessageUtil.error("获取价格等级失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    /**
     * 查询用户分类
     */
    queryUserTypeLevel() {
      let URL = "http://localhost:8081/peizhi/queryPeiZhi";
      axios.post(URL, {type: "客户分类"}).then(res => {
        switch (res.data.code) {
          case '00':
            this.types = res.data.data;
            break;
          default: {
            MessageUtil.error("获取用户分类失败");
          }
        }

      }).catch(() => {

        MessageUtil.error("网络异常");
      })
    },
    concatArea(e) {
      this.ruleForm.suozaiDiqu = this.area.province + "-" + this.area.city + "-" + this.area.country;
    },
    /**
     * 查询下一个地区
     * @param {"PROVINCE" | "CITY" | "COUNTRY"} type 省市区
     */
    queryNextArea(type) {
      switch (type) {
        case "PROVINCE": {
          this.area.proArr = ParseDataArea.queryProvince();
          if (this.area.city != '') this.clearCity();
          break;
        }
        case "CITY": {
          if (this.area.country != '') this.clearCity();
          console.log("选择的省级", this.area.proId)
          this.area.cityArr = ParseDataArea.queryCity(this.area.proId.substring(0, 2));
          break;
        }
        case "COUNTRY": {
          console.log("选择的市级单位", this.area.cityId)
          this.area.countArr = ParseDataArea.queryCountry(this.area.cityId.substring(0, 4));
          break;
        }
      }
    },
    clearCity() {
      this.area.cityId = '';
      this.area.city = '';
      this.area.cityArr.length = 0;
      this.clearCountry();
    },
    clearCountry() {
      this.area.countId = '';
      this.area.country = '';
      this.area.countArr.length = 0;
    }
  },
  data() {
    return {

      id: "",
      userAdd: false, //弹框默认显示与否
      userupdate: false,
      update: [],

      tableData: [],
      itemsList: [],
      ruleForm: {
        id: 0,
        bianhao: '',
        name: '',
        type: '',
        shangjiDanwei: '',
        kehuDengji: '',
        jiageDengji: '',
        suozaiDiqu: '',
        dizhi: '',
        beizhu: '',
        yewuyuan: '',
        shoujianName: '',
        shoujianPhone: '',
        shoujianDizhi: '',
        kaipiaoDanwei: '',
        kaipiaoDizhi: '',
        kaipiaoDianhua: '',
        shibiehao: '',
        kaipiaoYinhang: '',
        kaipiaoZhanghao: '',
      },
      ruleFdorm: {
        id: 0,
        bianhao: '',
        name: '',
        type: '',
        shangjiDanwei: '',
        kehuDengji: '',
        jiageDengji: '',
        suozaiDiqu: '',
        dizhi: '',
        beizhu: '',
        yewuyuan: '',
        shoujianName: '',
        shoujianPhone: '',
        shoujianDizhi: '',
        kaipiaoDanwei: '',
        kaipiaoDizhi: '',
        kaipiaoDianhua: '',
        shibiehao: '',
        kaipiaoYinhang: '',
        kaipiaoZhanghao: '',
      },
      types: [], // 查询到的客户分类 [{name}]
      kehuLevel: [], // 客户等级 [{name}]
      jiageLevel: [], // 数据结构与上级相同
      kehuState: [],
      yewuyuans: [],
      area: { // 三级联动
        province: '', // 省
        city: '', // 市
        country: '', // 县
        address: '', // 详细地址
        proId: '',
        cityId: '',
        countId: '',
        proArr: [],
        cityArr: [],
        countArr: [],
      },
      fuzzyQueryKeyword: {
        bianhao: '',
        name: '',
      },
      contactUser: {
        id: 0,
        name: '',
        department: '',
        zhiwu: '',
        phone: '',
        address: '',
        type: '', // 主联系人
        customerId: 0,
      },
      contactUserList_exist: [], // 记录已有联系人
      contactUserList: [  // 联系人
      ], // [{}]
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      rules: {
        name: [],
      },
      tableCheckList: [], // 批量删除用户
      currentRow: [], // 主联系人如果页面中勾选了主联系人，则这个为该联系人在数组中的下标
    };
  },
};
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

.el-header {
  background-color: #afb4b9;
  color: #333;
  line-height: 60px;
  font-size: 20px;
}

.spans {
  display: block;
  font-size: 20px;
}

.view-box {
  height: 60vh;
  overflow-y: scroll;
}
</style>
