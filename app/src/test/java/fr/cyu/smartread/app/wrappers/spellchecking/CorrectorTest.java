package fr.cyu.smartread.app.wrappers.spellchecking;

import fr.cyu.smartread.spellchecking.WordScore;
import fr.cyu.smartread.spellchecking.dictionary.NoDictionarySuitableForThisWordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CorrectorTest {
    Corrector corrector;

    @BeforeEach
    public void setUp() throws IOException {
        corrector = new Corrector();
    }

    @Test
    public void testGetCorrection() throws NoDictionarySuitableForThisWordException {
        ArrayList<WordScore> corrections = corrector.getCorrections("test");
        for (WordScore correction : corrections) {
            System.out.println(correction.toString());
        }
    }

}