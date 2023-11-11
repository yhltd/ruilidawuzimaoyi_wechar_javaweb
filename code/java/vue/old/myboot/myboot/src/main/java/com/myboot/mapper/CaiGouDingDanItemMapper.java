package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouDingDanItemMapper {

    //查询全部
    public List<CaiGouDingDanItem> getAll();

    //增加
    public List<CaiGouDingDanItem> caiGouDingDanAdd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("lishi_zuidi") String lishi_zuidi, @Param("caigou_danjia") String caigou_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jiaohuo_riqi") String jiaohuo_riqi, @Param("beizhu") String beizhu, @Param("caigou_id") Integer caigou_id);

    //修改
    public List<CaiGouDingDanItem> caiGouDingDanUpd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("lishi_zuidi") String lishi_zuidi, @Param("caigou_danjia") String caigou_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jiaohuo_riqi") String jiaohuo_riqi, @Param("beizhu") String beizhu, @Param("caigou_id") Integer caigou_id,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByCaiGouId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouDingDanItem> selectByCaiGouId(@Param("id") Integer id);


}
