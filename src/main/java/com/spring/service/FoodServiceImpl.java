package com.spring.service;

import com.spring.model.FoodItem;
import com.spring.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }
    @Override
    public FoodItem getById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

}
