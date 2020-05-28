package com.barrysheehan.www;

import java.util.Map;
import java.util.Set;

/**
 * Defines methods used to create a Map of words <i>wordMap</i> and a Set of
 * related words <i>dictionary</i> which the words in the map can reference.
 * <p>
 * A dictionary should contain a set of words which one or more words in the
 * more expansive wordMap can reference. For example, synonymous words can be
 * used to create a thesaurus-like Map. In this case a dictionary may contain
 * the word "animal", and its word map may contain words like "beast" and
 * "creature" which are synonymous with the word "animal".
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see AbstractThesaurus
 * @see Thesaurus
 * @since 1.8
 *
 */

public interface WordMap {

	/**
	 * Creates a Set to be used as a dictionary.
	 * 
	 * @return the populated Set of Strings.
	 */
	Set<String> createDictionary();

	/**
	 * Adds array of Strings <i>words</i> passed to it to the Set <i>dictionary</i>
	 * passed to it.
	 * 
	 * @param dictionary a Set to which words in the array, words, are to be added
	 * @param words      an array of Strings to be added to the the Set, dictionary
	 * @return the populated Set of Strings.
	 */
	Set<String> addDictionaryEntries(Set<String> dictionary, String[] words);

	/**
	 * Creates a Map to be used as a word map.
	 * 
	 * @return the populated Map.
	 */
	Map<String, String> initialiseWordMap();

	/**
	 * Adds a word or list of words to an already created Map.
	 * 
	 */
	void addWordMapEntries();
}