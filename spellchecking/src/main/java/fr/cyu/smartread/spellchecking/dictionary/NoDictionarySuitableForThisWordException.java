package fr.cyu.smartread.spellchecking.dictionary;

public class NoDictionarySuitableForThisWordException extends Exception {
    public NoDictionarySuitableForThisWordException(String message) {
        super(message);
    }
}
