package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouChuKu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouChuKuMapper {

    //查询全部
    public List<XiaoShouChuKu> getAll();

    //查询需要自己审核
    public List<XiaoShouChuKu> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouChuKu> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu);

    //增加
    public List<XiaoShouChuKu> chuKuAdd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu,@Param("dianpu") String dianpu,@Param("cangku") String cangku,@Param("beizhu") String beizhu,@Param("xiaoshou_id") String xiaoshou_id);

    //修改
    public List<XiaoShouChuKu> chuKuUpd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("kehu") String kehu,@Param("dianpu") String dianpu,@Param("cangku") String cangku,@Param("beizhu") String beizhu,@Param("xiaoshou_id") String xiaoshou_id,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByChuKuId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouChuKu> selectByChuKuId(@Param("id") Integer id);


}
