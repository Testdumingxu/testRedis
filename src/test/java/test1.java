import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import redis.Gcache;

public class test1 {

    private Gcache bean;

    @Test
    public void teststringbuilder() {
//        String strjson = "http://www.baidu.com";
//        StringBuilder stringBuilder = new StringBuilder(strjson);
////        stringBuilder.append(strjson).append("?");
//        long  timeNew =  System.currentTimeMillis()/ 1000;
//        System.out.println(timeNew);
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
        bean.set("key","test1");
        bean.close();
    }
}
