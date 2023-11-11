package com.myboot.mapper;

import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.pojo.ZhuanZhang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZhuanZhangMapper {

    //查询全部
    public List<ZhuanZhang> getAll();

    //条件查询
    public List<ZhuanZhang> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("zhuanru") String zhuanru,@Param("zhuanchu") String zhuanchu);

    //增加
    public int zhuanZhangAdd(ZhuanZhang zhuanZhang);

    //修改
    public int zhuanZhangUpd(ZhuanZhang zhuanZhang);

    //根据销售id删除
    public int deleteZhuanZhangById(@Param("id") Integer id);

    //根据销售id查询
    public List<ZhuanZhang> selectZhuanZhangById(@Param("id") Integer id);



}
