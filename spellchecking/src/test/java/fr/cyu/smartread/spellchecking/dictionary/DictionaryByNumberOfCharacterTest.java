package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryByNumberOfCharacterTest {
    DictionaryByNumberOfCharacter dict;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInstantiateCorrectWordLength() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DictionaryByNumberOfCharacter((short) 0);
        });

        dict = new DictionaryByNumberOfCharacter((short) 5);
        assertEquals(5, dict.getWordsLength());

        dict = new DictionaryByNumberOfCharacter((short) 25);
        assertEquals(25, dict.getWordsLength());

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DictionaryByNumberOfCharacter((short) 26);
        });
    }
}