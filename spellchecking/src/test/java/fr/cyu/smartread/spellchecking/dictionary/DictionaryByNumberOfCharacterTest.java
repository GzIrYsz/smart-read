package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryByNumberOfCharacterTest {
    DictionaryByNumberOfCharacter dict;

    @BeforeEach
    public void setUp() {
        dict = new DictionaryByNumberOfCharacter((short) 5);
    }

    @Test
    public void testInstantiateWithCorrectWordLength() {
        assertEquals(5, dict.getWordsLength());

        dict = new DictionaryByNumberOfCharacter((short) 25);
        assertEquals(25, dict.getWordsLength());
    }

    @Test
    public void testInstantiateWithIncorrectWordLength() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DictionaryByNumberOfCharacter((short) 0);
        });

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DictionaryByNumberOfCharacter((short) 26);
        });
    }

    @Test
    public void testAddCorrectWord() {
        assertDoesNotThrow(() -> {
            dict.addWord("aboie");
        });

        assertDoesNotThrow(() -> {
            dict.addWord("aider");
        });
    }

    @Test
    public void testAddIncorrectWord() {
        assertThrowsExactly(WordNotSupportedException.class, () -> {
            dict.addWord("abc");
        });

        assertThrowsExactly(WordNotSupportedException.class, () -> {
            dict.addWord("abaissa");
        });
    }

    @Test
    public void testPopulateWithCorrectFile() {
        assertDoesNotThrow(() -> {
            dict.populateFromFile("src/test/resources/5-chars-dictionary-test.csv");
        });

        assertEquals(15, dict.getWordList().size());
    }

    @Test
    public void testPopulateWithIncorrectFile() {
        assertThrowsExactly(WordNotSupportedException.class, () -> {
            dict.populateFromFile("src/test/resources/6-chars-dictionary-test.csv");
        });

        assertEquals(0, dict.getWordList().size());
    }
}