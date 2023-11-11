<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
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
          <el-button size="medium" @click="edits()">编辑</el-button>
          <el-button size="medium">删除</el-button>
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
      <el-container>
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <h5>全部分类</h5>
          <el-menu>
            <el-menu-item index="1-1">全部</el-menu-item>
            <el-menu-item index="1-2">默认分类</el-menu-item>
          </el-menu>
        </el-aside>

        <el-container>
          <el-main>
            <el-table
              :data="tableData"
              style="width: 100%"
              :row-class-name="tableRowClassName"
            >
              <el-table-column width="50">
                <el-checkbox></el-checkbox>
              </el-table-column>
              <el-table-column prop="gid" label="商品编号"> </el-table-column>
              <el-table-column prop="gname" label="商品名称"> </el-table-column>
              <el-table-column prop="gfenl" label="分类"> </el-table-column>
              <el-table-column prop="gtechnology" label="单位">
              </el-table-column>
            </el-table>
          </el-main>
        </el-container>
      </el-container>
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
  </div>
</template>

<script>
export default {
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
      optionsoo: [
        {
          value: "选项1",
          label: "上架",
        },
        {
          value: "选项2",
          label: "下架",
        },
      ],
      value3: "",
    };
  },
};
</script>

<style>
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
</style>
