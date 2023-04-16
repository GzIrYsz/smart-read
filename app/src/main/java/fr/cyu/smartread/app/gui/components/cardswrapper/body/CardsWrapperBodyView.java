package fr.cyu.smartread.app.gui.components.cardswrapper.body;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.card.CardView;
import fr.cyu.smartread.app.gui.components.cardswrapper.events.EventDeletingCardUpdate;
import fr.cyu.smartread.app.gui.observer.EventType;
import fr.cyu.smartread.app.gui.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class CardsWrapperBodyView extends JScrollPane implements Observer {
    private static final int NUMBER_OF_CARD_AT_START = 2;
    private final JPanel mainPanel = new JPanel(new FlowLayout());
    private final LinkedHashMap<Integer, CardView> cardsLinkedHashMap = new LinkedHashMap<>();
    public CardsWrapperBodyView() {
        super();
        setViewportView(mainPanel);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        initCards();
    }

    public int addCard() {
        CardView cardView = new CardView();
        int cardId =  cardView.getCardId();

        cardsLinkedHashMap.put(cardId, cardView);
        mainPanel.add(cardView);

        updateView();

        return cardId;
    }

    @Override
    public void update(EventType eventType, Object data) {
        if (eventType.getEventName().equals(EventDeletingCardUpdate.EVENT_NAME)) {
            Integer cardId = (Integer) data;
            removeCard(cardId);
        }
    }

    private void initCards() {
        for (int i = 0; i < NUMBER_OF_CARD_AT_START; i++) {
            addCard();
        }
    }

    private void removeCard(Integer cardId) {
        CardView cardsToRemove = cardsLinkedHashMap.get(cardId);
        mainPanel.remove(cardsToRemove);
        cardsLinkedHashMap.remove(cardId);
        updateView();
    }

    private void updateView() {
        revalidate();
        repaint();
        placeCursorAtToNewWidget();
    }

    private void placeCursorAtToNewWidget() {
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }

    public static void main(String[] args) {
        CardsWrapperBodyView cardsWrapperBodyView = new CardsWrapperBodyView();
        GUITestUtility.launchTest(cardsWrapperBodyView);
    }

}
