package com.tester.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redis.Gcache;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.GeoRadiusStoreParam;

@Test(groups = "geo")
public class RedisTestGeoCases {
    private Gcache bean;

    /**
     * 获取Bean
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("--------开始执行geo用例--------");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        bean = (Gcache) app.getBean("testGcache");
        bean.set("sortkey", "apple");
        bean.pfadd("myphone","hll","foo","bar","zap");
        bean.pfadd("myphone","zap","zap","zap");
        bean.pfadd("myphone","foo","bar");
        bean.lpush("sortkey2","apple","banana","cat");

    }

    @AfterClass
    public void aftersuite() {

        System.out.println("--------执行结束geo用例--------");
        System.out.println("-----开始清除key---------------");

    }

    /**
     * 暂时无法使用转换类型失败待与开发确认
     * @param key
     * @param member
     * @param radius
     */
//    @Test(description = "Param:final String key, final String member, final double radius, final GeoUnit unit,\n" +
//            "final GeoRadiusParam param, final GeoRadiusStoreParam storeParam;" +
//            "Case:georadiusByMemberStore正常调用;" +
//            "Return:返回 OK",
//            dataProvider = "Geo_all",
//            dataProviderClass = com.tester.data.TestGeoData.class)
//    public void Test_georadiusByMemberStore(final String key, final String member, final double radius) {
//        Long g1 = bean.geoadd(key, 13.583333, 37.316667, "{test}Agrigento");
//        System.out.println(g1);
//        Long g2 = bean.geoadd(key, 13.361389, 38.115556, "{test}Palermo");
//        System.out.println(g2);
//        GeoRadiusParam geoRadiusParam = GeoRadiusParam.geoRadiusParam().withCoord();
//
//        bean.georadiusByMemberStore(key, member, radius, GeoUnit.KM, geoRadiusParam, GeoRadiusStoreParam.geoRadiusStoreParam().store(key));
//
//        Assert.assertEquals("OK", result);
//        System.out.println("---------------------");
//        System.out.println(result);
//        System.out.println("---------------------");
//        Reporter.log("预期结果: OK | " + "实际结果: " + result);
//    }

//    @Test(description = "final String key, final double longitude, final double latitude,\n" +
//            "final double radius, final GeoUnit unit, final GeoRadiusParam param, final GeoRadiusStoreParam storeParam;" +
//            "Case:georadiusStore正常调用;" +
//            "Return:返回 long",
//            dataProvider = "Geo_all",
//            dataProviderClass = com.tester.data.TestGeoData.class)
//    public void Test_georadiusStore(final String key, final double longitude, final double latitude,
//                                    final double radius) {
//        Long g1 = bean.geoadd(key, 13.361389, 38.115556, "{test}Palermo");
//        System.out.println(g1);
//        Long g2 = bean.geoadd(key, 15.087269, 37.502669, "{test}Catania");
//        System.out.println(g2);
//        GeoRadiusParam geoRadiusParam = GeoRadiusParam.geoRadiusParam().withCoord();
//
//        Long result = bean.georadiusStore(key, longitude, latitude, radius, GeoUnit.KM, geoRadiusParam, GeoRadiusStoreParam.geoRadiusStoreParam().store(key));
//
//        Assert.assertEquals("OK", result);
//        System.out.println("---------------------");
//        System.out.println(result);
//        System.out.println("---------------------");
//        Reporter.log("预期结果: OK | " + "实际结果: " + result);
//    }
}
