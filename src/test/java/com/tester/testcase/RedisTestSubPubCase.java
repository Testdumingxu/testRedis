package com.tester.testcase;

import com.tester.util.RedisSubscriber;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test(groups = "subpub")
public class RedisTestSubPubCase {

    private Gcache bean;

    private RedisSubscriber redisSubscriber;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行subpub用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
        redisSubscriber = new RedisSubscriber();
    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束subpub用例--------");
        System.out.println("-----开始清除subpub缓存---------------");
    }

    /**
     * 订阅消息
     * @param channel
     * @param timeoutMilliSeconds
     */
//    @Test(description = "final JedisPubSub jedisPubSub, final String channel, final int timeoutMilliSeconds;" +
//            "Case:执行subscribeOne脚本;" +
//            "Return:返回 Object",
//            dataProvider = "SubPub_All",
//            dataProviderClass = com.tester.data.TestSubPubData.class)
//    public void Test_subscribeOne(final String channel, final int timeoutMilliSeconds) {
//        System.out.println("-----------启动订阅者-----------");
//        // 订阅频道
//        bean.subscribeOne(redisSubscriber, channel, timeoutMilliSeconds);

//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
//        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
//    }


}
