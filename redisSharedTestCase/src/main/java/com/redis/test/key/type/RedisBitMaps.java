package com.redis.test.key.type;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/6 21:54
 * @Description:
 */
public class RedisBitMaps {
    public static void main(String[] args) {
        Jedis jedis = RedisConnection.getConnection();
        jedis.setbit("bitKey",0L, false);

        Boolean bitKey = jedis.getbit("bitKey", 0L);
        System.out.println("读取 bitKey="+bitKey);

        //
        jedis.bitcount("bitKey");


        //返回字符串里面第一个被设置为1或者0的bit位。
        // false=0,true=1
        Long bitKey2 = jedis.bitpos("bitKey", false);
        System.out.println("第一个出现false的位置="+bitKey2);

        //获取指定范围内第一次出现false的位置
        Long bitKey1 = jedis.bitpos("bitKey", false, new BitPosParams(0L, 10L));
        System.out.println("在0 ~ 10 只能第一个出现false的位置="+bitKey1);

    }
}
