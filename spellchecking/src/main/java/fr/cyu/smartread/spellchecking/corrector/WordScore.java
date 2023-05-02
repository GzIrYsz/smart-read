package fr.cyu.smartread.spellchecking.corrector;

/**
 * This class represents the similarity distance for a given word and a target.
 *
 * @author Alexandre
 */
public class WordScore {
    private final String target;
    private final float score;

    /**
     * The default constructor.
     *
     * @param target The target word.
     * @param score The score between the words.
     */
    public WordScore(String target, float score) {
        this.target = target;
        this.score = score;
    }

    /**
     * @return The target word.
     */
    public final String getTarget() {
        return target;
    }

    /**
     * @return The score between the words.
     */
    public final float getScore() {
        return score;
    }

    /**
     * A String representation of the object.
     *
     * @return The String representation of the WordScore.
     */
    @Override
    public String toString() {
        return "WordScore{" +
                "target='" + target + '\'' +
                ", score=" + score +
                '}';
    }
}
