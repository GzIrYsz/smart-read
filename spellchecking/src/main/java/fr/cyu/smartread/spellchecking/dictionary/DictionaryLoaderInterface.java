package fr.cyu.smartread.spellchecking.dictionary;

public interface DictionaryLoaderInterface {
    Dictionary getAssociatedWordDict(String word) throws NoDictionarySuitableForThisWordException;
    DictionaryLoaderInterface addDictionary(Dictionary dict);
}
