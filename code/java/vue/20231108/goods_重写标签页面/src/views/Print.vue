<template>
  <el-container style="height: 100%;padding-bottom: 5%" direction="vertical">

    <kr-print-designer
        ref="printDesigner"
        :temp-value="value"
        :widget-options="widgets"
        :lodop-license="licenseInfo"
        @save="handleSave"
    />

  </el-container>
</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      value:{
        title: 'demo',
        width: 750,
        height: 550,
        pageWidth: 750,
        pageHeight: 550,
        tempItems: [],
      },
      widgets:[],
      //销售报价
      widgets1:[
        {
          type: "braid-txt",
          isEdit: true,
          title: "自定义文本",
          value: "自定义文本",
          defaultValue: "自定义文本"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "报价单编号",
          value: "{报价单编号}",
          defaultValue: "报价单编号",
          name: "bianhao"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "日期",
          value: "{日期}",
          defaultValue: "日期",
          name: "riqi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "客户",
          value: "{客户}",
          defaultValue: "客户",
          name: "kehu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "业务员",
          value: "{业务员}",
          defaultValue: "业务员",
          name: "yewuyuan"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "店铺",
          value: "{店铺}",
          defaultValue: "店铺",
          name: "dianpu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "销项税率",
          value: "{销项税率}",
          defaultValue: "销项税率",
          name: "xiaoxiangShuilv"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "备注",
          value: "{备注}",
          defaultValue: "备注",
          name: "xiaoxiangShuilv"
        },
        {
          type: "braid-table",
          title: "商品明细",
          name: "details",
          value: "{details}",
          defaultValue: [
            {
              shangpinBianhao: "{商品编号}",
              shangpinMingcheng: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              baojiaDanjia: "{报价单价}",
              jiashuiXiaoji: "{价税小计}",
              jianyiBaojia: "{建议报价}",
              xuyongRiqi: "{需用日期}",
              baojiaFudong: "{报价浮动}",
              beizhu: "{备注}",
            },
            {
              shangpinBianhao: "{商品编号}",
              shangpinMingcheng: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              baojiaDanjia: "{报价单价}",
              jiashuiXiaoji: "{价税小计}",
              jianyiBaojia: "{建议报价}",
              xuyongRiqi: "{需用日期}",
              baojiaFudong: "{报价浮动}",
              beizhu: "{备注}",
            },
          ],
          columnsAttr: [
            {
              title: "商品编号",
              value: "{商品编号}",
              name: "shangpinBianhao"
            },
            {
              title: "商品名称",
              value: "{商品名称}",
              name: "shangpinMingcheng"
            },
            {
              title: "规格",
              value: "{规格}",
              name: "guige"
            },
            {
              title: "材质",
              value: "{材质}",
              name: "caizhi"
            },
            {
              title: "技术标准",
              value: "{技术标准}",
              name: "jishuBiaozhun"
            },
            {
              title: "质保等级",
              value: "{质保等级}",
              name: "zhibaoDengji"
            },
            {
              title: "单位",
              value: "{单位}",
              name: "danwei"
            },
            {
              title: "数量",
              value: "{数量}",
              name: "shuliang"
            },
            {
              title: "报价单价",
              value: "{报价单价}",
              name: "baojiaDanjia"
            },
            {
              title: "价税小计",
              value: "{价税小计}",
              name: "jiashuiXiaoji"
            },
            {
              title: "建议报价",
              value: "{建议报价}",
              name: "jianyiBaojia"
            },
            {
              title: "需用日期",
              value: "{需用日期}",
              name: "xuyongRiqi"
            },
            {
              title: "报价浮动",
              value: "{报价浮动}",
              name: "baojiaFudong"
            },
            {
              title: "备注",
              value: "{备注}",
              name: "beizhu"
            }
          ]
        }
      ],

      //销售订单
      widgets2:[
        {
          type: "braid-txt",
          isEdit: true,
          title: "自定义文本",
          value: "自定义文本",
          defaultValue: "自定义文本"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "订单编号",
          value: "{订单编号}",
          defaultValue: "订单编号",
          name: "bianhao"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "日期",
          value: "{日期}",
          defaultValue: "日期",
          name: "riqi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "客户",
          value: "{客户}",
          defaultValue: "客户",
          name: "kehu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "业务员",
          value: "{业务员}",
          defaultValue: "业务员",
          name: "yewuyuan"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "收件人姓名",
          value: "{收件人姓名}",
          defaultValue: "收件人姓名",
          name: "shoujianren"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "收件人手机",
          value: "{收件人手机}",
          defaultValue: "收件人手机",
          name: "shoujianPhone"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "收件人地址",
          value: "{收件人地址}",
          defaultValue: "收件人地址",
          name: "shoujianDizhi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "店铺",
          value: "{店铺}",
          defaultValue: "店铺",
          name: "dianpu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "销项税率",
          value: "{销项税率}",
          defaultValue: "销项税率",
          name: "xiaoxiangShuilv"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "备注",
          value: "{备注}",
          defaultValue: "备注",
          name: "beizhu"
        },
        {
          type: "braid-table",
          title: "商品明细",
          name: "details",
          value: "{details}",
          defaultValue: [
            {
              shangpinBianhao: "{商品编号}",
              shangpinMingcheng: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              baojiaDanjia: "{单价}",
              jiashuiXiaoji: "{价税小计}",
              jianyiBaojia: "{建议售价}",
              xuyongRiqi: "{需用日期}",
              baojiaFudong: "{价格浮动}",
              beizhu: "{备注}",
            },
            {
              shangpinBianhao: "{商品编号}",
              shangpinMingcheng: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              baojiaDanjia: "{单价}",
              jiashuiXiaoji: "{价税小计}",
              jianyiBaojia: "{建议售价}",
              xuyongRiqi: "{需用日期}",
              baojiaFudong: "{价格浮动}",
              beizhu: "{备注}",
            },
          ],
          columnsAttr: [
            {
              title: "商品编号",
              value: "{商品编号}",
              name: "shangpinBianhao"
            },
            {
              title: "商品名称",
              value: "{商品名称}",
              name: "shangpinMingcheng"
            },
            {
              title: "规格",
              value: "{规格}",
              name: "guige"
            },
            {
              title: "材质",
              value: "{材质}",
              name: "caizhi"
            },
            {
              title: "技术标准",
              value: "{技术标准}",
              name: "jishuBiaozhun"
            },
            {
              title: "质保等级",
              value: "{质保等级}",
              name: "zhibaoDengji"
            },
            {
              title: "单位",
              value: "{单位}",
              name: "danwei"
            },
            {
              title: "数量",
              value: "{数量}",
              name: "shuliang"
            },
            {
              title: "单价",
              value: "{单价}",
              name: "baojiaDanjia"
            },
            {
              title: "价税小计",
              value: "{价税小计}",
              name: "jiashuiXiaoji"
            },
            {
              title: "建议售价",
              value: "{建议售价}",
              name: "jianyiBaojia"
            },
            {
              title: "需用日期",
              value: "{需用日期}",
              name: "xuyongRiqi"
            },
            {
              title: "价格浮动",
              value: "{价格浮动}",
              name: "baojiaFudong"
            },
            {
              title: "备注",
              value: "{备注}",
              name: "beizhu"
            }
          ]
        }
      ],

      //销售出库
      widgets3:[
        {
          type: "braid-txt",
          isEdit: true,
          title: "自定义文本",
          value: "自定义文本",
          defaultValue: "自定义文本"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "编号",
          value: "{编号}",
          defaultValue: "编号",
          name: "bianhao"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "日期",
          value: "{日期}",
          defaultValue: "日期",
          name: "riqi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "客户",
          value: "{客户}",
          defaultValue: "客户",
          name: "kehu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "店铺",
          value: "{店铺}",
          defaultValue: "店铺",
          name: "dianpu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "仓库",
          value: "{仓库}",
          defaultValue: "仓库",
          name: "cangku"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "备注",
          value: "{备注}",
          defaultValue: "备注",
          name: "beizhu"
        },
        {
          type: "braid-table",
          title: "商品明细",
          name: "details",
          value: "{details}",
          defaultValue: [
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              xiaoshouDanjia: "{销售单价}",
              jiashuiXiaoji: "{价税小计}",
              beizhu: "{备注}",
            },
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              baojiaDanjia: "{报价单价}",
              jiashuiXiaoji: "{价税小计}",
              jianyiBaojia: "{建议报价}",
              xuyongRiqi: "{需用日期}",
              baojiaFudong: "{报价浮动}",
              beizhu: "{备注}",
            },
          ],
          columnsAttr: [
            {
              title: "商品编号",
              value: "{商品编号}",
              name: "shangpinBianma"
            },
            {
              title: "商品名称",
              value: "{商品名称}",
              name: "name"
            },
            {
              title: "规格",
              value: "{规格}",
              name: "guige"
            },
            {
              title: "材质",
              value: "{材质}",
              name: "caizhi"
            },
            {
              title: "技术标准",
              value: "{技术标准}",
              name: "jishuBiaozhun"
            },
            {
              title: "质保等级",
              value: "{质保等级}",
              name: "zhibaoDengji"
            },
            {
              title: "单位",
              value: "{单位}",
              name: "danwei"
            },
            {
              title: "数量",
              value: "{数量}",
              name: "shuliang"
            },
            {
              title: "销售单价",
              value: "{销售单价}",
              name: "xiaoshouDanjia"
            },
            {
              title: "价税小计",
              value: "{价税小计}",
              name: "jiashuiXiaoji"
            },
            {
              title: "备注",
              value: "{备注}",
              name: "beizhu"
            }
          ]
        }
      ],

      //采购订单
      widgets4:[
        {
          type: "braid-txt",
          isEdit: true,
          title: "自定义文本",
          value: "自定义文本",
          defaultValue: "自定义文本"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "编号",
          value: "{编号}",
          defaultValue: "编号",
          name: "bianhao"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "日期",
          value: "{日期}",
          defaultValue: "日期",
          name: "riqi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "供应商",
          value: "{供应商}",
          defaultValue: "供应商",
          name: "gongyingshang"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "店铺",
          value: "{店铺}",
          defaultValue: "店铺",
          name: "dianpu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "进项税率",
          value: "{进项税率}",
          defaultValue: "进项税率",
          name: "jinxiangShuilv"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "备注",
          value: "{备注}",
          defaultValue: "备注",
          name: "beizhu"
        },
        {
          type: "braid-table",
          title: "商品明细",
          name: "details",
          value: "{details}",
          defaultValue: [
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              lishiZuidi: "{历史最低价}",
              caigouDanjia: "{采购单价}",
              jiashuiXiaoji: "{价税小计}",
              jiaohuoRiqi: "{交货日期}",
              beizhu: "{备注}",
            },
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              lishiZuidi: "{历史最低价}",
              caigouDanjia: "{采购单价}",
              jiashuiXiaoji: "{价税小计}",
              jiaohuoRiqi: "{交货日期}",
              beizhu: "{备注}",
            },
          ],
          columnsAttr: [
            {
              title: "商品编号",
              value: "{商品编号}",
              name: "shangpinBianma"
            },
            {
              title: "商品名称",
              value: "{商品名称}",
              name: "name"
            },
            {
              title: "规格",
              value: "{规格}",
              name: "guige"
            },
            {
              title: "材质",
              value: "{材质}",
              name: "caizhi"
            },
            {
              title: "技术标准",
              value: "{技术标准}",
              name: "jishuBiaozhun"
            },
            {
              title: "质保等级",
              value: "{质保等级}",
              name: "zhibaoDengji"
            },
            {
              title: "单位",
              value: "{单位}",
              name: "danwei"
            },
            {
              title: "数量",
              value: "{数量}",
              name: "shuliang"
            },
            {
              title: "历史最低价",
              value: "{历史最低价}",
              name: "lishiZuidi"
            },
            {
              title: "采购单价",
              value: "{采购单价}",
              name: "caigouDanjia"
            },
            {
              title: "价税小计",
              value: "{价税小计}",
              name: "jiashuiXiaoji"
            },
            {
              title: "交货日期",
              value: "{交货日期}",
              name: "jiaohuoRiqi"
            },
            {
              title: "备注",
              value: "{备注}",
              name: "beizhu"
            }
          ]
        }
      ],

      //采购入库
      widgets5:[
        {
          type: "braid-txt",
          isEdit: true,
          title: "自定义文本",
          value: "自定义文本",
          defaultValue: "自定义文本"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "编号",
          value: "{编号}",
          defaultValue: "编号",
          name: "bianhao"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "日期",
          value: "{日期}",
          defaultValue: "日期",
          name: "riqi"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "供应商",
          value: "{供应商}",
          defaultValue: "供应商",
          name: "gongyingshang"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "店铺",
          value: "{店铺}",
          defaultValue: "店铺",
          name: "dianpu"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "仓库",
          value: "{仓库}",
          defaultValue: "仓库",
          name: "cangku"
        },
        {
          type: "braid-txt",
          isEdit: false,
          title: "备注",
          value: "{备注}",
          defaultValue: "备注",
          name: "beizhu"
        },
        {
          type: "braid-table",
          title: "商品明细",
          name: "details",
          value: "{details}",
          defaultValue: [
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              caigouDanjia: "{采购单价}",
              jiashuiXiaoji: "{价税小计}",
              beizhu: "{备注}",
            },
            {
              shangpinBianma: "{商品编号}",
              name: "{商品名称}",
              guige: "{规格}",
              caizhi: "{材质}",
              jishuBiaozhun: "{技术标准}",
              zhibaoDengji: "{质保等级}",
              danwei: "{单位}",
              shuliang: "{数量}",
              caigouDanjia: "{采购单价}",
              jiashuiXiaoji: "{价税小计}",
              beizhu: "{备注}",
            },
          ],
          columnsAttr: [
            {
              title: "商品编号",
              value: "{商品编号}",
              name: "shangpinBianma"
            },
            {
              title: "商品名称",
              value: "{商品名称}",
              name: "name"
            },
            {
              title: "规格",
              value: "{规格}",
              name: "guige"
            },
            {
              title: "材质",
              value: "{材质}",
              name: "caizhi"
            },
            {
              title: "技术标准",
              value: "{技术标准}",
              name: "jishuBiaozhun"
            },
            {
              title: "质保等级",
              value: "{质保等级}",
              name: "zhibaoDengji"
            },
            {
              title: "单位",
              value: "{单位}",
              name: "danwei"
            },
            {
              title: "数量",
              value: "{数量}",
              name: "shuliang"
            },
            {
              title: "采购单价",
              value: "{采购单价}",
              name: "caigouDanjia"
            },
            {
              title: "价税小计",
              value: "{价税小计}",
              name: "jiashuiXiaoji"
            },
            {
              title: "备注",
              value: "{备注}",
              name: "beizhu"
            }
          ]
        }
      ],

    }
  },
  created() {
    this.getPageType();
  },
  mounted(){

  },
  methods: {
    getPageType(){
      this.id = this.$route.query.id;
      this.type = this.$route.query.type;
      if(this.type == '销售报价单'){
        this.widgets = this.widgets1
      }else if(this.type == '销售订单'){
        this.widgets = this.widgets2
      }else if(this.type == '销售出库单'){
        this.widgets = this.widgets3
      }else if(this.type == '采购订单'){
        this.widgets = this.widgets4
      }else if(this.type == '采购入库单'){
        this.widgets = this.widgets5
      }
      console.log(this.id)
      console.log(this.type)
      let url = "http://user-20200618gm:8102/printMuBan/getMuBanById"
      this.axios.post(url, {"id":this.id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this.MuBan = this_val[0]
          console.log(res.data.data);
          console.log(this.MuBan)
          if(this.MuBan.value == ""){
            this.value = {
              title: 'demo',
              width: 750,
              height: 550,
              pageWidth: 750,
              pageHeight: 550,
              tempItems: [],
            }
          }else{
            this.value = JSON.parse(this.MuBan.value)
            console.log(this.value)
            this.$refs.printDesigner.initTemp(this.value,this.widgets)
          }
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    handleSave(e) {
      this.MuBan.value = JSON.stringify(e)
      let url = "http://user-20200618gm:8102/printMuBan/muBanUpd"
      this.axios.post(url, this.MuBan).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
  }
}

</script>