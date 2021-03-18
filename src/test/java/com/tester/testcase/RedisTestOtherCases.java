package com.tester.testcase;

import com.tester.util.RedisSubscriber;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.GcacheClient;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test(groups = "other")
public class RedisTestOtherCases {
    private GcacheClient bean;

    private RedisSubscriber redisSubscriber;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行other用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (GcacheClient) app.getBean("testGcache");
        redisSubscriber = new RedisSubscriber();
    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束other用例--------");
        System.out.println("-----开始清除other缓存---------------");

    }

    @Test(description = "final String key;" +
            "Case:执行unlink命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Other_All",
            dataProviderClass = com.tester.data.TestOtherData.class
    )
    public void Test_unLink01() {
        bean.set("unlinkKey", "unlinkValue");
        Long result = bean.unlink("unlinkKey");
//        System.out.println("------------");
//        System.out.println(result);
//        System.out.println("------------");
        Assert.assertNotNull(result);
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String... keys;" +
            "Case:执行unlink命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Other_All",
            dataProviderClass = com.tester.data.TestOtherData.class
    )
    public void Test_unLink02() {
        bean.set("{unlinkKey}11", "unlinkValue11");
        bean.set("{unlinkKey}22", "unlinkValue22");
        String[] srr = {"{unlinkKey}11", "{unlinkKey}22"};

        Long result = bean.unlink(srr);
//        System.out.println("------------");
//        System.out.println(result);
//        System.out.println("------------");
        Assert.assertNotNull(result);
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }
}
