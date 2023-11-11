package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouBaoJiaItemMapper {

    //查询全部
    public List<XiaoShouBaoJiaItem> getAll();

    //增加
    public List<XiaoShouBaoJiaItem> baoJiaItemAdd(@Param("shangpin_bianhao") String shangpin_bianhao,@Param("shangpin_mingcheng") String shangpin_mingcheng,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("baojia_danjia") String baojia_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jianyi_baojia") String jianyi_baojia, @Param("xuyong_riqi") String xuyong_riqi, @Param("baojia_fudong") String baojia_fudong, @Param("beizhu") String beizhu, @Param("baojia_id") Integer baojia_id);

    //修改
    public List<XiaoShouBaoJiaItem> baoJiaItemUpd(@Param("shangpin_bianhao") String shangpin_bianhao,@Param("shangpin_mingcheng") String shangpin_mingcheng,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("baojia_danjia") String baojia_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jianyi_baojia") String jianyi_baojia, @Param("xuyong_riqi") String xuyong_riqi, @Param("baojia_fudong") String baojia_fudong, @Param("beizhu") String beizhu, @Param("baojia_id") Integer baojia_id,@Param("id") Integer id);

    //根据报价id删除
    public boolean deleteByBaoJiaId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(@Param("id") Integer id);


}
