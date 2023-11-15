package com.myboot.service.impl;

import com.myboot.mapper.CaiGouShouPiaoMapper;
import com.myboot.mapper.FileTableMapper;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.FileTable;
import com.myboot.service.CaiGouShouPiaoService;
import com.myboot.service.FileTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileTableImpl implements FileTableService {

    @Autowired
    private FileTableMapper mapper;

    @Override
    public List<FileTable> getAll(String id,String type) {
        List<FileTable> fileTable = mapper.getAll(id,type);
        return fileTable;
    }

    @Override
    public List<FileTable> getById(Integer id) {
        List<FileTable> fileTable = mapper.getById(id);
        return fileTable;
    }

    @Override
    public int fileAdd(FileTable fileTable){
        Integer shouPiao = mapper.fileAdd(fileTable);
        return shouPiao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteById(id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByFileId(List<String> list) {
        int count = 0;
        for(String id : list) {
            count += mapper.deleteByFileId(id);
        }
        return count;
    }

}
