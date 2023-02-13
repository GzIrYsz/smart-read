package fr.cyu.smartread.spellchecking.dictionary;

public class WordNotSupportedException extends IllegalArgumentException {
    private String word;

    public WordNotSupportedException(String word) {
        this.word = word;
    }

    public WordNotSupportedException(String word, String message) {
        super(message);
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
