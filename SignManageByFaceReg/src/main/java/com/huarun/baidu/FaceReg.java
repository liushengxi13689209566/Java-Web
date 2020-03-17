package com.huarun.baidu;

import com.huarun.utils.Photo;
//这是 BaiDuAi 所使用的 JSONObject
import org.json.JSONObject;

import java.util.HashMap;


public class FaceReg {
    //人脸检测
    public static String faceDetect(Photo image) {
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            //考虑别人来刷卡的问题
            options.put("face_field", "age");//返回的人脸信息
            options.put("max_face_num", "1");//最大人脸识别数 1
            options.put("face_type", "LIVE");//照骗类型 生活照

            JSONObject res = AiFaceObject.getClient().detect(image.getImage(), image.getImageType(), options);
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //人脸搜索
    public static String faceSearch(Photo image, String groupIdList) {
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("quality_control", "NORMAL");
            options.put("liveness_control", "LOW");
            options.put("max_user_num", "1");

            JSONObject res = AiFaceObject.getClient().search(image.getImage(), image.getImageType(), groupIdList, options);
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //
}
