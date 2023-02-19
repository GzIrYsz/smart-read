package fr.cyu.smartread.spellchecking.dictionary;

import org.junit.jupiter.api.BeforeEach;

class DictionaryByNumberOfCharacterLoaderTest {
    DictionaryByNumberOfCharacterLoader dictLoader;

    @BeforeEach
    void setUp() {
        dictLoader = new DictionaryByNumberOfCharacterLoader();
    }
}