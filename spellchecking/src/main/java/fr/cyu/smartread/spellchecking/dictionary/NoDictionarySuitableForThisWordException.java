package fr.cyu.smartread.spellchecking.dictionary;

/**
 * This class represents an exception thrown if there's no dictionary suitable for a given word.
 *
 * @author Alexandre
 */
public class NoDictionarySuitableForThisWordException extends Exception {
    /**
     * The default constructor.
     *
     * @param message The detailed message.
     */
    public NoDictionarySuitableForThisWordException(String message) {
        super(message);
    }
}
