package fr.cyu.smartread.spellchecking;

import fr.cyu.smartread.spellchecking.stringmetrics.InterfaceStringMetrics;

import java.util.ArrayList;

public class SpellChecker {
    private final InterfaceStringMetrics similarityDistanceComputer;

    public SpellChecker(InterfaceStringMetrics similarityDistanceComputer) {
        this.similarityDistanceComputer = similarityDistanceComputer;
    }

    public ArrayList<WordScore> computeSimilarityDistance(String source, ArrayList<String> listWords) {
        ArrayList<WordScore> listWordsScore = new ArrayList<>();

        for (String word: listWords) {
            float similarityDistanceScore = similarityDistanceComputer.computeDistance(source, word);
            WordScore wordScore = new WordScore(word, similarityDistanceScore);
            listWordsScore.add(wordScore);
        }
        return listWordsScore;
    }
}