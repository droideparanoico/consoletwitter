package com.droideparanoico.consoletwitter.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.droideparanoico.consoletwitter.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.droideparanoico.consoletwitter.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class MessageCommandTest {
	private static final String USER = "David";
	private static final String MESSAGE = "Happy new year!";
	private static final String COMMAND = USER + " -> " + MESSAGE;

	private PostCommand command;

	@Mock
	private Repository userRepository;

	@Before
	public void setUp() {
		command = new PostCommand(userRepository);
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
	public void testMatchEmptyl() {
		Assert.assertFalse(command.matches(""));
	}

	@Test
	public void testPost() {
		User user = mock(User.class);

		when(userRepository.getOrCreateUser(USER)).thenReturn(user);

		command.execute(COMMAND);

		verify(userRepository).getOrCreateUser(USER);
		verify(user).addPost(MESSAGE);

	}

}
