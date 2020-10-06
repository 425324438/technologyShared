package com.redis.test.key.type;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/6 21:54
 * @Description:
 */
public class RedisHyperloglogs {
    public static void main(String[] args) {
        Jedis jedis = RedisConnection.getConnection();
        //清空 key
        jedis.del("keypf");
        for (int i = 0; i < 1000; i++) {
            jedis.pfadd("keypf", UUID.randomUUID().toString());
        }
        long pfcount = jedis.pfcount("keypf");
        System.out.println("1000次写入，count的结果="+pfcount);
    }
}
