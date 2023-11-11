package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouDingDanMapper {

    //查询全部
    public List<CaiGouDingDan> getAll();

    //查询需要自己审核
    public List<CaiGouDingDan> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<CaiGouDingDan> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("gongyingshang") String gongyingshang,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //增加
    public List<CaiGouDingDan> caiGouDingDanAdd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("gongyingshang") String gongyingshang,@Param("dianpu") String dianpu,@Param("jinxiang_shuilv") String jinxiang_shuilv,@Param("beizhu") String beizhu,@Param("shenhe") String shenhe,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //修改
    public List<CaiGouDingDan> caiGouDingDanUpd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("gongyingshang") String gongyingshang,@Param("dianpu") String dianpu,@Param("jinxiang_shuilv") String jinxiang_shuilv,@Param("beizhu") String beizhu,@Param("shenhe") String shenhe,@Param("shenhe_zhuangtai") String shenhe_zhuangtai,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByCaiGouId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouDingDan> selectByCaiGouId(@Param("id") Integer id);


}
