package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.*;
import com.huarun.service.*;
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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTimeService courseTimeService;
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SignCaseService signCaseService;

    @RequestMapping(value = "/getMyCourse", method = RequestMethod.GET)
    public void getMyCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("jfvkfb     getCourse ");

        // 获取用户 ID
        String userID = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("userID") == null) {
            userID = request.getParameter("id");
        } else {
            userID = (String) session.getAttribute("userID");
        }

        System.out.println("userID == " + userID);

        List<CourseStudent> list = courseStudentService.queryMyCourseIDByUserID(userID);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        List<CourseDO> rows = new ArrayList<CourseDO>();

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

//        for (CourseDO ss :
//                list) {
//            System.out.println(ss);
//        }
    }

    @RequestMapping(value = "/getAllCourse", method = RequestMethod.GET)
    public void getAllCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CourseDO> rows = courseService.queryAllCourse();

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

        List<CourseDO> rows = new ArrayList<CourseDO>();

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

    @RequestMapping(value = "/getAllCourseTime", method = RequestMethod.GET)
    public void getAllCourseTime(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int course_id = Integer.parseInt(request.getParameter("course_id"));

        List<CourseTime> rows = courseTimeService.getCourseTimeByCourseID(course_id);

        System.out.println("rowas == " + rows);

//        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
//        System.out.println("result == " + result);
        ResponseUtil.write(response, array);
    }

    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
    public void delOneStudentInCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("进入delOneStudentInCourse");
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String student_id = request.getParameter("student_id");


        System.out.println("course_id == " + course_id);
        System.out.println("student_id == " + student_id);

        JSONObject result = new JSONObject();

        int ret = courseStudentService.delOneStudentInCourse(course_id, student_id);
        System.out.println("ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，删除该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }

        ret = signCaseService.deleteSignCaseOneStudentOneCourse(student_id, course_id);
        System.out.println(" deleteSignCaseOneStudentOneCourse ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，删除该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/addOneStudentInCourse", method = RequestMethod.GET)
    public void addOneStudentInCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("v");

        String student_id = request.getParameter("student_id");
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        System.out.println("student_id == " + student_id);
        System.out.println("course_id == " + course_id);

        JSONObject result = new JSONObject();

        StudentDO studentDO = studentService.getStudentInfoByStuID(student_id);
        if (studentDO == null) {
            result.put("status_code", StatusCode.NO_USERID);
            result.put("msg", "抱歉，我们没有查到这个 userID ,请确认 userID 输入是否正确");
            ResponseUtil.write(response, result);
            return;
        }

        int ret = courseStudentService.addOneStudentInCourse(course_id, student_id);
        System.out.println("ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，添加该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        //sign_case
        ret = signCaseService.initSignCaseOneStudentOneCourse(student_id, course_id);
        System.out.println(" initSignCaseOneStudentOneCourse ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，添加该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/importOneCourseStudents", method = RequestMethod.POST)
    public void importOneCourseStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int status_code = 0;
        String msg = "";

        System.out.println("进入importOneCourseStudents");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("course_id"));
        MultipartFile file = multipartRequest.getFile("file");

        System.out.println(file.getOriginalFilename());

        int course_id = Integer.parseInt(multipartRequest.getParameter("course_id"));

        // 读取文件内容
        int total = 0;
        int available = 0;
        if (file == null) {
            status_code = StatusCode.FILE_NULL;
            msg = "文件为空，失败！！";
        }
        Map<String, Object> importInfo = courseStudentService.importOneCourseStudents(file, course_id);
        if (importInfo != null) {
            total = (int) importInfo.get("total");
            available = (int) importInfo.get("available");
            status_code = StatusCode.SUCCESS;
            msg = "成功！！！";
        }

        //返回
        JSONObject result = new JSONObject();

        result.put("status_code", status_code);
        result.put("msg", msg);
        result.put("available", available);
        result.put("total", total);

        System.out.println("importOneCourseStudents result == " + result);

        ResponseUtil.write(response, result);

    }

    @RequestMapping(value = "/addOneCourse", method = RequestMethod.POST)
    public void addOneCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int status_code = 0;
        String msg = "";

        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        //返回
        JSONObject result = new JSONObject();

        System.out.println("进入 addOneCourse");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("course_name"));
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("course_times"));
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("course_credit"));
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("start_date"));
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("end_date"));

        //查参数
        String course_name = multipartRequest.getParameter("course_name");
        String course_times = multipartRequest.getParameter("course_times");
        String course_credit = multipartRequest.getParameter("course_credit");
        String start_date = multipartRequest.getParameter("start_date");
        String end_date = multipartRequest.getParameter("end_date");

        if (course_name == null || course_times == null || course_credit == null || start_date == null || end_date == null) {
            result.put("status_code", StatusCode.PARAM_ERROR);
            result.put("msg", " 添加失败，缺少对应的课程信息，请检查！！");
            ResponseUtil.write(response, result);
            return;
        }

        MultipartFile file = multipartRequest.getFile("course_time_table");
        if (file == null) {
            result.put("status_code", StatusCode.FILE_NULL);
            result.put("msg", " 添加失败，上课时间表为空，请检查！！");
            ResponseUtil.write(response, result);
            return;
        }

        System.out.println(file.getOriginalFilename());
        // 1. 先添加 course 表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        CourseDO courseDO = new CourseDO(0, course_name, Integer.parseInt(course_times), Integer.parseInt(course_credit),
                simpleDateFormat.parse(start_date), simpleDateFormat.parse(end_date));

        System.out.println("之前的 courseDO 是 == " + courseDO);

        int ret = courseService.addOneCourse(courseDO);
        if (ret < 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", " 添加失败，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        //2.加入我要上的课
        ret = courseTeacherService.addOneCourseToTeaID(userID, courseDO.getCourse_id());
        if (ret < 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", " 添加失败，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        //3.加入时间表

        //这里 Mybatis 会将插入数据的主键返回到 courseDO 对象的 course_id 中

        System.out.println("courseDO == " + courseDO);

        // 读取文件内容
//        int total = 0;
//        int available = 0;
//        Map<String, Object> importInfo = courseStudentService.importOneCourseStudents(file, course_id);
//        if (importInfo != null) {
//            total = (int) importInfo.get("total");
//            available = (int) importInfo.get("available");
//            status_code = StatusCode.SUCCESS;
//            msg = "成功！！！";
//        }


        result.put("status_code", 0);
        result.put("msg", msg);
//        result.put("available", available);
//        result.put("total", total);

        System.out.println("importOneCourseStudents result == " + result);

        ResponseUtil.write(response, result);

    }

    @RequestMapping(value = "/deleteOnCourseInTea", method = RequestMethod.GET)
    public void deleteOnCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入 deleteOnCourse");
        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");
        int course_id = Integer.parseInt(request.getParameter("course_id"));

        //返回
        JSONObject result = new JSONObject();
        int ret = courseTeacherService.deleteOnCourseInTeaID(userID, course_id);
        if (ret < 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "sorry! 删除失败了！请重试！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "删除成功啦！！");
        ResponseUtil.write(response, result);
        return;
    }
}
