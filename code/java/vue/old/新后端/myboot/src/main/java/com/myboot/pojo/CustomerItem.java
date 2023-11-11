package com.myboot.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * customer_item
 * @author 
 */
@Data
public class CustomerItem implements Serializable {
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 职务
     */
    private String zhiwu;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 类型
     */
    private String type;

    private String customerId;

    private static final long serialVersionUID = 1L;
}