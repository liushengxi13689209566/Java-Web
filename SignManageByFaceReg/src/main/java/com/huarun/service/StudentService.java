package com.huarun.service;

import com.huarun.exception.CourseStudentServiceException;
import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StudentService {

    //需要学生的所有信息
    StudentDO getStudentInfoByStuID(@Param("userID") String userID) throws StudentServiceException;

    //得到 某专业某个班级学生 的 所有信息
    List<StudentDO> getStudentInfoByMajorIDAndClassID(@Param("major_id") int major_id,
                                                      @Param("class_id") int class_id) throws StudentServiceException;

    //查询所有学生的所有信息
    List<StudentDO> getAllStudentsInfo() throws StudentServiceException;

    //得到最大的学号
    String getMaxStuID() throws StudentServiceException;

    //添加一个学生，密码默认为：身份证号后六位
    int addOneStudent(@Param("studentDO") StudentDO studentDO);

    //更新学生信息
    int updateOneStudent(@Param("studentDO") StudentDO studentDO);


    //删除一名学生
    int deleteOneStudent(@Param("userID") String userID);


    /**
     * 从文件中导入学生信息到一门课程中
     *
     * @param file 导入信息的文件
     * @return 返回一个Map，其中：key为total代表导入的总记录数，key为available代表有效导入的记录数
     */
    Map<String, Object> importAllStudentsInfo(MultipartFile file) throws IOException;
}
