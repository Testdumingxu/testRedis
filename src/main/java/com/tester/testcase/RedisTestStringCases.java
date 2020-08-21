package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import redis.Gcache;
import redis.clients.jedis.exceptions.JedisDataException;

@Test(groups = "string")
public class RedisTestStringCases {

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
    }

    @Test(description = "Param:String var1, String var2;" +
            "Case:指定key中插入value;" +
            "Return:返回 OK",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Set(String key, String value) {
        String result = bean.set(key, value);
        Reporter.log("插入的key值：" + key + ";" + "value值：" + bean.get(key));
        Assert.assertEquals("OK", result);
        Reporter.log("预期结果: OK | " + "实际结果: " + result);
    }

    @Test(description = "Param:String var1, String var2;" +
            "Case:key已存在追加到末尾;" +
            "Return: key 中字符串的长度",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Append(String key, String value) {
        Long result = bean.append(key, value);
        Reporter.log("插入的key值：" + key + ";" + "value值：" + result);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:String var1;" +
            "Case:计算被设置为 1 的比特位的数量;" +
            "Return: 被设置为 1 的位的数量",
            dataProvider = "String_all",//String_key
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_BitCount(String key) {
        Long result = bean.bitcount(key);
        Reporter.log("统计的key值：" + key + ";" + "value值：" + result);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:String var1 long var2, long var4;" +
            "Case:计算被设置为 1 的比特位的数量;" +
            "Return: 被设置为 1 的位的数量",
            dataProvider = "String_all",//String_key
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_BitCount2(String key) {
        Long result = bean.bitcount(key, 0, 1);
        Reporter.log("统计的key值：" + key + ";" + "value值：" + result);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:String var1;" +
            "Case:对存在的key且为数字值 or 不存在的key or key的值不为数字 进行 DECR;" +
            "Return: 存在的key:key本身-1|不存在的key-1|key不为数字: 抛异常",
            dataProvider = "String_all",//String_key
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Decr(String key) {
        // 这个key存在但不是数值类型的会引发异常
        if ("string_key".equals(key)) {
            Long result = bean.decr(key);
            throw new JedisDataException("key存在但不是数值的");
        } else {
            Long result = bean.decr(key);
            Reporter.log("统计的key值：" + key + ";" + "value值：" + result);
            Assert.assertNotNull(result);
            Reporter.log("实际结果: " + result);
        }
    }

    @Test(description = "Param:String var1 long var2;" +
            "Case:对存在的key且为数字值 or 不存在的key or key的值不为数字 进行 DECR;" +
            "Return: 存在的key:key本身-1|不存在的key-1|key不为数字: 抛异常",
            dataProvider = "String_all",//String_key
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_DecrBy(String key) {
        // 这个key存在但不是数值类型的会引发异常
        Long result = bean.decrBy(key, 2);
        Reporter.log("统计的key值：" + key + ";" + "value值：" + result);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:String var1;" +
            "Case:正常获取到key值;" +
            "Return: 当 key 不存在时，返回nil,否则，返回 key 的值: 抛异常",
            dependsOnMethods = {"Test_Set"},
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Get(String key, String value) {
        String result = bean.get(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:String var1;" +
            "Case:获取的key值存在 or 不存在;" +
            "Return: 存在返回1|不存在返回0",
            dependsOnMethods = {"Test_Set"},
            dataProvider = "String_all",//String_getbit
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_GetBit(String key, Boolean value) {
        Boolean result = bean.getbit(key, 1);
        Assert.assertEquals(value, result);
        Reporter.log("预期结果: " + value + " | " + "实际结果: " + result);
    }

    @Test(description = "String var1, long var2, long var4;" +
            "Case:正常传入start:0,end:2;" +
            "Return: 截取得出的子字符串",
            dependsOnMethods = {"Test_Set"},
            dataProvider = "String_all",//String_getrange
            dataProviderClass = com.tester.data.TestStringData.class
    )
    public void Test_GetRange(String key) {
        String result = bean.getrange(key, 0, 2);
        Assert.assertEquals("str", result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, String var2;" +
            "Case:设置value值返回旧的值;" +
            "Return: 返回给定 key 的旧值",
            dataProvider = "String_all",//String_GetSet
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_GetSet(String key, String value) {
        String result = bean.getSet(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1;" +
            "Case:key中储存的数字值增一;" +
            "Return: 返回+1后的值",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Incr(String key) {
        Long result = bean.incr(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, long var2;" +
            "Case:将 key 所储存的值+2;" +
            "Return: 返回相加后的值",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_IncrBy(String key) {
        bean.set("stringcount", "20");
        Long result = bean.incrBy(key, 2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, double var2;" +
            "Case:将 key 所储存的值+1.1;" +
            "Return: 返回给定 key 的旧值",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_IncrByFloat(String key, double value) {
        Double result = bean.incrByFloat(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, long var2, String var4;" +
            "Case:设置key值1000ms;" +
            "Return: 返回OK",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Psetex(String key, Long num, String value) {
        String result = bean.psetex("key", num, value);
        Assert.assertEquals("OK", result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, long var2, String var4;" +
            "Case:偏移量上的位为1;" +
            "Return: 返回指定偏移量原来储存的位",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Setbit(String key, Long value, String offset) {
        Boolean result = bean.setbit(key, value, offset);
        Assert.assertTrue(result);
        Reporter.log("实际结果: " + result);
    }


    @Test(description = "String var1, int var2, String var3;" +
            "Case:设置生存时间为60s;" +
            "Return: OK",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Setex(String key, Integer time, String value) {
        String result = bean.setex(key, time, value);
        Assert.assertEquals("OK", result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, String var2;" +
            "Case:key不存在时值插入成功|key存在时值不会被覆盖;" +
            "Return: 成功返回 1 失败返回 0 ",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_Setnx(String key, String value) {
        Long result = bean.setnx(key, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1, long var2, String var4;" +
            "Case:根据给定的偏移量覆盖key存储的value值;" +
            "Return: 返回字符串的长度",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_SetRange(String key, Long num, String value) {
        bean.set("stringSetRange", "hello world");
        Long result = bean.setrange(key, num, value);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "String var1;" +
            "Case:正确返回字符串长度|key不存在返回0;" +
            "Return: 返回字符串长度",
            dataProvider = "String_all",
            dataProviderClass = com.tester.data.TestStringData.class)
    public void Test_StrLen(String key) {
        Long result = bean.strlen(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }
}
