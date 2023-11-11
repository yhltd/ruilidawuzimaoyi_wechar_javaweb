package com.myboot.mapper;

import com.myboot.pojo.Details;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DetailsMapper {
    public List<Details> getAll();
    public int detailAdd(Details details);
    public int detailsUpdate(Details details);
    public int detailsDel(@Param("dId") Integer id);
}
