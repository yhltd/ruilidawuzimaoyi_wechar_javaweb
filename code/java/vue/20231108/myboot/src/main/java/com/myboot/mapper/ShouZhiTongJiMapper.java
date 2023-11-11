package com.myboot.mapper;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.ShouZhiTongJi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShouZhiTongJiMapper {

    public List<ShouZhiTongJi> getShouRuByDay(@Param("type") String type, @Param("riqi") String riqi);

    public List<ShouZhiTongJi> getZhiChuByDay(@Param("type") String type, @Param("riqi") String riqi);

    public List<ShouZhiTongJi> getShouRuByMonth(@Param("type") String type, @Param("riqi") String riqi);

    public List<ShouZhiTongJi> getZhiChuByMonth(@Param("type") String type, @Param("riqi") String riqi);

    public List<ShouZhiTongJi> getShouZhiByMonth(@Param("riqi") String riqi);
}
