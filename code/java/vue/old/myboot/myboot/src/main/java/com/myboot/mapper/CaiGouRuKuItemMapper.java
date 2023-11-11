package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.CaiGouRuKuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouRuKuItemMapper {

    //查询全部
    public List<CaiGouRuKuItem> getAll();

    //增加
    public List<CaiGouRuKuItem> caiGouRuKuAdd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("lishi_zuidi") String lishi_zuidi, @Param("caigou_danjia") String caigou_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jiaohuo_riqi") String jiaohuo_riqi, @Param("beizhu") String beizhu, @Param("ruku_id") Integer ruku_id);

    //修改
    public List<CaiGouRuKuItem> caiGouRuKuUpd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("lishi_zuidi") String lishi_zuidi, @Param("caigou_danjia") String caigou_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jiaohuo_riqi") String jiaohuo_riqi, @Param("beizhu") String beizhu, @Param("ruku_id") Integer ruku_id,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByRuKuItemId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouRuKuItem> selectByRuKuItemId(@Param("id") Integer id);


}
