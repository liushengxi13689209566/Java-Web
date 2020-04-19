package com.baidu.ai.aip;

import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**
 * 人脸检测与属性分析
 */
public class FaceDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceDetect() {
        String Filepath = "/Users/tattoo/Java-Web/Test_02/src/main/resources/test.jpg";
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            String image = Base64Util.encode(FileUtil.readFileByBytes(Filepath));

            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("face_field", "faceshape,facetype,age,beauty,gender,quality"); //所需要获取的人脸信息
            map.put("image_type", "BASE64");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "[调用鉴权接口获取的token]";
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
        FaceDetect.faceDetect();
    }
}
/*
{
    "error_code":0,
    "error_msg":"SUCCESS",
    "log_id":1555201354589,
    "timestamp":1587207424,
    "cached":0,
    "result":{
        "face_num":1,
        "face_list":[
            {
                "face_token":"3982e82fcb1263f98ee4dc68532c7b35",
                "location":{
                    "left":161.19,
                    "top":166.42,
                    "width":249,
                    "height":232,
                    "rotation":0
                },
                "face_probability":1,
                "angle":{
                    "yaw":-6.52,
                    "pitch":19.74,
                    "roll":-2.49
                },
                "face_shape":{
                    "type":"heart",
                    "probability":0.51
                },
                "face_type":{
                    "type":"human",
                    "probability":1
                },
                "age":22,
                "beauty":79.84,
                "gender":{
                    "type":"female",
                    "probability":1
                },
                "quality":{
                    "occlusion":{
                        "left_eye":0,
                        "right_eye":0,
                        "nose":0,
                        "mouth":0,
                        "left_cheek":0.02,
                        "right_cheek":0.01,
                        "chin_contour":0
                    },
                    "blur":0,
                    "illumination":167,
                    "completeness":1
                }
            }
        ]
    }
}

* */