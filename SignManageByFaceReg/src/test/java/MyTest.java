import com.huarun.pojo.Books;
import com.huarun.service.BookService;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MyTest {
    @Test
    public void test() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("female", true);
        jsonObj.put("discount", 9.5);
        jsonObj.put("age", "26");

        jsonObj.get("cbcvdbvbdv");
        System.out.println(jsonObj);

        System.out.println(jsonObj.get("cbcvdbvbdv"));
    }
}