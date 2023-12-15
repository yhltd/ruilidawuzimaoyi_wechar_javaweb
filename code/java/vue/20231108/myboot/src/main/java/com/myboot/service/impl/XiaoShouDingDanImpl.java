package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouDingDanItemMapper;
import com.myboot.mapper.XiaoShouDingDanMapper;
import com.myboot.pojo.*;
import com.myboot.service.XiaoShouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoShouDingDanImpl implements XiaoShouDingDanService {

    @Autowired
    private XiaoShouDingDanMapper mapper;

    @Autowired
    private XiaoShouDingDanItemMapper itemMapper;

    @Override
    public List<XiaoShouDingDan> getAll() {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAll();
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> getAllByName(String yewuyuan) {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAllByName(yewuyuan);
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> getAllByShenHe(String shenhe) {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAllByShenHe(shenhe);
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai){
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.queryList(start_date,stop_date,kehu,shenhe_zhuangtai);
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> queryListByName(String yewuyuan,String start_date,String stop_date,String kehu, String shenhe_zhuangtai){
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.queryList(start_date,stop_date,kehu,shenhe_zhuangtai);
        return xiaoShouDingDan;
    }

    @Override
    public int xiaoShouAdd(XiaoShouDingDan xiaoShouDingDan){
        Integer dingDan = mapper.xiaoShouAdd(xiaoShouDingDan);
        return dingDan;
    }

    @Override
    public int xiaoShouUpd(XiaoShouDingDan xiaoShouDingDan){
        Integer dingDan = mapper.xiaoShouUpd(xiaoShouDingDan);
        return dingDan;
    }

    @Override
    public int deleteXiaoShouById(Integer id){
        return mapper.deleteXiaoShouById(id);
    }

    @Override
    public List<XiaoShouDingDan> selectXiaoShouById(Integer id){
        return mapper.selectXiaoShouById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteXiaoShouById(id);
            itemMapper.deleteByXiaoShouId(id);
        }
        return count;
    }

    public List<XiaoShouDingDan> selectWeiFu(){
        return mapper.selectWeiFu();
    }

    @Override
    public List<XiaoShouDingDan> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    @Override
    public List<XiaoShouDingDan> shenHeList(String name){
        return mapper.shenHeList(name);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingShangPin(Integer id, String bianhao) {
        return mapper.xiangQingShangPin(id,bianhao);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingChuKu(String bianhao) {
        return mapper.xiangQingChuKu(bianhao);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingShouKuan(String bianhao) {
        return mapper.xiangQingShouKuan(bianhao);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingKaiPiao(String bianhao) {
        return mapper.xiangQingKaiPiao(bianhao);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingCaiGou(String bianhao) {
        return mapper.xiangQingCaiGou(bianhao);
    }

    @Override
    public List<XiaoShouDingDanXiangQing> xiangQingXuKaiPiao(String bianhao) {
        return mapper.xiangQingXuKaiPiao(bianhao);
    }

    //ztt 审核
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int xiaoShouShenHe(List<XiaoShouDingDan> list,String type) {
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            count += mapper.dingDanShenHe(list.get(i).getId(),list.get(i).getShenheList(),list.get(i).getShenheZhuangtai());
        }

        return count;
    }
    @Override
    public List<XiaoShouDingDan> getAllYiShen() {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAllYiShen();
        return xiaoShouDingDan;
    }
//ztt end
}
