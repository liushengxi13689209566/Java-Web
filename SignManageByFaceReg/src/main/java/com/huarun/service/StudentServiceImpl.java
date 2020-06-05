package com.huarun.service;

import com.baidu.aip.util.Base64Util;
import com.huarun.OtherStructure.FaceUserInfo;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.OtherStructure.UserAllFaceInfo;
import com.huarun.baidu.FaceRegObject;
import com.huarun.dao.StudentMapper;
import com.huarun.exception.CourseStudentServiceException;
import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentDO;


import com.huarun.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;

    //构造需要的 Excel 数据结构
    private ExcelUtil excelUtil = new ExcelUtil();

    @Override
    public StudentDO getStudentInfoByStuID(String userID) throws StudentServiceException {
        try {
            return studentMapper.getStudentInfoByStuID(userID);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public List<StudentDO> getStudentInfoByMajorIDAndClassID(int major_id, int class_id) throws StudentServiceException {
        try {
            return studentMapper.getStudentInfoByMajorIDAndClassID(major_id, class_id);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public List<StudentDO> getAllStudentsInfo() throws StudentServiceException {
        try {
            return studentMapper.getAllStudentsInfo();
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public String getMaxStuID() throws StudentServiceException {
        try {
            return studentMapper.getMaxStuID();
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public int addOneStudent(StudentDO studentDO) {
        int ret = 0;
        try {
            ret = studentMapper.addOneStudent(studentDO);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public int updateOneStudent(StudentDO studentDO) {
        int ret = 0;
        try {
            ret = studentMapper.updateOneStudent(studentDO);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public int deleteOneStudent(String userID) {
        int ret = 0;
        try {
            ret = studentMapper.deleteOneStudent(userID);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    private boolean userInfoCheck(UserAllFaceInfo userInfo) {
        //查空与身份证号
        return userInfo.getName() != null && userInfo.getSex() != null &&
                userInfo.getIdentity_card() != null && StringUtils.isNumeric(userInfo.getIdentity_card()) != false;
    }

    @Override
    public Map<String, Object> importAllStudentsInfo(MultipartFile file) throws IOException {
        // 初始化结果集
        Map<String, Object> result = new HashMap<>();
        int total = 0;
        int available = 0;
        System.out.println("进入 importAllStudentsInfo    SERVICE！！");

        // 从 Excel 文件中读取
        List<Object> userAllFaceInfoList = excelUtil.excelReader(UserAllFaceInfo.class, file);

        System.out.println("vbbfbvbfv");

        UserAllFaceInfo tt;
        for (Object str : userAllFaceInfoList) {
            tt = (UserAllFaceInfo) str;
            System.out.println("导入的一些信息是:" + tt);
        }

        if (userAllFaceInfoList != null) {
            total = userAllFaceInfoList.size();

            // 验证每一条记录
            UserAllFaceInfo userInfo;
            List<UserAllFaceInfo> availableList = new ArrayList<>();
            for (Object object : userAllFaceInfoList) {
                userInfo = (UserAllFaceInfo) object;
                if (userInfoCheck(userInfo)) {
                    System.out.println("userInfo == " + userInfo);
                    if (majorService.getMajorInfoByID(Integer.parseInt(userInfo.getMajor_id())) != null &&
                            classService.getClassInfoByClassID(Integer.parseInt(userInfo.getClass_id())) != null) {
                        availableList.add(userInfo);
                    }
                }
            }
            for (UserAllFaceInfo aa : availableList) {
                System.out.println("有效的信息有：" + aa);
            }
            //检查完成
            int tmp = Integer.parseInt(studentMapper.getMaxStuID().substring(1));
            for (UserAllFaceInfo availableUserInfo : availableList) {

                Picture image = new Picture(Base64Util.encode(FileUtil.readFileByBytes(availableUserInfo.getAddr_img())),
                        "BASE64");
                //人脸注册
                //为学生先生成 id
                tmp = tmp + 1;
                System.out.println("tmp == " + tmp);
                StudentDO studentDO = new StudentDO(
                        0,
                        "0" + tmp,
                        availableUserInfo.getName(),
                        availableUserInfo.getIdentity_card().substring(availableUserInfo.getIdentity_card().length() - 6),
                        Integer.parseInt(availableUserInfo.getMajor_id()),
                        Integer.parseInt(availableUserInfo.getClass_id()),
                        availableUserInfo.getIdentity_card(),
                        availableUserInfo.getSex());
                System.out.println("studentDO ==" + studentDO);
                FaceUserInfo faceUserInfo = new FaceUserInfo(
                        studentDO,
                        majorService.getMajorInfoByID(Integer.parseInt(availableUserInfo.getMajor_id())),
                        classService.getClassInfoByClassID(Integer.parseInt(availableUserInfo.getClass_id())));
                System.out.println("faceUserInfo ==" + faceUserInfo);

                Map<String, Object> ret = FaceRegObject.faceAdd(image, faceUserInfo);
                System.out.println("status_code == " + ret.get("status_code"));
                System.out.println("msg == " + ret.get("msg"));

                if ((int) ret.get("status_code") == StatusCode.SUCCESS) {
                    //2。保存到数据库
                    System.out.println("添加了改学航，available == ");

                    System.out.println(addOneStudent(studentDO));
                    available++;
                    System.out.println("添加了改学航，available == " + available);
                }
            }
        }
        result.put("total", total);
        result.put("available", available);
        return result;
    }

    @Override
    public File exportStudents(List<StudentInfoShow> studentInfoShowList) {
        System.out.println("进入了   public File exportStudents");
        return excelUtil.excelWriter(StudentInfoShow.class, studentInfoShowList);
    }
}
