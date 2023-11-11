package com.myboot.mapper;

import com.myboot.pojo.Receiving;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceivingMapper {
    public List<Receiving> getAll();
    public int receAdd(Receiving receiving);
    public int receUpdate(Receiving receiving);
    public int receDel(@Param("rId") Integer id);

}
