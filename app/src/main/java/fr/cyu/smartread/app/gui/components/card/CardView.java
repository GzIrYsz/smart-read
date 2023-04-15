package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.card.body.CardBodyController;
import fr.cyu.smartread.app.gui.components.card.body.CardBodyView;
import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;
import fr.cyu.smartread.app.gui.components.card.footer.controllers.CardFooterButtonCloseController;
import fr.cyu.smartread.app.gui.components.card.header.CardHeaderView;

import javax.swing.*;
import java.awt.*;

public class CardView extends JPanel {
    private int cardId;
    private CardHeaderView cardHeaderView;
    private CardBodyView cardBodyView;
    private CardFooterView cardFooterView;
    private final static Dimension preferredSize = new Dimension(270, 370);

    public CardView() {
        super();
        init();

        getCardHeaderView().getStat1().setText("J = 35%");
        getCardHeaderView().getStat2().setText("I = 15%");
        getCardHeaderView().getStat3().setText("L = 3%");
    }

    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(preferredSize);
        CardModel cardModel = new CardModel();
        cardId = cardModel.getCardId();

        cardHeaderView = new CardHeaderView();
        cardBodyView = new CardBodyView();
        cardFooterView = new CardFooterView();

        cardModel.addObserver(cardHeaderView);
        CardBodyController cardBodyController = new CardBodyController(cardModel, cardBodyView);
        cardBodyView.addMouseListener(cardBodyController);
        cardBodyView.addMouseMotionListener(cardBodyController);

        cardFooterView.getClearCardButton().addMouseListener(new CardFooterButtonCloseController(cardModel, cardFooterView, cardBodyView));

        add(cardHeaderView);
        add(cardBodyView);
        add(cardFooterView);
    }

    public int getCardId() {
        return cardId;
    }

    public CardHeaderView getCardHeaderView() {
        return cardHeaderView;
    }

    public CardBodyView getCardBodyView() {
        return cardBodyView;
    }

    public CardFooterView getCardFooterView() {
        return cardFooterView;
    }

    public static void main(String[] args) {
        GUITestUtility.launchTest(new CardView());
    }
}
