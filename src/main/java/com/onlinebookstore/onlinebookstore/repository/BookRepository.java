package com.onlinebookstore.onlinebookstore.repository;

import com.onlinebookstore.onlinebookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByCreatedAtDesc();

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByIsbnIn(List<String> isbnList);
}
