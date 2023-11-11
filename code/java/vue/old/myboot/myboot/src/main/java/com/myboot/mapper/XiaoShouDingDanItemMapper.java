package com.myboot.mapper;

import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.pojo.XiaoShouDingDanItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouDingDanItemMapper {

    //查询全部
    public List<XiaoShouDingDanItem> getAll();

    //增加
    public List<XiaoShouDingDanItem> xiaoShouItemAdd(@Param("shangpin_bianhao") String shangpin_bianhao,@Param("shangpin_mingcheng") String shangpin_mingcheng,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("baojia_danjia") String baojia_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jianyi_baojia") String jianyi_baojia, @Param("xuyong_riqi") String xuyong_riqi, @Param("baojia_fudong") String baojia_fudong, @Param("beizhu") String beizhu, @Param("xiaoshou_id") Integer xiaoshou_id);

    //修改
    public List<XiaoShouDingDanItem> xiaoShouItemUpd(@Param("shangpin_bianhao") String shangpin_bianhao,@Param("shangpin_mingcheng") String shangpin_mingcheng,@Param("guige") String guige, @Param("caizhi") String caizhi, @Param("jishu_biaozhun") String jishu_biaozhun,@Param("zhibao_dengji") String zhibao_dengji,@Param("danwei") String danwei, @Param("shuliang") String shuliang, @Param("baojia_danjia") String baojia_danjia, @Param("jiashui_xiaoji") String jiashui_xiaoji, @Param("jianyi_baojia") String jianyi_baojia, @Param("xuyong_riqi") String xuyong_riqi, @Param("baojia_fudong") String baojia_fudong, @Param("beizhu") String beizhu, @Param("xiaoshou_id") Integer xiaoshou_id,@Param("id") Integer id);

    //根据报价id删除
    public boolean deleteByXiaoShouId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouDingDanItem> selectByXiaoShouId(@Param("id") Integer id);


}
