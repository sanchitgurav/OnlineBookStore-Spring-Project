package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.book.entity.User;
import com.book.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

/*
 * Sanchit Gurav
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	// Save a new user with encrypted password and default role
	public User saveUser(User user) {
		// Encode the user's password before saving
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");	// Set the user role
		User newuser = userRepository.save(user);
		return newuser;
	}

	// Remove a session attribute used to display messages
	public void removeSessionMessage() {
		// Remove a session attribute (used to remove messages)
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("msg");
	}

	// Retrieve user details by ID
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

	// Update user details
	public void updateUserDetails(User updatedUser) {
		// Retrieve the existing user details
		User existingUser = getUserById(updatedUser.getId());
		if (existingUser != null) {
			// Update the existing user's details with the new values
			existingUser.setName(updatedUser.getName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setContact(updatedUser.getContact());
			// Save the updated user back to the database
			userRepository.save(existingUser);
		}
	}

	// Get a list of all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Delete a user by their ID
	public void deleteUser(int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			// Handle exceptions, e.g., when the user cannot be deleted
			e.printStackTrace();
		}
	}

}
