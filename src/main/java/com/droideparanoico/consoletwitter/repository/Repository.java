package com.droideparanoico.consoletwitter.repository;

import com.droideparanoico.consoletwitter.domain.User;

public interface Repository {
	/**
	 * Recovers the user from the repository, if the user does not exist, the method creates it.
	 * 
	 * @param userName
	 *            The username.
	 * @return The requested user.
	 * 
	 */
	User getOrCreateUser(String userName);

}
