package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * RocketMQ消费者客户端2
 * 模拟同一个消费者组里面的第二个消费者
 */
public class MyFirstRocketMQConsumer2 {
    public static void main(String[] args) {
        /*
        1.创建消费者Consumer，指定消费者组名
        2.指定Nameserver地址
        3.订阅主题Topic和Tag
        4.设置消费者的消费模式
        5.设置回调函数，处理消息
        6.启动消费者
        7.关闭消费者
         */
        System.out.println("消费者2启动...");

        DefaultMQPushConsumer consumer = null;
        try {
            // 1.创建消费者Consumer，指定消费者组名
            consumer = new DefaultMQPushConsumer("consumerGroupTest");
            // 2.指定Nameserver地址
            consumer.setNamesrvAddr("127.0.0.1:9876");
            // 3.订阅主题Topic和Tag
            consumer.subscribe("topicTest1", "*");
            // 4.设置消费者的消费模式
//            consumer.setMessageModel(MessageModel.CLUSTERING);// 默认就是集群消费模式，这里是为了演示
            consumer.setMessageModel(MessageModel.BROADCASTING);
            // 5.设置回调函数，处理消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        for (MessageExt messageExt : list) {
                            System.out.println("收到消息：topic:" + messageExt.getTopic() + "，消费队列：" + messageExt.getQueueId() + "，tag:" + messageExt.getTags() + "，body:" + new String(messageExt.getBody(), "utf-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            // 6.启动消费者consumer
            consumer.start();

            Thread.sleep(300000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭消费者
            if (consumer != null) {
                consumer.shutdown();
            }
        }
    }
}
