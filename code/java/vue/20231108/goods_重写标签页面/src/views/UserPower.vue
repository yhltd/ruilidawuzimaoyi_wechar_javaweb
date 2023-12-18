<template>
  <el-container style="height: 100%;" direction="vertical">

    <el-header style="background-color: transparent;">
      <el-row :gutter="15">
        <el-col :span="3">
          <el-input placeholder="权限名" v-model="name" class="input-with-select">
          </el-input>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="query()">查询</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="refresh()">刷新</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="addUser()">添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="primary" @click="updUser()">编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button size="small" round type="danger" @click="deleteClick()">删除</el-button>
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
            prop="name"
            label="权限名"
            width="auto">
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

      <el-form :model="Power" ref="addUsr" label-width="100px"
               class="demo-info">

        <!--        权限信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">权限信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="权限名" prop="shoujianName" class="custom-form-item">
              <el-input ref="acc_inp" v-model="Power.name" class="custom-login-inp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        账号管理-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">账号管理</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="zhanghaoguanliAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhanghaoguanliAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="zhanghaoguanliDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhanghaoguanliDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="zhanghaoguanliUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhanghaoguanliUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="zhanghaoguanliSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhanghaoguanliSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        权限管理-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">权限管理</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="quanxianAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.quanxianAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="quanxianDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.quanxianDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="quanxianUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.quanxianUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="quanxianSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.quanxianSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        客户资料-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">客户资料</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="kehuAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.kehuAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="kehuDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.kehuDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="kehuUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.kehuUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="kehuSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.kehuSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        供应商资料-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">供应商资料</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="gongyingshangAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.gongyingshangAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="gongyingshangDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.gongyingshangDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="gongyingshangUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.gongyingshangUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="gongyingshangSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.gongyingshangSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        商品资料-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">商品资料</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="shangpinAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shangpinAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="shangpinDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shangpinDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="shangpinUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shangpinUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="shangpinSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shangpinSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        附加税设置-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">附加税设置</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="修改" prop="fujiashuiUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.fujiashuiUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="fujiashuiSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.fujiashuiSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        配置信息-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">配置信息</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="peizhiAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.peizhiAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="peizhiDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.peizhiDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="peizhiUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.peizhiUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="peizhiSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.peizhiSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        销售报价-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售报价</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="xiaoshouBaojiaAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouBaojiaAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="xiaoshouBaojiaDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouBaojiaDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="xiaoshouBaojiaUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouBaojiaUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="xiaoshouBaojiaSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouBaojiaSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian3"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        销售订单-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售订单</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="xiaoshouDingdanAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouDingdanAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="xiaoshouDingdanDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouDingdanDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="xiaoshouDingdanUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouDingdanUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="xiaoshouDingdanSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouDingdanSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian3"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        销售出库-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售出库</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="xiaoshouChukuAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouChukuAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="xiaoshouChukuDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouChukuDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="xiaoshouChukuUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouChukuUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="xiaoshouChukuSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouChukuSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        销售开票-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">销售开票</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="xiaoshouKaipiaoAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouKaipiaoAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="xiaoshouKaipiaoDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouKaipiaoDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="xiaoshouKaipiaoUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouKaipiaoUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="xiaoshouKaipiaoSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.xiaoshouKaipiaoSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        收入记录-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">收入记录</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="shouruAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shouruAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="shouruDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shouruDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="shouruUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shouruUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="shouruSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shouruSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian3"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        采购订单-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">采购订单</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="caigouDingdanAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouDingdanAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="caigouDingdanDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouDingdanDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="caigouDingdanUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouDingdanUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="caigouDingdanSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouDingdanSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        采购入库-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">采购入库</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="caigouRukuAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouRukuAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="caigouRukuDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouRukuDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="caigouRukuUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouRukuUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="caigouRukuSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouRukuSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        采购收票-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">采购收票</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="caigouShoupiaoAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouShoupiaoAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="caigouShoupiaoDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouShoupiaoDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="caigouShoupiaoUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouShoupiaoUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="caigouShoupiaoSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.caigouShoupiaoSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        支出记录-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">支出记录</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="zhichuAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhichuAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian3"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="zhichuDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhichuDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="zhichuUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhichuUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="zhichuSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhichuSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        转账-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">转账</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="新增" prop="zhuanzhangAdd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhuanzhangAdd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除" prop="zhuanzhangDel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhuanzhangDel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="修改" prop="zhuanzhangUpd" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhuanzhangUpd" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="查看" prop="zhuanzhangSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhuanzhangSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        库存-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">库存</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="查看" prop="kucunSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.kucunSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        账户余额-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">账户余额</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="查看" prop="zhanghuYueSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhanghuYueSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        收入分类统计-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">收入分类统计</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="查看" prop="shouruTongjiSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.shouruTongjiSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        支出分类统计-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">支出分类统计</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="查看" prop="zhichuTongjiSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.zhichuTongjiSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--        月度收支统计-->
        <el-row :gutter="15">
          <el-col :span="6">
            <p class="dialog-title">月度收支统计</p>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :span="6">
            <el-form-item label="查看" prop="yueduTongjiSel" class="custom-form-item">
              <el-select style="z-index:999;" v-model="Power.yueduTongjiSel" filterable placeholder="请选择">
                <!-- types 为后端查询 -->
                <el-option
                    v-for="item in XiaLa_QuanXian2"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row :gutter="15">
          <el-col :span="4">
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                         @click="save">保存
              </el-button>
            </div>
          </el-col>
          <el-col :span="4">
            <div style="display: flex">
              <el-button class="custom-login-button" :loading="login_btn_loading" type="primary"
                         @click="addClose">取消
              </el-button>
            </div>
          </el-col>
        </el-row>



      </el-form>

    </el-dialog>

  </el-container>



</template>

<script>
import axios from "axios";
import MessageUtil from "@/utils/MessageUtil";
import parseArea from "@/utils/ParseDataArea";
export default {
  data() {
    return {
      tableHeight:window.innerHeight-window.innerHeight * 0.48,
      currentPage: 1, // 当前页数，
      pageSize: 10, // 每一页显示的条数
      total:20,
      name:'',
      bianhao:'',
      XiaLa_QuanXian3:[
        {
          name:'查看全部',
          label:'查看全部',
        },
        {
          name:'查看个人',
          label:'查看个人',
        },
        {
          name:'否',
          label:'否',
        }
      ],
      XiaLa_QuanXian2:[
        {
          name:'是',
          label:'是',
        },
        {
          name:'否',
          label:'否',
        }
      ],
      Power: {
        name:'', zhanghaoguanliAdd:'', zhanghaoguanliDel:'', zhanghaoguanliUpd:'', zhanghaoguanliSel:'', quanxianAdd:'', quanxianDel:'', quanxianUpd:'', quanxianSel:'', kehuAdd:'', kehuDel:'', kehuUpd:'', kehuSel:'', gongyingshangAdd:'', gongyingshangDel:'', gongyingshangUpd:'', gongyingshangSel:'', shangpinAdd:'', shangpinDel:'', shangpinUpd:'', shangpinSel:'', fujiashuiUpd:'', peizhiAdd:'', peizhiDel:'', peizhiUpd:'', peizhiSel:'', xiaoshouBaojiaAdd:'', xiaoshouBaojiaDel:'', xiaoshouBaojiaUpd:'', xiaoshouBaojiaSel:'', xiaoshouDingdanAdd:'', xiaoshouDingdanDel:'', xiaoshouDingdanUpd:'', xiaoshouDingdanSel:'', xiaoshouChukuAdd:'', xiaoshouChukuDel:'', xiaoshouChukuUpd:'', xiaoshouChukuSel:'', xiaoshouKaipiaoAdd:'', xiaoshouKaipiaoDel:'', xiaoshouKaipiaoUpd:'', xiaoshouKaipiaoSel:'', shouruAdd:'', shouruDel:'', shouruUpd:'', shouruSel:'', caigouDingdanAdd:'', caigouDingdanDel:'', caigouDingdanUpd:'', caigouDingdanSel:'', caigouRukuAdd:'', caigouRukuDel:'', caigouRukuUpd:'', caigouRukuSel:'', caigouShoupiaoAdd:'', caigouShoupiaoDel:'', caigouShoupiaoUpd:'', caigouShoupiaoSel:'', zhichuAdd:'', zhichuDel:'', zhichuUpd:'', zhichuSel:'', zhuanzhangAdd:'', zhuanzhangDel:'', zhuanzhangUpd:'', zhuanzhangSel:'', kucunSel:'', zhanghuYueSel:'', shouruTongjiSel:'', zhichuTongjiSel:'', yueduTongjiSel:'', fujiashuiSel:''
      },
      addDialog: false,
      updDialog: false,
      tableData: [],
      multipleSelection: []
    }
  },
  created() {
    this.getUser();
  },
  methods: {
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

    //新增窗口弹出
    addUser() {
      if(this.userPower.quanxianAdd != '是'){
        MessageUtil.error("无新增权限");
        return;
      }
      this.Power = {
        name:'',
        zhanghaoguanliAdd:'是',
        zhanghaoguanliDel:'是',
        zhanghaoguanliUpd:'是',
        zhanghaoguanliSel:'是',
        quanxianAdd:'是',
        quanxianDel:'是',
        quanxianUpd:'是',
        quanxianSel:'是',
        kehuAdd:'是',
        kehuDel:'是',
        kehuUpd:'是',
        kehuSel:'是',
        gongyingshangAdd:'是',
        gongyingshangDel:'是',
        gongyingshangUpd:'是',
        gongyingshangSel:'是',
        shangpinAdd:'是',
        shangpinDel:'是',
        shangpinUpd:'是',
        shangpinSel:'是',
        fujiashuiUpd:'是',
        peizhiAdd:'是',
        peizhiDel:'是',
        peizhiUpd:'是',
        peizhiSel:'是',
        xiaoshouBaojiaAdd:'是',
        xiaoshouBaojiaDel:'是',
        xiaoshouBaojiaUpd:'是',
        xiaoshouBaojiaSel:'查看全部',
        xiaoshouDingdanAdd:'是',
        xiaoshouDingdanDel:'是',
        xiaoshouDingdanUpd:'是',
        xiaoshouDingdanSel:'查看全部',
        xiaoshouChukuAdd:'是',
        xiaoshouChukuDel:'是',
        xiaoshouChukuUpd:'是',
        xiaoshouChukuSel:'是',
        xiaoshouKaipiaoAdd:'是',
        xiaoshouKaipiaoDel:'是',
        xiaoshouKaipiaoUpd:'是',
        xiaoshouKaipiaoSel:'是',
        shouruAdd:'是',
        shouruDel:'是',
        shouruUpd:'是',
        shouruSel:'查看全部',
        caigouDingdanAdd:'是',
        caigouDingdanDel:'是',
        caigouDingdanUpd:'是',
        caigouDingdanSel:'是',
        caigouRukuAdd:'是',
        caigouRukuDel:'是',
        caigouRukuUpd:'是',
        caigouRukuSel:'是',
        caigouShoupiaoAdd:'是',
        caigouShoupiaoDel:'是',
        caigouShoupiaoUpd:'是',
        caigouShoupiaoSel:'是',
        zhichuAdd:'是',
        zhichuDel:'是',
        zhichuUpd:'是',
        zhichuSel:'查看全部',
        zhuanzhangAdd:'是',
        zhuanzhangDel:'是',
        zhuanzhangUpd:'是',
        zhuanzhangSel:'是',
        kucunSel:'是',
        zhanghuYueSel:'是',
        shouruTongjiSel:'是',
        zhichuTongjiSel:'是',
        yueduTongjiSel:'是',
        fujiashuiSel:'是'
      }

      this.addDialog = true;
    },

    addClose(){
      this.addDialog = false;
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

      let url = "http://yhocn.cn:8102/userpower/queryUserPowerById"
      this.axios.post(url, {"id":this_id}).then(res => {
        if(res.data.code == '00') {
          var this_val = res.data.data
          this_val.body = this_val.itemList
          this.Power = this_val
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

    //查询全部
    getAll(){
      if(this.userPower.quanxianSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      let url = "http://yhocn.cn:8102/userpower/queryPower"
      this.axios.post(url, {"name":""}).then(res => {
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
          if(this.userPower.quanxianSel == '是'){
            this.getAll();
          }else{
            MessageUtil.error("无查询权限");
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

    //刷新
    refresh(){
      if(this.userPower.quanxianSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      this.getAll()
    },

    //条件查询
    query(){
      if(this.userPower.quanxianSel != '是'){
        MessageUtil.error("无查询权限");
        return;
      }
      var date = {
        name:this.name
      }
      let url = "http://yhocn.cn:8102/userpower/queryPower"
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

    savePower(){
      var save_list = this.Power
      let url = "http://yhocn.cn:8102/userpower/powerAdd"
      this.axios.post(url, save_list).then(res => {
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

    updPower(){
      if(this.userPower.quanxianUpd != '是'){
        MessageUtil.error("无修改权限");
        return;
      }
      var save_list = this.Power
      let url = "http://yhocn.cn:8102/userpower/powerUpd"
      this.axios.post(url, save_list).then(res => {
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
      if(this.Power.id != undefined && this.Power.id != null){
        this.updPower()
      }else{
        this.savePower()
      }
    },

    deleteClick(){
      if(this.userPower.quanxianDel != '是'){
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
        let url = "http://yhocn.cn:8102/userpower/delPower";
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
  }
}
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>