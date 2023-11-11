package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.User;

/*
 * Sanchit Gurav
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Custom query method to find a user by their email
	public User findByEmail(String email);

}
