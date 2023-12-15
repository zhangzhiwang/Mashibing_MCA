package com.mashibing.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 100; i++) {
            es.execute(new MyThread());
        }

    }

    static class MyThread extends Thread {
        public void run() {
            System.out.println("hello world!");
        }
    }
}
