package com.huarun.service;

import com.huarun.OtherStructure.CourseSignCaseRecord;
import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSignCaseRecordServiceImpl implements CourseSignCaseRecordService {
    @Autowired
    private SignCaseService signCaseService;
    @Autowired
    private CourseTimeService courseTimeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;

    @Override
    public List<CourseSignCaseRecord> getCourseSignCaseRecord(int course_id, int major_id, int class_id, String interval_time) throws StudentServiceException {
        List<CourseSignCaseRecord> rows = new ArrayList<>(); //最终会返回的数据

        MajorDO majorDO = majorService.getMajorInfoByID(major_id);
        ClassDO classDO = classService.getClassInfoByClassID(class_id);
        CourseInfo courseInfo = courseService.queryCourseByID(course_id);

        List<StudentDO> studentList = studentService.getStudentInfoByMajorIDAndClassID(major_id, class_id);
        //这个班级还没有人
        if (studentList.isEmpty()) {
            return rows;
        }
        System.out.println("studentList == " + studentList);
        System.out.println("studentList.size() == " + studentList.size());

        //得到 stuID;
        List<String> bitmapList = new ArrayList<>();
        for (int j = 0; j < studentList.size(); j++) {
            System.out.println("studentList.get(j).getId(), course_id == " + studentList.get(j).getId() + "   " + course_id);
            String bitmap = signCaseService.getSignCaseByUserIDAndCourseID(studentList.get(j).getId(), course_id).getSign_case_bitmap();
            System.out.println("bitmap == " + bitmap);
            //如果没有该同学没有记录 ：考勤记录
            if (!bitmap.isEmpty()) {
                bitmapList.add(bitmap);
            }
        }
        System.out.println("bitmapList == " + bitmapList);

        //先根据时间间隔查课程表信息；
        List<CourseTime> allTimeList = courseTimeService.getCourseTimeByCourseID(course_id);
        List<CourseTime> timeList = courseTimeService.getCourseTimeByTime(course_id, interval_time);
        if (timeList.isEmpty()) {
            return rows;
        }
        int idx_start = allTimeList.size() - timeList.size();

        System.out.println("allTimeList == " + allTimeList);
        System.out.println("timeList == " + timeList);
        System.out.println("idx_start == " + idx_start);

        int late_count = 0; //迟到人数
        int truancy_count = 0; //旷课人数
        int success_count = 0; //出勤人数

        //统计每一天
        for (int i = idx_start; i < idx_start + timeList.size(); i++) {
            //然后查各个学生的考勤情况并统计。
            for (int j = 0; j < bitmapList.size(); j++) {

                System.out.println("bitmapList.get(j).charAt(i) == " + bitmapList.get(j).charAt(i));

                if (bitmapList.get(j).charAt(i) == '0') {
                    truancy_count++;
                } else if (bitmapList.get(j).charAt(i) == '1') {
                    success_count++;
                } else {
                    late_count++;
                }
//                System.out.println("vfhbvfbhvbf"+truancy_count+"  "+ );
            }
            System.out.println("i == " + i);
            System.out.println("truancy_count == " + truancy_count);
            System.out.println("success_count == " + success_count);
            System.out.println("late_count == " + late_count);

            rows.add(new CourseSignCaseRecord(0, major_id, majorDO.getMajor_name(),
                    class_id, classDO.getClass_name(),
                    course_id, courseInfo.getCourse_name(),
                    timeList.get(i - idx_start).getCourse_start_timestamp(),
                    late_count, truancy_count, success_count));

            System.out.println("rows == " + rows);

            late_count = 0;
            truancy_count = 0;
            success_count = 0;
        }
        System.out.println("出循环 ");
        System.out.println("rows == " + rows);
        return rows;
    }
}
