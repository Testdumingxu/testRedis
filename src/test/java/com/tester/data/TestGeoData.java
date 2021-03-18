package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestGeoData {

    // string通用测试数据
    @DataProvider(name = "Geo_all")
    public static Object[][] String_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_georadiusByMemberStore")) {
            return new Object[][]{
                    // 测试数据 key值为字符串
                    {"{test}Sicily","{test}Agrigento",100.0}
            };
        } if (methodName.equals("Test_georadiusStore")) {
            return new Object[][]{
                    // 测试数据 key值为字符串
                    {"{test}Sicily",15.0, 37.0, 200.0}
            };
        } else {
            return new Object[][]{{}};
        }
    }
}
