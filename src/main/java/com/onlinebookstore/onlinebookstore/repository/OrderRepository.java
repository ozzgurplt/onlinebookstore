package com.onlinebookstore.onlinebookstore.repository;

import com.onlinebookstore.onlinebookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByUpdatedAtDesc(Long userId);
}
