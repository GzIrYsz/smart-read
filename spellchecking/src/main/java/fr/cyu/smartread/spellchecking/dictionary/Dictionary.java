package fr.cyu.smartread.spellchecking.dictionary;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> wordList;

    public Dictionary() {
        wordList = new ArrayList<>(0);
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public Dictionary addWord(String word) {
        wordList.add(word);
        return this;
    }
}
