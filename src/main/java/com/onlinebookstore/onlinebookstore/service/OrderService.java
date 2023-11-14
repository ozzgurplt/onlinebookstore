package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.entities.Book;
import com.onlinebookstore.onlinebookstore.entities.Order;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderDetails;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);
    List<Order> getUserOrders(Long userId);

    OrderDetails getOrderDetails(Long orderId);



}
