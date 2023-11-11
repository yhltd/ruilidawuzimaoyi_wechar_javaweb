<template>
    <div>
      <el-container>
        <el-header id="one">商品</el-header>
        <br />
        <el-form :model="ruleForm" label-width="100px" inline>
          <el-form-item label="订单编号">
            <el-input v-model="ruleForm.gname"></el-input>
          </el-form-item>
          <el-form-item label="订单日期">
            <el-input v-model="ruleForm.gname"></el-input>
          </el-form-item>
          <el-form-item label="供应商">
            <el-input v-model="ruleForm.gname"></el-input>
          </el-form-item>
          <el-form-item label="店铺">
            <el-select v-model="ruleForm.glevel" placeholder="客户分类">
              <el-option label="总店" value="总店"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="进项税率">
            <el-input v-model="ruleForm.gtechnology"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="ruleForm.gtext"></el-input>
          </el-form-item>
          <el-button>上传</el-button>
          <div class="app-container home">
            <div class="table">
            <el-table
              :data="historyForm.tableData"
              style="width: 100%"
              class="list-table"
              size="mini"
              border
            >
              <el-table-column
                label="#"
                align="center"
                prop="dimg"
                width="80px"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dimg"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="商品名称"
                align="center"
                prop="dspecifications"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dspecifications"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="规格型号"
                align="center"
                prop="dgoodsId"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dgoodsId"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="材质"
                align="center"
                prop="dretailPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dretailPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="技术标准"
                align="center"
                prop="dwholesalePrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dwholesalePrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="技术等级"
                align="center"
                prop="dcomtuterPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dcomtuterPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="单位" align="center" prop="dsales">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dsales"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="数量"
                align="center"
                prop="dprocurementPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dprocurementPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="采购单价/含税" align="center" prop="dentry">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dentry"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="备注" align="center" prop="dentry">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dentry"></el-input>
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
            ><i class="el-icon-plus"></i>添加规格</el-button
          >
          <el-button>批量添加规格</el-button>
        </div>
      </el-container>
      <el-foot>
        <div class="foot">
          <el-button size="small">保存</el-button>
          <el-button size="small" @click="easyAdd">保存&新增</el-button>
          <el-button size="small" @click="returns">取消</el-button>
        </div>
      </el-foot>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        historyForm: {
          tableData: [
            {
              dimg: "",
              dcomtuterPrice: "",
              dspecifications: "",
              dwholesalePrice: "",
              dprocurementPrice: "",
              dgoodsId: "",
              dwholesalePro: "",
              dproportion: "",
              denable: "",
              dcomyuterPro: "",
              dcomyuterPro: "",
              dretailPrice: "",
              dentry: "",
            },
          ],
        },
        tableData: [],
        ruleForm: {
          gid: "",
          gname: "",
          gfenl: "",
          gunit: "",
          gmaterial: "",
          gtechnology: "",
          glevel: "",
          gtext: "",
        },
      };
    },
    methods: {
      addList() {
        this.historyForm.tableData.push({
          dimg: "",
          dcomtuterPrice: "",
          dspecifications: "",
          dwholesalePrice: "",
          dprocurementPrice: "",
          dgoodsId: "",
          dwholesalePro: "",
          dproportion: "",
          denable: "",
          dcomyuterPro: "",
          dcomyuterPro: "",
          dretailPrice: "",
          dentry: "",
        });
      },
      // 新增行
      addParamsSetting() {
        this.addList();
      },
      // 删除当前行
      deleteRow(index) {
        this.historyForm.tableData.splice(index, 1);
      },
      returns() {
        this.$router.push("/dingdan");
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
  