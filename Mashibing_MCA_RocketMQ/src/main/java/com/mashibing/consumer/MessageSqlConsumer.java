package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者使用sql来过滤想要消费的消息
 */
public class MessageSqlConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = new DefaultMQPushConsumer("consumerGroupTest");
            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer.subscribe("testTopic5",
                    MessageSelector.bySql("(TAGS is not null and TAGS in ('tagA','tagC')) and (myNum is not null and myNum between 3 and 7)"));
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
