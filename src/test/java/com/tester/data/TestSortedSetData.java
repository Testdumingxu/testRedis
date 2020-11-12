package com.tester.data;

import org.testng.annotations.DataProvider;
import redis.clients.jedis.params.ZIncrByParams;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * RedisTestStringCases测试数据
 * 以数据驱动方式引入
 */
public class TestSortedSetData {


    // SortedSet通用测试数据
    @DataProvider(name = "SortedSet_all")
    public static Object[][] SortedSet_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_ZAdd")) {
            Map<String, Double> map = new HashMap<String, Double>();
            map.put("one", 1.0);
            map.put("two", 2.0);
            map.put("three", 3.0);
            return new Object[][]{
                    // 测试数据 key值为字符串
                    {"zaddkey", map, 3L}
            };
        } else if (methodName.equals("Test_ZCard")) {
            return new Object[][]{
                    {"zaddkey", 3L}, // 测试数据
            };
        } else if (methodName.equals("Test_ZAdd2")) {
            return new Object[][]{
                    {"zadd2key", 1.0, "hello", 1L} // 测试数据 随机移除setkey中的一个元素
            };
        }else if (methodName.equals("Test_ZCount2")) {
            return new Object[][]{
                    {"zcountkey", "2000.0", "5000.0", 3L} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZIncrBy")) {
            return new Object[][]{
                    {"zincrbykey", 2000.0, "tom", 4000.00} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZIncrBy2")) {
            byte[] var = {20};
            byte[] var2 = {30};
            double var3 = 2200.0;
            return new Object[][]{
                    {var, var3, var2} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZIncrBy3")) {
            byte[] var = {30};
            byte[] var2 = {40};
            double var3 = 2300.00;
            ZIncrByParams zIncrByParams = new ZIncrByParams();
            return new Object[][]{
                    {var, var3, var2, zIncrByParams} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZRange")) {
            return new Object[][]{
                    {"zaddkey", 0L, -1L} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZRank")) {
            return new Object[][]{
                    {"zaddkey", "two"}
            };
        } else if (methodName.equals("Test_ZRem")) {
            return new Object[][]{
                    {"ZRemkey", 1L, "tom"}, // 测试数据
                    {"ZRemkey", 2L, "jack", "peter"} // 测试数据
            };
        } else if (methodName.equals("Test_ZCount")) {
            return new Object[][]{
                    {"zcountkey", 2000.0, 4000.00, 2L}, // 测试数据 随机移除setkey中的一个元素
                    {"zcountkey", 50.0, 3950.00, 2L} // 测试数据 随机移除setkey中的一个元素
            };
        } else if (methodName.equals("Test_ZRangeByScore")) {
            return new Object[][]{
                    {"rangeByScoreKey", 2000.0, 4000.0}
            };
        } else if (methodName.equals("Test_ZRangeByScore2")) {

            byte[] var = {10};

            return new Object[][]{
                    {var, 2000.0, 5000.0}
            };
        }
        /**
         * else if (methodName.equals("Test_ZRangeByScore3")) {
         *             byte[] var = {10};
         *             byte[] var2 = {0};
         *             byte[] var3 = {20};
         *
         *             return new Object[][]{
         *                     {var, var2, var3}
         *             };
         *         }
         */
         else if (methodName.equals("Test_ZRangeByScore4")) {
            byte[] var = {10};

            return new Object[][]{
                    {var, 1000.00, 3000.00, 1, 2}
            };
        }
//         else if (methodName.equals("Test_ZRangeByScore5")) {
//            byte[] var = {10};
//            byte[] var2 = {20};
//            byte[] var3 = {30};
//
//            return new Object[][]{
//                    {var, var2, var3, 10.0, 20.0}
//            };
//        }
         else if (methodName.equals("Test_ZRevRangeByScore")) {
            return new Object[][]{
                    {"zrevrangeByScorekey", 2000.0, 4000.0}
            };
        } else if (methodName.equals("Test_ZRevRangeByScore2")) {
            byte[] var = {10};
            return new Object[][]{
                    {var, 1000.00, 3000.00}
            };
        } else if (methodName.equals("Test_ZRevRangeByScore3")) {
            byte[] var = {0};
            byte[] var2 = {0};
            byte[] var3 = {0};
            return new Object[][]{
                    {var, var2, var3}
            };
        } else if (methodName.equals("Test_ZRevRangeByScore4")) {
            byte[] var = {10};
            return new Object[][]{
                    {var, 1000.00, 2000.00, 3, 4}
            };
        } else if (methodName.equals("Test_ZRevRangeByScore5")) {
            byte[] var = {10};
            byte[] var2 = {20};
            byte[] var3 = {30};
            return new Object[][]{
                    {var, var2, var3, 1, 2}
            };
        } else if (methodName.equals("Test_ZrangeByScoreWithScores")) {
            return new Object[][]{
                    {"zrevrangeByScorekey", 1.00, 100.00}
            };
        } else if (methodName.equals("Test_ZrevrangeByScoreWithScores")) {
            return new Object[][]{
                    {"zrevrangeByScorekey", 100.00, 1.00}
            };
        } else if (methodName.equals("Test_ZRevRange")) {
            return new Object[][]{
                    {"zrevrangekey", 0L, -1L}
            };
        } else if (methodName.equals("Test_ZRevRank")) {
            return new Object[][]{
                    {"zrevrank", "tom"}
            };
        } else if (methodName.equals("Test_ZScan")) {
            return new Object[][]{
                    {"zscankey", "0"}
            };
        } else if (methodName.equals("Test_ZRemByRank")) {
            return new Object[][]{
                    {"zremrangeByRankKey", 0L, 1L}
            };
        } else if (methodName.equals("Test_ZScore")) {
            return new Object[][]{
                    {"zscorekey", "tom"}
            };
        } else if (methodName.equals("Test_ZRemRangeByScore")) {
            return new Object[][]{
                    {"zremRangeByScorekey", 2000.0, 3500.0}
            };
        } else if (methodName.equals("Test_ZRemRangeByScore2")) {
            byte[] var = {10};
            return new Object[][]{
                    {var, 1000.00, 3000.00}
            };
        } else if (methodName.equals("Test_ZRemRangeByScore3")) {
            byte[] var = {0};
            byte[] var2 = {0};
            byte[] var3 = {0};
            return new Object[][]{
                    {var, var2, var3}
            };
        } else if (methodName.equals("Test_Zlexcount")) {
            return new Object[][]{
                    {"zaddkey", "1", "2"}
            };
        } else if (methodName.equals("Test_ZrangeByLex")) {
            return new Object[][]{
                    {"zrangeByLexkey", "-", "+"}
            };
        } else if (methodName.equals("Test_ZrevrangeByLex")) {
            return new Object[][]{
                    {"ZrevrangeByLexkey", "-", "+"}
            };
        } else if (methodName.equals("Test_ZrangeWithScores")) {
            return new Object[][]{
                    {"ZrangeWithScores", 1L, 2L}
            };
        } else if (methodName.equals("Test_ZrevrangeByLex2")) {
            return new Object[][]{
                    {"ZrevrangeByLexkey", "-", "+",1,1}
            };
        } else if (methodName.equals("Test_ZrevrangeWithScores")) {
            return new Object[][]{
                    {"ZrevrangeWithScoreskey", 0L, 1L}
            };
        } else {
            return null;
        }
    }
}
