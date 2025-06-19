package com.spring.service;

import com.spring.model.FoodItem;
import java.util.List;

public interface FoodService {
    List<FoodItem> getAllFoodItems();
    FoodItem getById(Long id); // ✅ Required for CartController
}
