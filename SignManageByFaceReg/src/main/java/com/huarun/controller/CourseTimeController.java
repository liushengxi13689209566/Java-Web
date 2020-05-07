package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.huarun.pojo.CourseTime;
import com.huarun.service.CourseTimeService;
import com.huarun.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseTimeController {
    @Autowired
    private CourseTimeService courseTimeService;
    
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
}
