<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="Name" width="180"></el-table-column>
      <el-table-column prop="age" label="Age" width="180"></el-table-column>
      <!-- 添加其他表格列 -->
      <el-table-column label="Action">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.selected" type="success" closable @close="handleClose(scope.$index, scope.row)"></el-tag>
          <el-tag v-else type="danger" closable @close="handleSelect(scope.$index, scope.row)"></el-tag>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize"></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [], // 表格数据
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示条目数
    };
  },
  methods: {
    // 处理选择操作
    handleSelect(index, row) {
      this.tableData[index].selected = !this.tableData[index].selected; // 切换选中状态
    },
    // 处理关闭操作
    handleClose(index, row) {
      this.tableData.splice(index, 1); // 从数据中删除选中的行
    },
    // 处理分页大小改变事件
    handleSizeChange(val) {
      this.pageSize = val; // 更新每页显示条目数
      this.fetchData(); // 重新获取数据并渲染表格
    },
    // 处理当前页码改变事件
    handleCurrentChange(val) {
      this.currentPage = val; // 更新当前页码
      this.fetchData(); // 重新获取数据并渲染表格
    },
    // 获取数据的函数，根据实际需求实现该函数，从后端接口或其他数据源获取数据并更新到tableData中。这里只是模拟数据。
    fetchData() {
      // 这里只是模拟数据，实际开发中需要从后端接口或其他数据源获取数据。
      const newData = [...this.tableData]; // 创建新数组以避免改变原始数据
      for (let i = 0; i < newData.length; i++) {
        newData[i].selected = false; // 默认所有行都未选中
      }
      this.tableData = newData; // 更新表格数据
    },
  },
};
</script>


<style scoped>
/* 添加一个整体的边框 */
table {
border-collapse: collapse;
border: 1px solid #ccc;
}

/* 添加表头样式 */
th {
background-color: #f2f2f2;
color: #333;
font-weight: bold;
text-align: center;
}

/* 添加表格内容样式 */
td {
padding: 8px;
text-align: center;
border-bottom: 1px solid #eee;
}

/* 添加一些额外的样式来增强可读性 */
th[data-priority="high"] {
color: #007bff;
}

th[data-priority="medium"] {
color: #6c757d;
}

th[data-priority="low"] {
color: #ced4da;
}

/* 设置表格的自适应宽度和高度 */
table {
  width: 100%; /* 设置表格宽度为父元素的100% */
  height: auto; /* 设置表格高度为自动，以适应内容的高度 */
}

</style>