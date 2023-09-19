package com.mashibing.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketMQ生产者客户端
 */
public class MyFirstRocketMQProducer {
    public static void main(String[] args) {
        /*
        消息发送的步骤（同步发送消息）：
        1.创建消息生产者producer，并指定生产者组名
        2.指定Nameserver地址
        3.启动producer
        4.创建消息对象，指定Topic、Tag和消息体
        5.发送消息并同步接受消息发送的结果
        6.关闭生产者producer
        */
        DefaultMQProducer producer = null;
        try {
            // 1.创建消息生产者producer，并指定生产者组名
            producer = new DefaultMQProducer("producerGroupTest");
            // 2.指定Nameserver地址
            producer.setNamesrvAddr("127.0.0.1:9876");
            // 3.启动producer
            producer.start();
            for (int i = 0; i < 10; i++) {// 发送10条消息
                // 4.创建消息对象，指定Topic、Tag和消息体
                Message message = new Message("topicTest1", "tagTest1", "消息内容1".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 5.发送消息并同步接受消息发送的结果
                SendResult sendResult = producer.send(message);
                System.out.println("sendResult：" + sendResult);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(producer != null) {
                // 6.关闭生产者producer
                producer.shutdown();
            }
        }
    }
}
