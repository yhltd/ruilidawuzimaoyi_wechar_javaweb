<template>
    <div>
      <el-container style="height: auto;">
        <el-header id="one">商品</el-header>
        <br />
        <el-form :model="ruleForm" label-width="100px" inline>
          <el-form-item label="商品名称">
            <el-input v-model="ruleForm.gname"></el-input>
          </el-form-item>
          <el-form-item label="商品分类">
            <el-select v-model="ruleForm.gfenl" placeholder="客户分类">
              <el-option label="A类别" value="A类别"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位">
            <el-select v-model="ruleForm.gunit" placeholder="客户分类">
              <el-option label="个" value="个"> </el-option>
              <el-option label="吨" value="吨"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="材质">
            <el-input v-model="ruleForm.gmaterial"></el-input>
          </el-form-item>
          <el-form-item label="技术标准">
            <el-input v-model="ruleForm.gtechnology"></el-input>
          </el-form-item>
          <el-form-item label="质保等级">
            <el-select v-model="ruleForm.glevel" placeholder="客户分类">
              <el-option label="A" value="A"> </el-option>
              <el-option label="B" value="B"> </el-option>
              <el-option label="C" value="C"> </el-option>
              <el-option label="D" value="D"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="ruleForm.gtext"></el-input>
          </el-form-item>
          
          <el-container style="height: 600px;">
            <el-table
              :data="historyForm.tableData"
              style="width: 100%"
              class="list-table"
              size="mini"
              border
            >
              <el-table-column
                label="图片"
                align="center"
                prop="dimg"
                width="80px"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dimg"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="规格"
                align="center"
                prop="dspecifications"
                width="450px"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dspecifications"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="商品编号（条码）"
                align="center"
                prop="dgoodsId"
                width="450px"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dgoodsId"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="零售价格"
                align="center"
                prop="dretailPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dretailPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="批发价格"
                align="center"
                prop="dwholesalePrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dwholesalePrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="大客户价格"
                align="center"
                prop="dcomtuterPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dcomtuterPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="销项税率" align="center" prop="dsales">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dsales"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                label="采购价格"
                align="center"
                prop="dprocurementPrice"
              >
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dprocurementPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="进项税率" align="center" prop="dentry">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.dentry"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="启用" align="center" prop="denable">
                <template slot-scope="scope">
                  <el-checkbox v-model="scope.row.denable"></el-checkbox>
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
            
          </el-container>
          <el-button @click="addParamsSetting"
            ><i class="el-icon-plus"></i>添加规格</el-button
          >
          <el-button>批量添加规格</el-button>
        </el-form>
          
        
      <el-foot>
        <div class="foot">
          <el-button size="small">保存</el-button>
          <el-button size="small" @click="easyAdd">保存&新增</el-button>
          <el-button size="small" @click="returns">取消</el-button>
        </div>
      </el-foot>
      </el-container>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        uid:'',
        uname:'',
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
    created() {
        this.uid = this.$route.uid
        this.ruleForm.gid = this.uid
        this.uname = this.$route.uname
        this.ruleForm.gname = this.uname
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
        
        this.$router.push("/goods");
      },
      easyAdd() {
        

        this.axios
          .put("http://localhost:8081/goods/update", this.ruleForm)
          .then((res) => {
            if (res.data == "success") {
              this.$message({
                      message: "恭喜你，修改成功",
                      type: "success",
                    });
                    this.$router.push("/goods")
            } else {
              this.$message({
                message: "修改失败",
                type: "error",
              });
            }
          })
          .catch((error) => {
            console.log(error);
            this.$message({
                message: "修改失败",
                type: "error",
              });
          });
          this.axios
                .put(
                  "http://localhost:8081/details/update",
                  this.historyForm.tableData[0]
                )
                .then((res) => {
                  if (res.data == "success") {
                    this.$message({
                      message: "恭喜你，修改成功",
                      type: "success",
                    });
                  } else {
                    this.$message({
                      message: "修改失败",
                      type: "error",
                    });
                  }
                })
                .catch((error) => {
                  console.log(error);
                  this.$message({
                      message: "修改失败",
                      type: "error",
                    });
                });
      },
    },
  };
  </script>
  
  <style>
  .foot {
    text-align: center;
  }
  </style>
  