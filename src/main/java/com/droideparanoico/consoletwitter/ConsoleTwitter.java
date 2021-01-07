package com.droideparanoico.consoletwitter;

import java.util.Scanner;

import com.droideparanoico.consoletwitter.command.CommandInterpreter;
import com.droideparanoico.consoletwitter.repository.Repository;
import com.droideparanoico.consoletwitter.ui.ConsoleTwitterPrinter;
import com.droideparanoico.consoletwitter.ui.Printer;
import com.droideparanoico.consoletwitter.repository.UserRepository;

/**
 * ConsoleTwitter - Main class.
 */
public class ConsoleTwitter {
	private static final String WELCOME = "Welcome to Console Twitter. Please, enter a command:\n";
	private static final String PROMPT = "> ";
	private static final Scanner scanner = new Scanner(System.in);

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) {
		Repository userRepository = new UserRepository();
		Printer printer = new ConsoleTwitterPrinter(System.out);
		CommandInterpreter commandInterpreter = new CommandInterpreter(userRepository, printer);
		System.out.print(WELCOME);

		// Infinite loop to accept and display commands
		while (true) {
			System.out.print(PROMPT);
			String input = scanner.nextLine();
			commandInterpreter.executeCommand(input);
		}
	}
}
