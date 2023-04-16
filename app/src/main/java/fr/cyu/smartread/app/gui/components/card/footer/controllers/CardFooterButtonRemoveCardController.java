package fr.cyu.smartread.app.gui.components.card.footer.controllers;

import fr.cyu.smartread.app.gui.components.card.CardModel;
import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;

import java.awt.event.MouseEvent;

public class CardFooterButtonRemoveCardController extends CardFooterButtonController {
    public CardFooterButtonRemoveCardController(CardModel cardModel, CardFooterView view) {
        super(cardModel, view);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("bonjour depuis je te supprime");
        getCardModel().removeCardFromWrapper();
    }
}
