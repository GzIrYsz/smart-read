package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.gui.components.card.events.EventStatisticUpdate;
import fr.cyu.smartread.app.gui.observer.Observable;
import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardModel extends Observable {
    static int numberOfInstantiations = 0;
    private final int cardId;
    private ArrayList<PredictedLetter> predictionForLetters;
    private BufferedImage drawingZoneImg;
    private final boolean isBeingDeleted = false;
    public static BufferedImage blankImage;

    public CardModel() {
        super();
        cardId = getCardModelId();
        setDrawingZoneImg(blankImage);
    }

    private void computePredictionLetters() {
       //TODO appeler le modèle ici
    }

    public int getCardId() {
        return cardId;
    }

    private static int getCardModelId() {
       return numberOfInstantiations++; // Return Value and after increment it
    }

    public BufferedImage getDrawingZoneImg() {
        return drawingZoneImg;
    }

    public void setDrawingZoneImg(BufferedImage drawingZoneImg) {
        this.drawingZoneImg = drawingZoneImg;
        //TODO appeller ici le model pour donner de nouvelles prédictions
        computePredictionLetters();
        setPredictionForLetters(CardTestUtility.getFakePrediction());
        updateViewStatistics();
    }

    public void setPredictionForLetters(ArrayList<PredictedLetter> predictionForLetters) {
        this.predictionForLetters = predictionForLetters;
    }

    public ArrayList<PredictedLetter> getPredictionForLetters() {
        return predictionForLetters;
    }

    private void updateViewStatistics() {
        notifyObservers(new EventStatisticUpdate(), getPredictionForLetters());
    }
}
