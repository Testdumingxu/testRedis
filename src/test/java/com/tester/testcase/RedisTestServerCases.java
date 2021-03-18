package com.tester.testcase;

import com.tester.util.RedisSubscriber;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;

@Test(groups = "server")
public class RedisTestServerCases {

    private Gcache bean;

    private RedisSubscriber redisSubscriber;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行server用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
        redisSubscriber = new RedisSubscriber();
    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束server用例--------");
        System.out.println("-----开始清除server缓存---------------");
        bean.del("foo");
    }

    @Test(description = "final String key;" +
            "Case:执行memoryUsage命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Server_All",
            dataProviderClass = com.tester.data.TestServerData.class)
    public void Test_memoryUsage01(final String key) {

        bean.set("foo", "cat");
        Long result = bean.memoryUsage(key);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "byte[] key,int samples;" +
            "Case:执行memoryUsage命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Server_All",
            dataProviderClass = com.tester.data.TestServerData.class)
    public void Test_memoryUsage02(int samples) {
        String key1 = "memorykey";
        String field1 = "usage1";
        String value1 = "usage1value";
        byte[] k = key1.getBytes();
        byte[] f = field1.getBytes();
        byte[] v = value1.getBytes();
        // 数据类型为byte的key
        Long hset = bean.hset(k, f, v);
//        System.out.println("hest:>>>" + hset);

        Long result = bean.memoryUsage(k, samples);

        System.out.println("-----------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }




}
