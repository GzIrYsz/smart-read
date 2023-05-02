package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.gui.components.card.events.EventStatisticUpdate;
import fr.cyu.smartread.app.gui.components.cardswrapper.events.EventDeletingCardUpdate;
import fr.cyu.smartread.app.gui.observer.Observable;
import fr.cyu.smartread.app.wrappers.deeplearning.OCRDetector;
import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static fr.cyu.smartread.app.gui.components.card.body.CardBodyView.blankImg;

public class CardModel extends Observable {
    private static int numberOfInstantiations = 0;
    private final int cardId;
    private ArrayList<PredictedLetter> predictionForLetters;
    private BufferedImage drawingZoneImg;
    private final OCRDetector ocrDetector;

    public CardModel() {
        super();
        cardId = getCardModelId();
        setDrawingZoneImg(blankImg);
        try {
            ocrDetector = OCRDetector.getDetector();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<PredictedLetter> computePredictionLetters() {
       return ocrDetector.detect(drawingZoneImg);
    }

    public void setDrawingZoneImg(BufferedImage drawingZoneImg) {
        this.drawingZoneImg = drawingZoneImg;
        ArrayList<PredictedLetter> predictedLetters = computePredictionLetters();
        setPredictionForLetters(predictedLetters);
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
