package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 生产者发送标有某些tag的消息，消费者
 */
public class MessageTagProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("producerGroupTest2");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            for(int i = 1; i <= 10 ; i++) {
                Message message = null;
                if(i % 3 == 0) {
                    message = new Message("testTopic4", "tagA", ("message_" + i).getBytes());
                } else if(i % 2 == 0) {
                    message = new Message("testTopic4", "tagB", ("message_" + i).getBytes());
                } else {
                    message = new Message("testTopic4", "tagC", ("message_" + i).getBytes());
                }
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
