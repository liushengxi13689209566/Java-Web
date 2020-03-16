package com.huarun.controller;

import com.alibaba.fastjson.JSONObject;
import com.huarun.utils.ResponseUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;

//只要有人进行考勤就一定能够查出来想对应的信息，直接进行信息查询即可。
//注意需要检测图片质量

@Controller
public class FaceSignInController {
    @RequestMapping("/FaceSignIn")
    public void FaceSignIn(HttpServletResponse response) throws Exception {
        System.out.println("旅法 v 开放");

        System.out.println("v 发 v 了v 分");

        JSONObject result = new JSONObject();
        result.put("flag", true);
        result.put("msg", "hello world !!! ");

        System.out.println(result);

        response.setContentType("text/json");
        ResponseUtil.write(response, result);
    }
}

//package com.baidu.ai.aip;
//
//        import com.baidu.ai.aip.auth.AuthService;
//        import com.baidu.ai.aip.utils.HttpUtil;
//        import com.baidu.ai.aip.utils.GsonUtils;
//
//        import java.util.*;
//
///**
// * 用户信息查询
// */
//public class UserGet {
//
//    /**
//     * 重要提示代码中所需工具类
//     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
//     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
//     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
//     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
//     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
//     * 下载
//     */
//    public static String userGet() {
//
//        // 请求url
//        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
//        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("user_id", "user1");
//            map.put("group_id", "group_repeat");
//
//            String param = GsonUtils.toJson(map);
//
//            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = AuthService.getAuth();
//
//            String result = HttpUtil.post(url, accessToken, "application/json", param);
//            System.out.println(result);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        UserGet.userGet();
//    }
//}