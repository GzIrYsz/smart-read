package fr.cyu.smartread.app.wrappers.spellchecking;

import fr.cyu.smartread.spellchecking.corrector.SpellChecker;
import fr.cyu.smartread.spellchecking.corrector.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacter;
import fr.cyu.smartread.spellchecking.dictionary.DictionaryByNumberOfCharacterLoader;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;
import fr.cyu.smartread.spellchecking.stringmetrics.levenshtein.LevenshteinDistance;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Corrector {
    DictionaryByNumberOfCharacterLoader dictLoader = new DictionaryByNumberOfCharacterLoader();
    StringMetricsInterface stringMetrics = new LevenshteinDistance();
    SpellChecker spellChecker;

    public Corrector() throws IOException {
        populateLoader();
        spellChecker = new SpellChecker(stringMetrics, dictLoader);
    }

    private Corrector populateLoader() throws IOException {
        for (int i = 1; i < 26; i++) {
            dictLoader.addDictionary(new DictionaryByNumberOfCharacter(i).populateFromFile("src/main/resources/dictionaries/mots_" + i + ".csv"));
        }
        return this;
    }

    public ArrayList<WordScore> getCorrections(String word) throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> wordSCoreList = spellChecker.getSimilarityScore(word);
        ArrayList<WordScore> corrections = new ArrayList<>();
        for (WordScore wordScore : wordSCoreList) {
            if (isSuitableCorrection(word, wordScore)) {
                corrections.add(wordScore);
            }
        }
        return corrections;
    }

    private boolean isSuitableCorrection(String word, WordScore wordScore) {
        return wordScore.getScore() < (word.length() / 2);
    }
}
