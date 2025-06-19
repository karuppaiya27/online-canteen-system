package com.spring.service;

import com.spring.model.CartItem;
import com.spring.model.Order;
import com.spring.model.User;

import java.util.List;

public interface OrderService {
    Order placeOrder(User user, List<CartItem> cartItems, String paymentMethod);
    List<Order> getOrdersByUser(User user);
}
