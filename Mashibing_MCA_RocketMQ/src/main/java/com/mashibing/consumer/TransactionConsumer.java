package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 事务消息的消费者
 * 其实事务消息的消费者和非事务消息的消费者是一样的，就是普通的消费者，只不过要在消费消息的时候做好幂等性校验，还有对死信队列的消息做处理
 */
public class TransactionConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = new DefaultMQPushConsumer("consumerGroupTest");
            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer.subscribe("topicTest6", "*");
            // 5.设置回调函数，处理消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        // 伪码：开启消费者事务
                        for (MessageExt messageExt : list) {
                            // 进行本地事务处理并做好幂等性判断
                            System.out.println("事务id：" + messageExt.getTransactionId() + "，body:" + new String(messageExt.getBody(), "utf-8"));
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
