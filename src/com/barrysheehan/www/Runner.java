package com.barrysheehan.www;

import java.util.Scanner;

/**
 * Runs the Text Simplifier application by creating and running the necessary
 * objects.
 * <p>
 * A <code>Scanner</code> object is created to capture all user input throughout
 * the application, and a <code>TextSimplifier</code> object is created to
 * perform the text simplification functions of the app.
 * <p>
 * A <code>ConfigurationInteractable</code> is then created and is passed the
 * <code>Scanner</code> as well as the <code>TextSimplifier</code> object to
 * ensure it is configured before the application attempts to simplify any input
 * text.
 * <p>
 * Finally a <code>TextSimplifierInteractable</code> object is created to
 * capture user input and simplify it, the main functionality of the app.
 * 
 * @author Barry
 * @version 0.1
 * @since 1.8
 *
 */

public class Runner {
	public static void main(String[] args) {

		System.out.println(ConsoleColour.CYAN);
		System.out.println("***************************************************");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.1                *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
		System.out.println(ConsoleColour.RESET);

		TextSimplifier ts = new TextSimplifier();
		Scanner sc = new Scanner(System.in);

		// Configure TextSimplifier before attempting to simplify text
		ConfigurationInteractable config = new ConfigurationInteractable(ts, sc);
		config.start();

		TextSimplifierInteractable simplifier = new TextSimplifierInteractable(ts, sc);
		simplifier.start();

		sc.close(); // Close Scanner sc
	}
}
