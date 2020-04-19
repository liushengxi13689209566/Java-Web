package com.huarun.service;

import com.huarun.dao.ClassMapper;
import com.huarun.pojo.ClassDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public ClassDO getClassInfoByClassID(int class_id) {
        return classMapper.getClassInfoByClassID(class_id);
    }

    @Override
    public List<ClassDO> getAllMajor() {
        return classMapper.getAllMajor();
    }
}
