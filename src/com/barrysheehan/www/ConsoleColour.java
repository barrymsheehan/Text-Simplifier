package com.barrysheehan.www;

/**
 * Stores all possible values for text colours used in the program as ANSI
 * escape codes. The codes are used to colour text that is printed to the
 * console throughout the application.
 * <p>
 * Also contains a helper method <code>colourize()</code> which quickly adds
 * colour to a String before printing to the console.
 * 
 * @author John Healy
 * @author Barry Sheehan
 * @version 0.1
 * @since 1.8
 *
 */

public enum ConsoleColour { // ANSI escape codes
	//Reset
    RESET("\033[0m"),  							//Text Reset

    //Regular Colors
    BLACK("\033[0;30m"),   						//BLACK
    RED("\033[0;31m"),     						//RED
    GREEN("\033[0;32m"),   						//GREEN
    YELLOW("\033[0;33m"),  						//YELLOW
    BLUE("\033[0;34m"),    						//BLUE
    PURPLE("\033[0;35m"),  						//PURPLE
    CYAN("\033[0;36m"),    						//CYAN
    WHITE("\033[0;37m");   						//WHITE

	private final String colour;

	ConsoleColour(String colour) {
		this.colour = colour;
	}

	public String colour() {
		return this.colour;
	}

	@Override
	public String toString() {
		return colour;
	}

	/**
	 * Matches the <i>col</i> String passed to it with a constant in the Enum and
	 * appends the appropriate ANSI escape character to the <i>text</i> String in
	 * order to colourize it when printed to the screen.
	 * <p>
	 * Also appends the ANSI escape code stored at <code>STRING</code> in the Enum
	 * to the end of the String.
	 * <p>
	 * Prevents having to add an ANSI escape code to the beginning and end of each
	 * String that needs to be colourized before printing to the console.
	 * 
	 * @param col  a String containing reference to a constant contained in the
	 *             ConsoleColour Enum
	 * @param text text to be colourized
	 * @return colourized text
	 */
	public static String colourize(String col, String text) {
		return ConsoleColour.valueOf(col) + text + ConsoleColour.RESET;
	}
}
