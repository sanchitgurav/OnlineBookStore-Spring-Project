package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

/*
 * Sanchit Gurav
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Custom query method to find a book by its ID
	Book findById(Long id);

}
