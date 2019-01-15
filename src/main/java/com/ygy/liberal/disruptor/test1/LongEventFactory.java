package com.ygy.liberal.disruptor.test1;

import com.lmax.disruptor.EventFactory;

/**
 * Created by guoyao on 2018/11/14.
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
