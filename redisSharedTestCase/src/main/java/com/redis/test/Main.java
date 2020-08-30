package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/8/30 15:51
 * @Description:
 */
public class Main {
    public static String host = "localhost";
    public static int port = 6379;

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(host,port);
        Jedis jedis = jedisPool.getResource();

    }
}
