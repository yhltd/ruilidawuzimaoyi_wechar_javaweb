<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="类型" v-model="type" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="query()">查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="refresh()">刷新</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="deleteClick()">删除</el-button>
      </el-col>
    </el-row>
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="name"
          label="商品名称"
          width="120">
      </el-table-column>
      <el-table-column
          prop="type"
          label="商品分类"
          width="120">
      </el-table-column>
      <el-table-column
          prop="danwei"
          label="单位"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="caizhi"
          label="材质"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jishuBiaozhun"
          label="技术标准"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="zhibaoDengji"
          label="质保等级"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="beizhu"
          label="备注"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
    </el-table>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="Product" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        商品基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">商品基本信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="商品名称" prop="name" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Product.name" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="商品分类" prop="type" class="custom-form-item">
              <el-select v-model="Product.type" clearable filterable placeholder="请选择分类">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_Level"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单位" prop="danwei" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Product.danwei" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="材质" prop="caizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Product.caizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="技术标准" prop="jishuBiaozhun" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Product.jishuBiaozhun" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="质保等级" prop="zhibaoDengji" class="custom-form-item">
              <el-select v-model="Product.zhibaoDengji" clearable filterable placeholder="请选择采购员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_CaiGouYuan"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.caigouyuan" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Product.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <div v-for="(item, index) in Product.body">
          <!--        联系人信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">商品信息{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="规格" prop="guige" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].guige" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="编号" prop="bianhao" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].bianhao" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="零售价格" prop="lingshouPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].lingshouPrice" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="零售上浮比例" prop="lingshouBili" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].lingshouBili" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="批发价格" prop="pifaPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].pifaPrice" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="批发上浮比例" prop="pifaBili" class="custom-form-item">
                  <el-input ref="acc_inp" v-model="Product.body[index].pifaBili" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="大客户价格" prop="dakehuPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].dakehuPrice" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="大客户上浮比例" prop="dakehuBili" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].dakehuBili" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="采购价格" prop="caigouPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].caigouPrice" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="进项税率" prop="jinxiang" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].jinxiang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="销项税率" prop="xiaoxiang" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].xiaoxiang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="是否启用" prop="enable" class="custom-form-item">
                <el-select v-model="Product.type" clearable filterable placeholder="请选择分类">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_Level"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>

            <el-button v-if="index > 0" class="custom-login-button" :loading="login_btn_loading" type="primary"
                       @click="delLianXiRen(index)">删除
            </el-button>
          </el-row>
        </div>



        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                         @click="addItem">添加商品
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                         @click="save">保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                         @click="addClose">取消
              </el-button>
            </div>
          </el-col>
        </el-row>



      </el-form>
    </el-dialog>


  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      name:'',
      type:'',
      XiaLa_JianZuoKeHu:[
        {
          name:'是',
          label:'是',
        },
        {
          name:'否',
          label:'否',
        }
      ],
      XiaLa_LianXiRenLeiXing:[
        {
          name:'联系人',
          label:'联系人',
        },
        {
          name:'主联系人',
          label:'主联系人',
        }
      ],
      Product: {
        name:'',
        type:'',
        danwei:'',
        caizhi:'',
        jishu_biaozhun:'',
        zhibao_dengji:'',
        beizhu:'',
        body:[
          {
            product_id:'',
            image:'',
            guige:'',
            bianhao:'',
            lingshou_price:'',
            lingshou_bili:'',
            pifa_price:'',
            pifa_bili:'',
            dakehu_price:'',
            dakehu_bili:'',
            caigou_price:'',
            jinxiang:'',
            xiaoxiang:'',
            enable:'',
          }
        ]
      },
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getXiaLa_Level();
    this.getXiaLa_CaiGouYuan();
    this.getAll();
  },
  methods: {
    toggleSelection(rows) {
      console.log(rows)
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },

    //新增窗口弹出
    addUser() {
      this.Product = {
            name:'',
            type:'',
            danwei:'',
            caizhi:'',
            jishu_biaozhun:'',
            zhibao_dengji:'',
            beizhu:'',
            body:[
          {
            product_id:'',
            image:'',
            guige:'',
            bianhao:'',
            lingshou_price:'',
            lingshou_bili:'',
            pifa_price:'',
            pifa_bili:'',
            dakehu_price:'',
            dakehu_bili:'',
            caigou_price:'',
            jinxiang:'',
            xiaoxiang:'',
            enable:'',
          }
        ]
      }

      this.addDialog = true;
    },

    addClose(){
      this.addDialog = false;
    },

    //修改窗口弹出
    updUser() {
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }else if(this.multipleSelection.length > 1){
        MessageUtil.error("请选择一条明细信息再进行修改");
        return;
      }
      var this_id = this.multipleSelection[0].id

      console.log(this.multipleSelection)

      let url = "http://localhost:8081/product/selectById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.itemList
          this.Product = this_val
          console.log(res.data.data);
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

    },

    getXiaLa_Level(){
      let url = "http://localhost:8081/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"供应商等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_Level = res.data.data;
          for(var i=0; i<this.XiaLa_Level.length; i++){
            this.XiaLa_Level[i].label = this.XiaLa_Level.name
          }
          console.log("供应商等级下拉已获取");
        } else {
          console.log("供应商等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_CaiGouYuan(){
      let url = "http://localhost:8081/user/fuzzyQuery"
      this.axios.post(url,{"keyword":""}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_CaiGouYuan = res.data.data;
          for(var i=0; i<this.XiaLa_CaiGouYuan.length; i++){
            this.XiaLa_CaiGouYuan[i].label = this.XiaLa_CaiGouYuan.name
          }
          console.log("供应商等级下拉已获取");
        } else {
          console.log("供应商等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      let url = "http://localhost:8081/product/getAll"
      this.axios(url, this.form).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          console.log(res.data.data)
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //刷新
    refresh(){
      this.bianhao = ""
      this.name = ""
      this.getAll()
    },

    //条件查询
    query(){
      var date = {
        name:this.name,
        type:this.type,
        enable:'',
      }
      let url = "http://localhost:8081/product/queryList"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    delLianXiRen(index){
      console.log(index)
      this.$confirm('是否删除此联系人?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.Product.body.splice(index, 1)
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    addItem(){
      var this_lianxiren = this.Product.body
      this_lianxiren.push({
        id:0,
        product_id:'',
        image:'',
        guige:'',
        bianhao:'',
        lingshou_price:'',
        lingshou_bili:'',
        pifa_price:'',
        pifa_bili:'',
        dakehu_price:'',
        dakehu_bili:'',
        caigou_price:'',
        jinxiang:'',
        xiaoxiang:'',
        enable:'',
      })
      this.Product.body = this_lianxiren
    },

    saveGongYingShang(){
      var save_list = this.Product
      let url = "http://localhost:8081/product/productAdd"
      this.axios.post(url, {
        "head":this.Product,
        "body":this.Product.body
      }).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.addDialog = false;
          this.query()
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    updGongYingShang(){
      var save_list = this.Product
      let url = "http://localhost:8081/product/productUpd"
      this.axios.post(url, {
        "head":this.Product,
        "body":this.Product.body
      }).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.addDialog = false;
          this.query()
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    save(){
      if(this.Product.id != undefined && this.Product.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否删除当前选中的客户信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8081/product/delProduct";
        axios.post(url, {"list": list}).then(res => {
          MessageUtil.success(res.data.msg);
          this.del_popover_visible = false;
          this.del_loading = false;
          this.query()
        }).catch(() => {
          MessageUtil.error("网络异常");
          this.del_loading = false;
          this.del_popover_visible = false;
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  }
}
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>