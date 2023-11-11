<template>
  <div>
    <el-container style="height: 100%;">
      <el-row :gutter="20">
        <el-col :xs="9" :sm="6" :md="3" :lg="3" :xl="2">
        </el-col>
        <el-col :xs="20" :sm="6" :md="6" :lg="4" :xl="4">
          <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="fuzzyQuery()"></el-button>
          </el-input>
        </el-col>
        <el-col :xs="5" :sm="3" :md="2" :lg="2" :xl="1.1">
          <el-button size="medium" @click="addDialog">新增</el-button>
        </el-col>
        <el-col :xs="5" :sm="3" :md="2" :lg="2" :xl="1.1">
          <el-button size="medium" @click="updDialog">编辑</el-button>
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
              :data="tableData"
              style="width: 100%"
              @selection-change="handleSelectionChange"
          >
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column width="50" hidden>
            </el-table-column>
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
            <el-table-column prop="cfile" label="客户资料"></el-table-column>
          </el-table>
        </el-main>
      </el-container>
      <el-footer>
        <div class="page">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="page"
              :page-sizes="[10, 25, 50, 100]"
              :page-size="pageSize"
              layout="sizes, prev, pager, next"
              :total="total">
          </el-pagination>
        </div>
      </el-footer>
    </el-container>

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
                  :value="k">{{v }}
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
          <el-row v-for="(item, index) in contactUserList">
          <el-form-item label="姓名">
            <el-input v-model="item.name">{{ index }}</el-input>
          </el-form-item>
          <el-form-item label="部门">
            <el-input v-model="item.department"></el-input>
          </el-form-item>
          <el-form-item label="职务">
            <el-input v-model="item.zhiwu"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="item.phone"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="item.address"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio-group v-model="item.type">
              <el-radio label="联系人" value="联系人"></el-radio>
              <el-radio label="主联系人" value="主联系人"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button @click.prevent="delUserItem(index)">删除</el-button>
          </el-form-item>
        </el-row>
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
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="SubmitEvent">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改 -->
    <el-dialog
        title="客户"
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
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
            inline
        >
          <span class="spans">基本信息</span>
          <el-form-item label="客户编号" prop="name">
            <el-input v-model="ruleForm.cid"></el-input>
          </el-form-item>
          <el-form-item label="客户名称" prop="name">
            <el-input v-model="ruleForm.cname"></el-input>
          </el-form-item>
          <el-form-item label="客户分类">
            <el-select v-model="ruleForm.type" placeholder="客户分类">
              <el-option label="A类别" value="1">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上级单位">
            <el-input v-model="ruleForm.cboss"></el-input>
          </el-form-item>
          <el-form-item label="客户等级">
            <el-select v-model="ruleForm.kehuDengji" placeholder="客户等级">
              <el-option label="A等级" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="价格等级">
            <el-select v-model="ruleForm.jiageDengji" placeholder="价格等级">
              <el-option label="A类别" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所在地区">
            <el-select v-model="ruleForm.suozaiDiqu" placeholder="所在地区">
              <el-option label="A地区" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="ruleForm.dizhi"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="ruleForm.beizhu"></el-input>
          </el-form-item>
          <el-form-item label="业务员">
            <el-input v-model="ruleForm.yewuyuan"></el-input>
          </el-form-item>
          <!--          <el-form-item label="客户资料">-->
          <!--            <el-input v-model="ruleForm.cfile"></el-input>-->
          <!--          </el-form-item>-->
          <!--        </el-form>-->
          <!--        <el-form-->
          <!--            :model="ruleForm"-->
          <!--            ref="ruleForm"-->
          <!--            label-width="100px"-->
          <!--            class="demo-ruleForm"-->
          <!--            inline-->
          <!--        >-->
          <span class="spans">联系人信息</span>
          <el-form-item label="姓名">
            <el-input v-model="contactUser.name"></el-input>
          </el-form-item>
          <el-form-item label="部门">
            <el-input v-model="contactUser.department"></el-input>
          </el-form-item>
          <el-form-item label="职务">
            <el-input v-model="contactUser.zhiwu"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="contactUser.phone"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="contactUser.address"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio-group v-model="ruleForm.type">
              <el-radio label="联系人"></el-radio>
              <el-radio label="主联系人"></el-radio>
            </el-radio-group>
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
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="SubmitEventTwo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
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
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    edits(data) {
      this.id = data.cid
    },
    addDialog() {
      //显示Dialog弹框
      this.userAdd = true;
      this.queryNextArea('PROVINCE', undefined);

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
      this.updUserInfo = arr[0];
      console.log("获取到编辑信息", arr[0])
      //显示Dialog弹框
      this.userupdate = true;

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
          .post("http://localhost:8081/customer/customerUpd", {'list': this.contactUserList, 'baeseInfo': this.ruleForm})
          .then((res) => {
            switch (res.data.code) {
              case '00': {
                MessageUtil.success("修改成功");
                this.closeDialog();
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
    async queryAllSelectInfo() {
      this.queryNextArea('PROVINCE', undefined);
      this.queryPowers().catch(() => {
        MessageUtil.error("网络异常")
      })
      this.queryShenPis().catch(() => {
        MessageUtil.error("网络异常")
      });
      this.queryYewuyuan();
      this.queryJiageLevel();
      this.queryUserTypeLevel();
      this.queryContactUser();
      this.queryHesuanDanwei();
    },
    async queryYewuyuan() {
      this.SP_Loading = true
      let url = "http://localhost:8081/user/queryYewuyuan";
      axios.post(url).then(res => {
        switch (res.data.code) {
          case '00':
            this.业务员 = res.data.data;
            break;
          default: {
            MessageUtil.error("获取业务员失败");
          }
        }

      }).catch(() => {

        MessageUtil.error("网络异常");
      })
    },
    async fuzzyQuery() {
      this.fuzzy_query_loading = true;
      let url = 'http://localhost:8081/customer/queryList';
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
    /**
     * 查询所有用户
     */
    queryAllCustomer() {
      this.axios
          .get("http://localhost:8081/customer/getAll")
          .then((res) => {
            if (res.data.code == '00') {
              this.tableData = res.data.data;
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
      this.contactUserList.push(json)
    },
    /**
     * 动态删除联系人
     *
     * 用于添加或修改用户中联系人操作
     *
     * 会向后端发送请求来删除联系人
     * @param {Number} index 在联系人列表中的位置
     */
    delUserItem(index) {
      let list = this.contactUserList;
      let ind = -1;
      if (list == undefined || list.length <= index) {
        return;
      } else if ((ind = this.queryUserIsExist(list[index].id)) == -1) {
        // 不是已有联系人执行以下代码
        this.contactUserList.splice(index, 1);
        return;
      }
      let url = "http://localhost:8081/custom/queryContactUser";
      this.axios.post(url, {'id': id}).then(res => {
        if (res.data.code == '00') {
          this.contactUserList.splice(index, 1);
          this.contactUserList_exist.splice(ind, 1);
          MessageUtil.success("删除成功")
        } else {
          MessageUtil.error("删除失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },
    /**
     * 查询用户是否是已有联系人
     * @param {Number} id 用户id
     * @return {number | -1}   如果是一有联系人返回在已有联系人列表中的下标
     */
    queryUserIsExist(id) {
      if (id == undefined || id <= 0) {
        return -1;
      }
      let list = this.contactUserList_exist;
      let index = 0;
      for (let user in list) {
        if (user.id == id) {
          return index;
        }
        ++index;
      }
      return -1;
    },
    /**
     * 查询用户已有的联系人
     */
    queryContactUser(id) {
      let url = "http://localhost:8081/custom/queryContactUser";
      this.axios.post(url, {'id': id}).then(res => {
        if (res.data.code == '00') {
          this.contactUserList_exist = res.data.data;
          this.contactUserList = deepCopy(this.contactUserList_exist);
        } else {
          MessageUtil.error("获取联系人列表失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },
    queryHesuanDanwei() {
      let URL = "http://localhost:8081/peizhi/queryHesuanDanwei";
      axios.post(URL).then(res => {
        switch (res.data.code) {
          case '00':
            this.kehuLevel = res.data.data;
            break;
          default: {
            MessageUtil.error("获取核算单位失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    /**
     * 查询用户等级
     */
    queryUserLevel() {
      let URL = "http://localhost:8081/peizhi/queryAllUserLevel";
      axios.post(URL).then(res => {
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
      let URL = "http://localhost:8081/peizhi/queryAllJiageLevel";
      axios.post(URL).then(res => {
        switch (res.data.code) {
          case '00':
            this.kehuLevel = res.data.data;
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
      console.log("用户分类调用成功")
      let URL = "http://localhost:8081/peizhi/queryAllUserType";
      axios.post(URL).then(res => {
        switch (res.data.code) {
          case '00':
            this.kehuLevel = res.data.data;
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
      console.log("事件",e)
      console.log(this.area.countId)
      this.ruleForm.suozaiDiqu = this.area.province+"-"+this.area.city+"-"+this.area.country;
      console.log("三级联动结果", this.ruleForm.suozaiDiqu)
    },
    /**
     * 查询下一个地区
     * @param {"PROVINCE" | "CITY" | "COUNTRY"} type 省市区
     */
    queryNextArea(type) {
      switch (type) {
        case "PROVINCE": {
          this.area.proArr = ParseDataArea.queryProvince();
          if(this.area.city != '' ) this.clearCity();
          break;
        }
        case "CITY": {
          if(this.area.country != '' ) this.clearCity();
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
    clearCity(){
      this.area.cityId = '';
      this.area.city = '';
      this.area.cityArr.length = 0;
      this.clearCountry();
    },
    clearCountry(){
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
      keyword: '',
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
      page: 1,
      total: 10,
      pageSize: 10,
      rules: {
        name: [],
      },
      tableCheckList: [], // 批量删除用户
      mainUser: -1, // 主联系人如果页面中勾选了主联系人，则这个为该联系人在数组中的下标
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
</style>
