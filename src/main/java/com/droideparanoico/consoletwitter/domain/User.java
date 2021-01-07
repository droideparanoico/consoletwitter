package com.droideparanoico.consoletwitter.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<Message> messages = new ArrayList<>();
	private List<User> followingUsers = new ArrayList<>();

	public User(String userName) {
		this.name = userName;
	}

	public void addPost(String message) {
		messages.add(new Message(name, message));
	}

	public void addFollowing(User user) {
		if (!followingUsers.contains(user)) {
			followingUsers.add(user);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Message> getPosts() {
		return messages;
	}

	public void setPosts(List<Message> messages) {
		this.messages = messages;
	}

	public List<User> getFollowingUsers() {
		return followingUsers;
	}

	public void setFollowingUsers(List<User> followingUsers) {
		this.followingUsers = followingUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return name.equals(other.name);
	}
}
