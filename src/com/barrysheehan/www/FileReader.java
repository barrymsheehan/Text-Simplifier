package com.barrysheehan.www;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class contains static methods for parsing .txt files and for verifying
 * whether a file at a given path exists.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see File
 * @since 1.8
 */

public class FileReader {
	/**
	 * Parses the lines of a .txt file and adds them to a StringBuilder, which is
	 * finally returned as a String.
	 * <p>
	 * Uses a BufferedReader, InputStreamReader, FileInputStream, and File object to
	 * read from the file.
	 * 
	 * @param pathToFile the path to the file to be parsed
	 * @return the contents of the parsed file as a String
	 * @throws FileNotFoundException if a file does not exist at the file path
	 *                               supplied
	 * @throws IOException           if the file at the file path supplied can not
	 *                               be read
	 */
	public static String parse(String pathToFile) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToFile))));
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = br.readLine()) != null) { // Read lines in file, until null at EOF
			sb.append(line);
			sb.append(System.lineSeparator()); // Add line separator to StringBuilder between lines
		}

		br.close(); // Close BufferedReader
		return sb.toString();
	}

	/**
	 * Verifies whether a file exists at the supplied path.
	 * <p>
	 * Creates a File object at the specified file path, tests whether the file
	 * <b>is</b> a file and <b>is not</b> a directory, and returns a boolean value
	 * based on the result of those tests.
	 * 
	 * @param pathToFile the path at which to verify whether a file exists
	 * @return <code>true</code> if a file exists at the supplied path, or
	 *         <code>false</code> if a file does not exist at that path
	 */
	public static boolean fileExists(String pathToFile) {
		File f = new File(pathToFile); // Create new File object
		if (f.isFile() && !f.isDirectory()) { // File is a file and not a directory
			return true;
		} else {
			return false;
		}
	}
}
