package fr.cyu.smartread.app.gui.components.cardswrapper.header;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.cardswrapper.header.components.CardWrapperButton;

import javax.swing.*;
import java.awt.FlowLayout;

public class CardsWrapperHeaderView extends JPanel {
    private final CardWrapperButton buttonPlus = new CardWrapperButton(new ImageIcon("app/src/main/resources/icons/icon-plus.png"));
    private final CardWrapperButton buttonMinus = new CardWrapperButton(new ImageIcon("app/src/main/resources/icons/icon-minus.png"));
    public CardsWrapperHeaderView() {
        super(new FlowLayout());
        add(buttonPlus);
        add(buttonMinus);
    }

    public CardWrapperButton getButtonPlus() {
        return buttonPlus;
    }

    public CardWrapperButton getButtonMinus() {
        return buttonMinus;
    }

    public static void main(String[] args) {
        CardsWrapperHeaderView wrapperButton = new CardsWrapperHeaderView();
        GUITestUtility.launchTest(wrapperButton);
    }
}

