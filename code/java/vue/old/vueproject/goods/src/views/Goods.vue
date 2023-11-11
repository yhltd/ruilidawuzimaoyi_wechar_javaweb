<template>
  <div>
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-header id="one">商品</el-header>
      <br />
      <div>
        <el-select v-model="value1">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-select v-model="value2" placeholder="上架状态">
          <el-option
            v-for="item in optionso"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-button size="medium"><i class="el-icon-refresh"></i>刷新</el-button>
        &nbsp;
        <el-button-group>
          <el-button size="medium" @click="edits()">新增</el-button>
          <el-button size="medium">导入</el-button>
        </el-button-group>
        &nbsp;
        <el-button-group>
          <el-button size="medium" @click="upd(id,name)">编辑</el-button>
          <el-button size="medium" @click="del(id)">删除</el-button>
        </el-button-group>
        &nbsp;
        <el-dropdown>
          <el-button size="medium">
            批量更改<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>启用</el-dropdown-item>
            <el-dropdown-item>禁用</el-dropdown-item>
            <el-dropdown-item>分类更改</el-dropdown-item>
            <el-dropdown-item>更改零售价格</el-dropdown-item>
            <el-dropdown-item>更改采购价格</el-dropdown-item>
            <el-dropdown-item>更改最低库存</el-dropdown-item>
            <el-dropdown-item>批量上传更改指定字段</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <br />
      <el-container  style="height: 600px">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <h5>全部分类</h5>
          <el-menu>
            <el-menu-item index="1-1">全部</el-menu-item>
            <el-menu-item index="1-2">默认分类</el-menu-item>
          </el-menu>
        </el-aside>

        
          <el-main>
            <div class="table">
              <el-table
                :data="tableData"
                style="width: 100%"
                :row-class-name="tableRowClassName"
              >
                <el-table-column width="50">
                  <template slot-scope="scope">
                    <el-checkbox @click.native="dels(scope.row)"></el-checkbox>
                  </template>
                </el-table-column>
                <el-table-column prop="gid" label="商品编号"> </el-table-column>
                <el-table-column prop="gname" label="商品名称">
                </el-table-column>
                <el-table-column prop="gfenl" label="分类"> </el-table-column>
                <el-table-column prop="gunit" label="单位"> </el-table-column>
              </el-table>
            </div>
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
  </div>
</template>

<script>
export default {
  inject: ["reload"],
  created() {
    this.axios
      .get("http://localhost:8081/goods/getall")
      .then((res) => {
        this.tableData = res.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    upd(data1,data2){
      data1 = this.uid
      data2 = this.uname
      this.$router.push({
        path:'/updgoods',
        query:{
            uid : data1,
            uname:data2
        }
      })
    },
    dels(data) {
      this.uid = data.gid;
      this.uname = data.gname
    },
    del(data) {
      data = this.uid;
      this.axios
        .delete("http://localhost:8081/goods/del" + "/" + data)
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
        });
    },
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
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    edits() {
      this.$router.push("/editgoods");
    },
  },
  data() {
    return {
      uid: "",
      uname:"",
      tableData: [],
      options: [
        {
          value: "选项1",
          label: "启用",
        },
        {
          value: "选项2",
          label: "禁用",
        },
      ],
      value1: "选项1",
      optionso: [
        {
          value: "选项1",
          label: "上架",
        },
        {
          value: "选项2",
          label: "下架",
        },
      ],
      value2: "",
    };
  },
};
</script>

<style scoped>
#one {
  background-color: #b3c0d1;
  line-height: 60px;
}
.el-aside {
  background-color: #d3dce6;
}
.page {
  text-align: center;
}
.table {
  height: 100%;
}
</style>
