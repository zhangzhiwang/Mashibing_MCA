package com.mashibing.producer;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 幂等性生产者2
 */
public class TransactionProducer2 {
    public static void main(String[] args) {
        TransactionMQProducer producer = null;// 注意：事务消息生产者就不能使用DefaultMQProducer了，要使用带事务功能的TransactionMQProducer
        try {
            producer = new TransactionMQProducer("producerGroupTest");
            producer.setNamesrvAddr("127.0.0.1:9876");

            // 设置事务监听器，用于监听rmq的事务回查
            producer.setTransactionListener(new TransactionListener() {// 当然可以在外面定义一个TransactionListener的实现类，这里将实现类的对象传进来即可，以免使用匿名内部类影响可读性
                /**
                 * 执行生产者本地事务
                 * @param message
                 * @param o
                 * @return
                 */
                @Override
                public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                    /*
                    即使这里抛出了异常，那么主方法里面的
                    SendResult sendResult = producer.sendMessageInTransaction(message, null);
                    的返回值SendResult里面的状态仍然是成功，因为它发送的是半事务消息，表示半事务消息发送成功了，跟生产者本地业务无关
                     */
//                    int i = 1 / 0;

                    // 伪码：开启本地事务
                    // 执行本地事务代码
                    System.out.println("开始执行本地事务...");
                    System.out.println("事务id：" + message.getTransactionId());
                    // 本地事务执行完成后想rmq提交事务状态
                    LocalTransactionState lts = null;
//                    System.out.println("本地事务执行成功！");
//                    lts = LocalTransactionState.COMMIT_MESSAGE;// 本地事务执行成功，提交commit状态
//                    System.out.println("本地事务执行失败！");
//                    lts = LocalTransactionState.ROLLBACK_MESSAGE;// 本地事务执行失败，提交rollback状态
                    System.out.println("本地事务执行状态未知！");
                    lts = LocalTransactionState.UNKNOW;// 本地事务执行状态未知，提交UNKNOW状态
                    return null;
                }

                /**
                 * 事务回查
                 * @param messageExt
                 * @return
                 */
                @Override
                public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                    System.out.println("rmq事务回查...");
                    System.out.println("事务id：" + messageExt.getTransactionId());
                    // 伪码：检查本地事务是否执行成功
                    LocalTransactionState lts = null;
                    System.out.println("回查返回：本地事务执行成功！");
                    lts = LocalTransactionState.COMMIT_MESSAGE;// 本地事务执行成功，提交commit状态
//                    System.out.println("回查返回：本地事务执行失败！");
//                    lts = LocalTransactionState.ROLLBACK_MESSAGE;// 本地事务执行失败，提交rollback状态
//                    lts = LocalTransactionState.UNKNOW;// 本地事务执行状态未知，提交UNKNOW状态
                    return lts;
                }
            });

            // 设置线程池，rmq回查的时候是使用线程池里面的线程来进行，也就是上面的TransactionListener.checkLocalTransaction()方法是通过线程池中的线程来完成的
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            producer.setExecutorService(executorService);


            producer.start();
            for (int i = 0; i < 1; i++) {
                Message message = new Message("topicTest6", "tagTest1", "事务消息测试".getBytes(RemotingHelper.DEFAULT_CHARSET));
//                SendResult sendResult = producer.send(message);// 发送普通消息可以使用send()方法
                /*
                 发送事务消息要使用sendMessageInTransaction()方法，这个方法发送的是半事务消息。
                 1、如果半事务消息返回发送成功，那么会调用上面TransactionListener.executeLocalTransaction()方法执行生产者的本地事务
                 2、如果半事务消息返回发送失败，就不会执行生产者的本地事务
                 */
                SendResult sendResult = producer.sendMessageInTransaction(message, null);// 发送的是半事务消息
                System.out.println("半事务消息sendResult：" + sendResult);

                TimeUnit.MINUTES.sleep(3);
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
