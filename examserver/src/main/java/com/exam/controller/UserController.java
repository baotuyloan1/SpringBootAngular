package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * create user
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>();

		user.setProfile("default.png");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

//		encoding password with bcrypt_password_encoder
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		roles.add(userRole);
		return this.userService.createUser(user, roles);
	}

	/**
	 * get user by username
	 * 
	 * @param userName
	 * @return
	 */
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String userName) {
		return this.userService.getUser(userName);
	}

	/**
	 * delete the user by id
	 * 
	 * @param userId
	 */
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}

//	update api

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex);
	}
}
