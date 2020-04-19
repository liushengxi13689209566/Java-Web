package com.huarun.service;

import com.huarun.dao.MajorMapper;
import com.huarun.pojo.MajorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public MajorDO getMajorInfoByID(int major_id) {
        return majorMapper.getMajorInfoByID(major_id);
    }

    @Override
    public List<MajorDO> getAllMajor() {
        return majorMapper.getAllMajor();
    }
}
