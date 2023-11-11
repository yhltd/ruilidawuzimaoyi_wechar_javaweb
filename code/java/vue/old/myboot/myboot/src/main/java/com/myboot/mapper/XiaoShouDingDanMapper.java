package com.myboot.mapper;

import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouDingDan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouDingDanMapper {

    //查询全部
    public List<XiaoShouDingDan> getAll();

    //查询需要自己审核
    public List<XiaoShouDingDan> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouDingDan> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //增加
    public List<XiaoShouDingDan> xiaoShouAdd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu, @Param("yewuyuan") String yewuyuan, @Param("shoujianren") String shoujianren, @Param("shoujian_phone") String shoujian_phone, @Param("shoujian_dizhi") String shoujian_dizhi, @Param("dianpu") String dianpu,@Param("xiaoxiang_shuilv") String xiaoxiang_shuilv,@Param("beizhu") String beizhu, @Param("shenhe") String shenhe, @Param("shenhe_zhuangtai") String shenhe_zhuangtai, @Param("jiashui_heji") String jiashui_heji, @Param("jiage_dengji") String jiage_dengji);

    //修改
    public List<XiaoShouDingDan> xiaoShouUpd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu, @Param("yewuyuan") String yewuyuan, @Param("shoujianren") String shoujianren, @Param("shoujian_phone") String shoujian_phone, @Param("shoujian_dizhi") String shoujian_dizhi, @Param("dianpu") String dianpu,@Param("xiaoxiang_shuilv") String xiaoxiang_shuilv,@Param("beizhu") String beizhu, @Param("shenhe") String shenhe, @Param("shenhe_zhuangtai") String shenhe_zhuangtai, @Param("jiashui_heji") String jiashui_heji, @Param("jiage_dengji") String jiage_dengji,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteXiaoShouById(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouDingDan> selectXiaoShouById(@Param("id") Integer id);


}
