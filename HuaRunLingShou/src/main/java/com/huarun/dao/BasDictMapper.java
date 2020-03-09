package com.huarun.dao;

import com.huarun.pojo.BasDict;

import java.util.List;

public interface BasDictMapper {
    //增
    int addBasDict(BasDict basDict);

    //删
    int deleteBasDictById(int id);

    //改
    int updateBasDict(BasDict basDict);

    //查
    BasDict queryBasDictById(int id);

    //查询全部
    List<BasDict> queryAllBasDict();
}
