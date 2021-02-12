package com.droideparanoico.consoletwitter.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import com.droideparanoico.consoletwitter.ui.ConsoleTwitterPrinter;
import com.droideparanoico.consoletwitter.ui.Printer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InvalidCommandTest {
	private static final String USER = "David cucu";

	private InvalidCommand command;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		Printer printer = new ConsoleTwitterPrinter(printStream);
		command = new InvalidCommand(printer);
	}

	@Test
	public void testMatch() {
		Assert.assertFalse(command.matches(USER));
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
		command.execute(USER);

		String result = baos.toString(StandardCharsets.UTF_8);
		String expectedResult = String.format("Command: %s is invalid!\r\n", USER);
		Assert.assertEquals(expectedResult, result);
	}
}
