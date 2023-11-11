package com.myboot.pojo;

import lombok.Data;

@Data
public class ContactPerson {
    private Integer cpId;
    private String cpName;
    private String cpDepartment;
    private String cpDuties;
    private String cpPhone;
    private String cpAddress;
    private String cpType;
}
