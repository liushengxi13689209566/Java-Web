package com.huarun.service;

import com.huarun.OtherStructure.CourseSignCaseRecord;
import com.huarun.exception.StudentServiceException;

import java.util.List;

public interface CourseSignCaseRecordService {
    //根据 course_id ,major_id,class_id 生成最终的返回数据
    List<CourseSignCaseRecord> getCourseSignCaseRecord(int count, int course_id, int major_id, int class_id, String interval_time) throws StudentServiceException;
}
