package com.huarun.service;

import com.huarun.pojo.ProStorage;

import java.util.List;

public interface ProStorageService {
    //增
    int addProStorage(com.huarun.pojo.ProStorage proStorage);

    //删
    int deleteProStorageById(int id);

    //改
    int updateProStorage(ProStorage proStorages);

    //查
    ProStorage queryProStorageById(int id);

    //查询全部
    List<ProStorage> queryAllProStorage();
}
