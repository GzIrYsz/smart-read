package fr.cyu.smartread.spellchecking.dictionary;

public class DictionaryByNumberOfCharacter extends Dictionary {
    private short wordsLength;

    public DictionaryByNumberOfCharacter(int wordsLength) {
        super();
        setWordsLength((short) wordsLength);
    }

    @Override
    public Dictionary addWord(String word) throws WordNotSupportedException {
        if (word.length() != getWordsLength())
            throw new WordNotSupportedException(word, "The number of characters of the word does not match this dictionary");
        return super.addWord(word);
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
