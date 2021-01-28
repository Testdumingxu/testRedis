package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.ListPosition;

import java.util.List;

@Test(groups = "list")
public class RedisTestListCases {
    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");

        System.out.println("--------开始准备测试数据用例--------");
        bean.rpush("listkey4", "Hello", "World");
        bean.rpush("listkey5", "Strat", "Stop");
        bean.lpush("lpushkey", "hello");
        bean.lpush("lsetkey", "lsetValue");
        bean.rpush("ltrimkey", "hello", "world", "foo", "bar");
        bean.rpush("lpopkey", "hello", "world", "foo", "bar");
        bean.rpush("blpopkey", "hello");
        bean.rpush("brpopkey", "world");
        bean.lpush("lremkey", "morning", "hello", "morning", "hello", "morning");

        System.out.println("--------开始执行Hash用例--------");

    }

    @AfterClass
    public void aftersuite() {
        System.out.println("--------执行结束Hash用例--------");
        System.out.println("-----开始清除key---------------");
        bean.del("listkey");
        bean.del("listkey2");
        bean.del("listkey4");
        bean.del("listkey5");
        bean.del("lpushkey");
        bean.del("lremkey");
        bean.del("lsetkey");
        bean.del("ltrimkey");
        bean.del("lpopmkey");
        bean.del("blpopkey");
        bean.del("brpopkey");

    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|创建一个值|插入多个值|插入重复值|</br>" +
            "Return:|插入后返回元素数量|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LPush(String key, String... value) {
        Long result = bean.lpush(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String key, long start, long end|</br>",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LrangeBytes(String key, long start, long end) {
        List<byte[]> result = bean.lrangeBytes(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2|</br>" +
            "Case:|下标为0|下标为-1|下标超出区间范围|</br>" +
            "Return:|返回下标索引处的值|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LIndex(String key, long index, String expect) {
        bean.lpush("listkey2", "listValue3", "listValue2", "listValue");
        String result = bean.lindex(key, index);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|插入一个值|插入多个值|插入重复值|</br>" +
            "Return:|插入后返回元素数量|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_RPush(String key, String... value) {
        Long result = bean.rpush(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|对存在的key插入值|对不存在的key插入值|</br>" +
            "Return:|插入成功后返回元素数量|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_RPushx(String key, String... value) {
        Long result = bean.rpushx(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, ListPosition var2, String var3, String var4|</br>" +
            "Case:|在X值之前插入|在X值之后插入|X值不存在时插入|</br>" +
            "Return:|插入成功后返回元素数量|插入失败返回0|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LInsert(String key, ListPosition listPosition, String value, String newValue, Long expect) {
        Long result = bean.linsert(key, listPosition, value, newValue);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|列表非空|列表为空|</br>" +
            "Return:|列表为空返回0|返回列表元素数量|",
            dependsOnMethods = {"Test_LPush"},
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LLen(String key, Long expect) {
        Long result = bean.llen(key);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|移除一个key的头元素|key不存在|</br>" +
            "Return:|成功返回key的头元素|key不存在返回nil|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LPop(String key, String expect) {
        String result = bean.lpop(key);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|空列表插入值|非空列表插入值|</br>" +
            "Return:|插入成功后返回元素数量|插入失败返回0|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LPushx(String key, Long expect, String... value) {
        Long result = bean.lpushx(key, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2, long var4|</br>" +
            "Case:|start起始位置，end结束位置|</br>" +
            "Return:|返回给定位置的元素|",
            dependsOnMethods = {"Test_LPush"},
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LRange(String key, long start, long end) {
        List<String> result = bean.lrange(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2, String var4|</br>" +
            "Case:|从表头移除count数量的值|从表尾移除count数量的值|count=0移除所有value的值|</br>" +
            "Return:|返回被移除元素数量|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LRem(String key, Long count, String value, Long expect) {
        Long result = bean.lrem(key, count, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2, String var4|</br>" +
            "Case:|创建一个值|插入多个值|插入重复值|</br>" +
            "Return:|插入后返回元素数量|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LSet(String key, Long index, String value, String expect) {
        String result = bean.lset(key, index, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, long var2, long var4|</br>" +
            "Case:|移除start和end范围之外的元素|</br>" +
            "Return:|成功返回OK|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_LTrim(String key, long start, long end, String expect) {
        String result = bean.ltrim(key, start, end);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|弹出末尾元素|key不存在|</br>" +
            "Return:|key存在返回被弹出的元素|key不存在返回nil",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_RPop(String key, String expect) {
        String result = bean.rpop(key);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|在指定时间内查找弹出的元素|</br>" +
            "Return:|在指定时间内查找弹出的元素|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_BlPop(Integer time, String key) {
        List<String> result = bean.blpop(time, key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|弹出key尾部元素|</br>" +
            "Return:|在指定时间内查找弹出的元素|",
            dataProvider = "List_all",
            dataProviderClass = com.tester.data.TestListData.class)
    public void Test_BrPop(int time, String key) {
        List<String> result = bean.brpop(time, key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }
}
