package com.droideparanoico.consoletwitter.command;

public interface Command {
	/**
	 * Executes the input command string
	 * 
	 * @param commandLine
	 *            Command string to execute
	 */
	void execute(String commandLine);

	/**
	 * Checks if the given command is able to execute the given string
	 * 
	 * @param input
	 *            command string which is able to be executed with this command
	 * @return boolean
	 */
	boolean matches(String input);
}
