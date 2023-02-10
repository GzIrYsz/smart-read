package fr.cyu.smartread.spellchecking;

public class WordScore {
    private final String target;
    private final float score;

    public WordScore(String target, float score) {
        this.target = target;
        this.score = score;
    }

    public final String getTarget() {
        return target;
    }

    public final float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "WordScore{" +
                "target='" + target + '\'' +
                ", score=" + score +
                '}';
    }
}
