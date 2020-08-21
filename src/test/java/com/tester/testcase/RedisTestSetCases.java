package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.ScanResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

@Test(groups = "set")
public class RedisTestSetCases {

    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行set用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
    }

    @AfterClass
    public void aftersuite() {
        System.out.println("--------执行结束set用例--------");
        System.out.println("-----开始清除key---------------");
        // 清除键
        bean.del("setkey");
        bean.del("set_sremtest_key");
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|正常将元素插入到集合key中|</br>" +
            "Return:|返回 1 或者0|",
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Sadd(String key, String... value) {
        Object result = null;
        result = bean.sadd(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|key存在|key不存在|</br>" +
            "Return:|返回集合中元素的数量|key不存在时返回 0|",
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Scard(String key) {
        Long result = bean.scard(key);
        System.out.println(result);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|元素存在|元素不存在|</br>" +
            "Return:|存在返回true|不存在返回false|",
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_SisMember(String key, String value, Boolean expect) {
        Boolean result = bean.sismember(key, value);
        Assert.assertEquals(expect, result);
        Reporter.log("预期结果: " + expect + ";" + "实际结果: " + result);
    }


    @Test(description = "Param:|String|</br>" +
            "Case:|随机移除一个元素|</br>" +
            "Return:|返回被移除的多个元素|返回nil|",
            dependsOnMethods = {"Test_Sadd", "Test_SrandMember"}, // 依赖方法
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Spop(String key) {
        String result = bean.spop(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2|</br>" +
            "Case:|随机移除多个元素|集合不存在|</br>" +
            "Return:|返回被移除的多个元素|返回nil|",
            dependsOnMethods = {"Test_Sadd", "Test_SrandMember"}, // 依赖方法
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Spop2(String key, Long value) {
        Set<String> result = bean.spop(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }


    @Test(description = "Param:|String var1, int var2|</br>" +
            "Case: |仅提供key参数|</br>" +
            "Return:|随机返回一个值|",
            dependsOnMethods = {"Test_Sadd"},
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_SrandMember(String key) {
        Object result = null;
        result = bean.srandmember(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|移除指定的元素|移除多个元素|</br>" +
            "Return:|返回被成功移除的元素的数量|",
            dataProvider = "Set_all",
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Srem(String key, String... value) {
        if (value != null) {
            Long result = bean.srem(key, value);
            Assert.assertNotNull(result);
            Reporter.log("实际结果: " + result);
        }
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|元素存在|元素不存在|</br>" +
            "Return:|存在返回true|不存在返回false|",
            dataProvider = "Set_all",
            dependsOnMethods = {"Test_Sadd"},
            dataProviderClass = com.tester.data.TestSetData.class)
    public void Test_Sscan(String key) {
        ScanResult result = bean.sscan(key, "0");
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }
}
