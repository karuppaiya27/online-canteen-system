package com.spring.service;

import com.spring.model.CartItem;
import com.spring.model.FoodItem;
import com.spring.model.User;

import java.util.List;

public interface CartService {
    void addToCart(User user, FoodItem foodItem, int quantity);
    List<CartItem> getCartItems(User user);
    void clearCart(User user);
}
