package com.huarun.service;

import com.huarun.dao.ClassMapper;
import com.huarun.pojo.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public ClassInfo getClassInfoByClassID(int class_id) {
        return classMapper.getClassInfoByClassID(class_id);
    }
}
