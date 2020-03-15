package com.ygy.liberal.arithmetic.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by guoyao on 2019/2/21.
 */
public class DeadLockDemo {


    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        lock1.lock();
                        System.out.println("获取锁1" + Thread.currentThread().getName() + " :" + countDownLatch.getCount());
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock2.lock();
                        System.out.println("获取锁2");
                        lock2.unlock();
                        lock1.unlock();
                    }
                }
                , "thread-1").start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        lock2.lock();
                        countDownLatch.countDown();
                        System.out.println("获取锁2 :" + Thread.currentThread().getName() + " :" + countDownLatch.getCount());
                        lock1.lock();
                        System.out.println("获取锁1");
                        lock1.unlock();
                        lock2.unlock();
                    }
                }
                , "thread-2").start();
    }

}
