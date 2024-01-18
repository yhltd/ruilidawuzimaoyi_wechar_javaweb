template>
  <div>
    <el-container>
      <el-header>核算单位</el-header>
      <br />
      <div>
        <el-button size="medium" @click="showDialog"
          ><i class="el-icon-plus"></i>新增</el-button
        >
        <el-button size="medium"><i class="el-icon-s-marketing"></i>排序</el-button>
      </div>
      <br />
      <el-main>
        <el-table
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column prop="aname" label="核算单位"> </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="text"
                size="small"
                >编辑</el-button
              >
              <el-button type="text" size="small" @click="del(scope.row)"><i class="el-icon-delete"></i>删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    <el-dialog
      title="核算单位"
      :visible.sync="dialogAdd"
      width="40%"
      :fullscreen="false"
      :close-on-press-escape="false"
      show-close
      :close-on-click-modal="false"
      :before-close="closeDialog"
    >
      <span>
        <el-form :model="ruleForm" label-width="100px">
          <el-form-item label="核算单位">
            <el-input v-model="ruleForm.aname"></el-input>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog"><i class="el-icon-circe-close"></i>取 消</el-button>
        <el-button type="primary" @click="SubmitEvent"><i class="el-icon-check"></i>确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="核算单位"
      :visible.sync="update"
      width="40%"
      :fullscreen="false"
      :close-on-press-escape="false"
      show-close
      :close-on-click-modal="false"
      :before-close="closeDialog"
    >
      <span>
        <el-form :model="updateForm" label-width="100px">
          <el-form-item label="核算单位">
            <el-input v-model="updateForm.aname"></el-input>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog"><i class="el-icon-circe-close"></i>取 消</el-button>
        <el-button type="primary" @click="edit"><i class="el-icon-check"></i>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  inject: ["reload"],
  created() {
    this.axios
      .get("http://localhost:8102/account/getall")
      .then((res) => {
        this.tableData = res.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    handleClick(data) {
      this.datas = data.aid;
      this.update = true;
    },
    
    showDialog() {
      //显示Dialog弹框
      this.dialogAdd = true;
    },
    closeDialog() {
      //弹框的关闭方法
      this.dialogAdd = false;
      this.update = false;
    },
    SubmitEvent() {
      //确定事件
      this.axios
        .post("http://localhost:8102/account/add" + "/" + this.ruleForm.aname)
        .then((res) => {
          if (res.data == "success") {
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
            this.reload();
          } else {
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
        })
        .catch((error) => {});
    },
    edit() {
      this.axios
        .put(
          "http://localhost:8102/account/update" +
            "/" +
            this.updateForm.aname +
            "/" +
            this.datas
        )
        .then((res) => {
          if (res.data == "success") {
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          } else {
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          this.$message({
            message: "修改失败",
            type: "error",
          });
        });
    },
    del(data) {
      console.log(data.aid)
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios
            .delete("http://localhost:8102/account/delete" + "/" + data.aid)
            .then((res) => {
              if (res.data == "success") {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                this.reload();
              }
            })
            .catch((error) => {
              console.log(error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
  },
  data() {
    return {
      datas: "",
      dialogAdd: false, //弹框默认显示与否
      update: false,
      tableData: [],
      updateForm: [
        {
          aname: "",
        },
      ],
      ruleForm: [
        {
          aname: "",
        },
      ],
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
