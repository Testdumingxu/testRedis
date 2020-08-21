package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * RedisTestStringCases测试数据
 * 以数据驱动方式引入
 */
public class TestStringData {


    // string通用测试数据
    @DataProvider(name = "String_all")
    public static Object[][] String_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_Set") || methodName.equals("Test_Get")
                || methodName.equals("Test_Append")) {
            return new Object[][]{
                    // 测试数据 key值为字符串
                    {"stringkey", "stringvalue"}
            };
        } else if (methodName.equals("Test_BitCount") || methodName.equals("Test_BitCount2")
                || methodName.equals("Test_Decr") || methodName.equals("Test_DecrBy")
                || methodName.equals("Test_Incr")) {
            return new Object[][]{
                    // 测试数据 testdecr：key值为数值  test_decr：key值为非数值
                    {"testdecr"}, {"test_decr"}
            };
        } else if (methodName.equals("Test_IncrBy")) {
          return new Object[][] {
                  // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                  {"stringcount"},{"stringnumber"}
          };
        }else if (methodName.equals("Test_IncrByFloat")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringcount", 1.1}
            };
        } else if (methodName.equals("Test_GetBit")) {
            return new Object[][]{
                    // 测试数据 stringkey: 字符串, test_decr: 不存在的key
                    {"stringkey", true}, {"test_decr", false} // stringkey：字符串
            };
        } else if (methodName.equals("Test_Psetex")) {
            return new Object[][]{
                    // 测试数据 stringkey：字符串
                    {"stringkey3", 1000L, "hello"}
            };
        } else if (methodName.equals("Test_GetRange")) {
            return new Object[][]{
                    // 测试数据 stringkey：字符串
                    {"stringkey"}
            };
        } else if (methodName.equals("Test_GetSet")) {
            return new Object[][]{
                    // 测试数据 stringkey2: 字符串
                    {"stringkey2", "updatevalue"}
            };
        } else if (methodName.equals("Test_Setbit")) {
            return new Object[][]{
                    // 测试数据 stringsetbit: 字符串,01100001: 二进制, "1"：偏移量
                    {"stringsetbit", 01100001L, "1"}
            };
        }else if (methodName.equals("Test_Setnx")) {
            return new Object[][]{
                    // 测试数据
                    // stringkey4: 不存在
                    {"stringkey4", "stringvalue4"},
                    {"stringkey4", "stringvalue4"}
            };
        } else if (methodName.equals("Test_Setex")) {
            return new Object[][]{
                    // 测试数据 stringsetbit: 字符串,01100001: 二进制, "1"：偏移量
                    {"stringcache_user", 60, "10086"}
            };
        } else if (methodName.equals("Test_SetRange")) {
            return new Object[][]{
                    // 测试数据 stringSetRange: 字符串,6: 偏移量, "Redis"：字符串
                    {"stringSetRange", 6L, "Redis"}
            };
        } else if (methodName.equals("Test_StrLen")) {
            return new Object[][]{
                    // 测试数据 stringkey: 存在; stringstrlen: 不存在
                    {"stringkey"},
                    {"stringstrlen"}
            };
        } else {
            return null;
        }
    }
}
//
//    // string通用测试数据
//    @DataProvider(name = "String_getrange")
//    public static Object[][] String_getrange() {
//        return new Object[][]{
//                {"stringkey"} // key存在
//        };
//    }
//
//    @DataProvider(name = "String_GetSet")
//    public static Object[][] String_GetSet() {
//        return new Object[][]{
//                {"stringkey2", "updatevalue"}, // key存在
//        };
//    }
//
//    //decr测试数据
//    @DataProvider(name = "String_key")
//    public static Object[][] String_key() {
//        return new Object[][]{
//                {"testdecr"}, // key存在
//                {"test_decr"},// key不存在
////                {"string_key"}, //key存在但不是数值类型的
//        };
//    }
//
//    @DataProvider(name = "String_getbit")
//    public static Object[][] String_getbit() {
//        return new Object[][]{
//                {"stringkey", true}, // key存在
//                {"test_decr", false},// key不存在
//        };
//    }
//
//    // getset测试数据
//    @DataProvider(name = "String_getset")
//    public static Object[][] String_getset() {
//        return new Object[][]{
//                {"testdecr", ""}, // key存在
//                {"test_decr", 0},// key不存在
//        };
//    }
//}
