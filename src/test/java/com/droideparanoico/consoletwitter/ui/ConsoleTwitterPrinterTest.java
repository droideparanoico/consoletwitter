package com.droideparanoico.consoletwitter.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.droideparanoico.consoletwitter.domain.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleTwitterPrinterTest {
	private ConsoleTwitterPrinter printer;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		printer = new ConsoleTwitterPrinter(printStream);
	}

	@Test
	public void testDisplayPostForUserWithSeconds() {
		Message message = new Message("David", "Happy new year!", new Date(System.currentTimeMillis() - 10 * 1000L));
		printer.printPostForUser(message);
		String expectedResult = "Happy new year! (10 seconds ago)\r\n";
		Assert.assertEquals(expectedResult, baos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void testDisplayPostForUserWithMinutes() {
		Message message = new Message("David", "Happy new year!", new Date(System.currentTimeMillis() - 10 * 60 * 1000L));
		printer.printPostForUser(message);
		String expectedResult = "Happy new year! (10 minutes ago)\r\n";
		Assert.assertEquals(expectedResult, baos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void testDisplayPostForUserWithHours() {
		Message message = new Message("David", "Happy new year!", new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000L));
		printer.printPostForUser(message);
		String expectedResult = "Happy new year! (2 hours ago)\r\n";
		Assert.assertEquals(expectedResult, baos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void testDisplayPostForUserWithDays() {
		Message message = new Message("David", "Happy new year!", new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L));
		printer.printPostForUser(message);
		String expectedResult = "Happy new year! (1 day ago)\r\n";
		Assert.assertEquals(expectedResult, baos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void testDisplayPostForWall() {
		Message message = new Message("David", "Happy new year!", new Date(System.currentTimeMillis() - 10 * 1000L));
		printer.printPostForWall(message);
		String expectedResult = "David - Happy new year! (10 seconds ago)\r\n";
		Assert.assertEquals(expectedResult, baos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void testDisplayMessage() {
		String message = "Hello!";
		printer.printMessage(message);
		Assert.assertEquals(message + "\r\n", baos.toString(StandardCharsets.UTF_8));
	}
}
