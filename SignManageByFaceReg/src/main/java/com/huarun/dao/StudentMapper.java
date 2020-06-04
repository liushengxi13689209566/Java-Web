package com.huarun.dao;

import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentDO;
import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    //只要 name,password
    UserInfo getUserInfoByStuID(@Param("userID") String userID);

    //需要学生的所有信息
    StudentDO getStudentInfoByStuID(@Param("userID") String userID);

    //查询所有学生的所有信息
    List<StudentDO> getAllStudentsInfo();

    //得到最大的学号
    String getMaxStuID();

    //添加一个学生，密码默认为：身份证号后六位
    int addOneStudent(@Param("studentDO") StudentDO studentDO);

    // 修改密码
    void stuPasswordModify(@Param("userID") String userID, @Param("new_pass") String new_pass);

    //得到 某专业某个班级学生 的 所有信息
    List<StudentDO> getStudentInfoByMajorIDAndClassID(@Param("major_id") int major_id,
                                                      @Param("class_id") int class_id);


    //更新学生信息
    int updateOneStudent(@Param("studentDO") StudentDO studentDO);

    //删除一名学生
    int deleteOneStudent(@Param("userID") String userID);
}
