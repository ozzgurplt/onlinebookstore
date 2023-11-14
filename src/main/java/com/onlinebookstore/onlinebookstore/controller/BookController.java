package com.onlinebookstore.onlinebookstore.controller;

import com.onlinebookstore.onlinebookstore.entities.Book;
import com.onlinebookstore.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooksOrderedByCreationDateDesc();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.ok(addedBook);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(isbn, book);
        return ResponseEntity.ok(updatedBook);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {

        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}
