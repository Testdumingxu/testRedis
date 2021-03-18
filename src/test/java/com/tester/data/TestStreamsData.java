package com.tester.data;

import org.testng.annotations.DataProvider;
import redis.clients.jedis.StreamEntryID;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestStreamsData {
    // streams通用测试数据
    @DataProvider(name = "Streams_All")
    public static Object[][] Streams_All(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_xRead")) {

            return new Object[][]{
                    // 测试数据 插入值
                    {10, 1L}
            };
        } else if (methodName.equals("Test_xReadGroup")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {10, 2000}
            };
        } else if (methodName.equals("Test_xRange")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"LOL", new StreamEntryID("0-0"), new StreamEntryID("9999999999999-999999999999999"), 1000}
            };
        } else if (methodName.equals("Test_xRevRange")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"LOL", new StreamEntryID("9999999999999-999999999999999"), new StreamEntryID("0-0"), 1000}
            };
        } else if (methodName.equals("Test_xgroupCreate")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "group-name-fashi"}
            };
        } else if (methodName.equals("Test_xgroupSetID")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "group-name-fashi"}
            };
        } else if (methodName.equals("Test_xgroupDestroy")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "group-name-fashi"}
            };
        } else if (methodName.equals("Test_xgroupDelConsumer")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "group-name-fashi"}
            };
        } else if (methodName.equals("Test_xpending")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "group-name-fashi", 2}
            };
        } else if (methodName.equals("Test_xdel")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe"}
            };
        } else if (methodName.equals("Test_xtrim")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", 1L, true}
            };
        } else if (methodName.equals("Test_xclaim")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"wangzhe", "test-group"}
            };
        } else
         {
             return new Object[][]{{}};
        }
    }
}
