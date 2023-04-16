package fr.cyu.smartread.app.gui.components.cardswrapper.header.controllers;

import fr.cyu.smartread.app.gui.components.cardswrapper.CardsWrapperModel;
import fr.cyu.smartread.app.gui.components.cardswrapper.body.CardsWrapperBodyView;

import java.awt.event.ActionListener;

public abstract class CardsWrapperHeaderButtonController implements ActionListener {
    private final CardsWrapperModel wrapperModel;
    private final CardsWrapperBodyView wrapperBodyView;

    public CardsWrapperHeaderButtonController(CardsWrapperModel wrapperModel, CardsWrapperBodyView wrapperBodyView1) {
        this.wrapperModel = wrapperModel;
        this.wrapperBodyView = wrapperBodyView1;
    }

    public CardsWrapperModel getWrapperModel() {
        return wrapperModel;
    }

    public CardsWrapperBodyView getWrapperBodyView() {
        return wrapperBodyView;
    }
}
