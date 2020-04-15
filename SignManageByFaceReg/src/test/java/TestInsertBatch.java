//import com.huarun.dao.CourseStudentMapper;
//import com.huarun.pojo.CourseStudent;
//import com.huarun.test.StuIDpojo;
//import com.mysql.cj.util.Util;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class TestInsertBatch {
//
//
//    @Test
//    public void test_01() {
//        SqlSession session =
//        List<StuIDpojo> availableList = new ArrayList<>();
//
//        StuIDpojo stuIDpojo = new StuIDpojo("04161173");
//        availableList.add(stuIDpojo);
//
//        StuIDpojo stuIDpojo1 = new StuIDpojo("04161172");
//        availableList.add(stuIDpojo1);
//
//        StuIDpojo stuIDpojo2 = new StuIDpojo("04161177");
//        availableList.add(stuIDpojo2);
//
//        StuIDpojo stuIDpojo3 = new StuIDpojo("04161178");
//        availableList.add(stuIDpojo3);
//
//        StuIDpojo stuIDpojo4 = new StuIDpojo("04161179");
//        availableList.add(stuIDpojo4);
//
//        for (StuIDpojo tt : availableList) {
//            System.out.println("导入的有效的ID是:" + tt);
//        }
//        @Test
//        public void dynamicForeach3Test () {
//
//            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
//            final List ids = new ArrayList();
//            ids.add(1);
//            ids.add(2);
//            ids.add(3);
//            ids.add(6);
//            ids.add(7);
//            ids.add(9);
//            Map params = new HashMap();
//            params.put("ids", ids);
//            params.put("title", "中国");
//            List blogs = blogMapper.dynamicForeach3Test(params);
//            for (Blog blog : blogs)
//                System.out.println(blog);
//            session.close();
//        }
//
//    }
//}
