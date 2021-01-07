package com.droideparanoico.consoletwitter.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.droideparanoico.consoletwitter.domain.User;

public class UserRepositoryTest {
	private static final String USER = "David";

	private UserRepository userRepository;

	@Before
	public void setUp() {
		userRepository = new UserRepository();
	}

	@Test
	public void testGetOrCreateUser() {
		User user = userRepository.getOrCreateUser(USER);
		Assert.assertNotNull(user);
		Assert.assertEquals(USER, user.getName());
	}

	@Test
	public void testGetExistingUser() {
		User user = userRepository.getOrCreateUser(USER);
		User existingUser = userRepository.getOrCreateUser(USER);
		Assert.assertEquals(user, existingUser);
	}
}
