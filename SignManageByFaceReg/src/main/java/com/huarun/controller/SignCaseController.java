package com.huarun.controller;

import com.huarun.OtherStructure.SignCaseRecord;
import com.huarun.pojo.CourseTime;
import com.huarun.pojo.SignCase;
import com.huarun.service.CourseTimeService;
import com.huarun.service.SignCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public String getOneStuSignCase(HttpServletRequest request, int course_id, Model model) throws Exception {
//    public String getOneStuSignCase(HttpServletRequest request, int course_id, Model model) {
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
        for (SignCaseRecord tt : rows) {
            System.out.println(tt);
        }
        model.addAttribute("list", rows);
        return "SignCaseShow";
    }
}
