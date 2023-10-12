package com.mashibing.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketMQ生产者客户端
 * RocketMQ的源码学习采用的版本是4.8.0，里面有老师和自己写的注释与笔记，已上传到github：https://github.com/zhangzhiwang/rocketmq-all-4.8.0-source-release
 */
public class MyFirstRocketMQProducer {
    public static void main(String[] args) {
        /*
        消息发送的步骤：
        1.创建消息生产者producer，并指定生产者组名
        2.指定Nameserver地址
        3.启动producer
        4.创建消息对象，指定Topic、Tag和消息体
        5.发送消息
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
                Message message = new Message("topicTest1", "tagTest1", ("消息内容" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 5.发送消息
                // 5.1 同步发送——同步等待接受每条消息发送的结果
                SendResult sendResult = producer.send(message);
                System.out.println("sendResult：" + sendResult);
                // 5.2 异步发送——使用带回调的send方法，不等待broker返回的发送结果，broker有了发送结果会调用回调方法来通知
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功：" + sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送失败：" + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });
                // 5.3 单向发送——producer不会等待broker的发送结果并且也不关心发送结果，只管发送，broker也不会通知发送结果
//                producer.sendOneway(message);// sendOneway()方法是一个void类型

//                Thread.sleep(300);// 注意：由于是异步的，所以producer不会等待发送结果既往下面运行，很有可能消息还没有发送完就是行了下面的shutdown操作，这里为了演示要休眠一下。
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
    /*
    如果运行报：
    1、org.apache.rocketmq.client.exception.MQBrokerException: CODE: 14  DESC: service not available now. It may be caused by one of the following reasons: the broker's disk is full [CL:  0.94 CQ:  0.94 INDEX: -1.00], messages are put to the slave, message store has been shut down, etc.
    解决：清理硬盘
    2、No route info of this topic
    说明已存在指定的topic，需要将其删除后重试
     */
}
