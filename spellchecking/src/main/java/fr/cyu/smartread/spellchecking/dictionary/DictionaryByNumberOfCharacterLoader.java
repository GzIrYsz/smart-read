package fr.cyu.smartread.spellchecking.dictionary;

import java.util.HashMap;

public class DictionaryByNumberOfCharacterLoader implements DictionaryLoaderInterface {
    private HashMap<Short, DictionaryByNumberOfCharacter> hmWordByNbCharacters;
    private short minNbCharacters = 0;
    private short maxNbCharacters = 0;

    public DictionaryByNumberOfCharacterLoader() {
        hmWordByNbCharacters = new HashMap<>(0);
    }

    @Override
    public Dictionary getAssociatedWordDict(String word) throws WordNotSupportedException {
        if (word.length() < minNbCharacters || word.length() > maxNbCharacters) {
            throw new WordNotSupportedException(word, "This word length is not supported !");
        }
        return hmWordByNbCharacters.get(word.length());
    }

    @Override
    public DictionaryLoaderInterface addDictionary(Dictionary dict) {
        DictionaryByNumberOfCharacter dictByNbCharacters = (DictionaryByNumberOfCharacter) dict;
        hmWordByNbCharacters.put(dictByNbCharacters.getWordsLength(), dictByNbCharacters);
        updateNbCharacters(dictByNbCharacters);
        return this;
    }

    public DictionaryByNumberOfCharacterLoader updateNbCharacters(DictionaryByNumberOfCharacter dict) {
        if (dict.getWordsLength() < minNbCharacters) {
            minNbCharacters = dict.getWordsLength();
        } else if (dict.getWordsLength() > maxNbCharacters) {
            maxNbCharacters = dict.getWordsLength();
        }
        return this;
    }
}
