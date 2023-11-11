<template>
  <div>
    <el-container style="height: 100%;">
      <el-header>客户</el-header>
      <br />
      <div>
        <el-select  placeholder="客户状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-autocomplete placeholder="请输入内容">
          <i class="el-icon-more el-input__icon" slot="suffix"> </i>
        </el-autocomplete>
        &nbsp;
        <el-button size="medium"><i class="el-icon-search"></i>查找</el-button>
        &nbsp;
        <el-button-group>
          <el-button size="medium" @click="showDialog">新增</el-button>
          <el-button size="medium">导入</el-button>
        </el-button-group>
        &nbsp;
        <el-button-group>
          <el-button size="medium" @click="addDialog(id)">编辑</el-button>
          <el-button size="medium" @click="del(id)">删除</el-button>
        </el-button-group>
        &nbsp;
        <el-dropdown>
          <el-button>
            批量更改<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>更改业务员</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <br />
      <el-container style="height: 600px;">
      <el-main>
        <el-table
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column width="50">
            <template slot-scope="scope">
            <el-checkbox @click.native="edits(scope.row)"></el-checkbox>
          </template>
          </el-table-column>
          <el-table-column prop="bianhao" label="编号"> </el-table-column>
          <el-table-column prop="name" label="名称"> </el-table-column>
          <el-table-column prop="type" label="客户分类"> </el-table-column>
          <el-table-column prop="shangjiDanwei" label="上级单位"> </el-table-column>
          <el-table-column prop="kehuDengji" label="客户等级"> </el-table-column>
          <el-table-column prop="jiageDengji" label="价格等级"> </el-table-column>
          <el-table-column prop="suozaiDiqu" label="地区"> </el-table-column>
          <el-table-column prop="dizhi" label="地址"> </el-table-column>
          <el-table-column prop="beizhu" label="备注"> </el-table-column>
          <el-table-column prop="yewuyuan" label="业务员"> </el-table-column>
          <el-table-column prop="cfile" label="客户资料"> </el-table-column>
        </el-table>
      </el-main>
    </el-container>
      <el-footer>
        <div class="page">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[30, 50, 100, 200]"
            :page-size="100"
            background
            layout=" sizes, prev, pager, next"
            :total="1000"
          ></el-pagination>
        </div>
      </el-footer>
    </el-container>

    <el-dialog
      title="客户"
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
          <el-form-item label="客户编号" prop="name">
            <el-input v-model="ruleForm.cid"></el-input>
          </el-form-item>
          <el-form-item label="客户名称" prop="name">
            <el-input v-model="ruleForm.cname"></el-input>
          </el-form-item>
          <el-form-item label="客户分类">
            <el-select v-model="ruleForm.cfenl" placeholder="客户分类">
              <el-option label="A类别" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上级单位">
            <el-input v-model="ruleForm.cboss"></el-input>
          </el-form-item>
          <el-form-item label="客户等级">
            <el-select v-model="ruleForm.clevel" placeholder="客户等级">
              <el-option label="A等级" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="价格等级">
            <el-select v-model="ruleForm.ccustLevel" placeholder="价格等级">
              <el-option label="A类别" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所在地区">
            <el-select v-model="ruleForm.cregion" placeholder="所在地区">
              <el-option label="A地区" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="ruleForm.caddress"></el-input>
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
        </el-form>
        <el-form
          :model="lianxiForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">联系人信息</span>
          <el-form-item label="姓名">
            <el-input v-model="lianxiForm.cpName"></el-input>
          </el-form-item>
          <el-form-item label="部门">
            <el-input v-model="lianxiForm.cpDepartment"></el-input>
          </el-form-item>
          <el-form-item label="职务">
            <el-input v-model="lianxiForm.cpDuties"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="lianxiForm.cpPhone"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="lianxiForm.cpAddress"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio-group v-model="lianxiForm.cpType">
              <el-radio label="联系人"></el-radio>
              <el-radio label="主联系人"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>

        <el-form
          :model="shoujianForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">收件信息</span>
          <el-form-item label="收件人姓名">
            <el-input v-model="shoujianForm.rname"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话">
            <el-input v-model="shoujianForm.rphone"></el-input>
          </el-form-item>
          <el-form-item label="收件地址">
            <el-input v-model="shoujianForm.raddress"></el-input>
          </el-form-item>
        </el-form>

        <el-form
          :model="kaipiaoForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">开票信息</span>
          <el-form-item label="单位名称">
            <el-input v-model="kaipiaoForm.iname"></el-input>
          </el-form-item>
          <el-form-item label="纳税人标识号">
            <el-input v-model="kaipiaoForm.inumber"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="kaipiaoForm.iaddress"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="kaipiaoForm.iphone"></el-input>
          </el-form-item>
          <el-form-item label="开户银行">
            <el-input v-model="kaipiaoForm.ibank"></el-input>
          </el-form-item>
          <el-form-item label="银行账号">
            <el-input v-model="kaipiaoForm.ibankNumber"></el-input>
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
            <el-select v-model="ruleForm.cfenl" placeholder="客户分类">
              <el-option label="A类别" value="1">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上级单位">
            <el-input v-model="ruleForm.cboss"></el-input>
          </el-form-item>
          <el-form-item label="客户等级">
            <el-select v-model="ruleForm.clevel" placeholder="客户等级">
              <el-option label="A等级" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="价格等级">
            <el-select v-model="ruleForm.ccustLevel" placeholder="价格等级">
              <el-option label="A类别" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所在地区">
            <el-select v-model="ruleForm.cregion" placeholder="所在地区">
              <el-option label="A地区" value="1">
                
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="ruleForm.caddress"></el-input>
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
        </el-form>
        <el-form
          :model="lianxiForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">联系人信息</span>
          <el-form-item label="姓名">
            <el-input v-model="lianxiForm.cpName"></el-input>
          </el-form-item>
          <el-form-item label="部门">
            <el-input v-model="lianxiForm.cpDepartment"></el-input>
          </el-form-item>
          <el-form-item label="职务">
            <el-input v-model="lianxiForm.cpDuties"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="lianxiForm.cpPhone"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="lianxiForm.cpAddress"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio-group v-model="lianxiForm.cpType">
              <el-radio label="联系人"></el-radio>
              <el-radio label="主联系人"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>

        <el-form
          :model="shoujianForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">收件信息</span>
          <el-form-item label="收件人姓名">
            <el-input v-model="shoujianForm.rname"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话">
            <el-input v-model="shoujianForm.rphone"></el-input>
          </el-form-item>
          <el-form-item label="收件地址">
            <el-input v-model="shoujianForm.raddress"></el-input>
          </el-form-item>
        </el-form>

        <el-form
          :model="kaipiaoForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
          inline
        >
          <span class="spans">开票信息</span>
          <el-form-item label="单位名称">
            <el-input v-model="kaipiaoForm.iname"></el-input>
          </el-form-item>
          <el-form-item label="纳税人标识号">
            <el-input v-model="kaipiaoForm.inumber"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="kaipiaoForm.iaddress"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="kaipiaoForm.iphone"></el-input>
          </el-form-item>
          <el-form-item label="开户银行">
            <el-input v-model="kaipiaoForm.ibank"></el-input>
          </el-form-item>
          <el-form-item label="银行账号">
            <el-input v-model="kaipiaoForm.ibankNumber"></el-input>
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
export default {
  inject: ["reload"],
  created() {
    this.axios
      .get("http://localhost:8081/customer/getall")
      .then((res) => {
        console.log(res)
        this.tableData = res.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    edits(data){
      this.id = data.cid
    },
    showDialog() {
      //显示Dialog弹框
      this.userAdd = true;
    },
    addDialog(num) {
      //显示Dialog弹框
      num = this.id
      this.userupdate = true;
      this.ruleForm.cid = num
    },
    closeDialog() {
      //弹框的关闭方法
      this.userAdd = false;
      this.userupdate = false;
    },
    SubmitEvent() {
      //确定事件
        this.axios
        .post("http://localhost:8081/customer/add",this.ruleForm)
        .then((res) => {
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
        this.axios.post("http://localhost:8081/contact/add",this.lianxiForm)
        .then((res)=>{
          if(res.data == 'success'){
            if(res.data == 'success'){
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
          }
        })
        .catch((error)=>{
          console.log(error)
        })
        this.axios.post("http://localhost:8081/receiving/add",this.shoujianForm)
        .then((res)=>{
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
        })
        .catch((error)=>{
          console.log(error)
        })
        this.axios.post("http://localhost:8081/invoicing/add",this.kaipiaoForm)
        .then((res)=>{
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
        })
        .catch((error)=>{
          console.log(error)
        })
    },
    SubmitEventTwo(){
      this.axios
        .put("http://localhost:8081/customer/update",this.ruleForm)
        .then((res) => {
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
        this.axios
        .put("http://localhost:8081/contact/update",this.ruleForm)
        .then((res) => {
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
        this.axios
        .put("http://localhost:8081/receiving/update",this.ruleForm)
        .then((res) => {
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
        this.axios
        .put("http://localhost:8081/invoicing/update",this.ruleForm)
        .then((res) => {
          if(res.data == 'success'){
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          }else{
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    del(data){
      data = this.id
      console.log(data)
      this.axios.delete("http://localhost:8081/customer/delete"+"/"+data)
      .then((res) => {
          if (res.data == "success") {
            this.$message({
              message: "恭喜你，删除成功",
              type: "success",
            });
            this.reload();
          } else {
            this.$message({
              message: "删除失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
          this.$message({
              message: "删除失败",
              type: "error",
            });
        });
    },
    updateYewu() {},
  },
  data() {
    return {
      id:"",
      userAdd: false, //弹框默认显示与否
      userupdate:false,
      update: [],

      tableData: [],
      lianxiForm:{
        cpId:'',
        cpDepartment:'',
        cpDuties:'',
        cpPhone:'',
        cpAddress:'',
        cpType:'',
      },
      shoujianForm:{
        rname:"",
        rphone:"",
        raddress:"",
      },
      kaipiaoForm:{
        iname:'',
        inumber:'',
        iphone:'',
        iaddress:'',
        ibank:'',
        ibankNumber:'',
      },
      ruleForm: {
        cid: "",
        cname: "",
        cfenl: "",
        cboss: "",
        clevel: "",
        ccustLevel: "",
        cregion: "",
        caddress: "",
        ctext: "",
        salesMan: "",
        cfile:"",
      },
      rules: {
        name: [],
      },
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
