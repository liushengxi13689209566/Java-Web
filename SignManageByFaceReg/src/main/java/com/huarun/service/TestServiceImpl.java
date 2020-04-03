package com.huarun.service;

import com.huarun.dao.TestMapper;
import com.huarun.pojo.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestInfo> queryallbyStr(String num) {
        System.out.println("成功进入 service 层 ");
        System.out.println("num == " + num);
        return testMapper.queryallbyStr(num);
    }

    @Override
    public List<TestInfo> queryall() {
        return testMapper.queryall();
    }
}
