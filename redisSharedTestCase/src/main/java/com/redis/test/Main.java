package com.redis.test;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.xml.ws.RequestWrapper;
import java.io.IOException;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/8/30 15:51
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Jedis jedis = RedisConnection.getConnection();


    }
}
