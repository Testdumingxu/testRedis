package com.tester.data;

import org.testng.annotations.DataProvider;
import redis.clients.jedis.ListPosition;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * RedisTestStringCases测试数据
 * 以数据驱动方式引入
 */
public class TestListData {


    // string通用测试数据
    @DataProvider(name = "List_all")
    public static Object[][] List_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_LPush")) {

            return new Object[][]{
                    // 测试数据 插入值
                    {"listkey", "python"},
                    {"listkey", "python"},
                    {"listkey", "java", "go"},
            };
        } else if (methodName.equals("Test_LIndex")) {

            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey2", 0L, "listValue"},
                    {"listkey2", -1L, "listValue3"},
                    {"listkey2", 9L, null},
            };
        } else if (methodName.equals("Test_RPush")) {
            String[] str = {"rPushValue"};
            String[] str2 = {"rPushValue2","rPushValue2"};

            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey3", str},
                    {"listkey3", str},
                    {"listkey3", str2},
            };
        } else if (methodName.equals("Test_RPushx")) {
            String[] str = {"rPushxValue"};
            String[] str2 = {"rPushValueFail"};

            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey3", str},
                    {"listkey_no", str2},
            };
        } else if (methodName.equals("Test_LLen")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey", 4L},
                    {"listnull", 0L},

            };
        } else if (methodName.equals("Test_LPop")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey5", "Strat"},
                    {"listkey_no", null},
            };
        } else if (methodName.equals("Test_LPushx")) {
            return new Object[][]{
                    // 测试数据 lpushkey存在
                    {"lpushkey", 2L, "world"},
                    // listkey_no 不存在
                    {"listkey_no", 0L, "addfaild"},
            };
        } else if (methodName.equals("Test_LRange")) {
            return new Object[][]{
                    // 测试数据 lpushkey存在
                    {"listkey", 0L, 2L}
            };
        } else if (methodName.equals("Test_LRem")) {
            return new Object[][]{
                    {"lremkey", 2L, "morning", 2L},
                    {"lremkey", -1L, "morning", 1L},
                    {"lremkey", 0L, "hello", 2L}
            };
        } else if (methodName.equals("Test_LTrim")) {
            return new Object[][]{
                    {"ltrimkey", 1L, -1L, "OK"}
            };
        } else if (methodName.equals("Test_LSet")) {
            return new Object[][]{
                    {"lsetkey", 0L, "play game", "OK"}
            };
        } else if (methodName.equals("Test_BlPop")) {
            return new Object[][]{
                    {30, "blpopkey"}
            };
        } else if (methodName.equals("Test_RPop")) {
            return new Object[][]{
                    {"lpopkey", "bar"},
                    {"lpopkey_no", null}
            };
        } else if (methodName.equals("Test_LrangeBytes")) {
            return new Object[][]{
                    {"LrangeBytes", 1L, 2L}
            };
        } else if (methodName.equals("Test_BrPop")) {
            return new Object[][]{
                    {30,"brpopkey"}
            };
        } else if (methodName.equals("Test_LInsert")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"listkey4", ListPosition.BEFORE, "World", "There", 3L},
                    {"listkey4", ListPosition.AFTER, "Hello", "Hi", 4L},
                    {"listkey4", ListPosition.AFTER, "Value_not_exist", "python", -1L},
                    {"listkey_no", ListPosition.BEFORE, "Value_not_exist", "python", 0L},
            };
        } else if (methodName.equals("Test_lPos01")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"mylist", "c"}
            };
        } else if (methodName.equals("Test_lPos02")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"mylist", "c"}
            };
        } else if (methodName.equals("Test_lPos03")) {
            return new Object[][]{
                    // 测试数据 返回索引处的值
                    {"mylist", "c"}
            };
        } else {
            return new Object[][]{{}};
        }
    }
}
