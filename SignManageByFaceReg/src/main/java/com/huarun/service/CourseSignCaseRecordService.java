package com.huarun.service;

import com.huarun.OtherStructure.CourseSignCaseRecord;

import java.util.List;

public interface CourseSignCaseRecordService {
    //根据 course_id ,major_id,class_id 生成最终的返回数据
    List<CourseSignCaseRecord> getCourseSignCaseRecord(int course_id, int major_id, int class_id);
}
