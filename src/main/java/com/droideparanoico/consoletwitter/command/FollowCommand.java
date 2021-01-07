package com.droideparanoico.consoletwitter.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.droideparanoico.consoletwitter.domain.User;
import com.droideparanoico.consoletwitter.repository.Repository;

public class FollowCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^(\\S+) follows (\\S+)$");
	private final Repository repository;

	public FollowCommand(Repository userRepository) {
		this.repository = userRepository;
	}

	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();
		String userName = matcher.group(1);
		String userNameToFollow = matcher.group(2);

		User currentUser = repository.getOrCreateUser(userName);
		User userToFollow = repository.getOrCreateUser(userNameToFollow);
		currentUser.addFollowing(userToFollow);
	}

	public boolean matches(String input) {
		if (input != null && !input.isEmpty()) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
