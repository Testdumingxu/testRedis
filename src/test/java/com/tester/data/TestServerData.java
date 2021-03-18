package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestServerData {
    // server通用测试数据
    @DataProvider(name = "Server_All")
    public static Object[][] Server_All(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_memoryUsage01")) {

            return new Object[][]{
                    // 测试数据 插入值
                    {"foo"}
            };
        } else if (methodName.equals("Test_memoryUsage02")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {10}
            };
        } else {
            return new Object[][]{{}};
        }
    }
}
