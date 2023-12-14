package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouRuKu;
import com.myboot.pojo.CaiGouRuKuAdd;
import com.myboot.pojo.CaiGouRuKuXiangQing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouRuKuMapper {

    //查询全部
    public List<CaiGouRuKu> getAll();

    //条件查询
    public List<CaiGouRuKu> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("gongyingshang") String gongyingshang);

    //增加
    public int caiGouRuKuAdd(CaiGouRuKu caiGouRuKu);

    //修改
    public int caiGouRuKuUpd(CaiGouRuKu caiGouRuKu);

    //根据采购id删除
    public int deleteByRuKuId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouRuKu> selectByRuKuId(@Param("id") Integer id);

    //查询最大单号
    public List<CaiGouRuKu> selectMaxDanHao();

    int deleteUsrById(Integer id);

    public List<CaiGouRuKu> selectWeiFu();

    public List<CaiGouRuKuXiangQing> xiangQingShangPin(Integer id);

    public List<CaiGouRuKuXiangQing> xiangQingShouKuan(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingKaiPiao(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingDingJin(String bianhao);

    public List<CaiGouRuKuXiangQing> xiangQingDingJinYiYong(String bianhao);

    public int chuKuDingJinUpd(Integer id,String dingjin);

    public List<CaiGouRuKuXiangQing> xiangQingXuKaiPiao(String bianhao);
}
