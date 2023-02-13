package fr.cyu.smartread.spellchecking.dictionary;

public class WordNotSupportedException extends IllegalArgumentException {
    private String word;

    public WordNotSupportedException(String word) {
        setWord(word);
    }

    public WordNotSupportedException(String word, String message) {
        super(message);
        setWord(word);
    }

    public WordNotSupportedException(String word, Throwable cause) {
        super(cause);
        setWord(word);
    }

    public WordNotSupportedException(String word, String message, Throwable cause) {
        super(message, cause);
        setWord(word);
    }

    public String getWord() {
        return this.word;
    }

    private void setWord(String word) {
        this.word = word;
    }
}
