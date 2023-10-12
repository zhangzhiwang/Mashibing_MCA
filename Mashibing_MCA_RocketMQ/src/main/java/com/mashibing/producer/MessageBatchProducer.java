package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生产者批量发送消息
 */
public class MessageBatchProducer {
    public static void main(String[] args) {
//        DefaultMQProducer producer = null;
//        try {
//            producer = new DefaultMQProducer("producerGroupTest3");
//            producer.setNamesrvAddr("127.0.0.1:9876");
//            producer.start();
//
//            List<Message> messageList = getMessageList("testTopic3");
//            producer.send(messageList);// 注意：批量发送消息一次性发送内容的大小不得超过4M，如果超过4M要对messageList做拆分并多次发送
//            System.out.println("消息发送完毕！");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(producer != null) {
//                producer.shutdown();
//            }
//        }
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap();
        map.putIfAbsent(1, "a");
        String result = map.putIfAbsent(1, "a");
        System.out.println(result);
    }

    public static List<Message> getMessageList(String topic) {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(topic, "批量消息1".getBytes()));
        messageList.add(new Message(topic, "批量消息2".getBytes()));
        messageList.add(new Message(topic, "批量消息3".getBytes()));
        messageList.add(new Message(topic, "批量消息4".getBytes()));
        messageList.add(new Message(topic, "批量消息5".getBytes()));
        return messageList;
    }
}
