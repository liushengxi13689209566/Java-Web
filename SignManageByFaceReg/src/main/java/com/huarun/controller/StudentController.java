package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.pojo.ClassInfo;
import com.huarun.pojo.CourseStudent;
import com.huarun.pojo.MajorInfo;
import com.huarun.pojo.StudentInfo;
import com.huarun.service.ClassService;
import com.huarun.service.CourseStudentService;
import com.huarun.service.MajorService;
import com.huarun.service.StudentService;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    //学生信息的展示是：StudentInfoShow 结构
    @RequestMapping(value = "/getOneCourseAllStudent", method = RequestMethod.GET)
    public void getOneCourseAllStudent(int course_id, HttpServletResponse response) throws Exception {
        List<CourseStudent> list = courseStudentService.queryOneCourseAllStudent(course_id);

        for (CourseStudent courseStudent : list) {
            System.out.println(courseStudent);
        }

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (CourseStudent courseStudent : list) {
            StudentInfo studentInfo = studentService.getStudentInfoByStuID(courseStudent.getStudent_id());
            ClassInfo classInfo = classService.getClassInfoByClassID(studentInfo.getClass_id());
            MajorInfo majorInfo = majorService.getMajorInfoByID(classInfo.getMajor_id());

            rows.add(new StudentInfoShow(studentInfo, classInfo, majorInfo));
        }
        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
//        return "/pagecomponent/StudentInfoShow.jsp";
    }


    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getSystemAllStudents", method = RequestMethod.GET)
    public void getSystemAllStudents(HttpServletResponse response) throws Exception {

        List<StudentInfo> list = studentService.getAllStudentsInfo();

        //构造返回的的结构数据
        List<StudentInfoShow> rows = new ArrayList<StudentInfoShow>();
        for (StudentInfo studentInfo : list) {
            ClassInfo classInfo = classService.getClassInfoByClassID(studentInfo.getClass_id());
            MajorInfo majorInfo = majorService.getMajorInfoByID(classInfo.getMajor_id());

            rows.add(new StudentInfoShow(studentInfo, classInfo, majorInfo));
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(rows));

//        留待优化，没有返回码了，哭哭！！！
//        JSONObject ret = new JSONObject();
//        ret.put("status_code", StatusCode.SUCCESS);
//        ret.put("msg", "成功");
//
//        JSONObject result = new JSONObject();
//        result.put("result", ret);
//        array.add(result);

        ResponseUtil.write(response, array);
    }
}
