package com.example.demo.concurrent;

/**
 * 死锁案例
 *
 * @author 牛朋朋
 * @date 2019/4/21
 */
public class DeadlockSample {


    public static void main(String[] args) throws InterruptedException {

        String lock1 = "lock1";
        String lock2 = "lock2";
        Person person1 = new Person("Thread1", lock1, lock2);
        Person person2 = new Person("Thread2", lock2, lock1);
        person1.start();
        person2.start();
        person1.join();
        person2.join();
    }


    static class Person extends Thread {

        private String lock1;

        private String lock2;


        public Person(String name, String lock1, String lock2) {
            super(name);
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(this.getName() + " get " + lock1);
                try {
                    Thread.sleep(1000);
                    synchronized (lock2) {
                        System.out.println(this.getName() + " get " + lock2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
