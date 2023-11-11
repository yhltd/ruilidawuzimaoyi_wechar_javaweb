<template>
  <el-container>
    <el-aside width="150px">
      <el-menu>
        <el-menu-item v-for="power in powerList">
          <el-button @click="showPower(power.id)">{{ power.name }}</el-button>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <el-row :gutter="40">
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button type="danger" @click="changeAllClick('否')">全部禁用</el-button>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button type="success" @click="changeAllClick('是')">全部启用</el-button>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button private="primary" @click="saveChange()">保存</el-button>
        </el-col>
        <el-col :xs="8" :sm="6" :md="5" :lg="3" :xl="2">
          <el-button @click="reset();">重置</el-button>
        </el-col>
      </el-row>
      <el-form v-model="form">
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="账号管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加账号">
                    <el-switch
                        v-model="form.zhanghaoguanliAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除账号">

                    <el-switch
                        v-model="form.zhanghaoguanliDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改账号">
                    <el-switch
                        v-model="form.zhanghaoguanliUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询账号">

                    <el-switch
                        v-model="form.zhanghaoguanliSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="权限管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加权限">
                    <el-switch
                        v-model="form.quanxianAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除权限">

                    <el-switch
                        v-model="form.quanxianDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改权限">
                    <el-switch
                        v-model="form.quanxianUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询权限">

                    <el-switch
                        v-model="form.quanxianSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="客户管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加客户">
                    <el-switch
                        v-model="form.kehuAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除客户">

                    <el-switch
                        v-model="form.kehuDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改客户">
                    <el-switch
                        v-model="form.kehuUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询客户">

                    <el-switch
                        v-model="form.kehuSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="供应商管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加供应商">
                    <el-switch
                        v-model="form.gongyingshangAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除供应商">

                    <el-switch
                        v-model="form.gongyingshangDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改供应商">
                    <el-switch
                        v-model="form.gongyingshangUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询供应商">

                    <el-switch
                        v-model="form.gongyingshangSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="商品管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加商品">
                    <el-switch
                        v-model="form.shangpinAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除商品">

                    <el-switch
                        v-model="form.shangpinDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改商品">
                    <el-switch
                        v-model="form.shangpinUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询商品">

                    <el-switch
                        v-model="form.shangpinSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="配置管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加配置">
                    <el-switch
                        v-model="form.peizhiAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除配置">

                    <el-switch
                        v-model="form.peizhiDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改配置">
                    <el-switch
                        v-model="form.peizhiUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询配置">

                    <el-switch
                        v-model="form.peizhiSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="附加税管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改附加税">
                    <el-switch
                        v-model="form.fujiashuiUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询附加税">

                    <el-switch
                        v-model="form.fujiashuiSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="销售报价管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加报价">
                    <el-switch
                        v-model="form.xiaoshouBaojiaAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除报价">

                    <el-switch
                        v-model="form.xiaoshouBaojiaDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改报价">
                    <el-switch
                        v-model="form.xiaoshouBaojiaUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询报价">

                    <el-switch
                        v-model="form.xiaoshouBaojiaSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="销售订单管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加订单">
                    <el-switch
                        v-model="form.xiaoshouDingdanAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除订单">

                    <el-switch
                        v-model="form.xiaoshouDingdanDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改订单">
                    <el-switch
                        v-model="form.xiaoshouDingdanUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询订单">

                    <el-switch
                        v-model="form.xiaoshouDingdanSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="销售仓库管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加仓库">
                    <el-switch
                        v-model="form.xiaoshouChukuAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除仓库">

                    <el-switch
                        v-model="form.xiaoshouChukuDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改仓库">
                    <el-switch
                        v-model="form.xiaoshouChukuUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询仓库">

                    <el-switch
                        v-model="form.xiaoshouChukuSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="销售开票管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加票据">
                    <el-switch
                        v-model="form.xiaoshouKaipiaoAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除票据">

                    <el-switch
                        v-model="form.xiaoshouKaipiaoDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改票据">
                    <el-switch
                        v-model="form.xiaoshouKaipiaoDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询票据">

                    <el-switch
                        v-model="form.xiaoshouKaipiaoSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="收入管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加收入">
                    <el-switch
                        v-model="form.shouruAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除收入">

                    <el-switch
                        v-model="form.shouruDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改收入">
                    <el-switch
                        v-model="form.shouruUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询收入">

                    <el-switch
                        v-model="form.shouruSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="采购订单管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加订单">
                    <el-switch
                        v-model="form.caigouDingdanAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除订单">

                    <el-switch
                        v-model="form.caigouDingdanDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改订单">
                    <el-switch
                        v-model="form.caigouDingdanUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询订单">

                    <el-switch
                        v-model="form.caigouDingdanSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="采购入库管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加入库">
                    <el-switch
                        v-model="form.caigouRukuAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除入库">

                    <el-switch
                        v-model="form.caigouRukuDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改库存">
                    <el-switch
                        v-model="form.caigouRukuUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询库存">

                    <el-switch
                        v-model="form.caigouRukuSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="采购收票管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加票据">
                    <el-switch
                        v-model="form.caigouShoupiaoAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除票据">

                    <el-switch
                        v-model="form.caigouShoupiaoDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改票据">
                    <el-switch
                        v-model="form.caigouShoupiaoUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询票据">

                    <el-switch
                        v-model="form.caigouShoupiaoSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="支出管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加支出">
                    <el-switch
                        v-model="form.zhichuAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除支出">

                    <el-switch
                        v-model="form.zhichuDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改支出">
                    <el-switch
                        v-model="form.zhichuUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询支出">

                    <el-switch
                        v-model="form.zhichuSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="转账管理">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="添加转账">
                    <el-switch
                        v-model="form.zhuanzhangAdd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="删除转账">

                    <el-switch
                        v-model="form.zhuanzhangDel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="修改转账">
                    <el-switch
                        v-model="form.zhuanzhangUpd"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询转账">

                    <el-switch
                        v-model="form.zhuanzhangSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="库存管理">
              <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                <el-form-item label="查询库存">
                  <el-switch
                      v-model="form.kucunSel"
                      active-color="#13ce66"
                      inactive-color="#ff4949"
                      active-value="是"
                      inactive-value="否">
                  </el-switch>
                </el-form-item>
              </el-col>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="账户余额管理">
              <el-row :gutter="40">

                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="查询余额">

                    <el-switch
                        v-model="form.zhanghuYueSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :xs="40" :sm="40" :md="40" :lg="20" :xl="12">
            <el-form-item label="统计数据">
              <el-row :gutter="40">
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="收入统计">
                    <el-switch
                        v-model="form.shouruTongjiSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="支出统计">

                    <el-switch
                        v-model="form.zhichuTongjiSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="5" :lg="5" :xl="5">
                  <el-form-item label="月度统计">
                    <el-switch
                        v-model="form.yueduTongjiSel"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-value="是"
                        inactive-value="否">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import MessageUtil from "@/utils/MessageUtil";

const deepCopy = (obj) => {
  return JSON.parse(JSON.stringify(obj));
};
export default {
  data() {

    return {
      form: {
        id: 0,
        zhanghaoguanliAdd: '',
        zhanghaoguanliDel: '',
        zhanghaoguanliUpd: '',
        zhanghaoguanliSel: '',
        quanxianAdd: '',
        quanxianDel: '',
        quanxianUpd: '',
        quanxianSel: '',
        kehuAdd: '',
        kehuDel: '',
        kehuUpd: '',
        kehuSel: '',
        gongyingshangAdd: '',
        gongyingshangUpd: '',
        gongyingshangSel: '',
        gongyingshangDel: '',
        shangpinAdd: '',
        shangpinDel: '',
        shangpinUpd: '',
        shangpinSel: '',
        fujiashuiUpd: '',
        peizhiAdd: '',
        peizhiDel: '',
        peizhiUpd: '',
        peizhiSel: '',
        xiaoshouBaojiaAdd: '',
        xiaoshouBaojiaDel: '',
        xiaoshouBaojiaUpd: '',
        xiaoshouBaojiaSel: '',
        xiaoshouDingdanAdd: '',
        xiaoshouDingdanDel: '',
        xiaoshouDingdanSel: '',
        xiaoshouDingdanUpd: '',
        xiaoshouChukuAdd: '',
        xiaoshouChukuDel: '',
        xiaoshouChukuSel: '',
        xiaoshouChukuUpd: '',
        xiaoshouKaipiaoAdd: '',
        xiaoshouKaipiaoDel: '',
        xiaoshouKaipiaoSel: '',
        xiaoshouKaipiaoUpd: '',
        shouruAdd: '',
        shouruUpd: '',
        shouruSel: '',
        shouruDel: '',
        caigouDingdanAdd: '',
        caigouDingdanUpd: '',
        caigouDingdanSel: '',
        caigouDingdanDel: '',
        caigouRukuUpd: '',
        caigouRukuAdd: '',
        caigouRukuSel: '',
        caigouRukuDel: '',
        caigouShoupiaoAdd: '',
        caigouShoupiaoUpd: '',
        caigouShoupiaoDel: '',
        caigouShoupiaoSel: '',
        zhichuUpd: '',
        zhichuAdd: '',
        zhichuSel: '',
        zhichuDel: '',
        zhuanzhangUpd: '',
        zhuanzhangSel: '',
        zhuanzhangAdd: '',
        zhuanzhangDel: '',
        kucunSel: '',
        zhanghuYueSel: '',
        shouruTongjiSel: '',
        zhichuTongjiSel: '',
        yueduTongjiSel: '',
        fujiashuiSel: '',
      },
      powerList: [ // [{id: '', name:''}]
        {
          id: 1,
          name: '管理员'
        },
        {
          id: 2,
          name: '业务员2'
        },
        {
          id: 3,
          name: '业务员3'
        },
        {
          id: 4,
          name: '业务员4'
        },
        {
          id: 5,
          name: '业务员5'
        },
        {
          id: 6,
          name: '业务员6'
        }
      ],
      resetCache: [],
    }
  },
  methods: {
    showPower(id) {
      this.queryUserPower(id);
    },
    queryPower() {
      let url = "http://localhost:8081/userpower/queryUserPower";
      this.axios(url).then(res => {
        if(res.data.code = '00') {
          this.powerList = res.data.data;
          this.resetCache = deepCopy(this.form);
        } else {
          MessageUtil.error("获取权限失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },
    queryUserPower(id) {
      let url = "http://localhost:8081/userpower/queryUserPowerById";
      this.axios.post(url, {"id": id}).then(res => {
        if(res.data.code = '00') {
          this.form = res.data.data;
          this.resetCache = deepCopy(this.form);
          console.log(this.form)
        } else {
          MessageUtil.error("获取权限失败")
        }
      }).catch(() => {
        MessageUtil.error("网络异常")
      })
    },
    /**
     *  全部启用或禁用
     * @param {"是" | "否"} flag
     */
    changeAllClick(flag) {
      let data = this.form;
      this.changeAll(data, flag).then(() => {
        MessageUtil.success("操作成功, 需要点击保存按钮保存更改")
      }).catch(() => {
        MessageUtil.error("操作失败, 即将重置为初始状态");
        this.reset()
      })
    },
    async reset() {
      this.form = deepCopy(this.resetCache);
      MessageUtil.success("重置成功")
    },

    async changeAll(data, flag) {
      for (let i in data) {
        data[i] = flag;
      }
    },
    saveChange() {
      let url = "http://localhost:8081/userpower/save"
      this.axios.post(url, this.form).then(res => {
        if(res.data.code == '00') {
          MessageUtil.success("保存成功");
          this.resetCache = deepCopy(this.form);
        } else {
          MessageUtil.error(res.data.msg);
        }
      }).catch(() => {
        MessageUtil.error("网络异常");
      })
    }
  },
  created() {
    this.queryPower()
  }


}
</script>

<style>
::v-deep .el-form-item__label {
  font-size: 30px;
  border-right: 2px solid red;
}
</style>