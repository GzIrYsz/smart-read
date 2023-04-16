package fr.cyu.smartread.app.gui.components.cardswrapper;

import fr.cyu.smartread.app.gui.components.cardswrapper.events.EventDeletingCardUpdate;
import fr.cyu.smartread.app.gui.observer.Observable;
import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.NoSuchElementException;

public class CardsWrapperModel extends Observable {
    private final LinkedMap<Integer, PredictedLetter> recognizedWord = new LinkedMap<>();
    private final static PredictedLetter DEFAULT_PREDICTION = new PredictedLetter('A', 0);

    public CardsWrapperModel() {

    }

    public void addCard(int cardId) {
        recognizedWord.put(cardId, DEFAULT_PREDICTION);
    }

    public void removeCard(Integer cardId) throws NoSuchElementException {
        System.out.println(cardId);
        recognizedWord.remove(cardId);
        updateWrapperViewToDeletingCard(cardId);
    }

    public int getIdFromLastCard() {
        return recognizedWord.lastKey();
    }

    private void updateWrapperViewToDeletingCard(Integer cardId) {
        notifyObservers(new EventDeletingCardUpdate(), cardId);
    }


}
