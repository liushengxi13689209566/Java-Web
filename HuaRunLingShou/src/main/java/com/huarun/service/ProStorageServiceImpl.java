package com.huarun.service;

import com.huarun.dao.ProStorageMapper;
import com.huarun.pojo.ProStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProStorageServiceImpl implements ProStorageService {

    @Autowired
    // service 层调用 dao 层: 组合dao层
    private ProStorageMapper proStorageMapper;

    public void setProStorageMapper(ProStorageMapper proStorageMapper) {
        this.proStorageMapper = proStorageMapper;
    }

    @Override
    public int addProStorage(ProStorage proStorage) {
        return proStorageMapper.addProStorage(proStorage);
    }

    @Override
    public int deleteProStorageById(int id) {
        return proStorageMapper.deleteProStorageById(id);
    }

    @Override
    public int updateProStorage(ProStorage proStorages) {
        return proStorageMapper.updateProStorage(proStorages);
    }

    @Override
    public ProStorage queryProStorageById(int id) {
        return proStorageMapper.queryProStorageById(id);
    }

    @Override
    public List<ProStorage> queryAllProStorage() {
        return proStorageMapper.queryAllProStorage();
    }
}
