package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 消费者消费消息实现分区有序，注意是分区有序不是全局有序
 */
public class MessageOrderConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = new DefaultMQPushConsumer("consumerGroupTest2");
            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer.subscribe("testTopic2", "*");
            consumer.registerMessageListener(new MessageListenerOrderly() {// MessageListenerOrderly带顺序的消息监听器，即顺序消费。MessageListenerOrderly会自动创建多个线程分别消费一个topic下的不同消息队列
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                    try {
                        context.setAutoCommit(true);// 设置自动提交offset

                        for (MessageExt message : list) {
                            System.out.println("消费消息：内容" + new String(message.getBody(), "utf-8") + "，线程：" + Thread.currentThread().getName() + "，topic：" + message.getTopic());
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                        /*
                        在MessageListenerOrderly监听器里面，不要使用ConsumeConcurrentlyStatus.RECONSUME_LATER策略，
                        因为这个策略会将消费失败的消息放到另一个重试队列里面然后继续消费后面的消息，这样就不能保证消息的顺序消费了。
                        要使用SUSPEND_CURRENT_QUEUE_A_MOMENT策略，这个策略就是将第一次消费失败的消息还放在原来的队列里面不动，一会儿再来消费，
                        这样可以保证消息的顺序性
                         */
                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });

            consumer.start();
            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
            /*
            通过打印可以看到，MessageListenerOrderly监听器会自动创建多个线程，每个线程分别消费一个topic下的一个队列，并保证每个线程消费的消息都是有序的
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(consumer != null) {
                consumer.shutdown();
            }
        }
    }
}
