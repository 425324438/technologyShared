package com.redis.test.key.type;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/6 21:51
 * @Description: 使用redis的list实现消息队列
 */
public class RedisList {
    public static String MSG_PIPELINE="MSG_PIPELINE";
    public static void main(String[] args) {

        //启动生产者
        new Thread(new Producer()).start();

        //启动消费者
        new Thread(new Consumer()).start();

    }
}

class Producer implements Runnable{

    public void run() {
        Jedis jedis = RedisConnection.getConnection();

        int i =0;
        while (true){
            String s = String.valueOf(i++);
            //生产者往队列里写入数据
            jedis.rpush(RedisList.MSG_PIPELINE, s);
            System.out.println("Producer write in reds = "+ s);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("生产者线程被打断");
            }
        }
    }
}

class Consumer implements Runnable{

    public void run() {
        //消费者使用阻塞读来实现未读取到消息时线程等待
        Jedis jedis = RedisConnection.getConnection();
        while (true){
            /**
             * int timeout 单次等待超时时间。如果超过这个时间没有获取到队列中的消息，则会返回null，
             *             并且再次重试获取队列中的消息
             * String key  redis List 的key
             */
            List<String> rpop = jedis.brpop(10,RedisList.MSG_PIPELINE);
            System.out.println("Consumer read redis = "+rpop);
        }
    }
}