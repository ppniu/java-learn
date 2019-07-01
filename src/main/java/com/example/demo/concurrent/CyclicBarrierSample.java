package com.example.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 *
 * @author 牛朋朋
 * @date 2019/4/21
 */
public class CyclicBarrierSample {


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("ACTION GO AGAIN...");
        });

        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new CyclicWorker("Person " + i, barrier));
            thread.start();;
        }
    }


    static class CyclicWorker implements Runnable {

        private CyclicBarrier barrier;

        private String name;

        public CyclicWorker(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {

            for (int i = 0; i < 2; i++) {
                System.out.println(name + " Executed ...");
                try {
                    //等待所有thread 使用完 parties, 然后进行下一次循环
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
