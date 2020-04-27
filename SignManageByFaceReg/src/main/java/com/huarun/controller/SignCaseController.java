package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.CourseSignCaseRecord;
import com.huarun.OtherStructure.SignCaseRecord;
import com.huarun.pojo.*;
import com.huarun.service.*;
import com.huarun.test.PrintTest;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.apache.ibatis.jdbc.Null;
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
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private CourseMajorClassService courseMajorClassService;
    @Autowired
    private MajorClassService majorClassService;
    @Autowired
    private CourseSignCaseRecordService courseSignCaseRecordService;

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

        System.out.println("signcase == " + signCase);

        //构造返回的的结构数据
        List<SignCaseRecord> rows = new ArrayList<SignCaseRecord>();
        System.out.println("size == " + timeList.size());
        for (int i = 0; i < timeList.size(); i++) {
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //方法一
            String tsStr = sdf.format(timeList.get(i).getCourse_start_timestamp());

            System.out.println("tsStr == " + tsStr);

            rows.add(new SignCaseRecord(i + 1,
                    timeList.get(i).getCourse_start_timestamp(),
                    timeList.get(i).getCourse_end_timestamp(),
                    signCase.getSign_case_bitmap().charAt(i)));
        }

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
//        System.out.println("result == " + result);
        ResponseUtil.write(response, array);
    }


    @RequestMapping(value = "/getMyCourseSignCase")
    public void getMyCourseSignCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入了 getMyCourseSignCase ");
        // 获取用户 ID ,一般为老师！！！
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userID");

        System.out.println("UserID == " + userID);
        String interval_time = request.getParameter("interval_time");
        System.out.println("interval_time == " + interval_time);

        List<CourseSignCaseRecord> rows = new ArrayList<>(); //最终会返回的数据

        List<CourseTeacher> courseIDList = courseTeacherService.getTeacherAllCourse(userID);

        System.out.println("courseIDList == " + courseIDList);

//        这里会写的有点复杂，需要完善～！！！
        //会得到相关的课程 ID
//        for (CourseTeacher courseTeacher : courseTeacherList) {
        for (int i = 0; i < courseIDList.size(); i++) {
            List<CourseMajorClassDO> majorClassIDList =
                    courseMajorClassService.getMajorClassInfoByCourseID(courseIDList.get(i).getCourse_id());

            System.out.println("majorClassIDList == " + majorClassIDList);

            //会得到 专业ID 班级ID
            for (int j = 0; j < majorClassIDList.size(); j++) {
                //使用基本数据类型的时候，如果字段是NULL，那么JDBC会返回0，但是这里会有一个问题。
                //有可能0在你的业务逻辑代表着特定含义，这时候就可能出现一些意想不到的后果。
                if (majorClassIDList.get(j).getClass_id() == 0) {
                    List<MajorClassDO> classIDList = majorClassService.getClassListByMajorID(majorClassIDList.get(j).getMajor_id());

                    System.out.println("classIDList == " + classIDList);

                    for (MajorClassDO majorClassDO : classIDList) {
                        rows.addAll(courseSignCaseRecordService.getCourseSignCaseRecord(courseIDList.get(i).getCourse_id(),
                                majorClassIDList.get(j).getMajor_id(),
                                majorClassDO.getClass_id(),
                                interval_time));
                    }
                } else {
                    rows.addAll(courseSignCaseRecordService.getCourseSignCaseRecord(courseIDList.get(i).getCourse_id(),
                            majorClassIDList.get(j).getMajor_id(),
                            majorClassIDList.get(j).getClass_id(),
                            interval_time));
                }
            }
            System.out.println("temp rows ==" + rows);

        }

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
        System.out.println("array == " + array.toJSONString());

        ResponseUtil.write(response, array);
    }
}
