package fr.cyu.smartread.spellchecking.dictionary;

/**
 * This interface contains the methods that should be implemented by any DictionaryLoader.
 *
 * @author Thomas REMY
 */
public interface DictionaryLoaderInterface {
    /**
     * Returns the dictionary associated with the given word.
     *
     * @param word The word given.
     * @return The dictionary associated with the word.
     * @throws NoDictionarySuitableForThisWordException If there's no dictionary suitable for the word given.
     */
    Dictionary getAssociatedWordDict(String word) throws NoDictionarySuitableForThisWordException;

    /**
     * To add a dictionary to the loader.
     *
     * @param dict The dictionary to add.
     * @return The loader object itself.
     */
    DictionaryLoaderInterface addDictionary(Dictionary dict);
}
