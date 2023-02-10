package fr.cyu.smartread.spellchecking;

import fr.cyu.smartread.spellchecking.stringmetrics.InterfaceStringMetrics;
import fr.cyu.smartread.spellchecking.stringmetrics.LevenshteinDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSpellChecker {

    private InterfaceStringMetrics similarityDistanceComputer;
    private SpellChecker spellChecker;

    @BeforeEach
    void initAttributes() {
        similarityDistanceComputer = new LevenshteinDistance();
        spellChecker = new SpellChecker(similarityDistanceComputer);
    }

    @Test
    void shouldReturnAnEmptyArrayIfListWordsIsEmpty() {
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        assertEquals(0, listWordsScore.size());
    }

    @Test
    void shouldReturnOneWordScoreInArrayIfListWordsHasOneWord() {
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("mot 1");
        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        assertEquals(1, listWordsScore.size());
    }

    @Test
    void shouldReturnTwoWordScoreInArrayIfListWordsHasTwoWords() {
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("mot 1");
        listWords.add("mot 2");
        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        assertEquals(2, listWordsScore.size());
    }

    @Test
    void shouldReturnThreeWordScoreInArrayIfListWordsHasThreeWords() {
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("mot 1");
        listWords.add("mot 2");
        listWords.add("mot 3");
        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        assertEquals(3, listWordsScore.size());
    }

    @Test
    void shouldHaveEqualsScoreBetweenArrayAndStartWords(){
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("mot 1");
        listWords.add("mot 2");
        listWords.add("mot 3");

        float[] tabScores = new float[3];
        tabScores[0] = similarityDistanceComputer.computeDistance(source, listWords.get(0));
        tabScores[1] = similarityDistanceComputer.computeDistance(source, listWords.get(1));
        tabScores[2] = similarityDistanceComputer.computeDistance(source, listWords.get(2));

        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        for (int i = 0; i < 3; i++) {
            assertEquals(tabScores[i], listWordsScore.get(i).getScore());
            assertEquals(tabScores[i], listWordsScore.get(i).getScore());
        }
    }

    @Test
    void shouldHaveEqualsStrBetweenArrayTargetsAndListWords(){
        String source = "Bonjour";
        ArrayList<String> listWords = new ArrayList<>();
        listWords.add("mot 1");
        listWords.add("mot 2");
        listWords.add("mot 3");

        float[] tabScores = new float[3];
        tabScores[0] = similarityDistanceComputer.computeDistance(source, listWords.get(0));
        tabScores[1] = similarityDistanceComputer.computeDistance(source, listWords.get(1));
        tabScores[2] = similarityDistanceComputer.computeDistance(source, listWords.get(2));

        ArrayList<WordScore> listWordsScore = spellChecker.computeSimilarityDistance(source, listWords);

        for (int i = 0; i < 3; i++) {
            assertEquals(listWords.get(i), listWordsScore.get(i).getTarget());
        }
    }
}
