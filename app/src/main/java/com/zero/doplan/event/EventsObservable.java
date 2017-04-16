package com.zero.doplan.event;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventsObservable {
    private final static String TAG = "EventsObservable";
    private static volatile EventsObservable sNotificationCenter;
    private final static Map<String, ArrayList<EventObserver>> EVENT_OBSERVER_MAPS = new HashMap<>();

    private EventsObservable() {

    }

    public synchronized static EventsObservable getInstance() {
        if (sNotificationCenter == null) {
            sNotificationCenter = new EventsObservable();
        }
        return sNotificationCenter;
    }

    public synchronized void registerObserver(String eventType, EventObserver eventObserver) {
        ArrayList<EventObserver> eventObservers = EVENT_OBSERVER_MAPS.get(eventType);
        if (eventObservers == null) {
            eventObservers = new ArrayList<>();
            EVENT_OBSERVER_MAPS.put(eventType, eventObservers);
        }
        if (eventObservers.contains(eventObserver)) {
            return;
        }
        eventObservers.add(eventObserver);
    }

    public synchronized void unregisterObserver(String eventType, EventObserver eventObserver) {

        if (eventObserver == null) {
            throw new IllegalArgumentException("The observer is null.");
        }

        ArrayList<EventObserver> eventObservers = EVENT_OBSERVER_MAPS.get(eventType);
        if (eventObservers.indexOf(eventObserver) == -1) {
            return;
        }
        eventObservers.remove(eventObserver);
    }


    public synchronized void dispatchEvent(String group, String eventType, Bundle eventArgs) {
        ArrayList<EventObserver> eventObservers = EVENT_OBSERVER_MAPS.get(eventType);
        if (eventObservers != null && !eventObservers.isEmpty()) {
            for (EventObserver eventObserver : eventObservers) {
                if (group.equals(eventObserver.getGroup())) {
                    // 不属于被通知分组的不进行通知
                    eventObserver.dispatchChange(eventType, eventArgs);
                }
            }
        }
    }
}
