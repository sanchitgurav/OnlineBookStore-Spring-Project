package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;

/*
 * Sanchit Gurav
 */

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	// Save a book to the database
	public void save(Book b) {
		bookRepository.save(b);
	}

	// Retrieve a list of all books in the database
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	// Retrieve a book by its ID
	public Book getBookById(int id) {
		return bookRepository.findById(id).get();
	}

	// Delete a book by its ID
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}

}
