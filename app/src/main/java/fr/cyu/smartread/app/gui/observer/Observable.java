package fr.cyu.smartread.app.gui.observer;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> observers;

    public Observable(@NotNull ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public Observable() {
        this(new ArrayList<Observer>());
    }

    public void addObserver(@NotNull Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(EventType eventType, Object data) {
        for (Observer observer: observers) {
            observer.update(eventType, data);
        }
    }
}
