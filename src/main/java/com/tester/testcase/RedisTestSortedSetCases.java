package com.tester.testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Test(groups = "sortedset")
public class RedisTestSortedSetCases {

    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行set用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");

        Map<String, Double> map = new HashMap<String, Double>();
        map.put("jack", 2000.0);
        map.put("peter", 3500.0);
        map.put("tom", 5000.0);

        System.out.println("--------开始造测试数据-----------");
        bean.zadd("rangeByScoreKey", 2000, "xiaoming");
        bean.zadd("rangeByScoreKey", 4000, "xiaohong");
        bean.zadd("ZRemkey", map);
        bean.zadd("zremrangeByRankKey", map);
        bean.zadd("zremRangeByScorekey", map);
        bean.zadd("zrevrangekey", map);
        bean.zadd("zrevrangeByScorekey", map);
        bean.zadd("zrevrank", map);
        bean.zadd("zscankey", map);
        bean.zadd("zscorekey", map);


        Map<String, Double> zincrby_map = new HashMap<String, Double>();
        zincrby_map.put("tom", 2000.0);

        bean.zadd("zcountkey", map);
        bean.zadd("zincrbykey", zincrby_map);
    }

    @AfterClass
    public void aftersuite() {
        System.out.println("--------执行结束set用例--------");
        System.out.println("-----开始清除key---------------");
        // 清除key
        bean.del("zaddkey");
        bean.del("zadd2key");
        bean.del("zcountkey");
        bean.del("zincrbykey");
        bean.del("rangeByScoreKey");
        bean.del("ZRemkey");
        bean.del("zremrangeByRankKey");
        bean.del("zremRangeByScore");
        bean.del("zrevrangekey");
        bean.del("zrevrangeByScorekey");
        bean.del("zrevrank");
        bean.del("zscankey");
        bean.del("zscore");

    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|正常将元素插入到集合key中|</br>" +
            "Return:|返回插入后的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZAdd(String key, Map<String, Double> value, Long expect) {
        Long result = bean.zadd(key, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|正常将元素插入到集合key中|</br>" +
            "Return:|返回插入后的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZAdd2(String key, double name, String value, Long expect) {
        Long result = bean.zadd(key, name, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|key存在|key不存在|</br>" +
            "Return:|返回有序集合数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZCard(String key, Long expect) {
        Long result = bean.zcard(key);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String key, double min, double max|</br>" +
            "Case:|计算2000-5000薪水内的人数|</br>" +
            "Return:|返回min-max范围内的成员数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZCount(String key, double min, double max, Long expect) {
        Long result = bean.zcount(key, min, max);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String key, String min, String max|</br>" +
            "Case:|正常将元素插入到集合key中|</br>" +
            "Return:|返回插入后的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZCount2(String key, String min, String max, Long expect) {
        Long result = bean.zcount(key, min, max);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, double var2, String var4|</br>" +
            "Case:|为成员member增量2000| 为成员member -50</br>" +
            "Return:|返回增量后的新值|返回减量后的新值",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZIncrBy(String key, double value, String name, Double expect) {
        Double result = bean.zincrby(key, value, name);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZIncrBy2(String var1, double var2, String var4, ZIncrByParams var5) {
//        Long result = bean.zincrby(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1, long var2, long var4|</br>" +
            "Case:|指定区间值|</br>" +
            "Return:|返回有序的成员集合|",
            dependsOnMethods = {"Test_ZAdd"},
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRange(String key, long start, long end) {
        Set<String> result = bean.zrange(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, double var2, double var4|</br>" +
            "Case:|指定区间值|</br>" +
            "Return:|返回有序的成员集合|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRangeByScore(String key, double start, double end) {
        Set<String> result = bean.zrangeByScore(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|指定区间值|</br>" +
//            "Return:|返回有序的成员集合|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRangeByScore2(String key, String min, String max) {
//        Long result = bean.zrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRangeByScore3(String key, double name, String value, Long expect) {
//        Long result = bean.zrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRangeByScore4(String key, double name, String value, Long expect) {
//        Long result = bean.zrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|查看一个member的排名|</br>" +
            "Return:|返回score 的排名|",
            dependsOnMethods = {"Test_ZAdd"},
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRank(String key, String value) {
        Long result = bean.zrank(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String... var2|</br>" +
            "Case:|移除一个成员|移除过个成员|</br>" +
            "Return:|返回被成功移除的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRem(String key, Long expect, String... value) {
        Long result = bean.zrem(key, value);
        Assert.assertEquals(result, expect);
        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
    }

    @Test(description = "Param:|String key, long start, long end|</br>" +
            "Case:|移除下标范围内的成员|</br>" +
            "Return:|返回被移除的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRemByRank(String key, long start, long end) {
        Long result = bean.zremrangeByRank(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|移除指定位置内的成员|</br>" +
            "Return:|返回被移除的成员数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRemRangeByScore(String key, double start, double end) {
        Long result = bean.zremrangeByScore(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRemByRank2(String key, double name, String value, Long expect) {
//        Long result = bean.zremrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1, long var2, long var4|</br>" +
            "Case:|倒序输出|</br>" +
            "Return:|返回成员的结果集|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRevRange(String key, long start, long end) {
        Set<String> result = bean.zrevrange(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|倒序输出|</br>" +
            "Return:|返回成员的结果集|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRevRangeByScore(String key, double start, double end) {
        Set<String> result = bean.zrevrangeByScore(key, start, end);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRevRangeByScore2(String key, double name, String value, Long expect) {
//        Long result = bean.zrevrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
////            "Case:|正常将元素插入到集合key中|</br>" +
////            "Return:|返回插入后的元素数量|",
////            dataProvider = "SortedSet_all",
////            dataProviderClass = com.tester.data.TestSortedSetData.class)
////    public void Test_ZRevRangeByScore3(String key, double name, String value, Long expect) {
////        Long result = bean.zrevrangeByScore(key, name, value);
////        Assert.assertEquals(result, expect);
////        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
////    }

//    @Test(description = "Param:|String var1, String var2|</br>" +
//            "Case:|正常将元素插入到集合key中|</br>" +
//            "Return:|返回插入后的元素数量|",
//            dataProvider = "SortedSet_all",
//            dataProviderClass = com.tester.data.TestSortedSetData.class)
//    public void Test_ZRevRangeByScore4(String key, double name, String value, Long expect) {
//        Long result = bean.zrevrangeByScore(key, name, value);
//        Assert.assertEquals(result, expect);
//        Reporter.log("预期结果: " + expect + "|实际结果: " + result);
//    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|对成员进行排名|</br>" +
            "Return:|返回某个成员的排名|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZRevRank(String key, String value) {
        Long result = bean.zrevrank(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|正常将元素插入到集合key中|</br>" +
            "Return:|返回插入后的元素数量|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZScore(String key, String value) {
        Double result = bean.zscore(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }

    @Test(description = "Param:|String var1, String var2|</br>" +
            "Case:|匹配给定值|</br>" +
            "Return:|返回一个有序集合|",
            dataProvider = "SortedSet_all",
            dataProviderClass = com.tester.data.TestSortedSetData.class)
    public void Test_ZScan(String key, String value) {
        ScanResult result = bean.zscan(key, value);
        Assert.assertNotNull(result);
        Reporter.log("|实际结果: " + result);
    }
}
