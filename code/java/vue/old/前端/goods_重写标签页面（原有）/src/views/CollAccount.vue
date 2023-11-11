<template>
  <div>
    <el-container>
      <el-header>账户</el-header>
      <br />
      <div>
        <el-button size="medium" @click="showDialog"><i class="el-icon-plus"></i>新增</el-button>
        <el-button size="medium">排序</el-button>
      </div>
      <br />
      <el-main>
        <el-table
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column prop="date" label="客户编号"> </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="text"
                size="small"
                >编辑</el-button
              >
              <el-button type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    <el-dialog
      title="编辑"
      :visible.sync="userAdd"
      width="40%"
      :fullscreen="false"
      :close-on-press-escape="false"
      show-close
      :close-on-click-modal="false"
      :before-close="closeDialog"
    >
      <span>
        <el-form
          :model="ruleForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        
        >
          <el-form-item label="客户编号" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="SubmitEvent">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    showDialog() {
      //显示Dialog弹框
      this.userAdd = true;
    },
    closeDialog() {
      //弹框的关闭方法
      this.userAdd = false;
    },
    SubmitEvent() {
      //确定事件
    },
  },
  data() {
    return {
      userAdd: false, //弹框默认显示与否
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      ruleForm:[
        {
          name:'',
        }
      ]
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
</style>
