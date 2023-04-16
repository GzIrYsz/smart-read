package fr.cyu.smartread.app.gui.components.cardswrapper.events;

import fr.cyu.smartread.app.gui.observer.EventType;

public class EventDeletingCardUpdate extends EventType {
    public static final String EVENT_NAME = "EVENT_DELETING_CARD";

    public EventDeletingCardUpdate() {
        setEventName(EVENT_NAME);
    }
}
