package com.droideparanoico.consoletwitter.command;

import com.droideparanoico.consoletwitter.ui.Printer;

public class InvalidCommand implements Command {

	private final Printer postDisplayer;

	public InvalidCommand(Printer postDisplayer) {
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		postDisplayer.printMessage(String.format("Command: %s is invalid!", commandLine));
	}

	@Override
	public boolean matches(String input) {
		return false;
	}

}
