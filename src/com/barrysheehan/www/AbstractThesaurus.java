package com.barrysheehan.www;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Implements WordMap to create a <b>thesaurus</b> in which words in the
 * <i>dictionary</i> Set provide possible values for any words added as keys to
 * the <i>wordMap</i> map.
 * <p>
 * A dictionary of words as Strings is created as a Set. This Set is then used
 * as a reference when adding words to the Map wordMap. Any text of words added
 * to wordMap is first checked to see whether it contains a word that exists in
 * the dictionary. If so, the words are added to the wordMap. If not, the words
 * are not added to the wordMap.
 * 
 * @author Barry
 * @version 0.1
 * @see WordMap
 * @see Thesaurus
 * @since 1.8
 */

public abstract class AbstractThesaurus implements WordMap {

	// Instance variables
	private Map<String, String> wordMap; // Each key is a word, its value is a related word from dictionary, forming a
											// thesaurus-like object
	private Set<String> dictionary; // Set of words tested against when adding words to above Map
	private String dictionaryString; // Stores words to be added to dictionary as a String
	private String wordListString; // Stores words to be added to wordMap as a String

	// Getters and Setters
	public Map<String, String> getWordMap() {
		return this.wordMap;
	}

	public void setWordMap(Map<String, String> wordMap) {
		this.wordMap = wordMap;
	}

	public void setDictionary(Set<String> dictionary) {
		this.dictionary = dictionary;
	}

	public Set<String> getDictionary() {
		return this.dictionary;
	}

	public String getDictionaryString() {
		return this.dictionaryString;
	}

	public void setDictionaryString(String dictionarySource) {
		this.dictionaryString = dictionarySource;
	}

	public String getWordListString() {
		return this.wordListString;
	}

	public void setWordListString(String wordListSource) {
		this.wordListString = wordListSource;
	}

	// Constructors
	/**
	 * Creates an <code>AbstractThesaurus</code> using the two Strings passed to it.
	 * <p>
	 * The first String passed is the text used to create a <i>dictionary</i> Set.
	 * It is first "initialised", during which it should be added to the instance
	 * variable <i>dictionaryString</i>. The String stored at dictionaryString is
	 * then used to populate both the dictionary Set and the <i>wordMap</i> Map.
	 * <p>
	 * The second String passed is the text used to create a wordMap Map. It is
	 * first "initialised", during which it should be added to the instance variable
	 * <i>wordMapString</i>. The String stored at wordMapString is then used to add
	 * additional words to the wordMap.
	 * 
	 * @param dictionarySource text to be used to populate the dictionary Set and
	 *                         initilise the wordMap Map
	 * @param wordListSource   text to be used to populate the wordMap Map
	 */
	public AbstractThesaurus(String dictionarySource, String wordListSource) {
		initialiseDictionaryString(dictionarySource);
		initialiseWordListString(wordListSource);

		setDictionary(createDictionary());
		setWordMap(initialiseWordMap());
		addWordMapEntries();
	}

	/**
	 * Populates the Set passed to it with words from <i>dictionarySring</i>.
	 * <p>
	 * Takes the String dictionaryString and adds its contents to the supplied Set.
	 * 
	 * @return tempDictionary a populated Set
	 */
	@Override
	public Set<String> createDictionary() {
		Set<String> tempDictionary = new TreeSet<String>(); // dictionary as TreeSet
		String[] words = dictionaryString.split(System.lineSeparator()); // Split words by System.lineSeparator()

		addDictionaryEntries(tempDictionary, words);
		return tempDictionary;
	}

	/**
	 * Adds a supplied list of words <i>words</i> to the supplied Set
	 * <i>dictionary</i> and returns the populated Set.
	 * 
	 * @param dictionary a Set to which words are to be added
	 * @param words      an array of Strings to be added to the Set
	 * @return dictionary a set populated with supplied Strings
	 */
	@Override
	public Set<String> addDictionaryEntries(Set<String> dictionary, String[] words) {
		for (String word : words) {
			word = word.toLowerCase(); // Additions to dictionary lower case
			dictionary.add(word); // O(log(n))
		}
		return dictionary;
	}

	/**
	 * Creates a Map and adds each word from <i>dictionaryString</i> to it, mapping
	 * the word to itself.
	 * <p>
	 * Each word in dictionaryString is mapped to itself at initialisation as this
	 * word is always considered to refer to itself.
	 * 
	 * @return tempWordMap a populated Map
	 */
	@Override
	public Map<String, String> initialiseWordMap() {
		Map<String, String> tempWordMap = new TreeMap<String, String>(); // wordMap as TreeMap
		String[] lines = dictionaryString.split(System.lineSeparator()); // Split words by System.lineSeparator()

		for (String line : lines) {
			line = line.toLowerCase(); // Additions to dictionary are lower case
			tempWordMap.put(line, line); // O(log(n))
		}
		return tempWordMap;
	}

	/**
	 * Populates the Map <i>wordMap</i> with the contents of the String stored at
	 * <i>wordListString</i>.
	 * <p>
	 * Reads String at wordListString and splits it into lines <i>lines</i> and
	 * subsequently words <i>words</i>. The dictionary is checked to determine
	 * whether it contains each word. If so, variable <i>dictionaryWord</i> is
	 * updated to contain that word. All words on the same line of the text are
	 * added as keys to the wordMap, and the dictionaryWord is added as the value of
	 * each key.
	 */
	@Override
	public void addWordMapEntries() {
		String[] lines = wordListString.split(System.lineSeparator());

		for (String line : lines) {
			String[] words = line.split(","); // Split words at comma
			String dictionaryWord = null;

			for (String word : words) {
				word = word.toLowerCase();

				if (dictionary.contains(word)) { // If the word from input text is found in the dictionary
					dictionaryWord = word; // Set dictionary word to the word in question
					break;
				}
			}

			if (dictionaryWord != null) { // If a String has been stored in dictionaryWord
				for (String word : words) {
					wordMap.put(word, dictionaryWord); //O(log(n)) // Add word from input as new key, dictionary word as its value
				}
			}
		}
	}

	/**
	 * Allows initialisation of the <i>dictionaryString</i> variable from a supplied
	 * String.
	 * <p>
	 * Abstract to allow any class that extends <code>AbstractThesaurus</code> to
	 * implement a way to populate dictionaryString. For example, the variable can
	 * be populated form a text file, or user keyboard input.
	 * 
	 * @param dictionarySource a String from which <i>dictionaryString</i> can be
	 *                         populated.
	 */
	protected abstract void initialiseDictionaryString(String dictionarySource);

	/**
	 * Allows initialisation of the <i>wordListStrig</i> variable from a supplied
	 * String.
	 * <p>
	 * Abstract to allow any class that extends <code>AbstractThesaurus</code> to
	 * implement a way to populate wordListString. For example, the variable can be
	 * populated form a text file, or user keyboard input.
	 * 
	 * @param wordListSource a String from which <i>wordListString</i> can be
	 *                       populated.
	 */
	protected abstract void initialiseWordListString(String wordListSource);
}
