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
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()"><i class="el-icon-circle-plus-outline"></i>添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updUser()"><i class="el-icon-edit"></i>编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteClick()"><i class="el-icon-delete"></i>删除</el-button>
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
            prop="dianpu"
            label="店铺"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            prop="cangku"
            label="仓库"
            width="200"
            show-overflow-tooltip>
        </el-table-column>

        <!-- 12.5 -->
        <el-table-column
            prop="chukuDanwei"
            label="出库单位"
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
            prop="beizhu"
            label="备注"
            width="200"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope">
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
            <p class="dialog-title">出库单信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="订单编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="销售单号" prop="xiaoshouId" class="custom-form-item">
              <el-input ref="acc_inp" @click.native="selectCaiGouDan()" v-model="gongYingShang.xiaoshouId" class="custom-login-inp" readonly="true" placeholder="点击选择销售单"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="日期" prop="riqi" class="custom-form-item">
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
            <el-form-item label="客户" prop="kehu" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.kehu" clearable filterable placeholder="请选择客户">
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
          <el-col :span="10"><!-- ztt 修改宽度-->
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item">
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
<!--              ztt end-->
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="10"><!-- ztt 修改宽度-->
            <el-form-item label="仓库" prop="cangku" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.cangku" clearable filterable placeholder="请选择仓库">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_CangKu"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--            ztt  快速跳转到配置项页-->
              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                         @click="goToPeiZhi('/basePeizhi', '仓库')">添加</el-button>
<!--              ztt end-->
<!--              <el-input ref="acc_inp" v-model="gongYingShang.jinxiangShuilv" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>

          <!--          12.5-->
          <el-col :span="6">
            <el-form-item label="出库单位" prop="chukuDanwei" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.chukuDanwei" clearable filterable placeholder="请选择出库单位">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_chukuDanwei"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
              <!--              <el-input ref="acc_inp" v-model="gongYingShang.jinxiangShuilv" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="业务员" prop="yewuyuan" class="custom-form-item">
              <el-select style="z-index:999;" v-model="gongYingShang.yewuyuan" clearable filterable placeholder="请选择业务员">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_ShenHe"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
<!--              <el-input ref="acc_inp" v-model="gongYingShang.yewuyuan" class="custom-login-inp"></el-input>-->
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <div v-for="(item, index) in gongYingShang.body">
          <!--        商品信息-->
          <el-row :gutter="15" type="flex" align="middle">
            <el-col :span="6">
              <p class="dialog-title">商品信息{{index + 1}}</p>
            </el-col>
            <el-col :span="6">
              <el-button v-if="gongYingShang.body.length > 1" @click="delLianXiRen(index)" type="danger" icon="el-icon-delete" circle></el-button>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="6">
              <el-form-item label="商品编码" prop="shangpinBianma" class="custom-form-item" >
                <el-input ref="acc_inp" readonly="true" @click.native="selectProduct(index)" v-model="gongYingShang.body[index].shangpinBianma" class="custom-login-inp" placeholder="点击选择商品"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="名称" prop="name" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].name" class="custom-login-inp"></el-input>
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
            <el-col :span="10"><!--  ztt修改宽度-->
<!--              <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"-->
<!--                         @click="goToPeiZhi('/basePeizhi', '质保等级')">添加</el-button>-->
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
                <!--          ztt    快速跳转到配置项页-->
                <el-button style="width: 40px;height: 40px;padding-left:5px;background-color: #57a8f5;color:#ffffff"
                           @click="goToPeiZhi('/basePeizhi', '质保等级')">添加</el-button>
                <!--            ztt end-->
<!--                <el-input ref="acc_inp" v-model="gongYingShang.body[index].zhibaoDengji" class="custom-login-inp"></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="单位" prop="danwei" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].danwei" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="数量" prop="shuliang" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index)" v-model="gongYingShang.body[index].shuliang" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="销售单价" prop="xiaoshouDanjia" class="custom-form-item">
                <el-input ref="acc_inp" @change="changeValue(index)" v-model="gongYingShang.body[index].xiaoshouDanjia" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="价税小计" prop="jiashuiXiaoji" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].jiashuiXiaoji" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="备注" prop="beizhu" class="custom-form-item">
                <el-input ref="acc_inp" v-model="gongYingShang.body[index].beizhu" class="custom-login-inp"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="addLianXiRen" style="margin-left: 30px"><i class="el-icon-circle-plus-outline"></i>添加商品
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button"  type="primary"
                         @click="save" style="margin-left: 30px"><i class="el-icon-finished"></i>保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
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
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
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
            prop="zuidijia"
            label="历史最低"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="jinxiang"
            label="进项税率"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="xiaoxiang"
            label="销项税率"
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

    <el-dialog title="" :visible.sync="selCaiGouDan" width="80%">

      <el-row :gutter="15">
        <el-col :span="5">
          <el-date-picker
              style="width:100%"
              v-model="caigou_start"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择起始日期">
          </el-date-picker>
        </el-col>
        <el-col :span="5">
          <el-date-picker
              style="width:100%"
              v-model="caigou_stop"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择结束日期">
          </el-date-picker>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="客户" v-model="caigou_kehu" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="CaiGouQuery()"><i class="el-icon-search"></i>查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" @click="CaiGourefresh()"><i class="el-icon-refresh"></i>刷新</el-button>
        </el-col>
      </el-row>

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
          :data="CaiGouDan" :row-class-name="rowClassName2" @row-click="CaiGouDanClick" style="width: 100%">
        <el-table-column
            prop="bianhao"
            label="编号"
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
            prop="dianpu"
            label="店铺"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="xiaoxiangShuilv"
            label="销项税率"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="beizhu"
            label="备注"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="shenhe"
            label="审核人"
            width="180"
        ></el-table-column>
        <el-table-column
            prop="shenheZhuangtai"
            label="审核状态"
            width="180"
        ></el-table-column>
      </el-table>

    </el-dialog>

    <el-dialog title="" :visible.sync="selKaiPiao" width="80%">

      <el-table
          border
          :header-cell-style="{background:'#d6e5ef',color:'#000'}"
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
        <el-button  type="primary" @click="kaipiaoNext()"><i class="el-icon-check"></i>确定</el-button>
        <el-button  @click="selKaiPiao=false"><i class="el-icon-close"></i>取消</el-button>
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
              <el-select style="z-index:999;" ref="acc_inp" v-model="kaipiaoBody.kaipiaoDanwei" class="custom-login-inp1" placeholder="请选择">
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
              <el-select style="z-index:999;" ref="acc_inp" v-model="kaipiaoBody.xinxiTuisong" class="custom-login-inp1" placeholder="请选择">
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
        <el-button  type="primary" @click="kaipiaoSure()"><i class="el-icon-check"></i>确定</el-button>
        <el-button  @click="selKaiPiaoConfirm=false"><i class="el-icon-close"></i>取消</el-button>
      </span>

    </el-dialog>

    <el-dialog
        title=""
        :visible.sync="dingjinUseVisible"
        width="30%"
        >
      <el-row :gutter="15">
        <el-col :span="24">
          <el-form :model="dingjinUseAdd" ref="addUsr" label-width="100px" class="demo-info" size="medium">
            <el-form-item label="抵减订金" prop="dingjinUseAdd" class="custom-form-item">
              <el-input ref="acc_inp" @input="dingjinInput" v-model="dingjinUseAdd" class="custom-login-inp1"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dingjinUseVisible = false"><i class="el-icon-close"></i>取 消</el-button>
        <el-button type="primary" @click="dingjinUseSave()"><i class="el-icon-check"></i>确 定</el-button>
      </span>
    </el-dialog>

    <!--    详情窗口弹出-->
    <el-drawer
        title="" :visible.sync="drawer" size="70%" :with-header="false">
      <el-form :model="gongYingShang" ref="addUsr" label-width="100px"
               class="demo-info" size="medium">
        <el-row :gutter="15">
          <el-col :span="5">
            <p class="dialog-title" style="margin-left: 30px">出库单信息</p>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="20">
            <p style="margin-left: 30px">本单欠款：{{qiankuan}}，抵减订金：{{dingjinUse}}，订单总额：{{shangpin_money}}，订金总额：{{dingjinSum}}，订金余额：{{dingjinYue}}</p>
          </el-col>
          <el-col :span="4">
            <p class="dialog-title">
              <el-button class="dialog-title" size="small" round type="primary" @click="dingjinUseClick()">订金抵欠款</el-button>
            </p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="10">
            <el-form-item label="订单编号" prop="bianhao" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.bianhao" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="销售单号" prop="xiaoshouId" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.xiaoshouId" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="日期" prop="riqi" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.riqi" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="客户" prop="kehu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.kehu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="店铺" prop="dianpu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.dianpu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="仓库"prop="cangku" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.cangku" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="备注" prop="beizhu" class="custom-form-item">
              <el-input ref="acc_inp" v-model="gongYingShang.beizhu" class="custom-login-inp1" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div>
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
            :header-cell-style="{background:'#d6e5ef',color:'#000'}"
            :data="gongYingShang.body" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="shangpinBianma"
              label="商品编码"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="name"
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
              prop="xiaoshouDanjia"
              label="销售单价"
              width="180"
          ></el-table-column>
          <el-table-column
              prop="jiashuiXiaoji"
              label="价税小计"
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
            :header-cell-style="{background:'#d6e5ef',color:'#000'}"
            :data="gongYingShang.shoukuan" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="shouzhiBianhao"
              label="收款单号"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="shouzhiRiqi"
              label="日期"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="shoufuType"
              label="收款类别"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="money"
              label="金额"
              width="auto"
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
            :header-cell-style="{background:'#d6e5ef',color:'#000'}"
            :data="gongYingShang.kaipiao" :row-class-name="rowClassName" @row-click="rowClick" style="width: 94%;margin-left: 30px">
          <el-table-column
              prop="xiaoshouBianhao"
              label="出库单号"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="kaipiaoRiqi"
              label="开票日期"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="jiashuiHeji"
              label="金额"
              width="auto"
          ></el-table-column>
          <el-table-column
              prop="beizhu"
              label="备注"
              width="auto"
          ></el-table-column>
        </el-table>
      </div>


      <div class="bottom" style="height: 50px"></div>
    </el-drawer>


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
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      caigou_start: "",
      caigou_stop: "",
      caigou_kehu: "",
      p_index:'',
      start_date:'',
      stop_date:'',
      kehu:'',
      shenhe_zhuangtai:'',
      shenhe:'',
      Protype:'',
      Proname:'',
      CaiGouDan:[],
      XiaLa_CangKu:[],
      XiaLa_KeHu:[],
      XiaLa_DianPu:[],
      CaiGou_Product:[],
      XiaLa_GongYingShang:[],
      XiaLa_ShenHe:[],
      XiaLa_ZhiBaoDengJi:[],
      XiaLa_chukuDanwei:[],
      xukaiList:[],
      XiaLa_HeSuanDanWei:[],
      kaipiaoBody:{},
      dingjinUse:0,
      dingjinUseAdd:0,
      drawer:false,//详情窗口隐藏设置
      dingjinUseVisible:false,
      selKaiPiao: false,
      selKaiPiaoConfirm: false,
      chukuDanwei:'',
      yewuyuan:'',
      qiankuan:0,
      shangpin_money:0,
      dingjinSum:0,
      dingjinYue:0,
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
        bianhao:'',
        riqi: '',
        kehu: '',
        dianpu: '',
        cangku: '',
        beizhu: '',
        xiaoshouId:'',
        chukuDanwei:'',
        yewuyuan:'',
        body:[
          {
            shangpinBianma:'',
            name:'',
            guige:'',
            caizhi:'',
            jishuBiaozhun:'',
            zhibaoDengji:'',
            danwei:'',
            shuliang:'',
            xiaoshouDanjia:'',
            jiashuiXiaoji:'',
            beizhu:'',
          }
        ]
      },
      addDialog: false,
      updDialog: false,
      selProduct: false,
      selCaiGouDan: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
    this.getCaiGouProduct();
    this.getCaiGouDan();
    this.getXiaLa_GongYingShang();
    this.getXiaLa_KeHu();
    this.getXiaLa_DianPu();
    this.getXiaLa_CangKu();
    this.getXiaLa_MuBan();
    this.getXiaLa_ZhiBaoDengJi();
    this.getXiaLa_chukuDanwei();
    this.getXiaLa_ShenHe();
    this.getXiaLa_HeSuanDanWei();
  },
  methods: {
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

    rowClassName2: function ({ row }) {
      if (row.isselect == 0) {
        return "hidden-row";
      }
      return '';
    },

    dingjinUseClick(){
      var this_use = this.dingjinUse
      if(this_use == 0){
        if(this.qiankuan > this.dingjinYue){
          this_use = this.dingjinYue
        }else{
          this_use = this.qiankuan
        }
      }
      this.dingjinUseAdd = this_use
      this.dingjinUseVisible = true;
    },

    dingjinUseSave(){
      console.log(this.dingjinUseAdd)
      if(this.dingjinUseAdd != "" && this.dingjinUseAdd != null){
        let url="http://yhocn.cn:8102/xiaoShouChuKu/chuKuDingJinUpd"
        this.axios.post(url,{'id':this.gongYingShang.id,'dingjin':this.dingjinUseAdd}).then(res=>{
          if(res.data.code=='00'){
            MessageUtil.success("完成");
            this.drawer=false;
            this.dingjinUseVisible=false;
          }else {
            MessageUtil.error("获取失败");
          }
        }).catch(()=>{
          MessageUtil.error("网络异常");
        })
      }
    },

    shoukuan_add(){
      if(this.qiankuan <= 0){
        MessageUtil.error("此订单欠款已结清");
        return;
      }
      var yukuan = this.qiankuan
      var dingdan = this.gongYingShang
      RouterUtil.to('/shourujilu?shoufu_type=收欠款&yukuan=' + yukuan + '&dingdan=' + JSON.stringify(dingdan));
    },

    dingjinInput(){
      if(this.dingjinUseAdd * 1 > this.shangpin_money){
        this.dingjinUseAdd = this.shangpin_money
      }
    },

    kaipiao_add(){
      var bianhao = this.gongYingShang.bianhao
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/xiangQingXuKaiPiao"
      this.axios.post(url, {'bianhao':bianhao}).then(res => {
        if(res.data.code == '00') {
          var xukaipiao = res.data.xukaipiao
          if(xukaipiao.length < 1){
            MessageUtil.error("出库单已全部开票");
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
      let url="http://yhocn.cn:8102/kaiPiao/kaiPiaoListAdd"
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

    seeList(row){
      this.p_id=row.id
      let url="http://yhocn.cn:8102/xiaoShouChuKu/selectXiangQingById"
      this.axios.post(url,{'id':row.id,'bianhao':row.bianhao}).then(res=>{
        if(res.data.code=='00'){
          console.log(res.data)
          var this_val = res.data.dingdan[0]
          this_val.body = res.data.shangpin
          this.shangpin_money = 0
          for(var i=0; i<res.data.shangpin.length; i++){
            this.shangpin_money = this.shangpin_money + Math.round(res.data.shangpin[i].jiashuiXiaoji * 100) / 100
          }
          this_val.shoukuan = res.data.shoukuan
          var shoukuan_money = 0
          for(var i=0; i<res.data.shoukuan.length; i++){
            shoukuan_money = shoukuan_money + Math.round(res.data.shoukuan[i].money * 100) / 100
          }
          this_val.kaipiao = res.data.kaipiao
          this_val.dingjin = res.data.dingjin
          this_val.dingjinyiyong = res.data.dingjinyiyong
          this.dingjinUse = res.data.dingdan[0].dingjinUse * 1
          this.dingjinSum = res.data.dingjin[0].dingjin * 1
          this.yiyong = res.data.dingjinyiyong[0].yiyong * 1
          this.dingjinYue = this.dingjinSum - this.yiyong
          this.qiankuan = this.shangpin_money - this.dingjinUse - shoukuan_money
          this.gongYingShang = this_val
          console.log(res.data.data);
          console.log("获取成功");
          this.drawer=true;
        }else {
          MessageUtil.error("获取失败");
        }
      }).catch(()=>{
        MessageUtil.error("网络异常");
      })
    },

    CaiGouDanClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.kehu = row.kehu
        this.gongYingShang.dianpu = row.dianpu
        this.gongYingShang.xiaoshouId = row.bianhao
        var id = row.id
        let url = "http://yhocn.cn:8102/xiaoShouDingDan/selectXiaoShouById"
        this.axios.post(url, {"id":id}).then(res => {
          if(res.data.code == '00') {
            var this_val = res.data.data
            var this_list = []
            for(var i=0; i<this_val.itemList.length; i++){
              this_list.push({
                shangpinBianma:this_val.itemList[i].shangpinBianhao,
                name:this_val.itemList[i].shangpinMingcheng,
                guige:this_val.itemList[i].guige,
                caizhi:this_val.itemList[i].caizhi,
                jishuBiaozhun:this_val.itemList[i].jishuBiaozhun,
                zhibaoDengji:this_val.itemList[i].zhibaoDengji,
                danwei:this_val.itemList[i].danwei,
                shuliang:this_val.itemList[i].shuliang,
                xiaoshouDanjia:this_val.itemList[i].baojiaDanjia,
                jiashuiXiaoji:this_val.itemList[i].jiashuiXiaoji,
                beizhu:'',
              })
            }

            this.gongYingShang.body = this_list
            console.log(res.data.data);
            console.log("获取成功");
            this.selCaiGouDan = false;
          } else {
            MessageUtil.error("获取失败");
          }
        }).catch(() => {
          MessageUtil.error("网络异常");
        })

        this.gongYingShang.body[this.p_index].caizhi = row.caizhi
        this.selProduct = false;
      }).catch(() => {
      });
    },


    rowClick(row,column,event){
      console.log(row)
      this.$confirm('是否选择此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.gongYingShang.body[this.p_index].shangpinBianma = row.bianhao
        this.gongYingShang.body[this.p_index].name = row.name
        this.gongYingShang.body[this.p_index].guige = row.guige
        this.gongYingShang.body[this.p_index].caizhi = row.caizhi
        this.gongYingShang.body[this.p_index].jishuBiaozhun = row.jishuBiaozhun
        this.gongYingShang.body[this.p_index].zhibaoDengji = row.zhibaoDengji
        this.gongYingShang.body[this.p_index].danwei = row.danwei
        // this.gongYingShang.body[this.p_index].shuliang
        // this.gongYingShang.body[this.p_index].caigouDanjia = row.caigouPrice
        // this.gongYingShang.body[this.p_index].jiashuiXiaoji
        // this.gongYingShang.body[this.p_index].jiaohuoRiqi
        // this.gongYingShang.body[this.p_index].beizhu
        this.selProduct = false;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

    changeValue(index){
      console.log(index)
      this.gongYingShang.body[index].jiashuiXiaoji = Math.round(this.gongYingShang.body[index].caigouDanjia * this.gongYingShang.body[index].shuliang * 100) / 100
    },

    CaiGouQuery(){
      for(var i=0; i<this.CaiGouDan.length;i++){
        var panduan = true
        console.log(i)
        if(this.caigou_start != ''){
          if(this.CaiGouDan[i].riqi < this.caigou_start){
            panduan = false
          }
        }
        if(this.caigou_stop != ''){
          if(this.CaiGouDan[i].riqi > this.caigou_stop){
            panduan = false
          }
        }
        if(this.caigou_kehu != ''){
          if(this.CaiGouDan[i].kehu.indexOf(this.caigou_kehu) == -1){
            panduan = false
          }
        }
        if(panduan){
          this.CaiGouDan[i].isselect = 1
        }else{
          this.CaiGouDan[i].isselect = 0
        }
      }
    },

    CaiGourefresh(){
      for(var i=0; i<this.CaiGouDan.length;i++){
        this.CaiGouDan[i].isselect = 1
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
      if(this.userPower.xiaoshouChukuAdd != '是'){
        MessageUtil.error("无添加权限");
        return;
      }
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/selectMaxDanHao"
      this.axios.post(url, {}).then(res => {
        if(res.data.code == '00') {
          var this_danhao = Math.trunc(res.data.data[0].bianhao)
          console.log(this_danhao)
          this_danhao = PrefixInteger(this_danhao,6)
          console.log(this_danhao)
          this_danhao = "CK" + this_danhao
          var chukuDanwei = ""
          for(var i=0; i<this.XiaLa_HeSuanDanWei.length; i++){
            if(this.XiaLa_HeSuanDanWei[i].id == this.userInfo.hesuanDanwei){
              chukuDanwei = this.XiaLa_HeSuanDanWei[i].name
            }
          }
          var cangku = ""
          for(var i=0; i<this.XiaLa_CangKu.length; i++){
            if(this.XiaLa_CangKu[i].id == this.userInfo.cangku){
              cangku = this.XiaLa_CangKu[i].name
            }
          }
          this.gongYingShang = {
            bianhao:this_danhao,
            riqi: getNowDate(),
            kehu: '',
            dianpu: '',
            cangku: cangku,
            beizhu: '',
            xiaoshouId: '',
            chukuDanwei:chukuDanwei,
            yewuyuan:this.userInfo.name,
            body:[
              {
                shangpinBianma:'',
                name:'',
                guige:'',
                caizhi:'',
                jishuBiaozhun:'',
                zhibaoDengji:'',
                danwei:'',
                shuliang:'',
                xiaoshouDanjia:'',
                jiashuiXiaoji:'',
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


    },

    addClose(){
      this.addDialog = false;
    },

    selectProduct(index){
      console.log(index)
      this.p_index = index
      this.Proname = ""
      this.Protype = ""
      for(var i=0; i<this.CaiGou_Product.length; i++){
        this.CaiGou_Product[i].isselect = 1
      }
      this.selProduct = true;
    },

    selectCaiGouDan(index){
      console.log(index)
      this.caigou_start = ""
      this.caigou_stop = ""
      this.caigou_kehu = ""
      for(var i=0; i<this.CaiGouDan.length; i++){
        this.CaiGouDan[i].isselect = 1
      }
      this.selCaiGouDan = true;
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

      let url = "http://yhocn.cn:8102/xiaoShouChuKu/selectByChuKuId"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          console.log(res.data.data)
          var this_val = res.data.data
          this_val.body = this_val.chuKuItem
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

    getCaiGouProduct(){
      let url = "http://yhocn.cn:8102/product/selectCaiGouProduct"
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.CaiGou_Product = res.data.data;
          console.log(this.CaiGou_Product)
          console.log("入库商品列表已获取");
        } else {
          console.log("入库商品列表获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getCaiGouDan(){
      let url = "http://yhocn.cn:8102/xiaoShouDingDan/getAllYiShen"//ztt修改url
      this.axios(url).then(res => {
        if(res.data.code == '00') {
          this.CaiGouDan = res.data.data;
          console.log(this.CaiGouDan)
          console.log("销售单列表已获取");
        } else {
          console.log("销售单列表获取失败");
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
          if(this.userPower.xiaoshouChukuSel == '是'){
            this.getAll();
          }else{
            MessageUtil.error("无查询权限");
          }

          var dingdan = this.$route.query.dingdan;
          var shangpin = this.$route.query.shangpin;
          if(dingdan != undefined){
            dingdan = JSON.parse(dingdan)
            shangpin = JSON.parse(shangpin)
            console.log(dingdan)
            console.log(shangpin)
            let url = "http://yhocn.cn:8102/xiaoShouChuKu/selectMaxDanHao"
            this.axios.post(url, {}).then(res => {
              if(res.data.code == '00') {
                var this_danhao = Math.trunc(res.data.data[0].bianhao)
                console.log(this_danhao)
                this_danhao = PrefixInteger(this_danhao,6)
                console.log(this_danhao)
                this_danhao = "CK" + this_danhao

                this.gongYingShang = {
                  bianhao:this_danhao,
                  riqi: getNowDate(),
                  kehu: dingdan.kehu,
                  dianpu: dingdan.dianpu,
                  cangku: '',
                  beizhu: '',
                  xiaoshouId: dingdan.bianhao,
                  body:[
                  ]
                }

                for(var i=0; i<shangpin.length; i++){
                  this.gongYingShang.body.push(
                      {
                        shangpinBianma:shangpin[i].shangpinBianhao,
                        name:shangpin[i].shangpinMingcheng,
                        guige:shangpin[i].guige,
                        caizhi:shangpin[i].caizhi,
                        jishuBiaozhun:shangpin[i].jishuBiaozhun,
                        zhibaoDengji:shangpin[i].zhibaoDengji,
                        danwei:shangpin[i].danwei,
                        shuliang:shangpin[i].weichuShuliang,
                        xiaoshouDanjia:shangpin[i].baojiaDanjia,
                        jiashuiXiaoji:shangpin[i].jiashuiXiaoji,
                        beizhu:'',
                      }
                  )
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
          }

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

    getXiaLa_CangKu(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"仓库"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_CangKu = res.data.data;
          for(var i=0; i<this.XiaLa_CangKu.length; i++){
            this.XiaLa_CangKu[i].label = this.XiaLa_CangKu.name
          }
          console.log("仓库下拉已获取");
        } else {
          console.log("仓库下拉获取失败");
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    },

    getXiaLa_MuBan(){
      let url = "http://yhocn.cn:8102/printMuBan/getMuBanByType"
      this.axios.post(url, {"type":"销售出库单"}).then(res => {
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

    getXiaLa_chukuDanwei(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
      this.axios.post(url, {"type":"核算单位"}).then(res => {
        if(res.data.code == '00') {
          this.XiaLa_chukuDanwei = res.data.data;
          for(var i=0; i<this.XiaLa_chukuDanwei.length; i++){
            this.XiaLa_chukuDanwei[i].label = this.XiaLa_chukuDanwei.name
          }
          console.log("质保等级下拉已获取");
        } else {
          console.log("质保等级下拉获取失败");
        }
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

    getXiaLa_HeSuanDanWei(){
      let url = "http://yhocn.cn:8102/peizhi/queryPeiZhi"
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
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/getAll"
      this.axios(url, this.form).then(res => {
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
      if(this.userPower.xiaoshouChukuSel == '是'){
        this.getAll();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    query(){
      this.shenheButton = false
      if(this.userPower.xiaoshouChukuSel == '是'){
        this.queryList();
      }else{
        MessageUtil.error("无查询权限");
      }
    },

    //条件查询
    queryList(){
      var start_date = this.start_date
      var stop_date = this.stop_date
      if(start_date == '' || start_date == null){
        start_date = '1900-01-01'
      }
      if(stop_date == '' || stop_date == null){
        stop_date = '2100-12-31'
      }
      var date = {
        start_date:start_date,
        stop_date:stop_date,
        kehu:this.kehu,
      }
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/queryList"
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
        shangpinBianma:'',
        name:'',
        guige:'',
        caizhi:'',
        jishuBiaozhun:'',
        zhibaoDengji:'',
        danwei:'',
        shuliang:'',
        caigouDanjia:'',
        jiashuiXiaoji:'',
        beizhu:'',
      })
      this.gongYingShang.body = this_lianxiren
    },

    selectLeiXing(index,value){
      console.log(index)
      console.log(value)
      console.log(this.gongYingShang.body)
      if(this.gongYingShang.body[index].type == '主联系人'){
        for(var i=0; i<this.gongYingShang.body.length; i++){
          if(i != index){
            this.gongYingShang.body[i].type = "联系人"
          }
        }
      }
    },

    saveGongYingShang(){
      var save_list = this.gongYingShang
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/xiaoShouChuKuAdd"
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
      let url = "http://yhocn.cn:8102/xiaoShouChuKu/xiaoShouChuKuUpd"
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

      if(this.userPower.xiaoshouChukuUpd != '是' && (this.gongYingShang.id != undefined && this.gongYingShang.id != null)){
        MessageUtil.error("无修改权限");
        return;
      }

      if(this.gongYingShang.kehu == ''){
        MessageUtil.error("请选择客户");
        return;
      }
      if(this.gongYingShang.dianpu == ''){
        MessageUtil.error("请选择店铺");
        return;
      }
      if(this.gongYingShang.cangku == ''){
        MessageUtil.error("请选择仓库");
        return;
      }

      if(this.gongYingShang.chukuDanwei == ''){
        MessageUtil.error("请填写出库单位");
        return;
      }
      if(this.gongYingShang.yewuyuan == ''){
        MessageUtil.error("请填写业务员");
        return;
      }
      if(this.gongYingShang.xiaoshouId == ''){
        MessageUtil.error("请选择销售单号");
        return;
      }

      for(var i=0; i<this.gongYingShang.body.length; i++){
        if(this.gongYingShang.body[i].shangpinBianma == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未选择商品');
          return;
        }

        if(this.gongYingShang.body[i].danwei == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写单位');
          return;
        }

        if(this.gongYingShang.body[i].shuliang == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写数量');
          return;
        }
        if(this.gongYingShang.body[i].xiaoshouDanjia == ''){
          MessageUtil.error('第' + (i * 1+1) + '条商品未填写单价');
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
      if(this.userPower.xiaoshouChukuDel != '是'){
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
        let url = "http://yhocn.cn:8102/xiaoShouChuKu/delXiaoShouChuKu";
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

      let url = "http://yhocn.cn:8102/xiaoShouChuKu/selectByChuKuId"
      this.axios.post(url, {"id":this.p_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.details = this_val.chuKuItem
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