package fr.cyu.smartread.spellchecking.corrector;

import fr.cyu.smartread.spellchecking.dictionary.DictionaryLoaderInterface;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;

import java.util.ArrayList;

/**
 * This class represents a spell checker.
 *
 * @author Alexandre
 */
public class SpellChecker {
    private final StringMetricsInterface similarityDistanceComputer;
    private final DictionaryLoaderInterface dictionaryLoader;

    /**
     * The default constructor for a spell checker.
     *
     * @param similarityDistanceComputer A similarity distance computer.
     * @param dictionaryLoader A dictionary loader.
     */
    public SpellChecker(StringMetricsInterface similarityDistanceComputer, DictionaryLoaderInterface dictionaryLoader) {
        this.similarityDistanceComputer = similarityDistanceComputer;
        this.dictionaryLoader = dictionaryLoader;
    }

    /**
     * The default constructor for a spell checker.
     *
     * @param dictionaryLoader A similarity distance computer.
     * @param similarityDistanceComputer A dictionary loader.
     */
    public SpellChecker(DictionaryLoaderInterface dictionaryLoader, StringMetricsInterface similarityDistanceComputer) {
        this(similarityDistanceComputer, dictionaryLoader);
    }

    /**
     * Returns a list of WordScore for the given word and the words in the dictionary.
     *
     * @param source The source word.
     * @return An ArrayList of WordScore objects.
     * @throws NoDictionarySuitableForThisWordException If there's no dictionary suitable for the given word.
     */
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