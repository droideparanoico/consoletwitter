package com.droideparanoico.consoletwitter.command;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.droideparanoico.consoletwitter.domain.Message;
import com.droideparanoico.consoletwitter.domain.User;
import com.droideparanoico.consoletwitter.repository.Repository;
import com.droideparanoico.consoletwitter.ui.Printer;

public class WallCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^(\\S+) wall$");

	private final Repository userRepository;
	private final Printer postDisplayer;

	public WallCommand(Repository userRepository, Printer postDisplayer) {
		this.userRepository = userRepository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();

		String userName = matcher.group(1);
		User user = userRepository.getOrCreateUser(userName);
		// Get user's messages.
		List<Message> messages = new ArrayList<>(user.getPosts());
		// Add following users post.
		for (User following : user.getFollowingUsers()) {
			messages.addAll(following.getPosts());
		}
		// Sort messages.
		messages.sort((p1, p2) -> p2.getPublishDate().compareTo(p1.getPublishDate()));
		for (Message message : messages) {
			postDisplayer.printPostForWall(message);
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
