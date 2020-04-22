import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.utils.StatusCode;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTest {
//    @Test
//    public Map<String, Object> compare(Timestamp course_start_timestamp, Timestamp sign_timestamp) {
//        Map<String, Object> result = new HashMap<>();
//
//        //直接按照一个long值去判断即可
//        return result;
//    }

    @Test
    public void TestTest() {
        Timestamp a = Timestamp.valueOf("2018-05-18 09:32:32");
        Timestamp b = Timestamp.valueOf("2018-05-11 09:32:32");

//        if(b-a)
        if (b.before(a)) {
            System.out.println("b时间比a时间早");
        }
    }

//    public void test() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date d1 = sdf.parse("2020-04-22 21:00:00");
//            Date d2 = sdf1.parse("2020-04-21 21:00:00");
//            Calendar ca = Calendar.getInstance();
//
////ca.add(Calendar.DATE, -1);//当前日期的前一天
//
//            int year = ca.get(Calendar.YEAR);//获取年份
//            int month = ca.get(Calendar.MONTH) + 1;//获取月份 (从0开始计算，所以需要加一)
//            int day = ca.get(Calendar.DATE);//获取日
//            int hour = ca.get(Calendar.HOUR);//小时 
//            int minute = ca.get(Calendar.MINUTE);//分 
//            int second = ca.get(Calendar.SECOND);//秒
//
//            int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK) - 1;//获取今天是一周的第几天，根据地区的不同判断是否需要减一，这里我们需要减一
//
//            System.out.println("用Calendar获得日期是：" + year + "年" + month + "月" + day + "日");
//            System.out.println("用Calendar获得时间是：" + hour + "时" + minute + "分" + second + "秒");
//            System.out.println("今天是一周的第" + WeekOfYear + "天");
//            //显示今天是一周的第几天（我做的这个例子正好是周二，故结果显示2，如果你再周6运行，那么显示6）
//            /**
//                 * 1表示大于，返回-1表示小于，返回0表示相等。
//                 */
//            System.out.println(d1.compareTo(ca.getTime()));
//            System.out.println(d2.compareTo(ca.getTime()));
//
//        } catch (ParseException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


//    String id = "0416117911";
//        System.out.println("id.substring(1) == "+id.substring(1));
//    int tt = Integer.parseInt(id.substring(1));
//        System.out.println("tt +1  == "+tt +1);
//    tt +=1;
//        System.out.println("tt   == "+tt);
//        System.out.println("fvbfhvbhfbv == "+"0"+tt);
//
//
//        System.out.println("max +1 == "+Integer.parseInt(id.substring(1))+1);
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
//        String str = "2019 come on";
    // 方法一
//        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i));
//        }
//        String st = "[{\"name\":\"刘生玺\",\"id\":\"04161173\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1606\"},{\"name\":\"刘贵财\",\"id\":\"04161172\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"张三\",\"id\":\"04161177\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"李四\",\"id\":\"04161178\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"},{\"name\":\"王麻子\",\"id\":\"04161179\",\"major_name\":\"计算机科学与技术\",\"class_name\":\"1601\"}]";
//        JSONArray array = JSONArray.parseArray(st);
//
//        JSONObject result = new JSONObject();
//
//
//        result.put("status_code", StatusCode.SUCCESS);
//        result.put("msg", "成功");
//
//
//        array.add(result);
//        System.out.println(array);
//
//        System.out.println("code == " + array.getIntValue(Integer.parseInt("status_code")));

}
