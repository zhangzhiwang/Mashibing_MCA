package com.mashibing.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.util.List;
import java.util.Set;

/**
 * 消费者客户端常用的设置
 */
public class ConsumerCommonConfig {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = null;
        try {
            /*
             创建消费者Consumer，指定消费者组名。
             消费者组的重要性要比生产者组的重要性（非事务消息）高，因为它涉及到消费者怎么去瓜分topic以及topic下的message queue，
             不同组的消费者和同一组的消费者瓜分的情况完全不一样
             */
            consumer = new DefaultMQPushConsumer("consumerGroupTest");
            // 指定Nameserver地址
            consumer.setNamesrvAddr("127.0.0.1:9876");
            // 设置消费者的消费模式
//            consumer.setMessageModel(MessageModel.CLUSTERING);// 默认就是集群消费模式，这里是为了演示
            consumer.setMessageModel(MessageModel.BROADCASTING);
            /*
             指定从哪开始消费消息，具体可以看ConsumeFromWhere的枚举值
             常用的有：
             CONSUME_FROM_LAST_OFFSET：从上一次消费的地方继续消费
             CONSUME_FROM_MAX_OFFSET：从最大偏移量开始消费
             CONSUME_FROM_MIN_OFFSET：从最小偏移量开始消费
             CONSUME_FROM_TIMESTAMP：从启动时间戳开始消费
             */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            // 消费者使用的最小线程数和最大线程数
            consumer.setConsumeThreadMin(10);
            consumer.setConsumeThreadMax(20);
            // 消费者向broker拉取消息的时间间隔
            consumer.setPullInterval(0);
            // 一次性拉取消息的条数，默认是32条
            consumer.setPullBatchSize(32);
            // 消息消费失败后的重试次数，-1代表16次，如果超过指定重试次数后仍然消费失败那么这条消息就会进去死信队列
            consumer.setMaxReconsumeTimes(-1);
            // 消费的超时时间，注意单位是分钟
            consumer.setConsumeTimeout(1);

            // 消费者常用的方法：
            // 获取消费者所订阅的topic下有多少消息队列
            Set<MessageQueue> messageQueueSet = consumer.fetchSubscribeMessageQueues("testTopic3");
            for(MessageQueue mq : messageQueueSet) {
                System.out.println(mq.getQueueId());
            }

            // 订阅主题Topic和Tag
            consumer.subscribe("testTopic3", "*");// 支持正则
            consumer.subscribe("testTopic3", "tagA || tagB");// 注意区分写法：MessageSelector.byTag("tagA | tagB")
            consumer.subscribe("testTopic3", MessageSelector.bySql(""));// 使用sql语句过滤消息
            consumer.subscribe("testTopic3", MessageSelector.byTag("tagA | tagB"));// 使用tag过滤消息
            // 设置回调函数，处理消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        for (MessageExt messageExt : list) {
                            System.out.println("收到消息：topic:" + messageExt.getTopic() + "，消费队列：" + messageExt.getQueueId() + "，tag:" + messageExt.getTags() + "，body:" + new String(messageExt.getBody(), "utf-8"));
//                            System.out.println("收到消息：topic:" + messageExt.getTopic() + "，消费延迟：" + (messageExt.getStoreTimestamp() - messageExt.getBornTimestamp()) + "ms");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        /*
                         通过字面意思可以看出来是"一会儿再消费"的意思，它的实现原理是将消费失败的消息放到一个同名的重试队列中然后继续消费后面的消息，
                         过一会儿再去消费重试队列里面的消息，这种策略一般使用在MessageListenerConcurrently里面，它不能保证消费消息的顺序性
                         */
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            // 启动消费者consumer
            consumer.start();

            Thread.sleep(300000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭消费者
            if (consumer != null) {
                consumer.shutdown();
            }
        }
    }
}
