package com.tester.data;

import org.testng.annotations.DataProvider;
import redis.clients.jedis.SortingParams;

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
        } else if (methodName.equals("Test_Increx")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"testdecr2", 10}
            };
        } else if (methodName.equals("Test_Substr")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"testdecr2", 0, 1}
            };
        } else if (methodName.equals("Test_IncrBy")) {
          return new Object[][] {
                  // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                  {"stringcount"},{"stringnumber"}
          };
        } else if (methodName.equals("Test_setAndCompress")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompress","stringsetAndCompressValue"}
            };
        } else if (methodName.equals("getAndUncompress")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompress", "stringsetAndCompressValue"}
            };
        } else if (methodName.equals("Test_Exists")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompress"}
            };
        } else if (methodName.equals("Test_Persist")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringmykey", null}
            };
        } else if (methodName.equals("Test_Expire")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"keyexpire", 10, 1L}
            };
        } else if (methodName.equals("Test_Pexpire")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"keyPexpire", 10000L, 1L}
            };
        } else if (methodName.equals("Test_ExpireAt")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"keyExpireAt", System.currentTimeMillis()/ 1000L, 1L}
            };
        } else if (methodName.equals("Test_PexpireAt")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"keyPexpireAt", System.currentTimeMillis()/ 1000L, 1L}
            };
        } else if (methodName.equals("Test_Type")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompress", "string"}
            };
        } else if (methodName.equals("getAndUncompressUtf8")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompressUtf8", "stringsetAndCompressUtf8Value"}
            };
        } else if (methodName.equals("Test_ttl")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"keyexpire"}
            };
        } else if (methodName.equals("Test_setAndCompressUtf8")) {
            return new Object[][] {
                    // 测试数据 stringcount(存在):key值为数值, stringnumber(不存在):key值为数值
                    {"stringsetAndCompressUtf8","stringsetAndCompressUtf8Value"}
            };
        } else if (methodName.equals("Test_IncrByFloat")) {
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
                    {"stringsetbit", 01100001L, "0"}
            };
        }else if (methodName.equals("Test_Setnx")) {
            return new Object[][]{
                    // 测试数据
                    // stringkey4: 不存在
                    {"stringkey4", "stringvalue4"},
                    {"stringkey4", "stringvalue4"}
            };
        } else if (methodName.equals("Test_Setex")) {
            byte[] bytes = new byte[3];
            bytes[0] = 1;
            bytes[1] = 0;
            bytes[2] = 0;

            return new Object[][]{
                    // 测试数据 stringsetbit: 字符串,01100001: 二进制, "1"：偏移量
                    {"stringcache_user", 60, bytes, "OK"}
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
        } else if (methodName.equals("Test_SetNxex")) {
            return new Object[][]{
                    // 测试数据 stringkey: 存在; stringstrlen: 不存在
                    {"stringSetNxex",10,"stringSetNxex"}
            };
        } else if (methodName.equals("Test_Sort")) {
            return new Object[][]{
                    {"sortkey3"}
            };
        } else if (methodName.equals("Test_Sort2")) {
            return new Object[][]{
                    {"sortkey3"}
            };
        } else if (methodName.equals("Test_Pfadd")) {
            String[] str = new String[] {"1","2"};
            return new Object[][]{
                    {"Hello Gcache", str}
            };
        } else if (methodName.equals("Test_Echo")) {
            return new Object[][]{
                    {"Hello Gcache"}
            };
        } else if (methodName.equals("Test_pfcount")) {
            return new Object[][]{
                    {"myphone"}
            };
        } else if (methodName.equals("Test_Pttl")) {
            return new Object[][]{
                    {"getkey22", -2L}
            };
        } else if (methodName.equals("Test_GetBytes")) {
            return new Object[][]{
                    {"getbyteskey"}
            };
        } else if (methodName.equals("Test_Setex2")) {
            return new Object[][]{
                    {"Setexkey", 10, "Setexvalue", "OK"}
            };
        } else if (methodName.equals("Test_Setex3")) {
            byte[] var = {10};
            byte[] var2 = {20};
            return new Object[][]{
                    // 测试数据 stringsetbit: 字符串,01100001: 二进制, "1"：偏移量
                    {var, 60, var2}
            };
        } else if (methodName.equals("Test_bitfieldReadonly")) {
            String[] a = {"get","i8","0"};
            return new Object[][]{
                    {"test", a}
            };
        } else {
            return new Object[][]{{}};
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
