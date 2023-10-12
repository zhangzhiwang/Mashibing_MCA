package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 生产者发送的消息加入自定义属性及其值，消费者可以使用自定义的属性来过滤消息
 */
public class MessageSqlProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("producerGroupTest2");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            for(int i = 1; i <= 10 ; i++) {
                Message message = null;
                if(i % 3 == 0) {
                    message = new Message("testTopic5", "tagA", ("message_" + i).getBytes());
                } else if(i % 2 == 0) {
                    message = new Message("testTopic5", "tagB", ("message_" + i).getBytes());
                } else {
                    message = new Message("testTopic5", "tagC", ("message_" + i).getBytes());
                }
                message.putUserProperty("myNum", "" + i);
                SendResult sendResult = producer.send(message);
                System.out.println(sendResult);
            }
            System.out.println("所有消息发送完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.shutdown();
            }
        }
    }
}
