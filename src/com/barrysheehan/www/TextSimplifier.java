package com.barrysheehan.www;

/**
 * Simplifies a String passed to it and returns a simplified version of the
 * String. Is configured with a <code>Thesaurus</code> object, used to swap
 * words in input text with other predefined words.
 * <p>
 * Is configured with the path to a dictionary file <i>dictionaryFile</i> and a
 * word list file <i>wordListFile</i> which contain paths to .txt files. The
 * files at these paths are used to create a <code>Thesaurus</code> object
 * specific to this instance of <code>TextSimplifier</code>.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see TextSwapper
 * @see TextSimplifierInteractable
 * @see Thesaurus
 * @since 1.8
 */

public class TextSimplifier implements TextSwapper {

	// Instance variables
	private String dictionaryFile = "../dictionaryFile.txt"; // Default path to dictionaryFile
	private String wordListFile = "../wordListFile.txt"; // Default path to wordListFile
	private Thesaurus thesaurus; // thesaurus stores Thesaurus currently in use

	// configured flag used to determine whether TextSimplifier object is configured
	// from within another class or itself
	private boolean configured = false;

	// Getters and Setters
	public String getDictionaryFile() {
		return this.dictionaryFile;
	}

	public void setDictionaryFile(String pathToTextFile) {
		this.dictionaryFile = pathToTextFile;
	}

	public String getWordListFile() {
		return this.wordListFile;
	}

	public void setWordListFile(String pathToTextFile) {
		this.wordListFile = pathToTextFile;
	}

	/**
	 * Returns boolean value stored at <i>configured</i> instance variable.
	 * <p>
	 * Used to determine whether the current <code>TextSimplifier</code> object has
	 * been configured.
	 * 
	 * @return boolean stored at the configured instance variable
	 */
	public boolean isConfigured() {
		return this.configured;
	}

	/**
	 * Creates a <code>Thesaurus</code> object to be used in simplification
	 * operations and sets the <i>configured</i> instance variable to
	 * <code>true</code>.
	 * <p>
	 * Informs the user that <code>TextSimplifier</code> is being initialised with a
	 * new <code>Thesaurus</code>, and when the initialisation is done.
	 * <p>
	 * This method must be called each time the dictionaryFile or wordListFile are
	 * changed, otherwise the <code>Thesaurus</code> being used will not contain the
	 * updated dictionary or word list.
	 */
	public void init() {
		System.out.println(ConsoleColour.colourize("CYAN", "> Initialising new Thesaurus..."));
		thesaurus = new Thesaurus(getDictionaryFile(), getWordListFile());
		configured = true;
		System.out.println(ConsoleColour.colourize("GREEN", "> Done!\n"));
	}

	/**
	 * Simplifies a String supplied to it by testing each word within it against the
	 * keys stored in the wordMap variable of <i>thesaurus</i>, swapping words when
	 * appropriate and returning the simplified text.
	 * 
	 * @param inputText the text to be simplified
	 * @return the simplified text
	 */
	@Override
	public String swapText(String inputText) {
		String[] words = inputText.split(" "); // Split lines of text at space character

		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			word = word.toLowerCase();

			if (testWord(word)) { // If word matches a key in the thesaurus' wordMap
				word = swapWord(word); // call swapWord method on that word
				sb.append(ConsoleColour.GREEN + word + ConsoleColour.RESET); // Append word to StringBuilder and
																				// colourize it GREEN
			} else { // Input word doesn't match a key in the wordMap
				sb.append(ConsoleColour.RED + word + ConsoleColour.RESET); // Append word to StringBuilder and colourize
																			// it RED
			}
			sb.append(" "); // Append a space between each word added to the StringBuilder
		}
		return sb.toString(); // Return StringBuilder as String
	}

	/**
	 * Checks whether the input String appears as a key in <i>thesaurus</i>'
	 * <i>wordMap</i>.
	 * 
	 * @param word a word to be tested
	 * @return <code>true</code> if the supplied word appears as a key in
	 *         thesaurus'wordMap, or <code>false</code> if it does not
	 */
	@Override
	public boolean testWord(String word) {
		if (thesaurus.getWordMap().containsKey(word)) { // O(log(n))
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Takes input word as a key and replaces it with the value stored at that key
	 * in <i>thesaurus</i>' <i>wordMap</i>.
	 * 
	 * @param word the word to be simplified
	 * @return the simplified word
	 */
	@Override
	public String swapWord(String word) {
		word = thesaurus.getWordMap().get(word); // O(log(n))
		return word;
	}
}
