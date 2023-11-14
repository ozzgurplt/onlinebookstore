package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.entities.Book;
import com.onlinebookstore.onlinebookstore.exception.BookNotFoundException;
import com.onlinebookstore.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooksOrderedByCreationDateDesc() {
        return bookRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String isbn, Book updatedBook) {
        Book existingBook = getBookByIsbn(isbn);
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(String isbn) {

        Book existingBook = getBookByIsbn(isbn);
        bookRepository.delete(existingBook);
    }

    @Override
    public List<Book> getBooksByIsbnList(List<String> isbnList) {
        return bookRepository.findByIsbnIn(isbnList);
    }
}
