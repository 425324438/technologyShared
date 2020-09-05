package com.redis.test.redis.connection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/8/30 16:20
 * @Description:
 */
public class RedisConnection {
    private static String host = "localhost";
    private static int port = 6379;

    public static Jedis getConnection(){
        JedisPool jedisPool = new JedisPool(host,port);
        return jedisPool.getResource();
    }
}
