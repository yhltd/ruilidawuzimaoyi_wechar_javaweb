package com.myboot.service.impl;

import com.myboot.mapper.KuCunMapper;
import com.myboot.pojo.KuCun;
import com.myboot.service.KuCunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KuCunImpl implements KuCunService {

    @Autowired
    private KuCunMapper mapper;

    @Override
    public List<KuCun> getKuCun() {
        List<KuCun> kuCun = mapper.getKuCun();
        return kuCun;
    }

    @Override
    public List<KuCun> getKuCunByCangKu(String cangku,String type) {
        List<KuCun> kuCun = mapper.getKuCunByCangKu(cangku,type);
        return kuCun;
    }


}
