package com.tester.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * RedisTestStringCases测试数据
 * 以数据驱动方式引入
 */
public class TestSetData {


    // string通用测试数据
    @DataProvider(name = "Set_all")
    public static Object[][] Set_all(Method method) {
        String methodName = method.getName();
        // 如果有这个方法则返回对应的数据
        if (methodName.equals("Test_Sadd")) {
            return new Object[][]{
                    // 测试数据 key值为字符串
                    {"setkey", "setvalue", "setvalue2", "setvalue3", "setvalue4"},
                    // 重复添加元素
                    {"setkey", "setvalue", "setvalue2", "setvalue3", "setvalue4"}
            };
        } else if (methodName.equals("Test_Spop")) {
            return new Object[][]{
                    {"setkey"} // 测试数据 随机移除setkey中的一个元素
            };
        }else if (methodName.equals("Test_Spop2")) {
            return new Object[][]{
                    {"setkey", 2L} // 测试数据 setkey: 随机移除setkey中的2个元素
            };
        } else if (methodName.equals("Test_SrandMember")) {
            return new Object[][]{ {"setkey"} }; // 测试数据 随机返回setkey中的元素值
        } else if (methodName.equals("Test_Srem")) {
            return new Object[][]{
                    // 测试数据 移除set_sremtest下面的srem1
                    {"set_sremtest_key", "srem1"},
                    // 移除多个元素 移除set_sremtest下的srem2、srem3
                    {"set_sremtest_key", "srem2", "srem3"}
            };
        } else if (methodName.equals("Test_SisMember")) {
            return new Object[][]{
                    // 测试数据 setkey:存在|setvalue:存在|断言:true
                    {"setkey", "setvalue", true},
                    // 测试数据 setkey:存在|setnull:不存在|断言:false
                    {"setkey", "setnull", false}
            };
        } else if (methodName.equals("Test_Scard")) {
            return new Object[][]{
                    // 测试数据 setkey：key存在  set_null：key不存在
                    {"setkey"}, {"set_null"}
            };
        }else if (methodName.equals("Test_Sscan")) {
            return new Object[][]{ {"setkey"} }; // 测试数据 随机移除setkey中的一个元素
        } else {
            return null;
        }
    }
}
