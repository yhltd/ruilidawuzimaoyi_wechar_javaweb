package com.myboot.mapper;

import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.CustomerItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CustomerItemMapper {
    long countByExample(CustomerItemExample example);

    int deleteByExample(CustomerItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerItem record);

    int insertSelective(CustomerItem record);

    List<CustomerItem> selectByExample(CustomerItemExample example);

    CustomerItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerItem record, @Param("example") CustomerItemExample example);

    int updateByExample(@Param("record") CustomerItem record, @Param("example") CustomerItemExample example);

    int updateByPrimaryKeySelective(CustomerItem record);

    int updateByPrimaryKey(CustomerItem record);
}