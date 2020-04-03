package com.huarun.service;

import com.huarun.pojo.TestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestService {
    List<TestInfo> queryallbyStr(String num);
    List<TestInfo> queryall();
}
