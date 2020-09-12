package com.redis.test.key.type.delayqueue;

import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/12 16:20
 * @Description:
 */
public class RedisDelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = RedisConnection.getConnection();
        jedis.del("delay_q");
        final RedisDelayQueue<String> queue = new RedisDelayQueue<>( "delay_q");

        //创建生产者线程池
        ThreadPoolExecutor producer = new ThreadPoolExecutor(3, 9, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        int[] delayScore = {1800,1600,1400,1200,1000,8000,6000,4000,2000,0,};
        for (int i = 0; i < 10; i++) {
            final int score = delayScore[new Random().nextInt(9)];
            final int msgCode = i ;

            producer.execute(()->{
                queue.delay("code:"+msgCode, score);
            });
        }


        //创建消费者线程池
        ThreadPoolExecutor consumer = new ThreadPoolExecutor(3, 6, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        consumer.execute(()->{
            System.out.println("consumer线程");
            queue.loop();
        });

    }
}
