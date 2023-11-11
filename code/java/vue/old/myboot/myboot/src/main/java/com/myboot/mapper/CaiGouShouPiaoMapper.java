package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouShouPiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouShouPiaoMapper {

    //查询全部
    public List<CaiGouShouPiao> getAll();

    //查询需要自己审核
    public List<CaiGouShouPiao> getAllByShenHe(@Param("xinxi_tuisong") String xinxi_tuisong);

    //条件查询
    public List<CaiGouShouPiao> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kaipiao_danwei") String kaipiao_danwei,@Param("shoupiao_danwei") String shoupiao_danwei,@Param("shoupiao_zhuangtai") String shoupiao_zhuangtai);

    //增加
    public List<CaiGouShouPiao> caiGouShouPiaoAdd(@Param("caigou_bianhao") String caigou_bianhao,@Param("shoupiao_danwei") String shoupiao_danwei,@Param("kaipiao_danwei") String kaipiao_danwei,@Param("kaipiao_riqi") String kaipiao_riqi,@Param("kaipiao_jine") String kaipiao_jine,@Param("kaipiao_shuie") String kaipiao_shuie,@Param("jiashui_heji") String jiashui_heji,@Param("beizhu") String beizhu,@Param("xinxi_tuisong") String xinxi_tuisong,@Param("shoupiao_zhuangtai") String shoupiao_zhuangtai);

    //修改
    public List<CaiGouShouPiao> caiGouShouPiaoUpd(@Param("caigou_bianhao") String caigou_bianhao,@Param("shoupiao_danwei") String shoupiao_danwei,@Param("kaipiao_danwei") String kaipiao_danwei,@Param("kaipiao_riqi") String kaipiao_riqi,@Param("kaipiao_jine") String kaipiao_jine,@Param("kaipiao_shuie") String kaipiao_shuie,@Param("jiashui_heji") String jiashui_heji,@Param("beizhu") String beizhu,@Param("xinxi_tuisong") String xinxi_tuisong,@Param("shoupiao_zhuangtai") String shoupiao_zhuangtai,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByShouPiaoId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouShouPiao> selectByShouPiaoId(@Param("id") Integer id);


}
