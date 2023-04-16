package fr.cyu.smartread.app.gui.components.cardswrapper.header.controllers;

import fr.cyu.smartread.app.gui.components.cardswrapper.CardsWrapperModel;
import fr.cyu.smartread.app.gui.components.cardswrapper.body.CardsWrapperBodyView;

import java.awt.event.ActionEvent;

public class CardsWrapperHeaderButtonPlusController extends CardsWrapperHeaderButtonController {
    public CardsWrapperHeaderButtonPlusController(CardsWrapperModel wrapperModel, CardsWrapperBodyView wrapperBodyView1) {
        super(wrapperModel, wrapperBodyView1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Bonjour");
        int cardId = getWrapperBodyView().addCard();
        getWrapperModel().addCard(cardId);
    }
}
