<template>
  <div>
    <el-container direction="vertical">
      <el-form :model="ruleForm" label-width="100px" inline>
        <el-form-item label="编号">
          <el-input v-model="ruleForm.bianhao"></el-input>
        </el-form-item>
        <el-form-item label="订单日期">
          <el-input v-model="ruleForm.riqi"></el-input>
        </el-form-item>
        <el-form-item label="供应商">
          <el-input v-model="ruleForm.gongyingshang"></el-input>
        </el-form-item>
        <el-form-item label="店铺">
          <el-select v-model="ruleForm.dianpu" placeholder="客户分类">
            <el-option label="总店" value="总店"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="进项税率">
          <el-input v-model="ruleForm.jinxiangShuilv"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="ruleForm.beizhu"></el-input>
        </el-form-item>
        <el-button>上传</el-button>
        <div class="app-container home">
          <div class="table">
            <el-table
                :data="cacheTableList"
                style="width: 100%"
                class="list-table"
                size="mini"
                border
            >
              <el-table-column
                  label="#"
                  align="center"
                  prop="shangpinBianma"
                  width="80px"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.shangpinBianma"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="商品名称"
                  align="center"
                  prop="name"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.name"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="规格型号"
                  align="center"
                  prop="guige"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.guige"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="材质"
                  align="center"
                  prop="caizhi"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.caizhi"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="技术标准"
                  align="center"
                  prop="jishuBiaozhun"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.jishuBiaozhun"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="技术等级"
                  align="center"
                  prop="jishuDengji"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.jishuDengji"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="单位" align="center" prop="danwei">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.danwei"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                  label="数量"
                  align="center"
                  prop="shuliang"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.shuliang"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="采购单价/含税" align="center" prop="caigouDanjia">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.caigouDanjia"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="备注" align="center" prop="beizhu">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.beizhu"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="审核人" align="center" prop="dentry">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dentry"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="状态" align="center" prop="dentry">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dentry"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" prop="dose">
                <template slot-scope="scope">
                  <el-button
                      icon="el-icon-delete"
                      size="small"
                      @click.native.prevent="deleteRow(scope.$index)"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-form>
      <div>
        <el-button @click="addParamsSetting"
        ><i class="el-icon-plus"></i>添加规格
        </el-button
        >
        <el-button>批量添加规格</el-button>
      </div>
    </el-container>
    <el-foot>
      <div class="foot">
        <el-button size="small">保存</el-button>
        <el-button size="small" @click="easyAdd">保存&新增</el-button>
        <el-button size="small" @click="cancel()">取消</el-button>
      </div>
    </el-foot>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cacheTableList: [
        {
          shangpinBianma: "e",
          jishuDengji: "d",
          name: "d",
          jishuBiaozhun: "d",
          shuliang: "d",
          guige: "d",
          dwholesalePro: "d",
          dproportion: "d",
          denable: "d",
          dcomyuterPro: "d",
          caizhi: "d",
          caigouDanjia: "d",
        },
      ],
      tableData: [],
      ruleForm: {
        id: "",
        bianhao: '',
        riqi: "",
        gongyingshang: "",
        dianpu: "",
        gmaterial: "",
        jinxiangShuilv: "",
        glevel: "",
        beizhu: "",
      },
      dateRange: '',
      beginDate: '',
      endDate: '',
      // fu
    };
  },
  methods: {
    submit() {
      let url = ""
      this.axios.post(url, {'baseInfo': this.ruleForm, 'list': this.cacheTableList}).then(res => {
        if (res.data.code == '') {
        }
      })
    },
    addList() {
      this.cacheTableList.push({
        shangpinBianma: "",
        jishuDengji: "",
        name: "",
        jishuBiaozhun: "",
        shuliang: "",
        guige: "",
        dwholesalePro: "",
        dproportion: "",
        denable: "",
        dcomyuterPro: "",
        caizhi: "",
        dentry: "",
      });
    },
    // 新增行
    addParamsSetting() {
      this.addList();
    },
    // 删除当前行
    deleteRow(index) {
      this.cacheTableList.splice(index, 1);
    },
    /**
     * 取消
     */
    cancel() {
      this.$refs['vertical'].resetFields();
      this.cacheTableList.length = 0;
      this.$emit.closeDialog();
    },

  },
};
</script>

<style>
.foot {
  text-align: center;
}

.table {
  height: 70%;
}
</style>
  