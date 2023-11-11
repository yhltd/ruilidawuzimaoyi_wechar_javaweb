<template>
  <el-container>
    <el-main>
      <el-form :model="userInfo" ref="usrInfo" :rules="rules" label-width="100px"
               class="demo-info">
        <el-form-item label="账号" prop="userName" class="custom-form-item">
          <el-input :disabled="true" ref="acc_inp" v-model="userInfo.userName" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" class="custom-form-item">
          <el-input :disabled="!show_upd_btn" ref="pwd_inp" type="password" v-model="userInfo.password"
                    class="custom-login-inp"
                    @keyup.enter.native="(submitForm('usrInfo'))"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="name" class="custom-form-item">
          <el-input :disabled="!show_upd_btn" ref="name" v-model="userInfo.name" class="custom-login-inp"
                    @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="userName" class="custom-form-item">
          <el-select :disabled="!show_upd_btn" v-model="userInfo.power" placeholder="请选择">
            <el-option
                v-for="item in powers"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审批" prop="userName" class="custom-form-item">
          <el-select :disabled="!show_upd_btn" v-model="userInfo.shenpi" placeholder="请选择">
            <el-option
                v-for="item in shenpis"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="仓库" prop="userName" class="custom-form-item">
          <el-select :disabled="!show_upd_btn" v-model="userInfo.cangku" placeholder="请选择">
            <el-option
                v-for="item in cangkus"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="店铺" prop="userName" class="custom-form-item">
          <el-select :disabled="!show_upd_btn" v-model="userInfo.dianpus" placeholder="请选择">
            <el-option
                v-for="item in dianpus"
                :key="item.id"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <!-- 添加loading组件防止重复点击 -->
          <el-button v-show="show_upd_btn" class="custom-login-button" :loading="login_btn_loading" type="primary"
                     @click="submitForm('usrInfo')">提交修改
          </el-button>
          <el-button v-show="!show_upd_btn" @click="changeBtnHid(); queryAllSelectInfo()">修改</el-button>
          <el-button v-show="show_upd_btn" @click="queryUsrInfoFromSession()">重置</el-button>
          <el-button v-show="show_upd_btn" @click="changeBtnHid()">取消</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";

export default {
  data() {
    return {
      show_upd_btn: false,
      login_btn_loading: false,
      userInfo: {
        id: 1,
        name: '',
        userName: '',
        password: '',
        power: '',
        shenpi: '',
        cangku: '',
        dianpu: '',
        hesuanDanwei: '',
      },
      powers: [],
      shenpis: [],
      cangkus: [],
      dianpus: [],
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: false, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    /**
     *
     * @param formName
     */
    submitForm(formName) {
      this.login_btn_loading = true;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let URL = "http://localhost:8081/user/updUsrInfo";
          axios.post(
              URL, this.ruleForm, false
          ).then(res => {
            switch (res.data.code) {
              case "00": {
                MessageUtil.success("修改成功");
                window.sessionStorage.setItem("userInfo", JSON.stringify(res.data.data.user));
                window.sessionStorage.setItem("userPower", JSON.stringify(res.data.data.userPower));
                break;
              }
              case "06": {
                MessageUtil.error("账号或密码错误");
                break;
              }
              case "10": {
                MessageUtil.error("用户无访问权限");
                break;
              }
            }
            this.login_btn_loading = false;
          })
        } else {
          MessageUtil.error('账号或密码格式错误');
          this.login_btn_loading = false;
          this.inpFocus("acc_inp");
        }
      })
    },
    /**
     * 查询同步用户信息
     */
    async queryUserInfo() {
      let URL = "http://localhost:8081/user/queryUserInfo";
      axios.post(
          URL, {"id": this.userInfo.id}, true
      ).then(res => {
        console.log(res.data)
        switch (res.data.code) {
          case "00": {
            MessageUtil.success("修改成功");
            window.sessionStorage.setItem("userInfo", JSON.stringify(res.data.data.user));
            break;
          }
          case "03": {
            MessageUtil.error("更新失败");
            break;
          }
          default: {
            MessageUtil.error("未知错误")
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    async queryAllSelectInfo() {
      this.queryCangKus().catch(()=> {
        MessageUtil.error("网络异常")
      });
      this.queryPowers().catch(()=> {
        MessageUtil.error("网络异d常")
      })
      this.queryDianPus().catch(()=> {
        MessageUtil.error("网络异常")
      });
      this.queryShenPis().catch(()=> {
        MessageUtil.error("网络异常")
      });
    },
    async queryPowers() {
      let url = "http://localhost:8081/power/getUserPowerIdAndName";
      axios.post(url).then(res => {
        switch (res.data.code) {
          case '00':
            this.powers = res.data.data;
            break;
          default: {
            MessageUtil.error("获取权限失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    async queryDianPus() {
    },
    async queryCangKus() {
    },
    async queryShenPis() {
      let url = "http://localhost:8081/user/queryUsers";
      axios.post(url).then(res => {
        switch (res.data.code) {
          case '00':
            this.shenpis = res.data.data;
            break;
          default: {
            MessageUtil.error("获取审批人失败");
          }
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    inpBlur(refName) {
      this.$refs[refName].blur();
    },
    inpFocus(refName) {
      this.$refs[refName].focus();
    },
    changeBtnHid() {
      this.show_upd_btn = !this.show_upd_btn;
    },
    queryUsrInfoFromSession() {
      let usrInfo = window.sessionStorage.getItem("userInfo");
      if (usrInfo === 'undefined' || usrInfo == null) {
        // 如果session中不含有本数据，则让用户重新登陆
        MessageUtil.warning("登录过期，重新登陆");
        this.$router.push("/");
        return;
      } else {
        this.userInfo = JSON.parse(usrInfo);
      }
      console.log(this.userInfo.userName);
    }
  },
  created() {
    this.queryUsrInfoFromSession();
  }
}
</script>

<style scoped>
.demo-info {
  width: 400px;
  margin: 0 auto;
}
</style>