package fr.cyu.smartread.app.gui.observer;

public interface Observer {
    void update(EventType eventType, Object data);
}
