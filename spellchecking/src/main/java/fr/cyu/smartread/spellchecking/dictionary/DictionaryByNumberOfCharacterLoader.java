package fr.cyu.smartread.spellchecking.dictionary;

import java.util.HashMap;

public class DictionaryByNumberOfCharacterLoader implements DictionaryLoaderInterface {
    private final HashMap<Short, DictionaryByNumberOfCharacter> hmWordByNbCharacters;
    private short minNbCharacters = 0;
    private short maxNbCharacters = 0;

    public DictionaryByNumberOfCharacterLoader() {
        hmWordByNbCharacters = new HashMap<>(0);
    }

    @Override
    public Dictionary getAssociatedWordDict(String word) throws WordNotSupportedException, NoDictionarySuitableForThisWordException {
        if (word.length() < minNbCharacters || word.length() > maxNbCharacters) {
            throw new WordNotSupportedException(word, "This word length is not supported !");
        }

        if (!hasDictForThisWord(word))
            throw new NoDictionarySuitableForThisWordException("This loader don't have any dictionary for words of " + word.length() + " characters");
        return hmWordByNbCharacters.get((short) word.length());
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

    private boolean hasDictForThisWord(String word) {
        return hmWordByNbCharacters.containsKey((short) word.length());
    }
}
