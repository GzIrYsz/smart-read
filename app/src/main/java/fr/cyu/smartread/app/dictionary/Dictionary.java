package fr.cyu.smartread.app.dictionary;

import java.util.Collection;

public interface Dictionary {
    Collection<String> getAssociatedWordDict(String word) throws IllegalArgumentException;
}
