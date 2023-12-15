package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouBaoJiaItemMapper;
import com.myboot.mapper.XiaoShouBaoJiaMapper;
import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouBaoJiaAdd;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.service.XiaoShouBaoJiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoShouBaoJiaImpl implements XiaoShouBaoJiaService {

    @Autowired
    private XiaoShouBaoJiaMapper mapper;

    @Autowired
    private XiaoShouBaoJiaItemMapper itemMapper;

    @Override
    public List<XiaoShouBaoJia> getAll() {
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.getAll();
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> getAllByName(String yewuyuan) {
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.getAllByName(yewuyuan);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> getAllByShenHe(String shenhe) {
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.getAllByShenHe(shenhe);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai){
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.queryList(start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> queryListByName(String yewuyuan,String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai){
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.queryListByName(yewuyuan,start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return xiaoShouBaoJia;
    }

    @Override
    public int baoJiaAdd(XiaoShouBaoJia xiaoShouBaoJia){
        Integer baoJia = mapper.baoJiaAdd(xiaoShouBaoJia);
        return baoJia;
    }

    @Override
    public int baoJiaUpd(XiaoShouBaoJia xiaoShouBaoJia){
        Integer baoJia = mapper.baoJiaUpd(xiaoShouBaoJia);
        return baoJia;
    }

    @Override
    public int deleteBaoJiaById(Integer id){
        return mapper.deleteBaoJiaById(id);
    }

    @Override
    public List<XiaoShouBaoJia> selectBaoJiaById(Integer id){
        return mapper.selectBaoJiaById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteBaoJiaById(id);
            itemMapper.deleteByBaoJiaId(id);
        }
        return count;
    }

    @Override
    public List<XiaoShouBaoJia> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    @Override
    public List<XiaoShouBaoJia> shenheList(String name){
        return mapper.shenheList(name);
    }


    //ztt 审核
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int baoJiaShenHe(List<XiaoShouBaoJia> list) {
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            count += mapper.baoJiaShenHe(list.get(i).getId(),list.get(i).getShenheList(),list.get(i).getShenheZhuangtai());
        }
        return count;
    }
    //ztt end


}
