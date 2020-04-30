package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.*;
import com.huarun.pojo.*;
import com.huarun.service.*;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private StudentService studentService;
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;


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
        int count = 0;

        for (int i = 0; i < courseIDList.size(); i++) {
            int course_id = courseIDList.get(i).getCourse_id();
            //先根据时间间隔查课程表信息；
            int totalCount = courseTimeService.getTotalCountByCourseID(course_id);
            List<CourseTime> timeList = courseTimeService.getCourseTimeByTime(course_id, interval_time);

            //没有数据就继续
            if (timeList.isEmpty()) {
                continue;
            }
            int idx_start = totalCount - timeList.size();

            //所有的学生是不变的
            List<CourseStudent> studentIDList = courseStudentService.queryOneCourseAllStudent(course_id);
            Map<String, String> signCaseMap = new HashMap<>();

            //所有学生的考勤结果也要先提前取出来。
            for (CourseStudent studentID : studentIDList) {
                signCaseMap.put(studentID.getStudent_id(),
                        signCaseService.getSignCaseByUserIDAndCourseID(studentID.getStudent_id(), course_id).getSign_case_bitmap());
            }

            //统计每一天
            for (int j = idx_start; j < idx_start + timeList.size(); j++) {

                Map<CourseSignCaseKK, CourseSignCaseVV> map = new HashMap<>();

                for (CourseStudent studentID : studentIDList) {
                    StudentDO studentDO = studentService.getStudentInfoByStuID(studentID.getStudent_id());

                    CourseSignCaseKK kk = new CourseSignCaseKK(studentDO.getMajor_id(), studentDO.getClass_id());

                    if (map.containsKey(kk)) {

                        System.out.println("包含：" + kk);
                        System.out.println("signCaseMap.(j) == " + signCaseMap.get(studentDO.getId()).charAt(j));

                        if (signCaseMap.get(studentDO.getId()).charAt(j) == '2')
                            map.get(kk).late_count_increment();
                        else if (signCaseMap.get(studentDO.getId()).charAt(j) == '1')
                            map.get(kk).success_count_increment();
                        else
                            map.get(kk).truancy_count_increment();
                    } else {
                        System.out.println("不包含：" + kk);
                        map.put(kk, new CourseSignCaseVV(0, 1, 0));
                    }
                }
                for (Map.Entry<CourseSignCaseKK, CourseSignCaseVV> entry : map.entrySet()) {

                    System.out.println(entry.getKey() + ":" + entry.getValue());

                    rows.add(new CourseSignCaseRecord(count++,
                            entry.getKey(),
                            entry.getValue(),
                            majorService.getMajorInfoByID(entry.getKey().getMajor_id()).getMajor_name(),
                            classService.getClassInfoByClassID(entry.getKey().getClass_id()).getClass_name(),
                            course_id,
                            courseService.queryCourseByID(course_id).getCourse_name(),
                            j,
                            timeList.get(j - idx_start).getCourse_start_timestamp()
                    ));
                }
            }

//            后来新建的两张表是不需要的！！

            JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
            System.out.println("array == " + array.toJSONString());

            ResponseUtil.write(response, array);
        }
    }

    @RequestMapping(value = "/getOneDayClassSignCase")
    public void getOneDayClassSignCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入了 getOneDayClassSignCase ");

        List<OneDayClassSignCaseRecord> rows = new ArrayList<>();

        System.out.println("进入addOneStudent-------------" + request.getParameter("course_id"));
        System.out.println("进入addOneStudent-------------" + request.getParameter("major_id"));
        System.out.println("进入addOneStudent-------------" + request.getParameter("class_id"));
        System.out.println("进入addOneStudent-------------" + request.getParameter("bitmap_idx"));

        //所有数据的校验由前端之后来做
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        int major_id = Integer.parseInt(request.getParameter("major_id"));
        int class_id = Integer.parseInt(request.getParameter("class_id"));
        int bitmap_idx = Integer.parseInt(request.getParameter("bitmap_idx"));

        List<StudentDO> studentList = studentService.getStudentInfoByMajorIDAndClassID(major_id, class_id);

        //不会出现空的情况
        //if (studentList.isEmpty())
        for (int i = 0; i < studentList.size(); i++) {
            StudentDO tmp = studentList.get(i);
            rows.add(new OneDayClassSignCaseRecord(i + 1, tmp,
                    signCaseService.getSignCaseByUserDOAndCourseID(tmp, course_id).getSign_case_bitmap().charAt(bitmap_idx)));
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));
//        result.put("rows", array);
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
        System.out.println("array == " + array.toJSONString());
        ResponseUtil.write(response, array);
    }
}
