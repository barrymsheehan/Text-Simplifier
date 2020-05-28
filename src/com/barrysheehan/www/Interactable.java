package com.barrysheehan.www;

/**
 * Defines methods to be used when any form of user input needs to be captured
 * and processed.
 * <p>
 * Classes which implement this interface accept user input in some form and
 * then carry out a specific function, defined in the <code>execute()</code>
 * method.
 * 
 * @author Barry Sheehan
 * @version 0.1
 * @see TextSimplifierInteractable
 * @see ConfigurationInteractable
 * @since 1.8
 *
 */

public interface Interactable {

	/**
	 * Begin accepting user input.
	 */
	void start();

	/**
	 * Execute the class' intended specific function.
	 */
	void execute();
}
