package fr.cyu.smartread.app.gui.components.card.events;

import fr.cyu.smartread.app.gui.observer.EventType;

public class EventStatisticUpdate extends EventType {
    public static final String EVENT_NAME = "EVENT_STATISTICS_UPDATE";
    public EventStatisticUpdate() {
        setEventName(EVENT_NAME);
    }
}
