package com.myboot.mapper;

import com.myboot.pojo.CaiGouRuKuItem;
import com.myboot.pojo.XiaoShouChuKuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouChuKuItemMapper {

    //查询全部
    public List<XiaoShouChuKuItem> getAll();

    //增加
    public List<XiaoShouChuKuItem> chuKuItemAdd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige,@Param("caizhi") String caizhi,@Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei,@Param("shuliang") String shuliang,@Param("xiaoshou_danjia") String xiaoshou_danjia,@Param("jiashui_xiaoji") String jiashui_xiaoji,@Param("beizhu") String beizhu,@Param("chuku_id") Integer chuku_id);

    //修改
    public List<XiaoShouChuKuItem> chuKuItemUpd(@Param("shangpin_bianma") String shangpin_bianma,@Param("name") String name,@Param("guige") String guige,@Param("caizhi") String caizhi,@Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei,@Param("shuliang") String shuliang,@Param("xiaoshou_danjia") String xiaoshou_danjia,@Param("jiashui_xiaoji") String jiashui_xiaoji,@Param("beizhu") String beizhu,@Param("chuku_id") Integer chuku_id,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByChuKuItemId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouChuKuItem> selectByChuKuItemId(@Param("id") Integer id);


}
