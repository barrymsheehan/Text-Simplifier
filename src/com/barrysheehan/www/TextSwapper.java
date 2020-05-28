package com.barrysheehan.www;

/**
 * Defines methods used to swap words from a body of text with any words of the
 * programmer's choosing.
 * <p>
 * A String of text is passed to a class implementing <code>TextSwapper</code>.
 * It tests each word against criteria defined by the programmer and returns a
 * new text with the appropriate words swapped with a predefined word or words.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see TextSimplifier
 * @since 1.8
 *
 */

public interface TextSwapper {

	/**
	 * Reads a supplied String of text and returns the same text with appropriate
	 * words changed to match a word or words predefined in the program.
	 * 
	 * @param text text to be read, tested and swapped
	 * @return text with the appropriate words swapped
	 */
	String swapText(String text);

	/**
	 * Tests a word passed to it against criteria defined by the programmer,
	 * returning a boolean value based on the success of the test.
	 * 
	 * @param word a word to to test
	 * @return <code>true</code> or <code>false</code> depending on whether testing
	 *         criteria are met
	 */
	boolean testWord(String word);

	/**
	 * Swaps a word provided to it with another predefined word
	 * 
	 * @param word word to be swapped with a predefined word
	 * @return the predefined word
	 */
	String swapWord(String word);
}
