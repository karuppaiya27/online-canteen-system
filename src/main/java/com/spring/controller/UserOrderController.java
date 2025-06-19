package com.spring.controller;

import com.spring.model.CartItem;
import com.spring.model.Order;
import com.spring.model.User;
import com.spring.service.CartService;
import com.spring.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String paymentMethod, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CartItem> cartItems = cartService.getCartItems(user);

        if (cartItems.isEmpty()) return "redirect:/cart";

        orderService.placeOrder(user, cartItems, paymentMethod);
        cartService.clearCart(user);

        return "checkout-success";
    }

    @GetMapping("/orders")
    public String viewOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.getOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "user-orders";
    }
}
