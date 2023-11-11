package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.entity.CartItem;
import com.book.entity.User;
import com.book.repository.CartItemRepository;
import com.book.repository.UserRepository;

/*
 * Sanchit Gurav
 */

@Service
public class ShoppingCartService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserRepository userRepository;

	// Get a list of cart items for a user
	public List<CartItem> listCartItems(User user) {
		return cartItemRepository.findByUser(user);
	}

	// Add a book to the shopping cart
	public void addToCart(String username, Book selectedBook) {
		// Fetch the user
		User user = userRepository.findByEmail(username);
		// Create a CartItem
		CartItem cartItem = new CartItem();
		cartItem.setBook(selectedBook);
		cartItem.setUser(user);
		cartItem.setQuantity(1); // Set the quantity as needed
		// Add the item to the shopping cart
		cartItemRepository.save(cartItem);
	}

	// Delete a cart item by its ID
	public void deleteCartItemById(int cartItemId, String username) {
		User user = userRepository.findByEmail(username);
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if (user != null && cartItem.isPresent()) {
			// Ensure that the cart item belongs to the user
			if (cartItem.get().getUser().getId() == user.getId()) {
				cartItemRepository.delete(cartItem.get());
			}
		}
	}
	
	public void clearCart(String username) {
		// Find all cart items for the given user
	    List<CartItem> cartItems = cartItemRepository.findCartItemsByUserEmail(username);

	    // Delete each cart item
	    for (CartItem cartItem : cartItems) {
	        cartItemRepository.delete(cartItem);
	    }
	}

}
