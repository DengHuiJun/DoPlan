package com.zero.doplan.event;

import android.os.Bundle;


public class NotificationCenter {
    private final static String TAG = "NotificationCenter";

    private final static NotificationCenter INSTANCE = new NotificationCenter();

    public static final String DEFAULT_GROUP = "defaultGroup";

    public static NotificationCenter getInstance() {
        return INSTANCE;
    }

    public void notify(String eventType) {
        notify(DEFAULT_GROUP, eventType, null);
    }

    public void notify(String group, String eventType) {
        notify(group, eventType, null);
    }

    public void notify(String group, String eventType, Bundle eventArgs) {
        EventsType eventsType = EventsType.getInstance();
        EventsObservable eventsObservable = EventsObservable.getInstance();
        if (eventsType.contains(eventType)) {
            group = (group == null ? "" : group);
            eventsObservable.dispatchEvent(group, eventType, eventArgs);
        } else {
            // TODO error
        }
    }
}
