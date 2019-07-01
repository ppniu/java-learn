package com.example.demo.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * //再入锁
 *
 * @author 牛朋朋
 * @date 2019/4/21
 */
public class ReentrantLockSample {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {


        new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "进入等待");
                    //调用await()方法后线程会释放当前占用的锁
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                //当线程被唤醒后，它就会尝试重新获得与之绑定的重入锁，一旦获取成功将继续执行。
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                //获取锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "进入等待");
                try {
                    //睡眠2s
                    Thread.sleep(2000);
                    //随机唤醒等待队列的一个线程
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "休息结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();


    }


    /**流程：在调用await()方法前线程必须获得重入锁，。
     * 同理在调用signal()方法时当前线程也必须获得相应重入锁（代码32行），调用signal()方法后系统会从condition.await()等待队列中唤醒一个线程。
     *
     * 所以调用signal()方法后一定要释放当前占用的锁（代码41行），这样被唤醒的线程才能有获得锁的机会，才能继续执行。
     *
     */
}
