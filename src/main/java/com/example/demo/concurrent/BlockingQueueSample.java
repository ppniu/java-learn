package com.example.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 并发队列
 *
 * @author 牛朋朋
 * @date 2019/4/21
 */
public class BlockingQueueSample {

    private static final String EXIT_MSG = "good bye!";


    public static void main(String[] args) {
        //使用较小的队列,以更􏱔好展示输出影响
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Thread thread1 = new Thread(new Producer(blockingQueue));
        Thread thread2 = new Thread(new Consumer(blockingQueue));
        thread1.start();
        thread2.start();
    }


    //生产者-消费者模型


    static class Producer implements Runnable {

        private BlockingQueue<String> blockingQueue;


        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                    String message = "MSG" + i;
                    blockingQueue.put(message);
                    System.out.println("Produced one item " + message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("Produce Done");
                blockingQueue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Consumer implements Runnable {

        private BlockingQueue<String> blockingQueue;


        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            String msg = null;
            try {
                while (!EXIT_MSG.equals((msg = blockingQueue.take()))) {
                    System.out.println("Consume item " + msg);
                    Thread.sleep(20);
                }
            } catch (InterruptedException e) {

            }

        }

    }

}
