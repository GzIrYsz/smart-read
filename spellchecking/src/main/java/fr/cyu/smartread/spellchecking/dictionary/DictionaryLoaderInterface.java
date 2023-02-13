package fr.cyu.smartread.spellchecking.dictionary;

public interface DictionaryLoaderInterface {
    Dictionary getAssociatedWordDict(String word);
    DictionaryLoaderInterface addDictionary(Dictionary dict);
}
