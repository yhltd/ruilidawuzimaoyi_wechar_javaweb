package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.FileTable;

import java.util.List;

public interface FileTableService {

    //查询全部
    public List<FileTable> getAll(String id,String type);

    //查询全部
    public List<FileTable> getById(Integer id);

    //根据id删除
    public int deleteById(List<Integer> list);

    //根据id删除
    public int deleteByFileId(List<String> list);

    //增加
    public int fileAdd(FileTable fileTable);


}