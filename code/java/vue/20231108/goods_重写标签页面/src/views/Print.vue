<template>
  <div id="app">
    <kr-print-designer
        style="margin-bottom: 5%"
        :temp-value="value"
        :widget-options="widgets"
        :lodop-license="licenseInfo"
        @save="handleSave"
    />
  </div>
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
      licenseInfo: {
        strCompanyName: 'xxx有限公司',
        strLicense: 'ab123456789',
        strLicenseA: 'bb123456',
        strLicenseB: 'cc123456'
      },
      widgets:[
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
              damwei: "{单位}",
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
              damwei: "{单位}",
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
              name: "damwei"
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
      console.log(this.id)
      console.log(this.type)
      let url = "http://localhost:8081/printMuBan/getMuBanById"
      this.axios.sync.post(url, {"id":this.id}).then(res => {
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

            // this.$refs.printDesigner.initTemp(this.value,this.widgets)
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
      console.log(e)
      console.log(JSON.stringify(e))
    },
  }
}

</script>