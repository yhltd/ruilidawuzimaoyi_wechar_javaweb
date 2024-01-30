// miniprogram/pages/login/login.js
// const requestUrl = require('../../config').requestUrl
const app = getApp();


var login = function(that,info) {
  var finduser, passwod, adminis, gongsi;
  var listAll = [];
  const db = wx.cloud.database();
  var gongsi = app.globalData.gongsi
  console.log(that.data.gongsi)
  //财务
  var system = that.data.system
  console.log(system)
  var sql = "select * from userInfo where username ='" + info.inputName + "' and password = '" + info.inputPwd + "'"
  wx.cloud.callFunction({
    name: 'sqlserver_ruilida',
    data:{
      query : sql
    },
    success : res =>{
      var list = res.result.recordset
      console.log(list)
      if(list.length == 0){
        wx.showToast({
          title: '用户名或密码错误',
          icon:'none',
        })
      }else{
        var user_list = list[0]
        if(that.data.jizhu_panduan){
          that.remember_user(info.inputName,info.inputPwd)
        }else{
          that.remove_user()
        }
        wx.navigateTo({
          url:'../../package_ruilida/page/shows/shows?userInfo='+JSON.stringify(user_list)
        })
      }
    },
    err: res => {
      console.log("错误!")
    },
    fail: res => {
      console.log(res)
      wx.showToast({
        title: '请求失败！',
        icon: 'none',
        duration: 3000
      })
      console.log("请求失败！")
    },
    complete: () => {
      that.setData({
        lock : true
      })
    }
  })
}



function getCompanyTime(that,info,sort_name){
  var date = new Date()
  var nowTime = date.getFullYear()+"/"+(parseInt(date.getMonth())+1)+"/"+date.getDate()
  var sql = "select CASE WHEN endtime < '"+nowTime+"' THEN 1 ELSE 0 END as endtime,CASE WHEN mark2<'"+nowTime+"' THEN 1 ELSE 0 END as mark2,mark1,isnull(mark3,'') as mark3 from control_soft_time where soft_name ='"+sort_name+"'"

  if(sort_name=='财务' || sort_name=='排产' || sort_name=='人事' || sort_name=='进销存' || sort_name=='分权' || sort_name=='门店' || sort_name=='教务'){
    sql += " and name = '"+that.data.gongsi+"'"
  }
  console.log(sql)
  wx.cloud.callFunction({
    name : 'sqlServer_system',
    data : {
      query : sql
    },
    success : res=> {
      var list = res.result.recordset
      var result = ""
      console.log(list)
      if(list[0].endtime == 1){
        result = "工具到期，请联系我公司续费"
      }else if(list[0].mark2 == 1){
        result = "服务器到期，请联系我公司续费"
      }
      if(list[0].mark3 != null && list[0].mark3 != undefined){
        list[0].mark3 = list[0].mark3.trim()
        if(list[0].mark3 != ""){
          list[0].mark3 = list[0].mark3.split(":")[1]
          list[0].mark3 = list[0].mark3.replace("(","")
          list[0].mark3 = list[0].mark3.replace(")","")
        }
        that.setData({
          userNum: list[0].mark3
        })
      }
      console.log(list[0].mark3)
      if(result==""){
        login(that,info)
      }else{
        wx.showModal({
          title : '提示',
          content : result,
          showCancel: false,
        })
        return;
      }
    }
  })
} 

Page({

  /**
   * 页面的初始数据
   */
  data: {
    lock : true,
    system : "选择系统",
    systemArray :[],
    pickerArray : [],
    input_text: "",
    name: '',
    pwd: '',
    gongsi: "选择公司",
    jizhu_panduan:false,
  },

  checkboxChange(e){
    var _this = this
    _this.setData({
      jizhu_panduan: !_this.data.jizhu_panduan
    })
    console.log(_this.data.jizhu_panduan)
  },

  remember_user(username,password){
    var _this = this
    wx.setStorage({
      key:"user",
      data:username
    })
    wx.setStorage({
      key:"pass",
      data:password
    })
  },

  remove_user(){
    wx.removeStorage({
      key: 'user',
      success (res) {
          console.log(res.errMsg)
      }
    })
    wx.removeStorage({
      key: 'pass',
      success (res) {
          console.log(res.errMsg)
      }
    })
  },
  
  bindNameInput: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  bindPwdInput: function(e) {
    this.setData({
      pwd: e.detail.value
    })
  },

  formLogin: function(e) {
    var _this = this
    login(this,e.detail.value)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(e) {
    
    var that = this;
    wx.getStorage({
      key: 'user',
      success (res) {
        console.log(res.data)
        that.setData({
          name:res.data,
          jizhu_panduan:true
        })
      }
    })
    wx.getStorage({
      key: 'pass',
      success (res) {
        console.log(res.data)
        that.setData({
          pwd:res.data,
          jizhu_panduan:true
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    wx.hideToast({
      success: (res) => {
        console.log("隐藏消息提示框")
      },
    })
    this.setData({
      lock : true
    })
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})