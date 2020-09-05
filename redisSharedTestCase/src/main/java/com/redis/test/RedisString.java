package com.redis.test;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/5 16:21
 * @Description:
 */
public class RedisString {
    public static void main(String[] args) {
        Jedis jedis = RedisConnection.getConnection();

        /**
         * cacheKey 缓存的key
         * cacheValue 缓存的value
         * SetParams.setParams().ex(10)  缓存的超时时间
         */
        jedis.set("cacheKey","cacheValue",SetParams.setParams().ex(10));

        //分布式锁的正确姿势
        jedis.set("","", SetParams.setParams().nx());
    }
}
