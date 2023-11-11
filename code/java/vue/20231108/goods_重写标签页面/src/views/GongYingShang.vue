<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-input placeholder="编号" v-model="bianhao" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input placeholder="名称" v-model="name" class="input-with-select">
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
          prop="bianhao"
          label="编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="name"
          label="名称"
          width="120">
      </el-table-column>
      <el-table-column
          prop="gongyingshangDengji"
          label="等级"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="suozaiDiqu"
          label="所在地区"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="dizhi"
          label="地址"
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
          prop="caigouyuan"
          label="采购员"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="jianzuoKehu"
          label="兼做客户"
          width="200"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="zhuyaoYewu"
          label="主要业务"
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

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        供应商基本信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">供应商基本信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="名称" prop="name" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.name" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="等级" prop="gongyingshangDengji" class="custom-form-item">
              <el-select v-model="gongYingShang.gongyingshangDengji" clearable filterable placeholder="请选择等级">
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
            <el-form-item label="地区" prop="suozaiDiqu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.suozaiDiqu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="地址" prop="dizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.dizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="采购员" prop="caigouyuan" class="custom-form-item">
              <el-select v-model="gongYingShang.caigouyuan" clearable filterable placeholder="请选择采购员">
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
            <el-form-item label="兼做客户" prop="jianzuoKehu" class="custom-form-item">
              <el-select v-model="gongYingShang.jianzuoKehu" clearable filterable placeholder="请选择兼做客户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_JianZuoKeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jianzuoKehu" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="主要业务" prop="zhuyaoYewu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.zhuyaoYewu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!--        收件信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">收件人信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">

          <el-col :span="6">
            <el-form-item label="收件人姓名" prop="shoujianName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianName" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人电话" prop="shoujianPhone" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianPhone" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人地址" prop="shoujianDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!--        开票信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">开票信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="单位名称" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDanwei" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="纳税人识别号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shibiehao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开票地址" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDizhi" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoDianhua" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开户银行" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoYinhang" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="银行账号" prop="userName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kaipiaoZhanghao" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <div v-for="(item, index) in gongYingShang.body">
          <!--        联系人信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">联系人信息{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="姓名" prop="name" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].name" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="部门" prop="department" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].department" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="职务" prop="zhiwu" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhiwu" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="电话" prop="phone" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].phone" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址" prop="address" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].address" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="类型" prop="type" class="custom-form-item">
                <el-select :index="index" @change="selectLeiXing(index,value)" v-model="gongYingShang.body[index].type" clearable filterable placeholder="请选择联系人类型">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_LianXiRenLeiXing"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].type" class="custom-login-inp"></el-input>-->
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
                           @click="addLianXiRen">添加联系人
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
      bianhao:'',
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
      gongYingShang: {
        id: 0,
        bianhao:'123123',
        name: '',
        gongyingshangDengji: '',
        suozaiDiqu: '',
        dizhi: '',
        beizhu: '',
        caigouyuan:'',
        jianzuoKehu:'',
        zhuyaoYewu:'',
        shoujianName:'',
        shoujianPhone:'',
        shoujian_dizhi:'',
        kaipiaoDanwei:'',
        shibiehao:'',
        kaipiaoDizhi:'',
        kaipiaoDianhua:'',
        kaipiaoYinhang:'',
        kaipiaoZhanghao:'',
        body:[
          {
            id:0,
            name:'',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            gongyingshangId:'',
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
      this.gongYingShang = {
          bianhao:'',
          name: '',
          gongyingshangDengji: '',
          suozaiDiqu: '',
          dizhi: '',
          beizhu: '',
          caigouyuan:'',
          jianzuoKehu:'',
          zhuyaoYewu:'',
          shoujianName:'',
          shoujianPhone:'',
          shoujian_dizhi:'',
          kaipiaoDanwei:'',
          shibiehao:'',
          kaipiaoDizhi:'',
          kaipiaoDianhua:'',
          kaipiaoYinhang:'',
          kaipiaoZhanghao:'',
        body:[
          {
            name:'1',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            gongyingshangId:'',
          },
          {
            name:'2',
            department:'',
            zhiwu:'',
            phone:'',
            address:'',
            type:'',
            gongyingshangId:'',
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

      let url = "http://localhost:8081/gongYingShang/selectById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.itemList
          this.gongYingShang = this_val
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
      let url = "http://localhost:8081/gongYingShang/getAll"
      this.axios(url, this.form).then(res => {
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

    //刷新
    refresh(){
      this.bianhao = ""
      this.name = ""
      this.getAll()
    },

    //条件查询
    query(){
      var date = {
        bianhao:this.bianhao,
        name:this.name
      }
      let url = "http://localhost:8081/gongYingShang/queryList"
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
        this.gongYingShang.body.splice(index, 1)
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

    addLianXiRen(){
      var this_lianxiren = this.gongYingShang.body
      this_lianxiren.push({
        id:0,
        name:'',
        department:'',
        zhiwu:'',
        phone:'',
        address:'',
        type:'',
        gongyingshangId:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    selectLeiXing(index,value){
      console.log(index)
      console.log(value)
      console.log(this.gongYingShang.body)
      if(this.gongYingShang.body[index].type == '主联系人'){
        for(var i=0; i<this.gongYingShang.body.length; i++){
          if(i != index){
            this.gongYingShang.body[i].type = "联系人"
          }
        }
      }
    },

    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/gongYingShang/gongyingshangAdd"
      this.axios.post(url, {
        "head":this.gongYingShang,
        "body":this.gongYingShang.body
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
      var save_list = this.gongYingShang
      let url = "http://localhost:8081/gongYingShang/gongyingshangUpd"
      this.axios.post(url, {
        "head":this.gongYingShang,
        "body":this.gongYingShang.body
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
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
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
        let url = "http://localhost:8081/gongYingShang/delGongYingShang";
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