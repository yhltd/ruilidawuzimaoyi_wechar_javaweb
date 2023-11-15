package com.myboot.service.impl;

import com.myboot.mapper.CaiGouShouPiaoMapper;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.service.CaiGouShouPiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaiGouShouPiaoImpl implements CaiGouShouPiaoService {

    @Autowired
    private CaiGouShouPiaoMapper mapper;

    @Override
    public List<CaiGouShouPiao> getAll() {
        List<CaiGouShouPiao> caiGouShouPiao = mapper.getAll();
        return caiGouShouPiao;
    }

    @Override
    public List<CaiGouShouPiao> getAllByShenHe(String xinxi_tuisong) {
        List<CaiGouShouPiao> caiGouShouPiao = mapper.getAllByShenHe(xinxi_tuisong);
        return caiGouShouPiao;
    }

    @Override
    public List<CaiGouShouPiao> queryList(String start_date,String stop_date,String kaipiao_danwei,String shoupiao_danwei,String shoupiao_zhuangtai){
        List<CaiGouShouPiao> caiGouShouPiao = mapper.queryList(start_date,stop_date,kaipiao_danwei,shoupiao_danwei,shoupiao_zhuangtai);
        return caiGouShouPiao;
    }

    @Override
    public int caiGouShouPiaoAdd(CaiGouShouPiao caiGouShouPiao){
        Integer shouPiao = mapper.caiGouShouPiaoAdd(caiGouShouPiao);
        return shouPiao;
    }

    @Override
    public int caiGouShouPiaoUpd(CaiGouShouPiao caiGouShouPiao){
        Integer shouPiao = mapper.caiGouShouPiaoUpd(caiGouShouPiao);
        return shouPiao;
    }

    @Override
    public int deleteByShouPiaoId(Integer id){
        return mapper.deleteByShouPiaoId(id);
    }

    @Override
    public List<CaiGouShouPiao> selectByShouPiaoId(Integer id){
        return mapper.selectByShouPiaoId(id);
    }

    @Override
    public List<CaiGouShouPiao> selectYingShou() {
        return mapper.selectYingShou();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteByShouPiaoId(id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int shouPiao(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.shouPiao(id);
        }
        return count;
    }

}
