package com.huarun.service;

import com.huarun.dao.BasDictMapper;
import com.huarun.pojo.BasDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasDictServiceImpl implements BasDictService {
    @Autowired
    // service 层调用 dao 层: 组合dao层
    private BasDictMapper basDictMapper;

    @Override
    public int addBasDict(BasDict basDict) {
        return basDictMapper.addBasDict(basDict);
    }

    @Override
    public int deleteBasDictById(int id) {
        return basDictMapper.deleteBasDictById(id);
    }

    @Override
    public int updateBasDict(BasDict basDict) {
        return basDictMapper.updateBasDict(basDict);
    }

    @Override
    public BasDict queryBasDictById(int id) {
        return basDictMapper.queryBasDictById(id);
    }

    @Override
    public List<BasDict> queryAllBasDict() {
        return basDictMapper.queryAllBasDict();
    }
}
