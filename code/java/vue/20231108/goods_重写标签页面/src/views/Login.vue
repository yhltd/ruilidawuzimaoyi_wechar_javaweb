<!-- 登录页面 -->
<template>
  <el-container class="custom-bg">
    <div class="custom-box">
      <div class="custom-header">
        <!-- 企业logo -->
        <div class="logo">进销存管理系统登录</div>
      </div>
      <div class="tab-box">
        <el-tabs v-model="activeName" style="border-bottom: none;">
<!--          <el-tab-pane label="账密登录" name="acc_pwd">-->
            <!-- ****** 所有在el-tab-pane下的字元素必须同时使用 custom-tab-pane 类选择器 ******        -->
            <el-form :model="ruleForm" ref="ruleForm" :rules="rules" label-width="60px"
                     class="demo-ruleForm custom-style custom-tab-pane">
              <el-form-item label="账号" prop="username" class="custom-form-item">
                <el-input ref="acc_inp" v-model="ruleForm.username" class="custom-login-inp" @keyup.enter.native="inpFocus('pwd_inp')"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password" class="custom-form-item">
                <el-input ref="pwd_inp" type="password" v-model="ruleForm.password" class="custom-login-inp"  @keyup.enter.native="(submitForm('ruleForm'))"></el-input>
              </el-form-item>
              <el-form-item>
                <!-- 添加loading组件防止重复点击 -->
                <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                           @click="submitForm('ruleForm')">登录
                </el-button>
              </el-form-item>
            </el-form>
<!--          </el-tab-pane>-->
<!--          <el-tab-pane label="手机号登录" name="">-->
<!--            <div class="custom-tab-pane"></div>-->
<!--          </el-tab-pane>-->
<!--          <el-tab-pane label="扫码登录" name="">-->
<!--            <div class="custom-tab-pane"></div>-->
<!--          </el-tab-pane>-->
        </el-tabs>
      </div>
      <!--        <div class="custom-footer">-->
      <!-- 保留 -->
      <!--        </div>-->
    </div>
    <!--      </el-col>-->
    <!--    </el-row>-->
  </el-container>
</template>

<script>
import axios from "axios";
import MessageUtil from "../utils/MessageUtil.js"
import router from "@/router";

export default {
  data() {
    return {
      login_btn_loading: false,
      activeName: 'acc_pwd',
      ruleForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
  },
  methods: {
    /**
     * 登录
     * @param formName
     */
    submitForm(formName) {
      this.login_btn_loading = true;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("ok")
          let URL = "http://yhocn.cn:8102/V0/login";
          axios.post(
              URL, this.ruleForm, false
          ).then(res => {
              console.log(res.data.code)
            switch (res.data.code) {
              case "00": {
                MessageUtil.success("登录成功");
                this.$store.commit('loginSuccess',JSON.stringify(res.data.data.user))
                this.$store.commit('powerSuccess',JSON.stringify(res.data.data.userPower))
                // window.sessionStorage.setItem("userInfo", JSON.stringify(res.data.data.user));
                // window.sessionStorage.setItem("userPower", JSON.stringify(res.data.data.userPower));
                this.$router.push("main");
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
            };
            this.login_btn_loading = false;
          }).catch(() => {
            MessageUtil.error("网络异常");
            // this.login_btn_loading = false;
          }).finally(() => {
            this.login_btn_loading = false;
          })
        } else {
          MessageUtil.error('账号或密码格式错误');
          this.login_btn_loading = false;
          this.inpFocus("acc_inp");
        }
      })
    },
    inpBlur(refName) {
      this.$refs[refName].blur();
    },
    inpFocus(refName) {
      this.$refs[refName].focus();
    }
  }
}

</script>

<!-- 覆盖原有element-ui样式-->
<style scoped>

::v-deep .custom-login-inp input {
  border: none;
  outline: none;
}

::v-deep .el-tabs__item {
  width: 110px;
  text-align: center;
}

::v-deep .el-tabs__nav-wrap::after {
  display: none;
}

::v-deep div.el-tabs__active-bar.is-top {
  max-width: 20px;
  left: 33px;
}

::v-deep .el-form-item:last-of-type {
  width: 100%;
  height: 40px;
  position: relative;
}
</style>

<style scoped>
body {
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.custom-bg {
  height: 100vh;
  width: 100vw;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("../assets/login-bg.png") no-repeat;
  background-size: 100% 100%;
  overflow: hidden;
}

.custom-box {
  width: 400px;
  /*height: 450px;*/
  height: fit-content;
  background-color: rgba(256, 256, 256, 1);
  border-radius: 10px;
  overflow: hidden;
}

.custom-header {
  height: 60px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid #000;
}

.logo {
  width: fit-content;
  height: fit-content;
}

.tab-box {
  height: 200px;
  display: flex;
  justify-content: center;
  padding-top: 30px;
}

.custom-tab-pane {
  width: 300px;
  min-height: 200px;
  height: fit-content;
  margin: 0 auto;
}

.custom-form-item {
  border-bottom: 2px solid #99CCFF;
}

.custom-login-button {
  position: absolute;
  width: 150px;
  left: 20px;
}
</style>