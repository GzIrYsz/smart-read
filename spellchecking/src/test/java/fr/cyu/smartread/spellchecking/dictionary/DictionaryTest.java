package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    private Dictionary dict;

    @BeforeEach
    void setUp() {
        dict = new Dictionary();
    }

    @Test
    void testGetEmptyListAtInstantiation() {
        assertEquals(0, dict.getWordList().size());
    }

    @Test
    void testAdd1NewWordsToDict() {
        dict.addWord("test");
        assertEquals(1, dict.getWordList().size());
    }

    @Test
    void testAdd3NewWordsToDict() {
        dict.addWord("bonjour");
        dict.addWord("au revoir");
        dict.addWord("salut");
        assertEquals(3, dict.getWordList().size());
    }

    @Test
    void testPopulateFromCsvFile() {
        assertDoesNotThrow(() -> {
            dict.populateFromFile("src/test/resources/test-dictionary.csv");
        });
        assertEquals(3, dict.getWordList().size());
    }

    @Test
    void testPopulateFromTxtFile() {
        assertDoesNotThrow(() -> {
            dict.populateFromFile("src/test/resources/test-dictionary.txt");
        });
        assertEquals(6, dict.getWordList().size());
    }
}