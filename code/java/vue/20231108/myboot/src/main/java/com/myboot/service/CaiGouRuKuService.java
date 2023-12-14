package com.myboot.service;

import com.myboot.pojo.*;

import java.util.List;

public interface CaiGouRuKuService {

    //查询全部
    public List<CaiGouRuKu> getAll();

    //条件查询
    public List<CaiGouRuKu> queryList(String start_date,String stop_date,String gongyingshang);

    //增加
    public int caiGouRuKuAdd(CaiGouRuKu caiGouRuKu);

    //修改
    public int caiGouRuKuUpd(CaiGouRuKu caiGouRuKu);

    //根据采购id删除
    public int deleteByRuKuId(Integer id);

    //根据采购id查询
    public List<CaiGouRuKu> selectByRuKuId(Integer id);

    //获取最大单号
    public List<CaiGouRuKu> selectMaxDanHao();

    int delUsers(List<Integer> list);

    public List<CaiGouRuKu> selectWeiFu();

    public List<CaiGouRuKuXiangQing> xiangQingShangPin(Integer id);

    public List<CaiGouRuKuXiangQing> xiangQingShouKuan(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingKaiPiao(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingDingJin(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingDingJinYiYong(String bianhao);

    public int chuKuDingJinUpd(Integer id,String dingjin);

    public List<CaiGouRuKuXiangQing> xiangQingXuKaiPiao(String bianhao);

}