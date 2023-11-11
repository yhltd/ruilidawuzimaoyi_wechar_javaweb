package com.myboot.pojo;

/**
 * 与 peizhi 表中的 type 保持一致，如果添加 type 类型需要同步更改
 * @author hui
 * @date 2023/11/8 11:26
 */
public enum PeizhiTypeEnum {
    CANG_KU("仓库"),
    DIAN_PU("店铺"),
    SHOU_KUAN_ZHNAG_HU("收款账户"),
    KE_HU_LEVEL("客户等级"),
    ZHI_BAO_LEVEL("质保等级"),
    GONG_YING_SHANG_LEVEL("供应商等级"),
    KE_HU_TYPE("客户分类"),
    JIA_GE_LEVEL("价格等级"),
    SHANG_PIN_TYPE("商品分类"),
    HE_SUAN_DAN_WEI("核算单位"),
    JI_ZHANG_TYPE("记账分类"),
    JIA_GE_SHANGFU("价格上浮率"),
    JI_ZHANG_MING_XI_TYPE("记账明细类型");
    private final String TYPE;
    private PeizhiTypeEnum(String type) {
        this.TYPE = type;
    }

    public String getTYPE() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "PeizhiTypeEnum{" +
                "TYPE='" + TYPE + '\'' +
                '}';
    }
}
