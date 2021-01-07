package com.droideparanoico.consoletwitter.command;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
public class ReadCommandTest {
	private static final String USER = "David";

	private ReadCommand command;

	@Mock
	private Repository userRepository;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		Printer printer = new ConsoleTwitterPrinter(printStream);
		command = new ReadCommand(userRepository, printer);
	}

	@Test
	public void testMatch() {
		Assert.assertTrue(command.matches(USER));
	}

	@Test
	public void testMatchNull() {
		Assert.assertFalse(command.matches(null));
	}

	@Test
	public void testMatchEmptyl() {
		Assert.assertFalse(command.matches(""));
	}

	@Test
	public void testRead() {
		User user = spy(new User(USER));
		user.setPosts(Arrays.asList(new Message(USER, "Happy new year!", new Date(
				System.currentTimeMillis() - 5 * 1000L)),
				new Message(USER, "Best wishes to everyone", new Date(System.currentTimeMillis() - 10 * 1000L))));

		when(userRepository.getOrCreateUser(USER)).thenReturn(user);

		command.execute(USER);

		verify(userRepository).getOrCreateUser(USER);

		String result = baos.toString(StandardCharsets.UTF_8);
		String expectedResult = "Happy new year! (5 seconds ago)\r\nBest wishes to everyone (10 seconds ago)\r\n";
		Assert.assertEquals(expectedResult, result);
	}
}
