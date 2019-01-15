package com.ygy.liberal.disruptor.test1;


import com.lmax.disruptor.EventHandler;

/**
 * Created by guoyao on 2018/11/14.
 * 事件处理器
 */
public class LongEventHandler1 implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(10 * 1000);
        long number = event.getValue();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1 consumer finished.number=" + number);
    }
}
