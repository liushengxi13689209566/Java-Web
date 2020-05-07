package com.huarun.controller;

import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.StudentDO;
import com.huarun.service.*;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SignCaseService signCaseService;

    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
    public void delOneStudentInCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("进入delOneStudentInCourse");
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String student_id = request.getParameter("student_id");


        System.out.println("course_id == " + course_id);
        System.out.println("student_id == " + student_id);

        JSONObject result = new JSONObject();

        int ret = courseStudentService.delOneStudentInCourse(course_id, student_id);
        System.out.println("ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，删除该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }

        ret = signCaseService.deleteSignCaseOneStudentOneCourse(student_id, course_id);
        System.out.println(" deleteSignCaseOneStudentOneCourse ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，删除该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/addOneStudentInCourse", method = RequestMethod.GET)
    public void addOneStudentInCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("v");

        String student_id = request.getParameter("student_id");
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        System.out.println("student_id == " + student_id);
        System.out.println("course_id == " + course_id);

        JSONObject result = new JSONObject();

        StudentDO studentDO = studentService.getStudentInfoByStuID(student_id);
        if (studentDO == null) {
            result.put("status_code", StatusCode.NO_USERID);
            result.put("msg", "抱歉，我们没有查到这个 userID ,请确认 userID 输入是否正确");
            ResponseUtil.write(response, result);
            return;
        }

        int ret = courseStudentService.addOneStudentInCourse(course_id, student_id);
        System.out.println("ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，添加该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        //sign_case
        ret = signCaseService.initSignCaseOneStudentOneCourse(student_id, course_id);
        System.out.println(" initSignCaseOneStudentOneCourse ret  == " + ret);

        if (ret != 1) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "抱歉，添加该学生失败了，请重试！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");
        System.out.println("result == " + result);
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/importOneCourseStudents", method = RequestMethod.POST)
    public void importOneCourseStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int status_code = 0;
        String msg = "";

        System.out.println("进入importOneCourseStudents");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入importOneCourseStudents-------------" + multipartRequest.getParameter("course_id"));
        MultipartFile file = multipartRequest.getFile("file");

        System.out.println(file.getOriginalFilename());

        int course_id = Integer.parseInt(multipartRequest.getParameter("course_id"));

        // 读取文件内容
        int total = 0;
        int available = 0;
        if (file == null) {
            status_code = StatusCode.FILE_NULL;
            msg = "文件为空，失败！！";
        }
        Map<String, Object> importInfo = courseStudentService.importOneCourseStudents(file, course_id);
        if (importInfo != null) {
            total = (int) importInfo.get("total");
            available = (int) importInfo.get("available");
            status_code = StatusCode.SUCCESS;
            msg = "成功！！！";
        }

        //返回
        JSONObject result = new JSONObject();

        result.put("status_code", status_code);
        result.put("msg", msg);
        result.put("available", available);
        result.put("total", total);

        System.out.println("importOneCourseStudents result == " + result);

        ResponseUtil.write(response, result);

    }
}
