package com.droideparanoico.consoletwitter.command;

import java.util.ArrayList;
import java.util.List;

import com.droideparanoico.consoletwitter.repository.Repository;
import com.droideparanoico.consoletwitter.ui.Printer;

public class CommandInterpreter {
	private final List<Command> commands = new ArrayList<>();
	private final Command invalidCommand;

	public CommandInterpreter(Repository userRepository, Printer printer) {
		commands.add(new PostCommand(userRepository));
		commands.add(new ReadCommand(userRepository, printer));
		commands.add(new FollowCommand(userRepository));
		commands.add(new WallCommand(userRepository, printer));

		invalidCommand = new InvalidCommand(printer);
	}

	public void executeCommand(String commandLine) {
		Command command = getCommand(commandLine);
		command.execute(commandLine);
	}

	public Command getCommand(String commandLine) {
		for (Command command : commands) {
			if (command.matches(commandLine))
				return command;
		}
		return invalidCommand;
	}

}
