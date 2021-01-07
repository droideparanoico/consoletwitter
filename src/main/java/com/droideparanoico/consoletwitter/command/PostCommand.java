package com.droideparanoico.consoletwitter.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.droideparanoico.consoletwitter.domain.User;
import com.droideparanoico.consoletwitter.repository.Repository;

public class PostCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^(\\S+) -> (.+)$");

	private final Repository userRepository;

	public PostCommand(Repository userRepository) {
		this.userRepository = userRepository;
	}

	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();

		String userName = matcher.group(1);
		String message = matcher.group(2);

		User user = userRepository.getOrCreateUser(userName);
		user.addPost(message);
	}

	public boolean matches(String input) {
		if (input != null && !input.isEmpty()) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
