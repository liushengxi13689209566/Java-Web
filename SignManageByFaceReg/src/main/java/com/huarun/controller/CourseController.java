package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.CourseInfo;
import com.huarun.pojo.TestInfo;
import com.huarun.service.CourseService;
import com.huarun.service.TestService;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    public void getCourse(HttpServletResponse response) throws Exception {

        System.out.println("jfvkfb     getCourse ");

        List<CourseInfo> list = courseService.queryAllCourse();
        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));

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
}
