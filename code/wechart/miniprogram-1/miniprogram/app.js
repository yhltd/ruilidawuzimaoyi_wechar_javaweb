//app.js
App({
  globalData: {
    //瑞利达小程序，批量选择商品存储变量
    ruili_pro:[],
  },

  onLaunch: function () {
    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力')
    } else {
      wx.cloud.init({
        env:'my-env-id',
        env:'yhltd-hsxl2',
        traceUser: true,
      })
    }
  }
})

