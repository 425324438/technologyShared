package com.redis.test.key.type.delayqueue;


import com.alibaba.fastjson.JSON;
import com.redis.test.redis.connection.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author: 425324438@qq.com
 * @Date: 2020/9/12 15:47
 * @Description: redis实现的延时队列
 */
public class RedisDelayQueue<T>  {

    /**
     * 消息体
     */
    static class TaskItem<T> {
        private T msg;
        private int delayScore;
    }

    /**
     * 队列名称
     */
    private String queueKey;

    public RedisDelayQueue(String queueKey) {
        this.queueKey = queueKey;
    }

    /**
     * 往队列写入消息
     * @param msg 消息
     * @param delayScore 延迟时间
     */
    public void delay(T msg, int delayScore) {
        Jedis jedis = RedisConnection.getConnection();
        TaskItem<T> task = new TaskItem<T>();
        task.msg = msg;
        task.delayScore=delayScore;
        String s = JSON.toJSONString(task);
        System.out.println("producer线程池id="+Thread.currentThread().getId()+",写入延迟队列，val="+JSON.toJSONString(task));
        //塞入延迟队列
        jedis.zadd(queueKey,System.currentTimeMillis() + delayScore, s);
        jedis.close();
    }

    /**
     * 轮询获取消息队列中的消息
     */
    public void loop(){
        System.out.println("consumer线程id="+Thread.currentThread().getId()+"启动");
        Jedis jedis = RedisConnection.getConnection();
        while (!Thread.interrupted()){
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()){
                try {
                    System.out.println("consumer没有读取到数据，线程休眠");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("线程被打断");
                    //如果线程被打断，则退出当前线程
                    break;
                }
                continue;
            }
            String msg = values.iterator().next();
            //根据当前获取的值，尝试删除。如果删除成功则为只有当前线程获取到了本条消息
            if(jedis.zrem(queueKey,msg) > 0){
                TaskItem<T> taskItem = JSON.parseObject(msg, TaskItem.class);
                System.out.println("线程池id="+Thread.currentThread().getId()+",获取到了消息："+taskItem.msg);
            }
        }
        jedis.close();
    }
}
