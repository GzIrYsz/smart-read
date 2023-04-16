package fr.cyu.smartread.app.gui.components.cardswrapper;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.components.cardswrapper.body.CardsWrapperBodyView;
import fr.cyu.smartread.app.gui.components.cardswrapper.header.CardsWrapperHeaderView;
import fr.cyu.smartread.app.gui.components.cardswrapper.header.controllers.CardsWrapperHeaderButtonMinusController;
import fr.cyu.smartread.app.gui.components.cardswrapper.header.controllers.CardsWrapperHeaderButtonPlusController;

import javax.swing.*;
import java.awt.*;

public class CardsWrapperView extends JPanel {
    private final CardsWrapperModel cardsWrapperModel = new CardsWrapperModel();
    private final CardsWrapperHeaderView cardsWrapperHeaderView = new CardsWrapperHeaderView();
    private final CardsWrapperBodyView cardsWrapperBodyView = new CardsWrapperBodyView(cardsWrapperModel);

    public CardsWrapperView() {
        super();
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(alignComponentLeftInPanel(cardsWrapperHeaderView));
        add(cardsWrapperBodyView);

        cardsWrapperModel.addObserver(cardsWrapperBodyView);

        CardsWrapperHeaderButtonPlusController cardsWrapperHeaderButtonPlusController = new CardsWrapperHeaderButtonPlusController(cardsWrapperModel, cardsWrapperBodyView);
        cardsWrapperHeaderView.getButtonPlus().addActionListener(cardsWrapperHeaderButtonPlusController);

        CardsWrapperHeaderButtonMinusController cardsWrapperHeaderButtonMinusController = new CardsWrapperHeaderButtonMinusController(cardsWrapperModel, cardsWrapperBodyView);
        cardsWrapperHeaderView.getButtonMinus().addActionListener(cardsWrapperHeaderButtonMinusController);
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
