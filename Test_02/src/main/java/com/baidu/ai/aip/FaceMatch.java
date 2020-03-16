package com.baidu.ai.aip;

import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**
 * 人脸对比
 */
public class FaceMatch {
    public static String faceMatch() {
        String Filepath1 = "/Users/tattoo/Java-Web/Test_02/src/main/resources/welkin_02.jpg";
        String Filepath2 = "/Users/tattoo/Java-Web/Test_02/src/main/resources/welkin_03.jpg";
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String image1 = Base64Util.encode(FileUtil.readFileByBytes(Filepath1));
            String image2 = Base64Util.encode(FileUtil.readFileByBytes(Filepath2));

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "HIGH");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "HIGH");

            List<Map<String,Object>> list =  new ArrayList<>();
            list.add(map1);
            list.add(map2);

            String param = GsonUtils.toJson(list);

            System.out.println("bhvjdbsvnsvnjk=======" + param);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceMatch.faceMatch();
    }
}
