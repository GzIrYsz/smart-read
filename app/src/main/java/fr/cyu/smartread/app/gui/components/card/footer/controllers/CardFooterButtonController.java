package fr.cyu.smartread.app.gui.components.card.footer.controllers;

import fr.cyu.smartread.app.gui.components.card.CardModel;
import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract public class CardFooterButtonController implements MouseListener {
    private final CardModel cardModel;
    private final CardFooterView view;

    public CardFooterButtonController(CardModel cardModel, CardFooterView view) {
        this.cardModel = cardModel;
        this.view = view;
    }

    @Override
    abstract public void mouseClicked(MouseEvent e);


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public CardFooterView getCardFooterView() {
        return view;
    }
}
