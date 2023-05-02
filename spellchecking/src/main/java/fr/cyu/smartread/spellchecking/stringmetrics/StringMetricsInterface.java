package fr.cyu.smartread.spellchecking.stringmetrics;

/**
 * Interface for different type of similarity measurements.
 *
 * @author Alexandre
 */
public interface StringMetricsInterface {
    /**
     * Compute the distance between the two words.
     *
     * @param source The source word.
     * @param target The target word.
     * @return The distance between the two words.
     */
    float computeDistance(String source, String target);
}
