import com.alibaba.fastjson.JSONObject;
import com.huarun.dao.TestMapper;
import com.huarun.pojo.TestInfo;
import com.huarun.service.CourseService;
import com.huarun.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

        for (int i = 0; i < str.length(); i++) {

            System.out.println(str.charAt(i));
        }
    }
}
