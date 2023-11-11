<template>
  <el-container direction="vertical">
    <el-row :gutter="15">
      <el-col :span="3">
        <el-input placeholder="权限名" v-model="name" class="input-with-select">
        </el-input>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="query()">查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="refresh()">刷新</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="addUser()">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="updUser()">编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="deleteClick()">删除</el-button>
      </el-col>
    </el-row>
    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="name"
          label="权限名"
          width="200">
      </el-table-column>
    </el-table>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>

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
              <el-select v-model="Power.zhanghaoguanliAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhanghaoguanliDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhanghaoguanliUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhanghaoguanliSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.quanxianAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.quanxianDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.quanxianUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.quanxianSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.kehuAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.kehuDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.kehuUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.kehuSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.gongyingshangAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.gongyingshangDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.gongyingshangUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.gongyingshangSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.shangpinAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.shangpinDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.shangpinUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.shangpinSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.fujiashuiUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.fujiashuiSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.peizhiAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.peizhiDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.peizhiUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.peizhiSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouBaojiaAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouBaojiaDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouBaojiaUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouBaojiaSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouDingdanAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouDingdanDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouDingdanUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouDingdanSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouChukuAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouChukuDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouChukuUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouChukuSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouKaipiaoAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouKaipiaoDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouKaipiaoUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.xiaoshouKaipiaoSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.shouruAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.shouruDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.shouruUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.shouruSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouDingdanAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouDingdanDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouDingdanUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouDingdanSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouRukuAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouRukuDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouRukuUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouRukuSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouShoupiaoAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouShoupiaoDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouShoupiaoUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.caigouShoupiaoSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhichuAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhichuDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhichuUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhichuSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhuanzhangAdd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhuanzhangDel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhuanzhangUpd" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhuanzhangSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.kucunSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhanghuYueSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.shouruTongjiSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.zhichuTongjiSel" filterable placeholder="请选择">
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
              <el-select v-model="Power.yueduTongjiSel" filterable placeholder="请选择">
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
    this.getAll();
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
      this.Power = {
        name:'', zhanghaoguanliAdd:'', zhanghaoguanliDel:'', zhanghaoguanliUpd:'', zhanghaoguanliSel:'', quanxianAdd:'', quanxianDel:'', quanxianUpd:'', quanxianSel:'', kehuAdd:'', kehuDel:'', kehuUpd:'', kehuSel:'', gongyingshangAdd:'', gongyingshangDel:'', gongyingshangUpd:'', gongyingshangSel:'', shangpinAdd:'', shangpinDel:'', shangpinUpd:'', shangpinSel:'', fujiashuiUpd:'', peizhiAdd:'', peizhiDel:'', peizhiUpd:'', peizhiSel:'', xiaoshouBaojiaAdd:'', xiaoshouBaojiaDel:'', xiaoshouBaojiaUpd:'', xiaoshouBaojiaSel:'', xiaoshouDingdanAdd:'', xiaoshouDingdanDel:'', xiaoshouDingdanUpd:'', xiaoshouDingdanSel:'', xiaoshouChukuAdd:'', xiaoshouChukuDel:'', xiaoshouChukuUpd:'', xiaoshouChukuSel:'', xiaoshouKaipiaoAdd:'', xiaoshouKaipiaoDel:'', xiaoshouKaipiaoUpd:'', xiaoshouKaipiaoSel:'', shouruAdd:'', shouruDel:'', shouruUpd:'', shouruSel:'', caigouDingdanAdd:'', caigouDingdanDel:'', caigouDingdanUpd:'', caigouDingdanSel:'', caigouRukuAdd:'', caigouRukuDel:'', caigouRukuUpd:'', caigouRukuSel:'', caigouShoupiaoAdd:'', caigouShoupiaoDel:'', caigouShoupiaoUpd:'', caigouShoupiaoSel:'', zhichuAdd:'', zhichuDel:'', zhichuUpd:'', zhichuSel:'', zhuanzhangAdd:'', zhuanzhangDel:'', zhuanzhangUpd:'', zhuanzhangSel:'', kucunSel:'', zhanghuYueSel:'', shouruTongjiSel:'', zhichuTongjiSel:'', yueduTongjiSel:'', fujiashuiSel:''
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

      let url = "http://localhost:8081/userpower/queryUserPowerById"
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
      let url = "http://localhost:8081/userpower/queryPower"
      this.axios.post(url, {"name":""}).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
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
      this.bianhao = ""
      this.name = ""
      this.getAll()
    },

    //条件查询
    query(){
      var date = {
        name:this.name
      }
      let url = "http://localhost:8081/userpower/queryPower"
      this.axios.post(url, date).then(res => {
        if(res.data.code == '00') {
          this.tableData = res.data.data;
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
      let url = "http://localhost:8081/userpower/powerAdd"
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
      var save_list = this.Power
      let url = "http://localhost:8081/userpower/powerUpd"
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
      if(this.multipleSelection.length == 0){
        MessageUtil.error("未选中信息");
        return;
      }
      this.$confirm('是否删除当前选中的客户信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        var list = []
        for(var i=0; i<this.multipleSelection.length; i++){
          list.push(this.multipleSelection[i].id)
        }
        console.log(list)
        let url = "http://localhost:8081/userpower/delPower";
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
  }
}
</script>
<style>
.dialog-title{
  font-weight:bold;
  font-size: larger;
}
</style>