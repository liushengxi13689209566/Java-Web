package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.SignCaseRecord;
import com.huarun.pojo.CourseTime;
import com.huarun.pojo.SignCase;
import com.huarun.service.CourseTimeService;
import com.huarun.service.SignCaseService;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/SignCase")
public class SignCaseController {
    @Autowired
    private SignCaseService signCaseService;
    @Autowired
    private CourseTimeService courseTimeService;

    @RequestMapping(value = "/OneCourseSignCase/getOneStuSignCase")
//    public String getOneStuSignCase(HttpServletRequest request, HttpServletResponse response, int course_id) {
    public String getOneStuSignCase(HttpServletRequest request, int course_id, Model model) {
        System.out.println("进入了 getOneStuSignCase ");
        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");
        System.out.println("UserID == " + userID);
        System.out.println("course_id == " + course_id);

        List<CourseTime> timeList = courseTimeService.getCourseTimeByCourseID(course_id);

        for (CourseTime tt : timeList) {
            System.out.println(tt);
        }

        SignCase signCase = signCaseService.getSignCaseByUserIDAndCourseID(userID, course_id);


        //构造返回的的结构数据
        List<SignCaseRecord> rows = new ArrayList<SignCaseRecord>();
        for (int i = 0; i < timeList.size(); i++) {
            rows.add(new SignCaseRecord(timeList.get(i).getCourse_start_timestamp(),
                    timeList.get(i).getCourse_end_timestamp(), signCase.getSign_case_bitmap().charAt(i)));
        }
        model.addAttribute("list", rows);


//        JSONObject result = new JSONObject();
//        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));
//
//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
//        System.out.println("result == " + result);
//        ResponseUtil.write(response, result);

        return "allSignCase";
    }

}
