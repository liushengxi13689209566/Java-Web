package com.huarun.controller;

import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.FaceUserInfo;
import com.huarun.baidu.FaceRegObject;
import com.huarun.pojo.CourseTime;
import com.huarun.pojo.StudentDO;
import com.huarun.service.*;
import com.huarun.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//只要有人进行考勤就一定能够查出来想对应的信息，直接进行信息查询即可。
//注意需要检测图片质量

@Controller
public class FaceSignInController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseTimeService courseTimeService;

//    private Map<String, Object> dateCompare(Timestamp course_start_timestamp, Timestamp sign_timestamp) {
//        Map<String, Object> result = new HashMap<>();
//        return result;
//    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CourseTime> courseTimeList = courseTimeService.getCourseTimeByCourseID(3);
        for (CourseTime courseTime : courseTimeList) {
            System.out.println(courseTime);
        }
    }

    @RequestMapping(value = "/FaceSignIn", method = RequestMethod.POST)
    public void FaceSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("fvvbdvdsvbdbv");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入importOneCourseStudents-------------");


        System.out.println("course_id == " + multipartRequest.getParameter("course_id"));

        System.out.println("userID == " + multipartRequest.getParameter("userID"));
        System.out.println("face_image == " + multipartRequest.getParameter("face_image"));
        //单位是毫秒
//        System.out.println("timestamp == " + multipartRequest.getParameter("timestamp"));

//        int course_id = Integer.parseInt(multipartRequest.getParameter("course_id"));
//        Timestamp sign_timestamp = Timestamp.valueOf(multipartRequest.getParameter("timestamp"));

        //调用失败：请重试！
        JSONObject result = new JSONObject();
        //1.先查 id 是否存在
        StudentDO studentDO = studentService.getStudentInfoByStuID(request.getParameter("userID"));
        if (studentDO == null) {
            result.put("status_code", StatusCode.NO_USERID);
            result.put("msg", "抱歉，我们没有查到这个 userID ,请确认 userID 输入是否正确");
            ResponseUtil.write(response, result);
            return;
        }
        //2.检测是否有人脸存在-》请正对摄像头
        Picture image = new Picture(request.getParameter("face_image"), "BASE64");
        //生活照，进行活体检测
        Map<String, Object> ret = FaceRegObject.faceDetect(image, true, FacePictureType.LIVE);

        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));

        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }
        //3.人脸匹配认证（如果我输入一个其他人的ID的话，需要进行一个匹配检测）
        FaceUserInfo faceUserInfo = new FaceUserInfo(
                studentDO,
                majorService.getMajorInfoByID(studentDO.getMajor_id()),
                classService.getClassInfoByClassID(studentDO.getClass_id()));
        ret = FaceRegObject.faceSearch(image, true, faceUserInfo);

        System.out.println("status_code == " + ret.get("status_code"));
        System.out.println("msg == " + ret.get("msg"));
        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }

        //3。检查课程等是否需要上(因为已经是联动的，所以就不需要检测了)
        //4.比对课程时间

//        List<CourseTime> courseTimeList = courseTimeService.getCourseTimeByCourseID(course_id);
//        for (CourseTime courseTime : courseTimeList) {
//            dateCompare(courseTime.getCourse_start_timestamp(), sign_timestamp);
//        }

        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "考勤成功啦");
        ResponseUtil.write(response, result);
        System.out.println(result);
    }
}