package fr.cyu.smartread.app.gui.components.cardswrapper.body;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.card.CardView;

import javax.swing.*;
import java.awt.*;

public class CardsWrapperBodyView extends JScrollPane {
    private static final int NUMBER_OF_CARD_AT_START = 5;
    private final JPanel mainPanel = new JPanel(new FlowLayout());
    public CardsWrapperBodyView() {
        super();
        setViewportView(mainPanel);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        initCards();
    }

    public void addCard() {
        mainPanel.add(new CardView());
    }

    private void initCards() {
        for (int i = 0; i < NUMBER_OF_CARD_AT_START; i++) {
            addCard();
        }
    }

    public static void main(String[] args) {
        CardsWrapperBodyView cardsWrapperBodyView = new CardsWrapperBodyView();
        GUITestUtility.launchTest(cardsWrapperBodyView);
    }
}
