package com.droideparanoico.consoletwitter.command;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import com.droideparanoico.consoletwitter.domain.Message;
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
public class WallCommandTest {
	private static final String USER = "David";
	private static final String USER_2 = "Julia";
	private static final String COMMAND = USER + " wall";

	private WallCommand command;
	@Mock
	private Repository userRepository;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		Printer printer = new ConsoleTwitterPrinter(printStream);
		command = new WallCommand(userRepository, printer);
	}

	@Test
	public void testMatch() {
		Assert.assertTrue(command.matches(COMMAND));
	}

	@Test
	public void testMatchNull() {
		Assert.assertFalse(command.matches(null));
	}

	@Test
	public void testMatchEmpty() {
		Assert.assertFalse(command.matches(""));
	}

	@Test
	public void testRead() {
		User user = spy(new User(USER));
		user.setPosts(Arrays.asList(new Message(USER, "Happy new year!", new Date(
				System.currentTimeMillis() - 5 * 1000L)),
				new Message(USER, "Best wishes to everyone", new Date(System.currentTimeMillis() - 20 * 1000L))));

		User user2 = spy(new User(USER_2));
		user2.setPosts(Collections.singletonList(new Message(USER_2, "Time to get cozy", new Date(System.currentTimeMillis() - 10 * 1000L))));

		user.addFollowing(user2);

		when(userRepository.getOrCreateUser(USER)).thenReturn(user);

		command.execute(COMMAND);

		verify(userRepository).getOrCreateUser(USER);
		verify(user).getPosts();
		verify(user).getFollowingUsers();

		verify(user2).getPosts();

		String result = baos.toString(StandardCharsets.UTF_8);
		String expectedResult = "David - Happy new year! (5 seconds ago)\r\nJulia - Time to get cozy (10 seconds ago)\r\nDavid - Best wishes to everyone (20 seconds ago)\r\n";
		Assert.assertEquals(expectedResult, result);
	}
}
