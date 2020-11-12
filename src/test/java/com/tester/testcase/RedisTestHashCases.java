package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

@Test(groups = "hash")
public class RedisTestHashCases {
    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行Hash用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("google", "www.google.com");
        map.put("yahoo", "www.yahoo.com");


        System.out.println("---------------开始创建数据------------------");
        bean.hset("hexistskey", "hexistsname", "hexistsValue");
        bean.hmset("hmgetkey", map );

    }

    @AfterClass
    public void aftersuite() {
        System.out.println("--------执行结束Hash用例--------");
        System.out.println("-----开始清除key---------------");
        bean.del("hashkey22");
        bean.del("hashkey33");
        bean.del("hashkey");
        bean.del("hashincrekey");
        bean.del("hsetnxkey");
        bean.del("hexistskey");
        bean.del("hmgetkey");
    }

    @Test(description = "Param:|String var1, String var2, String var3|</br>" +
            "Case:|创建一个哈希表|覆盖哈希表中的旧值</br>" +
            "Return:|创建返回 1 覆盖返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hset(String key, String name, String value2) {
        Long result = bean.hset(key, name, value2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|创建一个哈希表|覆盖哈希表中的旧值</br>" +
            "Return:|创建返回 1 覆盖返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hset2(String key, String name, byte[] value2) {
        Long result = bean.hset(key, name, value2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|byte[] key, byte[] name, byte[] value2|</br>" +
            "Case:|创建一个哈希表|覆盖哈希表中的旧值</br>" +
            "Return:|创建返回 1 覆盖返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hset3(byte[] key, byte[] name, byte[] value2) {
        Long result = bean.hset(key, name, value2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|byte[] key, byte[] name, byte[] value2|</br>" +
            "Case:|创建一个哈希表|覆盖哈希表中的旧值</br>" +
            "Return:|创建返回 1 覆盖返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hset4(byte[] var1, Map<byte[], byte[]> var2) {
        Long result = bean.hset(var1, var2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String key,String field|</br>",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HgetBytes(String key,String field) {
        byte[] result = bean.hgetBytes(key, field);
//        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String key,String field|</br>",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HmsetBytes(String key, Map<String, byte[]> hash) {
        String result = bean.hmsetBytes(key, hash);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String key,String field|</br>",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hmsetex(String key, Map<String, String> hash,int seconds) {
        String result = bean.hmsetex(key, hash, seconds);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }



    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|删除一个字段|删除2个字段|删除不存在的字段|</br>" +
            "Return:|返回1|返回2|返回0|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset","Test_Hexists","Test_Hget"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hdel(String key, String... name) {
        Long result = bean.hdel(key, name);
        int result2 = result.intValue();
        switch (result2) {
            case 0:
                Reporter.log("删除不存在的字段：" + result);
                break;
            case 1:
                Reporter.log("删除一个字段成功: " + result);
                break;
            case 2:
                Reporter.log("删除两个字段成功: " + result);
                break;
            default:
                Reporter.log("用例不通过实际结果为:" + result);
        }
    }

    @Test(description = "Param:|byte[] var1, byte[]... var2|</br>" +
            "Case:|删除bytekey|</br>" +
            "Return:|创建返回 1 覆盖返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hdel2(byte[] var1, byte[]... var2) {
        Long result = bean.hdel(var1, var2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|字段存在|key不存在|字段不存在|</br>" +
            "Return:|存在返回true|key不存在返回false|字段不存在返回false|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hexists(String key, String name) {
        Boolean result = bean.hexists(key, name);
        if (result) {
            Assert.assertTrue(result);
            Reporter.log("key：" + key + "|字段：" + name +"存在" + "|实际结果: " + result);
        } else {
            Assert.assertFalse(result);
            Reporter.log(key + "or" + name +"不存在|" + "|实际结果: " + result);
        }
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取指定字段的值|指定字段不存在|key不存在|</br>" +
            "Return:|存在返回字段值|不存在返回nil|返回nil|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hget(String key, String name, String expect) {
        String result = bean.hget(key, name);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: "+ expect + "|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, byte[] var2|</br>" +
//            "Case:|获取指定字段的值|指定字段不存在|key不存在|</br>" +
//            "Return:|存在返回字段值|不存在返回nil|返回nil|",
//            dataProvider = "Hash_all",
//            dataProviderClass = com.tester.data.TestHashData.class)
//    public void Test_Hget2(String key, String name, String expect) {
//        String result = bean.hget(key, name);
//        Assert.assertEquals(expect, result);
//        Reporter.log("预期结果: "+ expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|key存在|key不存在|</br>" +
            "Return:|存在返回所有字段及字段值|不存在返回空列表|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HgetAll(String key) {
        Map result = bean.hgetAll(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|key存在|key不存在|</br>" +
            "Return:|存在返回所有字段及字段值|不存在返回空列表|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HgetAll2(byte[] key) {
        Map result = bean.hgetAll(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|key存在|key不存在|</br>" +
            "Return:|存在返回所有字段及字段值|不存在返回空列表|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HgetAllMSBytes(String key) {
        Map result = bean.hgetAllMSBytes(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|increment为正数200|increment为负数50|</br>" +
            "Return:|创建相加后的值|返回减去50后的值|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HincrBy(String key, String name, Long num, Long expect) {
        Long result = bean.hincrBy(key, name, num);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + " |实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|传入小数|传入指数符号|key不存在|key存在字段不存在|</br>" +
            "Return:|返回相加后的值|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HincrByFloat(String key, String name, Double value, Double expect) {
        Double result = bean.hincrByFloat(key, name, value);
        Assert.assertEquals(result,expect);
        Reporter.log("预期结果: " + expect + " |实际结果: " + result);
    }

    @Test(description = "Param:|final byte[] key, final byte[] field, final double value|</br>" +
            "Case:|传入小数|传入指数符号|key不存在|key存在字段不存在|</br>" +
            "Return:|返回相加后的值|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_HincrByFloat2(final byte[] key, final byte[] field, final double value) {
        Double result = bean.hincrByFloat(key, field, value);
        Assert.assertNotNull(result);
        Reporter.log(  " |实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|key存在|key不存在</br>" +
            "Return:|返回所有字段|返回nil|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hkeys(String key) {
        Set result = bean.hkeys(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|正确返回字段的数量|key不存在</br>" +
            "Return:|返回哈希表key中字段的数量|返回0|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hlen(String key, Long expect) {
        Long result = bean.hlen(key);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|在key中给定一个存在的字段|在key给定一个不存在的字段|</br>" +
            "Return:|创建返回key中的一个字段值|字段不存在返回nil|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hmget(String key, String...name) {
        List<String> result = bean.hmget(key, name);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|插入多个字段|重复插入字段</br>" +
            "Return:|创建返回 OK| 返回OK将替换旧值|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hmset(String key, Map<String,String> name, String expect) {
        String result = bean.hmset(key, name);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|key的字段不存在插入值|key中字段存在插入值</br>" +
            "Return:|成功返回 1| 无效返回0|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class,
            enabled = false) // 跳过执行
    public void Test_Hsetnx(String key, String name, byte[] value2, Long expect) {
        Long result = bean.hsetnx(key, name, value2);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
//            "Case:|key的字段不存在插入值|key中字段存在插入值</br>" +
//            "Return:|成功返回 1| 无效返回0|",
//            dataProvider = "Hash_all",
//            dataProviderClass = com.tester.data.TestHashData.class)
//    public void Test_Hsetnx2(byte[] key, byte[] name, byte[] value2, Long expect) {
//        Long result = bean.hsetnx(key, name, value2);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1|</br>" +
            "Case:|key存在|key不存在</br>" +
            "Return:|返回所有数据|返回nil|",
            dataProvider = "Hash_all",
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hvals(String key) {
        List result = bean.hvals(key);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取所有元素数量|</br>" +
            "Return:|返回所有元素|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hscan(String key, String name) {
        ScanResult result = bean.hscan(key, name);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取所有元素数量|</br>" +
            "Return:|返回所有元素|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hscan2(final String key, final String cursor, final int count) {
        ScanResult result = bean.hscan(key, cursor, count);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取所有元素数量|</br>" +
            "Return:|返回所有元素|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hscan3(String var1, String var2) {
        ScanResult result = bean.hscan(var1, var2, ScanParams.SCAN_POINTER_START.codePointCount(0,1));
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取所有元素数量|</br>" +
            "Return:|返回所有元素|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hscan4(byte[] var1, byte[] var2) {
        ScanResult result = bean.hscan(var1, var2);
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2, byte[] var3|</br>" +
            "Case:|获取所有元素数量|</br>" +
            "Return:|返回所有元素|",
            dataProvider = "Hash_all",
            dependsOnMethods = {"Test_Hset"},
            dataProviderClass = com.tester.data.TestHashData.class)
    public void Test_Hscan5(byte[] var1, byte[] var2, ScanParams scanParams) {
        ScanResult result = bean.hscan(var1, var2, scanParams.count(1));
        Assert.assertNotNull(result);
        Reporter.log("实际结果: " + result);
    }
}
