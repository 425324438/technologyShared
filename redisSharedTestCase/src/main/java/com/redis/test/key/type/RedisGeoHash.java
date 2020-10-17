package com.redis.test.key.type;

import com.alibaba.fastjson.JSON;
import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/6 22:02
 * @Description:
 */
public class RedisGeoHash {
    static String key = "company";

    public static void main(String[] args) {

        Jedis jedis = RedisConnection.getConnection();
        jedis.del(key);
        //新增
        jedis.geoadd(key,116.48105,39.996794,"juejin");
        jedis.geoadd(key,116.514203,39.905409,"ireader");
        jedis.geoadd(key,116.489033,40.007669,"meituan");
        jedis.geoadd(key,116.562108,39.787602,"jd");
        jedis.geoadd(key,116.334255,40.027400,"xiaomi");

        //计算两个元素之间的距离
        Double geodist = jedis.geodist("company", "juejin", "xiaomi", GeoUnit.M);
        System.out.println("掘金和小米之间的距离="+geodist);

        //获取元素位置
        List<GeoCoordinate> geopos = jedis.geopos("company", "xiaomi");
        System.out.println("小米的坐标位置="+ JSON.toJSONString(geopos));

        //根据member查询附件元素
        GeoRadiusParam param = new GeoRadiusParam();
        //查询个数
        param.count(1);
        //正序
        param.sortAscending();
        //显示距离
        param.withDist();
        List<GeoRadiusResponse> xiaomi = jedis.georadiusByMember(key, "xiaomi", 20, GeoUnit.KM, param);
        System.out.println("距离小米最近的="+JSON.toJSONString(xiaomi));

        //根据坐标查询附件元素
        GeoRadiusParam param1 = new GeoRadiusParam();
        param1.count(2);
        //倒序
        param1.sortDescending();
        //显示距离
        param1.withDist();
        List<GeoRadiusResponse> byitude = jedis.georadiusReadonly(key, 116.514202, 39.905409, 20, GeoUnit.KM,param1);
        System.out.println("根据坐标查询="+JSON.toJSONString(byitude));

    }
}