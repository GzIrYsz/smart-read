package fr.cyu.smartread.app.gui.components.card.footer;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.card.footer.components.CardFooterButton;

import javax.swing.*;
import java.awt.*;

public class CardFooterView extends JPanel {
    private CardFooterButton clearCardButton;
    private CardFooterButton statsButton;
    private CardFooterButton removeCardButton;

    public CardFooterView() {
        super(new FlowLayout(FlowLayout.CENTER, 40, 0));
        init();
    }

    protected void init() {
        clearCardButton = new CardFooterButton("app/src/main/resources/icons/icon-effacer.png");
        statsButton = new CardFooterButton("app/src/main/resources/icons/icon-effacer.png");
        removeCardButton = new CardFooterButton("app/src/main/resources/icons/icon-effacer.png");

        add(clearCardButton);
        add(statsButton);
        add(removeCardButton);

    }

    public CardFooterButton getClearCardButton() {
        return clearCardButton;
    }

    public CardFooterButton getStatsButton() {
        return statsButton;
    }

    public CardFooterButton getRemoveCardButton() {
        return removeCardButton;
    }

    public static void main(String[] args) {
        GUITestUtility.launchTest(new CardFooterView());
    }
}