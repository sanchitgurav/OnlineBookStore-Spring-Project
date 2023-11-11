package com.book.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entity.Order;
import com.book.entity.User;
import com.book.repository.UserRepository;
import com.book.service.OrderService;
import com.book.service.ShoppingCartService;

/*
 * Sanchit Gurav
 */

@Controller
@RequestMapping("/user")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShoppingCartService cartService;

	// Create an order
	@GetMapping("/placeOrder")
	public String placeOrder(Principal principal, Model model, @RequestParam("totalAmount") double totalAmount) {
		String username = principal.getName();
		User user = userRepository.findByEmail(username);
		Order order = orderService.createOrder(user, totalAmount);
		
		// Clear the user's cart after placing the order
        cartService.clearCart(username);
		
		return "redirect:/user/orders";
	}

	// Retrieve and display orders for a user
	@GetMapping("/orders")
	public String viewOrders(Principal principal, Model model) {
		String username = principal.getName();
		User user = userRepository.findByEmail(username);
		List<Order> orders = orderService.getOrdersByUser(user);
		model.addAttribute("orders", orders);
		return "orderList";
	}

}
