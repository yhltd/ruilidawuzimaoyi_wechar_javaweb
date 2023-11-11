package com.myboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String cId;
    private String cName;
    private String cFenl;
    private String cBoss;
    private String cLevel;
    private String cCustLevel;
    private String cRegion;
    private String cAddress;
    private String cText;
    private String salesMan;
    private String cFile;
}
