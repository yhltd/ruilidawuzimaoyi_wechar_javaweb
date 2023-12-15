<template>
  <el-container direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-date-picker
              style="width:100%"
              v-model="start_date"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择起始日期">
          </el-date-picker>
        </el-col>
        <el-col :span="3">
          <el-date-picker
              style="width:100%"
              v-model="stop_date"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择结束日期">
          </el-date-picker>
        </el-col>
        <el-col :span="3">
          <el-select v-model="kehu" clearable filterable placeholder="请选择客户">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_KeHu"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="shenhe" clearable filterable placeholder="请选择审核状态">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_ShenHeZhuangTai"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" round size="small" type="primary" @click="addUser()">添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" round size="small" type="primary" @click="updUser()">编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="myShenHe()">需要我审核</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="shenheButton" round size="small" type="primary" @click="shenheClick()">审核</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" round size="small" type="danger" @click="deleteClick()">删除</el-button>
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
            prop="bianhao"
            label="编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="riqi"
            label="日期"
            width="120">
        </el-table-column>
        <el-table-column
            prop="kehu"
            label="客户"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="yewuyuan"
            label="业务员"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianren"
            label="收件人姓名"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianPhone"
            label="收件人手机"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoujianDizhi"
            label="收件人地址"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="dianpu"
            label="店铺"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="xiaoxiangShuilv"
            label="销项税率"
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
            prop="jiageDengji"
            label="价格等级"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shenhe"
            label="审核人"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shenheZhuangtai"
            label="审核状态"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="chukuZhuangtai"
            label="出库状态"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="kaipiaoZhuangtai"
            label="开票状态"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="shoukuanZhuangtai"
            label="收款状态"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="220">//修改了宽度
          <template slot-scope="scope">
            <el-button @click="getfileList(scope.row)" type="text" size="small"><i class="el-icon-folder-opened"></i>查看文件</el-button>
            <el-button @click="printShow(scope.row)" type="text" size="small"><i class="el-icon-printer"></i>打印</el-button>
            <!--            增加查看详情的按钮-->
            <el-button @click="seeList(scope.row)" type="text" size="small"><i class="el-icon-tickets"></i>查看详情</el-button>
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

    <el-dialog title="" :visible.sync="addDialog" width="80%">

      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        采购单信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售单信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="订单编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单日期" prop="riqi" class="custom-form-item">
              <el-date-picker
                  style="width:100%"
                  v-model="gongYingShang.riqi"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="客户" prop="kehu" class="custom-form-item" :rules="[{required:true,message:'客户不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select v-model="gongYingShang.kehu" @change="changeKeHu()" clearable filterable placeholder="请选择客户">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_KeHu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价格等级" prop="jiageDengji" class="custom-form-item" :rules="[{required:true,message:'价格等级不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" disabled="true" v-model="gongYingShang.jiageDengji" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item" :rules="[{required:true,message:'业务员不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select v-model="gongYingShang.yewuyuan" clearable filterable placeholder="请选择业务员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShenHe"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人姓名" prop="shoujianren" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianren" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人手机" prop="shoujianPhone" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianPhone" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="收件人地址" prop="shoujianDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianDizhi" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10"><!-- ztt 修改宽度-->
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item" :rules="[{required:true,message:'店铺不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select v-model="gongYingShang.dianpu" clearable filterable placeholder="请选择店铺">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_DianPu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--            ztt  快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '店铺')">添加</el-button>
              <!--            ztt end-->
<!--              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="销项税率" prop="xiaoxiangShuilv" class="custom-form-item" :rules="[{required:true,message:'销项税率不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoxiangShuilv" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价税合计" prop="jiashuiHeji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.jiashuiHeji" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
<!--ztt 审核-->
          <el-col :span="6">
            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shenhe" class="custom-login-inp" @click.native="seeShenHeRen"></el-input>
            </el-form-item>
          </el-col>
<!--          ztt end-->
<!--          <el-col :span="6">-->
<!--            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">-->
<!--              <el-select v-model="gongYingShang.shenhe" clearable filterable placeholder="请选择审核人">-->
<!--                &lt;!&ndash; types 为后端查询 &ndash;&gt;-->
<!--                <el-option-->
<!--                    v-for="item in XiaLa_ShenHe"-->
<!--                    :key="item.name"-->
<!--                    :label="item.name"-->
<!--                    :value="item.name">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--              &lt;!&ndash;              <el-input ref="acc_inp" v-model="gongYingShang.caigouyuan" class="custom-login-inp"></el-input>&ndash;&gt;-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <!--          ztt增加销售单位-->
          <el-col :span="6">
            <el-form-item label="销售单位" prop="xiaoshoudanwei" class="custom-form-item" :rules="[{required:true,message:'销售单位不能为空',trigger: 'blur'}]"><!-- 设置必填字段 -->
              <el-select v-model="gongYingShang.xiaoshouDanwei" clearable filterable placeholder="请选择销售单位">
                <el-option
                    v-for="item in XiaLa_HeSuanDanWei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <!--            ztt end-->

        </el-row>


        <div v-for="(item, index) in gongYingShang.body">
          <!--        商品信息-->
          <el-row :gutter="15">
            <el-col :span="6">
              <p class="dialog-title">商品信息{{index + 1}}</p>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="商品编码" prop="shangpinBianhao" class="custom-form-item" >
                <el-input ref="acc_inp" readonly="true" @click.native="selectProduct(index)" v-model="gongYingShang.body[index].shangpinBianhao" class="custom-login-inp" placeholder="点击选择商品"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="名称" prop="shangpinMingcheng" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].shangpinMingcheng" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="规格" prop="guige" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].guige" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="材质" prop="caizhi" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].caizhi" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="技术标准" prop="jishuBiaozhun" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jishuBiaozhun" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10"><!-- ztt 修改宽度-->
              <el-form-item label="质保等级" prop="zhibaoDengji" class="custom-form-item">
                <el-select v-model="gongYingShang.body[index].zhibaoDengji" clearable filterable placeholder="请选择质保等级">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_ZhiBaoDengJi"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--         ztt     快速跳转到配置项页-->
                <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                           @click="goToPeiZhi('/basePeizhi', '质保等级')">添加</el-button>
                <!--            ztt end-->
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhibaoDengji" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="单位" prop="danwei" class="custom-form-item" :rules="[{required:true,message:'单位不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].danwei" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="数量" prop="shuliang" class="custom-form-item" :rules="[{required:true,message:'数量不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" @change="changeValue(index,'shuliang')" v-model="gongYingShang.body[index].shuliang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="报价单价" prop="baojiaDanjia" class="custom-form-item" :rules="[{required:true,message:'报价单价不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" @change="changeValue(index,'baojia_danjia')" v-model="gongYingShang.body[index].baojiaDanjia" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="价税小计" prop="jiashuiXiaoji" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jiashuiXiaoji" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="建议报价" prop="jianyiBaojia" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jianyiBaojia" class="custom-login-inp" disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="需用日期" prop="xuyongRiqi" class="custom-form-item">
                <el-date-picker
                    style="width:100%"
                    v-model="gongYingShang.body[index].xuyongRiqi"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择需用日期">
                </el-date-picker>
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jiaohuoRiqi" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="报价浮动" prop="baojiaFudong" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index,'baojia_fudong')" v-model="gongYingShang.body[index].baojiaFudong" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="备注" prop="beizhu" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].beizhu" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-button v-if="index > 0" class="custom-login-button"  type="primary"
                       @click="delLianXiRen(index)">删除
            </el-button>
          </el-row>
        </div>

        <el-row :gutter="15">
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addLianXiRen">添加商品
              </el-button>
            </div>
          </el-col>
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save">保存
              </el-button>
            </div>
          </el-col>
          <!--ztt 审核-->
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="saveAndShenHe" style="margin-left: 30px">保存并提交审核
              </el-button>
            </div>
          </el-col>
          <!--          ztt end-->
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="addClose">取消
              </el-button>
            </div>
          </el-col>
        </el-row>



      </el-form>
    </el-dialog>

    <el-dialog title="" :visible.sync="selProduct" width="80%">

      <el-row :gutter="15">
        <el-col :span="5">
          <el-input placeholder="商品分类" v-model="Protype" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="商品名称" v-model="Proname" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProQuery()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="ProRefresh()">刷新</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          :data="CaiGou_Product" :row-class-name="rowClassName" @row-click="rowClick" style="width: 100%">
        <el-table-column
            prop="bianhao"
            label="编号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="name"
            label="名称"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="type"
            label="类别"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="danwei"
            label="单位"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="caizhi"
            label="材质"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jishuBiaozhun"
            label="技术标准"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="zhibaoDengji"
            label="质保等级"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="caigouPrice"
            label="采购价格"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jinxiang"
            label="进项税率"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="zuigaojia"
            label="历史最高价"
            width="180"
        ></el-table-column>
      </el-table>

    </el-dialog>

    <el-dialog title="" :visible.sync="selKaiPiao" width="80%">

      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          :data="xukaiList" :row-class-name="rowClassName" style="width: 100%">
        <el-table-column
            prop="bianhao"
            label="出库单号"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="riqi"
            label="日期"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="kehu"
            label="客户"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jiashuiXiaoji"
            label="总额"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="weikai"
            label="未开票"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="thisKai"
            label="本次开票"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="thisKai"
            label="本次开票"
            width="180"
        >
          <template slot-scope="scope">
            <el-input @input="kaipiaoInput(scope.$index, scope.row)" v-model="scope.row.thisKai" placeholder="请输入内容" clearable></el-input>
          </template>
        </el-table-column>
      </el-table>

      <span slot="footer" class="dialog-footer">
        <el-button  type="primary" @click="kaipiaoNext()">确定</el-button>
        <el-button  @click="selKaiPiao=false">取消</el-button>
      </span>

    </el-dialog>

    <el-dialog title="" :visible.sync="selKaiPiaoConfirm" width="50%">

      <el-form :model="kaipiaoBody" ref="addUser" label-width="100px"
               class="demo-info" size="medium">
        <el-row :gutter="15">
          <el-col :span="10">
            <el-form-item label="收票单位" prop="shoupiaoDanwei" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.shoupiaoDanwei" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="纳税人识别号" prop="shibiehao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.shibiehao" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="开票地址" prop="kaipiaoDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.kaipiaoDizhi" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="开票电话" prop="kaipiaoDianhua" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.kaipiaoDianhua" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="开户银行" prop="kaipiaoYinhang" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.kaipiaoYinhang" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="银行账号" prop="kaipiaoZhanghao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.kaipiaoZhanghao" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="开票日期" prop="kaipiaoRiqi" class="custom-form-item">
              <el-date-picker
                  style="width:100%"
                  v-model="kaipiaoBody.kaipiaoRiqi"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="kaipiaoBody.beizhu" class="custom-login-inp1" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="开票单位" prop="kaipiaoDanwei" class="custom-form-item">
              <el-select ref="acc_inp" v-model="kaipiaoBody.kaipiaoDanwei" class="custom-login-inp1" placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_HeSuanDanWei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="信息推送" prop="xinxiTuisong" class="custom-form-item">
              <el-select ref="acc_inp" v-model="kaipiaoBody.xinxiTuisong" class="custom-login-inp1" placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShenHe"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button  type="primary" @click="kaipiaoSure()">确定</el-button>
        <el-button  @click="selKaiPiaoConfirm=false">取消</el-button>
      </span>

    </el-dialog>

    <el-dialog title="" :visible.sync="printDialog" width="50%">

      <el-form label-width="100px" class="demo-info">
        <el-form-item label="打印模板" prop="printName" class="custom-form-item">
          <el-select v-model="printName" clearable filterable placeholder="请选择模板">
            <!-- types 为后端查询 -->
            <el-option
                v-for="item in XiaLa_MuBan"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button  type="primary" @click="printStart()">打印预览</el-button>
          <el-button  @click="printBack()">取消</el-button>
        </span>
    </el-dialog>

    <!-- 文件上传 -->
    <el-dialog title="" :visible.sync="fileDialog" width="80%">
      <input ref="up_file" type="file" id="up_file" @change="uploadSelect()" style="display: none">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" :loading="downloadLoading" @click="upload()">文件上传</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          :data="FileList" style="width: 100%">
        <el-table-column
            prop="fileName"
            label="文件名"
            width="auto"
        ></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope">
            <el-button :loading="downloadLoading" @click="downloadFile(scope.row)" type="text" size="small">下载</el-button>
            <el-button :loading="downloadLoading" @click="deleteFile(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-dialog>

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        center>
      <span>请选择审核状态</span>
      <span slot="footer" class="dialog-footer">
        <el-button  type="primary" @click="shenheTrue">审核通过</el-button>
        <el-button  @click="shenheFalse">审核未通过</el-button>
      </span>
    </el-dialog>

    <!--详情窗口弹出-->
    <el-drawer
        title="" :visible.sync="drawer" size="70%" :with-header="false">
      <el-form :model="gongYingShang" ref="addUser" label-width="100px"
               class="demo-info" size="medium">
        <el-row :gutter="15">
          <el-col :span="5">
            <p class="dialog-title" style="margin-left: 30px">销售单信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="10">
            <el-form-item label="订单编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="订单日期" prop="riqi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.riqi" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="客户" prop="kehu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kehu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="价格等级" prop="jiageDengJi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.jiageDengji" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.yewuyuan" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人姓名" prop="shoujianren" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianren" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人手机" prop="shoujianPhone" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianPhone" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="收件人地址" prop="shoujianDizhi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shoujianDizhi" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="销项税率" prop="xiaoxiangShuilv" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoxiangShuilv" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="价税合计" prop="jiashuiHeji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.jiashuiHeji" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shenhe" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <!--        增加销售单位-->
          <el-col :span="10">
            <el-form-item label="销售单位" prop="xiaoshoudanwei" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoshouDanwei" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>

      <div >
        <!--        商品信息 v-for="(item, index) in gongYingShang.body {{index + 1}}"-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">商品信息</p>
          </el-col>
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">金额合计：{{shangpin_money}}</p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#F2F5F7'}"
            :data="gongYingShang.body" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="shangpinBianhao"
              label="商品编码"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="shangpinMingcheng"
              label="名称"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="guige"
              label="规格"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="caizhi"
              label="材质"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jishuBiaozhun"
              label="技术标准"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="zhibaoDengji"
              label="质保等级"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="danwei"
              label="单位"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="shuliang"
              label="数量"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="chukuShuliang"
              label="已出库数量"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="weichuShuliang"
              label="未出库数量"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="baojiaDanjia"
              label="报价单价"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jiashuiXiaoji"
              label="价税小计"
              width="180"
          ></el-table-column>
        </el-table>
      </div>

      <div >
        <!--        出库信息 v-for="(item, index) in gongYingShang.body {{index + 1}}"-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">出库信息</p>
          </el-col>
          <el-col :span="6">
            <p class="dialog-title">
              <el-button class="dialog-title" size="small" round type="primary" @click="chuku_add()">添加出库</el-button>
            </p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#F2F5F7'}"
            :data="gongYingShang.chuku" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="bianhao"
              label="出库单号"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="riqi"
              label="日期"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jiashuiXiaoji"
              label="金额"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="beizhu"
              label="备注"
              width="180"
          ></el-table-column>
        </el-table>
      </div>


      <div >
        <!--        收款信息 v-for="(item, index) in gongYingShang.body {{index + 1}}"-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">收款信息</p>
          </el-col>
          <el-col :span="6">
            <p class="dialog-title">
              <el-button class="dialog-title" size="small" round type="primary" @click="shoukuan_add()">添加收款</el-button>
            </p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#F2F5F7'}"
            :data="gongYingShang.shoukuan" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="shouzhiBianhao"
              label="收款单号"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="shouzhiRiqi"
              label="日期"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="shoufuType"
              label="收款类别"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="money"
              label="金额"
              width="180"
          ></el-table-column>
        </el-table>
      </div>


      <div >
        <!--        开票信息 v-for="(item, index) in gongYingShang.body {{index + 1}}"-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">开票信息</p>
          </el-col>
          <el-col :span="6">
            <p class="dialog-title">
              <el-button class="dialog-title" size="small" round type="primary" @click="kaipiao_add()">添加开票</el-button>
            </p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#F2F5F7'}"
            :data="gongYingShang.kaipiao" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="xiaoshouBianhao"
              label="出库单号"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="kaipiaoRiqi"
              label="开票日期"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jiashuiHeji"
              label="金额"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="beizhu"
              label="备注"
              width="180"
          ></el-table-column>
        </el-table>
      </div>

      <div >
        <!--        采购信息 v-for="(item, index) in gongYingShang.body {{index + 1}}"-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">采购信息</p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#F2F5F7'}"
            :data="gongYingShang.caigou" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="bianhao"
              label="采购单号"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="riqi"
              label="日期"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jiashuiXiaoji"
              label="金额"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="beizhu"
              label="备注"
              width="180"
          ></el-table-column>
        </el-table>
      </div>

      <div class="bottom" style="height: 50px"></div>

    </el-drawer>
<!--    ztt 审核-->
    <el-dialog title="" :visible.sync="userList" width="90%">
      <el-row :gutter="15">
        <el-col :span="6">
          <p class="dialog-title">审核人信息</p>
        </el-col>
      </el-row>
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          style="width: 100%;;"
          :height="tableHeight"
          :data="users"
          @selection-change="shenheSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="name"
            label="审核人"
            width="auto">
        </el-table-column>
      </el-table>
      <el-col :span="4">
        <div style="display: flex;margin-top: 20px">
          <el-button class="custom-login-button"  type="primary"
                     @click="addUserNameList" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>添加
          </el-button>
        </div>
      </el-col>
    </el-dialog>
<!--    ztt end-->

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
      printName:'',
      XiaLa_MuBan:[],
      printDialog:false,
      fileDialog:false,
      downloadLoading:false,
      FileList:[],
      users:[],//ztt审核人
      userList:false,//ztt审核人
      shenheMultipleSelection: [],//ztt审核人多选
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      dialogVisible:false,
      shenheButton:false,
      drawer:false,//详情窗口隐藏设置
      shangpin_money:0,//详情页商品合计金额
      p_index:'',
      start_date:'',
      stop_date:'',
      kehu:'',
      shenhe_zhuangtai:'',
      shenhe:'',
      Protype:'',
      Proname:'',
      xukaiList:[],
      p_list:[],
      XiaLa_KeHu:[],
      XiaLa_User:[],
      XiaLa_DianPu:[],
      CaiGou_Product:[],
      XiaLa_GongYingShang:[],
      XiaLa_ShenHe:[],
      XiaLa_ZhiBaoDengJi:[],
      kaipiaoList:[],
      kaipiaoBody:{},
      XiaLa_HeSuanDanWei:[],
      XiaLa_ShenHeZhuangTai:[
        {
          name:'审核中',
          label:'审核中',
        },
        {
          name:'审核通过',
          label:'审核通过',
        },
        {
          name:'审核未通过',
          label:'审核未通过',
        }
      ],
      gongYingShang: {
        bianhao:'123123',
        riqi: getNowDate(),
        kehu: '',
        yewuyuan: '',
        shoujianren:'',
        shoujianPhone:'',
        shoujianDizhi:'',
        dianpu: '',
        xiaoxiangShuilv: '',
        beizhu:'',
        shenhe:'',
        jiageDengji:'',
        jiashui_heji:'',
        xiaoshouDanwei:'',//ztt销售单位
        shenheZhuangtai:'审核中',
        body:[
          {
            shangpinBianhao:'',
            shangpinMingcheng:'',
            guige:'',
            caizhi:'',
            jishuBiaozhun:'',
            zhibaoDengji:'',
            danwei:'',
            shuliang:'',
            baojiaDanjia:'',
            jiashuiXiaoji:'',
            jianyiBaojia:'',
            xuyongRiqi:'',
            baojiaFudong:'',
            beizhu:'',
          }
        ]
      },
      addDialog: false,
      updDialog: false,
      selProduct: false,
      selKaiPiao: false,
      selKaiPiaoConfirm: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.getXiaoShouProduct();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_KeHu()
    this.getXiaLa_ShenHe();
    this.getXiaLa_DianPu();
    this.getXiaLa_MuBan();
    this.getXiaLa_ZhiBaoDengJi();
    this.getXiaLa_HeSuanDanWei();
  },
  methods: {
    // ztt 审核
    seeShenHeRen(){
      if(this.userPower.xiaoshouBaojiaAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://localhost:8102/user/queryAllUsers"
      this.axios.post(url).then(res =>{
        if(res.data.code == 0){
          this.users = res.data.data
          console.log("ss")
          console.log(this.users)
          this.userList = true
        }
      })
    },
//ztt 跳转到相应配置项页
    goToPeiZhi(url,pageType){
      RouterUtil.to(url + '?type=' + pageType);
      },
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

    rowClassName: function ({ row }) {
      if (row.isselect == 0) {
        return "hidden-row";
      }
      return '';
    },

    kaipiaoInput(index,row){
      if(this.xukaiList[index].weikai * 1 < this.xukaiList[index].thisKai * 1){
        this.xukaiList[index].thisKai = this.xukaiList[index].weikai * 1
      }
    },

    kaipiaoNext(){
      var xukaiList = this.xukaiList
      var kaipiaoList = []
      for(var i=0; i < xukaiList.length; i++){
        if(xukaiList[i].thisKai != "" && xukaiList[i].thisKai != null && xukaiList[i].thisKai != undefined){
          if(xukaiList[i].thisKai * 1 > 0){
            kaipiaoList.push(xukaiList[i])
          }
        }
      }
      console.log(kaipiaoList)
      if(xukaiList.length < 1){
        MessageUtil.error("请填写本次开票金额");
        return;
      }
      this.kaipiaoList = kaipiaoList
      var customerList = this.XiaLa_KeHu
      this.kaipiaoBody = ""
      for(var i=0; i<customerList.length; i++){
        if(customerList[i].name == this.gongYingShang.kehu){
          this.kaipiaoBody={
            shoupiaoDanwei : customerList[i].name,
            shibiehao : customerList[i].shibiehao,
            kaipiaoDizhi : customerList[i].kaipiaoDizhi,
            kaipiaoDianhua : customerList[i].kaipiaoDianhua,
            kaipiaoYinhang : customerList[i].kaipiaoYinhang,
            kaipiaoZhanghao : customerList[i].kaipiaoZhanghao,
            xiaoshouBianhao:'',
            kaipiaoRiqi:'',
            kaipiaoJine:'',
            kaipiaoShuie:'',
            jiashuiHeji:'',
            beizhu:'',
            kaipiaoDanwei:'',
            xinxiTuisong:'',
            kaipiaoZhuangtai:'待开票',
          }
        }
      }
      this.selKaiPiao = false
      this.selKaiPiaoConfirm = true
    },


    kaipiaoSure(){
      var kaipiaoBody = this.kaipiaoBody
      console.log(kaipiaoBody)
      console.log(this.kaipiaoList)
      if(kaipiaoBody.kaipiaoRiqi == ""){
        MessageUtil.error("未填写开票日期");
        return;
      }
      if(kaipiaoBody.kaipiaoDanwei == ""){
        MessageUtil.error("未填写开票单位");
        return;
      }
      if(kaipiaoBody.xinxiTuisong == ""){
        MessageUtil.error("未填写信息推送");
        return;
      }
      var date = {
        head:kaipiaoBody,
        body:this.kaipiaoList,
      }
      let url="http://localhost:8102/kaiPiao/kaiPiaoListAdd"
      this.axios.post(url,date).then(res => {
        if(res.data.code=="00"){
          this.selKaiPiaoConfirm = false
          this.drawer = false
          MessageUtil.success("完成");
        }else{
          MessageUtil.error("获取失败");
        }
      }).catch(()=>{
        MessageUtil.error("网络异常");
      })
    },


    // 查看详情窗口弹出
    seeList(row){
      this.p_id=row.id
      let url="http://localhost:8102/xiaoShouDingDan/selectXiangQingById"
      this.axios.post(url,{'id':row.id,'bianhao':row.bianhao}).then(res => {
        if(res.data.code=="00"){
          console.log(res.data)
          var this_val = res.data.dingdan[0]
          this_val.body = res.data.shangpin
          var shangpin_money = 0
          for(var i=0; i<res.data.shangpin.length; i++){
            shangpin_money = shangpin_money + Math.round(res.data.shangpin[i].jiashuiXiaoji * 100) / 100
          }
          console.log(shangpin_money)
          this.shangpin_money = shangpin_money
          this_val.chuku = res.data.chuku
          this_val.shoukuan = res.data.shoukuan
          this_val.kaipiao = res.data.kaipiao
          this_val.caigou = res.data.caigou
          this.gongYingShang=this_val
          console.log(res.data.data);
          console.log("获取成功");
          this.drawer= true;
        }else{
          MessageUtil.error("获取失败");
        }
      }).catch(()=>{
        MessageUtil.error("网络异常");
      })
    },

    chuku_add(){
      console.log(this.gongYingShang.shangpin)
      var shangpin_list = this.gongYingShang.body
      var add_shangpin = []
      for(var i=0; i<shangpin_list.length; i++){
        if(shangpin_list[i].weichuShuliang *1 > 0){
          add_shangpin.push(shangpin_list[i])
        }
      }
      if(add_shangpin.length < 1){
        MessageUtil.error("此订单商品已全部出库");
        return;
      }
      var dingdan = this.gongYingShang
      RouterUtil.to('/xiaoshouchuku?dingdan=' + JSON.stringify(this.gongYingShang) + "&shangpin=" + JSON.stringify(add_shangpin));
    },

    shoukuan_add(){
      var shoukuan_list = this.gongYingShang.shoukuan
      var shoukuan_sum = 0
      for(var i=0; i<shoukuan_list.length; i++){
        if(shoukuan_list[i].shoufuType == '收订金'){
          shoukuan_sum = shoukuan_sum + Math.round(shoukuan_list[i].money * 100) / 100
        }
      }
      if(shoukuan_sum >= this.shangpin_money){
        MessageUtil.error("此订单订金与订单价税合计相同'");
        return;
      }

      var yukuan = this.shangpin_money - shoukuan_sum
      var dingdan = this.gongYingShang
      RouterUtil.to('/shourujilu?shoufu_type=收订金&yukuan=' + yukuan + '&dingdan=' + JSON.stringify(dingdan));

    },

    kaipiao_add(){
      var bianhao = this.gongYingShang.bianhao
      let url = "http://localhost:8102/xiaoShouDingDan/xiangQingXuKaiPiao"
      this.axios.post(url, {'bianhao':bianhao}).then(res => {
        if(res.data.code == '00') {
          var xukaipiao = res.data.xukaipiao
          if(xukaipiao.length < 1){
            MessageUtil.error("此订单的出库单已全部开票");
            return;
          }
          this.xukaiList = xukaipiao
          this.selKaiPiao = true
          console.log(xukaipiao)
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    rowClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.body[this.p_index].shangpinBianhao = row.bianhao
        this.gongYingShang.body[this.p_index].shangpinMingcheng = row.name
        this.gongYingShang.body[this.p_index].guige = row.guige
        this.gongYingShang.body[this.p_index].caizhi = row.caizhi
        this.gongYingShang.body[this.p_index].jishuBiaozhun = row.jishuBiaozhun
        this.gongYingShang.body[this.p_index].zhibaoDengji = row.zhibaoDengji
        this.gongYingShang.body[this.p_index].danwei = row.danwei

        this.gongYingShang.body[this.p_index].caigouDanjia = row.caigouPrice

        if(this.gongYingShang.jiageDengji == '零售价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.lingshouPrice
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.lingshouPrice
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.lingshouPrice * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }else if(this.gongYingShang.jiageDengji == '批发价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.pifaPrice
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.pifaPrice
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.pifa_price * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }else if(this.gongYingShang.jiageDengji == '大客户价格'){
          this.gongYingShang.body[this.p_index].jianyiBaojia = row.dakehuPrice
          if(this.gongYingShang.body[this.p_index].baojiaFudong == ''){
            this.gongYingShang.body[this.p_index].baojiaFudong = 100
            this.gongYingShang.body[this.p_index].baojiaDanjia = row.dakehuPrice
          }else{
            this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round((row.dakehuPrice * this.gongYingShang.body[this.p_index].baojiaFudong / 100) * 100) / 100
          }
        }

        if(this.gongYingShang.body[this.p_index].baojiaDanjia != '' && this.gongYingShang.body[this.p_index].shuliang != ''){
          this.gongYingShang.body[this.p_index].jiashuiXiaoji = Math.round(this.gongYingShang.body[this.p_index].baojiaDanjia * this.gongYingShang.body[this.p_index].shuliang * 100) / 100
        }
        this.selProduct = false;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

    changeValue(index,column){
      console.log(index,column)
      if(column == 'baojia_fudong'){
        this.gongYingShang.body[this.p_index].baojiaDanjia = Math.round(this.gongYingShang.body[this.p_index].jianyiBaojia * this.gongYingShang.body[this.p_index].baojiaFudong / 100 * 100) / 100
      }
      if(column == 'baojia_danjia'){
        this.gongYingShang.body[this.p_index].baojiaFudong = Math.round(this.gongYingShang.body[this.p_index].baojiaDanjia / this.gongYingShang.body[this.p_index].jianyiBaojia * 100 * 100) / 100
      }
      this.gongYingShang.body[this.p_index].jiashuiXiaoji = Math.round(this.gongYingShang.body[this.p_index].shuliang * this.gongYingShang.body[this.p_index].baojiaDanjia * 100) / 100
      var jiashui = 0
      for(var i=0; i<this.gongYingShang.body.length; i++){
        jiashui = Math.round((jiashui*1) + (this.gongYingShang.body[i].jiashuiXiaoji*1)*100) /100
      }
      this.gongYingShang.jiashuiHeji = jiashui
    },

    changeKeHu(){
      var this_val = this.gongYingShang.kehu
      for(var i=0; i< this.XiaLa_KeHu.length; i++){
        if(this.XiaLa_KeHu[i].name == this_val){
          this.gongYingShang.jiageDengji = this.XiaLa_KeHu[i].jiageDengji
          this.gongYingShang.shoujianren = this.XiaLa_KeHu[i].shoujianName
          this.gongYingShang.shoujianPhone = this.XiaLa_KeHu[i].shoujianPhone
          this.gongYingShang.shoujianDizhi = this.XiaLa_KeHu[i].shoujianDizhi
          break;
        }
      }
    },

    ProQuery(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        var panduan = true
        console.log(i)
        if(this.Protype != ''){
          if(this.CaiGou_Product[i].type.indexOf(this.Protype) == -1){
            panduan = false
          }
        }
        if(this.Proname != ''){
          if(this.CaiGou_Product[i].name.indexOf(this.Proname) == -1){
            panduan = false
          }
        }
        if(panduan){
          this.CaiGou_Product[i].isselect = 1
        }else{
          this.CaiGou_Product[i].isselect = 0
        }
      }
    },

    Prorefresh(){
      for(var i=0; i<this.CaiGou_Product.length;i++){
        this.CaiGou_Product[i].isselect = 1
      }
    },

    //新增窗口弹出
    addUser() {
      if(this.userPower.xiaoshouDingdanAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://localhost:8102/xiaoShouBaoJia/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "XS" + this_danhao
          this.gongYingShang = {
            bianhao:this_danhao,
            riqi: getNowDate(),
            kehu: '',
            yewuyuan: this.userInfo.name,
            dianpu: '',
            xiaoxiangShuilv: '',
            beizhu:'',
            shenhe:this.userInfo.shenpi,
            jiageDengji:'',
            xiaoshouDanwei:'',//ztt销售单位
            shenheZhuangtai:'未审核',//ztt修改审核
            body:[
              {
                shangpinBianhao:'',
                shangpinMingcheng:'',
                guige:'',
                caizhi:'',
                jishuBiaozhun:'',
                zhibaoDengji:'',
                danwei:'',
                shuliang:'',
                baojiaDanjia:'',
                jiashuiXiaoji:'',
                jianyiBaojia:'',
                xuyongRiqi:'',
                baojiaFudong:'',
                beizhu:'',
              }
            ]
          }
          console.log(res.data.data);
          console.log("获取成功");
          this.addDialog = true;
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })


      this.addDialog = true;
    },

    addClose(){
      this.addDialog = false;
    },

    selectProduct(index){
      if(this.gongYingShang.jiageDengji == '' || this.gongYingShang.jiageDengji == null || this.gongYingShang.jiageDengji == undefined){
        MessageUtil.error("未读取到客户价格等级，请选择客户后再试");
        return;
      }
      console.log(index)
      this.p_index = index
      this.Proname = ""
      this.Protype = ""
      for(var i=0; i<this.CaiGou_Product.length; i++){
        this.CaiGou_Product[i].isselect = 1
      }
      this.selProduct = true;
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

      let url = "http://localhost:8102/xiaoShouDingDan/selectXiaoShouById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          if(res.data.data.shenheZhuangtai == '审核未通过'){
            res.data.data.shenheZhuangtai = '审核中'
          }
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

    getXiaoShouProduct(){
      let url = "http://localhost:8102/product/selectXiaoShouProduct"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("销售商品列表已获取");
        } else {
          console.log("销售商品列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
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
          // <!--    ztt 导航栏快速跳转未审核-->
          this.shuju = this.$route.query.xiaoshoudingdanshenhegeshu;
          console.log(this.shuju);
          if( this.shuju != undefined ){
            this.myShenHe();
          }else {
            if (this.userPower.xiaoshouBaojiaSel == '查看全部') {
              this.getAll();
            } else if (this.userPower.xiaoshouBaojiaSel == '查看个人') {
              this.getAllByName();
            } else {
              MessageUtil.error("无查询权限");
            }
          }
          // ztt end
          window.localStorage.setItem('userPower',JSON.stringify(res.data.data))
          console.log("权限信息已获取");
        } else {
          console.log("权限信息获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_GongYingShang(){
      let url = "http://localhost:8102/gongYingShang/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_GongYingShang = res.data.data;
          for(var i=0; i<this.XiaLa_GongYingShang.length; i++){
            this.XiaLa_GongYingShang[i].label = this.XiaLa_GongYingShang.name
          }
          console.log("供应商下拉已获取");
        } else {
          console.log("供应商下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_KeHu(){
      let url = "http://localhost:8102/customer/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_KeHu = res.data.data;
          for(var i=0; i<this.XiaLa_KeHu.length; i++){
            this.XiaLa_KeHu[i].label = this.XiaLa_KeHu.name
          }
          console.log("客户下拉已获取");
        } else {
          console.log("客户下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_User(){
      let url = "http://localhost:8102/user/getall"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_User = res.data.data;
          for(var i=0; i<this.XiaLa_User.length; i++){
            this.XiaLa_User[i].label = this.XiaLa_User.name
          }
          console.log("业务员下拉已获取");
        } else {
          console.log("业务员下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ShenHe(){
      let url = "http://localhost:8102/user/fuzzyQuery"
      this.axios.post(url,{"keyword":""}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ShenHe = res.data.data;
          for(var i=0; i<this.XiaLa_ShenHe.length; i++){
            this.XiaLa_ShenHe[i].label = this.XiaLa_ShenHe.name
          }
          console.log("审核人下拉已获取");
        } else {
          console.log("审核人下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_DianPu(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"店铺"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_DianPu = res.data.data;
          for(var i=0; i<this.XiaLa_DianPu.length; i++){
            this.XiaLa_DianPu[i].label = this.XiaLa_DianPu.name
          }
          console.log("店铺下拉已获取");
        } else {
          console.log("店铺下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_MuBan(){
      let url = "http://localhost:8102/printMuBan/getMuBanByType"
      this.axios.post(url, {"type":"销售订单"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_MuBan = res.data.data;
          for(var i=0; i<this.XiaLa_MuBan.length; i++){
            this.XiaLa_MuBan[i].label = this.XiaLa_MuBan.name
          }
          console.log("打印模板下拉已获取");
        } else {
          console.log("打印模板下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ZhiBaoDengJi(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"质保等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_ZhiBaoDengJi = res.data.data;
          for(var i=0; i<this.XiaLa_ZhiBaoDengJi.length; i++){
            this.XiaLa_ZhiBaoDengJi[i].label = this.XiaLa_ZhiBaoDengJi.name
          }
          console.log("质保等级下拉已获取");
        } else {
          console.log("质保等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_HeSuanDanWei(){
      let url = "http://localhost:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"核算单位"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_HeSuanDanWei = res.data.data;
          for(var i=0; i<this.XiaLa_HeSuanDanWei.length; i++){
            this.XiaLa_HeSuanDanWei[i].label = this.XiaLa_HeSuanDanWei.name
          }
          console.log("核算单位下拉已获取");
        } else {
          console.log("核算单位下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      this.shenheButton = false
      let url = "http://localhost:8102/xiaoShouDingDan/getAll"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          //ztt 审核
          for(var i=this.tableData.length -1 ; i >= 0; i--){
            if(this.tableData[i].shenheZhuangtai == '未提交审核'){
              if(this.tableData[i].yewuyuan != this.userInfo.name){
                this.tableData.splice(i,1)
              }
            }
          }
          //ztt end
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAllByName(){
      this.shenheButton = false
      let url = "http://localhost:8102/xiaoShouDingDan/getAllByName"
      this.axios.post(url, {"yewuyuan":this.userInfo.name}).then(res => {
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
      this.shenheButton = false
      if(this.userPower.xiaoshouDingdanSel == '查看全部'){
        this.getAll();
      }else if(this.userPower.xiaoshouDingdanSel == '查看个人'){
        this.getAllByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    query(){
      this.shenheButton = false
      if(this.userPower.xiaoshouDingdanSel == '查看全部'){
        this.queryList();
      }else if(this.userPower.xiaoshouDingdanSel == '查看个人'){
        this.queryListByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == ''){
        start_date = '1900-01-01'
      }
      if(stop_date == ''){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        kehu:this.kehu,
        shenhe_zhuangtai:this.shenhe
      }
      let url = "http://localhost:8102/xiaoShouDingDan/queryList"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          //ztt 审核
          for(var i=this.tableData.length -1 ; i >= 0; i--){
            if(this.tableData[i].shenheZhuangtai == '未提交审核'){
              if(this.tableData[i].yewuyuan != this.userInfo.name){
                this.tableData.splice(i,1)
              }
            }
          }
          //ztt end
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    queryListByName(){
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == ''){
        start_date = '1900-01-01'
      }
      if(stop_date == ''){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        kehu:this.kehu,
        shenhe_zhuangtai:this.shenhe,
        yewuyuan:this.userInfo.yewuyuan,
      }
      let url = "http://localhost:8102/xiaoShouDingDan/queryListByName"
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

    //条件查询
    myShenHe(){
      this.shenheButton = true
      let url = "http://localhost:8102/xiaoShouDingDan/shenheList"
      this.axios.post(url, {"name":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          //ztt 审核
          var shenheName = []
          var shenheList = []
          for(var i=this.tableData.length - 1; i>= 0; i--){
            console.log("xunhuan")
            console.log(this.tableData)
            console.log("x")
            console.log(this.tableData[i].shenhe)
            if (this.tableData[i].shenhe != undefined){
              var this_arr = this.tableData[i].shenhe.split(",")
            }
            console.log("a")
            console.log(this_arr)
            console.log("xz")
            console.log(this.tableData[i].shenheList)
            if (this.tableData[i].shenheList != undefined){
              var shenhe_arr = this.tableData[i].shenheList.split(",")
            }
            console.log("s")
            console.log(shenhe_arr)
            for(var j = 0; j < this_arr.length; j++){
              shenheName.push(this_arr[j])
              console.log("z")
            }

            for(var j = 0; j < shenhe_arr.length; j++){
              shenheList.push(shenhe_arr[j])
              console.log("x")
            }
            var panduan = false
            var xiabiao = ""
            for(var j = 0; j < this_arr.length; j++){
              if(this_arr[j] == this.userInfo.name){
                panduan = true
                xiabiao = j
                console.log("c")
              }
            }

            if (panduan == false){
              this.tableData.splice(i,1)
              console.log("v")
            }else if(xiabiao != "" || xiabiao >= 0){
              if(shenhe_arr[xiabiao] == "审核通过"){
                this.tableData.splice(i,1)
                console.log("b")
              }
            }

          }
          //ztt end
          this.total = res.data.data.length;
          MessageUtil.success("共查询到" + this.tableData.length + "条数据")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },
    // ztt审核按钮提交修改
    saveAndShenHe(){
      var this_list = this.gongYingShang
      this_list.shenheZhuangtai = "审核中"
      this.save();
    },
    //ztt end
    delLianXiRen(index){
      console.log(index)
      this.$confirm('是否删除此信息?', '提示', {
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
        shangpinBianhao:'',
        shangpinMingcheng:'',
        guige:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        danwei:'',
        shuliang:'',
        baojiaDanjia:'',
        jiashuiXiaoji:'',
        jianyiBaojia:'',
        xuyongRiqi:'',
        baojiaFudong:'',
        beizhu:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://localhost:8102/xiaoShouDingDan/xiaoShouAdd"
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
      let url = "http://localhost:8102/xiaoShouDingDan/xiaoShouUpd"
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

      if(this.userPower.xiaoshouDingdanUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
        return;
      }

      if(this.gongYingShang.riqi == ''){
        MessageUtil.error('请选择日期');
        return;
      }
      if(this.gongYingShang.kehu == ''){
        MessageUtil.error('请选择客户');
        return;
      }
      if(this.gongYingShang.yewuyuan == ''){
        MessageUtil.error('请选择业务员');
        return;
      }
      if(this.gongYingShang.dianpu == ''){
        MessageUtil.error('请选择店铺');
        return;
      }
      if(this.gongYingShang.shenhe == ''){
        MessageUtil.error('请选择审核人');
        return;
      }
      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].shangpinBianhao == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未选择商品');
          return;
        }
        if(this.gongYingShang.body[i].shuliang == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写数量');
          return;
        }
        if(this.gongYingShang.body[i].baojiaDanjia == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写报价单价');
          return;
        }
      }

      if(this.gongYingShang.id != undefined && this.gongYingShang.id != null){
        this.updGongYingShang()
      }else{
        this.saveGongYingShang()
      }
    },

    deleteClick(){
      if(this.userPower.xiaoshouDingdanDel != '是'){
        MessageUtil.error("无删除权限");
        return;
      }
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
        let url = "http://localhost:8102/xiaoShouDingDan/delXiaoShouBaoJia";
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


    shenheClick(){
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.p_list = []
      for(var i=0; i<this.multipleSelection.length; i++){
        this.p_list.push(this.multipleSelection[i])//ztt 审核
      }
      this.dialogVisible = true;
    },

    shenheTrue(){
      console.log(this.p_list)
      //ztt 审核
      var this_list = []
      for(var i=0; i<this.p_list.length; i++){
        console.log("p_list")
        console.log(this.p_list[i])
        console.log(this.p_list[i].shenhe)
        var this_shenhe = this.p_list[i].shenhe.split(",")
        var this_shenhe_list = this.p_list[i].shenheList.split(",")
        var shenhe_str = ""
        var shenheList_str = ""
        for(var j=0; j<this_shenhe.length; j++){
          if(this_shenhe[j] == this.userInfo.name){
            if(shenheList_str == ""){
              shenheList_str = "审核通过"
            }else{
              shenheList_str = shenheList_str + ",审核通过"
            }
          }else{
            if(shenheList_str == ""){
              shenheList_str = this_shenhe_list[j]
            }else{
              shenheList_str = shenheList_str + "," + this_shenhe_list[j]
            }
          }
        }
        var shenheZhuangtai = this.p_list[i].shenheZhuangtai
        var shenhe_arr = shenheList_str.split(",")
        var this_panduan = true
        for(var j=0; j<shenhe_arr.length; j++){
          if(shenhe_arr[j] != '审核通过'){
            this_panduan = false
          }
        }
        if(this_panduan == true && this.p_list[i].shenheZhuangtai != "未提交审核"){
          shenheZhuangtai = "审核通过"
        }
        if(this_panduan == false && this.p_list[i].shenheZhuangtai != "未提交审核"){
          shenheZhuangtai = "审核中"
        }
        this_list.push({
          id:this.p_list[i].id,
          shenheList:shenheList_str,
          shenheZhuangtai:shenheZhuangtai
        })
      }
      console.log(this_list)
      let url = "http://localhost:8102/xiaoShouDingDan/dingDanShenHe";
      axios.post(url, {"list": this_list}).then(res => {
        //ztt end
        MessageUtil.success(res.data.msg);
        this.dialogVisible = false;
        this.myShenHe()
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.dialogVisible = false;
      })
    },
    shenheFalse(){
      console.log(this.p_list)
      //ztt 审核
      var this_list = []
      for(var i=0; i<this.p_list.length; i++){
        console.log(this.p_list[i])
        var this_shenhe = this.p_list[i].shenhe.split(",")
        var this_shenhe_list = this.p_list[i].shenheList.split(",")
        var shenhe_str = ""
        var shenheList_str = ""
        for(var j=0; j<this_shenhe.length; j++){
          if(this_shenhe[j] == this.userInfo.name){
            if(shenheList_str == ""){
              shenheList_str = "审核未通过"
            }else{
              shenheList_str = shenheList_str + ",审核未通过"
            }
          }else{
            if(shenheList_str == ""){
              shenheList_str = this_shenhe_list[j]
            }else{
              shenheList_str = shenheList_str + "," + this_shenhe_list[j]
            }
          }
        }
        var shenheZhuangtai = this.p_list[i].shenheZhuangtai
        var shenhe_arr = shenheList_str.split(",")
        var this_panduan = true
        for(var j=0; j<shenhe_arr.length; j++){
          if(shenhe_arr[j] != '审核未通过'){
            this_panduan = false
          }
        }
        if(this_panduan == true && this.p_list[i].shenheZhuangtai != "未提交审核"){
          shenheZhuangtai = "审核通过"
        }
        if(this_panduan == false && this.p_list[i].shenheZhuangtai != "未提交审核"){
          shenheZhuangtai = "审核中"
        }
        this_list.push({
          id:this.p_list[i].id,
          shenheList:shenheList_str,
          shenheZhuangtai:shenheZhuangtai
        })
      }
      console.log(this_list)
      let url = "http://localhost:8102/xiaoShouDingDan/dingDanShenHe";
      axios.post(url, {"list": this_list}).then(res => {
        // ztt end
        MessageUtil.success(res.data.msg);
        this.dialogVisible = false;
        this.myShenHe()
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.dialogVisible = false;
      })
    },
    // ztt多选添加审核人按钮
    addUserNameList(){
      var this_list = this.gongYingShang
      console.log("kaishi")
      console.log(this_list)
      var str = ""
      var shenhe = ""
      for (var i=0; i<this.shenheMultipleSelection.length;i++){

        if(str == ""){
          str = this.shenheMultipleSelection[i].name
          shenhe = "审核中"
        }else{
          str = str + "," + this.shenheMultipleSelection[i].name
          shenhe = shenhe + ',' + "审核中"
        }
        console.log("shuju")
        console.log(this.shenheMultipleSelection[i].name)
      }
      this_list.shenhe = str
      this_list.shenheList = shenhe
      this.gongYingShang = this_list
      this.userList = false
    },
    shenheSelectionChange(val){
      this.shenheMultipleSelection = val;
      console.log(val)
    },
//ztt end
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.currentPage = 1
      console.log(`每页 ${val} 条`);
    },

    getfileList(row){
      console.log(row)
      this.p_id = row.id
      let url = "http://localhost:8102/fileTable/getAll"
      this.axios.post(url, {"id":row.id,"type":"销售订单"}).then(res => {
        if(res.data.code == '00') {
          this.FileList = res.data.data;
          this.fileDialog = true
          MessageUtil.success("共查询到" + this.FileList.length + "条数据")

        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    refreshfileList(){
      let url = "http://localhost:8102/fileTable/getAll"
      this.axios.post(url, {"id":this.p_id,"type":"销售订单"}).then(res => {
        if(res.data.code == '00') {
          this.FileList = res.data.data;
          this.fileDialog = true
          MessageUtil.success("共查询到" + this.FileList.length + "条数据")

        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    downloadFile(row){
      console.log(row)
      let url = "http://localhost:8102/fileTable/getById"
      this.axios.post(url, {"id":row.id}).then(res => {
        if(res.data.code == '00') {
          if(res.data.data[0].fileName != '' && res.data.data[0].fileName != null){
            downloadFileByBase64(res.data.data[0].fileName, res.data.data[0].file.split(',')[1])
          }
          MessageUtil.success("读取到文件，正在下载")
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    deleteFile(row){
      console.log(row)
      this.downloadLoading = true
      let url = "http://localhost:8102/fileTable/deleteById"
      this.axios.post(url, {"list":[row.id]}).then(res => {
        if(res.data.code == '00') {
          console.log(res)
          MessageUtil.success(res.data.msg);
          this.downloadLoading = false
          this.refreshfileList()
        } else {
          MessageUtil.error(res.data.msg);
          this.downloadLoading = false
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
        this.downloadLoading = false
      })
    },

    upload(){
      this.$refs.up_file.click();
    },

    uploadSelect(){
      this.downloadLoading = true
      var file = document.getElementById("up_file").files;
      console.log(file)
      if(file.length == 0){
        this.downloadLoading = false
      }
      var oFReader = new FileReader();
      var this_file = file[0];
      var fileName = file[0].name;
      var obj = [];
      console.log("fileName:" + fileName)
      oFReader.readAsDataURL(this_file);
      oFReader.onload = oFRevent => {
        console.log('oFRevent----', oFRevent)
        this_file = oFRevent.target.result;
        obj = {
          "fileId": this.p_id,
          "fileName": fileName,
          "file": this_file,
          "type": "销售订单",
        };
        let url = "http://localhost:8102/fileTable/fileAdd"
        this.axios.post(url, obj).then(res => {
          if(res.data.code == '00') {
            console.log(res)
            MessageUtil.success(res.data.msg);
            this.downloadLoading = false
            this.refreshfileList()
          } else {
            MessageUtil.error(res.data.msg);
            this.downloadLoading = false
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
          this.downloadLoading = false
        })
      }
    },

    printShow(row){
      console.log(row)
      console.log(row.id)
      this.p_id = row.id
      this.printDialog = true
      this.printName = ""
    },

    printBack(){
      this.printDialog = false
    },

    printStart(){
      var MuBan = ""
      if(this.printName == ""){
        MessageUtil.error("请选择模板");
        return;
      }
      for(var i=0; i<this.XiaLa_MuBan.length; i++){
        if(this.XiaLa_MuBan[i].name == this.printName){
          MuBan = this.XiaLa_MuBan[i].value
          if(MuBan != ""){
            MuBan = JSON.parse(MuBan)
            break;
          }
        }
      }
      if(MuBan == ""){
        MessageUtil.error("未读取到模板信息，请设计此模板样式后再试");
        return;
      }

      let url = "http://localhost:8102/xiaoShouDingDan/selectXiaoShouById"
      this.axios.post(url, {"id":this.p_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.details = this_val.itemList
          this.$lodop.preview(MuBan, [this_val]);
          console.log(res.data.data);
          console.log("获取成功");
        } else {
          MessageUtil.error("获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })

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

function dataURLtoBlob(dataurl, name) {//name:文件名
  var mime = name.substring(name.lastIndexOf('.') + 1)//后缀名
  var bstr = atob(dataurl), n = bstr.length, u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new Blob([u8arr], {type: mime});
}

function downloadFile(url, name = '默认文件名') {
  var a = document.createElement("a")//创建a标签触发点击下载
  a.setAttribute("href", url)//附上
  a.setAttribute("download", name);
  a.setAttribute("target", "_blank");
  let clickEvent = document.createEvent("MouseEvents");
  clickEvent.initEvent("click", true, true);
  a.dispatchEvent(clickEvent);
}

//主函数
function downloadFileByBase64(name, base64) {
  var myBlob = dataURLtoBlob(base64, name);
  var myUrl = URL.createObjectURL(myBlob);
  downloadFile(myUrl, name)
}

//获取后缀
function getType(file) {
  var filename = file;
  var index1 = filename.lastIndexOf(".");
  var index2 = filename.length;
  var type = filename.substring(index1 + 1, index2);
  return type;
}

//根据文件后缀 获取base64前缀不同
function getBase64Type(type) {
  switch (type) {
    case 'data:text/plain;base64':
      return 'txt';
    case 'data:application/msword;base64':
      return 'doc';
    case 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64':
      return 'docx';
    case 'data:application/vnd.ms-excel;base64':
      return 'xls';
    case 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64':
      return 'xlsx';
    case 'data:application/pdf;base64':
      return 'pdf';
    case 'data:application/vnd.openxmlformats-officedocument.presentationml.presentation;base64':
      return 'pptx';
    case 'data:application/vnd.ms-powerpoint;base64':
      return 'ppt';
    case 'data:image/png;base64':
      return 'png';
    case 'data:image/jpeg;base64':
      return 'jpg';
    case 'data:image/gif;base64':
      return 'gif';
    case 'data:image/svg+xml;base64':
      return 'svg';
    case 'data:image/x-icon;base64':
      return 'ico';
    case 'data:image/bmp;base64':
      return 'bmp';
  }
}

function base64ToBlob(code) {
  code = code.replace(/[\n\r]/g, '');
  const raw = window.atob(code);
  const rawLength = raw.length;
  const uInt8Array = new Uint8Array(rawLength);
  for (let i = 0; i < rawLength; ++i) {
    uInt8Array[i] = raw.charCodeAt(i)
  }
  return new Blob([uInt8Array], {type: 'application/pdf'})
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