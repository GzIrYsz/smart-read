package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    private Dictionary dict;

    @BeforeEach
    void setUp() {
        dict = new Dictionary();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetEmptyListAtInstantiation() {
        assertEquals(0, dict.getWordList().size());
    }

    @Test
    void testAddNewStringToList() {
        dict.addWord("test");
        assertEquals(1, dict.getWordList().size());
    }
}