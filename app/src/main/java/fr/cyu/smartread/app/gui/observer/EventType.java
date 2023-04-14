package fr.cyu.smartread.app.gui.observer;

abstract public class EventType {
     String eventName;

    protected void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
