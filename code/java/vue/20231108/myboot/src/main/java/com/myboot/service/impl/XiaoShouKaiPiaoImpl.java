package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouDingDanMapper;
import com.myboot.mapper.XiaoShouKaiPiaoMapper;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.service.XiaoShouDingDanService;
import com.myboot.service.XiaoShouKaiPiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoShouKaiPiaoImpl implements XiaoShouKaiPiaoService {

    @Autowired
    private XiaoShouKaiPiaoMapper mapper;

    @Override
    public List<XiaoShouKaiPiao> getAll() {
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = mapper.getAll();
        return xiaoShouKaiPiao;
    }

    @Override
    public List<XiaoShouKaiPiao> getAllByShenHe(String xinxi_tuisong) {
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = mapper.getAllByShenHe(xinxi_tuisong);
        return xiaoShouKaiPiao;
    }

    @Override
    public List<XiaoShouKaiPiao> queryList(String start_date,String stop_date,String shoupiao_danwei, String kaipiao_danwei,String kaipiao_zhuangtai){
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = mapper.queryList(start_date,stop_date,shoupiao_danwei,kaipiao_danwei,kaipiao_zhuangtai);
        return xiaoShouKaiPiao;
    }

    @Override
    public int kaiPiaoAdd(XiaoShouKaiPiao xiaoShouKaiPiao){
        Integer kaiPiao = mapper.kaiPiaoAdd(xiaoShouKaiPiao);
        return kaiPiao;
    }

    @Override
    public int kaiPiaoUpd(XiaoShouKaiPiao xiaoShouKaiPiao) {
        Integer kaiPiao = mapper.kaiPiaoUpd(xiaoShouKaiPiao);
        return kaiPiao;
    }

    @Override
    public int deleteXiaoShouById(Integer id){
        return mapper.deleteXiaoShouById(id);
    }

    @Override
    public List<XiaoShouKaiPiao> selectXiaoShouById(Integer id){
        return mapper.selectXiaoShouById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteXiaoShouById(id);
        }
        return count;
    }

    @Override
    public List<XiaoShouKaiPiao> selectYingKai() {
        return mapper.selectYingKai();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int kaiPiao(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.kaiPiao(id);
        }
        return count;
    }

}
