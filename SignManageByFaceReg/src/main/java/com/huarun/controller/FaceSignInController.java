package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.huarun.OtherStructure.FaceUserInfo;
import com.huarun.baidu.FaceRegObject;
import com.huarun.pojo.CourseStudent;
import com.huarun.pojo.CourseTime;
import com.huarun.service.*;
import com.huarun.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//只要有人进行考勤就一定能够查出来想对应的信息，直接进行信息查询即可。
//注意需要检测图片质量

@Controller
public class FaceSignInController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTimeService courseTimeService;
    @Autowired
    private SignCaseService signCaseService;
    @Autowired
    private CourseStudentService courseStudentService;

    @RequestMapping(value = "/FaceSignIn", method = RequestMethod.POST)
    public void FaceSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("fvvbdvdsvbdbv");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("进入importOneCourseStudents-------------");

        System.out.println("face_image == " + multipartRequest.getParameter("face_image"));
        //单位是毫秒
        System.out.println("timestamp == " + multipartRequest.getParameter("timestamp"));

        long sign_timestamp = Long.parseLong(multipartRequest.getParameter("timestamp"));

        //调用失败：请重试！
        JSONObject result = new JSONObject();
        //1.检测是否有人脸存在-》请正对摄像头
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

        //3.人脸搜索
        ret = FaceRegObject.faceSearch(image, true);

        System.out.println("搜死status_code == " + ret.get("status_code"));
        System.out.println("搜索msg == " + ret.get("msg"));
        System.out.println("user_info ==  == " + ret.get("user_info"));


        if ((int) ret.get("status_code") != StatusCode.SUCCESS) {
            result.put("status_code", ret.get("status_code"));
            result.put("msg", ret.get("msg"));
            ResponseUtil.write(response, result);
            return;
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        //获取用户数据
        FaceUserInfo faceUserInfo = JSON.parseObject((String) ret.get("user_info"), FaceUserInfo.class);
        System.out.println("faceInfo == ********" + faceUserInfo);

        List<CourseStudent> courseStudentList = courseStudentService.queryMyCourseIDByUserID(faceUserInfo.getId());
        //遍历课程 ID
        for (CourseStudent courseStudentData : courseStudentList) {
            //寻找课程时间表
            List<CourseTime> courseTimeList = courseTimeService.getCourseTimeByCourseID(courseStudentData.getCourse_id());

            //记录考勤情况，直接返回即可。
            ret = dateCompare(courseTimeList, sign_timestamp);

            //只有两种"正常情况"！！！
            int status_code = (int) ret.get("status_code");

            System.out.println("status_code == " + ret.get("status_code"));
            System.out.println("msg == " + ret.get("msg"));

            if (status_code == StatusCode.NO_SEARCH_RESULT) {
                continue;
            }
            //如果不符合，继续，符合的话就成功了
            System.out.println("order_number == " + (int) ret.get("order_number"));
            //5.生成相应的考勤记录
            String bitmap = signCaseService.getSignCaseByUserIDAndCourseID(faceUserInfo.getId(),
                    courseStudentData.getCourse_id()).getSign_case_bitmap();

            System.out.println("bitmap == " + bitmap);

            StringBuffer buffer = new StringBuffer(bitmap);
            if (status_code == StatusCode.SUCCESS) {
                System.out.println("SUccesss");
                buffer.setCharAt((int) ret.get("order_number"), StatusCode.SIGN_SUCCESS);
            } else if (status_code == StatusCode.CLASS_LATE) {
                System.out.println("CLASS_LATE");
                buffer.setCharAt((int) ret.get("order_number"), StatusCode.SIGN_LATE);
            }
            System.out.println("buffer.toString() == " + buffer.toString());

            int tt = signCaseService.updateSignCaseByUserIDAndCourseID(faceUserInfo.getId(),
                    courseStudentData.getCourse_id(), buffer.toString());
            if (tt < 1) {
                result.put("status_code", StatusCode.CALL_FAILED);
                result.put("msg", "后台记录考勤情况异常，请重试！！");
                ResponseUtil.write(response, result);
                return;
            }

            //给出考勤记录的提示信息
            int total_time = (int) ret.get("order_number") + 1;
            int late_time = 0; //迟到
            int truancy_time = 0; //旷课次数
            int success_time = 0; //出勤次数

            for (int i = 0; i < total_time; i++) {
                if (buffer.toString().charAt(i) == '0')
                    truancy_time++;
                else if (buffer.toString().charAt(i) == '1')
                    success_time++;
                else
                    late_time++;
            }
            result.put("status_code", status_code);
            result.put("msg", "考勤课程：" + courseService.queryCourseByID(courseStudentData.getCourse_id()).getCourse_name() +
                    ret.get("msg") + "\r\n\r\n 截止目前，你的考勤结果统计如下：\r\n" + "共 " + total_time + " 次课\r\n" +
                    "迟到：" + late_time + " 次课\r\n" +
                    "缺勤：" + truancy_time + " 次课\r\n" +
                    "成功：" + success_time + " 次课\r\n"
            );
            ResponseUtil.write(response, result);
            System.out.println(result);
            return;
        }
        result.put("status_code", StatusCode.NO_COURSE_TIME);
        result.put("msg", "考勤失败，请确认你的课程日期与考勤时间！，只在前半小时与后十分钟内签到有效哦！");
        ResponseUtil.write(response, result);
        System.out.println(result);
        return;
    }

    //时间戳的单位都是毫秒
    private Map<String, Object> dateCompare(List<CourseTime> courseTimeList, long sign_time) {
        Map<String, Object> result = new HashMap<>();
        sign_time = sign_time / 1000;
        int count = -1;
        for (CourseTime courseTime : courseTimeList) {
            count++;
            long start_time = courseTime.getCourse_start_timestamp().getTime() / 1000;

            System.out.println("courseTime == " + courseTime);
            System.out.println("start_time == " + start_time);
            System.out.println("count == " + count);

            //转换为秒来比较！！
            //前半小时：成功，后十分钟：迟到
            if (sign_time > (start_time - 1800) && sign_time <= start_time) {
                result.put("status_code", StatusCode.SUCCESS);
                result.put("msg", "考勤成功啦");
                result.put("order_number", count);
                return result;
            }
            if (sign_time > start_time && sign_time < (start_time + 600)) {
                result.put("status_code", StatusCode.CLASS_LATE);
                result.put("msg", "抱歉，你已经迟到了！");
                result.put("order_number", count);
                return result;
            }
        }
        result.put("status_code", StatusCode.NO_SEARCH_RESULT);
        return result;
    }
}