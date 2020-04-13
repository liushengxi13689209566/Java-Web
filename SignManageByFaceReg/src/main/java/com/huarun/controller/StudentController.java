package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.service.CourseStudentService;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseStudentService courseStudentService;

    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
//    public void delOneStudentInCourse(String student_id, int course_id) {
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
}
