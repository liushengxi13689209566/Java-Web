package com.huarun.service;

import com.huarun.OtherStructure.CourseSignCaseRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSignCaseRecordServiceImpl implements CourseSignCaseRecordService {
    @Override
    public List<CourseSignCaseRecord> getCourseSignCaseRecord(int course_id, int major_id, int class_id) {
        List<CourseSignCaseRecord> rows = new ArrayList<>(); //最终会返回的数据

        return rows;
    }
}
