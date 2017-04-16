package com.zero.doplan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zero.doplan.event.EventObserver;
import com.zero.doplan.event.EventsObservable;
import com.zero.doplan.event.NotificationCenter;

import java.lang.ref.WeakReference;

/**
 * Created by Allen.D on 17/4/8.
 */

public abstract class BaseObserverFragment extends Fragment {

    private FragmentEventObserver mObserver;
    protected Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mObserver = new FragmentEventObserver(this);
        registerEventObserver(mObserver);

        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
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

    private static class FragmentEventObserver extends EventObserver {
        private final WeakReference<BaseObserverFragment> mFragment;

        public FragmentEventObserver(BaseObserverFragment fragment) {
            super();
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void onChange(String eventType, Bundle eventArgs) {
            BaseObserverFragment fragment = mFragment.get();
            if (fragment != null) {
                fragment.onChange(eventType, eventArgs);
            }
        }

        @Override
        public String getGroup() {
            BaseObserverFragment fragment = mFragment.get();

            if (fragment != null) {
                return fragment.getGroup();
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
