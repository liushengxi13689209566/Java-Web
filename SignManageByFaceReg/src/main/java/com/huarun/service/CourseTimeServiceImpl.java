package com.huarun.service;

import com.huarun.OtherStructure.CourseTimeStamp;
import com.huarun.dao.CourseTimeMapper;
import com.huarun.pojo.CourseTime;
import com.huarun.utils.ExcelUtil;
import com.huarun.utils.StatusCode;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CourseTimeServiceImpl implements CourseTimeService {
    @Autowired
    private CourseTimeMapper courseTimeMapper;

    //构造需要的 Excel 数据结构
    private ExcelUtil excelUtil = new ExcelUtil();

    private boolean courseTimeCheck(CourseTimeStamp courseTimeStamp) {
        return courseTimeStamp.getId() != null && courseTimeStamp.getCourse_start_timestamp() != null &&
                courseTimeStamp.getCourse_end_timestamp() != null &&
                courseTimeStamp.getCourse_start_timestamp().compareTo(courseTimeStamp.getCourse_end_timestamp()) < 0;
    }

    @Override
    public List<CourseTime> getCourseTimeByCourseID(int course_id) {
        return courseTimeMapper.getCourseTimeByCourseID(course_id);
    }

    @Override
    public int getTotalCountByCourseID(int course_id) {
        return courseTimeMapper.getTotalCountByCourseID(course_id);
    }

    @Override
    public int getTotalCourseCountBeforeToday(int course_id) {
        int ret = 0;
        try {
            ret = courseTimeMapper.getTotalCourseCountBeforeToday(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        } finally {
        }
        return ret;
    }

    @Override
    public List<CourseTime> getCourseTimeByTime(int course_id, String interval_time) {
        return courseTimeMapper.getCourseTimeByTime(course_id, interval_time);
    }

    @Override
    public CourseTime getSignCourseTime(int course_id, long sign_timestamp) {
        CourseTime courseTime = new CourseTime();
        try {
            courseTime = courseTimeMapper.getSignCourseTime(course_id, sign_timestamp / 1000);//单位需要转化为秒
        } catch (PersistenceException e) {
            courseTime = null;
        } finally {
        }
        return courseTime;
    }

    @Override
    public int deleteOneCourse(int course_id) {
        int ret = 0;
        try {
            ret = courseTimeMapper.deleteOneCourse(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public Map<String, Object> importOneCourseTime(MultipartFile file, int course_id) {
        // 初始化结果集
        Map<String, Object> result = new HashMap<>();
        int total = 0;
        int available = 0;
        System.out.println("进入 importOneCourseTime  SERVICE！！");

        // 从 Excel 文件中读取
        List<Object> courseTimeList = excelUtil.excelReader(CourseTimeStamp.class, file);

        if (courseTimeList.size() == 0) {
            System.out.println("00000000000000000000");
        }

        System.out.println("vbbfbvbfv");

        CourseTimeStamp courseTimeStamp;
        for (Object str : courseTimeList) {
            courseTimeStamp = (CourseTimeStamp) str;
            System.out.println("导入的 CourseTime 是:" + courseTimeStamp);
        }
        System.out.println("vbbfbvbfv");

        if (courseTimeList != null) {
            total = courseTimeList.size();
            // 验证每一条记录
            try {
                List<CourseTimeStamp> availableList = new ArrayList<>();

                for (Object object : courseTimeList) {
                    courseTimeStamp = (CourseTimeStamp) object;
                    if (courseTimeCheck(courseTimeStamp)) {

                        System.out.println("course_timestrap == " + courseTimeStamp);

                        availableList.add(courseTimeStamp);
                    }
                }
                //主要查的还是在时间上有没有冲突的问题
                //去重

                for (CourseTimeStamp tt : availableList) {
                    System.out.println("---------导入的 有效的记录是:" + tt);
                }

                HashSet set = new HashSet(availableList);
                availableList.clear();
                //把 HashSet 对象添加至 List 集合
                availableList.addAll(set);

                for (CourseTimeStamp tt : availableList) {
                    System.out.println("+++++++ 导入的 有效的记录是:" + tt);
                }

                // 保存到数据库
                available = availableList.size();
                if (available > 0) {
                    Map params = new HashMap();
                    params.put("availableList", availableList);
                    params.put("course_id", course_id);
                    courseTimeMapper.insertBatch(params);
                }
            } catch (PersistenceException e) {
                result.put("status_code", StatusCode.INSERT_BATCH_ERROR);
                return result;
            }
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("total", total);
        result.put("available", available);
        return result;
    }
}
