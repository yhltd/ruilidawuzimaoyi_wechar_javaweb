<template>
  <el-container style="height: 100%;" direction="vertical">

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
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" size="small" round type="primary" @click="addUser()"><i class="el-icon-circle-plus-outline"></i>添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" size="small" round type="primary" @click="updUser()"><i class="el-icon-edit"></i>编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="myShenHe()">需要我审核</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="shenheButton" size="small" round type="primary" @click="shenheClick()">审核</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button v-if="!shenheButton" size="small" round type="danger" @click="deleteClick()"><i class="el-icon-delete"></i>删除</el-button>
        </el-col>
      </el-row>
    </el-header>

    <el-main refs="main" style="height: 50%;">
      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
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
            prop="shenhe"
            label="审核人"
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
            prop="shenheZhuangtai"
            label="审核状态"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="220"
            class="custom-column1"><!--修改了宽度-->
          <template slot-scope="scope">
            <el-button @click="getfileList(scope.row)" type="text" size="small"><i class="el-icon-folder-opened"></i>查看文件</el-button>
            <el-button @click="printShow(scope.row)" type="text" size="small"><i class="el-icon-printer"></i>打印</el-button>
            <!--            增加查看详情按钮-->
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
            <p class="dialog-title">销售报价单信息</p>
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
              <el-select style="z-index:999;" v-model="gongYingShang.kehu" @change="changeKeHu()" clearable filterable placeholder="请选择客户">
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
              <el-select style="z-index:999;" v-model="gongYingShang.jiageDengji" clearable filterable placeholder="请选择价格等级">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_jiageDengji"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item" :rules="[{required:true,message:'业务员不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.yewuyuan" clearable filterable placeholder="请选择业务员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_User"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.gongyingshangDengji" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="10"><!-- ztt 修改宽度-->
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item" :rules="[{required:true,message:'店铺不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
              <el-select style="z-index:999;" v-model="gongYingShang.dianpu" clearable filterable placeholder="请选择店铺">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_DianPu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--             ztt 快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '店铺')">添加</el-button>
                <!--            ztt end-->
            </el-form-item>
  <!--              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp"></el-input>-->

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
          <!--         ztt 审核人列表修改-->
          <el-col :span="6">
            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shenhe" class="custom-login-inp" @click.native="seeShenHeRen"></el-input>
            </el-form-item>
          </el-col>
          <!--            ztt end-->
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
          <!--       ztt   增加销售单位-->
          <el-col :span="6">
            <el-form-item label="销售单位" prop="xiaoshoudanwei" class="custom-form-item" :rules="[{required:true,message:'销售单位不能为空',trigger: 'blur'}]"><!-- 设置必填字段 -->
              <el-select style="z-index:999;" v-model="gongYingShang.xiaoshoudanwei" clearable filterable placeholder="请选择销售单位">
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
                <el-select style="z-index:999;" v-model="gongYingShang.body[index].zhibaoDengji" clearable filterable placeholder="请选择质保等级">
                  <!-- types 为后端查询 -->
                  <el-option
                      v-for="item in XiaLa_ZhiBaoDengJi"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name">
                  </el-option>
                </el-select>
                <!--             ztt 快速跳转到配置项页-->
                <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                           @click="goToPeiZhi('/basePeizhi', '质保等级')">添加</el-button>
                <!--            ztt end-->
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhibaoDengji" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="单位" :prop="'body.' + index +'.danwei'" class="custom-form-item" :rules="[{required:true,message:'单位不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].danwei" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="数量" :prop="'body.' + index +'.shuliang'" class="custom-form-item" :rules="[{required:true,message:'数量不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
                <el-input ref="acc_inp" @change="changeValue(index,'shuliang')" v-model="gongYingShang.body[index].shuliang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="报价单价" :prop="'body.' + index +'.baojiaDanjia'" class="custom-form-item" :rules="[{required:true,message:'报价单价不能为空',trigger: 'blur'}]"><!--ztt 设置必填字段:rules -->
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
                       @click="delLianXiRen(index)"><i class="el-icon-delete"></i>删除
            </el-button>
          </el-row>
        </div>

        <el-row :gutter="15">
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addLianXiRen" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>添加商品
              </el-button>
            </div>
          </el-col>
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save" style="margin-left: 30px"><i class="el-icon-finished"></i>保存
              </el-button>
            </div>
          </el-col>
          <!--         ztt 审核按钮修改-->
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="saveAndShenHe" style="margin-left: 30px"><i class="el-icon-finished"></i>保存并提交审核
              </el-button>
            </div>
          </el-col>
          <!--            ztt end-->
          <el-col :span="5">
            <div style="display: flex">
              <el-button class="custom-login-button" type="primary"
                         @click="addClose" style="margin-left: 30px"><i class="el-icon-circle-close"></i>取消
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
          <el-button type="primary" @click="ProQuery()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="Prorefresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="queding()"><i class="el-icon-check"></i>确定</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#F2F5F7'}"
          :data="CaiGou_Product" :row-class-name="rowClassName" @row-click="rowClick" style="width: 100%"
          @selection-change="shoudongSelectChange">

<!--        <el-table-column-->
<!--            type="selection"-->
<!--            width="55">-->
<!--        </el-table-column>-->

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

    <el-dialog title="" :visible.sync="printDialog" width="50%">

      <el-form label-width="100px" class="demo-info">
          <el-form-item label="打印模板" prop="printName" class="custom-form-item">
            <el-select style="z-index:999;" v-model="printName" clearable filterable placeholder="请选择模板">
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
          <el-button  type="primary" @click="printStart()"><i class="el-icon-printer"></i>打印预览</el-button>
          <el-button  @click="printBack()"><i class="el-icon-circle-close"></i>取消</el-button>
        </span>
    </el-dialog>

    <!-- 文件上传 -->
    <el-dialog title="" :visible.sync="fileDialog" width="80%">
      <input ref="up_file" type="file" id="up_file" @change="uploadSelect()" style="display: none">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-button type="primary" :loading="downloadLoading" @click="upload()"><i class="el-icon-document-add"></i>文件上传</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          :data="FileList" style="width: 100%">
        <el-table-column
            prop="fileName"
            label="文件名"
            width="auto"
        ></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200"
            class="custom-column1">
          <template slot-scope="scope">
            <el-button :loading="downloadLoading" @click="downloadFile(scope.row)" type="text" size="small"><i class="el-icon-download"></i>下载</el-button>
            <el-button :loading="downloadLoading" @click="deleteFile(scope.row)" type="text" size="small"><i class="el-icon-delete"></i>删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-dialog>

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <span>请选择审核状态</span>
      <span slot="footer" class="dialog-footer">
        <el-button  type="primary" @click="shenheTrue"><i class="el-icon-check"></i>审核通过</el-button>
        <el-button  @click="shenheFalse"><i class="el-icon-close"></i>审核未通过</el-button>
      </span>
    </el-dialog>

    <!--查看详情窗口弹出-->
    <el-drawer
        title="" :visible.sync="drawer"  size="70%" :with-header="false" >
      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info" size="medium">
        <el-row :gutter="15">
          <el-col :span="5">
            <p class="dialog-title" style="margin-left: 30px">销售报价单信息</p>
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
            <el-form-item label="价格等级" prop="jiageDengji" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.jiageDengji" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.yewuyuan" class="custom-login-inp1" readonly></el-input>
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
            <el-form-item label="审核人" prop="shenhe" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.shenhe" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <!--        增加销售单位-->
          <el-col :span="10">
            <el-form-item label="销售单位" prop="xiaoshoudanwei" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoshoudanwei" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div >
        <!--        商品信息 -->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title" style="margin-left: 30px">商品信息</p>
          </el-col>
        </el-row>
        <el-table
            border
            :header-cell-style="{background:'#d6e5ef',color:'#000'}"
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
              prop="lishiZuigao"
              label="历史最高价"
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
          <el-table-column
              prop="jianyiBaojia"
              label="建议报价"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="xuyongRiqi"
              label="需用日期"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="baojiaFudong"
              label="报价浮动"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="beizhu"
              label="备注"
              width="180"
          ></el-table-column>
        </el-table>
      </div>
<!--      <div class="bottom" style="height: 300px"></div>-->
    </el-drawer>
    <!--ztt审核人信息-->
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
    <!--            ztt end-->

  </el-container>





</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
//ztt 导入跳转配置项的RouterUtil
import RouterUtil from "../utils/RouterU.js"
//ztt end
export default {
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      printName:'',
      XiaLa_MuBan:[],
      printDialog:false,
      fileDialog:false,
      downloadLoading:false,
      drawer:false,//详情窗口隐藏
      FileList:[],
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      dialogVisible:false,
      shenheButton:false,
      userInfo:'',
      userPower:'',
      p_index:'',
      start_date:'',
      stop_date:'',
      kehu:'',
      shenhe_zhuangtai:'',
      shenhe:'',
      Protype:'',
      Proname:'',
      p_list:[],
      users:[],//ztt审核人
      userList:false,//ztt审核人
      shenheMultipleSelection: [],//ztt审核人多选
      XiaLa_HeSuanDanWei:[],//ztt销售单位
      XiaLa_KeHu:[],
      XiaLa_User:[],
      XiaLa_DianPu:[],
      CaiGou_Product:[],
      XiaLa_GongYingShang:[],
      XiaLa_ZhiBaoDengJi:[],
      XiaLa_ShenHe:[],
      shoudongSelection: [],
      XiaLa_jiageDengji:[],
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
        dianpu: '',
        xiaoxiangShuilv: '',
        beizhu:'',
        shenhe:'',
        xiaoshoudanwei:'',//ztt销售单位
        jiageDengji:'',
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
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    console.log(window.innerHeight)
    this.getUser();
    this.getXiaoShouProduct();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_KeHu()
    this.getXiaLa_ShenHe();
    this.getXiaLa_DianPu();
    this.getXiaLa_ZhiBaoDengJi();
    this.getXiaLa_MuBan();
    this.getjianyiBaojia();
    this.getXiaLa_jiageDengji();
    this.getXiaLa_HeSuanDanWei();//ztt销售单位
    this.getXiaLa_User()
  },
  methods: {
    // ztt审核人修改
    seeShenHeRen(){
      if(this.userPower.xiaoshouBaojiaAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://yhocn.cn:8102/user/queryAllUsers"
      this.axios.post(url).then(res =>{
        if(res.data.code == 0){
          this.users = res.data.data
          console.log("ss")
          console.log(this.users)
          this.userList = true
        }
      })
    },
    //ztt end
    // ztt增加销售单位关联核算数据
    getXiaLa_HeSuanDanWei(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url,{"type":"核算单位"}).then(res =>{
        if(res.data.code == '00'){
          this.XiaLa_HeSuanDanWei = res.data.data;
          for (var i=0; i<this.XiaLa_HeSuanDanWei.length; i++){
            this.XiaLa_HeSuanDanWei[i].label = this.XiaLa_HeSuanDanWei.name
          }
          console.log("核算单位下拉已获取");
        } else {
          console.log("核算单位下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },
    //ztt end

    //ztt 跳转到相应配置项页
    goToPeiZhi(url,pageType){
      RouterUtil.to(url + '?type=' + pageType);
    },
    //ztt end
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

    shoudongSelectChange(val) {
      console.log("传1")
      this.shoudongSelection = val;
      console.log(val)
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },

    queding() {
      if(this.shoudongSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      var this_id = this.shoudongSelection[0].id

      console.log(this.shoudongSelection)
    },

    rowClassName: function ({ row }) {
      if (row.isselect == 0) {
        return "hidden-row";
      }
      return '';
    },

    // 查看详情页面弹出
    seeList(row){
      this.p_id=row.id
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/selectBaoJiaById"
      this.axios.post(url, {"id":row.id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.itemList
          this.gongYingShang = this_val
          console.log(res.data.data);
          console.log("获取成功");
          this.drawer = true;
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
    },

    changeKeHu(){
      var this_val = this.gongYingShang.kehu
      for(var i=0; i< this.XiaLa_KeHu.length; i++){
        if(this.XiaLa_KeHu[i].name == this_val){
          this.gongYingShang.jiageDengji = this.XiaLa_KeHu[i].jiageDengji
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
      if(this.userPower.xiaoshouBaojiaAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "BJ" + this_danhao
          this.gongYingShang = {
            bianhao:this_danhao,
            riqi: getNowDate(),
            kehu: '',
            yewuyuan: this.userInfo.name,
            dianpu: '',
            xiaoxiangShuilv: '',
            beizhu:'',
            shenhe:this.userInfo.shenpi,
            xiaoshoudanwei:'',//ztt销售单位
            jiageDengji:'',
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

          let url = "http://yhocn.cn:8102/peizhi/selectByPeiZhiId"
          this.axios.post(url,{"id":this.userInfo.dianpu}).then(res => {
            if(res.data.code == '00') {
              console.log(res.data.data[0].name)
              this.gongYingShang.dianpu = res.data.data[0].name
              console.log("默认店铺信息已获取");
            } else {
              console.log("账号店铺获取失败");
            }
          }).catch(() => {
            MessageUtil.error("网络异常");
          })

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

      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/selectBaoJiaById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          if(res.data.data.shenheZhuangtai == '审核未通过'){
            res.data.data.shenheZhuangtai = '审核中'
          }
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
      let url = "http://yhocn.cn:8102/product/selectXiaoShouProduct"
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
              // <!--    ztt 导航栏快速跳转未审核-->
          this.shuju = this.$route.query.baojiashenhegeshu
          console.log('判断查询审核:' + this.$route.query.baojiashenhegeshu)
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
      let url = "http://yhocn.cn:8102/gongYingShang/getAll"
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
      let url = "http://yhocn.cn:8102/customer/getAll"
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
      let url = "http://yhocn.cn:8102/user/getall"
      this.axios(url).then(res => {
          this.XiaLa_User = res.data
          for(var i=0; i<this.XiaLa_User.length; i++){
            this.XiaLa_User[i].label = this.XiaLa_User.name
          }
          console.log("业务员下拉已获取");
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_ShenHe(){
      let url = "http://yhocn.cn:8102/user/fuzzyQuery"
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
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
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

    getXiaLa_ZhiBaoDengJi(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
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

    getXiaLa_MuBan(){
      let url = "http://yhocn.cn:8102/printMuBan/getMuBanByType"
      this.axios.post(url, {"type":"销售报价单"}).then(res => {
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

    getjianyiBaojia(){
      let url = "http://yhocn.cn:8102/peiZhiShuiLv/getAll"
      this.axios(url, this.form).then(res => {
        if(res.data.code == '00') {
          this.ShuiLv = res.data.data[0];
          var fujia_shuilv = 1
          if(this.ShuiLv.zhuangtai == '是'){
            fujia_shuilv = fujia_shuilv + (this.ShuiLv.shuilv / 100)
          }
          for(var i=0; i<this.tableData.length; i++){
            var jinxiang = this.tableData[i].jinxiang / 100
            var xiaoxiang = this.tableData[i].xiaoxiang / 100
            var pifa_bili = this.tableData[i].pifaBili / 100
            var lingshou_bili = this.tableData[i].lingshouBili / 100
            var dakehu_bili = this.tableData[i].dakehuBili / 100
            var caigou_price = this.tableData[i].caigouPrice * 1
            this.tableData[i].lingshouPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * lingshou_bili)) * 100) / 100
            this.tableData[i].pifaPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * pifa_bili)) * 100 ) / 100
            this.tableData[i].dakehuPrice = Math.round((caigou_price * (1 + xiaoxiang * fujia_shuilv)) / ((1+jinxiang) * (1-(1+xiaoxiang*fujia_shuilv) * dakehu_bili)) * 100) / 100
          }
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_jiageDengji(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"价格等级"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_jiageDengji = res.data.data;
          for(var i=0; i<this.XiaLa_jiageDengji.length; i++){
            this.XiaLa_jiageDengji[i].label = this.XiaLa_jiageDengji.name
          }
          console.log("价格等级下拉已获取");
        } else {
          console.log("价格等级下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    //查询全部
    getAll(){
      this.shenheButton = false
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/getAll"
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
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/getAllByName"
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
      this.start_date = ''
      this.stop_date = ''
      this.kehu = ''
      this.shenhe = ''
      this.shenheButton = false
      if(this.userPower.xiaoshouBaojiaSel == '查看全部'){
        this.getAll();
      }else if(this.userPower.xiaoshouBaojiaSel == '查看个人'){
        this.getAllByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    query(){
      this.shenheButton = false
      if(this.userPower.xiaoshouBaojiaSel == '查看全部'){
        this.queryList();
      }else if(this.userPower.xiaoshouBaojiaSel == '查看个人'){
        this.queryListByName();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
      this.shenheButton = false
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
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/queryList"
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

    //条件查询
    queryListByName(){
      this.shenheButton = false
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
        yewuyuan:this.userInfo.name,
      }
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/queryListByName"
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
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/shenheList"
      this.axios.post(url, {"name":this.userInfo.name}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
          //ztt 审核
          var shenheName = []
          var shenheList = []
          for(var i=this.tableData.length - 1; i>= 0; i--) {
            console.log("xunhuan")
            console.log(this.tableData)
            if (this.tableData[i].shenhe != undefined) {
              var this_arr = this.tableData[i].shenhe.split(",")
            }
            console.log("a")
            console.log(this_arr)
            if (this.tableData[i].shenheList != undefined) {
              var shenhe_arr = this.tableData[i].shenheList.split(",")
            }
            console.log("s")
            console.log(shenhe_arr)
            for (var j = 0; j < this_arr.length; j++) {
              shenheName.push(this_arr[j])
              console.log("z")
            }

            for (var j = 0; j < shenhe_arr.length; j++) {
              shenheList.push(shenhe_arr[j])
              console.log("x")
            }
            var panduan = false
            var xiabiao = ""
            for (var j = 0; j < this_arr.length; j++) {
              if (this_arr[j] == this.userInfo.name) {
                panduan = true
                xiabiao = j
                console.log("c")
              }
            }

            if (panduan == false) {
              this.tableData.splice(i, 1)
              console.log("v")
            } else if (xiabiao != "" || xiabiao >= 0) {
              if (shenhe_arr[xiabiao] == "审核通过") {
                this.tableData.splice(i, 1)
                console.log("b")
              }
            }
            //ztt end
            this.total = res.data.data.length;
          }
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
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/baoJiaAdd"
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
      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/baoJiaUpd"
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
      if(this.userPower.xiaoshouBaojiaUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
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
      if(this.gongYingShang.shenheren == ''){
        MessageUtil.error('请选择审核人');
        return;
      }
      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].shangpinBianma == ''){
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
      if(this.userPower.xiaoshouBaojiaDel != '是'){
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
        let url = "http://yhocn.cn:8102/xiaoShouBaoJia/delXiaoShouBaoJia";
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

      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/baoJiaShenHe";
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

      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/baoJiaShenHe";
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
    // 审核人信息多选
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
      let url = "http://yhocn.cn:8102/fileTable/getAll"
      this.axios.post(url, {"id":row.id,"type":"销售报价单"}).then(res => {
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
      let url = "http://yhocn.cn:8102/fileTable/getAll"
      this.axios.post(url, {"id":this.p_id,"type":"销售报价单"}).then(res => {
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
      let url = "http://yhocn.cn:8102/fileTable/getById"
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
      let url = "http://yhocn.cn:8102/fileTable/deleteById"
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
          "type": "销售报价单",
        };
        let url = "http://yhocn.cn:8102/fileTable/fileAdd"
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

      let url = "http://yhocn.cn:8102/xiaoShouBaoJia/selectBaoJiaById"
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

html,body,#app {
  height: 100%;
  margin: 0;
  padding: 0;
}
/deep/.custom-column1{
  color:#000!important;
  background:#d6e5ef!important;
}
</style>