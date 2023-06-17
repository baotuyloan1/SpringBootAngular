package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	// creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	// get user by username
	public User getUser(String userName);

	// delete user by ud
	public void deleteUser(Long userId);

}
