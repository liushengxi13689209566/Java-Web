package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.CourseDO;
import com.huarun.pojo.CourseTeacher;
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
public class CourseTeacherController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTeacherService courseTeacherService;

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


    @RequestMapping(value = "/addOneCourseToTea", method = RequestMethod.GET)
    public void addOneCourseToTea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入 addOneCourseToTea");
        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        //返回
        JSONObject result = new JSONObject();
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        CourseDO courseDO = courseService.queryCourseByID(course_id);
        if (courseDO == null) {
            result.put("status_code", StatusCode.NO_COURSE);
            result.put("msg", "抱歉，我们没有查到这个 课程ID ,请确认 课程ID 输入是否正确");
            ResponseUtil.write(response, result);
            return;
        }
        int ret = courseTeacherService.addOneCourseToTeaID(userID, course_id);
        if (ret < 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "sorry! 添加失败了！请重试！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "添加成功！");
        ResponseUtil.write(response, result);
        return;
    }

    @RequestMapping(value = "/deleteOneCourseInTea", method = RequestMethod.GET)
    public void deleteOneCourseInTea(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
