package com.huarun.service;

import com.huarun.dao.CourseStudentMapper;
import com.huarun.dao.SignCaseMapper;
import com.huarun.dao.StudentMapper;
import com.huarun.exception.CourseStudentServiceException;
import com.huarun.pojo.CourseStudent;
import com.huarun.test.StuIDpojo;
import com.huarun.utils.ExcelUtil;
import org.apache.commons.lang.StringUtils;
// mybatis 总的异常处理
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseStudentServiceImpl implements CourseStudentService {
    @Autowired
    private CourseStudentMapper courseStudentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SignCaseMapper signCaseMapper;

    //构造需要的 Excel 数据结构
    private ExcelUtil excelUtil = new ExcelUtil();

    @Override
    public List<CourseStudent> queryMyCourseIDByUserID(String userID) {
        return courseStudentMapper.queryMyCourseIDByUserID(userID);
    }

    @Override
    public List<CourseStudent> queryOneCourseAllStudent(int course_id) {
        return courseStudentMapper.queryOneCourseAllStudent(course_id);
    }

    @Override
    public int delOneStudentInCourse(int course_id, String student_id) throws CourseStudentServiceException {
        try {
            return courseStudentMapper.delOneStudentInCourse(course_id, student_id);
        } catch (PersistenceException e) {
            throw new CourseStudentServiceException(e);
        }
    }

    @Override
    public int deleteOneCourse(int course_id) {
        int ret = 0;
        try {
            ret = courseStudentMapper.deleteOneCourse(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public int deleteOneStudent(String userID) {
        int ret = 0;
        try {
            ret = courseStudentMapper.deleteOneStudent(userID);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public int addOneStudentInCourse(int course_id, String student_id) throws CourseStudentServiceException {
        try {
            return courseStudentMapper.addOneStudentInCourse(course_id, student_id);
        } catch (PersistenceException e) {
            throw new CourseStudentServiceException(e);
        }
    }

    //这里需要处理一下 excel 中输入 04161173 会没有0的情况
    private boolean customerCheck(StuIDpojo stuIDpojo1) {
        boolean ret = StringUtils.isNumeric(stuIDpojo1.getId());
        System.out.println("ret == " + ret);
        System.out.println("stuIDpojo1.getId() == " + stuIDpojo1.getId());
        System.out.println("length  == " + stuIDpojo1.getId().length());

        if (ret) {
            if (stuIDpojo1.getId().length() == 7) {
                stuIDpojo1.setId('0' + stuIDpojo1.getId());
            }
        }
        return ret;
    }

    @Override
    public Map<String, Object> importOneCourseStudents(MultipartFile file, int course_id) throws CourseStudentServiceException, IOException {
        // 初始化结果集
        Map<String, Object> result = new HashMap<>();
        int total = 0;
        int available = 0;
        System.out.println("进入importOneCourseStudents    SERVICE！！");

        // 从 Excel 文件中读取
        List<Object> studentsID = excelUtil.excelReader(StuIDpojo.class, file);

        System.out.println("vbbfbvbfv");

        StuIDpojo stuIDpojo;
        for (Object str : studentsID) {
            stuIDpojo = (StuIDpojo) str;
            System.out.println("导入的ID是:" + stuIDpojo);
        }

        if (studentsID != null) {
            total = studentsID.size();

            // 验证每一条记录
            try {
                StuIDpojo stuIDpojo1;
                List<StuIDpojo> availableList = new ArrayList<>();
                for (Object object : studentsID) {
                    stuIDpojo1 = (StuIDpojo) object;
                    if (customerCheck(stuIDpojo1)) {
                        System.out.println("stuIDpojo1 == " + stuIDpojo1);
                        if (studentMapper.getUserInfoByStuID(stuIDpojo1.getId()) != null) {
                            availableList.add(stuIDpojo1);
                        }
                    }
                }
                for (StuIDpojo tt : availableList) {
                    System.out.println("导入的有效的ID是:" + tt);
                }

                // 保存到数据库
                available = availableList.size();
                if (available > 0) {
                    Map params = new HashMap();
                    params.put("availableList", availableList);
                    params.put("course_id", course_id);
                    courseStudentMapper.insertBatch(params);
                    //记录考勤数据
                    for (StuIDpojo tt : availableList) {
                        signCaseMapper.initSignCaseOneStudentOneCourse(tt.getId(), course_id);
                    }
                }
            } catch (PersistenceException e) {
                throw new CourseStudentServiceException(e);
            }
        }
        result.put("total", total);
        result.put("available", available);
        return result;
    }
}
