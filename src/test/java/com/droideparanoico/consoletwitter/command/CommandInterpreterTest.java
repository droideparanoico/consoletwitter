package com.droideparanoico.consoletwitter.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import com.droideparanoico.consoletwitter.repository.Repository;
import com.droideparanoico.consoletwitter.ui.ConsoleTwitterPrinter;
import com.droideparanoico.consoletwitter.ui.Printer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.droideparanoico.consoletwitter.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class CommandInterpreterTest {
	private static final String USER = "David";
	private static final String USER_2 = "Julia";
	private static final String MESSAGE = "Happy new year!";
	private static final String POST_COMMAND = USER + " -> " + MESSAGE;
	private static final String FOLLOW_COMMAND = USER + " follows " + USER_2;
	private static final String WALL_COMMAND = USER + " wall";

	private CommandInterpreter commandInterpreter;

	@Mock
	private Repository userRepository;
	private PrintStream printStream;

	@Before
	public void setUp() {
		Printer printer = new ConsoleTwitterPrinter(printStream);
		commandInterpreter = spy(new CommandInterpreter(userRepository, printer));
	}

	@Test
	public void executePostCommandTest() {
		User user = mock(User.class);
		when(userRepository.getOrCreateUser(USER)).thenReturn(user);
		commandInterpreter.executeCommand(POST_COMMAND);
		verify(commandInterpreter).getCommand(POST_COMMAND);
	}

	@Test
	public void getPostCommandTest() {
		Command returnedCommand = commandInterpreter.getCommand(POST_COMMAND);
		Assert.assertTrue(returnedCommand instanceof PostCommand);
	}

	@Test
	public void getFollowCommandTest() {
		Command returnedCommand = commandInterpreter.getCommand(FOLLOW_COMMAND);
		Assert.assertTrue(returnedCommand instanceof FollowCommand);
	}

	@Test
	public void getReadCommandTest() {
		Command returnedCommand = commandInterpreter.getCommand(USER);
		Assert.assertTrue(returnedCommand instanceof ReadCommand);
	}

	@Test
	public void getWallCommandTest() {
		Command returnedCommand = commandInterpreter.getCommand(WALL_COMMAND);
		Assert.assertTrue(returnedCommand instanceof WallCommand);
	}
}
