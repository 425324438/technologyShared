package com.redis.test.key.type;

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
         *
         * 对应的命令 SET lock_key VALUE
         */
        jedis.set("cacheKey","cacheValue",SetParams.setParams().ex(10));


        /**
         * 分布式锁简单实现
         * 对应的命令 SET lock_key VALUE NX EX单位时秒
         *
         * EX seconds – 设置键key的过期时间，
         * PX milliseconds – 设置键key的过期时间，单位时毫秒
         * NX – 只有键key不存在的时候才会设置key的值
         * XX – 只有键key存在的时候才会设置key的值
         */
        jedis.set("lock_key","lock_value[通常设置为=当前时间+过期时间]", SetParams.setParams().nx().ex(1000));
    }
    /**
     * keys的淘汰策略    http://redis.cn/commands/expire.html
     * Redis分布式锁     http://redis.cn/topics/distlock.html
     */
}
