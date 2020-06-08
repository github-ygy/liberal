package com.ygy.liberal.lock;

import com.google.common.base.Preconditions;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author guoyao
 * @date 2020-04-25
 */
public class CustomerLock {

    private static class LockSync extends AbstractQueuedSynchronizer {
        public void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }

        public void unlock() {
            release(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            //获取状态
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                if (getExclusiveOwnerThread() == Thread.currentThread()) {
                    Preconditions.checkArgument(state + arg > 0, "状态需要大于0");
                    setState(state + arg);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();
            int now = state - arg;
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalStateException();
            }
            Preconditions.checkArgument(now >= 0, "状态需要大于0");
            if (now == 0) {
                setState(0);
                setExclusiveOwnerThread(null);
                return true;
            }
            setState(now);
            return false;
        }
    }

    private static class SemaphoreSync extends AbstractQueuedSynchronizer {

        private SemaphoreSync(int accessCount) {
            setState(accessCount);
        }

        /**
         * 1.获取资源数,如果资源数小于0则需要进入队列中获取资源
         * 2.如果资源数大于0并获取成功,则陈宫
         * 3.如果获取失败,进入队列等待获取资源
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            for ( ; ; ) {
                int currentAccessCount = getState();
                int canAccessCount = currentAccessCount - arg;
                if (canAccessCount < 0) {
                    return canAccessCount;
                }
                if (compareAndSetState(currentAccessCount, canAccessCount)) {
                    return canAccessCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int state = getState();
                int canAccessCount = getState() + arg;
                if (canAccessCount < state) {
                    throw new IllegalStateException();
                }
                if (compareAndSetState(state, canAccessCount)) {
                    return true;
                }
            }
        }
    }
}
