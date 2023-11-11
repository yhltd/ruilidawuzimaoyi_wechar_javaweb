package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouKaiPiaoMapper;
import com.myboot.mapper.ZhuanZhangMapper;
import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.pojo.ZhuanZhang;
import com.myboot.service.XiaoShouKaiPiaoService;
import com.myboot.service.ZhuanZhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZhuanZhangImpl implements ZhuanZhangService {

    @Autowired
    private ZhuanZhangMapper mapper;

    @Override
    public List<ZhuanZhang> getAll() {
        List<ZhuanZhang> zhuanZhang = mapper.getAll();
        return zhuanZhang;
    }

    @Override
    public List<ZhuanZhang> queryList(String start_date,String stop_date,String zhuanru, String zhuanchu){
        List<ZhuanZhang> zhuanZhang = mapper.queryList(start_date,stop_date,zhuanru,zhuanchu);
        return zhuanZhang;
    }

    @Override
    public int zhuanZhangAdd(ZhuanZhang zhuanZhang){
        Integer zhuanZhang1 = mapper.zhuanZhangAdd(zhuanZhang);
        return zhuanZhang1;
    }

    @Override
    public int zhuanZhangUpd(ZhuanZhang zhuanZhang) {
        Integer zhuanZhang1 = mapper.zhuanZhangUpd(zhuanZhang);
        return zhuanZhang1;
    }

    @Override
    public int deleteZhuanZhangById(Integer id){
        return mapper.deleteZhuanZhangById(id);
    }

    @Override
    public List<ZhuanZhang> selectZhuanZhangById(Integer id){
        return mapper.selectZhuanZhangById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteZhuanZhangById(id);
        }
        return count;
    }

}
