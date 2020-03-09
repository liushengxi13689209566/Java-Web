package com.huarun.dao;


import com.huarun.pojo.ProStorage;

import java.util.List;

public interface ProStorageMapper {
    //增
    int addProStorage(com.huarun.pojo.ProStorage proStorage);

    //删
    int deleteProStorageById(int id);

    //改
    int updateProStorage(com.huarun.pojo.ProStorage proStorages);

    //查
    com.huarun.pojo.ProStorage queryProStorageById(int id);

    //查询全部
    List<com.huarun.pojo.ProStorage> queryAllProStorage();
}