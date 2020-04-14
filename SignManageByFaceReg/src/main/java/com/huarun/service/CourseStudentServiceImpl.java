package com.huarun.service;

import com.huarun.dao.CourseStudentMapper;
import com.huarun.dao.StudentMapper;
import com.huarun.exception.CourseStudentServiceException;
import com.huarun.pojo.CourseStudent;
import com.huarun.utils.ExcelUtil;
import org.apache.commons.lang.StringUtils;
// mybatis 总的异常处理
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private ExcelUtil excelUtil;

    @Override
    public List<CourseStudent> queryMyCourseIDByUserID(String userID) {
        return courseStudentMapper.queryMyCourseIDByUserID(userID);
    }

    @Override
    public List<CourseStudent> queryOneCourseAllStudent(int course_id) {
        return courseStudentMapper.queryOneCourseAllStudent(course_id);
    }

    @Override
    public int delOneStudentInCourse(int course_id, String student_id) {
        return courseStudentMapper.delOneStudentInCourse(course_id, student_id);
    }

    //这里需要处理一下excel中输入 04161173 会没有0的情况
    private boolean customerCheck(String stu_id) {
        boolean ret = StringUtils.isNumeric(stu_id);
        if (ret) {
            if (stu_id.length() == 7) {
                stu_id = '0' + stu_id;
            }
        }
        return ret;
    }

    @Override
    public Map<String, Object> importOneCourseStudents(MultipartFile file, int course_id) throws CourseStudentServiceException {
        // 初始化结果集
        Map<String, Object> result = new HashMap<>();
        int total = 0;
        int available = 0;

        // 从 Excel 文件中读取
        List<Object> studentsID = excelUtil.excelReader(String.class, file);
        if (studentsID != null) {
            total = studentsID.size();

            // 验证每一条记录
            try {
                List<String> availableList = new ArrayList<>();
                for (Object object : studentsID) {
                    String id = (String) object;
                    if (customerCheck(id)) {
                        if (studentMapper.getUserInfoByStuID(id) != null)
                            availableList.add(id);
                    }
                }

                for (String str : availableList) {
                    System.out.println("导入的ID是:" + str);
                }

                // 保存到数据库
                available = availableList.size();
                if (available > 0) {
                    courseStudentMapper.insertBatch(availableList, course_id);
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
