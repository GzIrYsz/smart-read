package fr.cyu.smartread.spellchecking;

import fr.cyu.smartread.spellchecking.dictionary.DictionaryLoaderInterface;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;

import java.util.ArrayList;

public class SpellChecker {
    private final StringMetricsInterface similarityDistanceComputer;
    private final DictionaryLoaderInterface dictionaryLoader;

    public SpellChecker(StringMetricsInterface similarityDistanceComputer, DictionaryLoaderInterface dictionaryLoader) {
        this.similarityDistanceComputer = similarityDistanceComputer;
        this.dictionaryLoader = dictionaryLoader;
    }

    public SpellChecker(DictionaryLoaderInterface dictionaryLoader, StringMetricsInterface similarityDistanceComputer) {
        this(similarityDistanceComputer, dictionaryLoader);
    }

    public ArrayList<WordScore> getSimilarityScore(String source) throws NoDictionarySuitableForThisWordException {
        ArrayList<String> listWords = dictionaryLoader.getAssociatedWordDict(source).getWordList();
        ArrayList<WordScore> listWordsScore = new ArrayList<>();

        for (String word: listWords) {
            float similarityScore = similarityDistanceComputer.computeDistance(source, word);
            listWordsScore.add(new WordScore(word, similarityScore));
        }

        return listWordsScore;
    }
}