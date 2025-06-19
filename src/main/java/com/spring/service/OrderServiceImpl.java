package com.spring.service;

import com.spring.model.*;
import com.spring.repository.OrderItemRepository;
import com.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Override
    public Order placeOrder(User user, List<CartItem> cartItems, String paymentMethod) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentMethod(paymentMethod);

        double total = 0;
        List<OrderItem> items = new ArrayList<>();

        for (CartItem cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setFoodItem(cart.getFoodItem());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getFoodItem().getPrice());

            total += cart.getQuantity() * cart.getFoodItem().getPrice();
            items.add(item);
        }

        order.setTotalAmount(total);
        order.setOrderItems(items);
        Order savedOrder = orderRepo.save(order);
        orderItemRepo.saveAll(items);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepo.findByUser(user);
    }
}
