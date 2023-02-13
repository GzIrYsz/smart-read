package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryByNumberOfCharacterLoaderTest {
    DictionaryByNumberOfCharacterLoader dictLoader;

    @BeforeEach
    void setUp() {
        dictLoader = new DictionaryByNumberOfCharacterLoader();
    }
}