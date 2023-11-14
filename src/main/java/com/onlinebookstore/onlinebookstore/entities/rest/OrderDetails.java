package com.onlinebookstore.onlinebookstore.entities.rest;

import com.onlinebookstore.onlinebookstore.entities.Book;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetails {
    private Long orderId;
    private Long userId;
    private double totalPrice;
    private LocalDateTime orderDate;
    private List<Book> books;

    public void setBooks(List<Book> books) {
    }
}
