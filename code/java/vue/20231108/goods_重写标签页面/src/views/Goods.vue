<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
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
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()">添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updUser()">编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteClick()">删除</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          ref="multipleTable"
          :data="tableData.slice((currentPage -1) * pageSize, pageSize * currentPage)"
          tooltip-effect="dark"
          style="width: 100%"
          :height="tableHeight"
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
        <el-table-column
            prop="bianhao"
            label="商品编号"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="guige"
            label="规格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="lingshouPrice"
            label="零售价格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="lingshouBili"
            label="零售上浮比例"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="pifaPrice"
            label="批发价格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="pifaBili"
            label="批发上浮比例"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="dakehuPrice"
            label="大客户价格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="dakehuBili"
            label="大客户上浮比例"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="caigouPrice"
            label="采购价格"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="jinxiang"
            label="进项税率"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="xiaoxiang"
            label="销项税率"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="enable"
            label="是否启用"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
      </el-table>
    </el-main>

    <el-footer style="height: 10%;margin-bottom: 5%">
      <el-pagination
          :currentPage="currentPage"
          :page-sizes="[10,20,30,40,50]"
          :page-size="pageSize"
          background
          layout="total, sizes, prev,pager,next,jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-footer>


    <el-dialog title="" :visible.sync="addDialog" width="90%">

      <el-form :model="Product" ref="addUsr" label-width="100px"
               class="demo-info">

        <input ref="file" type="file" id="pic_file" @change="fileSelect()" style="display: none">
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
                    v-for="item in XiaLa_ShangPinFenLei"
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
              <el-select v-model="Product.zhibaoDengji" clearable filterable placeholder="请选择质保等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ZhiBaoDengJi"
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
              <el-form-item label="商品图片" prop="image" class="custom-form-item">
                <div style="height: 150px" >
                  <img class="product" :src="Product.body[index].image"></img>
                </div>
              </el-form-item>
            </el-col>

            <el-col :span="3">
              <el-button class="custom-login-button"  type="primary"
                         @click="uploadImg(index)">上传图片
              </el-button>
            </el-col>
            <el-col :span="3">
              <el-button class="custom-login-button"  type="primary"
                         @click="deleteImg(index)">删除图片
              </el-button>
            </el-col>
            <el-col :span="3">
              <el-button v-if="index > 0" class="custom-login-button"  type="primary"
                         @click="delLianXiRen(index)">删除商品
              </el-button>
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
                <el-input ref="acc_inp" v-model="Product.body[index].bianhao" class="custom-login-inp" disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="零售价格" prop="lingshouPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].lingshouPrice" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="零售上浮比例%" prop="lingshouBili" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].lingshouBili" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="批发价格" prop="pifaPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].pifaPrice" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="批发上浮比例%" prop="pifaBili" class="custom-form-item">
                  <el-input ref="acc_inp" v-model="Product.body[index].pifaBili" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="大客户价格" prop="dakehuPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].dakehuPrice" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="大客户上浮比例%" prop="dakehuBili" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].dakehuBili" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="采购价格" prop="caigouPrice" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].caigouPrice" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="进项税率%" prop="jinxiang" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].jinxiang" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="销项税率%" prop="xiaoxiang" class="custom-form-item">
                <el-input ref="acc_inp" v-model="Product.body[index].xiaoxiang" class="custom-login-inp" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="是否启用" prop="enable" class="custom-form-item">
                <el-select v-model="Product.body[index].enable" clearable filterable placeholder="请选择是否启用">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_ShiFouQiYong"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
          </el-row>
        </div>



        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addItem">添加商品
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save">保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
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
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      name:'',
      type:'',
      XiaLa_ShangPinFenLei:[],
      XiaLa_ZhiBaoDengJi:[],
      XiaLa_ShiFouQiYong:[
        {
          name:'是',
          label:'是',
        },
        {
          name:'否',
          label:'否',
        }
      ],
      Product: {
        name:'',
        type:'',
        danwei:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        beizhu:'',
        body:[
          {
            productId:'',
            image:'',
            guige:'',
            bianhao:'',
            lingshouPrice:'',
            lingshouBili:'',
            pifaPrice:'',
            pifaBili:'',
            dakehuPrice:'',
            dakehuBili:'',
            caigouPrice:'',
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
    this.getXiaLa_ShangPinFenLei();
    this.getXiaLa_ZhiBaoDengJi();
    this.getUser();
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
      if(this.userPower.shangpinAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }

      let url = "http://localhost:8102/product/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,4)
          console.log(this_danhao)
          this_danhao = "P" + this_danhao
          this.Product = {
            name:'',
            type:'',
            danwei:'',
            caizhi:'',
            jishuBiaozhun:'',
            zhibaoDengji:'',
            beizhu:'',
            body:[
              {
                productId:'',
                image:'',
                guige:'',
                bianhao:this_danhao,
                lingshouPrice:'',
                lingshouBili:'',
                pifaPrice:'',
                pifaBili:'',
                dakehuPrice:'',
                dakehuBili:'',
                caigouPrice:'',
                jinxiang:'',
                xiaoxiang:'',
                enable:'是',
              }
            ]
          }

          console.log(res.data.data);
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

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

      let url = "http://localhost:8102/product/selectById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.itemList
          this.Product = this_val
          var this_item = []

          for(var i=0; i<this_val.itemList.length; i++){
            this_item.push({
              productId:this_val.itemList[i].productId,
              image:this_val.itemList[i].image,
              guige:this_val.itemList[i].guige,
              bianhao:this_val.itemList[i].bianhao,
              lingshouPrice:this_val.itemList[i].lingshouPrice,
              lingshouBili:this_val.itemList[i].lingshouBili,
              pifaPrice:this_val.itemList[i].pifaPrice,
              pifaBili:this_val.itemList[i].pifaBili,
              dakehuPrice:this_val.itemList[i].dakehuPrice,
              dakehuBili:this_val.itemList[i].dakehuBili,
              caigouPrice:this_val.itemList[i].caigouPrice,
              jinxiang:this_val.itemList[i].jinxiang,
              xiaoxiang:this_val.itemList[i].xiaoxiang,
              enable:this_val.itemList[i].enable,
            })
          }
          this.oldProduct = {
            name:this_val.name,
            type:this_val.type,
            danwei:this_val.danwei,
            caizhi:this_val.caizhi,
            jishuBiaozhun:this_val.jishuBiaozhun,
            zhibaoDengji:this_val.zhibaoDengji,
            beizhu:this_val.beizhu,
            body:this_item
          }
          console.log(this.Product)
          console.log(this.oldProduct)
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

    getUser(){
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://localhost:8102/user/queryUserInfoById"
      this.axios.post(url,{"id":this.userInfo.id}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userInfo = res.data.data
          window.localStorage.setItem('userInfo',JSON.stringify(res.data.data))
          console.log("账号信息已获取");
        } else {
          console.log("账号信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
      let poweruUrl = "http://localhost:8102/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          if(this.userPower.shangpinSel == '是'){
            this.getAll();
          }else{
            MessageUtil.error("无查询权限");
          }
          window.localStorage.setItem('userPower',JSON.stringify(res.data.data))
          console.log("权限信息已获取");
        } else {
          console.log("权限信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ShangPinFenLei(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"商品分类"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ShangPinFenLei = res.data.data;
          for(var i=0; i<this.XiaLa_ShangPinFenLei.length; i++){
            this.XiaLa_ShangPinFenLei[i].label = this.XiaLa_ShangPinFenLei.name
          }
          console.log("商品分类下拉已获取");
        } else {
          console.log("商品分类下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ZhiBaoDengJi(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"质保等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ZhiBaoDengJi = res.data.data;
          for(var i=0; i<this.XiaLa_ZhiBaoDengJi.length; i++){
            this.XiaLa_ZhiBaoDengJi[i].label = this.XiaLa_ZhiBaoDengJi.name
          }
          console.log("质保等级下拉已获取");
        } else {
          console.log("质保等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_CaiGouYuan(){
      let url = "http://localhost:8102/user/fuzzyQuery"
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
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://localhost:8102/product/getAll"
      this.axios(url, this.form).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;

          let url = "http://localhost:8102/peiZhiShuiLv/getAll"
          this.axios(url, this.form).then(res => {
            if(res.data.code == '00') {
              this.ShuiLv = res.data.data[0];
              var fujia_shuilv = 1
              if(this.ShuiLv.zhuangtai == '是'){
                fujia_shuilv = fujia_shuilv + (this.ShuiLv.shuilv / 100)
              }
              for(var i=0; i<this.tableData.length; i++){
                var jinxiang = this.tableData[i].jinxiang / 100
                var xiaoxiang = this.tableData[i].xiaoxiang / 100
                var pifa_bili = this.tableData[i].pifaBili / 100
                var lingshou_bili = this.tableData[i].lingshouBili / 100
                var dakehu_bili = this.tableData[i].dakehuBili / 100
                var caigou_price = this.tableData[i].caigouPrice * 1
                this.tableData[i].lingshouPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * lingshou_bili)) * 100) / 100
                this.tableData[i].pifaPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * pifa_bili)) * 100 ) / 100
                this.tableData[i].dakehuPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * dakehu_bili)) * 100) / 100
              }
            } else {
              MessageUtil.error(res.data.msg);
            }
          }).catch(() => {
            MessageUtil.error("网络异常");
          })

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
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    //条件查询
    query(){
      if(this.userPower.shangpinSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        name:this.name,
        type:this.type,
        enable:'',
      }
      let url = "http://localhost:8102/product/queryList"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;

          let url = "http://localhost:8102/peiZhiShuiLv/getAll"
          this.axios(url, this.form).then(res => {
            if(res.data.code == '00') {
              this.ShuiLv = res.data.data[0];
              var fujia_shuilv = 1
              if(this.ShuiLv.zhuangtai == '是'){
                fujia_shuilv = fujia_shuilv + (this.ShuiLv.shuilv / 100)
              }
              for(var i=0; i<this.tableData.length; i++){
                var jinxiang = this.tableData[i].jinxiang / 100
                var xiaoxiang = this.tableData[i].xiaoxiang / 100
                var pifa_bili = this.tableData[i].pifaBili / 100
                var lingshou_bili = this.tableData[i].lingshouBili / 100
                var dakehu_bili = this.tableData[i].dakehuBili / 100
                var caigou_price = this.tableData[i].caigouPrice * 1
                this.tableData[i].lingshouPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * lingshou_bili)) * 100) / 100
                this.tableData[i].pifaPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * pifa_bili)) * 100 ) / 100
                this.tableData[i].dakehuPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * dakehu_bili)) * 100) / 100
              }
            } else {
              MessageUtil.error(res.data.msg);
            }
          }).catch(() => {
            MessageUtil.error("网络异常");
          })

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
      this.$confirm('是否删除此商品?', '提示', {
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
      var list = this.Product.body
      if(list.length > 1){
        var this_bianhao = this.Product.body[list.length -1].bianhao
        var this_head = this_bianhao.substr(0,6)
        var this_foot = this_bianhao.substr(6,3)
        this_foot = parseInt(this_foot) + 1
        this_bianhao = this_head + PrefixInteger(this_foot,2)
        console.log(this_head)
        console.log(this_foot)
        console.log(this_bianhao)
        list.push({
          id:0,
          productId:'',
          image:'',
          guige:'',
          bianhao:this_bianhao,
          lingshouPrice:'',
          lingshouBili:'',
          pifaPrice:'',
          pifaBili:'',
          dakehuPrice:'',
          dakehuBili:'',
          caigouPrice:'',
          jinxiang:'',
          xiaoxiang:'',
          enable:'是',
        })
      }else{
        list.push({
          id:0,
          productId:'',
          image:'',
          guige:'',
          bianhao:list[0].bianhao + "-01",
          lingshouPrice:'',
          lingshouBili:'',
          pifaPrice:'',
          pifaBili:'',
          dakehuPrice:'',
          dakehuBili:'',
          caigouPrice:'',
          jinxiang:'',
          xiaoxiang:'',
          enable:'是',
        })
        console.log(list)
      }

      this.Product.body = list
    },

    saveGongYingShang(){
      var save_list = this.Product

      for(var i=0; i<save_list.body.length; i++){
        if(save_list.body[i].imgFileName != undefined && save_list.body[i].imgFileName != null && save_list.body[i].imgFileName != ""){
          save_list.body[i].image = "http://localhost:9088/ruilida/" + save_list.body[i].imgFileName
          var formData = new FormData();
          formData.append("file",save_list.body[i].imgFile)
          let url = "http://localhost:8102/file/upload"
          this.axios.post(url,formData).then(res => {
            console.log(res.msg)
          }).catch(() => {
            MessageUtil.error("网络异常");
          })
        }
      }


      let url = "http://localhost:8102/product/productAdd"
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
      for(var i=0; i<save_list.body.length; i++){
        if(save_list.body[i].imgFileName != undefined && save_list.body[i].imgFileName != null && save_list.body[i].imgFileName != ""){
          save_list.body[i].image = "http://localhost:9088/ruilida/" + save_list.body[i].imgFileName
          var formData = new FormData();
          formData.append("file",save_list.body[i].imgFile)
          let url = "http://localhost:8102/file/upload"
          this.axios.post(url,formData).then(res => {
            console.log(res.msg)
          }).catch(() => {
            MessageUtil.error("网络异常");
          })
        }
      }


      let url = "http://localhost:8102/product/productUpd"
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
        if(this.userPower.shangpinUpd != '是'){
          MessageUtil.error("无修改权限");
          return;
        }
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.userPower.shangpinDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否删除当前选中的信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8102/product/delProduct";
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

    uploadImg(index){
      this.p_index = index
      this.$refs.file.click();
    },

    fileSelect(){
      var file = document.getElementById("pic_file").files;
      var this_file = file[0];
      var fileName = file[0].name;
      console.log(fileName)
      console.log(this_file)
      let URL = window.URL || window.webkitURL;
      // 通过 file 生成目标 url
      let imgURL = URL.createObjectURL(this_file);
      console.log(imgURL)
      this.Product.body[this.p_index].image = imgURL
      this.Product.body[this.p_index].imgFile = file[0]
      this.Product.body[this.p_index].imgFileName = file[0].name
      console.log(this.Product.body[this.p_index])
    },

    deleteImg(index){
      this.Product.body[index].image = ""
      this.Product.body[index].imgFile = ""
      this.Product.body[index].imgFileName = ""
      console.log("新商品:")
      console.log(this.Product)
      console.log("老商品:")
      console.log(this.oldProduct)

    },

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.currentPage = 1
      console.log(`每页 ${val} 条`);
    },

  }
}

function PrefixInteger(num, n) {
  return (Array(n).join(0) + num).slice(-n);
}

</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
.product{
  width: 100%; height: 100%;
  object-fit: contain;
}
</style>