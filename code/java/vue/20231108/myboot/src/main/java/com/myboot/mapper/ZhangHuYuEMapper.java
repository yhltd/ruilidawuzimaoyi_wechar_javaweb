package com.myboot.mapper;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.ZhangHuYuE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZhangHuYuEMapper {

    public List<ZhangHuYuE> queryList(@Param("riqi") String riqi);


}
