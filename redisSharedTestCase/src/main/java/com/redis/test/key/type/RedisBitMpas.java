package com.redis.test.key.type;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/6 21:54
 * @Description:
 */
public class RedisBitMpas {
    public static void main(String[] args) {
        Jedis jedis = RedisConnection.getConnection();
    }
}
