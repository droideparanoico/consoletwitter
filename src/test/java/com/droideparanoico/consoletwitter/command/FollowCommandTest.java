package com.droideparanoico.consoletwitter.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
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
public class FollowCommandTest {
	private static final String USER_1 = "David";
	private static final String USER_2 = "Julia";
	private static final String COMMAND = USER_1 + " follows " + USER_2;

	private FollowCommand command;

	@Mock
	private Repository userRepository;

	@Before
	public void setUp() {
		command = new FollowCommand(userRepository);
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
	public void testFollow() {
		User user1 = spy(new User(USER_1));
		User user2 = mock(User.class);

		when(userRepository.getOrCreateUser(USER_1)).thenReturn(user1);
		when(userRepository.getOrCreateUser(USER_2)).thenReturn(user2);

		command.execute(COMMAND);

		verify(userRepository).getOrCreateUser(USER_1);
		verify(userRepository).getOrCreateUser(USER_2);
		verify(user1).addFollowing(user2);
		Assert.assertTrue(user1.getFollowingUsers().contains(user2));
	}

}
