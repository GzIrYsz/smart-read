package fr.cyu.smartread.app.gui.components.card.footer.controllers;

import fr.cyu.smartread.app.gui.components.card.CardModel;
import fr.cyu.smartread.app.gui.components.card.body.CardBodyView;
import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;

import java.awt.event.MouseEvent;

public class CardFooterButtonCloseController extends CardFooterButtonController{
    private final CardBodyView cardBodyView;
    public CardFooterButtonCloseController(CardModel cardModel, CardFooterView cardFooterView, CardBodyView cardBodyView) {
        super(cardModel, cardFooterView);
        this.cardBodyView = cardBodyView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cardBodyView.clearDrawingZone();
        getCardModel().setDrawingZoneImg(cardBodyView.getBufferedImage());
    }
}
