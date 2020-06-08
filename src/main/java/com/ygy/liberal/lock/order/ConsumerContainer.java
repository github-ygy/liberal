package com.ygy.liberal.lock.order;

import com.google.common.collect.Maps;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guoyao
 * @date 2020-04-30
 */
public class ConsumerContainer {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerContainer.class);

    private static final Map<String, CachedMessageOrderConsumer> DEVICE_CONSUMER_CONTAINER = Maps.newHashMap();

    private static final long TIME_OUT_SPAN = 2 * 1000;

    private static final long TRY_CHECK_DESTORY_TIME = 100;

    private static final HashedWheelTimer WHEEL_TIMER = new HashedWheelTimer();

    public static <T> void consumer(String devId, OrderPackage<T> speechSegment) {
        synchronized (devId.intern()) {
            CachedMessageOrderConsumer<T> cachedMessageOrderConsumer = DEVICE_CONSUMER_CONTAINER.get(devId);
            if (cachedMessageOrderConsumer == null) {
                cachedMessageOrderConsumer = new CachedMessageOrderConsumer<>();
                DEVICE_CONSUMER_CONTAINER.put(devId, cachedMessageOrderConsumer);
                timerRemove(devId, TIME_OUT_SPAN);
            }
            cachedMessageOrderConsumer.addAndConsumeMessage(speechSegment);
        }
    }

    private static void timerRemove(String devId, long delayTime) {
        WHEEL_TIMER.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                removeConsumer(devId);
            }
        }, delayTime, TimeUnit.MILLISECONDS);
    }

    private static void removeConsumer(String devId) {
        synchronized (devId.intern()) {
            CachedMessageOrderConsumer cachedMessageOrderConsumer = DEVICE_CONSUMER_CONTAINER.get(devId);
            if (cachedMessageOrderConsumer == null) {
                return;
            }
            if (!cachedMessageOrderConsumer.canDestory(TRY_CHECK_DESTORY_TIME, TIME_OUT_SPAN)) {
                timerRemove(devId, TIME_OUT_SPAN << 1);
                return;
            }
            DEVICE_CONSUMER_CONTAINER.remove(devId);
        }
    }

    public static void main(String[] args) {
        String devId = "123";
        OrderPackage<String> orderPackage3 = new OrderPackage<>(1, 3, OrderStatus.MIDDLE);
        OrderPackage<String> orderPackage2 = new OrderPackage<>(1, 2, OrderStatus.MIDDLE);
        OrderPackage<String> orderPackage4 = new OrderPackage<>(1, 4, OrderStatus.MIDDLE);
        OrderPackage<String> orderPackage5 = new OrderPackage<>(1, 5, OrderStatus.MIDDLE);
        OrderPackage<String> orderPackage1 = new OrderPackage<>(1, 1, OrderStatus.START);
        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (Exception ex) {
            }
            ConsumerContainer.consumer(devId, orderPackage1);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception ex) {
            }
            ConsumerContainer.consumer(devId, orderPackage2);
            new Thread(() -> {
                ConsumerContainer.consumer(devId, orderPackage5);
            }).start();
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
            }
            ConsumerContainer.consumer(devId, orderPackage4);
        }).start();
        ConsumerContainer.consumer(devId, orderPackage3);

        try {
            Thread.sleep(50000000);
        } catch (Exception e) {
        }
    }
}
