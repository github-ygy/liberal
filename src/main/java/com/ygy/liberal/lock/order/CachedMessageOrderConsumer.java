package com.ygy.liberal.lock.order;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guoyao
 * @date 2020-04-30
 */
public class CachedMessageOrderConsumer<T> {

    private PriorityQueue<OrderPackage<T>> speechQueue = new PriorityQueue<>((x, y) -> {
        return (x.getBatch() - y.getBatch()) + (x.getOrder() - y.getOrder());
    });

    private volatile Integer consumedBatch;

    private volatile Integer consumedOrder;

    private Long consumedTime = System.currentTimeMillis();

    private Lock messageLock = new ReentrantLock(true);

    private Condition condition = messageLock.newCondition();

    private Semaphore mainConsumer = new Semaphore(1);

    private int retryTimes = 0;

    public void addAndConsumeMessage(OrderPackage<T> orderPackage) {
        messageLock.lock();
        boolean added = false;
        boolean mainThread = false;
        try {
            Integer batch = orderPackage.getBatch();
            Integer order = orderPackage.getOrder();

            if (!checkAndValidBatch(batch)) {
                return;
            }

            if (!checkAndValidOrder(order)) {
                return;
            }

            if (!speechQueue.isEmpty() && speechQueue.contains(orderPackage)) {
                return;
            }

            speechQueue.add(orderPackage);
            added = true;
            //只有一个线程进行出来
            if (!mainConsumer.tryAcquire()) {
                return;
            }
            mainThread = true;
            OrderPackage<T> prePeek = null;
            while (!speechQueue.isEmpty()) {

                OrderPackage<T> peek = speechQueue.peek();

                //开始消费 消息切非头结点
                if (!checkNeedConsume(peek) && retryTimes < 3) {
                    //wait until notify
                    try {
                        condition.await(100, TimeUnit.MILLISECONDS);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (prePeek == null || prePeek == peek) {
                        retryTimes++;
                    }
                    prePeek = peek;
                    continue;
                }
                consumeMessage(speechQueue.poll());
                retryTimes = 0;
                continue;
            }
            mainConsumer.release();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (added && !mainThread) {
                try {
                    condition.signal();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            messageLock.unlock();
        }
    }

    private boolean checkAndValidOrder(Integer order) {
        if (consumedOrder != null && consumedOrder >= order) {
            return false;
        }

        return true;
    }

    private boolean checkAndValidBatch(Integer batch) {
        //批次落后:直接过滤
        if (consumedBatch != null && batch < consumedBatch) {
            return false;
        }
        if (consumedBatch != null && batch > consumedBatch) {
            resetQueue();
        }
        //重置或者还未开始消费
        if (consumedBatch == null) {
            consumedBatch = batch;
        }
        return true;
    }

    private boolean checkNeedConsume(OrderPackage<T> peek) {
        if (consumedOrder == null && peek.getStatus() == OrderStatus.START) {
            return true;
        }

        if (consumedOrder == null) {
            return false;
        }

        return peek.getOrder() - consumedOrder == 1;
    }

    private void resetQueue() {
        speechQueue.clear();
        consumedBatch = null;
        consumedOrder = 0;
    }

    private void consumeMessage(OrderPackage<T> consumePackage) {
        consumedOrder = consumePackage.getOrder();
        consumedTime = System.currentTimeMillis();
    }

    public boolean canDestory(long tryCheckDestoryTime, long timeOutSpan) {
        boolean getLocked = false;
        try {
            try {
                getLocked = messageLock.tryLock(tryCheckDestoryTime, TimeUnit.MILLISECONDS);
            } catch (Exception ex) {
                return false;
            }
            if (!getLocked) {
                return false;
            }
            if (timeOutSpan <= 0) {
                throw new IllegalStateException(" must > 0 ,timeOutSpan = " + timeOutSpan);
            }
            System.out.println("timeOutSpan : " + (System.currentTimeMillis() - consumedTime));
            return System.currentTimeMillis() - consumedTime >= timeOutSpan;
        } finally {
            if (getLocked) {
                messageLock.unlock();
            }
        }
    }


}
