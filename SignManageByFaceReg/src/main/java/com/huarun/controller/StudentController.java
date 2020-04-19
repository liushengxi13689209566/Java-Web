package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.util.Base64Util;
import com.huarun.OtherStructure.FaceAddUserInfo;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.baidu.FaceRegObject;
import com.huarun.pojo.ClassDO;
import com.huarun.pojo.CourseStudent;
import com.huarun.pojo.MajorDO;
import com.huarun.pojo.StudentDO;
import com.huarun.service.ClassService;
import com.huarun.service.CourseStudentService;
import com.huarun.service.MajorService;
import com.huarun.service.StudentService;
import com.huarun.utils.FacePictureType;
import com.huarun.utils.Picture;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    //学生信息的展示是：StudentInfoShow 结构
    @RequestMapping(value = "/getOneCourseAllStudent", method = RequestMethod.GET)
    public void getOneCourseAllStudent(int course_id, HttpServletResponse response) throws Exception {
        List<CourseStudent> list = courseStudentService.queryOneCourseAllStudent(course_id);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (CourseStudent courseStudent : list) {
            StudentDO studentDO = studentService.getStudentInfoByStuID(courseStudent.getStudent_id());
            MajorDO majorDO = majorService.getMajorInfoByID(studentDO.getMajor_id());
            ClassDO classDO = classService.getClassInfoByClassID(studentDO.getClass_id());

            rows.add(new StudentInfoShow(studentDO, classDO, majorDO));
        }
        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }


    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
    public void delOneStudentInCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("进入delOneStudentInCourse");
        String course_id_str = request.getParameter("course_id");
        String student_id = request.getParameter("student_id");


        System.out.println("course_id == " + course_id_str);
        System.out.println("student_id == " + student_id);

        courseStudentService.delOneStudentInCourse(Integer.parseInt(course_id_str), student_id);

        JSONObject result = new JSONObject();
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/getSystemAllStudents", method = RequestMethod.GET)
    public void getSystemAllStudents(HttpServletResponse response) throws Exception {

        List<StudentDO> list = studentService.getAllStudentsInfo();

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (StudentDO studentDO : list) {
            MajorDO majorDO = majorService.getMajorInfoByID(studentDO.getMajor_id());
            ClassDO classDO = classService.getClassInfoByClassID(studentDO.getClass_id());
            rows.add(new StudentInfoShow(studentDO, classDO, majorDO));
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        留待优化，没有返回码了，哭哭！！！
//        JSONObject ret = new JSONObject();
//        ret.put("status_code", StatusCode.SUCCESS);
//        ret.put("msg", "成功");
//
//        JSONObject result = new JSONObject();
//        result.put("result", ret);
//        array.add(result);

        ResponseUtil.write(response, array);
    }

    @RequestMapping(value = "/addOneStudent", method = RequestMethod.POST)
    public void addOneStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject result = new JSONObject();

        System.out.println("进入    addOneStudent");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_name"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("major_id"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("class_id"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_sex"));
        System.out.println("进入addOneStudent-------------" + multipartRequest.getParameter("student_id_card"));


        //所有数据的校验由前端之后来做
        String name = multipartRequest.getParameter("student_name");
        int major_id = Integer.parseInt(multipartRequest.getParameter("major_id"));
        int class_id = Integer.parseInt(multipartRequest.getParameter("class_id"));
        String sex = multipartRequest.getParameter("student_sex");
        String ID_card = multipartRequest.getParameter("student_id_card");


        MultipartFile file = multipartRequest.getFile("student_picture");
        System.out.println("student_picture === " + file.getOriginalFilename());

        //人脸检测图片（质量-》人脸-）
        Picture image = new Picture(Base64Util.encode(file.getBytes()), "BASE64");
        //证件照，不进行活体检测
        Map<String, Object> ret = FaceRegObject.faceDetect(image, false, FacePictureType.CERT);

        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));

        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }

        //人脸注册
        //为学生先生成 id
        FaceAddUserInfo faceAddUserInfo = new FaceAddUserInfo(
                studentService.getMaxStuID(),
                name,
                major_id,
                majorService.getMajorInfoByID(major_id).getMajor_name(),
                class_id,
                classService.getClassInfoByClassID(class_id).getClass_name()
        );
        ret = FaceRegObject.faceAdd(image, faceAddUserInfo);
        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));


        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }
        //数据库保存学生信息
        StudentDO studentDO = new StudentDO(
                0,
                "0" + Integer.parseInt(studentService.getMaxStuID().substring(1)) + 1,
                name,
                ID_card.substring(ID_card.length() - 6),
                major_id,
                class_id,
                ID_card,
                sex);
        studentService.addOneStudent(studentDO);

        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "添加成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }
}
