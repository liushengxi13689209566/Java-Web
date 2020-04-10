package com.huarun.service;

import com.huarun.dao.MajorMapper;
import com.huarun.pojo.MajorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public MajorInfo getMajorInfoByID(int major_id) {
        return majorMapper.getMajorInfoByID(major_id);
    }
}
