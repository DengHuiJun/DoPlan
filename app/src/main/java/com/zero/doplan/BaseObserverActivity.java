package com.zero.doplan;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zero.doplan.event.EventObserver;
import com.zero.doplan.event.EventsObservable;
import com.zero.doplan.event.NotificationCenter;

import java.lang.ref.WeakReference;

/**
 * 观察者模式的Activity
 * Created by zero on 17-4-7.
 */

public abstract class BaseObserverActivity extends BaseActionBarActivity {

    private ActivityEventObserver mObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mObserver = new ActivityEventObserver(this);
        registerEventObserver(mObserver);

    }

    @Override
    protected void onDestroy() {
        unregisterEventObserver(mObserver);
        super.onDestroy();
    }

    private void registerEventObserver(EventObserver eventObserver) {
        final String[] observerEventTypes = getObserverEventType();
        if (observerEventTypes != null) {
            final EventsObservable eventsObservable = EventsObservable.getInstance();
            for (String eventType : observerEventTypes) {
                eventsObservable.registerObserver(eventType, eventObserver);
            }
        }
    }

    private void unregisterEventObserver(EventObserver eventObserver) {
        final String[] observerEventTypes = getObserverEventType();
        if (observerEventTypes != null) {
            final EventsObservable eventsObservable = EventsObservable.getInstance();
            for (String eventType : observerEventTypes) {
                eventsObservable.unregisterObserver(eventType, eventObserver);
            }
        }
    }

    private static class ActivityEventObserver extends EventObserver {
        private final WeakReference<BaseObserverActivity> mActivity;

        public ActivityEventObserver(BaseObserverActivity activity) {
            super();
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void onChange(String eventType, Bundle eventArgs) {
            BaseObserverActivity activity = mActivity.get();
            if (activity != null) {
                activity.onChange(eventType, eventArgs);
            }
        }

        @Override
        public String getGroup() {
            BaseObserverActivity activity = mActivity.get();

            if (activity != null) {
                return activity.getGroup();
            }

            return NotificationCenter.DEFAULT_GROUP;
        }
    }

    protected abstract void onChange(String eventType, Bundle eventArgs);

    protected String getGroup() {
        return NotificationCenter.DEFAULT_GROUP;
    }

    /**
     * 实现返回需要观察者监听的业务事件类型
     *
     * @return 该界面所监听的事件类型
     */
    protected abstract String[] getObserverEventType();
}
