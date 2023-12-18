<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-input placeholder="模板名称" v-model="name" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button  size="small" round type="primary" @click="addUser()">添加</el-button>
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
            label="模板名称"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="type"
            label="模板类型"
            width="auto">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="auto">
          <template slot-scope="scope">
            <el-button @click="diyMuBan(scope.row)" type="text" size="small">编辑模板</el-button>
          </template>
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

    <el-dialog title="" :visible.sync="addDialog" width="50%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <el-form-item label="模板名称" prop="name" class="custom-form-item">
          <el-input ref="acc_inp"  v-model="gongYingShang.name" class="custom-login-inp"></el-input>
        </el-form-item>

        <el-form-item v-if="gongYingShang.id == undefined || gongYingShang.id == null || gongYingShang.id == ''" label="模板类型" prop="type" class="custom-form-item">
          <el-select style="z-index:999;" v-model="gongYingShang.type" clearable filterable placeholder="请选择模板类型">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_Type"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
          <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
        </el-form-item>


        <el-row :gutter="15">
          <el-col :span="4">
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
              <el-button class="custom-login-button" type="primary"
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
import RouterUtil from "@/utils/RouterU";
export default {
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      name:'',
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      XiaLa_Type:[
        {
          name:'销售报价单',
          label:'销售报价单'
        },
        {
          name:'销售订单',
          label:'销售订单'
        },
        {
          name:'销售出库单',
          label:'销售出库单'
        },
        {
          name:'采购订单',
          label:'采购订单'
        },
        {
          name:'采购入库单',
          label:'采购入库单'
        }
      ],
      gongYingShang: {
        name:'',
        type: '',
        value: '',
      },
      addDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.getXiaLa_User();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_HeSuanDanWei();
    this.getXiaLa_CangKu();
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
        name:'',
        type: '',
        value: '',
      }
      this.addDialog = true;
    },
    //新增窗口关闭
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

      let url = "http://yhocn.cn:8102/printMuBan/getMuBanById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this.gongYingShang = this_val[0]
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
    //读取账号信息（未实现）
    getUser(){
      this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
      this.userPower = JSON.parse(window.localStorage.getItem('userPower'))
      console.log(this.userInfo)
      console.log(this.userPower)
      let url = "http://yhocn.cn:8102/user/queryUserInfoById"
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
      let poweruUrl = "http://yhocn.cn:8102/userpower/getUserPowerByName"
      this.axios.post(poweruUrl,{"name":this.userInfo.power}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          this.userPower = res.data.data
          this.getAll();
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
      let url = "http://yhocn.cn:8102/printMuBan/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
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
      this.getAll()
    },

    query(){
      this.queryList();
    },

    //条件查询
    queryList(){
      var date = {
        name:this.name,
      }
      let url = "http://yhocn.cn:8102/printMuBan/queryList"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    //保存
    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://yhocn.cn:8102/printMuBan/muBanAdd"
      this.axios.post(url, save_list).then(res => {
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
    //修改
    updGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://yhocn.cn:8102/printMuBan/muBanUpd"
      this.axios.post(url, save_list).then(res => {
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
    //根据对象中有无id执行保存或修改
    save(){
      if(this.gongYingShang.name == ''){
        MessageUtil.error("请填写模板名称");
        return;
      }
      if(this.gongYingShang.type == ''){
        MessageUtil.error("请选择模板类型");
        return;
      }
      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },
    //删除选中
    deleteClick(){
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
        let url = "http://yhocn.cn:8102/printMuBan/delById";
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

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.currentPage = 1
      console.log(`每页 ${val} 条`);
    },

    diyMuBan(row){
      console.log(row)
      RouterUtil.to( '/print?id=' + row.id + '&type=' + row.type);
    }

  }
}

function PrefixInteger(num, n) {
  return (Array(n).join(0) + num).slice(-n);
}

function getNowDate() {
  var date = new Date();
  var sign1 = "-";
  var sign2 = ":";
  var year = date.getFullYear() // 年
  var month = date.getMonth() + 1; // 月
  var day  = date.getDate(); // 日
  var hour = date.getHours(); // 时
  var minutes = date.getMinutes(); // 分
  var seconds = date.getSeconds() //秒
  var weekArr = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'];
  var week = weekArr[date.getDay()];
  // 给一位数数据前面加 “0”
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (day >= 0 && day <= 9) {
    day = "0" + day;
  }
  if (hour >= 0 && hour <= 9) {
    hour = "0" + hour;
  }
  if (minutes >= 0 && minutes <= 9) {
    minutes = "0" + minutes;
  }
  if (seconds >= 0 && seconds <= 9) {
    seconds = "0" + seconds;
  }
  // var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds + " " + week;
  var currentdate = year + sign1 + month + sign1 + day ;
  return currentdate;
}
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
.el-table .hidden-row {
  display: none;
}

</style>