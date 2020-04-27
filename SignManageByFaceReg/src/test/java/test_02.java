import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class test_02 {
    @Test
    public void test() {
        List<String> stringList = new ArrayList<>();
        stringList.add("string0");
        stringList.add("string1");
        stringList.add("string2");
        stringList.add("string3");


        List<String> stringList2 = new ArrayList<>();
        stringList.add("stringvfv0");
        stringList.add("strinvfvg1");
        stringList.add("vfsvfvtring2");
        stringList.add("stvfvfvring3");

        stringList.addAll(stringList2);

        System.out.println(stringList);

//        for (String str : stringList) {
//            System.out.println(str);
//        }


    }
}
//        String str = "{\n" +
//                "    \"result\":{\n" +
//                "        \"face_num\":1,\n" +
//                "        \"face_list\":[\n" +
//                "            {\n" +
//                "                \"angle\":{\n" +
//                "                    \"roll\":-2.53,\n" +
//                "                    \"pitch\":10.29,\n" +
//                "                    \"yaw\":-0.66\n" +
//                "                },\n" +
//                "                \"face_token\":\"786a07a46abd51c71a7787d3427e7250\",\n" +
//                "                \"location\":{\n" +
//                "                    \"top\":123.33,\n" +
//                "                    \"left\":164.21,\n" +
//                "                    \"rotation\":0,\n" +
//                "                    \"width\":108,\n" +
//                "                    \"height\":115\n" +
//                "                },\n" +
//                "                \"face_probability\":1,\n" +
//                "                \"age\":23\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"log_id\":7525890010594,\n" +
//                "    \"error_msg\":\"SUCCESS\",\n" +
//                "    \"cached\":0,\n" +
//                "    \"error_code\":0,\n" +
//                "    \"timestamp\":1584452490\n" +
//                "}";
//        JSONObject obj = new JSONObject();
//        obj = JSONObject.parseObject(str);
////        System.out.println(obj.getString("face_token"));
////        System.out.println(obj.getJSONArray("user_list"));
////
////        for (Object object : obj.getJSONArray("user_list")) {
////            System.out.println(object);
////        }
////        JSONObject user1 = obj.getJSONArray("user_list").getJSONObject(0);
////
//////        System.out.println(obj.getJSONArray("user_list").get(0));
//        System.out.println(obj.getJSONObject("result").getIntValue("face_num"));

//        String str1 = "{\n" +
//                "    \"result\":null,\n" +
//                "    \"log_id\":9465455510189,\n" +
//                "    \"error_msg\":\"pic not has face\",\n" +
//                "    \"cached\":0,\n" +
//                "    \"error_code\":222202,\n" +
//                "    \"timestamp\":1584453927\n" +
//                "}";
//        JSONObject obj1 = new JSONObject();
//        obj1 = JSONObject.parseObject(str1);
//
//        String tt = obj1.getJSONObject("result").toString();
//
//        JSONObject obj2 = new JSONObject();
//        obj2 = JSONObject.parseObject(str1);
//        if (obj2.isEmpty()) {
//            System.out.println("csdcbdcc");
//        } else {
//            System.out.println("cdnvbksdbvkdbsvkbd");
//        }


