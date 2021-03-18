package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;

import java.util.List;

/**
 * redis脚本命令
 * eval 可以执行lua脚本命令
 */
@Test(groups = "script")
public class RedisTestScriptCase {
    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行script用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");


    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束script用例--------");
        System.out.println("-----开始清除script缓存---------------");
    }

    @Test(description = "String var1, String var2;" +
            "Case:执行eval01脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_Eval01(String var1, String sampleKey) {
        Object result = bean.eval(var1, sampleKey);
        Reporter.log("执行的脚本：" + var1 + ";" + "sampleKey：" + sampleKey);
        Assert.assertNotNull(result);
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "String var1, String var2;" +
            "Case:执行eval02脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_Eval02(String script, int keyCount, String... params) {
        Object result = bean.eval(script, keyCount, params);
        Reporter.log("执行的脚本：" + script + ";" +"key数量:" + keyCount + ";params：" + params);
        Assert.assertNotNull(result);
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "String var1, String var2;" +
            "Case:执行eval脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_Eval03(String script, List<String> keys, List<String> argv) {
        Object result = bean.eval(script, keys, argv);
        Reporter.log("执行的脚本：" + script + ";" + "sampleKey：" + keys + "argv: " + argv);
        Assert.assertNotNull(result);
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        // flush清空指定缓存
        bean.scriptFlush("foo");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String sha1, final String sampleKey;" +
            "Case:执行evalsha01脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_EvalSha01(String sha1) {

        String scriptLoad = bean.scriptLoad("return 'hello moto'", "0");
//        System.out.println("---------------------");
//        System.out.println("scriptload: " + scriptLoad);
//        System.out.println("---------------------");
        Object result = bean.evalsha(scriptLoad, sha1);
//        Reporter.log("执行的脚本：" + sha1 + ";" + "sampleKey：" + sampleKey);
//        Assert.assertNotNull(result);
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String sha1, final int keyCount, final String... params;" +
            "Case:执行执行evalsha02脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_EvalSha02(final int keyCount, final String... params) {

        String scriptLoad = bean.scriptLoad("return {KEYS[1],ARGV[1],ARGV[2]}", "0");
        Object result = bean.evalsha(scriptLoad, keyCount, params);
        Reporter.log("sha1：" + scriptLoad + ";" + "keyCount：" + keyCount + "params: " + params);
//        Assert.assertNotNull(result);
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String sha1, final List<String> keys, final List<String> args;" +
            "Case:执行evalsha03脚本;" +
            "Return:返回 Object",
            dataProvider = "Script_All",
            dataProviderClass = com.tester.data.TestScriptData.class)
    public void Test_EvalSha03(final List<String> keys, final List<String> args) {
        String scriptLoad = bean.scriptLoad("return {KEYS[1],ARGV[1],ARGV[2]}", "0");
        Object result = bean.evalsha(scriptLoad, keys, args);
        Reporter.log("执行的脚本：" + scriptLoad + ";" +"key数量:" + keys + ";params：" + args);
        Assert.assertNotNull(result);
        System.out.println("---------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "String sha1, String sampleKey;" +
            "Case:执行scriptExists;" +
            "Return:返回 Boolean"
            )
    public void Test_scriptExists01() {
        Object eval = bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");
        Boolean result = bean.scriptExists(eval.toString(), "foo");

        Reporter.log("执行的脚本：" + result + ";");
        Assert.assertTrue(result);

        bean.scriptFlush("foo");
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);

    }

    @Test(description = "String sampleKey, String... sha1;" +
            "Case:执行scriptExists;" +
            "Return:返回 List<Boolean>"
    )
    public void Test_scriptExists02() {
        Object eval1 = bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");
        Object eval2 = bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");
        String[] sha1 = {eval1.toString(), eval2.toString()};
        List<Boolean> result = bean.scriptExists("foo", sha1);

        Assert.assertNotNull(result);

        bean.scriptFlush("foo");
        System.out.println("---------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);

    }

    @Test(description = "String sampleKey, String... sha1;" +
            "Case: 执行scriptLoad;" +
            "Return:返回 Object"
    )
    public void Test_scriptLoad() {
        Object sha1 = bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");

        Assert.assertNotNull(sha1);

        bean.scriptFlush("foo");
//        System.out.println("---------------------------");
//        System.out.println("result: " + sha1);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + sha1);

    }

    @Test(description = "String sampleKey, String... sha1;" +
            "Case: 执行scriptFlush;" +
            "Return:返回 Object"
    )
    public void Test_scriptFlush() {
        Object sha1 = bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");
        String result = bean.scriptFlush("foo");

        Assert.assertNotNull(sha1);

//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);

    }

    @Test(description = "String sampleKey, String... sha1;" +
            "Case: 执行scriptKill;" +
            "Return:返回 Object"
    )
    public void Test_scriptKill() {
        bean.scriptLoad("return redis.call('set',KEYS[1],'bar')", "foo");
        String result = bean.scriptKill("foo");

        Assert.assertNull(result);

        bean.scriptFlush("foo");
        System.out.println("-----------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);

    }


    /**
     * 发布消息
     * @param channel
     * @param message
     */
//    @Test(description = "final String channel, final String message;" +
//            "Case:执行subscribeOne脚本;" +
//            "Return:返回 Object",
//
//            dataProvider = "SubPub_All",
//            dataProviderClass = com.tester.data.TestSubPubData.class)
//    public void Test_publishOne(final String channel, final String message) {
//        System.out.println("-----------开始发布-----------");
//        // 发送消息
//        bean.publishOne(channel, message);
//
//        Long result = bean.publishOne(channel, "紧急通知 ...");
//        System.out.println("消息发送完毕.....");
//
//        Assert.assertNotNull(result);
//
//        System.out.println("---------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
//        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
//    }

}
