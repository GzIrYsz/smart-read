package fr.cyu.smartread.app.gui.components.cardswrapper.body.controllers;

import fr.cyu.smartread.app.gui.components.cardswrapper.CardsWrapperModel;
import fr.cyu.smartread.app.gui.components.cardswrapper.events.EventDeletingCardUpdate;
import fr.cyu.smartread.app.gui.observer.EventType;
import fr.cyu.smartread.app.gui.observer.Observer;

public class ListenToCardsDeletionController implements Observer {
    private final CardsWrapperModel cardsWrapperModel;

    public ListenToCardsDeletionController(CardsWrapperModel cardsWrapperModel) {
        this.cardsWrapperModel = cardsWrapperModel;
    }

    @Override
    public void update(EventType eventType, Object data) {
        if (eventType.getEventName().equals(EventDeletingCardUpdate.EVENT_NAME)) {
            Integer cardId = (Integer) data;
            cardsWrapperModel.removeCard(cardId);
        }
    }
}
