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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping(value = "/SignCase")
public class SignCaseController {
    @Autowired
    private SignCaseService signCaseService;
    @Autowired
    private CourseTimeService courseTimeService;

    @RequestMapping(value = "/OneCourseSignCase/getOneStuSignCase")
    public void getOneStuSignCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入了 getOneStuSignCase ");
        // 获取用户 ID
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        System.out.println("UserID == " + userID);
        System.out.println("course_id == " + request.getParameter("course_id"));

        int course_id = Integer.parseInt(request.getParameter("course_id"));

        List<CourseTime> timeList = courseTimeService.getCourseTimeByCourseID(course_id);

        for (CourseTime tt : timeList) {
            System.out.println(tt);
        }

        SignCase signCase = signCaseService.getSignCaseByUserIDAndCourseID(userID, course_id);

        //构造返回的的结构数据
        List<SignCaseRecord> rows = new ArrayList<SignCaseRecord>();
        for (int i = 0; i < timeList.size(); i++) {
            String tsStr = "";
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //方法一
            tsStr = sdf.format(timeList.get(i).getCourse_start_timestamp());
            System.out.println(tsStr);
            rows.add(new SignCaseRecord(i + 1,
                    timeList.get(i).getCourse_start_timestamp(),
                    timeList.get(i).getCourse_end_timestamp(),
                    signCase.getSign_case_bitmap().charAt(i)));
        }

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }
}
