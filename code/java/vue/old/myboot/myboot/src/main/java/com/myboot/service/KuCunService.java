package com.myboot.service;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.XiaoShouKaiPiao;

import java.util.List;

public interface KuCunService {

    //查询全部
    public List<KuCun> getKuCun();

    //查询指定仓库
    public List<KuCun> getKuCunByCangKu(String cangku);


}