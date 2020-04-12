package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.pojo.*;
import com.huarun.service.*;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getMyCourse", method = RequestMethod.GET)
    public void getMyCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("jfvkfb     getCourse ");

        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        System.out.println("userID == " + userID);

        List<CourseStudent> list = courseStudentService.queryMyCourseIDByUserID(userID);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        List<CourseInfo> rows = new ArrayList<CourseInfo>();

        for (CourseStudent courseStudent : list) {
            System.out.println(courseService.queryCourseByID(courseStudent.getCourse_id()));

            rows.add(courseService.queryCourseByID(courseStudent.getCourse_id()));
        }

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);

//        for (CourseInfo ss :
//                list) {
//            System.out.println(ss);
//        }
    }

    @RequestMapping(value = "/getAllCourse", method = RequestMethod.GET)
    public void getAllCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CourseInfo> rows = courseService.queryAllCourse();

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "获取所有课程成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/getMyTeaCourse", method = RequestMethod.GET)
    public void getMyTeaCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        System.out.println("老师 userID == " + userID);

        List<CourseTeacher> list = courseTeacherService.getTeacherAllCourse(userID);

        for (CourseTeacher courseTeacher : list) {
            System.out.println(courseTeacher);
        }

        List<CourseInfo> rows = new ArrayList<CourseInfo>();

        for (CourseTeacher courseTeacher : list) {
            System.out.println(courseService.queryCourseByID(courseTeacher.getCourse_id()));

            rows.add(courseService.queryCourseByID(courseTeacher.getCourse_id()));
        }

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/getOneCourseAllStudent", method = RequestMethod.GET)
    public void getOneCourseAllStudent(int course_id, HttpServletResponse response) throws Exception {
        List<CourseStudent> list = courseStudentService.queryOneCourseAllStudent(course_id);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (CourseStudent courseStudent : list) {
            StudentInfo studentInfo = studentService.getStudentInfoByStuID(courseStudent.getStudent_id());
            ClassInfo classInfo = classService.getClassInfoByClassID(studentInfo.getClass_id());
            MajorInfo majorInfo = majorService.getMajorInfoByID(classInfo.getMajor_id());

            rows.add(new StudentInfoShow(studentInfo, classInfo, majorInfo));
        }
        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
//        return "/pagecomponent/StudentInfoShow.jsp";
    }
//    @RequestMapping(value = "/getOneCourseAllStudent", method = RequestMethod.GET)
//    public void setSessionCourseID()


}
