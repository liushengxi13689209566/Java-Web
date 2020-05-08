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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
        System.out.println("得到所有课程");

        List<CourseDO> rows = courseService.queryAllCourse();

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "获取所有课程成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    //真正意义上的添加一门课
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是 MM
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
        //2.加入时间表
        //这里 Mybatis 会将插入数据的主键返回到 courseDO 对象的 course_id 中
        System.out.println("courseDO == " + courseDO);

        // 读取文件内容
        int total = 0;
        int available = 0;
        Map<String, Object> importInfo = courseTimeService.importOneCourseTime(file, courseDO.getCourse_id());
        if (importInfo != null) {
            total = (int) importInfo.get("total");
            available = (int) importInfo.get("available");
            status_code = StatusCode.SUCCESS;
            msg = "成功！！！";
        }
        result.put("status_code", status_code);
        result.put("msg", msg);
        result.put("available", available);
        result.put("total", total);
        System.out.println("importOneCourseStudents result == " + result);
        ResponseUtil.write(response, result);
    }

    //真正意义上的删除一门课
    @Transactional
    @RequestMapping(value = "/deleteOneCourse", method = RequestMethod.GET)
    public void deleteOneCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int course_id = Integer.parseInt(request.getParameter("course_id"));

        System.out.println("进入删除课程，course_id == " + course_id);

        JSONObject result = new JSONObject();
        //course_student,course_teacher,sm_sign_case 删除
        // course_time ,course
        //写成一个事务
        try {
            courseStudentService.deleteOneCourse(course_id);
            courseTeacherService.deleteOneCourse(course_id);
            signCaseService.deleteOneCourse(course_id);
            courseTimeService.deleteOneCourse(course_id);
            courseService.deleteOneCourse(course_id);
        } catch (Exception e) {
            result.put("status_code", StatusCode.TRANSACTIONS_EXE_ERROR);
            result.put("msg", "删除该课程失败！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "删除该课程成功！！");
        ResponseUtil.write(response, result);
    }


    //更新一门课程（不包含对应的课程表时间表）
    @Transactional
    @RequestMapping(value = "/updateOneCourse", method = RequestMethod.POST)
    public void updateOneCourse(@RequestBody CourseDO courseDO, HttpServletResponse response) throws Exception {
        //返回
        JSONObject result = new JSONObject();

        System.out.println("进入 updateOneCourse");

        System.out.println(" courseDO 是 == " + courseDO);

        int ret = courseService.updateOneCourse(courseDO);
        if (ret < 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", " 更改失败，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", " 更改课程信息成功！");
        ResponseUtil.write(response, result);
        return;
    }
}
