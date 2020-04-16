import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.StudentInfoShow;
import com.huarun.dao.TestMapper;
import com.huarun.pojo.ClassInfo;
import com.huarun.pojo.MajorInfo;
import com.huarun.pojo.StudentInfo;
import com.huarun.pojo.TestInfo;
import com.huarun.service.CourseService;
import com.huarun.service.TestService;
import com.huarun.utils.StatusCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("female", true);
//        jsonObj.put("discount", 9.5);
//        jsonObj.put("age", "26");
//
//        jsonObj.get("cbcvdbvbdv");
//        System.out.println(jsonObj);
//
//        System.out.println(jsonObj.get("cbcvdbvbdv"));
//        List<TestInfo> list = testMapper.queryVarbinary();

//        if (list.isEmpty()) {
//            System.out.println("cvvvsvsv");
//        }
//        for (TestInfo ii : list) {
//            System.out.println(ii);
//        }
        String str = "2019 come on";

        // 方法一
//
//        for (int i = 0; i < str.length(); i++) {
//
//            System.out.println(str.charAt(i));
//        }
//
        String st = "[{\"name\":\"刘生玺\",\"id\":\"04161173\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1606\"},{\"name\":\"刘贵财\",\"id\":\"04161172\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"张三\",\"id\":\"04161177\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"李四\",\"id\":\"04161178\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"王麻子\",\"id\":\"04161179\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"}]";
        JSONArray array = JSONArray.parseArray(st);

        JSONObject result = new JSONObject();


        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");


        array.add(result);
        System.out.println(array);

        System.out.println("code == " + array.getIntValue(Integer.parseInt("status_code")));

    }
}
