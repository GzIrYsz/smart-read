package fr.cyu.smartread.app.wrappers.spellchecking;

import fr.cyu.smartread.spellchecking.SpellChecker;
import fr.cyu.smartread.spellchecking.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacter;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacterLoader;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;
import fr.cyu.smartread.spellchecking.stringmetrics.levenshtein.LevenshteinDistance;

import java.util.ArrayList;

public class Corrector {
    DictionaryByNumberOfCharacter dict1Char = new DictionaryByNumberOfCharacter(1);
    DictionaryByNumberOfCharacter dict2Chars  = new DictionaryByNumberOfCharacter(2);
    DictionaryByNumberOfCharacter dict3Chars  = new DictionaryByNumberOfCharacter(3);
    DictionaryByNumberOfCharacter dict4Chars  = new DictionaryByNumberOfCharacter(4);
    DictionaryByNumberOfCharacter dict5Chars  = new DictionaryByNumberOfCharacter(5);
    DictionaryByNumberOfCharacter dict6Chars  = new DictionaryByNumberOfCharacter(6);
    DictionaryByNumberOfCharacter dict7Chars  = new DictionaryByNumberOfCharacter(7);
    DictionaryByNumberOfCharacter dict8Chars  = new DictionaryByNumberOfCharacter(8);
    DictionaryByNumberOfCharacter dict9Chars  = new DictionaryByNumberOfCharacter(9);
    DictionaryByNumberOfCharacter dict10Chars  = new DictionaryByNumberOfCharacter(10);
    DictionaryByNumberOfCharacterLoader dictLoader = new DictionaryByNumberOfCharacterLoader();
    StringMetricsInterface stringMetrics = new LevenshteinDistance();
    SpellChecker spellChecker;

    public Corrector() {
        fillLoader();
        spellChecker = new SpellChecker(stringMetrics, dictLoader);
    }

    private Corrector fillLoader() {
        dictLoader.addDictionary(dict1Char);
        return this;
    }

    public ArrayList<WordScore> getCorrections(String word) throws NoDictionarySuitableForThisWordException {
        return spellChecker.getSimilarityScore(word);
    }
}
