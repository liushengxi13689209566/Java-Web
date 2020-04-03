package com.huarun.dao;

import com.huarun.pojo.TestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper {
    List<TestInfo> queryallbyStr(@Param("num") String num);

    List<TestInfo> queryallbyname(@Param("name") String name);

    List<TestInfo> queryallbyID(int id);

    List<TestInfo> queryall();
}
