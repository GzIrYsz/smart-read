package fr.cyu.smartread.app.gui.components.cardswrapper.header.controllers;

import fr.cyu.smartread.app.gui.components.cardswrapper.CardsWrapperModel;
import fr.cyu.smartread.app.gui.components.cardswrapper.body.CardsWrapperBodyView;

import java.awt.event.ActionEvent;

public class CardsWrapperHeaderButtonMinusController extends CardsWrapperHeaderButtonController {
    public CardsWrapperHeaderButtonMinusController(CardsWrapperModel wrapperModel, CardsWrapperBodyView wrapperBodyView1) {
        super(wrapperModel, wrapperBodyView1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int cardId = getWrapperModel().getIdFromLastCard();
        getWrapperModel().removeCard(cardId);
    }
}
