package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.gui.components.card.events.EventStatisticUpdate;
import fr.cyu.smartread.app.gui.components.cardswrapper.events.EventDeletingCardUpdate;
import fr.cyu.smartread.app.gui.observer.Observable;
import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static fr.cyu.smartread.app.gui.components.card.body.CardBodyView.blankImg;

public class CardModel extends Observable {
    private static int numberOfInstantiations = 0;
    private final int cardId;
    private ArrayList<PredictedLetter> predictionForLetters;
    private BufferedImage drawingZoneImg;

    public CardModel() {
        super();
        cardId = getCardModelId();
        setDrawingZoneImg(blankImg);
    }

    private void computePredictionLetters() {
       //TODO appeler le modèle ici
    }

    public void setDrawingZoneImg(BufferedImage drawingZoneImg) {
        this.drawingZoneImg = drawingZoneImg;
        //TODO appeller ici le model pour donner de nouvelles prédictions
        computePredictionLetters();
        setPredictionForLetters(CardTestUtility.getFakePrediction());
        updateViewStatistics();
    }

    public void removeCardFromWrapper() {
        notifyObservers(new EventDeletingCardUpdate(), getCardId());
    }

    public Integer getCardId() {
        return cardId;
    }

    private static int getCardModelId() {
       return numberOfInstantiations++; // Return Value and after increment it
    }

    public BufferedImage getDrawingZoneImg() {
        return drawingZoneImg;
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
