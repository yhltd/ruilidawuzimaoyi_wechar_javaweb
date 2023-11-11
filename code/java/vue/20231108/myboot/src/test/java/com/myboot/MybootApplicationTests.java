package com.myboot;

import com.myboot.pojo.Peizhi;
import com.myboot.pojo.PeizhiTypeEnum;
import com.myboot.service.PeizhiService;
import com.myboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybootApplicationTests {


    @Autowired
    private PeizhiService pService;

    @Test
    void contextLoads() {
        List<Peizhi> peizhis = pService.queryAllByType("仓库");
        System.out.println(peizhis);
    }




}
