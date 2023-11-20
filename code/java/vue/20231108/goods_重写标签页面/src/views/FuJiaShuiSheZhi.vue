<template>
  <el-container direction="vertical">

    <el-form :model="ZhuanZhang" ref="addUsr" label-width="150px"
             class="demo-info">

      <el-form-item label="税率" prop="shuilv" class="custom-form-item">
        <el-input v-model="ZhuanZhang.shuilv" class="custom-login-inp" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item label="是否启用" prop="zhuangtai" class="custom-form-item" >
        <el-select v-model="ZhuanZhang.zhuangtai" clearable filterable placeholder="请选择" style="width: 300px">
          <!-- types 为后端查询 -->
          <el-option
              v-for="item in XiaLa_ZhuangTai"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
        <!--          <el-input ref="acc_inp" v-model="ZhuanZhang.zhuanru" class="custom-login-inp" ></el-input>-->
      </el-form-item>

      <el-form-item label="零售上浮比例" prop="lingshou" class="custom-form-item">
        <el-input v-model="ZhuanZhang.lingshou" class="custom-login-inp" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item label="批发上浮比例" prop="pifa" class="custom-form-item">
        <el-input v-model="ZhuanZhang.pifa" class="custom-login-inp" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item label="大客户上浮比例" prop="dakehu" class="custom-form-item">
        <el-input v-model="ZhuanZhang.dakehu" class="custom-login-inp" style="width: 300px"></el-input>
      </el-form-item>


      <el-row :gutter="15">
        <el-col :span="4">
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
                       @click="refresh()">刷新
            </el-button>
          </div>
        </el-col>
      </el-row>



    </el-form>

  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      start_date:'',
      stop_date:'',
      zhuanru:'',
      zhuanchu:'',
      XiaLa_ZhuangTai:[
        {
          name:'是',
          label:'是',
        },
        {
          name:'否',
          label:'否',
        }
      ],
      XiaLa_ZhangHu:[],
      ZhuanZhang: {
        money:'',
        riqi:'',
        zhuanru:'',
        zhuanchu:'',
        zhaiyao:'',

      },
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
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
          if(this.userPower.zhuanzhangSel == '是'){
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

    //查询全部
    getAll(){
      if(this.userPower.fujiashuiSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://localhost:8102/peiZhiShuiLv/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.ZhuanZhang = res.data.data[0];
          MessageUtil.success("已刷新")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //刷新
    refresh(){
      this.getAll()
    },

    query(){
      this.shenheButton = false
      if(this.userPower.zhuanzhangSel == '是'){
        this.queryList();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    updGongYingShang(){
      var save_list = this.ZhuanZhang
      let url = "http://localhost:8102/peiZhiShuiLv/update"
      this.axios.post(url, save_list).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.addDialog = false;
          this.getAll()
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    save(){
      if(this.userPower.fujiashuiUpd != '是'){
        MessageUtil.error("无修改权限");
        return;
      }
      this.updGongYingShang()
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