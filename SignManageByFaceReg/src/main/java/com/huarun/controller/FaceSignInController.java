package com.huarun.controller;

import com.alibaba.fastjson.JSONObject;
import com.huarun.baidu.FaceRegObject;
import com.huarun.pojo.StudentDO;
import com.huarun.service.StudentService;
import com.huarun.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


//只要有人进行考勤就一定能够查出来想对应的信息，直接进行信息查询即可。
//注意需要检测图片质量

@Controller
public class FaceSignInController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/FaceSignIn")
    public void FaceSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("fvvbdvdsvbdbv");

        System.out.println("course_id == " + request.getParameter("course_id"));
        System.out.println("userID == " + request.getParameter("userID"));
        System.out.println("face_image == " + request.getParameter("face_image"));

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
        //3.人脸匹配认证（如果我输入一个其他人的ID的话，需要进行一个匹配检测。）


        //3。检查课程等是否需要上
        //4。

//        JSONObject ret = JSONObject.parseObject(str);
//        if (ret.getIntValue("error_code") != 0) {
//            result.put("status_code", StatusCode.CALL_FAILED);
//            result.put("msg", "请重试 ！！！");
//            System.out.println("call 失败！！！");
//            ResponseUtil.write(response, result);
//            return;
//        }
//
//        System.out.println("ret == " + ret);
//        System.out.println("face_num == " + ret.getJSONObject("result").getIntValue("face_num"));
//
//        if (ret.getJSONObject("result").getIntValue("face_num") != 1) {
//            result.put("status_code", StatusCode.NO_FACE);
//            result.put("msg", "请正对摄像头！！！");
//            ResponseUtil.write(response, result);
//            return;
//        }
//        System.out.println(ret);
//
//        System.out.println("检测通过");
//
//        //2.人脸搜素 -》Y：考勤成功 N：考勤失败，请确认已在系统中注册。
//        // 1:N 的搜索
//        str = FaceRegObject.faceSearch(image, "group_repeat");
//        System.out.println("face_search  str ==" + str);
//
//        if (str == null) {
//            result.put("status_code", StatusCode.CALL_ERROR);
//            result.put("msg", "请重试 ！！！");
//
//            ResponseUtil.write(response, result);
//            return;
//        }
//        ret = JSONObject.parseObject(str);
//        if (ret.getIntValue("error_code") != 0) {
//            result.put("status_code", StatusCode.CALL_FAILED);
//            result.put("msg", "请重试 ！！！");
//            ResponseUtil.write(response, result);
//            return;
//        }
//
////        System.out.println("刘生玺 ==  " + ret.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getFloat("score"));
//
//        if (ret.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getFloat("score") < ConfigParam.FACE_SEARCH_SCORE) {
//            result.put("status_code", StatusCode.NO_SEARCH_RESULT);
//            result.put("msg", "考勤失败，请确认已在系统中注册。！！！");
//            ResponseUtil.write(response, result);
//            return;
//        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "考勤成功啦");
        ResponseUtil.write(response, result);
        System.out.println(result);
    }
}