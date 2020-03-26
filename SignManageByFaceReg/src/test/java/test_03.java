import com.baidu.aip.util.Base64Util;
import com.huarun.baidu.AiFaceObject;
import com.huarun.baidu.FaceReg;
import com.huarun.utils.FileUtil;
import com.huarun.utils.Photo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class test_03 {

    public static void faceDetect() {
        String Filepath = "/Users/tattoo/Java-Web/Test_02/src/main/resources/tattoo_01.jpg";
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            String image = Base64Util.encode(FileUtil.readFileByBytes(Filepath));

            Photo photo = new Photo(image, "BASE64");
            FaceReg.faceDetect(photo);

            HashMap<String, String> map = new HashMap<>();
            map.put("image", image);
            map.put("face_field", "faceshape,facetype,age,beauty,gender"); //所需要获取的人脸信息
            map.put("image_type", "BASE64");

            JSONObject res = AiFaceObject.getClient().detect(photo.getImage(), photo.getImageType(), map);
            System.out.println(res);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        test_03.faceDetect();
        String str = "04161173";
        if (!StringUtils.isNumeric(str)) {
            System.out.println("vffbvv");
        } else {
            System.out.println("yes");
        }

        String str1 = "4161173";
        if (!StringUtils.isNumeric(str1)) {
            System.out.println("vffbvv");
        } else {
            System.out.println("yes");
        }
    }
}
