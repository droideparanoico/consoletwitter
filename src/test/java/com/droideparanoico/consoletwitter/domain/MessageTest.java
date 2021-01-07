package com.droideparanoico.consoletwitter.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest {

	@Test
	public void testPost() {
		Message message = new Message("David", "Happy new year!");
		Assert.assertEquals("Happy new year!", message.getMessage());
		Assert.assertEquals("David", message.getUserName());
		Assert.assertNotNull(message.getPublishDate());
	}

}
