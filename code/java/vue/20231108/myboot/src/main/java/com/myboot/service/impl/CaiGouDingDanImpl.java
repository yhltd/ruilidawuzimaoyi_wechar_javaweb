package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanAdd;
import com.myboot.pojo.CaiGouDingDanXiangQing;
import com.myboot.service.CaiGouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaiGouDingDanImpl implements CaiGouDingDanService {

    @Autowired
    private CaiGouDingDanMapper mapper;

    @Autowired
    private CaiGouDingDanItemMapper itemMapper;

    @Override
    public List<CaiGouDingDan> getAll() {
        List<CaiGouDingDan> caiGouDingDan = mapper.getAll();
        return caiGouDingDan;
    }

    @Override
    public List<CaiGouDingDan> getAllByShenHe(String shenhe) {
        List<CaiGouDingDan> caiGouDingDan = mapper.getAllByShenHe(shenhe);
        return caiGouDingDan;
    }

    public List<CaiGouDingDan> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai){
        List<CaiGouDingDan> caiGouDingDan = mapper.queryList(start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return caiGouDingDan;
    }

    public int caiGouDingDanAdd(CaiGouDingDan caiGouDingDan){
        int dingDan = mapper.caiGouDingDanAdd(caiGouDingDan);
        return dingDan;
    }

    public int caiGouDingDanUpd(CaiGouDingDan caiGouDingDan){
        int dingDan = mapper.caiGouDingDanUpd(caiGouDingDan);
        return dingDan;
    }

    public int deleteByCaiGouId(Integer id){
        return mapper.deleteByCaiGouId(id);
    }

    public List<CaiGouDingDan> selectByCaiGouId(Integer id){
        return mapper.selectByCaiGouId(id);
    }

    public List<CaiGouDingDan> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteByCaiGouId(id);
            itemMapper.deleteByCaiGouId(id);
        }
        return count;
    }

    public List<CaiGouDingDan> selectWeiFu(){
        return mapper.selectWeiFu();
    }

    //ztt 审核
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int caiGouShenHe(List<CaiGouDingDan> list,String type) {
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            count += mapper.caiGouShenHe(list.get(i).getId(),list.get(i).getShenheList(),list.get(i).getShenheZhuangtai());
        }
        return count;
    }
    @Override
    public List<CaiGouDingDan> getAllYiShen() {
        List<CaiGouDingDan> caiGouDingDan = mapper.getAllYiShen();
        return caiGouDingDan;
    }
    //ztt end
    @Override
    public List<CaiGouDingDanXiangQing> xiangQingShangPin(Integer id, String bianhao) {
        return mapper.xiangQingShangPin(id,bianhao);
    }

    @Override
    public List<CaiGouDingDanXiangQing> xiangQingChuKu(String bianhao) {
        return mapper.xiangQingChuKu(bianhao);
    }

    @Override
    public List<CaiGouDingDanXiangQing> xiangQingShouKuan(String bianhao) {
        return mapper.xiangQingShouKuan(bianhao);
    }

    @Override
    public List<CaiGouDingDanXiangQing> xiangQingKaiPiao(String bianhao) {
        return mapper.xiangQingKaiPiao(bianhao);
    }

    @Override
    public List<CaiGouDingDanXiangQing> xiangQingXuKaiPiao(String bianhao) {
        return mapper.xiangQingXuKaiPiao(bianhao);
    }


}
