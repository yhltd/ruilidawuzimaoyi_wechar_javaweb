package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaiGouRuKuAdd {
    private Integer id;
    private String bianhao;
    private String riqi;
    private String gongyingshang;
    private String dianpu;
    private String cangku;
    private String beizhu;
    private String caigou_id;
    private List<CaiGouRuKuItem> rukuItem;
}
