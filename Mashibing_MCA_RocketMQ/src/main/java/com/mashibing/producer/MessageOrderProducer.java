package com.mashibing.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者生产消息实现分区有序，注意是分区有序不是全局有序
 */
public class MessageOrderProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("producerGroupTest2");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            // 每一个订单的顺序是：创建-付款-物流-完成，但是生产者发送的顺序是全局无序的
            List<Order> orderList = getOrderList();
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                Message message = new Message("testTopic2", "testTag2", order.toString().getBytes());

                /*
                可以在发送是使用MessageQueueSelector来指定消息发往哪一个队列，如果不指定就会随机发送到topic上的某个队列，但要注意：这个随机行为不是生产者完成的，是broker完成的。
                当broker接收到请求后发现没有指定发往哪一个队列，那么broker会生成一个随机数然后模以队列的数量来决定发往哪一个meq，具体的实现逻辑可以参考源码：org.apache.rocketmq.broker.processor.SendMessageProcessor#asyncSendMessage
                里面调用的randomQueueId方法。
                 */
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {// 执行消息队列的选择器，也就是指定什么消息发往什么队列

                    /**
                     *
                     * @param list
                     * @param message
                     * @param o 这个参数就是send方法的第三个参数掺入的订单id：orderList.get(i).getId()
                     * @return
                     */
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        // 根据订单id来决定将订单发往topic下的哪个消息队列
                        int orderId = (int) o;
                        int queueId = orderId % list.size();// 根据订单id对topic下面的队列数量进行取模
                        return list.get(queueId);
                    }
                }, order.getId());// orderList.get(i).getId()，订单id作为上面select方法的第三个参数Object o

                System.out.println("订单：" + order + "被发往：queueId：" + sendResult.getMessageQueue().getQueueId() + "，发送状态为：" + sendResult.getSendStatus());
            }
            System.out.println("所有消息发送完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.shutdown();
            }
        }
    }

    public static List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, "创建"));
        orderList.add(new Order(2, "创建"));
        orderList.add(new Order(3, "创建"));
        orderList.add(new Order(1, "付款"));
        orderList.add(new Order(2, "付款"));
        orderList.add(new Order(3, "付款"));
        orderList.add(new Order(1, "物流"));
        orderList.add(new Order(2, "物流"));
        orderList.add(new Order(3, "物流"));
        orderList.add(new Order(1, "完成"));
        orderList.add(new Order(2, "完成"));
        orderList.add(new Order(3, "完成"));
        return orderList;
    }

    static class Order {
        private int id;
        private String desc;

        public Order(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public Order() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
