package com.ygy.liberal.disruptor.test1;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by guoyao on 2018/11/14.
 */
public class Starter {

    private static  RingBuffer<LongEvent> ringBuffer;

    public static void main(String[] args) throws Exception {
        DisruptorManager.init(new LongEventHandler1());
        for (long l = 0; true; l++) {
            DisruptorManager.putDataToQueue(l);
            Thread.sleep(1000);
        }
    }

    private static void start1() {
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；

        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory,
                ringBufferSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                AtomicInteger index = new AtomicInteger(1);
                return new Thread(null, r, "disruptor-thread-" + index.getAndIncrement());
            }
        }, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<LongEvent> eventHandler = new LongEventHandler1();
        disruptor.handleEventsWith(eventHandler);
        ringBuffer=disruptor.getRingBuffer();
        disruptor.start();
        for (Long index  = 1L;index < 100L;index ++) {
            ringBuffer.get(ringBuffer.next()).setValue(index);
        }
    }
}
