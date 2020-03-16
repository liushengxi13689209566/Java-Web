import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.Books;
import com.huarun.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MyTest {
    @Test
    public void test() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("female", true);
        jsonObj.put("hobbies", Arrays.asList(new String[]{"yoga", "swimming"}));
        jsonObj.put("discount", 9.5);
        jsonObj.put("age", "26");
        System.out.println(jsonObj);
    }
}