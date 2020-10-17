package com.redis.test.key.type;

import io.rebloom.client.Client;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/10/13 21:14
 * @Description: 演示redis的布隆过滤器
 */
public class RedisBloomFilter {

    static String key = "BloomFilterKey";


    public static void main(String[] args) {
        Client bfClient = new Client("localhost", 6379);
        bfClient.delete(key);

        //name : 容器的key
        //initCapacity : 容器的初始化容量
        //errorRate : 容器的精度
        //自动伸缩的过滤器
        bfClient.createFilter(key,100L,0.0001);

        for (int i = 0; i < 1001; i++) {
            boolean add = bfClient.add(key, i + "");
            System.out.println("添加成功，add="+add);
        }


    }
}
