package com.mashibing.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * 创建两个生产者
 */
public class CreatTwoProducerTest {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer1 = new DefaultMQProducer("producerGroupTest");
//        producer1.setUnitName("UN_1");
        producer1.setNamesrvAddr("127.0.0.1:9876");
        producer1.start();
        System.out.println("producer1启动成功");

        DefaultMQProducer producer2 = new DefaultMQProducer("producerGroupTest");
//        producer2.setUnitName("UN_2");
        producer2.setNamesrvAddr("127.0.0.1:9876");
        producer2.start();
        System.out.println("producer2启动成功");
    }
}
