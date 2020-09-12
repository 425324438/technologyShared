package com.redis.test.key.type.delayqueue;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/12 15:47
 * @Description: redis实现的延时队列
 */
public class RedisDelayQueue<T> {

    public RedisDelayQueue(String queueKey) {
        this.queueKey = queueKey;
    }

    static class TaskItem<T> {
        private T msg;
        private int delayScore;

        public int getDelayScore() {
            return delayScore;
        }

        public void setDelayScore(int delayScore) {
            this.delayScore = delayScore;
        }
        public T getMsg() {
            return msg;
        }

        public void setMsg(T msg) {
            this.msg = msg;
        }
    }


    private Jedis jedis;
    private String queueKey;

    public void delay(T msg, int delayScore) {
        Jedis jedis = RedisConnection.getConnection();
        TaskItem<T> task = new TaskItem<T>();
        task.msg = msg;
        task.delayScore=delayScore;
        String s = JSON.toJSONString(task);
        System.out.println("线程池id="+Thread.currentThread().getId()+",写入延迟队列，val="+JSON.toJSONString(task));
        //塞入延迟队列
        jedis.zadd(queueKey,System.currentTimeMillis() + delayScore, s);
    }

    public void loop(){
        while (!Thread.interrupted()){
            Jedis jedis = RedisConnection.getConnection();
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("线程被打断");
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            //抢到了
            if(jedis.zrem(queueKey,s) > 0){
                //json反序列化
                TaskItem<T> taskItem = JSON.parseObject(s, TaskItem.class);
                System.out.println("线程池id="+Thread.currentThread().getId()+",获取到了值："+taskItem.getMsg());
            }
        }
    }

}
