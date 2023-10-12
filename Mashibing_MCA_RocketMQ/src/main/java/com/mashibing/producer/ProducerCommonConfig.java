package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;
import java.util.Random;

/**
 * 生产者客户端常用的设置
 */
public class ProducerCommonConfig {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            // 定义生产者对象的时候要指定生产者组名，来指定该生产者是属于哪个组的，对于一般性的消息发送来说没有太大作用，但是对于事务性消息极为重要
            producer = new DefaultMQProducer("producerGroupTest");
            // 指定topic在每一个broker上的消息队列的数量
            producer.setDefaultTopicQueueNums(9);
            // 设置发送消息的超时时间，默认是3s
            producer.setSendMsgTimeout(3000);
            // 消息体超过多大时启动压缩，默认是4k
            producer.setCompressMsgBodyOverHowmuch(4 * 1024);
            // 同步发送时，消息发送失败的重试次数，默认是2次，重试2次加上第一次发送一种是3次
            producer.setRetryTimesWhenSendFailed(10);// 这里设置的是重试10次，加上第一次发送的一共是11次
            // 异步发送时，消息发送失败的重试次数，默认是2次，重试2次加上第一次发送一种是3次
            producer.setRetryTimesWhenSendAsyncFailed(10);
            // 当消息发往一个broker时如果发送失败，是否在重试的时候发往另一个broker，默认为false
            producer.setRetryAnotherBrokerWhenNotStoreOK(false);
            // 允许发送的最大消息大小，默认为4M
            producer.setMaxMessageSize(4 * 1024 * 1024);
            // 设置nameserver地址
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();
            for (int i = 0; i < 10; i++) {// 发送10条消息
                // 创建消息对象，指定Topic、Tag和消息体
                Message message = new Message("topicTest1", "tagTest1", ("消息内容" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息
                // 同步发送——同步等待接受每条消息发送的结果
                SendResult sendResult = producer.send(message);
                System.out.println("sendResult：" + sendResult);
                // 异步发送——使用带回调的send方法，不等待broker返回的发送结果，broker有了发送结果会调用回调方法来通知
//                producer.send(message, new SendCallback() {
//                    @Override
//                    public void onSuccess(SendResult sendResult) {
//                        System.out.println("发送成功：" + sendResult);
//                    }
//
//                    @Override
//                    public void onException(Throwable throwable) {
//                        System.out.println("发送失败：" + throwable.getMessage());
//                        throwable.printStackTrace();
//                    }
//                });
                /*
                 也可以使用重载的send方法：send(Message msg, MessageQueueSelector selector, Object arg, SendCallback sendCallback, long timeout)
                 第一个参数传入消息对象，第二个参数传入队列选择器，第三个参数传入一个数值用于第二个参数队列选择器中select方法的第三个入参，第四个传入回调函数，第五个传入发送超时时间
                 */
                producer.send(message,
                        new MessageQueueSelector() {
                            /**
                             *
                             * @param list
                             * @param message
                             * @param o 就是sendOneway方法第三个参数传入的值new Random().nextInt(3)
                             * @return
                             */
                            @Override
                            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                                int i = (int) o;
                                return list.get(i);
                            }
                        },
                        new Random().nextInt(3),
                        new SendCallback() {
                            @Override
                            public void onSuccess(SendResult sendResult) {
                                System.out.println("发送成功：" + sendResult);
                            }

                            @Override
                            public void onException(Throwable throwable) {
                                System.out.println("发送失败：" + throwable.getMessage());
                                throwable.printStackTrace();
                            }
                        },
                        10000
                );


                // 单向发送——producer不会等待broker的发送结果并且也不关心发送结果，只管发送，broker也不会通知发送结果
//                producer.sendOneway(message);// sendOneway()方法是一个void类型
                // 单向发送的重载方法，可以指定发往哪个队列
                producer.sendOneway(message, new MessageQueueSelector() {
                    /**
                     *
                     * @param list
                     * @param message
                     * @param o 就是sendOneway方法第三个参数传入的值new Random().nextInt(3)
                     * @return
                     */
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        int i = (int) o;
                        return list.get(i);
                    }
                }, new Random().nextInt(3));// sendOneway的第三个参数会作为MessageQueueSelector匿名内部类里面select方法的第三个入参Object传入

//                Thread.sleep(300);// 注意：由于是异步的，所以producer不会等待发送结果既往下面运行，很有可能消息还没有发送完就是行了下面的shutdown操作，这里为了演示要休眠一下。
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                // 关闭生产者producer
                producer.shutdown();
            }
        }
    }
}
