package com.huarun.controller;

import com.alibaba.fastjson.JSONObject;
import com.huarun.SignInTerminal.pojo.FaceSignInData;
import com.huarun.baidu.FaceReg;
import com.huarun.utils.ConfigParam;
import com.huarun.utils.StatusCode;
import com.huarun.utils.Photo;
import com.huarun.utils.ResponseUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


//只要有人进行考勤就一定能够查出来想对应的信息，直接进行信息查询即可。
//注意需要检测图片质量

@Controller
public class FaceSignInController {
    @RequestMapping("/FaceSignIn")
    @ResponseBody
    public void FaceSignIn(@RequestBody @Valid FaceSignInData faceSignInData, HttpServletResponse response) throws Exception {

        System.out.println("fvvbdvdsvbdbv");

        //调用失败：请重试！
        JSONObject result = new JSONObject();

        //1.检测是否有人脸存在-》请正对摄像头
        Photo image = new Photo(faceSignInData.getFaceImage(), "BASE64");
        String str = FaceReg.faceDetect(image);
        System.out.println("str  ==  " + str);
        if (str == null) {
            result.put("status_code", StatusCode.CALL_ERROR);
            result.put("msg", "请重试 ！！！");
            ResponseUtil.write(response, result);
            return;
        }
        JSONObject ret = JSONObject.parseObject(str);
        if (ret.getIntValue("error_code") != 0) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "请重试 ！！！");
            System.out.println("call 失败！！！");
            ResponseUtil.write(response, result);
            return;
        }

        System.out.println("ret == " + ret);
        System.out.println("face_num == " + ret.getJSONObject("result").getIntValue("face_num"));

        if (ret.getJSONObject("result").getIntValue("face_num") != 1) {
            result.put("status_code", StatusCode.NO_FACE);
            result.put("msg", "请正对摄像头！！！");
            ResponseUtil.write(response, result);
            return;
        }
        System.out.println(ret);

        System.out.println("检测通过");

        //2.人脸搜素 -》Y：考勤成功 N：考勤失败，请确认已在系统中注册。
        // 1:N 的搜索
        str = FaceReg.faceSearch(image, "group_repeat");
        System.out.println("face_search  str ==" + str);

        if (str == null) {
            result.put("status_code", StatusCode.CALL_ERROR);
            result.put("msg", "请重试 ！！！");

            ResponseUtil.write(response, result);
            return;
        }
        ret = JSONObject.parseObject(str);
        if (ret.getIntValue("error_code") != 0) {
            result.put("status_code", StatusCode.CALL_FAILED);
            result.put("msg", "请重试 ！！！");
            ResponseUtil.write(response, result);
            return;
        }

//        System.out.println("刘生玺 ==  " + ret.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getFloat("score"));

        if (ret.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getFloat("score") < ConfigParam.FACE_SEARCH_SCORE) {
            result.put("status_code", StatusCode.NO_SEARCH_RESULT);
            result.put("msg", "考勤失败，请确认已在系统中注册。！！！");
            ResponseUtil.write(response, result);
            return;
        }
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "考勤成功啦");
        ResponseUtil.write(response, result);
        System.out.println(result);
    }
}