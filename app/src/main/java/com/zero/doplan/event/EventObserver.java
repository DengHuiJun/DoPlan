package com.zero.doplan.event;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


/**
 * Receives call backs for changes to event occur. Must be implemented by objects which are added
 * to a notificationCenter.
 */
public abstract class EventObserver {
    private Handler mHandler;

    public EventObserver() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    /***
     * 观察者要实现的方法 用于在事件通知的时候做一些事情
     * <p>改方法是在Main Thread里执行的，对于长时间的操作要注意使用工作线程
     * @param eventType 通知事件类型
     */
    public abstract void onChange(String eventType, Bundle eventArgs);

    /**
     * 获取该观察者所属的分组信息。
     * 主要用于对观察者进行分组，这样进行通知的时候就可以知道该观察者是否应该被通知。
     *
     * @return 该观察者所属的分组信息
     */
    public abstract String getGroup();

    public final void dispatchChange(String eventType, Bundle eventArgs) {
        mHandler.post(new NotificationRunnable(eventType, eventArgs));
    }

    private final class NotificationRunnable implements Runnable {
        private String eventType;
        private Bundle eventArgs;

        public NotificationRunnable(String eventType, Bundle eventArgs) {
            this.eventType = eventType;
            this.eventArgs = eventArgs;
        }

        @Override
        public void run() {
            EventObserver.this.onChange(eventType, eventArgs);
        }
    }
}

