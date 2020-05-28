package com.barrysheehan.www;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Extends <code>AbstractThesaurus</code> and provides methods to build a
 * "thesaurus" <i>wordMap</i> from two .txt files.
 * <p>
 * Abstract methods from <code>AbstractThesaurus</code> are implemented and
 * include methods for parsing text files and initialising the values of
 * instance variables <i>dictoinaryString</i> and <i>wordListString</i>.
 * 
 * @author Barry
 * @version 0.1
 * @see WordMap
 * @see AbstractThesaurus
 * @since 1.8
 *
 */

public class Thesaurus extends AbstractThesaurus {

	// Constructors
	/**
	 * Calls the constructor from <code>super</code> which creates a <i>wordMap</i>
	 * using two supplied Strings.
	 * <p>
	 * Strings supplied are the paths to two text files used to create the wordMap.
	 * 
	 * @param pathToDictionaryFile the path to the text file to be used when
	 *                             building the <i>dictionary</i>
	 * @param pathToWordListFile   the path to the text file to be used when
	 *                             building a <i>wordMap</i>
	 */
	public Thesaurus(String pathToDictionaryFile, String pathToWordListFile) {
		super(pathToDictionaryFile, pathToWordListFile);
	}

	/**
	 * Parses a .txt file and adds its contents to the <i>dictionaryString</i>
	 * instance variable.
	 * <p>
	 * If the suppplied path does not lead to a .txt file, or the file can not be
	 * read, the user is informed
	 * 
	 * @param pathToTextFile the path to a text file to be parsed
	 */
	protected void initialiseDictionaryString(String pathToTextFile) {
		try {
			setDictionaryString(parse(pathToTextFile));
		} catch (FileNotFoundException fnfe) {
			System.out.println(ConsoleColour.colourize("RED",
					"> !ERROR: Dictionary file not found. File not found at " + pathToTextFile));
		} catch (IOException ioe) {
			System.out.println(
					ConsoleColour.colourize("RED", "> !ERROR: Unable to read Dictionary file at " + pathToTextFile));
		}
	}

	/**
	 * Parses a text file and adds its contents to the <i>wordListString</i>
	 * instance variable.
	 * <p>
	 * If the suppplied path does not lead to a .txt file, or the file can not be
	 * read, the user is informed
	 * 
	 * @param pathToTextFile the path to a text file to be parsed
	 */
	protected void initialiseWordListString(String pathToTextFile) {
		try {
			setWordListString(parse(pathToTextFile));
		} catch (FileNotFoundException fnfe) {
			System.out.println(ConsoleColour.colourize("RED",
					"> !ERROR: Word List file not found. File not found at " + pathToTextFile));
		} catch (IOException ioe) {
			System.out.println(ConsoleColour.colourize("RED",
					"> !ERROR: Unable to read from Word List file at " + pathToTextFile));
		}
	}

	/**
	 * Parses the file located at the supplied path using the
	 * <code>FileReader</code> class and return its contents as a String.
	 * 
	 * @param pathToTextFile the path to a .txt file to be parsed
	 * @return text the contents of the .txt file as a String
	 * @throws FileNotFoundException if a file does not exist at the file path
	 *                               supplied
	 * @throws IOException           if the file at the file path supplied can not
	 *                               be read
	 */
	private String parse(String pathToTextFile) throws FileNotFoundException, IOException {
		String text = FileReader.parse(pathToTextFile);
		return text;
	}
}
