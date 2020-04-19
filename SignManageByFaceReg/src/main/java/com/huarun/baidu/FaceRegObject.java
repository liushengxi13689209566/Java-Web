package com.huarun.baidu;


import com.huarun.OtherStructure.FaceAddUserInfo;
import com.huarun.utils.FacePictureType;
import com.huarun.utils.Picture;

import com.huarun.utils.StatusCode;
//这是 BaiDuAi 所使用的 JSONObject
import org.json.JSONObject;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/*
人脸库结构

人脸库、用户组、用户、用户下的人脸层级关系如下所示：

|- 人脸库(appid)
   |- 用户组一（group_id）
      |- 用户01（uid）
         |- 人脸（faceid）
      |- 用户02（uid）
         |- 人脸（faceid）
         |- 人脸（faceid）
         ....
       ....
   |- 用户组二（group_id）
   |- 用户组三（group_id）
   ....
关于人脸库的设置限制

每个开发者账号可以创建100个appid；
每个appid对应一个人脸库，且不同appid之间，人脸库互不相通；
每个人脸库下，可以创建多个用户组，用户组（group）数量没有限制；
每个用户组（group）下，可添加无限个user_id，无限张人脸（注：为了保证查询速度，单个group中的人脸容量上限建议为80万）；
每个用户（user_id）所能注册的最大人脸数量20；
提醒：每个人脸库对应一个appid，一定确保不要轻易删除后台应用列表中的appid，删除后则此人脸库将失效，无法进行任何查找！
* */

//所有的人脸都在同一个appid,同一个组中！！！

public class FaceRegObject {
    //face_type	 采用枚举类型进行选择
    //活体检测：是/否
    //人脸图片检测（质量，人数【限定一个，不可多了】）
    public static Map<String, Object> faceDetect(@Valid Picture image, boolean isLiveness, FacePictureType type) {
        try {
            Map<String, Object> result = new HashMap<>();
            if (image.isEmpty()) {
                result.put("status_code", StatusCode.FILE_NULL);
                result.put("msg", "传入的人脸照片为空！！！");
                return result;
            }
            HashMap<String, String> options = new HashMap<String, String>();
            //考虑别人来刷卡的问题
            options.put("face_field", "age,quality");//返回的人脸信息
            options.put("max_face_num", "1");//最大人脸识别数 1
//            options.put("face_type", "LIVE");//照骗类型 生活照
            options.put("face_type", type.getType());//照骗类型
            if (isLiveness) {
                options.put("liveness_control", "NORMAL");
            }
            JSONObject res = AiFaceObject.getClient().detect(image.getImage(), image.getImageType(), options);
            //检测并返回
            com.alibaba.fastjson.JSONObject ret = com.alibaba.fastjson.JSONObject.parseObject(res.toString());
            if (ret.getIntValue("error_code") != 0) {
                result.put("status_code", StatusCode.CALL_FAILED);
                result.put("msg", "人脸识别模块出错，请重试 ！！！");
                return result;
            }
            //1。人脸数量检测
            if (ret.getJSONObject("result").getIntValue("face_num") != 1) {
                result.put("status_code", StatusCode.FACE_COUNT_NOT_ONE);
                result.put("msg", "照片中人脸数目不等于1，请保持正对摄像头，保证只有你自己入镜");
                return result;
            }
            //2。人脸质量检测
            com.alibaba.fastjson.JSONArray face_list = ret.getJSONObject("result").getJSONArray("face_list");
            com.alibaba.fastjson.JSONObject qualityObj = face_list.getJSONObject(0).getJSONObject("quality");

            if (qualityObj.getDoubleValue("blur") > 0.7) {
                result.put("status_code", StatusCode.FACE_NOT_CLEAR);
                result.put("msg", "人脸照片模糊,无法识别！！！");
                return result;
            }
            if (qualityObj.getIntValue("completeness") != 1) {
                result.put("status_code", StatusCode.FACE_OVERFLOW);
                result.put("msg", "人脸溢出图像边界,请正对屏幕！！！");
                return result;
            }
            com.alibaba.fastjson.JSONObject occlusionObj = qualityObj.getJSONObject("occlusion");
            double sum = occlusionObj.getDoubleValue("left_eye")
                    + occlusionObj.getDoubleValue("right_eye")
                    + occlusionObj.getDoubleValue("nose")
                    + occlusionObj.getDoubleValue("mouth")
                    + occlusionObj.getDoubleValue("left_cheek")
                    + occlusionObj.getDoubleValue("right_cheek")
                    + occlusionObj.getDoubleValue("chin_contour");
            if (sum > 3.5) {
                result.put("status_code", StatusCode.FACE_SHELTER);
                result.put("msg", "请勿遮挡人脸！！！");
                return result;
            }
            //3。年龄检测
            double age = face_list.getJSONObject(0).getDoubleValue("age");
            if (age > 40 || age < 10) {
                result.put("status_code", StatusCode.AGE_OVER);
                result.put("msg", "不好意思，年龄超限！！！");
                return result;
            }
            //通过所有检测，成功
            result.put("status_code", StatusCode.SUCCESS);
            result.put("msg", "人脸照片检测成功通过！！！");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //人脸注册
    public static Map<String, Object> faceAdd(Picture image, FaceAddUserInfo faceAddUserInfo) {
        try {
            Map<String, Object> result = new HashMap<>();
            HashMap<String, String> options = new HashMap<String, String>();

            options.put("user_info", com.alibaba.fastjson.JSON.toJSONString(faceAddUserInfo));
            options.put("quality_control", "NORMAL");
            options.put("action_type", "REPLACE");

            JSONObject res = AiFaceObject.getClient().addUser(
                    image.getImage(),
                    image.getImageType(),
                    Integer.toString(faceAddUserInfo.getMajor_id()),
                    faceAddUserInfo.getId(),
                    options);

            //判断注册结果
            com.alibaba.fastjson.JSONObject ret = com.alibaba.fastjson.JSONObject.parseObject(res.toString());
            if (ret.getIntValue("error_code") != 0) {
                result.put("status_code", StatusCode.CALL_FAILED);
                result.put("msg", "人脸注册模块出错，请重试 ！！！");
                return result;
            }
            //通过所有检测，成功
            result.put("status_code", StatusCode.SUCCESS);
            result.put("msg", "人脸注册成功！！！");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //人脸搜索
    public static String faceSearch(Picture image, String groupIdList) {
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


