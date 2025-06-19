package com.spring.service;

import com.spring.model.CartItem;
import com.spring.model.FoodItem;
import com.spring.model.User;
import com.spring.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Override
    public void addToCart(User user, FoodItem foodItem, int quantity) {
        CartItem item = new CartItem();
        item.setUser(user);
        item.setFoodItem(foodItem);
        item.setQuantity(quantity);
        cartRepo.save(item);
    }

    @Override
    public List<CartItem> getCartItems(User user) {
        return cartRepo.findByUser(user);
    }

    @Override
    public void clearCart(User user) {
        cartRepo.deleteByUser(user);
    }
}
