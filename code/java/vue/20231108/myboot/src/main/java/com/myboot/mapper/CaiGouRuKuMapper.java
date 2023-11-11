package com.myboot.mapper;

import com.myboot.pojo.CaiGouRuKu;
import com.myboot.pojo.CaiGouRuKuAdd;
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
    public int caiGouRuKuAdd(CaiGouRuKu caiGouRuKu);

    //修改
    public int caiGouRuKuUpd(CaiGouRuKu caiGouRuKu);

    //根据采购id删除
    public int deleteByRuKuId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouRuKu> selectByRuKuId(@Param("id") Integer id);

    int deleteUsrById(Integer id);
}
