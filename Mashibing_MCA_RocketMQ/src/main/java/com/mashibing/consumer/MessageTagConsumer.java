package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者只消费指定的tag
 */
public class MessageTagConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = new DefaultMQPushConsumer("consumerGroupTest");
            consumer.setNamesrvAddr("127.0.0.1:9876");
//            consumer.subscribe("testTopic3", "*");// 消费所有tag
            consumer.subscribe("testTopic4", "tagA || tagC");// 消费tagA或者tagB的消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        for (MessageExt messageExt : list) {
                            System.out.println("收到消息：topic:" + messageExt.getTopic() + "，消费队列：" + messageExt.getQueueId() + "，tag:" + messageExt.getTags() + "，body:" + new String(messageExt.getBody(), "utf-8"));
                        }
                    } catch (Exception e) {
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
