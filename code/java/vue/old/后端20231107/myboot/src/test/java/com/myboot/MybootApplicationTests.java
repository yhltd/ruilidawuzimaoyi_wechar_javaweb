package com.myboot;

import com.myboot.pojo.AccountingUnit;
import com.myboot.pojo.User;
import com.myboot.service.AccountingUnitService;
import com.myboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybootApplicationTests {


    @Autowired
    private AccountingUnitService aService;

    @Test
    void contextLoads() {
       List<AccountingUnit> accountingUnits = aService.getAll();
        System.out.println(accountingUnits);
    }




}
