package com.tester.data;


import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * RedisTestScriptCase测试数据
 * 以数据驱动方式引入
 */
public class TestScriptData {
    // script通用测试数据
    @DataProvider(name = "Script_All")
    public static Object[][] Script_All(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_Eval01")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"return {1,2,{3,'Hello World!'}}", "0"}
            };
        } else if (methodName.equals("Test_Eval02")) {
            String[] params = {"foo", "first", "second"};
            return new Object[][]{
                    // 测试数据 插入值
                    {"return {KEYS[1],ARGV[1],ARGV[2]}", 1, params}
            };
        } else if (methodName.equals("Test_Eval03")) {
            List<String> keys = new ArrayList<String>();
            keys.add("foo");
            List<String> argv = new ArrayList<String>();
            argv.add("first");
            argv.add("second");
            return new Object[][]{
                    // 测试数据 插入值
                    {"return {KEYS[1],ARGV[1],ARGV[2]}", keys, argv}
            };
        } else if (methodName.equals("Test_EvalSha01")) {
            return new Object[][]{
                    // 测试数据 插入值
                    {"0"}
            };
        } else if (methodName.equals("Test_EvalSha02")) {
            String[] params = {"foo", "first", "second"};
            return new Object[][]{
                    // 测试数据 插入值
                    {1, params}
            };
        } else if (methodName.equals("Test_EvalSha03")) {
            List<String> keys = new ArrayList<String>();
            keys.add("foo");
            List<String> argv = new ArrayList<String>();
            argv.add("first");
            argv.add("second");
            return new Object[][]{
                    // 测试数据 插入值
                    {keys, argv}
            };
        }else
         {
             return new Object[][]{{}};
        }
    }
}
