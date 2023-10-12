package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 生产者发送延时消息
 */
public class MessageDelayProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("producerGroupTest3");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            for(int i = 0; i < 3; i++) {
                Message message = new Message("testTopic2", ("延时消息" + i).getBytes());
                message.setDelayTimeSec(5);// 延迟10秒投递？？TODO 是延迟10s投递，还是立即投递但是是在broker那里延迟10s推送给消费者
                producer.send(message);
                System.out.println("延时消息" + i + "投递成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(producer != null) {
                producer.shutdown();
            }
        }
    }
}
