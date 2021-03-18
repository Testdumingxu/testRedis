package com.tester.testcase;

import com.tester.util.RedisSubscriber;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.GcacheClient;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;

import java.util.*;

@Test(groups = "stream")
public class RedisTestStreamsCases {
    private GcacheClient bean;

    private RedisSubscriber redisSubscriber;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行stream用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (GcacheClient) app.getBean("testGcache");
        redisSubscriber = new RedisSubscriber();
    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束stream用例--------");
        System.out.println("-----开始清除stream缓存---------------");

    }

    @Test(description = "final String key;" +
            "Case:执行memoryUsage命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xRead(final int count, final long block) {
        StreamEntryID start = new StreamEntryID("0-0");
        StreamEntryID end = new StreamEntryID("9999999999999-999999999999999");

//        StreamEntryID streamEntryID2 = new StreamEntryID("0-1000");
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("foo", "bar");
//        map.put("foo2", "bar2");
//        StreamEntryID entryID = bean.xadd("somestream",StreamEntryID.NEW_ENTRY, map);

//        List<StreamEntry> somestream = bean.xrange("somestream", start, end, 1000);
//        System.out.println("------------somestream:" + somestream);

        HashMap<String, StreamEntryID> st = new HashMap<String, StreamEntryID>();
        st.put("somestream", start); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();

        List<Map.Entry<String, List<StreamEntry>>> result = bean.xread(count, block, a);
//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
//        bean.xdel("foo", streamEntryID);
    }

    @Test(description = "String groupname, String consumer, int count, long block, boolean noAck, Map.Entry<String, StreamEntryID> streams;" +
            "Case:执行xReadGroup命令;" +
            "Return:返回在内存中占用的字节数",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xReadGroup(int count, long block) {

        Map<String, String> map = new HashMap<>();
        map.put("hero", "VN");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "维鲁斯");
        System.out.println("map2:" + map2.size());

        StreamEntryID streamEntryID2 = bean.xadd("wangzhe", StreamEntryID.NEW_ENTRY, map2);
        System.out.println("streamEntryId2:" + streamEntryID2);

        StreamEntryID streamEntryID = bean.xadd("wangzhe", StreamEntryID.NEW_ENTRY, map);
        System.out.println("streamEntryId:" + streamEntryID);

//        String flag = bean.xgroupCreate("wangzhe", "fashi", StreamEntryID.LAST_ENTRY, true);
//        System.out.println("flag: " + flag);

        HashMap<String, StreamEntryID> st = new HashMap<>();
        st.put("wangzhe", StreamEntryID.UNRECEIVED_ENTRY); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();

        List<Map.Entry<String, List<StreamEntry>>> result = bean.xreadGroup("fashi", "me", count, block, true, a);
//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("wangzhe", streamEntryID);
        bean.xdel("wangzhe", streamEntryID2);

    }

    @Test(description = "final String key, final StreamEntryID id, final Map<String, String> hash;" +
            "Case:执行xAdd命令;" +
            "Return:返回StreamEntryID",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xAdd01() {
        Map<String, String> map = new HashMap<>();
        map.put("adc", "鲁班");
        map.put("打野", "盲僧");

        StreamEntryID result = bean.xadd("wangzhe", StreamEntryID.NEW_ENTRY, map);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("wangzhe", result);
    }

    @Test(description = "final String key, final StreamEntryID id, final Map<String, String> hash, final long maxLen, final boolean approximateLength;" +
            "Case:执行xAdd命令;" +
            "Return:返回StreamEntryID",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xAdd02() {
        Map<String, String> map = new HashMap<>();
        map.put("上单", "瑞文");
        map.put("中单", "辛德拉");

        StreamEntryID result = bean.xadd("LOL", StreamEntryID.NEW_ENTRY, map, 10L, true);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("LOL", result);
    }

    @Test(description = "final String key;" +
            "Case:执行xLen命令;" +
            "Return:返回流中的条目数",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xLen() {
        Map<String, String> map = new HashMap<>();
        map.put("上单", "瑞文");
        map.put("中单", "辛德拉");
        StreamEntryID streamEntryID = bean.xadd("LOL", StreamEntryID.NEW_ENTRY, map, 10L, false);
        Long result = bean.xlen("LOL");

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("LOL", streamEntryID);
    }

    @Test(description = "final String key, final StreamEntryID start, final StreamEntryID end, final int count;" +
            "Case:执行xRange命令;" +
            "Return:返回流中的条目数",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xRange(final String key, final StreamEntryID start, final StreamEntryID end, final int count) {
        Map<String, String> map = new HashMap<>();
        map.put("上单", "瑞文");
        map.put("中单", "辛德拉");
        StreamEntryID streamEntryID = bean.xadd("LOL", StreamEntryID.NEW_ENTRY, map, 10L, false);

        List<StreamEntry> result = bean.xrange(key, start, end, count);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("LOL", streamEntryID);
    }

    /**
     * 参数传参顺序目前有问题需要将end和start参数调换一下
     *
     * @param key
     * @param end
     * @param start
     * @param count
     */
    @Test(description = "final String key, final StreamEntryID end, final StreamEntryID start, final int count;" +
            "Case:执行xRevRange命令;" +
            "Return:返回流中的所有数据",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xRevRange(final String key, final StreamEntryID end, final StreamEntryID start, final int count) {
        Map<String, String> map = new HashMap<>();
        map.put("上单2", "瑞文2");
        map.put("中单2", "辛德拉2");
        StreamEntryID streamEntryID = bean.xadd("LOL", StreamEntryID.NEW_ENTRY, map, 10L, false);

        List<StreamEntry> result = bean.xrevrange(key, start, end, count);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("LOL", streamEntryID);
    }

    @Test(description = "final String key, final String group, final StreamEntryID... ids;" +
            "Case:执行xACK命令;" +
            "Return:返回成功确认的消息数",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xACK() {
        Map<String, String> map = new HashMap<>();
        map.put("hero", "盲僧");
//
        StreamEntryID streamEntryID2 = bean.xadd("wangzhe", StreamEntryID.NEW_ENTRY, map);
        System.out.println("streamEntryId2:" + streamEntryID2);

//        String flag = bean.xgroupCreate("wangzhe", "daye", StreamEntryID.LAST_ENTRY, true);
//        System.out.println("flag: " + flag);
        HashMap<String, StreamEntryID> st = new HashMap<String, StreamEntryID>();
        st.put("wangzhe", StreamEntryID.UNRECEIVED_ENTRY); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();

        StreamEntryID sid = new StreamEntryID();
        List<Map.Entry<String, List<StreamEntry>>> entries = bean.xreadGroup("daye", "me", 10, 1000L, false, a);
        for (Map.Entry<String, List<StreamEntry>> entry : entries) {
            List<StreamEntry> value = entry.getValue();
            for (StreamEntry streamEntry : value) {
                sid = streamEntry.getID();
            }
        }
//        System.out.println("xreadGroup:" + entries);
        Long result = bean.xack("wangzhe", "daye", sid);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除stream流
        bean.xdel("wangzhe", streamEntryID2);
    }

    @Test(description = "final String key, final String groupname, final StreamEntryID id, final boolean makeStream;" +
            "Case:执行xgroupCreate命令;" +
            "Return:返回新消费者组",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xgroupCreate(final String key, final String groupname) {
        // 创建消费者组
        String result = bean.xgroupCreate(key, groupname, StreamEntryID.LAST_ENTRY, false);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除消费者组
        Long aLong = bean.xgroupDestroy(key, groupname);
//        System.out.println(aLong);
    }

    @Test(description = "final String key, final String groupname, final StreamEntryID id;" +
            "Case:执行xgroupSetID命令;" +
            "Return:返回新消费者组",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xgroupSetID(final String key, final String groupname) {
        // 创建消费者组
        bean.xgroupCreate(key, groupname, StreamEntryID.LAST_ENTRY, false);
        String result = bean.xgroupSetID(key, groupname, StreamEntryID.LAST_ENTRY);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        // 清除消费者组
        bean.xgroupDestroy(key, groupname);
//        System.out.println(aLong);
    }

    @Test(description = "final String key, final String groupname;" +
            "Case:执行xgroupDestroy命令;" +
            "Return:删除新消费者组",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xgroupDestroy(final String key, final String groupname) {
        // 创建消费者组
        bean.xgroupCreate(key, groupname, StreamEntryID.LAST_ENTRY, false);
        // 删除消费者组
        Long result = bean.xgroupDestroy(key, groupname);

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Assert.assertEquals(result, "OK");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String key, final String groupname, final String consumername;" +
            "Case:执行xgroupDelConsumer命令;" +
            "Return:删除新消费者组",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xgroupDelConsumer(final String key, final String groupname) {
        // 创建消费者组
        bean.xgroupCreate(key, groupname, StreamEntryID.LAST_ENTRY, false);

        Map<String, String> map = new HashMap<>();
        map.put("hero", "小法师");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "炸弹人");
//        System.out.println("map2:" + map2.size());

        StreamEntryID streamEntryID2 = bean.xadd(key, StreamEntryID.NEW_ENTRY, map2);
//        System.out.println("streamEntryId2:" + streamEntryID2);

        StreamEntryID streamEntryID = bean.xadd(key, StreamEntryID.NEW_ENTRY, map);
//        System.out.println("streamEntryId:" + streamEntryID);

//        String flag = bean.xgroupCreate("wangzhe", "fashi", StreamEntryID.LAST_ENTRY, true);
//        System.out.println("flag: " + flag);

        HashMap<String, StreamEntryID> st = new HashMap<>();
        st.put(key, StreamEntryID.UNRECEIVED_ENTRY); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();

        List<Map.Entry<String, List<StreamEntry>>> list1 = bean.xreadGroup(groupname, "consumer1", 1, 2000L, true, a);
        List<Map.Entry<String, List<StreamEntry>>> list2 = bean.xreadGroup(groupname, "consumer2", 1, 2000L, true, a);
        System.out.println(list1);
        System.out.println(list2);

        // 删除消费者
        Long result = bean.xgroupDelConsumer(key, groupname, "consumer1");
        // 删除消费者组
        bean.xgroupDestroy(key, groupname);

        System.out.println("-----------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Assert.assertNotNull(result);
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
    }

    @Test(description = "final String key, final String groupname, final StreamEntryID start, final StreamEntryID end, final int count,\n" +
            "final String consumername;" +
            "Case:执行xpending命令;" +
            "Return:返回待处理消息列表中有每一条消息的详细信息",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xpending(final String key, final String groupname, final int count) {
        // 创建消费者组
        bean.xgroupCreate(key, groupname, StreamEntryID.LAST_ENTRY, true);

        Map<String, String> map = new HashMap<>();
        map.put("hero", "小法师");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "炸弹人");
//        System.out.println("map2:" + map2.size());

        StreamEntryID streamEntryID2 = bean.xadd(key, StreamEntryID.NEW_ENTRY, map2);
//        System.out.println("streamEntryId2:" + streamEntryID2);

        StreamEntryID streamEntryID = bean.xadd(key, StreamEntryID.NEW_ENTRY, map);
        HashMap<String, StreamEntryID> st = new HashMap<>();
        st.put(key, StreamEntryID.UNRECEIVED_ENTRY); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();
        List<Map.Entry<String, List<StreamEntry>>> consumer1 = bean.xreadGroup(groupname, "consumer1", 10, 2000L, false, a);
        System.out.println(consumer1);
        List<StreamPendingEntry> result = bean.xpending(key, groupname, new StreamEntryID("0-0"), new StreamEntryID("9999999999999-0"), 10, "consumer1");

//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        Assert.assertNotNull(result);
        // 清除消费者组
        bean.xgroupDestroy(key, groupname);
//        System.out.println(aLong);
    }

    @Test(description = "final String key, final StreamEntryID... ids;" +
            "Case:执行xdel命令;" +
            "Return:删除多个条目",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xdel(final String key) {

        Map<String, String> map = new HashMap<>();
        map.put("hero", "小法师");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "炸弹人");
//        System.out.println("map2:" + map2.size());

        StreamEntryID streamEntryID2 = bean.xadd(key, StreamEntryID.NEW_ENTRY, map2);
//        System.out.println("streamEntryId2:" + streamEntryID2);

        StreamEntryID streamEntryID = bean.xadd(key, StreamEntryID.NEW_ENTRY, map);

        StreamEntryID[] sid = {streamEntryID, streamEntryID2};

        Long result = bean.xdel(key, sid);

        System.out.println("-----------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        Assert.assertNotNull(result);

//        System.out.println(aLong);
    }

    @Test(description = "final String key, final StreamEntryID... ids;" +
            "Case:执行xtrim命令;" +
            "Return:删除多个条目",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xtrim(final String key, final long maxLen, final boolean approximateLength) {
        Map<String, String> map = new HashMap<>();
        map.put("hero", "小法师");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "炸弹人");
//        System.out.println("map2:" + map2.size());
        StreamEntryID id = bean.xadd(key, StreamEntryID.NEW_ENTRY, map, 10L, approximateLength);
        StreamEntryID id2 = bean.xadd(key, StreamEntryID.NEW_ENTRY, map, 10L, approximateLength);
//        System.out.println("streamEntryId2:" + streamEntryID2);
        StreamEntryID streamEntryID = bean.xadd(key, StreamEntryID.NEW_ENTRY, map);
        List<StreamEntry> list = bean.xrevrange(key, new StreamEntryID("0-0"), new StreamEntryID("9999999999999-0"), 2);
        System.out.println(list);

        Long result = bean.xtrim(key, maxLen, approximateLength);

        System.out.println("-----------------------------");
        System.out.println("result: " + result);
        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        Assert.assertNotNull(result);

//        System.out.println(aLong);
    }

    @Test(description = "final String key, final String group, final String consumername, final long minIdleTime, final long newIdleTime,\n" +
            "final int retries, final boolean force, final StreamEntryID... ids;" +
            "Case:执行xclaim命令;" +
            "Return:删除多个条目",
            dataProvider = "Streams_All",
            dataProviderClass = com.tester.data.TestStreamsData.class
    )
    public void Test_xclaim(final String key, final String group) {
        // 读取消息
        String xgroupCreate = bean.xgroupCreate(key, group, StreamEntryID.LAST_ENTRY, true);
//        System.out.println("xgroupCreate:"+xgroupCreate);
        HashMap<String, StreamEntryID> st = new HashMap<>();
        st.put(key, StreamEntryID.UNRECEIVED_ENTRY); //new StreamEntryID("")
        Map.Entry<String, StreamEntryID> a = st.entrySet().stream().findFirst().get();

        Map<String, String> map = new HashMap<>();
        map.put("hero", "小法师");

        Map<String, String> map2 = new HashMap<>();
        map2.put("hero", "炸弹人");
//        System.out.println("map2:" + map2.size());
        StreamEntryID id = bean.xadd(key, StreamEntryID.NEW_ENTRY, map, 10L, true);
        StreamEntryID id2 = bean.xadd(key, StreamEntryID.NEW_ENTRY, map, 10L, true);
//        System.out.println("streamEntryId2:" + streamEntryID2);
        StreamEntryID streamEntryID = bean.xadd(key, StreamEntryID.NEW_ENTRY, map);

        List<Map.Entry<String, List<StreamEntry>>> group1 = bean.xreadGroup(group, "me", 2, 2000L, false, a);

        StreamEntryID[] sid = {id, id2};
        List<StreamEntry> result = bean.xclaim(key, group, "me", 1L, 1L, 3, true, sid);
//
        bean.xgroupDestroy(key, group);
//        System.out.println("-----------------------------");
//        System.out.println("result: " + result);
//        System.out.println("-----------------------------");
        Reporter.log("预期结果: 不能为空 | " + "实际结果: " + result);
        Assert.assertNotNull(result);

//        System.out.println(aLong);
    }
}
