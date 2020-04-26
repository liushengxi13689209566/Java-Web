package com.huarun.service;

import com.huarun.dao.MajorClassMapper;
import com.huarun.pojo.MajorClassDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorClassServiceImpl implements MajorClassService {
    @Autowired
    private MajorClassMapper majorClassMapper;

    @Override
    public List<MajorClassDO> getClassListByMajorID(int major_id) {
        return majorClassMapper.getClassListByMajorID(major_id);
    }
}
