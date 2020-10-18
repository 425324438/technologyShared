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
    static String signIn = "SignIn";
    public static void main(String[] args) {

        Jedis jedis = RedisConnection.getConnection();

        System.out.println("模拟生成用户30天签到记录");
        int signCount = 0;
        for (int i = 0; i < 30; i++) {
            boolean bool = randomBool();
            signCount += bool ? 1 : 0;
            if(bool){
                System.out.println("\r第"+i+"天签到了,SignIn="+bool);
            }
            jedis.setbit(signIn,(long)i,bool);
        }
        System.out.println("模拟签到统计天数="+signCount);
        System.out.println("模拟生成用户30天签到记录,结束");
        System.out.println("---- ---- ---- ---- ---- --");

        System.out.println("redis获取实际签到天数= "+jedis.bitcount(signIn));
        System.out.println("第一天签到的时间= "+jedis.bitpos(signIn,true,new BitPosParams(0)));
        System.out.println("查询第19天是否签到= "+jedis.getbit(signIn,19L));

    }

    public static boolean randomBool(){
        return Math.random() > 0.7;
    }

}
