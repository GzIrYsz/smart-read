package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;

import java.util.ArrayList;

public class CardTestUtility {
    public static ArrayList<PredictedLetter> getFakePrediction() {
        ArrayList<PredictedLetter> fakePredictions = new ArrayList<>();
        fakePredictions.add(new PredictedLetter('A', 70.0f));
        fakePredictions.add(new PredictedLetter('T', 20.0f));
        fakePredictions.add(new PredictedLetter('C', 10.0f));

        return fakePredictions;
    }
}
