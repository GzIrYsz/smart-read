package fr.cyu.smartread.spellchecking.dictionary;

public class DictionaryByNumberOfCharacter extends Dictionary {
    private short wordsLength;

    public DictionaryByNumberOfCharacter(short wordsLength) {
        super();
        setWordsLength(wordsLength);
    }

    public short getWordsLength() {
        return this.wordsLength;
    }

    private void setWordsLength(short wordsLength) throws IllegalArgumentException {
        if ((wordsLength < 1) || (wordsLength > 25)) {
            throw new IllegalArgumentException("wordLength should be between 1 and 25 !");
        }
        this.wordsLength = wordsLength;
    }
}
