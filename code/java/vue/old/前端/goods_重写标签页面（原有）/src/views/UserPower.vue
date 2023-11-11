<template>
  <el-container>
    <el-main>
<!--      <el-table-->
<!--          :data="tableData"-->
<!--          height="250"-->
<!--          border-->
<!--          style="width: 100%">-->
<!--        <el-table-column-->
<!--            prop="name"-->
<!--            label="权限名"-->
<!--            width="180">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="zhanghaoguanliAdd"-->
<!--            label="添加账号"-->
<!--            width="50">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="zhanghaoguanliSel"-->
<!--            label="查询账号">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="zhanghaoguanliDel"-->
<!--            label="删除账号">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="zhanghaoguanliUpd"-->
<!--            label="账号修改">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="quanxianAdd"-->
<!--            label="权限添加">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="quanxianDel"-->
<!--            label="权限删除">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="quanxianUpd"-->
<!--            label="权限修改">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="quanxianSel"-->
<!--            label="权限查询">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="kehuAdd"-->
<!--            label="添加客户">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="kehuDel"-->
<!--            label="删除客户">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="kehuUpd"-->
<!--            label="修改客户">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="kehuSel"-->
<!--            label="查询客户">-->
<!--        </el-table-column>-->
<!--      </el-table>-->
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      tableData: {
        id: 0,
        zhanghaoguanliAdd:'',
        zhanghaoguanliDel:'',
        zhanghaoguanliUpd:'',
        zhanghaoguanliSel :'',
        quanxianAdd:'',
        quanxianDel:'',
        quanxianUpd:'',
        quanxianSel:'',
        kehuAdd:'',
        kehuDel:'',
        kehuUpd:'',
        kehuSel:'',
      }
    }
  }

}
</script>

<style scoped>

</style>