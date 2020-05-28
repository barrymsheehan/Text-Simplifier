package com.barrysheehan.www;

import java.util.Scanner;

/**
 * Prompts the user for input and uses a <code>TextSimplifier</code> object to
 * simplify user input.
 * <p>
 * Employs a <code>while</code> loop to show instructions to the user and
 * capture keyboard input until user explicitly quits.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see Interactable
 * @since 1.8
 */

public class TextSimplifierInteractable implements Interactable {

	// Instance variables
	private TextSimplifier ts; // Stores the TextSimplifier object used to simplify input text
	private Scanner sc; // Stores scanner for keyboard input
	private String text; // Stores user input if it is to be simplified

	// Constructors
	/**
	 * Create a <code>TextSimplifierInteractable</code> object using a supplied
	 * <code>TextSimplifier</code> and <code>Scanner</code>.
	 * 
	 * @param ts a <code>TextSimplifier</code> to be stored at the instance variable
	 *           <i>ts</i>
	 * @param sc a <code>Scanner</code> to be stored at the instance variable
	 *           <i>sc</i>
	 */
	public TextSimplifierInteractable(TextSimplifier ts, Scanner sc) {
		this.ts = ts;
		this.sc = sc;
	}

	/**
	 * Captures keyboard input, simplifies input text using
	 * <code>TextSimplifier</code> <i>ts</i>, and prints the result to screen.
	 * <p>
	 * The user is prompted to enter text to be simplified using the keyboard. When
	 * the user presses Enter the text is simplified and the result is printed to
	 * the screen via the <code>execute()</code> command. A <code>while</code> loop
	 * ensures the process is repeated until the user explicitly quits.
	 */
	@Override
	public void start() {
		boolean keepAlive = true; // keepAlive used in while to keep method running until changed to false

		while (keepAlive) {

			// Prompt user to input text to be simplified
			System.out.println(ConsoleColour.colourize("CYAN", "> Enter text below to simplify."));
			System.out.println(ConsoleColour.colourize("CYAN", "> Enter \"q\" at any time to quit."));
			System.out.println(
					ConsoleColour.colourize("CYAN", "> Enter \"c\" at any time to configure Text Simplifier.\n"));

			sc.reset(); // Ensures blank line or previous line is not taken as input
			String inputText = sc.nextLine(); // Scanner.nextLine() stores next line of keyboard input

			if (inputText.equals("q")) {
				keepAlive = false; // Stop the loop

				// Inform user that app is closing
				System.out.println(ConsoleColour.colourize("CYAN", "> Now exiting Text Simplifier."));
				System.out.println(ConsoleColour.colourize("CYAN", "> Goodbye!\n"));
				System.out.println(ConsoleColour.colourize("CYAN", "***************************************************"));
				System.out.println(ConsoleColour.colourize("CYAN", "***************************************************\n"));

			} else if (inputText.equals("c")) {
				openConfigurationInteractable(); // Create a new ConfigurationInteractable
			} else { // Input is neither "q" nor "c"
				text = inputText; // Set text
				execute();
			}
		}
	}

	/**
	 * Uses <code>TextSimplifier</code> <i>ts</i> to simplify text stored in
	 * instance variable <i>text</i> and prints result to the screen.
	 * <p>
	 * Also informs the user that swapped words will appear in green and unswapped
	 * words will appear in red.
	 */
	@Override
	public void execute() {
		String simplifiedText = ts.swapText(text); // Call TextSimplifier.swapText on user input

		System.out.println(ConsoleColour.colourize("GREEN", "\n" + "Simplified Text: Green"));
		System.out.println(ConsoleColour.colourize("RED", "Unsimplified Text: Red"));
		System.out.println("\n" + simplifiedText + "\n");
	}

	/**
	 * Creates a new <code>ConfigurationInteractable</code> and calls its
	 * <code>start()</code> method.
	 */
	private void openConfigurationInteractable() {
		ConfigurationInteractable ci = new ConfigurationInteractable(ts, sc);
		ci.start();
	}
}
