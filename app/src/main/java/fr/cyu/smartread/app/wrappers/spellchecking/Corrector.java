package fr.cyu.smartread.app.wrappers.spellchecking;

import fr.cyu.smartread.spellchecking.SpellChecker;
import fr.cyu.smartread.spellchecking.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacter;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacterLoader;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;
import fr.cyu.smartread.spellchecking.stringmetrics.levenshtein.LevenshteinDistance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Corrector {
    DictionaryByNumberOfCharacterLoader dictLoader = new DictionaryByNumberOfCharacterLoader();
    StringMetricsInterface stringMetrics = new LevenshteinDistance();
    SpellChecker spellChecker;

    public Corrector() throws IOException {
        populateLoader();
        spellChecker = new SpellChecker(stringMetrics, dictLoader);
    }

    private Corrector populateLoader() throws IOException {
        for (int i = 1; i < 11; i++) {
            dictLoader.addDictionary(new DictionaryByNumberOfCharacter(i).populateFromFile("src/main/resources/mots_" + i + ".csv"));
        }
        return this;
    }

    public ArrayList<WordScore> getCorrections(String word) throws NoDictionarySuitableForThisWordException {
        return spellChecker.getSimilarityScore(word);
    }
}
