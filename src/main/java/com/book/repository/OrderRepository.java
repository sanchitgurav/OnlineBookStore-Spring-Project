package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.book.entity.Order;
import com.book.entity.User;

/*
 * Sanchit Gurav
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Custom query method to find orders by the associated user
	List<Order> findByUser(User user);

}
