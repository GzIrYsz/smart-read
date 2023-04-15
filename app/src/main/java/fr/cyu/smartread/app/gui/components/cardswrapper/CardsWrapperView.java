package fr.cyu.smartread.app.gui.components.cardswrapper;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.cardswrapper.body.CardsWrapperBodyView;
import fr.cyu.smartread.app.gui.components.cardswrapper.header.CardsWrapperHeaderView;

import javax.swing.*;
import java.awt.*;

public class CardsWrapperView extends JPanel {
    private final CardsWrapperHeaderView cardsWrapperHeaderView = new CardsWrapperHeaderView();
    private final CardsWrapperBodyView cardsWrapperBodyView = new CardsWrapperBodyView();

    public CardsWrapperView() {
        super();
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(alignComponentLeftInPanel(new CardsWrapperHeaderView()));
        add(new CardsWrapperBodyView());
    }

    private JPanel alignComponentLeftInPanel(Component component) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jPanel.add(component);

        return jPanel;
    }

    public static void main(String[] args) {
        GUITestUtility.launchTest(new CardsWrapperView());
    }
}
