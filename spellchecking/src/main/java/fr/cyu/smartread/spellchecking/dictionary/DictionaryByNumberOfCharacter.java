package fr.cyu.smartread.spellchecking.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionaryByNumberOfCharacter extends Dictionary {
    private static final Logger logger = LogManager.getLogger();
    private short wordsLength;

    public DictionaryByNumberOfCharacter(int wordsLength) {
        super();
        setWordsLength((short) wordsLength);
    }

    @Override
    public Dictionary addWord(String word) throws WordNotSupportedException {
        if (word.length() != getWordsLength()) {
            logger.error("The number of characters of the word does not match this dictionary");
            throw new WordNotSupportedException(word, "The number of characters of the word does not match this dictionary");
        }
        return super.addWord(word);
    }

    public short getWordsLength() {
        return this.wordsLength;
    }

    private void setWordsLength(short wordsLength) throws IllegalArgumentException {
        if ((wordsLength < 1) || (wordsLength > 25)) {
            logger.error("wordLength should be between 1 and 25");
            throw new IllegalArgumentException("wordLength should be between 1 and 25 !");
        }
        this.wordsLength = wordsLength;
    }
}
