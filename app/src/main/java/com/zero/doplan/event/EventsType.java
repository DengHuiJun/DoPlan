package com.zero.doplan.event;

import java.util.HashSet;
import java.util.Set;

public class EventsType {

    private static volatile EventsType sInstance;
    private static final Set<String> eventsTypeSet = new HashSet<>();

    private static final String PKG = "com.zero.";

    public final static String REFRESH_DATA_ADD_EVENT = PKG + "refreshData";

    /**
     * !!! 所有系统的事件都在此加入事件列表
     */
    private EventsType() {
        eventsTypeSet.add(REFRESH_DATA_ADD_EVENT);
    }

    public static EventsType getsInstance() {
        if (sInstance == null) {
            synchronized (EventsType.class) {
                if (sInstance == null) {
                    sInstance = new EventsType();
                }
            }
        }
        return sInstance;
    }

    public boolean contains(String eventType) {
        return eventsTypeSet.contains(eventType);
    }
}
