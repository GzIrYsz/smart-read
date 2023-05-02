package fr.cyu.smartread.spellchecking.dictionary;

/**
 * This class represents an exception thrown if a word is not supported.
 *
 * @author Thomas REMY
 */
public class WordNotSupportedException extends IllegalArgumentException {
    private String word;

    /**
     * One of the constructors available.
     *
     * @param word The unsupported word.
     */
    public WordNotSupportedException(String word) {
        setWord(word);
    }

    /**
     * One of the constructors available.
     *
     * @param word The unsupported word.
     * @param message The detailed message.
     */
    public WordNotSupportedException(String word, String message) {
        super(message);
        setWord(word);
    }

    /**
     * One of the constructors available.
     *
     * @param word The unsupported word.
     * @param cause The cause.
     */
    public WordNotSupportedException(String word, Throwable cause) {
        super(cause);
        setWord(word);
    }

    /**
     * One of the constructors available.
     *
     * @param word The unsupported word.
     * @param message The detailed message.
     * @param cause The cause.
     */
    public WordNotSupportedException(String word, String message, Throwable cause) {
        super(message, cause);
        setWord(word);
    }

    /**
     * @return The unsupported word.
     */
    public String getWord() {
        return this.word;
    }

    private void setWord(String word) {
        this.word = word;
    }
}
