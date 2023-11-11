package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouBaoJia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouBaoJiaMapper {

    //查询全部
    public List<XiaoShouBaoJia> getAll();

    //查询需要自己审核
    public List<XiaoShouBaoJia> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouBaoJia> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //增加
    public List<XiaoShouBaoJia> baoJiaAdd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu,@Param("yewuyuan") String yewuyuan,@Param("dianpu") String dianpu,@Param("xiaoxiang_shuilv") String xiaoxiang_shuilv,@Param("beizhu") String beizhu,@Param("shenhe") String shenhe,@Param("jiage_dengji") String jiage_dengji,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //修改
    public List<XiaoShouBaoJia> baoJiaUpd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu,@Param("yewuyuan") String yewuyuan,@Param("dianpu") String dianpu,@Param("xiaoxiang_shuilv") String xiaoxiang_shuilv,@Param("beizhu") String beizhu,@Param("shenhe") String shenhe,@Param("jiage_dengji") String jiage_dengji,@Param("shenhe_zhuangtai") String shenhe_zhuangtai,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteBaoJiaById(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJia> selectBaoJiaById(@Param("id") Integer id);


}
