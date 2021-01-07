package com.droideparanoico.consoletwitter.domain;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	private User user;

	@Before
	public void setup() {
		user = new User("David");
	}

	@Test
	public void testPost() {
		user.addPost("Happy new year!");
		Assert.assertEquals(1, user.getPosts().size());
		Assert.assertEquals("Happy new year!", user.getPosts().get(0).getMessage());
		Assert.assertEquals("David", user.getPosts().get(0).getUserName());
	}

	@Test
	public void testFollowing() {
		user.addFollowing(new User("Julia"));
		Assert.assertEquals(1, user.getFollowingUsers().size());
		Assert.assertEquals("Julia", user.getFollowingUsers().get(0).getName());
	}

	@Test
	public void testUniqueFollowing() {
		user.addFollowing(new User("Julia"));
		user.addFollowing(new User("Julia"));
		Assert.assertEquals(1, user.getFollowingUsers().size());
	}

	@Test
	public void testSetFollowing() {
		user.setFollowingUsers(Collections.singletonList(new User("Julia")));
		Assert.assertEquals(1, user.getFollowingUsers().size());
	}

}
