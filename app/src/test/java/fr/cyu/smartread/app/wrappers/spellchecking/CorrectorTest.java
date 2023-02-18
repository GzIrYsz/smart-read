package fr.cyu.smartread.app.wrappers.spellchecking;

import fr.cyu.smartread.spellchecking.corrector.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

class CorrectorTest {
    Corrector corrector;

    @BeforeEach
    public void setUp() throws IOException {
        corrector = new Corrector();
    }

    @Test
    public void shouldNotReturn2232WhentestIsGiven() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("test");
        assertNotEquals(2232, corrections.size());
    }

    @Test
    public void shouldNotReturn26WhenaIsGiven() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("a");
        assertNotEquals(26, corrections.size());
    }

    @Test
    public void shouldNotReturn107WhenabIsGiven() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("ab");
        assertNotEquals(107, corrections.size());
    }

    @Test
    public void shouldReturnSuitableCorrectionsFor4CharsWord() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("test");
        for (WordScore correction : corrections) {
            assertTrue(correction.getScore() < 2);
        }
    }

    @Test
    public void shouldReturnSuitableCorrectionsFor7CharsWord() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("bonjoue");
        for (WordScore correction : corrections) {
            System.out.println(correction.toString());
            assertTrue(correction.getScore() < 3);
        }
    }
}