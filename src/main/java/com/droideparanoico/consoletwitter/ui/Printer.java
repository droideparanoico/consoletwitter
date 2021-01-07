package com.droideparanoico.consoletwitter.ui;

import com.droideparanoico.consoletwitter.domain.Message;

public interface Printer {

	/**
	 * Print the message as it will display on the wall.
	 * 
	 * @param message
	 *            The message to be printed.
	 */
	void printPostForWall(Message message);

	/**
	 * print the message as it will display on the user timeline.
	 * 
	 * @param message
	 *            The message to be printed.
	 */
	void printPostForUser(Message message);

	/**
	 * Print the message.
	 * 
	 * @param message
	 *            The message to be printed.
	 */
	void printMessage(String message);

}