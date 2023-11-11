package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouRuKu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouRuKuMapper {

    //查询全部
    public List<CaiGouRuKu> getAll();

    //条件查询
    public List<CaiGouRuKu> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("gongyingshang") String gongyingshang);

    //增加
    public List<CaiGouRuKu> caiGouRuKuAdd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("gongyingshang") String gongyingshang, @Param("dianpu") String dianpu, @Param("cangku") String cangku,@Param("beizhu") String beizhu,@Param("caigou_id") String caigou_id);

    //修改
    public List<CaiGouRuKu> caiGouRuKuUpd(@Param("bianhao") String bianhao,@Param("riqi") String riqi,@Param("gongyingshang") String gongyingshang, @Param("dianpu") String dianpu, @Param("cangku") String cangku,@Param("beizhu") String beizhu,@Param("caigou_id") String caigou_id,@Param("id") Integer id);

    //根据采购id删除
    public boolean deleteByRuKuId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouRuKu> selectByRuKuId(@Param("id") Integer id);


}
