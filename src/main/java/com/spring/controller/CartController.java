// File: CartController.java
package com.spring.controller;

import com.spring.model.CartItem;
import com.spring.model.FoodItem;
import com.spring.model.User;
import com.spring.service.CartService;
import com.spring.service.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/cart/add/{foodId}")
    public String addToCart(@PathVariable Long foodId, @RequestParam int quantity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        FoodItem item = foodService.getById(foodId);

        cartService.addToCart(user, item, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<CartItem> cartItems = cartService.getCartItems(user);

        double total = cartItems.stream()
            .mapToDouble(ci -> ci.getQuantity() * ci.getFoodItem().getPrice())
            .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart";
    }
    @GetMapping("/checkout")
    public String showCheckoutSuccess() {
        return "checkout-success";
    }

}
