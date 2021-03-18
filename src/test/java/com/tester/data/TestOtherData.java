package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestOtherData {
    // streams通用测试数据
    @DataProvider(name = "Other_All")
    public static Object[][] Other_All(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("")) {

            return new Object[][]{
                    // 测试数据 插入值
                    {}
            };
        } else {
            return new Object[][]{{}};
        }
    }
}
