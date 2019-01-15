package com.ygy.liberal.disruptor.test1;


import com.lmax.disruptor.EventHandler;

/**
 * Created by guoyao on 2018/11/14.
 * 事件处理器
 */
public class LongEventHandler2 implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {

        long number = event.getValue();
        number *= 10;
        System.out.println(System.currentTimeMillis()+": c2 consumer finished.number=" + number);
    }
}
