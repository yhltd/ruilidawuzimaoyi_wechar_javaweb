package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CustomerItemService;
import com.myboot.service.CustomerService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String bianhao = data.getString("bianhao");
        String name = data.getString("name");
        List<Customer> customers = service.queryList(bianhao,name);
        return ResponseCommon.success(customers);
    }

    @PostMapping("/customerAdd")
    public String customerAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        Customer customer = data.getObject("head", Customer.class);
        List<CustomerItem> customerItems = body.toJavaList(CustomerItem.class);
        Customer dingDan = new Customer();
        BeanUtils.copyProperties(customer, dingDan);
        Integer this_id = service.customerAdd(dingDan);
        for(int i=0; i<customerItems.size(); i++){
            customerItems.get(i).setCustomerId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(customerItems.get(i));
        }
        return ResponseCommon.success(customer);
    }

    @PostMapping("/customerUpd")
    public String customerUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        Customer customer = data.getObject("head", Customer.class);
        List<CustomerItem> customerItems = body.toJavaList(CustomerItem.class);
        Customer dingDan = new Customer();
        BeanUtils.copyProperties(customer, dingDan);
        Integer this_id = service.customerUpd(dingDan);
        itemService.itemDelById(dingDan.getId());
        for(int i=0; i<customerItems.size(); i++){
            customerItems.get(i).setCustomerId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(customerItems.get(i));
        }
        return ResponseCommon.success(customer);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectById")
    public String selectById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        Integer id = data.getInteger("id");
        List<Customer> customer = service.selectById(data.getInteger("id"));
        CustomerAdd customerAdd = new CustomerAdd();
        customerAdd.setId(customer.get(0).getId());
        customerAdd.setBianhao(customer.get(0).getBianhao());
        customerAdd.setName(customer.get(0).getName());
        customerAdd.setType(customer.get(0).getType());
        customerAdd.setShangjiDanwei(customer.get(0).getShangjiDanwei());
        customerAdd.setKehuDengji(customer.get(0).getKehuDengji());
        customerAdd.setJiageDengji(customer.get(0).getJiageDengji());
        customerAdd.setSuozaiDiqu(customer.get(0).getSuozaiDiqu());
        customerAdd.setDizhi(customer.get(0).getDizhi());
        customerAdd.setBeizhu(customer.get(0).getBeizhu());
        customerAdd.setYewuyuan(customer.get(0).getYewuyuan());
        customerAdd.setShoujianName(customer.get(0).getShoujianName());
        customerAdd.setShoujianPhone(customer.get(0).getShoujianPhone());
        customerAdd.setShoujianDizhi(customer.get(0).getShoujianDizhi());
        customerAdd.setKaipiaoDanwei(customer.get(0).getKaipiaoDanwei());
        customerAdd.setShibiehao(customer.get(0).getShibiehao());
        customerAdd.setKaipiaoDizhi(customer.get(0).getKaipiaoDizhi());
        customerAdd.setKaipiaoDianhua(customer.get(0).getKaipiaoDianhua());
        customerAdd.setKaipiaoYinhang(customer.get(0).getKaipiaoYinhang());
        customerAdd.setKaipiaoZhanghao(customer.get(0).getKaipiaoZhanghao());
        customerAdd.setItemList(itemService.selectByCustomerId(data.getInteger("id")));

        if (customer == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(customerAdd);
    }

    @RequestMapping("/delCustomer")
    public String delCustomer(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.deleteById(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }


}
