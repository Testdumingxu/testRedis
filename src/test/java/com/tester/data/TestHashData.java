package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * RedisTestStringCases测试数据
 * 以数据驱动方式引入
 */
public class TestHashData {


    // string通用测试数据
    @DataProvider(name = "Hash_all")
    public static Object[][] Hash_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_Hset")) {
            return new Object[][]{
                    // 测试数据 创建一个hash表key为hashkey
                    {"hashkey", "hashname", "hashValue"},
                    {"hashkey", "hashname2", "hashValue2"},
                    {"hashkey", "hashname3", "hashValue3"},
                    // 将旧值覆盖
                    {"hashkey", "hashname", "hashNewValue"},
            };
        } else if (methodName.equals("Test_Hset2")) {
            // 创建一个byte数组
            byte[] var = new byte[3];
            var[0] = 20;
            var[1] = 40;
            var[2] = 60;
            // 新的byte[]覆盖上面的值
            byte[] var2 = var;
            var2[0] += 1;
            var2[1] += 1;
            var2[2] += 1;
            return new Object[][]{
                    // 测试数据 创建一个hash表key为hashkey,值为byte[]
                    {"hashkey2", "hashname", var},
                    // 将旧值覆盖,值为byte[]
                    {"hashkey2", "hashname", var2}
            };
        } else if (methodName.equals("Test_Hexists")) {
            return new Object[][]{
                    // 测试数据 hashname字段存在
                    {"hexistskey", "hexistsname"},
                    // key不存在
                    {"hash_key_not_exist", "hashname2"},
                    // hash_not_exist不存在的字段
                    {"hexistskey", "hash_not_exist"}
            };
        } else if (methodName.equals("Test_HgetAll")) {
            return new Object[][]{
                    // 测试数据 hashkey存在
                    {"hexistskey"},
                    // key不存在
                    {"hash_key_not_exist"}
            };
        } else if (methodName.equals("Test_Hkeys")) {
            return new Object[][]{
                    // 测试数据 hashkey存在
                    {"hashkey"},
                    // key不存在
                    {"hash_key_not_exist"}
            };
        } else if (methodName.equals("Test_HincrBy")) {
            return new Object[][]{
                    // 测试数据 对hashincrekey的hashincrename字段进行增量200
                    {"hashincrekey", "hashincrename", 200L, 200L},
                    // 对hashincrekey的hashincrename字段进行增量200
                    {"hashincrekey", "hashincrename", -50L, 150L}
            };
        } else if (methodName.equals("Test_Hget")) {
            return new Object[][]{
                    // 测试数据 获取指定字段的值
                    {"hashkey", "hashname3", "hashValue3"},
                    // key不存在
                    {"hashkey", "hash_key_not_exist", null},
                    // hash_not_exist不存在的字段
                    {"hashkey", "hash_not_exist", null}
            };
        } else if (methodName.equals("Test_Hget2")) {
            return new Object[][]{
                    // 测试数据 获取指定字段的值
                    {"hashkey", "hashname3", "hashValue3"},
                    // key不存在
                    {"hashkey", "hash_key_not_exist", "nil"},
                    // hash_not_exist不存在的字段
                    {"hashkey", "hash_not_exist", "nil"}
            };
        } else if (methodName.equals("Test_HincrByFloat")) {
            return new Object[][]{
                    // 测试数据 传入指数符号
                    {"hashkey22", "hashname22", 2.0e2, 200.0},
                    // 传入小数
                    {"hashkey33", "hashname33", 10.50, 10.5}
            };
        } else if (methodName.equals("Test_Hlen")) {
            return new Object[][]{
                    // 测试数据 key存在
                    {"hashkey", 3L},
                    // key不存在
                    {"hashkey_absence", 0L}
            };
        } else if (methodName.equals("Test_Hmset")) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("google","www.google.com");
            hashMap.put("yahoo","www.yahoo.com");

            HashMap<String, String> hashMap2 = new HashMap<String, String>();
            hashMap2.put("google","doc.redisfans.com");
            hashMap2.put("yahoo","www.yahoo.com");
            return new Object[][]{
                    // 测试数据 插入多条数据
                    {"hmsetkey", hashMap, "OK"},
                    // 将google的字段覆盖后替换新值
                    {"hmsetkey", hashMap2, "OK"}
            };
        } else if (methodName.equals("Test_Hmget")) {
            return new Object[][]{
                    // 测试数据 删除hashname字段
                    {"hmgetkey", "google"},
                    // 将旧值覆盖,删除多个字段
                    {"hmgetkey", "yahoo", "google"},
                    // 删除不存在的字段
                    {"hmgetkey", "baidu"}
            };
        } else if (methodName.equals("Test_Hsetnx")) {
            byte[] b = {20,40};
            byte[] b1 = {30, 50};
            return new Object[][]{
                    // 测试数据 hsetnxname字段不存在
                    {"hsetnxkey", "hsetnxname",b, 1L},
                    // b1的值不会覆盖b的值
                    {"hsetnxkey", "hsetnxname", b1, 0L}
            };
        }else if (methodName.equals("Test_Hsetnx2")) {
            byte[] hsetnxkey = {10,20,30};
            byte[] hsetnxname = {40,50,60};
            byte[] hsetnxvalue = {70,80,90};
            return new Object[][]{
                    {hsetnxkey,hsetnxname, hsetnxvalue, 1L}
            };
        } else if (methodName.equals("Test_Hdel")) {
            return new Object[][]{
                    // 测试数据 删除hashname字段
                    {"hashkey", "hashname"},
                    // 将旧值覆盖,删除多个字段
                    {"hashkey", "hashname2", "hashname3"},
                    // 删除不存在的字段
                    {"hashkey", "hash_not_exist"}
            };
        } else if (methodName.equals("Test_Hscan")) {
            return new Object[][]{
                    // 测试数据 删除hashname字段
                    {"hashkey", "1"}
            };
        } else if (methodName.equals("Test_Hvals")) {
            return new Object[][]{
                    // 测试数据 key存在
                    {"hmsetkey"},
                    // key不存在
                    {"absencekey"}
            };
        } else {
            return null;
        }
    }
}
