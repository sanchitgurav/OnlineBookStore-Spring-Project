package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.entity.CartItem;
import com.book.entity.User;

/*
 * Sanchit Gurav
 */

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    // Custom query method to find cart items by the associated user
	public List<CartItem> findByUser(User user);

	@Query("SELECT c FROM CartItem c WHERE c.user.email = :email")
    List<CartItem> findCartItemsByUserEmail(@Param("email") String email);
	
}
