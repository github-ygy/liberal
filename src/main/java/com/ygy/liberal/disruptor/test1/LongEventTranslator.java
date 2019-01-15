package com.ygy.liberal.disruptor.test1;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * Created by guoyao on 2018/11/15.
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {


    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setValue(arg0);
    }
}


