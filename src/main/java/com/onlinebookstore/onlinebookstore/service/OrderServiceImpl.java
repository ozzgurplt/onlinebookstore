package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.entities.Book;
import com.onlinebookstore.onlinebookstore.entities.Order;
import com.onlinebookstore.onlinebookstore.entities.User;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderDetails;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderRequest;
import com.onlinebookstore.onlinebookstore.exception.MinimumOrderAmountException;
import com.onlinebookstore.onlinebookstore.exception.OrderNotFoundException;
import com.onlinebookstore.onlinebookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserService userService;

    private BookService bookService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.bookService = bookService;
    }


    @Override
    public Order placeOrder(OrderRequest orderRequest) {

        User user = userService.getUserById(orderRequest.getUserId());
        List<Book> books = bookService.getBooksByIsbnList(orderRequest.getIsbnList());


        double totalAmount = books.stream().mapToDouble(Book::getPrice).sum();
        if (totalAmount < 25) {
            throw new MinimumOrderAmountException("Minimum order amount should be 25$.");
        }


        Order order = new Order();
        order.setUser(user);
        order.setBooks(books);
        order.setTotalPrice(totalAmount);
        order.setOrderDate(LocalDateTime.now());


        return orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    @Override
    public OrderDetails getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));


        return createOrderDetails(order);
    }

    private OrderDetails createOrderDetails(Order order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(order.getOrderId());
        orderDetails.setUserId(order.getUser().getId());
        orderDetails.setTotalPrice(order.getTotalPrice());
        orderDetails.setOrderDate(order.getOrderDate());
        orderDetails.setBooks(order.getBooks());
        return orderDetails;
    }


}
