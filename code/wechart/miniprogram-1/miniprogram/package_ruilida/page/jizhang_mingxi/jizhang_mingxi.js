// package_ruilida/page/userInfo/userInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    xlShow2: false,
    cxShow: false,
    guige:'',
    type:'',
    list_check: [
      {
        name:'记账分类',
        columnName: "jizhang_fenlei",
        type: "text",
        width: "250rpx",
      },{
        name:'记账明细',
        columnName: 'jizhang_mingxi',
        type: "text",
        width: "250rpx",
      }
    ],
    all_result: ['记账分类', '记账明细'],
    result: ['记账分类', '记账明细'],
    gongneng_list:[
      {
        name:'查询'
      },{
        name:'导出Excel'
      }
    ],
    quanxuan_value: true,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var _this = this
    var userInfo = JSON.parse(options.userInfo)
    _this.setData({
      userInfo,
    })
  },

  onInput: function (e) {
    var _this = this
    let column = e.currentTarget.dataset.column
    _this.setData({
      [column]: e.detail.value
    })
  },

  sel1:function(){
    var _this = this
    _this.tableShow()
  },

  tableShow: function () {
    var _this = this
    var userInfo = _this.data.userInfo
    if(userInfo.power_mingxi.peizhi_sel != '是'){
      wx.showToast({
        title: '当前账号无权限',
        icon: 'none'
      })
      return;
    }
    var sql = "select * from jizhang_mingxi"
    
    console.log(sql)
    wx.cloud.callFunction({
      name: 'sqlserver_ruilida',
      data: {
        query: sql
      },
      success: res => {
        console.log(res)
        var list = res.result.recordsets[0]
        for(var i=0; i<list.length; i++){
          list[i].jizhang_mingxi2 = list[i].jizhang_mingxi.split("\n")
        }
        console.log(list)
        _this.setData({
          list: list,
          num: list.length,
        })
        console.log(list)
      },
      err: res => {
        console.log("错误!")
      },
      fail: res => {
        wx.showToast({
          title: '请求失败！',
          icon: 'none',
          duration: 3000
        })
        console.log("请求失败！")
      }
    })
  },

  click_view:function(e){
    var _this = this
    console.log(e.target.dataset.index)
    var index = e.target.dataset.index
    if(_this.data.list[index].jizhang_fenlei != '日常收支'){
      return;
    }
    var id = _this.data.list[index].id
    var userInfo = _this.data.userInfo
    if(userInfo.power_mingxi.peizhi_upd != '是'){
      wx.showToast({
        title: '当前账号无权限',
        icon: 'none'
      })
      return;
    }
    wx.navigateTo({
      url: '../jizhang_mingxiAdd/jizhang_mingxiAdd' + '?userInfo=' + JSON.stringify(_this.data.userInfo) + "&id=" + id,
    })
  },

  xiala_show: function (e) {
    var _this = this
    console.log('列名：', e.currentTarget.dataset.column)
    var column = e.currentTarget.dataset.column
    var list = _this.data[column + "_list"]
    _this.setData({
      list_xiala: list,
      click_column:column,
    })
    console.log(list)
    _this.setData({
      xlShow2: true
    })
  },

  select2: function (e) {
    var _this = this
    if (e.type == "select") {
      var new_val = e.detail.name
      var click_column = _this.data.click_column
      if(click_column == 'gongneng' && new_val == '导出Excel'){
        _this.setData({
          xlShow2: false,
          dayin_show: true,
        })
      }else if(click_column == 'gongneng' && new_val == '查询'){
        _this.setData({
          xlShow2: false,
          cxShow: true,
        })
      }else if(click_column == 'gongneng' && new_val == '刷新'){
        _this.setData({
          xlShow2: false,
          guige:'',
          type:'',
        })
        _this.sel1()
      }else{
        _this.setData({
          [click_column]: new_val,
          xlShow2: false,
        })
      }
    } else if (e.type == "close") {
      _this.setData({
        xlShow2:false,
      })
    }
  },
  qxShow:function(){
    var _this = this
    _this.setData({
      xlShow2: false,
      dayin_show: false,
      cxShow: false
    })
  },

  onChange(event) {
    var _this = this
    console.log('onChange')
    console.log(event)
    if(event.detail.length == _this.data.list_check.length){
      _this.setData({
        quanxuan_value: true,
      });
    }else{
      _this.setData({
        quanxuan_value: false,
      });
    }
    _this.setData({
      result: event.detail,
    });
  },

  toggle(event) {
    console.log('toggle')
    console.log(event)
    const { index } = event.currentTarget.dataset;
    const checkbox = this.selectComponent(`.checkboxes-${index}`);
    checkbox.toggle();
  },

  noop() {
    console.log('noop')
  },

  quanxuan:function(e){
    var _this = this
    console.log(e)
    var this_val = e.detail
    if(this_val == false){
      _this.setData({
        quanxuan_value: false,
        result:[]
      })
    }else{
      _this.setData({
        quanxuan_value: true,
        result:_this.data.all_result
      })
    }
  },

  toExcel:function(){
    var _this = this
    if(_this.data.result.length == 0){
      wx.showToast({
        title: '请选择导出信息',
        icon: 'none'
      })
      return;
    }
    var this_column = []
    var result = _this.data.result
    for(var j=0; j<_this.data.list_check.length; j++){
      for(var i=0; i<result.length; i++){
        if(_this.data.list_check[j].name == result[i]){
          this_column.push(_this.data.list_check[j])
          continue;
        }
      }
    }
    console.log(this_column)
    var list = _this.data.list;
    if(list.length == 0){
      wx.showToast({
        title: '无可导出数据，请查询后再试！',
        icon: 'none'
      })
      return;
    }
    _this.qxShow()
    wx.showLoading({
      title: '打开Excel中',
      mask : 'true'
    })
    console.log(list)
    var title_put = this_column
    console.log(title_put)
    var cloudList = {
      name : '产品规格',
      items : [],
      header : []
    }
    for(let i=0;i<title_put.length;i++){
      cloudList.header.push({
        item:title_put[i].name,
        type:title_put[i].type,
        width:parseInt(title_put[i].width.split("r")[0]),
        columnName:title_put[i].columnName
      })
    }

    cloudList.items = list
    console.log(cloudList)

    wx.cloud.callFunction({
      name:'getExcel',
      data:{
        list : cloudList
      },
      success: function(res){
        console.log("获取云储存id")
        var this_name = new Date().getTime() + ".xlsx"
        var fileId = res.result.fileID
        wx.cloud.downloadFile({
          fileID : res.result.fileID,
          success : res=> {
            console.log("获得临时路径",res.tempFilePath)
            delCloudFile(fileId)
            wx.openDocument({
              filePath: res.tempFilePath,
              fileType : 'xlsx',
              showMenu : true,
              success : res=> {
                wx.hideLoading({
                  success: (res) => {},
                })
                console.log("用户打开文件")
              }
            })
          }
        })
      },
      fail : res=> {
        console.log(res)
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    var _this = this
    _this.sel1()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})

function delCloudFile(fileId){
  var fileIds = [];
  fileIds.push(fileId);
  wx.cloud.deleteFile({
    fileList: fileIds,
    success: res => {
      console.log(res.fileList);
    },
    fail : console.error
  })
}