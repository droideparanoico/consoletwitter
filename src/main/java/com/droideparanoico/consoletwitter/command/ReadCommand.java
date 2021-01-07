package com.droideparanoico.consoletwitter.command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.droideparanoico.consoletwitter.domain.Message;
import com.droideparanoico.consoletwitter.domain.User;
import com.droideparanoico.consoletwitter.repository.Repository;
import com.droideparanoico.consoletwitter.ui.Printer;

public class ReadCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^\\S+$");

	private final Repository userRepository;
	private final Printer postDisplayer;

	public ReadCommand(Repository userRepository, Printer postDisplayer) {
		this.userRepository = userRepository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		User user = userRepository.getOrCreateUser(commandLine.trim());
		List<Message> messages = user.getPosts();
		// Sort messages.
		messages.sort((p1, p2) -> p2.getPublishDate().compareTo(p1.getPublishDate()));
		for (Message message : messages) {
			postDisplayer.printPostForUser(message);
		}
	}

	@Override
	public boolean matches(String input) {
		if (input != null && !input.isEmpty()) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
