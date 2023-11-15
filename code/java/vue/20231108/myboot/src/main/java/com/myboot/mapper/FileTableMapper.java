package com.myboot.mapper;

import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.FileTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileTableMapper {

    //查询全部
    public List<FileTable> getAll(String id,String type);

    //查询需要自己审核
    public List<FileTable> getById(@Param("id") Integer id);

    //增加
    public int fileAdd(FileTable fileTable);

    //根据采购id删除
    public int deleteById(@Param("id") Integer id);

    //根据采购id删除
    public int deleteByFileId(@Param("id") String id);


}
