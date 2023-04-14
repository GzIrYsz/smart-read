package fr.cyu.smartread.app.wrappers.deeplearning;

public class PredictedLetter {
    private final char letter;
    private final float percentOfConfidence;

    public PredictedLetter(char letter, float percentOfConfidence) {
        this.letter = letter;
        this.percentOfConfidence = percentOfConfidence;
    }

    public char getLetter() {
        return letter;
    }

    public float getPercentOfConfidence() {
        return percentOfConfidence;
    }
}
