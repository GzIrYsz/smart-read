package fr.cyu.smartread.spellchecking.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a disctionary.
 *
 * @author Thomas REMY
 */
public class Dictionary {
    private final ArrayList<String> wordList;

    /**
     * The default constructor for a dictionary.
     */
    public Dictionary() {
        wordList = new ArrayList<>(0);
    }

    /**
     * @return The word list.
     */
    public ArrayList<String> getWordList() {
        return wordList;
    }

    /**
     * Add a word to the dictionary.
     *
     * @param word The word to add.
     * @return The Dictionary object itself.
     */
    public Dictionary addWord(String word) {
        wordList.add(word);
        return this;
    }

    /**
     * Populate a dictionary from a file.
     *
     * @param path The path to the file.
     * @return The Dictionary object itself.
     * @throws IOException If bad things happens during the read.
     */
    public Dictionary populateFromFile(String path) throws IOException {
        File file = new File(path);
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            addWord(line);
        }
        br.close();
        fr.close();
        return this;
    }
}
