package fr.cyu.smartread.spellchecking.dictionary;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> wordList;
    private ArrayList<String> acceptedFiletypes = new ArrayList<>();

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

    public Dictionary populateFromFile(String path) throws FiletypeNotSupportedException, IOException {
        File file = new File(path);
        String filetype = Files.probeContentType(file.toPath());
        acceptedFiletypes.add("application/vnd.ms-excel");
        if (!acceptedFiletypes.contains(filetype)) {
            throw new FiletypeNotSupportedException(file);
        }
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
