package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestSubPubData {
    // script通用测试数据
    @DataProvider(name = "SubPub_All")
    public static Object[][] SubPub_All(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_subscribeOne")) {

            return new Object[][]{
                    // 测试数据 插入值
                    {"subscribeOne1", 10}
            };
        } else if (methodName.equals("Test_publishOne")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"subscribeOne1", "消息通知......"}
            };
        } else {
            return new Object[][]{{}};
        }
    }
}
