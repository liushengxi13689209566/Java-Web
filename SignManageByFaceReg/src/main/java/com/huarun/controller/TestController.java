package com.huarun.controller;

import com.huarun.pojo.TestInfo;
import com.huarun.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller

public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test")
    public void test() {
        System.out.println("chbsbdvbsd");

        List<TestInfo> list = testService.queryVarbinary();

//        if (list.isEmpty()) {
//            System.out.println("cvvvsvsv");
//        }
        for (TestInfo ii : list) {
            System.out.println(ii);
        }

//        List<TestInfo> list1 = testService.queryallbyStr("222");
//
//        if (list.isEmpty()) {
//            System.out.println("cvvvsvsv");
//        }
//        for (TestInfo ii : list1) {
//            System.out.println(ii);
//        }
    }
}
