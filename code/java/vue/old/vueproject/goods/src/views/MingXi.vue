<template>
    <div>
      <div>
          
        <el-select v-model="ruku" placeholder="入库状态">
          <el-option
            v-for="item in rukus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-select v-model="fukuan" placeholder="付款状态">
          <el-option
            v-for="item in fukuans"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-button size="medium"><i class="el-icon-refresh"></i>刷新</el-button>
        &nbsp;
        <el-button size="medium" @click="jump()">新增</el-button>
        &nbsp;
        <el-button size="medium">自己创建的订单</el-button>
        &nbsp;
        <el-button size="medium">可审核的订单</el-button>
      </div>
      <br />
      <el-main>
        <el-table
          border
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column width="50">
            <template slot-scope="scope">
              <el-checkbox @click.native="edits(scope.row)"></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column prop="cid" label="订单编号"> </el-table-column>
          <el-table-column prop="cname" label="订单日期"> </el-table-column>
          <el-table-column prop="cfenl" label="供应商"> </el-table-column>
          <el-table-column prop="cboss" label="价税小记"> </el-table-column>
          <el-table-column prop="clevel" label="备注"> </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small"
                >编辑</el-button
              >
              <el-button type="text" size="small" @click="del(scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-main>
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
    data() {
      return {
        rukus: [
          {
            value: "选项1",
            label: "已入库",
          },
          {
            value: "选项2",
            label: "未入库",
          },
        ],
        ruku: "",
        fukuans: [
          {
            value: "选项1",
            label: "已付款",
          },
          {
            value: "选项2",
            label: "未付款",
          },
        ],
        fukuan: "",
      }
    },
    methods: {
      jump(){
          this.$router.push('/dingdanadd')
      },
    },
  };
  </script>
  
  <style scoped></style>
  