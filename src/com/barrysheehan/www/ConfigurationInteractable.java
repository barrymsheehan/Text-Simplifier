package com.barrysheehan.www;

import java.util.Scanner;

/**
 * Prompts the user for input and uses that input to configure the supplied
 * <code>TextSimplifier</code> object.
 * <p>
 * Prompts user to enter the path to a dictionary file and the path to a word
 * list file. These are used to configure the supplied
 * <code>TextSimplifier</code>'s thesaurus.
 * <p>
 * Employs <code>while</code> loops to continue to prompt user to enter a file
 * path until a suitable one has been entered.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see Interactable
 * @since 1.8
 *
 */

public class ConfigurationInteractable implements Interactable {

	// Instance variables
	private TextSimplifier ts; // Stores the TextSimplifier object to be configured
	private Scanner sc; // Stores scanner for keyboard input

	private String newDictionaryFile; // Stores path to new TextSimplifier dictionaryFile as String
	private String newWordListFile; // Stores path to new TextSimplifier wordListFile as String

	// Constructors
	/**
	 * Create a <code>ConfigurationInteractable</code> object using a supplied
	 * <code>TextSimplifier</code> and <code>Scanner</code>.
	 * 
	 * @param ts a <code>TextSimplifier</code> to be stored at the instance variable
	 *           <i>ts</i>
	 * @param sc a <code>Scanner</code> to be stored at the instance variable
	 *           <i>sc</i>
	 */
	public ConfigurationInteractable(TextSimplifier ts, Scanner sc) {
		this.ts = ts;
		this.sc = sc;
	}

	@Override
	/**
	 * Prompts user to enter the paths to a dictionary file and a word list file,
	 * which are used to configure the <code>TextSimplifier</code> <i>ts</i> to
	 * allow simplification of text. The <code>TextSimplifier</code>'s
	 * <code>init</code> method is used to create a new <code>Thesaurus</code>
	 * configured with the input dictionary file and word list file.
	 * <p>
	 * When both the path to a dictionary file and the path to a word list file have
	 * been entered, the <code>execute()</code> method initialises the
	 * <code>TextSimplifier</code> with a new dictionary and word list gathered from
	 * the .txt files at these file paths.
	 */
	public void start() {
		newDictionaryFile = ts.getDictionaryFile(); // Initialise newDictionaryFile to current TextSimplifier
													// dictionaryFile
		newWordListFile = ts.getWordListFile(); // Initialise newWordListFile to current TextSimplifier wordListFile

		setNewDictionaryFile(); // Takes user input and updates newDictionaryFile accordingly
		setNewWordListFile(); // Takes user input and updates newWordListFile accordingly
		execute();
	}

	/**
	 * Prompts user to input path to a dictionary file, and sets that path as the
	 * value of <i>newDictionaryFile</i>.
	 * <p>
	 * If user enters "a", the current dictionary file is accepted.
	 * <p>
	 * In both cases, the <code>FileReader</code> class is used to verify that a
	 * file exists at the specified path. If so the newDictionaryFile variable is
	 * set to the String entered by the user, this is confirmed to the user and the
	 * loop is stopped. If not an error is displayed to the screen and the loop
	 * repeats, prompting the user to enter a new path.
	 */
	private void setNewDictionaryFile() {
		boolean keepAlive = true; // keepAlive used in while to keep method running until changed to false

		while (keepAlive) {
			System.out.println(ConsoleColour.colourize("CYAN",
					"> Enter the path to a new Dictionary file below, or enter \"a\" to accept current Dictionary file."));
			System.out.println(
					ConsoleColour.colourize("CYAN", "> Current Dictionary file: " + ts.getDictionaryFile() + "\n"));

			sc.reset(); // Ensures blank line or previous line is not taken as input
			String inputText = sc.nextLine(); // Scanner's nextLine() method stores next line of keyboard input

			if (inputText.equals("a")) { // User enters "a" to accept current file
				if (FileReader.fileExists(ts.getDictionaryFile())) { // Check that file exists at path of current
																		// Dictionary file
					System.out.println(ConsoleColour.colourize("GREEN",
							"> Current Dictionary file accepted: " + ts.getDictionaryFile() + "\n"));
					keepAlive = false; // Stop the loop
				} else { // File does not exist
					System.out.println(ConsoleColour.colourize("RED",
							"> !ERROR: Current Dictionary File not found at: " + ts.getDictionaryFile()));
					System.out.println(ConsoleColour.colourize("CYAN",
							"> Please enter the path to a valid Dictionary file to continue.\n"));
				}
			} else if (FileReader.fileExists(inputText)) { // Check that file exists at path entered by user
				newDictionaryFile = inputText; // Set newDictionaryFile to text input by user
				System.out.println(
						ConsoleColour.colourize("GREEN", "> Dictionary file updated: " + newDictionaryFile + "\n"));
				keepAlive = false; // Stop loop
			} else { // File does not exist
				System.out.println(ConsoleColour.colourize("RED", "> !ERROR: No file found at: " + inputText));
				System.out.println(ConsoleColour.colourize("CYAN",
						"> Please enter the path to a valid Dictionary file to continue.\n"));
			}
		}
	}

	/**
	 * Prompts user to input path to a word list file, and sets that path as the
	 * value of <i>newWordListFile</i>.
	 * <p>
	 * If user enters "a", the current word list file is accepted.
	 * <p>
	 * In both cases, the <code>FileReader</code> class is used to verify that a
	 * file exists at the specified path. If so the newWordListFile variable is set
	 * to the String entered by the user, this is confirmed to the user and the loop
	 * is stopped. If not an error is displayed to the screen and the loop repeats,
	 * prompting the user to enter a new path.
	 */
	private void setNewWordListFile() {
		boolean keepAlive = true; // Used to keep while loop running

		while (keepAlive) {
			System.out.println(ConsoleColour.colourize("CYAN",
					"> Enter the path to a new Word List file below, or enter \"a\" to accept current Word List file."));
			System.out.println(
					ConsoleColour.colourize("CYAN", "> Current Word list file: " + ts.getWordListFile() + "\n"));

			sc.reset(); // Ensures blank line or previous line is not taken as input
			String inputText = sc.nextLine(); // Scanner.nextLine() stores next line of keyboard input

			if (inputText.equals("a")) { // User enters "a" to accept current file
				if (FileReader.fileExists(ts.getWordListFile())) { // Check that file exists at path of current Word
																	// List file
					System.out.println(ConsoleColour.colourize("GREEN",
							"> Current Word List file accepted: " + ts.getWordListFile() + "\n"));
					keepAlive = false; // Stop the loop
				} else { // File does not exist
					System.out.println(ConsoleColour.colourize("RED",
							"> !ERROR: Current Word List file not found at: " + ts.getWordListFile()));
					System.out.println(ConsoleColour.colourize("CYAN",
							"> Please enter the path to a valid Word List file to continue.\n"));
				}
			} else if (FileReader.fileExists(inputText)) {
				newWordListFile = inputText; // Set newWordList to text input by user
				System.out.println(
						ConsoleColour.colourize("GREEN", "> Word list file updated: " + newWordListFile + "\n"));
				keepAlive = false; // Stop the loop
			} else { // File does not exist
				System.out.println(ConsoleColour.colourize("RED", "> !ERROR: No file found at: " + inputText));
				System.out.println(ConsoleColour.colourize("CYAN",
						"> Please enter the path to a valid Word List file to continue.\n"));
			}
		}
	}

	/**
	 * Updates the values of <i>dictionaryFile</i> and <i>wordListFile</i> in
	 * <code>TextSimplifier</code> <i>ts</i> and initialises it with a new
	 * <code>Thesaurus</code>.
	 * <p>
	 * Once the <code>TextSimplifier</code>'s dictionaryFile and wordListFile
	 * variables have been set with paths to two files, the
	 * <code>TextSimplifier</code>'s <code>init()</code> method is called, creating
	 * a new <code>Thesaurus</code> object and configuring the
	 * <code>TextSimplifier</code> to use it.
	 * <p>
	 * The method checks whether:
	 * <ul>
	 * <li>The value of newDictionaryFile matches the path to the current dictionary
	 * file</li>
	 * <li>The value of newWordListFile matches the path to the current word list
	 * file</li>
	 * <li>The <code>TextSimplifier</code> <i>ts</i> has been previously configured,
	 * based on its <code>configured</code> instance variable</li>
	 * </ul>
	 * <p>
	 * If both file paths are the same as the ones already configured, the method
	 * exits without configuring <i>ts</i>. However, if <i>ts</i> has not previously
	 * been configured, the method ensures that it is configured before the method
	 * exits. This means that a new <code>Thesaurus</code> is not created if it
	 * doesn't need to be and that the current <code>TextSimplifier</code> is always
	 * configured when the method exits.
	 */
	@Override
	public void execute() {
		if (!newDictionaryFile.equals(ts.getDictionaryFile()) || !newWordListFile.equals(ts.getWordListFile())
				|| !ts.isConfigured()) {
			ts.setDictionaryFile(newDictionaryFile);
			ts.setWordListFile(newWordListFile);
			ts.init();
		}
	}
}
