package fr.cyu.smartread.spellchecking.corrector;

import fr.cyu.smartread.spellchecking.corrector.SpellChecker;
import fr.cyu.smartread.spellchecking.corrector.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.*;
import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;
import fr.cyu.smartread.spellchecking.stringmetrics.levenshtein.LevenshteinDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpellCheckerTest {

    private StringMetricsInterface similarityDistanceComputer;
    private DictionaryLoaderInterface dictionaryLoader;
    private Dictionary dictionary;
    private SpellChecker spellChecker;
    private final String source = "salut";

    @BeforeEach
     void initAttributes() {
        similarityDistanceComputer = new LevenshteinDistance();
        dictionaryLoader = new DictionaryByNumberOfCharacterLoader();
        dictionary = new DictionaryByNumberOfCharacter(5);
        spellChecker = new SpellChecker(similarityDistanceComputer, dictionaryLoader);
    }

    @Test
    void shouldReturnAnEmptyArrayListIfDictionaryIsEmpty() throws NoDictionarySuitableForThisWordException {
        setNumberOfWordsInLoader(0);
        ArrayList<WordScore> listWordsScore = spellChecker.getSimilarityScore(source);
        assertEquals(listWordsScore.size(), 0);
    }

    @Test
    void shouldReturnOneWordIfDictionaryHasOneWord() throws NoDictionarySuitableForThisWordException {
        setNumberOfWordsInLoader(1);
        ArrayList<WordScore> listWordsScore = spellChecker.getSimilarityScore(source);
        assertEquals(listWordsScore.size(), 1);
    }

    @Test
    void shouldReturnTwoWordIfDictionaryHasTwoWord() throws NoDictionarySuitableForThisWordException {
        setNumberOfWordsInLoader(2);
        ArrayList<WordScore> listWordsScore = spellChecker.getSimilarityScore(source);
        assertEquals(listWordsScore.size(), 2);
    }

    @Test
    void scoreOfWordScoreMayBeEqualThatLevScore() throws NoDictionarySuitableForThisWordException {
        setNumberOfWordsInLoader(5);
        ArrayList<WordScore> listWordsScore = spellChecker.getSimilarityScore(source);
        for (WordScore wordScore: listWordsScore) {
            float levScore = similarityDistanceComputer.computeDistance(source, wordScore.getTarget());
            assertEquals(levScore, wordScore.getScore());
        }
    }

    private void setNumberOfWordsInLoader(int numberOfWords) {
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("accro");
        listWords.add("accru");
        listWords.add("saluc");
        listWords.add("after");
        listWords.add("aigus");

        for (int i = 0; i < numberOfWords; i++) {
            dictionary.addWord(listWords.get(i));
        }

        dictionaryLoader.addDictionary(dictionary);
    }
}
