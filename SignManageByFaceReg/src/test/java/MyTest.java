import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.OtherStructure.FaceUserInfo;
import com.huarun.utils.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        KK k1 = new KK("2020/5/8 8:00", "2020/5/8 10:00");
        KK k2 = new KK("2020/5/8 8:00", "2020/5/8 9:00");

        List<KK> list = new ArrayList<>();
        list.add(k1);
        list.add(k2);

        HashSet set = new HashSet(list);
        list.clear();
        list.addAll(set);
        System.out.println("list == " + list);
//
//        if (t1 == t2) {
//            System.out.println("vfvfvf");
//        }
//        String user_info = "{\n" +
//                "    \"class_id\":1,\n" +
//                "    \"class_name\":\"1606\",\n" +
//                "    \"id\":\"04161180\",\n" +
//                "    \"major_desc\":\"ComputerScienceAndTechnology\",\n" +
//                "    \"major_id\":1,\n" +
//                "    \"major_name\":\"计算机科学与技术\",\n" +
//                "    \"name\":\"04165555\"\n" +
//                "}";
//        FaceUserInfo faceUserInfo = JSON.parseObject(user_info, FaceUserInfo.class);
//        System.out.println("facein == " + faceUserInfo);

//        Group group = JSON.parseObject(jsonString, Group.class);


//        Map<KK, VV> map = new HashMap<>();
//
//        map.put(new KK(2, 3), new VV(0, 0, 0));
//
//        KK tt = new KK(2, 3);
//        if (map.containsKey(tt)) {
//            map.get(tt).setLate_count(111);
//        }
//
//        for (Map.Entry<KK, VV> entry : map.entrySet()) {
//            KK mapKey = entry.getKey();
//            VV mapValue = entry.getValue();
////            Record(KK,VV,getclass_name,getmajor_name);
////            后来新建的两张表是不需要的！！
//            System.out.println(mapKey + ":" + mapValue);
//        }

//        map.put(new KK(2, 3), new VV(0, 0, 0));
//
//        map.put(new KK(2, 3), new VV(11, 0, 0));
//        map.put(new KK(2, 3), new VV(22222, 0, 0));
//
//        for (Map.Entry<KK, VV> entry : map.entrySet()) {
//            KK mapKey = entry.getKey();
//            VV mapValue = entry.getValue();
////            Record(KK,VV,getclass_name,getmajor_name);
////            后来新建的两张表是不需要的！！
//            System.out.println(mapKey + ":" + mapValue);
//        }


//        String jsonString = JSON.toJSONString(map);
//        System.out.println(jsonString);

//        String str = "****";
//        if (StringUtils.isNotBlank(str)) {
//            StringBuilder sb = new StringBuilder("18698587234");
//
//            sb.replace(3, 7, str);
//            System.err.println("========" + sb.toString());
//        }
//        String test = "liushengxi ";
//        System.out.println("test.charAt(2) == " + test.charAt(2));
//        StringBuilder sb = new StringBuilder(test);
//        sb.setCharAt(2, '6');
//        System.out.println("sb == " + sb);
//        System.out.println("test == " + test);

//        StringBuffer buffer = new StringBuffer(test);
//        System.out.println(buffer);
//        buffer.setCharAt(2, '6');
//        System.out.println(buffer);

////        test.charAt(2) = '6';
//        test.replace(test.charAt(2), '6');
//        System.out.println("Test == " + test);

//        Timestamp a = Timestamp.valueOf("1587568267000");
//        System.out.println(a.toString());
//
//        Timestamp b = Timestamp.valueOf("2018-05-11 09:32:32");
//        b.getTime();
//
////        if(b-a)
//        if (b.before(a)) {
//            System.out.println("b时间比a时间早");
//        }
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
