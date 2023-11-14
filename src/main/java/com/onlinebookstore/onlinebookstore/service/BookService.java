package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooksOrderedByCreationDateDesc();

    Book getBookByIsbn(String isbn);

    Book addBook(Book book);

    Book updateBook(String isbn, Book updatedBook);

    void deleteBook(String isbn);

    List<Book> getBooksByIsbnList(List<String> isbnList);
}
