package com.droideparanoico.consoletwitter.repository;

import java.util.HashMap;
import java.util.Map;

import com.droideparanoico.consoletwitter.domain.User;

public class UserRepository implements Repository {
	private final Map<String, User> userMap = new HashMap<>();
	
	public User getOrCreateUser(String userName) {
		User user = userMap.get(userName);
		if (user == null) {
			user = new User(userName);
			userMap.put(userName, user);
		}
		return user;
	}

}
